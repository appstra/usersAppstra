package com.appstra.users.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
    private static String secretKey = "Appstr@2024";
    private static Algorithm algorithm = Algorithm.HMAC256(secretKey);
    public String create(String username){
        return JWT.create()
                .withSubject(username) //el asunto es el usuario
                .withIssuer("Appstr@2024") // creador del tocken
                .withIssuedAt(new Date()) // fecha de creacion
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1))) // fecha de expiracion
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
