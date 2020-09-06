package cn.maycope.common.commonResult;

/**
 * @Author Maycope
 * @Date 2020/9/4
 * @Version 1.0
 */

import  lombok.Getter;
import  lombok.AllArgsConstructor;


@Getter
@AllArgsConstructor
public enum ResultCode {

    LOGIN_ERROR(500, "用户名或密码错误"),
    TOKEN_ERROR(401, "Token已失效，请重新登录"),
    PARAM_ERROR(401, "参数错误"),
    USER_ERROR(500, "获取用户信息失败"),
    LOGOUT_ERROR(500, "退出失败"),
    SYSTEM_ERROR(500, "系统内部错误"),
    COMMON_ERROR(500, "系统内部错误"),
    FILE_ERROR(400, "上传的文件为空"),
    COMMON_SUCCESS(200, "成功"),
    RESET_SUCCESS(200, "重置密码成功");

    private Integer code;
    private  String messgage;


}
