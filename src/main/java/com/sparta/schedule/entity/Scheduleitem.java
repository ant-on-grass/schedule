package com.sparta.schedule.entity;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Scheduleitem {

    private Long id;
    private String author;
    private String contents ;
    private final String password ;
    private final LocalDateTime fixDate;
    //private final LocalDateTime fixDate; //TODO 고려해봐야함!
    private LocalDateTime flexDate;

    public Scheduleitem( String author, String contents, String password, LocalDateTime fixDate) {
        this.author=author;
        this.contents=contents;
        this.password = password;
        this.fixDate= fixDate;
    }


}
