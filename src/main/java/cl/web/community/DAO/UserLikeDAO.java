package cl.web.community.DAO;

import cl.web.community.entity.UserLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface UserLikeDAO extends JpaRepository<UserLike,Integer> {

    void deleteByBlogIdAndUserId(int bId,int uId);
}
