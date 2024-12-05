package com.sparta.schedule.service;


import com.sparta.schedule.dto.ResponseDto;
import com.sparta.schedule.dto.ViewRequestDto;
import com.sparta.schedule.entity.Scheduleitem;
import com.sparta.schedule.repository.ScheduleRepositoryJdbc;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ScheduleViewService {

    ScheduleRepositoryJdbc jdbc;

    public ScheduleViewService(ScheduleRepositoryJdbc jdbc) {
        this.jdbc = jdbc;
    }

    public List<ResponseDto> scheduleViewAll(ViewRequestDto dto) throws SQLException {

        //TODO 추후에 이곳은 검증 로직 이 들어갈 부분!!!

        //TODO 검증 후 넘김 - > repository 에 넘김
        List<ResponseDto> responseDtos = jdbc.scheduleViewAll(dto);
        //TODO repository 의 결과 값을 다시 로직으로 처리 할 수 있다



        //TODO 예로 scheduleViewAll 의 실행 로직
        //TODO 그후에 값을 controller 로 넘김
        return responseDtos;
    }
}
