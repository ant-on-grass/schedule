package com.sparta.schedule.service;


import com.sparta.schedule.dto.ResponseDto;
import com.sparta.schedule.repository.ScheduleRepositoryJdbc;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service // service 의 역할을 맡은 걸을 알려주는 annotaion // 해당 annotaion 은 conponent 를 상속받아, 자동 been 객체가 된다 - > 스프링컨테이너에 자동 등록!
public class ScheduleDeleteService {

    private ScheduleRepositoryJdbc jdbc;
    // DI
    public ScheduleDeleteService(ScheduleRepositoryJdbc jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * 삭제할 데이터를 처리
     * @param id
     * @return ResponseDto - void로 하려했는데, controller 에서 client로 응답을 줄 때,
     * <br> ResponseEntity<> 말고 어떤걸 써도 될지, 또 HTTPSTATUS 를 보내야해서 해당 타입으로 우선 정함
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ResponseDto scheduleSpecificDelete(Long id) throws SQLException, ClassNotFoundException {

        try(ResultSet resultSet = jdbc.scheduleSpecificDelete(id)){

            if(resultSet.next()){
                    resultSet.deleteRow(); // deleteRow() - resultSet.next()로 가다가 걸리는 놈 삭제, 아주 간단
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
