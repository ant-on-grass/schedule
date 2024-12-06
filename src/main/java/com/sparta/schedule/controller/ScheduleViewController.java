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
@RequestMapping("/schedules")
public class ScheduleViewController {

    private final ScheduleViewService scheduleViewService;

    public ScheduleViewController(ScheduleViewService scheduleViewService) {
        this.scheduleViewService = scheduleViewService;

    }

    @GetMapping()
    public ResponseEntity<List<ResponseDto>> scheduleViewAll() throws SQLException, ClassNotFoundException {

        // 서비스 호출
        List<ResponseDto> responseDtos = scheduleViewService.scheduleViewAll();


        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> test(@PathVariable("id") Long id) throws SQLException, ClassNotFoundException {

        // 서비스 호출

        ResponseDto responseDtos = scheduleViewService.scheduleView(id);


        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }


}
