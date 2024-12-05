package com.sparta.schedule.service;


import com.sparta.schedule.dto.CreateRequestDto;
import com.sparta.schedule.dto.CreateResponseDto;
import com.sparta.schedule.entity.Scheduleitem;
import com.sparta.schedule.repository.ScheduleRepositoryJdbc;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Getter
public class ScheduleServiceImpl implements ScheduleService {

    private ScheduleRepositoryJdbc scheduleRepositoryJdbc;
    //TODO final setter 주입 x - 생성자를 통해서만 초기화 가능 // 위 필드에 final 이 있으면 오토와이어드 생략가능 - 공부

    @Autowired
    public ScheduleServiceImpl(ScheduleRepositoryJdbc scheduleRepositoryJdbc) {
        this.scheduleRepositoryJdbc = scheduleRepositoryJdbc;
    }

    @Override
    public CreateResponseDto saveSchedule(CreateRequestDto dto) {

        //new Scheduleitem(dto)

        Scheduleitem scheduleitem = new Scheduleitem(dto.getAuthor(), dto.getContents(), dto.getPassword());

        LocalDateTime fixDate = LocalDateTime.now();
        scheduleitem.setFixDate(fixDate);
        LocalDateTime flexDate = fixDate;
        scheduleitem.setFlexDate(flexDate);

        scheduleRepositoryJdbc.scheduleSave(scheduleitem);

        return new CreateResponseDto(scheduleitem);
    }
}