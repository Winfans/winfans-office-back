package top.wffanshao.office.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wffanshao.office.bo.UserInfo;
import top.wffanshao.office.dao.UserDAO;
import top.wffanshao.office.pojo.OfficeDbUser;
import top.wffanshao.office.properties.JwtProperties;
import top.wffanshao.office.service.AuthService;
import top.wffanshao.office.service.UserService;
import top.wffanshao.office.utils.JwtUtils;

import java.sql.Timestamp;


/**
 * 描述：认证Service
 */
@Slf4j
@Service
public final class AuthServiceImpl implements AuthService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 描述：登录授权
     *
     * @param userName
     * @param userPasswd
     * @return
     */
    @Override
    public String auth(String userName, String userPasswd) {
        try {

            // 查询用户信息
            OfficeDbUser user = userService.findUserByUserNameAndUserPasswd(userName, userPasswd);

            // 获取用户id
            int userId = user.getUserId();

            // 查询结果为空，则直接返回null
            if (userId == 0) {
                return null;
            }

            // 查询结果不为空，则生成token
            String token = JwtUtils.generateToken(new UserInfo(userId, user.getUserName()),
                    jwtProperties.getPrivateKey(), jwtProperties.getExpire());

            System.out.println();
            if (user.getFirstLoginTime() == null) {
                user.setFirstLoginTime(new Timestamp(System.currentTimeMillis()));
            }

            user.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
            userDAO.save(user);

            // 返回token
            return token;

        } catch (Exception e) {
            log.error("token生成失败", e);
            return null;
        }
    }
}
