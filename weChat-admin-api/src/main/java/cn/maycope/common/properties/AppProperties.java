package cn.maycope.common.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author Maycope
 * @Date 2020/9/7
 * @Version 1.0
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:maycope.properties"})
@ConfigurationProperties(prefix = "tycoding")
public class AppProperties {

    private ShiroProperties shiro = new ShiroProperties();
}