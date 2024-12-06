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
@RequestMapping("/schedules") //해당 url 위치 선정!
@Getter
public class ScheduleCreatController {


    private ScheduleServiceImpl scheduleService;
    // DI
    public ScheduleCreatController(ScheduleServiceImpl scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping // API - post 를 쓸 것 이라 명명
    public ResponseEntity<ResponseDto> scheduleCreat(@RequestBody CreateRequestDto dto) { // body 안에 값을 이용하겠다!
        // RestController 이여서 요청 http에서 body 를 쓸 수 있다!

        // 서비스 호출
        //ResponseDto responseDto = scheduleService.saveSchedule(dto);
        // 처럼 쓰고 변수를 new ResponseEntity<>(responseDto,HttpStatus.OK); 해도 된다

        return new ResponseEntity<>(scheduleService.saveSchedule(dto),HttpStatus.OK);
        // HttpStatus ENUM CLASS 로 요청 후, 응답 시 나타나는 메세지 // 성공 : 200 , 400 : 클라이언트 오류 , 500 : 서버 오류
    }
}
