package com.zka.lyceena.services;

import com.zka.lyceena.entities.files.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FilesService {
    File store(MultipartFile file) throws IOException;
    File getFile(String id);
    void deleteFile(String id);
    List<File> findBySessionAttendanceId(Long sessionAttandenceId);
}
