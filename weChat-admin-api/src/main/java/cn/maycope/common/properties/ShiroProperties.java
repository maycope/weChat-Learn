package cn.maycope.common.properties;

import lombok.Data;

/**
 * @Author Maycope
 * @Date 2020/9/4
 * @Version 1.0
 */


@Data
public class ShiroProperties {

    private Long sessionTimeout;
    private int cookieTimeout;
    private  String anonUrl;
    private  String loginUrl;
    private  String successUrl;
    private String logoutUrl;
    private String cipherKey;

}
