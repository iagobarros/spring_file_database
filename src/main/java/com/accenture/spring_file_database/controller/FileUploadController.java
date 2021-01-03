package com.accenture.spring_file_database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.accenture.spring_file_database.dto.DatabaseFileDto;
import com.accenture.spring_file_database.model.DatabaseFile;
import com.accenture.spring_file_database.service.DatabaseFileService;

@RestController
public class FileUploadController {

    @Autowired
    private DatabaseFileService fileStorageService;

    @PostMapping("/uploadFile")
    public DatabaseFileDto uploadFile(@RequestParam("file") MultipartFile file) {
        DatabaseFile fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName.getFileName())
                .toUriString();

        return new DatabaseFileDto(fileName.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

}
