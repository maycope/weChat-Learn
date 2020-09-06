package cn.maycope.worker.controller;

import cn.maycope.common.commonResult.ResultCode;
import cn.maycope.common.commonResult.ResultType;
import cn.maycope.common.controller.BaseController;
import cn.maycope.common.exception.GlobalException;
import cn.maycope.common.utils.QueryPage;
import cn.maycope.worker.entity.User;
import cn.maycope.worker.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Maycope
 * @Date 2020/9/5
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public ResultType getUserInfo(){
        Map<String,Object> map = new HashMap<>();
        map.put("user",this.getUserInfo());
        map.put("token",this.getSession().getId());
        return  new ResultType<>(map);
    }

    @PostMapping
    public ResultType save(User user){
        try
        {
            userService.add(user);
            return  new ResultType();
        }catch (Exception e){
            throw  new GlobalException(e.getMessage());

        }
    }
    @PutMapping
    public  ResultType updata(User user){
        try
        {
            userService.update(user);
            return  new ResultType();
        }catch (Exception e){
            throw  new GlobalException(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public  ResultType delete(@PathVariable("id")Long id){
        try{
            userService.delete(id);
            return  new ResultType();
        }catch (Exception e){
            e.printStackTrace();
            throw  new GlobalException(e.getMessage());

        }
    }
    @GetMapping("/{id}")
    public  ResultType findById(@PathVariable("id") Long id){
        try
        {

            return  new ResultType<>(userService.findById(id));
        }catch (Exception e){
            e.printStackTrace();
            throw  new GlobalException(e.getMessage());

        }
    }
    @GetMapping("/findByName")
    public  ResultType findByName(String name){
        try
        {
            return  new ResultType<>(userService.findByName(name));
        }catch (Exception e){
            e.printStackTrace();
            throw  new GlobalException(e.getMessage());

        }
    }
//     getList
    @PostMapping("/list")
    public ResultType getList(@RequestBody User user, QueryPage queryPage){
        return  new ResultType<>(super.getData(userService.list(user, queryPage)));
    }


}
