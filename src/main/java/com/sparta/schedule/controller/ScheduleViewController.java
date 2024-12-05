package com.sparta.schedule.controller;

import com.sparta.schedule.dto.ResponseDto;
import com.sparta.schedule.dto.ViewRequestDto;
import com.sparta.schedule.service.ScheduleViewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping("/schedule")
public class ScheduleViewController {

    private final ScheduleViewService scheduleViewService;

    public ScheduleViewController(ScheduleViewService scheduleViewService) {
        this.scheduleViewService = scheduleViewService;

    }

    @GetMapping()
    public ResponseEntity<List<ResponseDto>> scheduleViewAll(@RequestBody ViewRequestDto dto) throws SQLException {

        // 서비스 호출
        List<ResponseDto> responseDtos = scheduleViewService.scheduleViewAll(dto);


        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

}
