package cl.web.community.DAO;

import cl.web.community.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CommentDAO extends JpaRepository<Comment,Integer> {
    @Query(value = "select new Comment (c.time,c.blogId,c.content,c.user.name,c.user.psrc) from Comment c where c.blogId = :blogId")
    Page<Comment> findAllByBlogId(@Param("blogId") int blogId, Pageable pageable);
}
