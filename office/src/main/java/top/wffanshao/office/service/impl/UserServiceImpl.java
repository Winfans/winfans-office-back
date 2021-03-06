package top.wffanshao.office.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.wffanshao.office.bo.UserInfo;
import top.wffanshao.office.dao.UserCustomerDAO;
import top.wffanshao.office.dao.UserDAO;
import top.wffanshao.office.dao.UserRoleDAO;
import top.wffanshao.office.dao.UserTeamDAO;
import top.wffanshao.office.dto.UserDTO;
import top.wffanshao.office.enums.ExceptionEnum;
import top.wffanshao.office.enums.RoleEnum;
import top.wffanshao.office.enums.StatusEnum;
import top.wffanshao.office.exception.MyException;
import top.wffanshao.office.pojo.OfficeDbUser;
import top.wffanshao.office.pojo.OfficeDbUserRole;
import top.wffanshao.office.properties.JwtProperties;
import top.wffanshao.office.service.UserService;
import top.wffanshao.office.utils.CodecUtils;
import top.wffanshao.office.utils.JwtUtils;
import top.wffanshao.office.vo.ResponsePage;

import javax.persistence.criteria.Path;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * 描述：用户Service实现类
 *
 * @author 杨炜帆
 * @date 2019/10/12
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Autowired
    private UserCustomerDAO userCustomerDAO;

    @Autowired
    private UserTeamDAO userTeamDAO;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 描述：检查用户名是否可用
     *
     * @param userName
     * @return true: 可用 false：不可用
     */
    @Override
    public Boolean checkUserName(String userName) {
        if (userDAO.countOfficeDbUserByUserName(userName) != 0) {
            log.error("[用户服务] 用户名不可用");
            return false;
        }
        return true;
    }

    /**
     * 描述： 用户注册
     *
     * @param user
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean register(OfficeDbUser user) {

        // 3.密码加密
        String encodePassword = CodecUtils.passwordBcryptEncode(user.getUserName().trim(), user.getUserPasswd().trim());
        user.setUserPasswd(encodePassword);
        user.setRegisterTime(new Timestamp(System.currentTimeMillis()));
        user.setStatus(1);

        // 4.写入数据库
        OfficeDbUser save = userDAO.save(user);

        if (save.getUserId() == 0) {
            log.error("[用户服务] 用户注册失败，用户名:{}", user.getUserName());
            return false;
        }
        OfficeDbUserRole userRole = new OfficeDbUserRole();
        userRole.setUserId(user.getUserId());
        userRole.setRoleId(RoleEnum.USER.getRoleId());
        userRole.setStatus(StatusEnum.INVALID.getCode());
        OfficeDbUserRole savedUserRole = userRoleDAO.saveAndFlush(userRole);

        if (savedUserRole == null) {
            throw new MyException(ExceptionEnum.USER_REGISTER_FAIL);
        }

        return true;

    }

    /**
     * 描述：用户验证
     *
     * @param userName
     * @param userPasswd
     * @return
     */
    @Override
    public OfficeDbUser findUserByUserNameAndUserPasswd(String userName, String userPasswd) {

        OfficeDbUser user = userDAO.findOfficeDbUserByUserName(userName);

        // 校验用户名
        if (user == null) {
            return null;
        }

        // 3. 校验密码
        boolean result = CodecUtils.passwordConfirm(userName + userPasswd, user.getUserPasswd());

        if (!result) {
            // 密码不正确
            throw new MyException(ExceptionEnum.PASSWORD_ERROR);
        }

        return user;
    }

    /**
     * 描述：查询用户信息
     *
     * @param token token
     * @return 用户信息
     */
    @Override
    public UserDTO getUserDTO(String token) {

        // 解析token获取用户id
        UserInfo userInfo;
        try {
            userInfo = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
        } catch (Exception e) {
            log.error("[团队服务] 解析用户token失败{}", e);
            throw new MyException(ExceptionEnum.PARSE_USER_TOKEN_FAIL);
        }

        Optional<OfficeDbUser> optional = userDAO.findById(userInfo.getUserId());
        UserDTO userDTO = new UserDTO();

        if (optional.isPresent()) {
            OfficeDbUser user = optional.get();
            userDTO.setUserName(user.getUserName());
            userDTO.setHeadImg(user.getHeadImg());
        } else {
            throw new MyException(ExceptionEnum.GET_USER_FAIL);
        }

        return userDTO;
    }

    /**
     * 描述：分页查询所有用户信息
     *
     * @return
     */
    @Override
    public List<UserDTO> findAllUserByPage() {
        System.out.println("test");
        List<OfficeDbUser> userList = userDAO.findAll();

        List<UserDTO> userDtoList = new LinkedList<>();

        userList.forEach(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(user.getUserId());
            userDTO.setUserName(user.getUserName());
            userDtoList.add(userDTO);
        });

        return userDtoList;
    }

    /**
     * 描述：根据userId查询用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public UserDTO findUserByUserId(Integer userId) {

        Optional<OfficeDbUser> optional = userDAO.findById(userId);

        if (optional.isPresent()) {
            OfficeDbUser user = optional.get();
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(user.getUserId());
            userDTO.setUserName(user.getUserName());
            return userDTO;
        }
        return null;
    }

    /**
     * 描述：删除用户信息
     *
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteUserByUserId(Integer userId) {

        try {
            userRoleDAO.findAll().forEach(userRole -> {
                if (userRole.getUserId() == userId) {
                    userRoleDAO.deleteByUserId(userId);
                }
            });

            userTeamDAO.findAll().forEach(userTeam -> {
                if (userTeam.getUserId() == userId) {
                    userTeamDAO.deleteByUserId(userId);
                }
            });

            userCustomerDAO.findAll().forEach(userCustomer -> {
                if (userCustomer.getUserId() == userId) {
                    userCustomerDAO.deleteByUserId(userId);
                }
            });

            if (userDAO.existsById(userId)) {
                userDAO.deleteById(userId);
            }

            return true;

        } catch (Exception e) {
            throw new MyException(ExceptionEnum.USER_DELETE_FAIL);
        }
    }

    /**
     * 描述：根据id修改相对应的用户信息
     *
     * @param userId
     * @param userDTO
     * @return
     */
    @Override
    public Boolean updateUserByUserId(Integer userId, UserDTO userDTO) {
        Optional<OfficeDbUser> optional = userDAO.findById(userId);

        if (optional.isPresent()) {
            OfficeDbUser user = optional.get();
            user.setUserName(userDTO.getUserName());
            userDAO.saveAndFlush(user);
            return true;

        }
        return false;
    }
}
