package cl.web.community.Service;

import cl.web.community.DAO.CommentDAO;
import cl.web.community.MyResult;
import cl.web.community.entity.Comment;
import cl.web.community.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    final
    CommentDAO commentDAO;

    public CommentService(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public Page<Comment> getComments(int bid,int p){
        Sort sort = Sort.by(Sort.Direction.ASC,"time");
        Pageable pageable = PageRequest.of(p-1,10);
        return commentDAO.findAllByBlogId(bid,pageable);
    }

    public MyResult createComment(Comment comment){
        Subject subject = SecurityUtils.getSubject();
        User user =(User) subject.getPrincipal();
        comment.setUser(user);
        commentDAO.save(comment);
        return new MyResult(200,"评论成功");
    }
}
