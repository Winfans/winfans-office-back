package com.jobgo.auth.bo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述：用户信息
 * 创建时间: 2019-08-13 8:41
 * 修改时间: 2019-08-13 8:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    private int userId;

    private String userPhone;

}