package top.wffanshao.office.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.wffanshao.office.dto.UserDTO;
import top.wffanshao.office.enums.ExceptionEnum;
import top.wffanshao.office.exception.MyException;
import top.wffanshao.office.pojo.OfficeDbUser;
import top.wffanshao.office.service.UserService;
import top.wffanshao.office.vo.ResponsePage;
import top.wffanshao.office.vo.ResponseResult;

import java.util.List;


/**
 * 描述：用户Controller
 *
 * @author 杨炜帆
 * @date 2019/10/12
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

    /**
     * 描述：查询用户信息
     *
     * @param token token
     * @return 用户信息
     */
    @GetMapping("getUserDTO")
    public ResponseEntity<ResponseResult<UserDTO>> getUserDTO(@CookieValue("OFFICE_TOKEN") String token) {
        return ResponseEntity.ok(new ResponseResult<>(200, "查询成功", userService.getUserDTO(token)));
    }

    /**
     * 描述：分页查询所有用户信息
     *
     * @return
     */
    @GetMapping("findAllUserByPage")
    public ResponseEntity<ResponseResult<List<UserDTO>>> findAllUserByPage() {
        return ResponseEntity.ok(new ResponseResult<>(200, "查询成功", userService.findAllUserByPage()));
    }


    /**
     * 描述：根据userId查询用户信息
     *
     * @param userId
     * @return
     */
    @GetMapping("findUserByUserId/{userId}")
    public ResponseEntity<ResponseResult<UserDTO>> findUserByUserId(
            @PathVariable("userId") Integer userId) {
        UserDTO userDto = userService.findUserByUserId(userId);
        if (userDto == null) {
            throw new MyException(ExceptionEnum.USER_NOT_FOUND);
        }
        return ResponseEntity.ok(new ResponseResult<>(200, "查询成功", userDto));
    }

    /**
     * 描述：删除用户信息
     *
     * @param userId
     * @return
     */
    @DeleteMapping("deleteUserByUserId/{userId}")
    public ResponseEntity<ResponseResult<Void>> deleteUserByUserId(
            @PathVariable("userId") Integer userId
    ) {

        boolean result = userService.deleteUserByUserId(userId);

        if (!result) {
            throw new MyException(ExceptionEnum.USER_DELETE_FAIL);
        }
        return ResponseEntity.ok(new ResponseResult<>(200, "删除成功"));
    }


    /**
     * 描述：根据id修改相对应的用户信息
     *
     * @param userId
     * @param userDTO
     * @return
     */
    @PostMapping("updateUserByUserId/{userId}")
    public ResponseEntity<ResponseResult<Void>> updateUserByUserId(
            @PathVariable("userId") Integer userId,
            UserDTO userDTO
    ) {
        Boolean result = userService.updateUserByUserId(userId, userDTO);
        if (!result) {
            throw new MyException(ExceptionEnum.USER_UPDATE_FAIL);
        }
        return ResponseEntity.ok(new ResponseResult<>(200, "修改成功"));
    }


}
