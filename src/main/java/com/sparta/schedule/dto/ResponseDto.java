package com.sparta.schedule.dto;


import com.sparta.schedule.entity.Scheduleitem;
import lombok.Getter;

@Getter
public class ResponseDto {

    private Long id;
    private String author;
    private String contents ;


    public ResponseDto(Scheduleitem scheduleitem){

        this.id = scheduleitem.getId();
        this.author = scheduleitem.getAuthor();
        this.contents = scheduleitem.getContents();


    }


}
