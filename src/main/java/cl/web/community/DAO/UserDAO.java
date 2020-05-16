package cl.web.community.DAO;

import cl.web.community.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User,Integer> {
    User getUserByName(String name);
    boolean existsUserByName(String name);
}
