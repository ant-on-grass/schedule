package com.sparta.schedule.service;

import com.sparta.schedule.dto.RequestDto;
import com.sparta.schedule.dto.ResponseDto;

public interface ScheduleService {

    public ResponseDto saveSchedule(RequestDto dto);
}
