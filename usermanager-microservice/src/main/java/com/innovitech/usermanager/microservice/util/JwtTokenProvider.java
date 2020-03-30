package com.innovitech.usermanager.microservice.util;

import com.innovitech.usermanager.api.exception.CustomException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.container.ContainerRequestContext;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class JwtTokenProvider {

    private static final String AUTH_HEADER_KEY = "Authorization";
    private static final String AUTH_HEADER_PREFIX = "Beaer ";
    private static final long VALIDITY_IN_MILISECONDS = 3600000;

    private static String SECRET_KEY = "TheSafestKeyInTheWorld";

    static {
        SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
    }


    public String createToken(String email) {

        Date now = new Date();
        Date validity = new Date(now.getTime() + VALIDITY_IN_MILISECONDS);

        return Jwts.builder()//
                .claim("email", email)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String getEmail(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().get("email", String.class);
    }

    public String resolveToken(ContainerRequestContext req) {
        List<String> authHeaders = req.getHeaders().get(AUTH_HEADER_KEY);
        if (authHeaders != null && authHeaders.size() > 0) {
            String authToken = authHeaders.get(0);
            return authToken.substring(AUTH_HEADER_PREFIX.length());
        }
        return null;
    }

    public void validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
        } catch (JwtException | IllegalArgumentException e) {
            throw new CustomException("Nincs joga megtekinteni vagy lejárt a tokene", 403);
        }
    }

}
