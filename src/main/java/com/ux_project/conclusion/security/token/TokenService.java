package com.ux_project.conclusion.security.token;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import com.ux_project.conclusion.domain.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    //@Value{"api.security.token.secret"}
    private String secret;// para DEPLOY

    public String gerarToken(Usuario usuario){
        try{
            Algorithm algorithm = Algorithm.HMAC256("teste");
            String token = JWT.create()
                    .withIssuer("API_PLURI")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException e){
            throw new RuntimeException("ERRO no TOKEN",e);
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256("teste");
            return JWT.require(algorithm)
                    .withIssuer("API_PLURI")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException e ){
            return "Erro no validate Token!";
        }
    }


    private Instant getExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
