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
@RequestMapping("/schedules") // 위치 선정
public class ScheduleViewController {

    private final ScheduleViewService scheduleViewService;
    // DI
    public ScheduleViewController(ScheduleViewService scheduleViewService) {
        this.scheduleViewService = scheduleViewService;

    }

    /**
     * 데이터를 조회하는 api - get
     * 전체 조회
     * @return 전체 조회 데이터
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @GetMapping() // API - GET
    public ResponseEntity<List<ResponseDto>> scheduleViewAll() throws SQLException, ClassNotFoundException {

        // 서비스 호출
        List<ResponseDto> responseDtos = scheduleViewService.scheduleViewAll();


        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    /**
     * 데이터를 조회하는 api - get
     * id 를 이용한 일부 조회
     * @param id
     * @return 해당 id 값에 맞는 데이터 조회
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @GetMapping("/{id}") // API - GET + 위치 선정
    public ResponseEntity<ResponseDto> test(@PathVariable("id") Long id) throws SQLException, ClassNotFoundException {

        // 서비스 호출
        ResponseDto responseDtos = scheduleViewService.scheduleView(id);

        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
        // HttpStatus ENUM CLASS 로 요청 후, 응답 시 나타나는 메세지 // 성공 : 200 , 400 : 클라이언트 오류 , 500 : 서버 오류
    }


}
