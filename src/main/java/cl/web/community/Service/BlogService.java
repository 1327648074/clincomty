package cl.web.community.Service;

import cl.web.community.DAO.BlogDAO;
import cl.web.community.MyResult;
import cl.web.community.entity.Blog;
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
public class BlogService {
    final
    BlogDAO blogDAO;

    @Autowired
    BlogService(BlogDAO blogDAO){
        this.blogDAO = blogDAO;
    }

    public Page<Blog> getBlogByPage(int p,int uId){

        Sort sort = Sort.by(Sort.Direction.DESC,"time");
        Pageable pageable = PageRequest.of(p-1,5);
        if (uId==-1){
            return blogDAO.findBlogs(pageable);
        }else {
            return blogDAO.findBlogsWithStatus(uId,pageable);
        }
    }

    public  Page<Blog> getCurrentUserBlog(int p,int uId){
        Sort sort = Sort.by(Sort.Direction.DESC,"time");
        Pageable pageable = PageRequest.of(p-1,5);
        Subject subject = SecurityUtils.getSubject();
        User u = (User) subject.getPrincipal();
        if (uId==-1) {
            return blogDAO.findBlogsByUser_id(u.getName(), pageable);
        }else {
            return blogDAO.findBlogsByUser_idWithStatus(uId,u.getName(),pageable);
        }
    }
    public  Page<Blog> getBlogByPageAndName(int p ,String name,int uId){
        Sort sort = Sort.by(Sort.Direction.DESC,"time");
        Pageable pageable = PageRequest.of(p-1,5);
        if (uId==-1) {
            return blogDAO.findBlogsByUser_id(name, pageable);
        }else {
            return blogDAO.findBlogsByUser_idWithStatus(uId,name,pageable);
        }
    }

    public MyResult createBlog(Blog blog){
        Subject subject = SecurityUtils.getSubject();
        User user =(User) subject.getPrincipal();
        blog.setUser(user);
        blogDAO.save(blog);
        return new MyResult(200,"分享成功");
    }
    public void updateCommentCount(int id){
        blogDAO.updateCommentCount(id);
    }

}
