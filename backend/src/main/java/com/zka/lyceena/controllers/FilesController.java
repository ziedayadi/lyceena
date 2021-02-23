package com.zka.lyceena.controllers;

import com.zka.lyceena.entities.files.File;
import com.zka.lyceena.services.FilesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FilesController {

    private static final Logger LOGGER = LogManager.getLogger(FilesController.class);
    @Autowired
    private FilesService filesService;

    @PostMapping("/upload")
    public ResponseEntity<File> upload(@RequestParam("file") MultipartFile multipartFile,@RequestParam("sessionId") Long sessionId) {
        try {
            return ResponseEntity.ok(this.filesService.store(multipartFile, sessionId));
        } catch (Exception e) {
            LOGGER.error("/upload",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
