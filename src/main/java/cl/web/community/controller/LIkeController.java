package cl.web.community.controller;

import cl.web.community.MyResult;
import cl.web.community.Service.UserLikeService;
import cl.web.community.entity.UserLike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LIkeController {

    final
    UserLikeService userLikeService;

    public LIkeController(UserLikeService userLikeService) {
        this.userLikeService = userLikeService;
    }

    @CrossOrigin
    @PostMapping("/api/ul/show")
    @ResponseBody
    public MyResult show(@RequestParam(name = "blogId") int blogId){
        return userLikeService.showLike(blogId);
    }

    @CrossOrigin
    @PostMapping("/api/ul/delete")
    @ResponseBody
    public MyResult delete(@RequestParam(name = "blogId") int blogId){
        return userLikeService.deleteLike(blogId);
    }
}
