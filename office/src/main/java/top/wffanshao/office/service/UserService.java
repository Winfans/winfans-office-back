package top.wffanshao.office.service;


import top.wffanshao.office.dto.UserDTO;
import top.wffanshao.office.pojo.OfficeDbUser;
import top.wffanshao.office.vo.ResponsePage;

import java.util.List;

/**
 * 描述：用户Service
 *
 * @author 杨炜帆
 * @date 2019/10/12
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


    /**
     * 描述：查询用户信息
     *
     * @param token token
     * @return 用户信息
     */
    UserDTO getUserDTO(String token);

    /**
     * 描述：分页查询所有用户信息
     *
     * @return
     */
    List<UserDTO> findAllUserByPage();

    /**
     * 描述：根据userId查询用户信息
     *
     * @param userId
     * @return
     */
    UserDTO findUserByUserId(Integer userId);

    /**
     * 描述：删除用户信息
     *
     * @param userId
     * @return
     */
    boolean deleteUserByUserId(Integer userId);

    /**
     * 描述：根据id修改相对应的用户信息
     *
     * @param userId
     * @param userDTO
     * @return
     */
    Boolean updateUserByUserId( Integer userId, UserDTO userDTO);

}
