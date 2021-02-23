package com.zka.lyceena.services;

import com.zka.lyceena.dao.FilesJpaRepository;
import com.zka.lyceena.dao.SessionAttendanceJpaRepository;
import com.zka.lyceena.entities.attendance.SessionAttendance;
import com.zka.lyceena.entities.files.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class FilesServiceImpl implements FilesService {

    @Autowired
    private FilesJpaRepository filesJpaRepository;

    @Autowired
    private SessionAttendanceJpaRepository sessionAttendanceJpaRepository;

    @Override
    public File store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        File fileEntity = new File(fileName, file.getContentType(), file.getBytes());
        return filesJpaRepository.save(fileEntity);
    }

    @Override
    public File store(MultipartFile file, Long sessionId) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        File fileEntity = new File(fileName, file.getContentType(), file.getBytes());
        SessionAttendance sessionAttendance = this.sessionAttendanceJpaRepository.findById(sessionId).orElse(null);
        fileEntity.setSessionAttendance(sessionAttendance);
        return this.filesJpaRepository.save(fileEntity);
    }

    @Override
    public File getFile(String id) {
        return filesJpaRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteFile(String id) {
        this.filesJpaRepository.deleteById(id);
    }

    @Override
    public List<File> findBySessionAttendanceId(Long sessionAttendanceId) {
        return this.filesJpaRepository.findBySessionAttendanceId(sessionAttendanceId);
    }
}
