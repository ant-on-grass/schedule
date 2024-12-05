package com.sparta.schedule.dto;


import com.sparta.schedule.entity.Scheduleitem;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseDto { //TODO client 로 돌아오는 dto

    private Long id;
    private String author;
    private String contents ;
    private LocalDateTime fixDate;
    private LocalDateTime flexDate;

    public ResponseDto() {}

    public ResponseDto(Scheduleitem scheduleitem){

        this.id = scheduleitem.getId();
        this.author = scheduleitem.getAuthor();
        this.contents = scheduleitem.getContents();
        this.flexDate = scheduleitem.getFlexDate();
        this.fixDate = scheduleitem.getFlexDate();




    }


}
