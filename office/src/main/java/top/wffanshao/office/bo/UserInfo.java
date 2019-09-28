package top.wffanshao.office.bo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述：用户信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class UserInfo {

    private Integer userId;

    private String userName;

}