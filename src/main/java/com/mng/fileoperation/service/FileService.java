package com.mng.fileoperation.service;

import com.mng.fileoperation.model.FileData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
     FileData save(MultipartFile file);
     List<FileData> loadAll();
     void deleteFile(String fileName) throws IOException;
     byte[] readFile(String fileName) throws IOException;
     FileData updateFile(MultipartFile file, Long fileId);
}
