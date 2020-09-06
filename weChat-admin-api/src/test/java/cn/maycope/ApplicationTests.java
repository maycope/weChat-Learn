package cn.maycope;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
class ApplicationTests {
    @Test
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

    public static String inputPassToDbPass(String inputPass, String saltDB) {
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDBPass(formPass, saltDB);
        // 结合起来的一次完整加密。
        return dbPass;
    }
    public static void main(String[] args) {
        String  result =inputPassToDbPass("12345","maycope");
        System.out.println(result);
    }
}
