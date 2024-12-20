package com.sparta.schedule.service;


import com.sparta.schedule.dto.CreateRequestDto;
import com.sparta.schedule.dto.ResponseDto;
import com.sparta.schedule.entity.Scheduleitem;
import com.sparta.schedule.repository.ScheduleRepositoryJdbc;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
// service 의 역할을 맡은 걸을 알려주는 annotaion // 해당 annotaion 은 conponent 를 상속받아, 자동 been 객체가 된다 - > 스프링컨테이너에 자동 등록!

@Getter
public class ScheduleServiceImpl implements ScheduleService {

    private ScheduleRepositoryJdbc scheduleRepositoryJdbc;
    //TODO final setter 주입 x - 생성자를 통해서만 초기화 가능
    //TODO 위 필드에 final 이 있으면 오토와이어드 생략가능 < - 이건 필드 주입 - 공부

    // DI
    @Autowired
    public ScheduleServiceImpl(ScheduleRepositoryJdbc scheduleRepositoryJdbc) {
        this.scheduleRepositoryJdbc = scheduleRepositoryJdbc;
    }

    /**
     * 저장할 메타 데이터를 모아서, repository 에게 전달하는 메서드
     *
     * @param dto
     */
    @Override
    public ResponseDto saveSchedule(CreateRequestDto dto) {

        //new Scheduleitem(dto)

        Scheduleitem scheduleitem = new Scheduleitem(dto.getAuthor(), dto.getContents(), dto.getPassword());

        LocalDateTime fixDate = LocalDateTime.now();
        scheduleitem.setFixDate(fixDate);
        LocalDateTime flexDate = fixDate;
        scheduleitem.setFlexDate(flexDate);

        //TODO 세상에! scheduleSave()에서 scheduleitem의 매개변수 값에 setId를 통해 scheduleitem 객체에 id 값을 채워줌
        scheduleRepositoryJdbc.scheduleSave(scheduleitem);

        return new ResponseDto(scheduleitem);
    }
}