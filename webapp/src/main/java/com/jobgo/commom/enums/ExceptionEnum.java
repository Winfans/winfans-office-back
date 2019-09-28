package com.jobgo.commom.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 描述：异常枚举
 *
 * 创建时间: 2019-07-16 22:38
 * 修改时间: 2019-07-16 22:38
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ExceptionEnum {
    /**
     * 授权中心
     */
    VERIFY_USER_FAIL(500, "用户验证失败"),
    NO_AUTHENTICATION(401,"未授权"),
    /**
     * 工作微服务
     */
    JOB_NOT_FOUND(404, "工作不存在"),
    DELETE_JOB_FAIL(500, "删除工作失败"),
    UPDATE_JOB_STATUS_FAIL(500, "修改工作状态失败"),


    /**
     * 用户微服务
     */
    REGISTER_FAIL(400, "注册失败"),
    PASSWORD_ERROR(400, "密码错误"),
    USER_NOT_FOUND(404, "用户不存在"),
    VERIFY_CODE_SEND_FAIL(500, "验证码发送失败"),
    INVALID_USER_DATA_TYPE(400, "用户数据类型无效"),
    STUDENT_NOT_FOUND(404, "学生不存在"),
    SCHOOL_NOT_FOUND(404, "学校不存在"),
    ENTERPRISE_NOT_FOUND(404, "企业不存在"),
    SUBMIT_FAIL(400, "提交失败"),
    AUTHENTICATED_OR_AUTHENTICATING(400, "已认证或审核中"),

    /**
     * 订单微服务
     */
    ORDER_NOT_FOUND(404, "订单不存在"),
    ORDER_STATUS_ERROR(400, "订单状态错误"),
    WX_PAY_ERROR(500, "微信支付失败"),
    INVALID_SIGN(400, "无效的签名"),
    INVALID_ORDER_PARAM(400, "无效的订单参数"),
    UPDATE_ORDER_STATUS_ERROR(500, "更新订单状态错误"),


    /**
     * 上传微服务
     */
    UPLOAD_FAIL(400, "上传失败");

    private int code;
    private String msg;
}
