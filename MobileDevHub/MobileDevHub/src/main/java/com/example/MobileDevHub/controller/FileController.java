package com.example.MobileDevHub.controller;

import com.example.MobileDevHub.entity.FileUpload;
import com.example.MobileDevHub.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam("entityId") Long entityId,
                                             @RequestParam("entityType") String entityType) {
        String message = fileService.uploadFile(file, entityId, entityType);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("/download/{entityType}/{entityId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String entityType,
                                                 @PathVariable Long entityId) {
        FileUpload fileUpload = fileService.getFileByEntity(entityType, entityId);
        ByteArrayResource resource = new ByteArrayResource(fileUpload.getFileContent().getBytes());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileUpload.getFileName() + "\"")
                .body(resource);
    }
}
