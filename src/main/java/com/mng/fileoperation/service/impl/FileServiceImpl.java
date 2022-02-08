package com.mng.fileoperation.service.impl;

import com.google.common.base.Enums;
import com.mng.fileoperation.constant.FileExtension;
import com.mng.fileoperation.model.FileData;
import com.mng.fileoperation.repository.FileRepository;
import com.mng.fileoperation.service.FileService;
import com.mng.fileoperation.validator.FileServiceValidationRule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    @Value("${upload.path}")
    private String uploadPath;


    private final FileRepository fileRepository;

    @ExceptionHandler(MultipartException.class)
    public String handleError1(MultipartException e, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
        return "redirect:/uploadStatus";

    }

    @Override
    public FileData save(MultipartFile file) {
        FileData fileData = new FileData();
        try {
            fileData.setFileName(file.getOriginalFilename());
            fileData.setPath(uploadPath);
            fileData.setSize(file.getSize());
            fileData.setFileExtension(checkAndReturnFileExtension(getExtensionByStringHandling(file.getOriginalFilename()).get()));
            Path root = Paths.get(uploadPath);
            if (!Files.exists(root)) {
                init();
            }
            Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()));
            fileRepository.save(fileData);
        }

         catch (Exception e) {
             throw new RuntimeException(e.getMessage());
        }

     return fileData;
    }

    @Override
    public FileData updateFile(MultipartFile file, Long fileId) {

        FileData fileData = fileRepository.findById(fileId).orElseThrow();

        try {
            deleteFile(fileData.getFileName());
            fileData.setFileName(file.getOriginalFilename());
            fileData.setPath(uploadPath);
            fileData.setSize(file.getSize());
            fileData.setFileExtension(checkAndReturnFileExtension(getExtensionByStringHandling(file.getOriginalFilename()).get()));


            Path root = Paths.get(uploadPath);
            if (!Files.exists(root)) {
                init();
            }
            Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()));
            fileRepository.save(fileData);
        }

        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return fileData;
    }



    @Override
    public byte[] readFile(String fileName) throws IOException {
        File file = new File(uploadPath + "/" + fileName);
        Path path = Paths.get(file.getAbsolutePath());
        return Files.readAllBytes(path);
    }



    @Override
    public void deleteFile(String fileName) throws IOException {
        File file = new File(uploadPath + "/" + fileName);
        Path path = Paths.get(file.getAbsolutePath());
        FileSystemUtils.deleteRecursively(path);
    }

    @Override
    public List<FileData> loadAll() {
       return fileRepository.findAll();

    }

    @PostConstruct
    private void init() {
        try {
            Files.createDirectories(Paths.get(uploadPath));
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload folder!");
        }
    }

    private Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    private FileExtension checkAndReturnFileExtension(String fileExtension) {
        if (Enums.getIfPresent(FileExtension.class, fileExtension).isPresent()) {
            return FileExtension.valueOf(fileExtension);
        }
        else {
            throw new RuntimeException(FileServiceValidationRule.UNEXPECTED_FILE_EXTENSION_ERROR.getDescription());
        }
    }

}
