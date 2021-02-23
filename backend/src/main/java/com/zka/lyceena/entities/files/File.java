package com.zka.lyceena.entities.files;

import com.zka.lyceena.entities.attendance.SessionAttendance;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "FILE")
public class File {

    @Id
    @Column(name = "ID")
    private String id = UUID.randomUUID().toString();

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private String type;

    @Lob
    @Column(name = "DATA")
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "SESSION_ATTENDANCE")
    private SessionAttendance sessionAttendance;

    public File(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }
}
