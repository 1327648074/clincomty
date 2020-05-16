package cl.web.community.Service;

import cl.web.community.DAO.BlogDAO;
import cl.web.community.DAO.UserLikeDAO;

import cl.web.community.MyResult;
import cl.web.community.entity.User;
import cl.web.community.entity.UserLike;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserLikeService {

    final
    UserLikeDAO userLikeDAO;
    final BlogDAO blogDAO;

    public UserLikeService(UserLikeDAO userLikeDAO, BlogDAO blogDAO) {
        this.userLikeDAO = userLikeDAO;
        this.blogDAO = blogDAO;
    }


    public MyResult showLike(int blogId){
        Subject subject = SecurityUtils.getSubject();

        User user = (User) subject.getPrincipal();
        UserLike ul = new UserLike();
        ul.setBlogId(blogId);
        ul.setUserId(user.getId());
        ul.setStatus("T");
        userLikeDAO.save(ul);
        blogDAO.addLikes(blogId);
        return new MyResult(200,"点赞成功");
    }
    public MyResult deleteLike(int blogId){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        userLikeDAO.deleteByBlogIdAndUserId(blogId,user.getId());
        blogDAO.deleteLikes(blogId);
        return new MyResult(200,"取消点赞");
    }
}
