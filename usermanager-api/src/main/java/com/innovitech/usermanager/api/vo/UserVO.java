package com.innovitech.usermanager.api.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserVO {

    private Long id;

    private String name;

    private String email;

    private String password;

}
