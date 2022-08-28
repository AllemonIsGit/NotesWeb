package com.allemon.notesweb.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JWTTokenUtil {
    private final Algorithm algorithm = Algorithm.HMAC512("this is my key");
    private final JWTVerifier verifier = JWT.require(algorithm).build();

    public String createToken(String username) {
        Algorithm algorithm = Algorithm.HMAC512("this is my key");
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(Date.from(Instant.now()))
                .withIssuer("NotesWeb")
                .withExpiresAt(Date.from(Instant.now().plus(15, ChronoUnit.MINUTES)))
                .sign(algorithm);
    }

    public DecodedJWT validateToken(String token) {
        return verifier.verify(token);
    }
}
