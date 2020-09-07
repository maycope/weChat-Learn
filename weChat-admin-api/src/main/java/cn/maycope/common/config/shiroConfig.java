package cn.maycope.common.config;


import cn.maycope.common.properties.AppProperties;
import cn.maycope.common.properties.ShiroProperties;
import cn.maycope.common.realm.UserRealm;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author Maycope
 * @Date 2020/9/4
 * @Version 1.0
 */

@Configuration
public class shiroConfig {

     @Autowired
     private AppProperties properties;
//
     @Bean
     public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("Manager") DefaultWebSecurityManager defaultWebSessionManager) {
         ShiroProperties shiroProperties = properties.getShiro();
         ShiroFilterFactoryBean bean =new ShiroFilterFactoryBean();
         // 设置安全管理器
         bean.setSecurityManager(defaultWebSessionManager);
         bean.setLoginUrl(shiroProperties.getLoginUrl());
         bean.setSuccessUrl(shiroProperties.getSuccessUrl());

//          进行了高度的封装处理
         /**
          * anon: 不需要进行任何的验证
          * authc: 必须进行验证之后才能够访问。
          * user: 必须拥有记住我这个功能才能够访问
          * perms: 拥有对某个资源的权限才能够访问
          * role: 拥有某个角色权限才能够访问。
          */
         Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
         String[] urls =shiroProperties.getAnonUrl().split(".");
         for(String url: urls){
             filterChainDefinitionMap.put(url,"anon");
         }
         filterChainDefinitionMap.put("/**","user");
         bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
         return  bean;
     }
     @Bean(value = "Manager")
    public SecurityManager securityManager(UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(cacheManager());
        securityManager.setRememberMeManager(rememberMeManager());
        return  securityManager;

    }
//
////     其实下面的都不知道什么意思
    @Bean
    public SimpleCookie rememberMeCookie() {
        ShiroProperties shiro = properties.getShiro();
        // 需要和前端<input name="remember">中的name对应
        SimpleCookie simpleCookie = new SimpleCookie("remember");
        simpleCookie.setMaxAge(shiro.getCookieTimeout());
        return simpleCookie;
    }

    @Bean
    public CookieRememberMeManager rememberMeManager() {
        ShiroProperties shiro = properties.getShiro();
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode(shiro.getCipherKey()));
        return cookieRememberMeManager;
    }
//
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
//    @Bean
//    public CacheManager cacheManager() {
//        return new EhCacheManager();
//    }
//
//    @Bean
//    public SessionDAO sessionDAO() {
//        return new EnterpriseCacheSessionDAO();
//    }
//    @Bean
//    public  AuthSessionManager sessionManager(){
//        ShiroProperties shiroProperties = new ShiroProperties();
//         AuthSessionManager authSessionManager =new AuthSessionManager();
//        Collection<SessionListener> listeners = new ArrayList<>();
//        listeners.add(new ShiroSessionListener());
//        // 设置 session超时时间
//        authSessionManager.setGlobalSessionTimeout(shiroProperties.getSessionTimeout() * 1000L);
//        authSessionManager.setSessionListeners(listeners);
//        authSessionManager.setSessionDAO(sessionDAO());
//        return authSessionManager;
//    }
//@Autowired
//private AppProperties properties;

//    @Bean
//    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
//        ShiroProperties shiro = properties.getShiro();
//        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
//        filter.setSecurityManager(securityManager);
//        filter.setLoginUrl(shiro.getLoginUrl());
//        filter.setSuccessUrl(shiro.getSuccessUrl());
//
//        Map<String, String> filterChain = new LinkedHashMap<>();
//        String[] urls = shiro.getAnonUrl().split(",");
//        for (String url : urls) {
//            filterChain.put(url, "anon");
//        }
//        filterChain.put("/**", "user");
////        filterChain.put("/**", "anon");
//        filter.setFilterChainDefinitionMap(filterChain);
//        return filter;
//    }
//
//    @Bean
//    public SecurityManager securityManager(UserRealm authRealm) {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setRealm(authRealm);
//        securityManager.setSessionManager(sessionManager());
//        securityManager.setCacheManager(cacheManager());
//        securityManager.setRememberMeManager(rememberMeManager());
//        return securityManager;
//    }

//    @Bean
//    public SimpleCookie rememberMeCookie() {
//        ShiroProperties shiro = properties.getShiro();
//        // 需要和前端<input name="remember">中的name对应
//        SimpleCookie simpleCookie = new SimpleCookie("remember");
//        simpleCookie.setMaxAge(shiro.getCookieTimeout());
//        return simpleCookie;
//    }
//
//    @Bean
//    public CookieRememberMeManager rememberMeManager() {
//        ShiroProperties shiro = properties.getShiro();
//        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
//        cookieRememberMeManager.setCookie(rememberMeCookie());
//        cookieRememberMeManager.setCipherKey(Base64.decode(shiro.getCipherKey()));
//        return cookieRememberMeManager;
//    }

//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//        return authorizationAttributeSourceAdvisor;
//    }

    @Bean
    public CacheManager cacheManager() {
        return new EhCacheManager();
    }

    @Bean
    public SessionDAO sessionDAO() {
        return new EnterpriseCacheSessionDAO();
    }

    @Bean
    public AuthSessionManager sessionManager() {
        // 自定义SessionManager，校验请求头中的Token信息
        AuthSessionManager sessionManager = new AuthSessionManager();
        Collection<SessionListener> listeners = new ArrayList<>();
        listeners.add(new ShiroSessionListener());
        // 设置 session超时时间
        sessionManager.setGlobalSessionTimeout(properties.getShiro().getSessionTimeout() * 1000L);
        sessionManager.setSessionListeners(listeners);
        sessionManager.setSessionDAO(sessionDAO());
        return sessionManager;
    }

}
