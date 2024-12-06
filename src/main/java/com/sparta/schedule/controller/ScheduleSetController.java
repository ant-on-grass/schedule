package com.sparta.schedule.controller;

import com.sparta.schedule.dto.ResponseDto;
import com.sparta.schedule.dto.SetRequestDto;
import com.sparta.schedule.service.ScheduleSetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;


@RestController
@RequestMapping("/schedules") // 위치 선정
public class ScheduleSetController {


    private ScheduleSetService scheduleSetService;
    // DI
    public ScheduleSetController(ScheduleSetService scheduleSetService) {
        this.scheduleSetService = scheduleSetService;
    }

    @PatchMapping("/{id}") // API - PATCH + 위치 선정
    //@PatchMapping("/id={id}&author={author}&contents={contents}")//TODO 이렇게 쓰면, 위치를 지정해 줄 때, 타이핑해야해서 패스 // 물론 되는지는 모른다
    public ResponseEntity<ResponseDto> scheduleSpecificSet(@PathVariable("id") Long id,
        @RequestBody SetRequestDto dto) throws SQLException {
        // @PathVariable annotation과 @RequestBody annotation 은 함께 쓰일 수 있다.


        // 서비스 호출
        //scheduleSetService.scheduleSpecificSet(id ,dto);

        return new ResponseEntity<>(scheduleSetService.scheduleSpecificSet(id ,dto),HttpStatus.OK);
        // HttpStatus ENUM CLASS 로 요청 후, 응답 시 나타나는 메세지 // 성공 : 200 , 400 : 클라이언트 오류 , 500 : 서버 오류
    }
}
