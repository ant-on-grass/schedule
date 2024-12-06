package com.sparta.schedule.entity;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Scheduleitem { // 데이터 베이스에 저장이 될 객체 클래스

    private Long id;
    private String author;
    private String contents ;
    private final String password ;
    private LocalDateTime fixDate;
    //private final LocalDateTime fixDate; //TODO 고려해봐야함!
    private LocalDateTime flexDate;

    public Scheduleitem( String author, String contents, String password) {
        this.author=author;
        this.contents=contents;
        this.password = password;
    }


}
