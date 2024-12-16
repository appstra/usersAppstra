package com.appstra.users.config;

import com.appstra.users.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
    private static String secretKey = "Appstr@2024";
    private static Algorithm algorithm = Algorithm.HMAC256(secretKey);
    public String create(User user){
        return JWT.create()
                .withSubject(user.getUserUser()) //el asunto es el usuario
                .withIssuer("usersAppstra") // creador del tocken
                .withIssuedAt(new Date()) // fecha de creacion
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(4))) // fecha de expiracion
                .withClaim("UserId",user.getUserId())
                .withClaim("personId",user.getPerson().getPersonId())
                .sign(algorithm);
    }

    /**
     * metodo para validar si el JWT es valido
     * @param jwt
     * @return Boolean
     */
    public boolean isValid(String jwt){
        try {
            JWT.require(algorithm)
                    .build()
                    .verify(jwt);
            return true;
        }catch (JWTVerificationException ex){
            return false;
        }
    }

    /**
     * retorna la informacion interna del JWT
     * @param jwt
     * @return retorna un String
     */
    public  String gatUserName(String jwt){
        return JWT.require(algorithm)
                .build()
                .verify(jwt)
                .getSubject();
    }
}
