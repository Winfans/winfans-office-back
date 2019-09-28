package com.jobgo.commom.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * 描述：密码工具类
 * 创建时间: 2019-08-17 17:22
 * 修改时间: 2019-08-17 17:22
 */
public class CodecUtils {

    /**
     * 描述：密码加密
     *
     * @param userPhone
     * @param password
     * @return
     */
    public static String passwordBcryptEncode(String userPhone, String password) {
        return new BCryptPasswordEncoder().encode(userPhone + password);
    }

    /**
     * 描述：判断输入的密码与加密后的密码是否一致
     *
     * @param rawPassword
     * @param encodePassword
     * @return
     */
    public static Boolean passwordConfirm(String rawPassword, String encodePassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, encodePassword);
    }
}
