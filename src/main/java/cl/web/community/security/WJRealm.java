package cl.web.community.security;

import cl.web.community.Service.UserService;
import cl.web.community.entity.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;



public class WJRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;



    //获取认证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username  = authenticationToken.getPrincipal().toString();
        User user = userService.getUserByName(username);
        return new SimpleAuthenticationInfo(user,
                user.getPwd(),
                ByteSource.Util.bytes(user.getSalt()),
                getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
