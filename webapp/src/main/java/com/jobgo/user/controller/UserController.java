package com.jobgo.user.controller;

import com.jobgo.commom.enums.ExceptionEnum;
import com.jobgo.commom.exception.MyException;
import com.jobgo.commom.vo.ResponseResult;
import com.jobgo.user.pojo.JobgoJgDbUser;
import com.jobgo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 描述：用户Controller
 * 创建时间: 2019-08-13 8:41
 * 修改时间: 2019-08-13 8:41
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 描述：检查用户名和用户手机号码是否可用
     *
     * @param data
     * @param type 1：手机号码 2：用户名
     * @return true: 可用 false：不可用
     */
    @GetMapping("check/{data}/{type}")
    public ResponseEntity<ResponseResult<Boolean>> checkUserData(@PathVariable("data") String data,
                                                                 @PathVariable("type") Integer type) {
        Boolean result = this.userService.checkUserData(data, type);
        if (result == null) {
            throw new MyException(ExceptionEnum.INVALID_USER_DATA_TYPE);
        }
        return ResponseEntity.ok(new ResponseResult<>(200, "用户名或用户手机号码是否可用", result));
    }

    /**
     * 描述：发送手机验证码
     *
     * @param userPhone
     * @return
     */
    @PostMapping("code")
    public ResponseEntity<ResponseResult<Void>> sendVerifyCode(@RequestParam("userPhone") String userPhone) {
        Boolean result = this.userService.sendVerifyCode(userPhone);
        if (result == null || !result) {
            throw new MyException(ExceptionEnum.VERIFY_CODE_SEND_FAIL);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseResult<>(201, "验证码发送成功"));
    }

    /**
     * 描述：注册
     *
     * @param user
     * @param code
     * @return
     */
    @PostMapping("register")
    public ResponseEntity<ResponseResult<Void>> register(JobgoJgDbUser user, @RequestParam("code") String code) {
        Boolean boo = this.userService.register(user, code);
        if (boo == null || !boo) {
            throw new MyException(ExceptionEnum.REGISTER_FAIL);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseResult<>(201, "注册成功"));
    }


    /**
     * 用户验证
     *
     * @param userPhone
     * @param userPassword
     * @return
     */
    @GetMapping("query")
    public ResponseEntity<ResponseResult<JobgoJgDbUser>> queryUser(@RequestParam("userPhone") String userPhone, @RequestParam("userPassword") String userPassword) {
        JobgoJgDbUser user = this.userService.queryUser(userPhone, userPassword);

        if (user.getUserId() == 0) {
            throw new MyException(ExceptionEnum.USER_NOT_FOUND);
        }
        return ResponseEntity.ok(new ResponseResult<>(200, "验证成功", user));
    }

}
