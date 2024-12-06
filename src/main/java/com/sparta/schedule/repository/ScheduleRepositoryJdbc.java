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


@Repository // repository 의 역할을 맡은 걸을 알려주는 annotaion // 해당 annotaion 은 conponent 를 상속받아, 자동 been 객체가 된다 - > 스프링컨테이너에 자동 등록!
@Slf4j // 감별 annotaion // tmi : 사용해보지는 못했다. 해당 클래스에서 문제가 있는 부분이 있어 혹시 필요할까 싶어 넣어둔 annotaion
public class ScheduleRepositoryJdbc implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;
    // JdbcTemplate database 와 연동을 굳이 안해도, database 에 값을 조회 혹은 수정 삭제를 할 적에 쓸 수 있다 - 아주 용이
    // < - > 밑에 나올 Connection 은 database 를 지정하여 사용해야하는 친구! 안 용이

    public ScheduleRepositoryJdbc(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    // DI

    /**
     * 저장 데이터를 생성하는 메서드
     *
     *
     * @param scheduleitem
     */
    public void scheduleSave(Scheduleitem scheduleitem) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        // SimpleJdbcInsert jdbc를 사용할 때, 편하게 코드를 짤 수 있게 끔 도와주는 도구
        // tmi :  물론 본인은 잘 못쓴다.

        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");
        // usingGeneratedKeyColumns() 레코드? 생성 시에 자동으로 값을 증가시키며 생성될 컬럼
        // withTableName() 테이블의 이름

        // withSchemaName() 스키마의 이름
        // tmi : 해당 메서드를 이용했다가 삽질을 3~5 시간함... 이유를 모르겠으나, 해당 메서드를 이용 시 database를 찾지 못함

        // 값을 넣어주려 하는 과정 // 이 과정에서 Map 이 쓰임
        // 다른 방법도 많은 듯하다!
        Map<String, String> parameters = new HashMap<>();
        parameters.put("author", scheduleitem.getAuthor());
        parameters.put("comments", scheduleitem.getContents());
        parameters.put("flexDate", scheduleitem.getFlexDate().toString());
        //TODO Localdatetime 타입인 flexDate 와 fixDate 을 저장시에 string으로 바꿈 // 해당 과정이 데이터 관리에 용이 할 것이라 한다... - '영빈gpt'
        parameters.put("fixDate", scheduleitem.getFixDate().toString());
        parameters.put("password", scheduleitem.getPassword());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        // executeAndReturnKey - 위에 usingGeneratedKeyColumns(); 의 칼럼에 자동 생성 값 + 데이터 저장
        // MapSqlParameterSource - 확실하지는 않지만, 저장 데이터에 넣을 값을 작성한 코드를 소스로 하여 execute

        scheduleitem.setId(key.longValue());
        //TODO 리턴 값이 필요 없는게, 사용한 매개 변수에 id 값만 넣어주면, 해당 메서드에서 하는 역할 아이템 저장(id 포함) + scheduleitem 에 id 값을 저장
        //TODO Service 에서 필요했던 id 값을 반환하기만 하면 되어, id를 리턴하기보단, 해당 메서드에서 이용 매개변수의 필드 값에 해당 값을 넣어줌으로써, 이전 과정 생략!


        // 밑에 코드는 위에 오류로 인해, 코드를 변경하면서 생긴 잔해입니다. 다만, 밑에 코드로 실행이 되어 주석으로 남깁니다!

        /*SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("schedule")
                .usingGeneratedKeyColumns("id")
                .usingColumns("author","comments","flexDate","fixDate","password");
                // 사용할 컬럼을 명시한 부분

        // Map.of() 로 한번에 넣는 과정!
        final Map<String, Object> parameters = Map.of( // Map에 values 타입을 Object로 하여 flexDate 과 fixDate 을 한번에 묶으려했다!
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

    /**
     * 데이터 베이스에 저장된 모든 값을 조회하는 메서드
     * <br>ps 굳이 service에게 줘야하나? 그 정도에 처리가 있나 고민하다가 수정안한 녀석
     * @return List<ResponseDto> 모든 데이터를 품은 녀석 - 단 ResponseDto 의 필드엔 비밀번호가 없다! - 요구사항
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<ResponseDto> scheduleViewAll() throws SQLException, ClassNotFoundException {

        Connection connection = getConnection();
        // getConnection() - 내가 정의한 메서드
        // tmi : database 연결을 할 적에 반복하는 것이 싫어 만듬 // 근데 이럴꺼면 jdbcTemplate 을 쓰는 것이 맞았다.. ㅠ

        String sql = "select * from schedule"; // 조회할 조건을 sql 문법에 맞게 String 으로 할당

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql); // TODO prepareStatement 는 공부가 필요...
            //TODO 요 특이한 try문! 은 데이터 베이스?에서만 사용한다.
            // 사용 후,데이터 베이스를 닫지않으면 자원?낭비? 가 되기에 꼭 닫아주어야하는 데,
            // '()' 안에 닫히는 타이밍될 녀석을 넣어 주면 된다. ex 위에   preparedStatement 사용이 끝나면 닫아라! 머 이런식이다.

            ResultSet resultSet = preparedStatement.executeQuery() // executeQuery() - 이건 딱봐도 쿼리를 실행!
            // ResultSet 클래스에 대해서는 정확히 모른다 다면 preparedStatement 의 반환 타입이라는 것 정도만 알고 잇다.
        ) {
            List<ResponseDto> viewalldata = new ArrayList<>();
            while(resultSet.next()) { // resultSet.next() - 생각보다 중한 녀석
                // 작성한 sql에 맞는 쿼리를 찾았다면 해당 메서드를 통해, 모든 레코드를 가보게끔 해줌(while 문에서) - 더이상의 레코드가 없다면 종료! 자동으로 while 을 나옴
                // 처음 시작은 첫번째 레코드 전 - > 아무것도 선택이 안된 상태이고 , 한번 resultSet.next() 하게 되면, 첫번째 레코드로 이동

                ResponseDto responseDto = new ResponseDto();
                responseDto.setId(resultSet.getLong("id")); // 담는 과정
                responseDto.setAuthor(resultSet.getString("author"));
                responseDto.setContents(resultSet.getString("comments"));

                Timestamp flexDatePreConvert = resultSet.getTimestamp("flexDate"); // 변환 과정
                // tmi : 생각해보면 아까 생성할 때, 마지막에 string으로 바꿔서 넣었는데... 왜 되지?

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

    /**
     * 전체 데이터 조회하는 메서드
     *
     * @param id
     * @return ResultSet 값을 service에 주어 처리하도록 !
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ResultSet scheduleView(Long id) throws SQLException, ClassNotFoundException {

        Connection connection = getConnection();
        // 위에 적어두어 생략

        String sql = "Select * from schedule where id = ?";
        // 검색 조건의 sql문 - ?는 조건의 값이 들어갈 공간? 이라 생각하자

        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setLong(1,id);
        // ps.set 에 첫번째 매개변수는 작성한 쿼리문에 ? 값이 만약 여러개라면, ? 마다의 번호를 차례로 주어 어딘지 알게 해준다.
        //TODO ps.setInt(1, dto.getId()); <- 타입이 틀린 예

        ResultSet resultSet = ps.executeQuery();

        return resultSet;
        }

    /**
     * 특정 조건(id)으로 할 일과 작성자 명을 수정하게 끔 해주는 메서드
     *
     * @param id
     * @param dto
     * @return ResultSet - service가 처리하도록!
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ResultSet scheduleSpecificSet(Long id , SetRequestDto dto ) throws SQLException, ClassNotFoundException {
        // 위와 대부분이 같아 달라진 부분 외엔 주석 생략

        Connection connection = getConnection();

        String sql = "Select * from schedule where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        //TODO Statement 객체를 생성할 때 ResultSet의 동시성과 유형을 설정?
        // tmi : 해당 부분에서도 오류가 생겨 한시간은 소비...

        //TODO ps.setInt(1, dto.getId());
        ps.setLong(1,id);
        ResultSet resultSet = ps.executeQuery();

        return resultSet;
    }

    /**
     * 특정 조건(id)을 가지고 데이터 베이스에 저장된 값을 삭제하는 메서드
     * @param id
     * @return ResultSet - service가 처리하도록!
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ResultSet scheduleSpecificDelete(Long id) throws SQLException, ClassNotFoundException {
        // 위와 같아 주석 생략
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

    /**
     *  Connection 클래스를 이용하기 위해 database를 설정해주는 메서드
     *
     * @return Connection
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 1. Connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/schedule", "root", "testroot1234!@#$");
        return connection;
    }

}
