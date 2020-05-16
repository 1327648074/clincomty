package cl.web.community.controller;

import cl.web.community.MyResult;
import cl.web.community.Service.UserService;
import cl.web.community.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class VerifyController {
    final
    UserService userService;

    @Autowired
    public VerifyController(UserService userService) {
        this.userService = userService;
    }


    @CrossOrigin
    @GetMapping("/api/test")
    @ResponseBody
    public User test(){
        return userService.getUserByName("test");
    }


    @CrossOrigin
    @PostMapping("/api/login")
    @ResponseBody
    public MyResult login(@RequestBody Map<String,String> dataMap){
        String username = dataMap.get("username");
        String password = dataMap.get("password");
        return userService.login(username,password);
    }
    @CrossOrigin
    @PostMapping("/api/register")
    @ResponseBody
    public MyResult register(@RequestBody Map<String,String> dataMap){
        String name = dataMap.get("name");
        String pwd = dataMap.get("pwd");
        String sex = dataMap.get("sex");
        String email = dataMap.get("email");
        String code = dataMap.get("checkCode");
        User user = new User(name,pwd,sex,email);
        Session session = SecurityUtils.getSubject().getSession(false);
        if (session==null||!session.getAttribute(email).equals(code)){
            return new MyResult(400,"验证码错误");
        }
        session.stop();
        return userService.register(user);
    }

    @CrossOrigin
    @GetMapping("/api/logout")
    @ResponseBody
    public MyResult logout(){
        Subject subject = SecurityUtils.getSubject();
        if (subject!=null){
            System.out.println("确认等出");
            subject.logout();
        }
        return new MyResult(200,"已登出");
    }

}
