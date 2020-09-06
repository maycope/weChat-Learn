package cn.maycope.common.commonResult;

import lombok.*;

import java.io.Serializable;

/**
 * @Author Maycope
 * @Date 2020/9/4
 * @Version 1.0
 */


@Builder
@ToString
@AllArgsConstructor
public class ResultType<T> implements Serializable {

//     这里为什么这么设计 是因为设置一个默认值
    @Getter
    @Setter
    private  int code = CommonConstant.SUCCESS;

    @Getter
    @Setter
    private  String  message =  "success";

    @Getter
    @Setter
    private  T data;

// 无参构造函数
    public  ResultType(){
        super();
    }

//  传递进来一个data数据
    public  ResultType(T data){
        super();
        this.data = data;
    }
    public  ResultType(String message){
        super();
        this.message = message;
    }
//     传递过来code和message
    public ResultType(int code ,String message){
        this.code = code;
        this.message = message;
    }
//
    public  ResultType (T data ,String message){
        super();
        this.data = data;
        this.message = message;
    }
    public ResultType(ResultCode resultCode){
        super();
        this.code = resultCode.getCode();
        this.message = resultCode.getMessgage();
    }
    public  ResultType(Throwable throwable){
        super();
        this.code = CommonConstant.ERROR;
        this.message = throwable.getMessage();
    }
    public  ResultType(String message ,Throwable throwable){
        super();
        this.message = message;
        this.code = CommonConstant.ERROR;
    }

}
