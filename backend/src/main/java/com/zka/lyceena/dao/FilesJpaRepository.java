package com.zka.lyceena.dao;

import com.zka.lyceena.entities.files.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilesJpaRepository extends JpaRepository<File,String> {

    @Query("Select f from File f where " +
            "f.sessionAttendance.id = :sessionAttendanceId")
    List<File> findBySessionAttendanceId(@Param("sessionAttendanceId") Long sessionAttendanceId);
}
