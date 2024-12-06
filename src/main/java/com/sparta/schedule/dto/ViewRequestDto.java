package com.sparta.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
// 자동 getter 를 만들어주는 annotation

@AllArgsConstructor
// 자동 생성자를 만들어주는 annotation

public class ViewRequestDto { //TODO 갈때의 dto 다른 서버로

    private Long id;

    public ViewRequestDto() {} // viewAll 용

}


