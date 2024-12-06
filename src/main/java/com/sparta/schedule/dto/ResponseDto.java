package com.sparta.schedule.dto;


import com.sparta.schedule.entity.Scheduleitem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDto { //TODO client 로 돌아오는 dto

    private Long id;
    private String author;
    private String contents ;
    private LocalDateTime fixDate;
    private LocalDateTime flexDate;

    public ResponseDto(){} //TODO 기본 생성자가 필요! massagecontroller 가 서버에 맞는 형태로 바꿔줄때에 필요하다.

    public ResponseDto(Scheduleitem scheduleitem){

        this.id = scheduleitem.getId();
        this.author = scheduleitem.getAuthor();
        this.contents = scheduleitem.getContents();
        this.fixDate = scheduleitem.getFixDate();
        this.flexDate = scheduleitem.getFlexDate();



    }


}
