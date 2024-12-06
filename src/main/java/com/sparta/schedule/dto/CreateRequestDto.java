package com.sparta.schedule.dto;


import lombok.Getter;

@Getter
// 자동 getter 를 만들어주는 annotation

//@AllArgsConstructor
// 자동 생성자를 만들어주는 annotation


public class CreateRequestDto { //TODO 갈때의 dto 다른 서버로
// 요청할 때에, 알맞은 API 를 찾아주는 역할

    private String author;
    private String contents ;
    private String password ;

    }
