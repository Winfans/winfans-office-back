package com.jobgo.auth.service;


/**
 * 描述：授权中心Service
 * 创建时间: 2019-08-18 10:45
 * 修改时间: 2019-08-18 10:45
 */
public interface AuthenticationService {

    /**
     * 登录授权
     *
     * @param userPhone
     * @param userPassword
     * @return
     */
    String authentication(String userPhone, String userPassword);
}
