package com.sparta.schedule.repository;

import com.sparta.schedule.dto.ResponseDto;
import com.sparta.schedule.entity.Scheduleitem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


@Repository
public class ScheduleRepositoryJdbc implements ScheduleRepository {

    private final Map<Long, Scheduleitem> scheduleList = new HashMap<>();
    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
}
