package cn.maycope.common.controller;

import cn.maycope.worker.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Maycope
 * @Date 2020/9/4
 * @Version 1.0
 */



public class BaseController {




//     这一切都是shiro框架帮助我们完成了已经，表示获取到的是当前登录的对象。
    protected static Subject getSubject(){
        return SecurityUtils.getSubject();
    }
    protected User getCurrentUser(){
//         表示获取到的是当前登录的用户。
        return  (User) getSubject().getPrincipal();
    }
    protected Session getSession(){
        return  getSubject().getSession();
    }
    protected  void login(AuthenticationToken token){
        getSubject().login(token);
    }


    public Map<String,Object> getData(IPage<?> page){
        Map<String,Object> map = new HashMap<>();
        map.put("rows",page.getRecords());
        map.put("total",page.getTotal());
        return  map;
    }
    public  Map<String,Object> getToken(){
        Map<String, Object> map = new HashMap<>();
        map.put("token", getSession().getId());
        return map;
    }

}
