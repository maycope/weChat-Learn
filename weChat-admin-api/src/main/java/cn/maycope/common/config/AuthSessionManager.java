package cn.maycope.common.config;

import cn.maycope.common.commonResult.CommonConstant;
import cn.maycope.common.commonResult.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * @Author Maycope
 * @Date 2020/9/4
 * @Version 1.0
 */
public class AuthSessionManager extends DefaultWebSessionManager {

    public AuthSessionManager(){
        super();
    }

    public Serializable getSessionId(ServletRequest request, ServletResponse response){
//         获取请求头Header 中的Token 登陆时候定义token=SessionID
        String token = WebUtils.toHttp(request).getHeader(CommonConstant.AUTHORIZATION);
        if(!StringUtils.isEmpty(token)){
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID,token);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return token;
        } else {
            // 否则按默认从Cookie中获取JSESSIONID
            return super.getSessionId(request, response);
        }

    }

}
