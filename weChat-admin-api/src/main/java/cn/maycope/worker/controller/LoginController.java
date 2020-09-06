package cn.maycope.worker.controller;

import cn.maycope.common.commonResult.ResultCode;
import cn.maycope.common.commonResult.ResultType;
import cn.maycope.common.controller.BaseController;
import cn.maycope.common.exception.GlobalException;
import cn.maycope.common.utils.MD5;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.apache.shiro.subject.Subject;

import java.util.HashMap;
import java.util.Map;


/**
 * @Author Maycope
 * @Date 2020/9/4
 * @Version 1.0
 */
@RestController
public class LoginController extends BaseController {


    @GetMapping("/login")
    public ResultType login(@RequestParam("username") String username,
                            @RequestParam("password") String password)
    {
        if(username == null || password == null)
        {
            return new ResultType(ResultCode.LOGIN_ERROR.getMessgage());
       //    throw  new GlobalException(ResultCode.LOGIN_ERROR.getMessgage());
        }
//        表示通过 shiro获取到当前的登录的用户。
        Subject subject =getSubject();

//         获取加密过后的密码
        String en_password = MD5.encryptPassword(username,password);
        UsernamePasswordToken token =new  UsernamePasswordToken(username,en_password);

        try{
//             其实这些自己都看过视频学习过吧 哎 还是以为自己会了没有做记录啊
//             就是最简单的 获取到subject之后进行操作。
            subject.login(token);
            Map<String,Object> map =new HashMap<>();
            map.put("token",subject.getSession().getId());
            map.put("user",this.getCurrentUser());
            return  new ResultType<>(map);

        }
        catch (Exception e){
            e.printStackTrace();
//             已经封装好了
            return  new ResultType<>(e);
        }

    }

    /**
     * 登录出去
     * @return
     */

    @DeleteMapping("/logout")
    public  ResultType logout()
    {
        // 表示登录出去的功能
        Subject subject =getSubject();
        subject.logout();
        return  new ResultType();
    }


}
