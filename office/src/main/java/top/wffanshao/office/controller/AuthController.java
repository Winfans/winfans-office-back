package top.wffanshao.office.controller;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.wffanshao.office.bo.UserInfo;
import top.wffanshao.office.enums.ExceptionEnum;
import top.wffanshao.office.exception.MyException;
import top.wffanshao.office.properties.JwtProperties;
import top.wffanshao.office.service.AuthService;
import top.wffanshao.office.utils.CookieUtils;
import top.wffanshao.office.utils.JwtUtils;
import top.wffanshao.office.vo.ResponseResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述：认证Controller
 */
@RestController
@RequestMapping("auth")
public final class AuthController {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private AuthService authService;

    @PostMapping("auth")
    public ResponseEntity<ResponseResult<Void>> authentication(
            @RequestParam("userName") String userName,
            @RequestParam("userPasswd") String userPasswd,
            HttpServletRequest request,
            HttpServletResponse response) {

        // 登录校验
        String token = this.authService.auth(userName, userPasswd);

        if (StringUtils.isBlank(token)) {
            throw new MyException(ExceptionEnum.NO_AUTHENTICATION);
        }

        // 将token写入cookie,并指定httpOnly为true，防止通过JS获取和修改
        CookieUtils.setCookie(request, response, jwtProperties.getCookieName(),
                token, jwtProperties.getCookieMaxAge(), true);
        return ResponseEntity.ok(new ResponseResult<>(200, "已授权"));
    }


    /**
     * 描述：验证用户信息
     *
     * @param token
     * @return
     */
    @GetMapping("verify")
    public ResponseEntity<ResponseResult<UserInfo>> verifyUser(@CookieValue("OFFICE_TOKEN") String token, HttpServletRequest request, HttpServletResponse response) {
        try {
            // 从token中解析token信息
            UserInfo userInfo = JwtUtils.getInfoFromToken(token, this.jwtProperties.getPublicKey());

            // 解析成功要重新刷新token
            token = JwtUtils.generateToken(userInfo, this.jwtProperties.getPrivateKey(), this.jwtProperties.getExpire());

            // 更新cookie中的token
            CookieUtils.setCookie(request, response, jwtProperties.getCookieName(),
                    token, jwtProperties.getCookieMaxAge(), true);

            // 解析成功返回用户信息
            return ResponseEntity.ok(new ResponseResult<>(200, "验证成功", userInfo));

        } catch (Exception e) {
            throw new MyException(ExceptionEnum.USER_REGISTER_FAIL);
        }
    }

}
