package com.innovitech.usermanager.microservice.filter;

import com.innovitech.usermanager.api.exception.CustomException;
import com.innovitech.usermanager.microservice.util.JwtTokenProvider;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Arrays;

@Provider
public class SecurityFilter implements ContainerRequestFilter {

    private static final String[] SECURED_URL_PATHS = {"users", "addresses"};

    @Inject
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        boolean accessSecured = Arrays.stream(SECURED_URL_PATHS)
                .anyMatch(path -> containerRequestContext.getUriInfo().getPath().contains(path));

        if (accessSecured) {
            String token = jwtTokenProvider.resolveToken(containerRequestContext);
            if (token != null) {
                jwtTokenProvider.validateToken(token);
            } else {
                throw new CustomException("Nincs joga a megtekintéshez", 403);
            }
        }


    }
}
