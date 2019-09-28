package top.wffanshao.office.service;


/**
 * 描述：认证Service
 */
public interface AuthService {

    /**
     * 描述：登录授权
     *
     * @param userName
     * @param userPasswd
     * @return
     */
    String auth(String userName, String userPasswd);
}
