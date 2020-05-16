package cl.web.community.Service;

import cl.web.community.DAO.RecordDAO;
import cl.web.community.DAO.ScheduleDAO;
import cl.web.community.MyResult;
import cl.web.community.entity.Record;
import cl.web.community.entity.Schedule;
import cl.web.community.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordService {
    final
    RecordDAO recordDAO;
    final
    ScheduleDAO scheduleDAO;

    public RecordService(RecordDAO recordDAO, ScheduleDAO scheduleDAO) {
        this.recordDAO = recordDAO;
        this.scheduleDAO = scheduleDAO;
    }



    public MyResult addRecord(int scheduleId){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        if (!scheduleDAO.existsByIdAndUser_Name(scheduleId,user.getName())){
            return new MyResult(400,"违法操作");
        }
        Record record = new Record();
        record.setScheduleId(scheduleId);
        recordDAO.save(record);
        return new MyResult(200,"打卡成功");
    }
}
