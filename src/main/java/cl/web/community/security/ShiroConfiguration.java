package cl.web.community.security;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;



@Configuration
public class ShiroConfiguration {
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, Filter> myFilers = new HashMap<>();
        myFilers.put("myAuth", getMyFilter());
        shiroFilterFactoryBean.setFilters(myFilers);

        Map<String,String> filterChainMap = new LinkedHashMap<>();
        filterChainMap.put("/api/test/**","myAuth");
        filterChainMap.put("/api/schedule/**","myAuth");
        filterChainMap.put("/api/comment/create","myAuth");
        filterChainMap.put("/api/img/**","myAuth");
        filterChainMap.put("/api/blog/my/**","myAuth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);


        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getWJRealm());
        return securityManager;
    }

    @Bean
    public WJRealm getWJRealm() {
        WJRealm wjRealm = new WJRealm();
        wjRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return wjRealm;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    public MyAuthorizationFilter getMyFilter(){
        return new MyAuthorizationFilter();
    }
}
