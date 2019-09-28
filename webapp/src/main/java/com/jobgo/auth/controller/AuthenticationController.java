package com.jobgo.auth.controller;

import com.jobgo.auth.bo.UserInfo;
import com.jobgo.auth.properties.JwtProperties;
import com.jobgo.auth.service.AuthenticationService;
import com.jobgo.auth.utils.JwtUtils;
import com.jobgo.commom.enums.ExceptionEnum;
import com.jobgo.commom.exception.MyException;
import com.jobgo.commom.utils.CookieUtils;
import com.jobgo.commom.vo.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述：授权中心Contoller
 * 创建时间: 2019-08-18 10:45
 * 修改时间: 2019-08-18 10:45
 */
@RestController
public class AuthenticationController {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * 登录授权
     *
     * @param userPhone
     * @param userPassword
     * @return
     */
    @PostMapping("authentication")
    public ResponseEntity<ResponseResult<Void>> authentication(
            @RequestParam("userPhone") String userPhone,
            @RequestParam("userPassword") String userPassword,
            HttpServletRequest request,
            HttpServletResponse response) {

        // 登录校验
        String token = this.authenticationService.authentication(userPhone, userPassword);
        if (StringUtils.isBlank(token)) {
            throw new MyException(ExceptionEnum.NO_AUTHENTICATION);
        }

        // 将token写入cookie,并指定httpOnly为true，防止通过JS获取和修改
        CookieUtils.setCookie(request, response, jwtProperties.getCookieName(),
                token, jwtProperties.getCookieMaxAge(), true);
        return ResponseEntity.ok(new ResponseResult<>(200, "已授权"));
    }

    /**
     * 验证用户信息
     *
     * @param token
     * @return
     */
    @GetMapping("verify")
    public ResponseEntity<ResponseResult<UserInfo>> verifyUser(@CookieValue("JOBGO_TOKEN") String token, HttpServletRequest request, HttpServletResponse response) {
        try {

            // 从token中解析token信息
            UserInfo userInfo = JwtUtils.getInfoFromToken(token, this.jwtProperties.getPublicKey());

            // 解析成功要重新刷新token
            token = JwtUtils.generateToken(userInfo, this.jwtProperties.getPrivateKey(), this.jwtProperties.getExpire());

            // 更新cookie中的token
            CookieUtils.setCookie(request, response, this.jwtProperties.getCookieName(), token, this.jwtProperties.getCookieMaxAge(), true);

            // 解析成功返回用户信息
            return ResponseEntity.ok(new ResponseResult<>(200, "验证成功", userInfo));

        } catch (Exception e) {
            throw new MyException(ExceptionEnum.VERIFY_USER_FAIL);
        }
    }
}
