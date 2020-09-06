package cn.maycope.common.controller;

import cn.maycope.common.commonResult.ResultCode;
import cn.maycope.common.commonResult.ResultType;
import cn.maycope.common.exception.GlobalException;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Maycope
 * @Date 2020/9/5
 * @Version 1.0
 */
@Slf4j
@RestControllerAdvice
public class MyControllerAdvice {


    @ExceptionHandler(value = GlobalException.class)
    public ResultType globalExceptionHandler(GlobalException e){
        // 打印出现的异常的信息
      log.error("全局异常,{}",e.getMessage());
      e.printStackTrace();
      return new  ResultType<>(ResultCode.SYSTEM_ERROR.getMessgage());
    }

    @InitBinder
    public  void initBinder(WebDataBinder binder){

    }
    @ModelAttribute
    public  void addAttributes(Model model){
        model.addAttribute("author","maycope");
    }

}
