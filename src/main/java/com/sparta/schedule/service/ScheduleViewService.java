package com.sparta.schedule.service;


import com.sparta.schedule.dto.CreateRequestDto;
import com.sparta.schedule.dto.CreateResponseDto;
import com.sparta.schedule.repository.ScheduleRepositoryJdbc;
import org.springframework.stereotype.Service;

@Service
public class ScheduleViewService {

    ScheduleRepositoryJdbc jdbc;

    public ScheduleViewService(ScheduleRepositoryJdbc jdbc) {
        this.jdbc = jdbc;
    }

    public CreateResponseDto scheduleViewAll(CreateRequestDto dto) {

        //TODO 추후에 이곳은 검증 로직 이 들어갈 부분!!!

        //TODO 검증 후 넘김 - > repository 에 넘김
        jdbc.scheduleViewAll(dto);
        //TODO repository 의 결과 값을 다시 로직으로 처리 할 수 있다

        //TODO 예로 scheduleViewAll 의 실행 로직
        //TODO 그후에 값을 controller 로 넘김
        return  new CreateResponseDto();
    }
}
