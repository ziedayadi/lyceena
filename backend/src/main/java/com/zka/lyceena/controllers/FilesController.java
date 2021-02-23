package com.zka.lyceena.controllers;

import com.zka.lyceena.dto.file.ResponseFile;
import com.zka.lyceena.entities.files.File;
import com.zka.lyceena.services.FilesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/files")
public class FilesController {

    private static final Logger LOGGER = LogManager.getLogger(FilesController.class);
    @Autowired
    private FilesService filesService;

    @PostMapping("/upload")
    public ResponseEntity<File> upload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("sessionId") Long sessionId) {
        try {
            return ResponseEntity.ok(this.filesService.store(multipartFile, sessionId));
        } catch (Exception e) {
            LOGGER.error("/upload", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/session/{sessionId}")
    public ResponseEntity<List<ResponseFile>> findBySessionAttendanceId(@PathVariable("sessionId") Long sessionId) {
        try {
            List<ResponseFile> responseFiles = this.filesService.findBySessionAttendanceId(sessionId)
                    .stream()
                    .map(f -> new ResponseFile(f.getName(), f.getId(), f.getType(), f.getData().length)
                    ).collect(Collectors.toList());

            return ResponseEntity.ok(responseFiles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/file/{fileId}")
    public ResponseEntity<byte[]> getFile(@PathVariable("fileId") String id) {
        File file = this.filesService.getFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(file.getData());
    }

    @DeleteMapping("/file/{fileId}")
    public void deleteFile(@PathVariable("fileId") String id) {
        this.filesService.deleteFile(id);
    }
}
