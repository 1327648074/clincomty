package cl.web.community.controller;

import cl.web.community.MyResult;
import cl.web.community.Service.RecordService;
import cl.web.community.Service.ScheduleService;
import cl.web.community.entity.Record;
import cl.web.community.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class ScheduleController {

    final
    ScheduleService scheduleService;

    final
    RecordService recordService;
    public ScheduleController(ScheduleService scheduleService, RecordService recordService) {
        this.scheduleService = scheduleService;
        this.recordService = recordService;
    }

    @CrossOrigin
    @GetMapping("/api/schedule")
    @ResponseBody
    public List<Schedule> getSchedule(){
        return scheduleService.getScheduleByCurrentUser();
    }

    @CrossOrigin
    @PostMapping("/api/schedule/create")
    @ResponseBody
    public MyResult createSchedule(@RequestBody Schedule schedule){
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天


        Date tomorrow = c.getTime();

         if (schedule.getEnd().before(tomorrow)){
             return new MyResult(400,"创建失败，时间过短");
         }

        return scheduleService.CreateSchedule(schedule);
    }
    @CrossOrigin
    @PostMapping("/api/schedule/record")
    @ResponseBody
    public MyResult record(@RequestParam(name = "schedule_id") int id){
        return recordService.addRecord(id);
    }
}
