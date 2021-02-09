package com.zka.lyceena.entities.trace;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name="HTTP_TRACE")
public class LyceenaHttpTrace {

    @Id
    @Column(name = "ID")
    private String id = UUID.randomUUID().toString();

    @Column(name = "METHOD")
    private String method;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "RESPONSE_STATUS")
    private Integer responseStatus;

    @Column(name = "TIMESTAMP")
    private Instant timestamp;

    @Column(name = "TIME_TAKEN")
    private Long timeTaken;

    @Column(name = "URI")
    private String uri;

}
