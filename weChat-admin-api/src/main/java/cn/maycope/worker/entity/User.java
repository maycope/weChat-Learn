package cn.maycope.worker.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author Maycope
 * @Date 2020/9/4
 * @Version 1.0
 */

@Data

@Accessors(chain = true)
/**
 * 表示setters 返回当前的对象。
 */
// https://blog.csdn.net/weixin_38229356/article/details/82937420
@TableName(value = "weChat_user")
// 表示我们的表名称
public class User implements Serializable {


    @TableId(value = "id" ,type = IdType.AUTO)
    private Long id;
    private  String username;
    private  String password;
    private  String avatar;


}
