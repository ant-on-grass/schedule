package com.sparta.schedule.service;

import com.sparta.schedule.dto.CreateRequestDto;
import com.sparta.schedule.dto.ResponseDto;

public interface ScheduleService {

    public ResponseDto saveSchedule(CreateRequestDto dto);
}
