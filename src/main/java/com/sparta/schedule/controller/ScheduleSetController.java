package com.sparta.schedule.controller;


import com.sparta.schedule.dto.ResponseDto;
import com.sparta.schedule.dto.SetRequestDto;
import com.sparta.schedule.service.ScheduleSetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleSetController {

    ScheduleSetService scheduleSetService;

    public ScheduleSetController(ScheduleSetService scheduleSetService) {
        this.scheduleSetService = scheduleSetService;
    }
    @PatchMapping()
    public ResponseEntity<List<ResponseDto>> scheduleSpecifisSet(@RequestBody SetRequestDto dto) {


        scheduleSetService.scheduleSetSpecific(dto);

        return new ResponseEntity<>(HttpStatus.OK));
    }

}
