package com.sparta.schedule.dto;


import lombok.Getter;

@Getter
//@AllArgsConstructor
public class CreateRequestDto { //TODO 갈때의 dto 다른 서버로

    private String author;
    private String contents ;
    private String password ;

    }
