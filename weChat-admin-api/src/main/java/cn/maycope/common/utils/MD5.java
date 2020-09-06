package cn.maycope.common.utils;

/**
 * @Author Maycope
 * @Date 2020/9/4
 * @Version 1.0
 */

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.DigestUtils;


/**
 *  Md5 加密
 *  对于Md5加密而言可以自行使用MD5的加密的方式 也可以使用到 shiro框架自带的加密的方式
 *  1. 使用到自己的加密的方式
 */
public class MD5 {

    /**
     * 利用到自身的加密的手段进行加密处理
     * 分为两级加密处理
     * 一级是盐值已经知道 进行机密，二级是利用到每个用户不同的用户名进行加密处理。
     * 二级加密的好处在于可以在数据传递的时候传递一级的加密密码，而不会被发现密码，
     * 到数据库中再进行二级密码的加密处理。
     * @param src
     * @return
     */
    public static  String md5(String src){
        return  DigestUtils.md5DigestAsHex(src.getBytes());
    }
    private static final String salt = "1a2b3c4d";

    public static String inputPassToFormPass(String inputPass) {
        String str = ""+salt.charAt(0)+salt.charAt(2) + inputPass +salt.charAt(5) + salt.charAt(4);

        // 一级加密
        return md5(str);
    }
    public static String formPassToDBPass(String formPass, String salt) {
        String str = ""+salt.charAt(0)+salt.charAt(2) + formPass +salt.charAt(5) + salt.charAt(4);
        // 二级加密
        return md5(str);
    }
    public static String encryptPasswordTwo(String saltDB, String inputPass) {
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDBPass(formPass, saltDB);

        return dbPass;
    }

    /**
     *
     * 第二种加密的方法是利用到Shiro本身自带的加密的方式，选择性选择加密的算法是是哪种算法进行加密的处理。
     *
     */
    // 算法名称
    private static final String ALGORITH_NAME = "MD5";
    // 迭代次数
    private static final int HASH_ITERATIONS = 1024;

    //加密算法
    public static String encryptPassword(String username, String password) {
        if (username == null || password == null) {
            return null;
        }
        return new SimpleHash(
                ALGORITH_NAME,
                StringUtils.lowerCase(StringUtils.lowerCase(password)),
                ByteSource.Util.bytes(StringUtils.lowerCase(username)),
                HASH_ITERATIONS).toHex();
    }


}
