package cl.web.community.DAO;

import cl.web.community.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordDAO extends JpaRepository<Record,Integer> {
}
