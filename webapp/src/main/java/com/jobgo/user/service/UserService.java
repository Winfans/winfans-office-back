package com.jobgo.user.service;

import com.jobgo.user.pojo.JobgoJgDbUser;

/**
 * 描述：用户Service
 * 创建时间: 2019-08-13 8:41
 * 修改时间: 2019-08-15 8:41
 */
public interface UserService {


    /**
     * 描述：检查用户名和用户手机号码是否可用
     *
     * @param data
     * @param type 1：手机号码 2：用户名
     * @return true: 可用 false：不可用
     */
    Boolean checkUserData(String data, Integer type);

    /**
     * 描述：发送手机验证码
     *
     * @param userPhone
     * @return
     */
    Boolean sendVerifyCode(String userPhone);

    /**
     * 描述：用户注册
     *
     * @param user
     * @param code
     * @return
     */
    Boolean register(JobgoJgDbUser user, String code);

    /**
     * 描述：用户验证
     *
     * @param userPhone
     * @param userPassword
     * @return
     */
    JobgoJgDbUser queryUser(String userPhone, String userPassword);

}
