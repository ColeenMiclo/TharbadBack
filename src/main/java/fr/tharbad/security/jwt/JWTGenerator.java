package fr.tharbad.security.jwt;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import fr.tharbad.security.models.UserEntity;

@Component
public class JWTGenerator {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long JWT_EXPIRATION = 86400000;

    //JWT Token generation
    public String generateToken(Authentication authentication) {

        //Get username from authentication object
        UserEntity user = (UserEntity) authentication.getPrincipal();
        String email = user.getEmail();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + JWT_EXPIRATION);

        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(new Date())
            .setExpiration(expirationDate)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    //Get username from JWT Token
    public String getEmailFromJWT(String token) {
        Claims claims =Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody();
        return claims.getSubject(); 
    }

    //Validate JWT Token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("Invalid JWT Token", ex);
    }
}
}
