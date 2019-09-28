package top.wffanshao.office.service;


import top.wffanshao.office.pojo.OfficeDbUser;

/**
 * 描述：用户Service
 */
public interface UserService {

    /**
     * 描述：检查用户名是否可用
     *
     * @param userName
     * @return true: 可用 false：不可用
     */
    Boolean checkUserName(String userName);

    /**
     * 描述： 用户注册
     *
     * @param user
     * @return
     */
    Boolean register(OfficeDbUser user);

    /**
     * 描述：用户验证
     *
     * @param userName
     * @param userPasswd
     * @return
     */
    OfficeDbUser findUserByUserNameAndUserPasswd(String userName, String userPasswd);

}
