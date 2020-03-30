package com.innovitech.usermanager.microservice.endpoint;

import com.innovitech.usermanager.api.service.UserService;
import com.innovitech.usermanager.api.vo.UserVO;
import com.innovitech.usermanager.microservice.response.UserResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserEndpoint {

    @Inject
    private UserService userService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        UserResponse userResponse = new UserResponse();
        userResponse.setUsers(userService.findAll());
        return Response.ok(userResponse).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(UserVO user) {
        userService.persist(user);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editUser(UserVO userVO) {
        userService.update(userVO);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{userId}")
    public Response deleteUser(@PathParam("userId") String userId) {
        userService.remove(Long.parseLong(userId));
        return Response.ok().build();
    }

}
