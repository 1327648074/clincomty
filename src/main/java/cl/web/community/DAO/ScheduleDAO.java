package cl.web.community.DAO;

import cl.web.community.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleDAO extends JpaRepository<Schedule,Integer> {
    List<Schedule> findAllByUser_Name(String name);
    boolean existsByIdAndUser_Name(int id,String name);
}
