package com.sparta.schedule.controller;

import com.sparta.schedule.dto.ResponseDto;
import com.sparta.schedule.entity.Scheduleitem;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/schedules")
public class ScheduleCreatController {


    private final Map<Long, ResponseDto>scheduleList = new HashMap<>();

    @PostMapping
    public ResponseDto scheduleCreat(@RequestBody ResponseDto dto) {

        Long id = 0L;

        if(scheduleList.size() == 0){
            id = 1L;
        } else{
            id = scheduleList.size()+1L;
        }

        Scheduleitem scheduleitem = new Scheduleitem(id, dto.getAuthor(), dto.getContents());


        return new ResponseDto(scheduleitem);
    }
}
