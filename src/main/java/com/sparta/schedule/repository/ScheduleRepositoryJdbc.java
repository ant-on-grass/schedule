package com.sparta.schedule.repository;

import com.sparta.schedule.dto.ResponseDto;
import com.sparta.schedule.dto.ViewRequestDto;
import com.sparta.schedule.entity.Scheduleitem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class ScheduleRepositoryJdbc implements ScheduleRepository {

    private final Map<Long, Scheduleitem> scheduleList = new HashMap<>();
    private final JdbcTemplate jdbcTemplate;
    private Connection connection;

    public ScheduleRepositoryJdbc(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public ResponseDto scheduleSave(Scheduleitem scheduleitem) {

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withSchemaName("schedule").withTableName("schedule").usingGeneratedKeyColumns("id");

        final Map<String, Object> parameters = Map.of(
                "author", scheduleitem.getAuthor(),
                "comments", scheduleitem.getContents(),
                "flexDate", scheduleitem.getFlexDate(),
                "fixDate", scheduleitem.getFixDate(),
                "password", scheduleitem.getPassword()
                );

        Number key = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        Long id = (Long) key;

        scheduleitem.setId(id);
        return new ResponseDto(scheduleitem);
    }


    public List<ResponseDto> scheduleViewAll(ViewRequestDto dto) throws SQLException {

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
    public ResponseDto scheduleView(ViewRequestDto dto) throws SQLException {
        String sql = "Select * from schedule where id = ?";
        ResponseDto responseDto = new ResponseDto();
        try (
            PreparedStatement ps = connection.prepareStatement(sql))
            {
            //TODO ps.setInt(1, dto.getId());
            ps.setLong(1,dto.getId());
            try (
                ResultSet resultSet = ps.executeQuery())
            {
            if(resultSet.next()){
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
                System.out.println("ID " + dto.getId() + "에 해당하는 데이터가 없습니다.");
                return null; //TODO 혹은 Optional.empty()로 감싸기
            }
            }

            }
        }
}
