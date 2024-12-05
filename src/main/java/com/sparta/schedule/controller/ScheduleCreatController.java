package com.sparta.schedule.controller;

import com.sparta.schedule.dto.CreateRequestDto;
import com.sparta.schedule.dto.ResponseDto;
import com.sparta.schedule.service.ScheduleServiceImpl;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
@Getter
public class ScheduleCreatController {


    private ScheduleServiceImpl scheduleService;

    public ScheduleCreatController(ScheduleServiceImpl scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> scheduleCreat(@RequestBody CreateRequestDto dto) {

        //ResponseDto responseDto = scheduleService.saveSchedule(dto);

        return new ResponseEntity<>(scheduleService.saveSchedule(dto),HttpStatus.OK);
    }
}
