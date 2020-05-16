package cl.web.community.DAO;

import cl.web.community.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface BlogDAO extends JpaRepository<Blog,Integer> {
    @Query(value = "select new Blog (b.id,b.likes,b.title,b.content,b.psrc,b.time,b.commentCount,b.user.name,b.user.psrc) from Blog b")
    Page<Blog> findBlogs(Pageable pageable);
    @Query(value = "select new Blog (b.id,b.likes,b.title,b.content,b.psrc,b.time,b.commentCount,b.user.name,b.user.psrc) from Blog b where b.user.name =:name")
    Page<Blog> findBlogsByUser_id(@Param("name") String name, Pageable pageable);

    @Query(value = "select new Blog (b.id,b.likes,ul.status,b.title,b.content,b.psrc,b.time,b.commentCount,b.user.name,b.user.psrc) from Blog b left join UserLike ul on ul.blogId = b.id and ul.userId=:id")
    Page<Blog> findBlogsWithStatus(@Param("id") int id,  Pageable pageable);
    @Query(value = "select new Blog (b.id,b.likes,b.title,b.content,b.psrc,b.time,b.commentCount,b.user.name,b.user.psrc) from Blog b left join UserLike ul on ul.blogId = b.id and ul.userId=:id where b.user.name =:name")
    Page<Blog> findBlogsByUser_idWithStatus(@Param("id")int id, @Param("name") String name, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update Blog b set b.commentCount = b.commentCount+1 where b.id=:id")
    void updateCommentCount(@Param("id") int id);

    @Transactional
    @Modifying
    @Query(value = "update Blog b set b.likes = b.likes+1 where b.id=:id")
    void addLikes(@Param("id") int id);

    @Transactional
    @Modifying
    @Query(value = "update Blog b set b.likes = b.likes-1 where b.id=:id")
    void deleteLikes(@Param("id") int id);
}
