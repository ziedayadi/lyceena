package com.zka.lyceena.dto;

import lombok.Data;

@Data
public class UpdateSessionDto {

    private Integer classRoomId;
    private Integer dayId;
    private Integer startHourId;
    private Integer endHourId;
    private Integer oldSessionId;

}
