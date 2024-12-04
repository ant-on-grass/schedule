package com.sparta.schedule.controller;

import com.sparta.schedule.dto.RequestDto;
import com.sparta.schedule.dto.ResponseDto;
import com.sparta.schedule.entity.Scheduleitem;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/schedules")
public class ScheduleCreatController {


    private final Map<Long, RequestDto>scheduleList = new HashMap<>();

    @PostMapping
    public ResponseDto scheduleCreat(@RequestBody RequestDto dto) {

        Long id = 0L;

        if(scheduleList.size() == 0){
            id = 1L;
        } else{
            id = scheduleList.size()+1L;
        }

        scheduleList.put(id,dto);

        Scheduleitem scheduleitem = new Scheduleitem(id, dto.getAuthor(), dto.getContents(),dto.getPassword());

        LocalDateTime fixDate = LocalDateTime.now();
        scheduleitem.setFixDate(fixDate);
        LocalDateTime flexDate = fixDate;
        scheduleitem.setFlexDate(flexDate);

        return new ResponseDto(scheduleitem);
    }
}
