package com.sparta.schedule.controller;


import com.sparta.schedule.dto.ResponseDto;
import com.sparta.schedule.service.ScheduleDeleteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/schedules") // 위치 선정
public class ScheduleDeleteController {

    private ScheduleDeleteService scheduleDeleteService;
    // DI
    public ScheduleDeleteController(ScheduleDeleteService scheduleDeleteService) {
        this.scheduleDeleteService = scheduleDeleteService;
    }

    @DeleteMapping("/{id}") // API -DELETE + 위치 선정 // '/' 에 이전은 따로 표기 안함. 위에 schedules 명시되어서
    public ResponseEntity<ResponseDto> scheduleSpecificDelete(@PathVariable("id") Long id) throws SQLException, ClassNotFoundException {
        // @PathVariable - 해당 annotation 은 위치 선정에 변할 수 있는 값이 있을 때 쓴다
        // ps @PathVariable("id") 에서 ("id") 는 위치를 못찾을 때가 있어서 명시를 해두는 것이 좋다

        // 서비스 호출
        scheduleDeleteService.scheduleSpecificDelete(id);

        return new ResponseEntity<ResponseDto>(scheduleDeleteService.scheduleSpecificDelete(id),HttpStatus.OK);
        // HttpStatus ENUM CLASS 로 요청 후, 응답 시 나타나는 메세지 // 성공 : 200 , 400 : 클라이언트 오류 , 500 : 서버 오류
    }



}
