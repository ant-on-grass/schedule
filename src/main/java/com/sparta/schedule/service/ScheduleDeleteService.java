package com.sparta.schedule.service;


import com.sparta.schedule.dto.ResponseDto;
import com.sparta.schedule.repository.ScheduleRepositoryJdbc;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class ScheduleDeleteService {

    private ScheduleRepositoryJdbc jdbc;

    public ScheduleDeleteService(ScheduleRepositoryJdbc jdbc) {
        this.jdbc = jdbc;
    }

    public ResponseDto scheduleSpecificDelete(Long id) throws SQLException, ClassNotFoundException {

        try(ResultSet resultSet = jdbc.scheduleSpecificDelete(id)){

            if(resultSet.next()){
                    resultSet.deleteRow();
                    return null;
                }else {
                System.out.println("비밀번호가 틀립니다");
                return null;
            }
        } catch (SQLException e) {
            throw new SQLException("오류",e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("오류",e);
        }


    }
}
