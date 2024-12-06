package com.sparta.schedule.service;

import com.sparta.schedule.dto.ResponseDto;
import com.sparta.schedule.dto.SetRequestDto;
import com.sparta.schedule.repository.ScheduleRepositoryJdbc;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class ScheduleSetService {

    private ScheduleRepositoryJdbc scheduleRepositoryJdbc;

    public ScheduleSetService(ScheduleRepositoryJdbc scheduleRepositoryJdbc) {
        this.scheduleRepositoryJdbc = scheduleRepositoryJdbc;
    }

    public ResponseDto scheduleSpecificSet(Long id , SetRequestDto dto) throws SQLException {

        if( dto.getAuthor() != null ){

            try(ResultSet resultSet = scheduleRepositoryJdbc.scheduleSpecificSet(id, dto)){

                if(resultSet.next()){
                    if(resultSet.getString("password").equals(dto.getPassword())){

                        LocalDateTime flexDate = LocalDateTime.now();

                        resultSet.updateString(2,dto.getAuthor());
                        resultSet.updateString(3,dto.getContents());
                        resultSet.updateObject(4,flexDate);

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
