package com.sparta.schedule.controller;


import com.sparta.schedule.dto.ResponseDto;
import com.sparta.schedule.service.ScheduleDeleteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/schedules")
public class ScheduleDeleteController {

    private ScheduleDeleteService scheduleDeleteService;

    public ScheduleDeleteController(ScheduleDeleteService scheduleDeleteService) {
        this.scheduleDeleteService = scheduleDeleteService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> scheduleSpecificDelete(@PathVariable("id") Long id) throws SQLException, ClassNotFoundException {

        scheduleDeleteService.scheduleSpecificDelete(id);

        return new ResponseEntity<ResponseDto>(scheduleDeleteService.scheduleSpecificDelete(id),HttpStatus.OK);
    }



}
