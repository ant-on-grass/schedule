package com.sparta.schedule.repository;

import com.sparta.schedule.dto.ResponseDto;
import com.sparta.schedule.dto.SetRequestDto;
import com.sparta.schedule.entity.Scheduleitem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
@Slf4j
public class ScheduleRepositoryJdbc implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;


    public ScheduleRepositoryJdbc(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void scheduleSave(Scheduleitem scheduleitem) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("author", scheduleitem.getAuthor());
        parameters.put("comments", scheduleitem.getContents());
        parameters.put("flexDate", scheduleitem.getFlexDate().toString());
        parameters.put("fixDate", scheduleitem.getFixDate().toString());
        parameters.put("password", scheduleitem.getPassword());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        //TODO 리턴 값이 필요 없는게 사용한 매개 변수에 id 값만 넣어주면, 해당 메서드에서 하는 역할 아이템 저장(id 포함) + scheduleitem 에 id 값을 저장
        scheduleitem.setId(key.longValue());

        /*SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("schedule")
                .usingGeneratedKeyColumns("id")
                .usingColumns("author","comments","flexDate","fixDate","password");


        final Map<String, Object> parameters = Map.of(
                "author", scheduleitem.getAuthor(),
                "comments", scheduleitem.getContents(),
                "flexDate", scheduleitem.getFlexDate(),
                "fixDate", scheduleitem.getFixDate(),
                "password", scheduleitem.getPassword()
                );

        Number key = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        scheduleitem.setId(key.longValue());

*/
    }


    public List<ResponseDto> scheduleViewAll() throws SQLException, ClassNotFoundException {

        Connection connection = getConnection();


        String sql = "select * from schedule";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            List<ResponseDto> viewalldata = new ArrayList<>();
            while(resultSet.next()) {
                ResponseDto responseDto = new ResponseDto();
                responseDto.setId(resultSet.getLong("id"));
                responseDto.setAuthor(resultSet.getString("author"));
                responseDto.setContents(resultSet.getString("comments"));

                Timestamp flexDatePreConvert = resultSet.getTimestamp("flexDate");
                if (flexDatePreConvert != null) {
                    responseDto.setFlexDate(flexDatePreConvert.toLocalDateTime());
                } //TODO responseDto.setFlexDate(resultSet.getDate("flexDate")); < - LocalDateTime 타입인 flexDate 는
                //TODO 해당 방법으로는 바로 LocalDateTime 타입의 값을 반환받을 수 없어, 시간과 날자를 받을 수 있는 timestamp 로 받은 뒤, LocalDateTime 으로 변환시켜줘야한다!!

                Timestamp fixDatePreConvert = resultSet.getTimestamp("fixDate");
                if (fixDatePreConvert != null) {
                    responseDto.setFixDate(fixDatePreConvert.toLocalDateTime());
                }
                viewalldata.add(responseDto);

            } return viewalldata;
        }catch (SQLException e) {
            // 예외 처리: 로그 추가
            e.printStackTrace();
            throw new RuntimeException("Database error occurred while fetching schedule", e);
        }

    }


    public ResultSet scheduleView(Long id) throws SQLException, ClassNotFoundException {

        Connection connection = getConnection();

        String sql = "Select * from schedule where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        //TODO ps.setInt(1, dto.getId());
        ps.setLong(1,id);
        ResultSet resultSet = ps.executeQuery();

        return resultSet;
        }

    public ResultSet scheduleSpecificSet(Long id , SetRequestDto dto ) throws SQLException, ClassNotFoundException {

        Connection connection = getConnection();

        String sql = "Select * from schedule where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        //TODO Statement 객체를 생성할 때 ResultSet의 동시성과 유형을 설정?

        //TODO ps.setInt(1, dto.getId());
        ps.setLong(1,id);
        ResultSet resultSet = ps.executeQuery();

        return resultSet;
    }

    public ResultSet scheduleSpecificDelete(Long id) throws SQLException, ClassNotFoundException {

        Connection connection = getConnection();

        String sql = "Select * from schedule where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        //TODO Statement 객체를 생성할 때 ResultSet의 동시성과 유형을 설정?

        //TODO ps.setInt(1, dto.getId());
        ps.setLong(1,id);
        ResultSet resultSet = ps.executeQuery();

        return resultSet;
    }


    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 1. Connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/schedule", "root", "testroot1234!@#$");
        return connection;
    }

}
