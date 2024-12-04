package com.sparta.schedule.dto;


import com.sparta.schedule.entity.Scheduleitem;
import lombok.Getter;

@Getter
public class ResponseDto {

    private Long id;
    private String author;
    private String contents ;

    public ResponseDto(){} //TODO 기본 생성자가 필요! massagecontroller 가 서버에 맞는 형태로 바꿔줄때에 필요하다.

    public ResponseDto(Scheduleitem scheduleitem){

        this.id = scheduleitem.getId();
        this.author = scheduleitem.getAuthor();
        this.contents = scheduleitem.getContents();


    }


}
