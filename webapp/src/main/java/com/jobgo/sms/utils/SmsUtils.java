package com.jobgo.sms.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.jobgo.sms.properties.SmsProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 描述：短信服务工具类
 * 创建时间: 2019-08-16 15:31
 * 修改时间: 2019-08-16 15:31
 */
@Slf4j
@Component
@EnableConfigurationProperties
public class SmsUtils {

    @Autowired
    private SmsProperties prop;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //产品域名,开发者无需替换
    private static final String domain = "dysmsapi.aliyuncs.com";

    //产品域名,开发者无需替换
    private static final String KEY_PREFIX = "sms:phone:";

    private static final long SMS_MIN_INTERVAL_IN_MILLIS = 60000;

    public CommonResponse sendSms(String phoneNumber, String signName, String templateCode, String templateParam) {

        // 按照手机号码限流
        String key = KEY_PREFIX + phoneNumber;
        // 读取时间
        String lastTime = stringRedisTemplate.opsForValue().get(key);

        if (StringUtils.isNotBlank(lastTime)) {
            Long last = Long.valueOf(lastTime);
            // 小于一分钟不能重新发送验证码
            if (System.currentTimeMillis() - last <= SMS_MIN_INTERVAL_IN_MILLIS) {
                log.info("[短信服务] 发送短信验证码频率过高，手机号码:{}", phoneNumber);
                return null;
            }
        }

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        DefaultProfile profile = DefaultProfile.getProfile("default", prop.getAccessKeyId(), prop.getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);

        // 组装请求对象-具体描述见控制台-文档部分内容
        CommonRequest request = new CommonRequest();

        request.setMethod(MethodType.POST);
        request.setDomain(domain);
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "default");

        // 必填:待发送手机号
        request.putQueryParameter("PhoneNumbers", phoneNumber);

        // 必填:短信签名-可在短信控制台中找到
        request.putQueryParameter("SignName", signName);

        // 必填:短信模板-可在短信控制台中找到
        request.putQueryParameter("TemplateCode", templateCode);

        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"
        request.putQueryParameter("TemplateParam", "{\"code\": \"" + templateParam + "\"}");

        // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        // request.putQueryParameter("SmsUpExtendCode", "sms");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        // request.putQueryParameter("OutId", "sms");

        // 发送短信成功后
        try {
            CommonResponse commonResponse = client.getCommonResponse(request);
            if (!StringUtils.contains(commonResponse.getData(), "OK")) {
                log.error("[短信服务] 发送短信验证码失败，手机号码:{}", phoneNumber);
                return null;
            }
            stringRedisTemplate.opsForValue().set(key, String.valueOf(System.currentTimeMillis()), 1, TimeUnit.MINUTES);
            log.info("[短信服务] 发送短信验证码成功，手机号码:{}", phoneNumber);
            return commonResponse;
        } catch (ClientException e) {
            log.error("[短信服务] 发送短信验证码失败，手机号码:{}", phoneNumber);
            return null;
        }
    }

}
