package cl.web.community.Service;

import cl.web.community.DAO.UserDAO;
import cl.web.community.MyResult;
import cl.web.community.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    final
    UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean verify(String name,String pwd){
        return true;
    }
    public boolean isExisted(String name){
        return userDAO.existsUserByName(name);
    }
    public MyResult register(User user){
        if (isExisted(user.getName())){
            return new MyResult(400,"用户名已存在");
        }
        String pwd = user.getPwd();
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String encodePwd = new SimpleHash("md5",pwd,salt,times).toString();
        user.setPwd(encodePwd);
        user.setSalt(salt);
        userDAO.save(user);
        return new MyResult(200,"注册成功");
    }

    public MyResult login(String name , String pwd){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name,pwd);
        token.setRememberMe(true);
        try{
            subject.login(token);
            return new MyResult(200,"登录成功");
        }catch (AuthenticationException e){
            return new MyResult(400,"用户名或密码错误");
        }
    }
    private void modifyShiroInfo(User user){
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principals = subject.getPrincipals();
        String realmName= principals.getRealmNames().iterator().next();
        PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(user, realmName);
        subject.runAs(newPrincipalCollection);
    }

    public MyResult modifyUser(User user){
        Subject subject = SecurityUtils.getSubject();
        User old = (User) subject.getPrincipal();
        user.setId(old.getId());
        user.setPwd(old.getPwd());
        user.setSalt(old.getSalt());
        userDAO.save(user);
        modifyShiroInfo(user);
        return new MyResult(200,"修改成功");
    }

    public User getUserByName(String name){
        return userDAO.getUserByName(name);
    }
}
