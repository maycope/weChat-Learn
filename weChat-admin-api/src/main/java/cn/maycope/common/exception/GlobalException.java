package cn.maycope.common.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author Maycope
 * @Date 2020/9/4
 * @Version 1.0
 */

public class GlobalException extends  RuntimeException{



    @Getter
    @Setter
    private String message;

    public  GlobalException(String message)
    {
        super();
        this.message = message;
    }

}
