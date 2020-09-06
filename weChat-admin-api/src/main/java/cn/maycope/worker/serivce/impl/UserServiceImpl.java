package cn.maycope.worker.serivce.impl;

import cn.maycope.common.exception.GlobalException;
import cn.maycope.common.utils.LambdaQueryProperties;
import cn.maycope.common.utils.MD5;
import cn.maycope.common.utils.QueryPage;
import cn.maycope.worker.entity.User;
import cn.maycope.worker.mapper.UserMapper;
import cn.maycope.worker.serivce.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Maycope
 * @Date 2020/9/5
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LambdaQueryProperties lambdaQueryProperties;

    @Override
    public void add(User user) {
        User users = this.findByName(user.getUsername());
        if(users != null)
        {
            throw  new GlobalException("用户名已经存在");
        }

    }

    @Override
    public void update(User user) {
        if(user.getPassword()!=null && user.getUsername()!=null){
            String en_password = MD5.encryptPassword(user.getUsername(),user.getPassword());
            user.setPassword(en_password);
        }
        else {
            user.setPassword(null);
        }
//         这些方法都是自带的呀
        userMapper.updateById(user);
    }

    @Override
    public void delete(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public User findById(Long id) {
        LambdaQueryWrapper<User> lambdaQueryWrapper =lambdaQueryProperties.getLambdaQueryWrapper();
        lambdaQueryWrapper.eq(User::getId,id);
        List<User> list =userMapper.selectList(lambdaQueryWrapper);
        return  list.size()>0?list.get(0):null;
    }

    @Override
    public User findByName(String name) {
//         如何把这个新定义的东西抽出来
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,userMapper);
        List<User> list = userMapper.selectList(queryWrapper);
        return  list.size()>0?list.get(0):null;

    }

    @Override
    @Transactional
    public IPage<?> list(User user, QueryPage queryPage) {
        IPage<User> page = new Page<>(queryPage.getCurrentPage(),queryPage.getLimit());
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(user.getUsername()),User::getUsername,user.getUsername());
        queryWrapper.orderByDesc(User::getId);
        return  userMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<User> list(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(user.getUsername()),User::getUsername,user.getPassword());
        queryWrapper.orderByDesc(User::getId);
        return  userMapper.selectList(queryWrapper);
    }
}
