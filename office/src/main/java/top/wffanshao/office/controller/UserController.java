package top.wffanshao.office.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.wffanshao.office.enums.ExceptionEnum;
import top.wffanshao.office.exception.MyException;
import top.wffanshao.office.pojo.OfficeDbUser;
import top.wffanshao.office.service.UserService;
import top.wffanshao.office.vo.ResponseResult;


/**
 * 描述：用户Controller
 */
@RestController
@RequestMapping("user")
public final class UserController {

    @Autowired
    private UserService userService;


    /**
     * 描述：检查用户名是否可用
     *
     * @param userName 用户名
     * @return true: 可用 false：不可用
     */
    @GetMapping("check/{userName}")
    public ResponseEntity<ResponseResult<Boolean>> checkUserData(@PathVariable("userName") String userName) {
        Boolean result = userService.checkUserName(userName);
        if (!result) {
            throw new MyException(ExceptionEnum.USERNAME_CANNOT_USED);
        }
        return ResponseEntity.ok(new ResponseResult<>(200, "用户名可用", result));
    }


    /**
     * 描述：注册
     *
     * @param user
     * @return
     */
    @PostMapping("register")
    public ResponseEntity<ResponseResult<Void>> register(OfficeDbUser user) {

        Boolean result = this.userService.register(user);

        if (!result) {
            throw new MyException(ExceptionEnum.USER_REGISTER_FAIL);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseResult<>(201, "注册成功"));
    }


}
