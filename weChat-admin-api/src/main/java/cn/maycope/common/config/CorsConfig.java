package cn.maycope.common.config;

import  org.springframework.web.filter.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @Author Maycope
 * @Date 2020/9/5
 * @Version 1.0
 */

/**
 * 解决跨域等问题。
 * 不懂什么意思
 */
@Configuration
public class CorsConfig {
//     方法一
    private CorsConfiguration buildConfig()
    {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        对于* 来说 表示所有的地址都可以访问
        corsConfiguration.addAllowedOrigin("*");
//        跨域的请求头
        corsConfiguration.addAllowedHeader("*");
//        跨域请求的方法。
        corsConfiguration.addAllowedOrigin("*");
        return  corsConfiguration;
    }
    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",buildConfig());
        CorsFilter corsFilter =new CorsFilter(source);
        return  corsFilter;
    }

//     方法二
 //   @CrossOrigin(origins = "*",allowCredentials="true",allowedHeaders = "",methods = {})
}
