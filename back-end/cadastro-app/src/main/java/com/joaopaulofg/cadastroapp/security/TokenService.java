package com.joaopaulofg.cadastroapp.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private static final long EXPIRATION = 1000 * 60 * 30; // 30 minutos

    public String generateToken(Long usuarioId, String telefone) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret.getBytes(StandardCharsets.UTF_8));
            return JWT.create()
                    .withIssuer("cadastro-app")
                    .withSubject(usuarioId.toString())
                    .withClaim("telefone", telefone)
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error generating token", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret.getBytes(StandardCharsets.UTF_8));
            return JWT.require(algorithm)
                    .withIssuer("cadastro-app")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }
}
