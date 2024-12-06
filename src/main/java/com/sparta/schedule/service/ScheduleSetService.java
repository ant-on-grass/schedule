package com.sparta.schedule.service;

import com.sparta.schedule.dto.ResponseDto;
import com.sparta.schedule.dto.SetRequestDto;
import com.sparta.schedule.entity.Scheduleitem;
import com.sparta.schedule.repository.ScheduleRepositoryJdbc;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service // service 의 역할을 맡은 걸을 알려주는 annotaion // 해당 annotaion 은 conponent 를 상속받아, 자동 been 객체가 된다 - > 스프링컨테이너에 자동 등록!
public class ScheduleSetService {

    private ScheduleRepositoryJdbc scheduleRepositoryJdbc;

    // DI
    public ScheduleSetService(ScheduleRepositoryJdbc scheduleRepositoryJdbc) {
        this.scheduleRepositoryJdbc = scheduleRepositoryJdbc;
    }

    /**
     * repository에서 가지고 온 저장 데이터를 가공!
     * @param id
     * @param dto
     * @return ResponseDto - Scheduleitem 말고, 비밀번호가 없어야해서!
     * @throws SQLException
     */
    public ResponseDto scheduleSpecificSet(Long id , SetRequestDto dto) throws SQLException {
    // repository 에서 반복 되는 것들 생략

        if( dto.getAuthor() != null ){

            try(ResultSet resultSet = scheduleRepositoryJdbc.scheduleSpecificSet(id, dto)){

                if(resultSet.next()){
                    if(resultSet.getString("password").equals(dto.getPassword())){

                        LocalDateTime flexDate = LocalDateTime.now();

                        resultSet.updateString(2,dto.getAuthor());
                        resultSet.updateString(3,dto.getContents());
                        resultSet.updateObject(4,flexDate);
                        resultSet.updateRow(); //TODO  resultSet.update 사용하여 값 변경시 반드시 호출해야 변경 사항이 적용됨

                        ResponseDto responseDto = new ResponseDto();

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
                    }
                }
            } catch (SQLException e) {
                throw new SQLException("오류",e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("오류",e);
            }
        }
        else {
            throw new SQLException("오류 발생");
        }
        return null;
    }
}
