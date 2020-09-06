package cn.maycope.common.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Maycope
 * @Date 2020/9/5
 * @Version 1.0
 */
@Configuration
public class MyBatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
     return  new PaginationInterceptor();
    }


}
