package com.sparta.schedule.service;


import com.sparta.schedule.dto.ResponseDto;
import com.sparta.schedule.repository.ScheduleRepositoryJdbc;
import org.springframework.stereotype.Service;

@Service
public class ScheduleSetService {

    ScheduleRepositoryJdbc scheduleRepositoryJdbc;

    public ScheduleSetService(ScheduleRepositoryJdbc scheduleRepositoryJdbc) {
        this.scheduleRepositoryJdbc = scheduleRepositoryJdbc;

    }

    public ResponseDto scheduleSetSpecific(ResponseDto dto) {

        scheduleRepositoryJdbc.scheduleSetSpecific();


        return
    }


}
