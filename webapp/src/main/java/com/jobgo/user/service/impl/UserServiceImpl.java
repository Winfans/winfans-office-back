package com.jobgo.user.service.impl;

import com.jobgo.commom.enums.ExceptionEnum;
import com.jobgo.commom.exception.MyException;
import com.jobgo.commom.utils.CodecUtils;
import com.jobgo.commom.utils.JsonUtils;
import com.jobgo.commom.utils.NumberUtils;
import com.jobgo.user.dao.UserDAO;
import com.jobgo.user.pojo.JobgoJgDbUser;
import com.jobgo.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * 描述：用户Service实现类
 * 创建时间: 2019-08-13 8:41
 * 修改时间: 2019-08-13 8:41
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String PHONE_KEY_PREFIX = "jobgo:user:code:phone:";

    private static final String INFO_KEY_PREFIX2 = "jobgo:user:info:";


    /**
     * 描述：检查用户名和用户手机号码是否可用
     *
     * @param data
     * @param type 1：手机号码 2：用户名
     * @return true: 可用 false：不可用
     */
    @Override
    public Boolean checkUserData(String data, Integer type) {
        switch (type) {
            case 1:
                return userDao.countJobgoJgDbUserByUserPhone(data) == 0;
            case 2:
                return userDao.countJobgoJgDbUserByUserName(data) == 0;
            default:
                log.error("[用户服务] 无效用户数据类型");
                return null;
        }
    }

    /**
     * 描述：发送手机验证码
     *
     * @param userPhone
     * @return
     */
    @Override
    public Boolean sendVerifyCode(String userPhone) {

        // 1.生成验证码
        String code = NumberUtils.generateCode(6);
        Map<String, String> msg = new HashMap<>();
        msg.put("phone", userPhone);
        msg.put("code", code);

        try {

            // 2.发送短信
            this.amqpTemplate.convertAndSend("jobgo.sms.exchange", "sms.verify.code", msg);

            // 3.将code存入redis
            stringRedisTemplate.opsForValue().set(PHONE_KEY_PREFIX + userPhone, code, 5, TimeUnit.MINUTES);

            return true;
        } catch (Exception e) {
            log.error("[短信服务] 发送短信验证码失败，手机号码:{}", userPhone, e);
            return false;
        }
    }

    /**
     * 用户注册
     *
     * @param user
     * @param code
     * @return
     */
    @Transactional
    @Override
    public Boolean register(JobgoJgDbUser user, String code) {
        String key = PHONE_KEY_PREFIX + user.getUserPhone();

        // 1.从redis中取出验证码
        String codeCache = stringRedisTemplate.opsForValue().get(key);

        // 2.检查验证码是否正确
        if (!StringUtils.equals(codeCache, code)) {
            // 不正确，返回
            return false;
        }
        // 3.密码加密
        String encodePassword = CodecUtils.passwordBcryptEncode(user.getUserPhone().trim(), user.getUserPassword().trim());
        user.setUserPassword(encodePassword);

        user.setUserIdentified(1);
        user.setIsAdmin(0);
        user.setCreatedTime(new Timestamp(new Date().getTime()));
        user.setUpdatedTime(new Timestamp(new Date().getTime()));

        // 4.写入数据库
        JobgoJgDbUser save = userDao.save(user);
        if (ObjectUtils.isEmpty(save)) {
            log.error("[用户服务] 用户注册失败，手机号码:{}", user.getUserPhone());
            return false;
        }
        try {
            // 5.如果注册成功，则删掉redis中的code
            this.stringRedisTemplate.delete(PHONE_KEY_PREFIX + save.getUserPhone());
            return true;
        } catch (Exception e) {
            log.error("[用户服务] 删除缓存验证码失败，验证码:{}", code, e);
            return false;
        }
    }

    /**
     * 用户验证
     *
     * @param userPhone
     * @param userPassword
     * @return
     */
    @Override
    public JobgoJgDbUser queryUser(String userPhone, String userPassword) {
        // 1.缓存中查询
        BoundHashOperations<String, Object, Object> hashOps = this.stringRedisTemplate.boundHashOps(INFO_KEY_PREFIX2);
        String userStr = (String) hashOps.get(userPhone);
        JobgoJgDbUser user;
        if (StringUtils.isBlank(userStr)) {
            // 在缓存中没有查到，去数据库查,查到放入缓存当中
            user = userDao.findJobgoJgDbUsersByUserPhone(userPhone);
            hashOps.put(user.getUserPhone(), JsonUtils.serialize(user));
        } else {
            user = JsonUtils.parse(userStr, JobgoJgDbUser.class);
        }

        // 2.校验用户名
        if (user == null) {
            return null;
        }

        // 3. 校验密码
        boolean result = CodecUtils.passwordConfirm(userPhone + userPassword, user.getUserPassword());

        if (!result) {
            // 密码不正确
            throw new MyException(ExceptionEnum.PASSWORD_ERROR);
        }

        return user;
    }
}
