package com.jobgo.auth.service.impl;

import com.jobgo.auth.bo.UserInfo;
import com.jobgo.auth.properties.JwtProperties;
import com.jobgo.auth.service.AuthenticationService;
import com.jobgo.auth.utils.JwtUtils;
import com.jobgo.user.pojo.JobgoJgDbUser;
import com.jobgo.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述：授权中心Service实现类
 * 创建时间: 2019-08-18 10:45
 * 修改时间: 2019-08-18 10:45
 */
@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {


    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private UserService userService;

    /**
     * 登录授权
     *
     * @param userPhone
     * @param userPassword
     * @return
     */
    @Override
    public String authentication(String userPhone, String userPassword) {
        try {

            // 调用微服务查询用户信息
            JobgoJgDbUser user = userService.queryUser(userPhone, userPassword);

            // 获取用户id
            int userId = user.getUserId();

            // 查询结果为空，则直接返回null
            if (userId == 0) {
                return null;
            }

            // 查询结果不为空，则生成token
            String token = JwtUtils.generateToken(new UserInfo(userId, user.getUserPhone()),
                    jwtProperties.getPrivateKey(), jwtProperties.getExpire());

            return token;
        } catch (Exception e) {
            log.error("[授权中心] token生成失败", e);
            return null;
        }
    }
}
