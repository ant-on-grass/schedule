package com.sparta.schedule.controller;

import com.sparta.schedule.dto.RequestDto;
import com.sparta.schedule.dto.ResponseDto;
import com.sparta.schedule.entity.Scheduleitem;
import com.sparta.schedule.service.ScheduleService;
import com.sparta.schedule.service.ScheduleServiceImpl;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/schedules")
@Getter
public class ScheduleCreatController {


    private ScheduleServiceImpl scheduleService;

    public ScheduleCreatController(ScheduleServiceImpl scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> scheduleCreat(@RequestBody RequestDto dto) {

        //ResponseDto responseDto = scheduleService.saveSchedule(dto);

        return new ResponseEntity<>(scheduleService.saveSchedule(dto),HttpStatus.OK);
    }
}