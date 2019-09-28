package top.wffanshao.office.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.wffanshao.office.dao.UserDAO;
import top.wffanshao.office.enums.ExceptionEnum;
import top.wffanshao.office.exception.MyException;
import top.wffanshao.office.pojo.OfficeDbUser;
import top.wffanshao.office.service.UserService;
import top.wffanshao.office.utils.CodecUtils;

import java.sql.Timestamp;

/**
 * 描述：用户Service实现类
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

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
    @Transactional
    public Boolean register(OfficeDbUser user) {

        // 3.密码加密
        String encodePassword = CodecUtils.passwordBcryptEncode(user.getUserName().trim(), user.getUserPasswd().trim());
        user.setUserPasswd(encodePassword);

        user.setAdmin(0);
        user.setRegisterTime(new Timestamp(System.currentTimeMillis()));

        // 4.写入数据库
        OfficeDbUser save = userDAO.save(user);

        if (save.getUserId() == 0) {
            log.error("[用户服务] 用户注册失败，用户名:{}", user.getUserName());
            return false;
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
}
