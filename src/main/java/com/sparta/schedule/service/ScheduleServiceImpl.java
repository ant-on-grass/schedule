package com.sparta.schedule.service;


import com.sparta.schedule.dto.RequestDto;
import com.sparta.schedule.dto.ResponseDto;
import com.sparta.schedule.entity.Scheduleitem;
import com.sparta.schedule.repository.ScheduleRepositoryJdbc;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Getter
public class ScheduleServiceImpl implements ScheduleService {

    private ScheduleRepositoryJdbc scheduleRepositoryJdbc;

    public ScheduleServiceImpl(ScheduleRepositoryJdbc scheduleRepositoryJdbc) {
        this.scheduleRepositoryJdbc = scheduleRepositoryJdbc;
    }

    @Override
    public ResponseDto saveSchedule(RequestDto dto) {

        //new Scheduleitem(dto)

        Scheduleitem scheduleitem = new Scheduleitem(dto.getAuthor(), dto.getContents(), dto.getPassword());

        LocalDateTime fixDate = LocalDateTime.now();
        scheduleitem.setFixDate(fixDate);
        LocalDateTime flexDate = fixDate;
        scheduleitem.setFlexDate(flexDate);

        scheduleRepositoryJdbc.scheduleSave(scheduleitem);

        return new ResponseDto(scheduleitem);
    }
}