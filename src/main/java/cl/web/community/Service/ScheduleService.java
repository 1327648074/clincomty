package cl.web.community.Service;

import cl.web.community.DAO.ScheduleDAO;
import cl.web.community.MyResult;
import cl.web.community.entity.Schedule;
import cl.web.community.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    final
    ScheduleDAO scheduleDAO;

    public ScheduleService(ScheduleDAO scheduleDAO) {
        this.scheduleDAO = scheduleDAO;
    }

    public List<Schedule> getScheduleByCurrentUser(){
        Subject subject = SecurityUtils.getSubject();
        User user =(User) subject.getPrincipal();
        return scheduleDAO.findAllByUser_Name(user.getName());
    }
    public MyResult CreateSchedule(Schedule schedule){
        Subject subject = SecurityUtils.getSubject();
        User user =(User) subject.getPrincipal();
        schedule.setUser(user);
        scheduleDAO.save(schedule);
        return new MyResult(200,"创建成功");
    }
}
