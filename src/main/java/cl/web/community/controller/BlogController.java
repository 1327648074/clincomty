package cl.web.community.controller;

import cl.web.community.MyResult;
import cl.web.community.Service.BlogService;
import cl.web.community.entity.Blog;
import cl.web.community.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class BlogController {
    final
    BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    private int getCurrentUID(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            return ((User) subject.getPrincipal()).getId();
        }else {
            return -1;
        }
    }


    @CrossOrigin
    @PostMapping("/api/blog")
    @ResponseBody
    public Page<Blog> getBlogByPage(@RequestParam(value = "page") int p){
        return blogService.getBlogByPage(p,getCurrentUID());

    }
    @CrossOrigin
    @PostMapping("/api/blog/name")
    @ResponseBody
    public Page<Blog> getBlogByPageAndName(@RequestParam(value = "name") String name,@RequestParam(value = "page") int page){
        return blogService.getBlogByPageAndName(page,name,getCurrentUID());
    }
    @CrossOrigin
    @PostMapping("/api/blog/my/self")
    @ResponseBody
    public Page<Blog> getBlogByPageAndName(@RequestParam(value = "page") int page){
        return blogService.getCurrentUserBlog(page,getCurrentUID());
    }
    @CrossOrigin
    @PostMapping("/api/blog/my/create")
    @ResponseBody
    public MyResult createBlog(@RequestBody Blog blog){
        return blogService.createBlog(blog);
    }
}
