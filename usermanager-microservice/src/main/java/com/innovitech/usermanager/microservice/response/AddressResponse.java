package com.innovitech.usermanager.microservice.response;

import com.innovitech.usermanager.api.vo.AddressVO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddressResponse {

    private List<AddressVO> addresses;

}
