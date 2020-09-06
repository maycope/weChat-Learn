package cn.maycope.common.utils;

import cn.maycope.worker.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Maycope
 * @Date 2020/9/5
 * @Version 1.0
 */


@Data
@Configuration
public class LambdaQueryProperties {

    LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
}
