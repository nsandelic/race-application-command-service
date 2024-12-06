package hr.spring.race.application.command.service.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.logging.Logger;

@Component
public class JwtUtil{

    private static final Logger logger = Logger.getLogger(JwtUtil.class.getName());
    private static final String SECRET_KEY = "2582-8189-4ngr8-s687";
    private static final long EXPIRATION_TIME = 3600000;

    private final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

    public String generateToken(String username, String role) {
        logger.info("JwtUtil - > generateToken(), Username: " + username + ", Role: " + role);
        return JWT.create()
                .withSubject(username)
                .withClaim("role", role)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(algorithm);
    }

    public boolean validateToken(String token) {
        logger.info("JwtUtil - > validateToken(), token: " + token);
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            verifier.verify(token);
            logger.info("JwtUtil - > validation successful");
            return true;
        } catch (JWTVerificationException e) {
            logger.info("JwtUtil - > validation unsuccessful");
            return false;
        }
    }

    public String getUsername(String token) {
        logger.info("JwtUtil - > getUsername(), token: " + token);
        DecodedJWT decodedJWT = JWT.require(algorithm)
                .build()
                .verify(token);
        logger.info("JwtUtil - > Returning username: " + decodedJWT.getSubject());
        return decodedJWT.getSubject();
    }

    public String getRole(String token) {
        logger.info("JwtUtil - > getRole(), token: " + token);
        DecodedJWT decodedJWT = JWT.require(algorithm)
                .build()
                .verify(token);
        logger.info("JwtUtil - > Returning role: " + decodedJWT.getClaim("role").asString());
        return decodedJWT.getClaim("role").asString();
    }
}
