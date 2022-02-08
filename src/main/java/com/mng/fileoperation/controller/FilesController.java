package com.mng.fileoperation.controller;

import com.mng.fileoperation.model.FileData;
import com.mng.fileoperation.model.UploadResponseMessage;
import com.mng.fileoperation.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("files")
@RequiredArgsConstructor
@Api(value = "FileData Api documentation")
public class FilesController {

    private final FileService fileService;

    @PostMapping
    @ApiOperation(value = "Upload a new file method")
    public ResponseEntity<UploadResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            fileService.save(file);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new UploadResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new UploadResponseMessage("Could not upload the file: " + file.getOriginalFilename()
                            + "! "+e.getMessage()));
        }
    }

    @PutMapping("{fileId:.+}")
    @ApiOperation(value = "Update a file method")
    public ResponseEntity<UploadResponseMessage> updateFile(@RequestParam("file") MultipartFile file
            , @PathVariable Long fileId) {
        try {
            fileService.updateFile(file, fileId);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new UploadResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new UploadResponseMessage("Could not upload the file: " + file.getOriginalFilename()
                            + "! "+e.getMessage()));
        }
    }

    @GetMapping
    @ApiOperation(value = "Get all files method")
    public List<FileData> getListFiles() {
        return fileService.loadAll();

    }

    @GetMapping("{filename:.+}")
    @ApiOperation(value = "Get file content method")
    @ResponseBody
    public byte[] getFile(@PathVariable String filename) throws IOException {
        return fileService.readFile(filename);
    }

    @DeleteMapping("{filename:.+}")
    @ApiOperation(value = "Delete a file method")
    @ResponseBody
    public void deleteFile(@PathVariable String filename) throws IOException {
        fileService.deleteFile(filename);
    }
}
