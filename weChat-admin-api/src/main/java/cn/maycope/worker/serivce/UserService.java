package cn.maycope.worker.serivce;

import cn.maycope.common.utils.QueryPage;
import cn.maycope.worker.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author Maycope
 * @Date 2020/9/5
 * @Version 1.0
 */
public interface UserService {


    void add(User user);

    void update(User user);

    void delete(Long id);

    User findByName(String name);

    IPage<?> list(User user, QueryPage queryPage);
    List<User> list(User user);

    User findById(Long id);
}
