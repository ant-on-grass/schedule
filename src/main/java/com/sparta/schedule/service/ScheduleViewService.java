package com.sparta.schedule.service;


import com.sparta.schedule.dto.ResponseDto;
import com.sparta.schedule.dto.ViewRequestDto;
import com.sparta.schedule.entity.Scheduleitem;
import com.sparta.schedule.repository.ScheduleRepositoryJdbc;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Service // service 의 역할을 맡은 걸을 알려주는 annotaion // 해당 annotaion 은 conponent 를 상속받아, 자동 been 객체가 된다 - > 스프링컨테이너에 자동 등록!
public class ScheduleViewService {

    ScheduleRepositoryJdbc jdbc;

    // DI
    public ScheduleViewService(ScheduleRepositoryJdbc jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * 모든 데이터 조회? 메서드 - 그냥 전달 메서드
     *
     * @retuen ResponseDto - Scheduleitem 말고, 비밀번호가 없어야해서!
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<ResponseDto> scheduleViewAll() throws SQLException, ClassNotFoundException {

        //TODO 추후에 이곳은 검증 로직 이 들어갈 부분!!!

        //TODO 검증 후 넘김 - > repository 에 넘김
        List<ResponseDto> responseDtos = jdbc.scheduleViewAll();
        //TODO repository 의 결과 값을 다시 로직으로 처리 할 수 있다


        //TODO 예로 scheduleViewAll 의 실행 로직
        //TODO 그후에 값을 controller 로 넘김
        return responseDtos;
    }

    /**
     * 특정 조건(id)에 맞는 저장 데이터를 보여주는 메서드
     * @param id
     * @return ResponseDto - Scheduleitem 말고, 비밀번호가 없어야해서!
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ResponseDto scheduleView(Long id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = jdbc.scheduleView(id);

        ResponseDto responseDto = new ResponseDto();
        try (resultSet) {
            if (resultSet.next()) {
                responseDto.setId(resultSet.getLong("id"));
                responseDto.setAuthor(resultSet.getString("author"));
                responseDto.setContents(resultSet.getString("comments"));


                Timestamp flexDatePreConvert = resultSet.getTimestamp("flexDate");
                if (flexDatePreConvert != null) {
                    responseDto.setFlexDate(flexDatePreConvert.toLocalDateTime());
                }

                Timestamp fixDatePreConvert = resultSet.getTimestamp("fixDate");
                if (fixDatePreConvert != null) {
                    responseDto.setFixDate(fixDatePreConvert.toLocalDateTime());

                }
                return responseDto;
            } else {
                // 데이터가 없을 경우
                System.out.println("ID " + id + "에 해당하는 데이터가 없습니다.");
                return null; //TODO 혹은 Optional.empty()로 감싸기
            }
        }
    }
}

