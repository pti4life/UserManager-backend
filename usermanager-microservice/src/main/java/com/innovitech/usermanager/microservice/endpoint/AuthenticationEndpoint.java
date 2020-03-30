package com.innovitech.usermanager.microservice.endpoint;

import com.innovitech.usermanager.api.service.UserService;
import com.innovitech.usermanager.api.vo.UserVO;
import com.innovitech.usermanager.microservice.util.JwtTokenProvider;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class AuthenticationEndpoint {

    @Inject
    private UserService userService;

    @Inject
    private JwtTokenProvider jwtTokenProvider;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(UserVO user) {
        userService.authenticate(user);
        return Response.ok(jwtTokenProvider.createToken(user.getEmail())).build();
    }
}
