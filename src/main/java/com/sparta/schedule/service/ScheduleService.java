package com.sparta.schedule.service;

import com.sparta.schedule.dto.CreateRequestDto;
import com.sparta.schedule.dto.CreateResponseDto;

public interface ScheduleService {

    public CreateResponseDto saveSchedule(CreateRequestDto dto);
}
