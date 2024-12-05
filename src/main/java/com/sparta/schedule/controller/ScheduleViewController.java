package com.sparta.schedule.controller;

import com.sparta.schedule.dto.CreateRequestDto;
import com.sparta.schedule.dto.CreateResponseDto;
import com.sparta.schedule.service.ScheduleViewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/schedule")
public class ScheduleViewController {

    ScheduleViewService scheduleViewService;

    ScheduleViewController(ScheduleViewService scheduleViewService) {
        this.scheduleViewService = scheduleViewService;

    }

    @GetMapping
    public ResponseEntity<CreateResponseDto> scheduleViewAll(@RequestBody CreateRequestDto dto) {

        // 서비스 호출
        CreateResponseDto createResponseDto = scheduleViewService.scheduleViewAll(dto);


        return new ResponseEntity<>(createResponseDto, HttpStatus.OK);
    }
}
