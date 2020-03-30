package com.innovitech.usermanager.microservice.response;

import com.innovitech.usermanager.api.vo.UserVO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponse {

    private List<UserVO> users;

}
