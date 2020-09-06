package cn.maycope.common.exceptionHandler;

import cn.maycope.common.commonResult.CommonConstant;
import cn.maycope.common.commonResult.ResultType;
import cn.maycope.common.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author Maycope
 * @Date 2020/9/4
 * @Version 1.0
 */

@Slf4j
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
//     捕捉的是全局的异常。
    @ExceptionHandler(value = Exception.class)
    public ResultType exception(Exception e){
        log.error("内部错误,{}",e.getMessage());
        e.printStackTrace();
        return  new ResultType(e);
    }
//     捕捉的是 自定义的异常信息
    @ExceptionHandler(value = GlobalException.class)
    public ResultType globalExceptionHandle(GlobalException e) {
        log.error("全局异常, {}", e.getMessage());
        e.printStackTrace();
        return new ResultType<>(CommonConstant.ERROR, e.getMessage());
    }
// 捕捉到的是 未授权的异常信息
    @ExceptionHandler(value = UnauthorizedException.class)
    public ResultType handleUnauthorizedException(UnauthorizedException e) {
        log.error("UnauthorizedException, {}", e.getMessage());
        return new ResultType(HttpStatus.FORBIDDEN, e.getMessage());
    }
//  对于身份的认证。
    @ExceptionHandler(value = AuthenticationException.class)
    public ResultType handleAuthenticationException(AuthenticationException e) {
        log.error("AuthenticationException, {}", e.getMessage());
        return new ResultType(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
// 权限的认知。
    @ExceptionHandler(value = AuthorizationException.class)
    public ResultType handleAuthorizationException(AuthorizationException e) {
        log.error("AuthorizationException, {}", e.getMessage());
        return new ResultType<>(HttpStatus.UNAUTHORIZED, e.getMessage());
    }

}
