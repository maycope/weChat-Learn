package cn.maycope.common.realm;

import cn.maycope.common.commonResult.ResultCode;
import cn.maycope.worker.entity.User;
import cn.maycope.worker.serivce.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Maycope
 * @Date 2020/9/4
 * @Version 1.0
 */
public class UserRealm extends AuthorizingRealm {


    @Autowired
    private UserService userService;

    /**
     * 进行系列的权限认证
     * 权限认证系列就是对于某一个页面来说 我们添加权限的认证管理，
     * 对于不同的页面我们要求登录的用户需要有不同的权限和身份 可以具体查看此篇文章。
     * https://blog.csdn.net/weixin_44015043/article/details/108015769
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 进行身份的认证
     * 写来写去就这么多的东西呀
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) (token);
        String username = userToken.getUsername();
        if(username == null)
        {
            throw new AuthenticationException(ResultCode.TOKEN_ERROR.getMessgage());
        }
        String password =new String((char[]) userToken.getPassword());
        User user = userService.findByName(username);

        if(user == null || !password.equals(user.getPassword())){
            throw new IncorrectCredentialsException(ResultCode.LOGIN_ERROR.getMessgage());
        }

        return  new SimpleAuthenticationInfo(user,password,getName());

    }
}
