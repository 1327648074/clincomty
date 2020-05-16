package cl.web.community.controller;

import cl.web.community.MyResult;
import cl.web.community.Service.BlogService;
import cl.web.community.Service.CommentService;
import cl.web.community.entity.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {

    final
    CommentService commentService;
    final
    BlogService blogService;

    public CommentController(CommentService commentService, BlogService blogService) {
        this.commentService = commentService;
        this.blogService = blogService;
    }

    @CrossOrigin
    @PostMapping("/api/comment")
    @ResponseBody
    public Page<Comment> getCommentsBybId(@RequestParam(value = "blogId")int bid,@RequestParam(value = "page") int p){
        return commentService.getComments(bid,p);
    }
    @CrossOrigin
    @PostMapping("/api/comment/create")
    @ResponseBody
    public MyResult createComment(@RequestBody Comment comment){
        blogService.updateCommentCount(comment.getBlogId());
        return commentService.createComment(comment);
    }


}
