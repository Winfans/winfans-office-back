package top.wffanshao.office.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * 描述：密码工具类
 */
public final class CodecUtils {

    /**
     * 描述：密码加密
     *
     * @param userName
     * @param userPasswd
     * @return
     */
    public static String passwordBcryptEncode(String userName, String userPasswd) {
        return new BCryptPasswordEncoder().encode(userName + userPasswd);
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
