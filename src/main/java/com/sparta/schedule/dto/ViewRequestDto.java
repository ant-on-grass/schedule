package com.sparta.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ViewRequestDto { //TODO 갈때의 dto 다른 서버로

    private Long id;

    public ViewRequestDto() {} // viewAll 용

}


