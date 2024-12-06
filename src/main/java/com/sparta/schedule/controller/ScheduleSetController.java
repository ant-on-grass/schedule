package com.sparta.schedule.controller;

import com.sparta.schedule.dto.ResponseDto;
import com.sparta.schedule.dto.SetRequestDto;
import com.sparta.schedule.service.ScheduleSetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;


@RestController
@RequestMapping("/schedules")
public class ScheduleSetController {


    private ScheduleSetService scheduleSetService;

    public ScheduleSetController(ScheduleSetService scheduleSetService) {
        this.scheduleSetService = scheduleSetService;
    }

    @PatchMapping("/{id}")
    //@PatchMapping("/id={id}&author={author}&contents={contents}")//TODO 이건 아닌듯?
    public ResponseEntity<ResponseDto> scheduleSpecificSet(@PathVariable("id") Long id,
        @RequestBody SetRequestDto dto) throws SQLException {

        //scheduleSetService.scheduleSpecificSet(id ,dto);

        return new ResponseEntity<>(scheduleSetService.scheduleSpecificSet(id ,dto),HttpStatus.OK);
    }
}
