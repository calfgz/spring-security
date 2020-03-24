package cn.calfgz.springboot.security.entiy;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhongwm
 * @description:
 * @date 2020-03-24 12:07
 */
@Data
public class MyUser implements Serializable {
    private String userName;

    private String password;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked= true;

    private boolean credentialsNonExpired= true;

    private boolean enabled= true;
}
