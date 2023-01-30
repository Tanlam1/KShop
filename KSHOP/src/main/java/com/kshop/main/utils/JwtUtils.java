package com.kshop.main.utils;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.kshop.main.domain.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtils {

    private static String secret = "code_secret";
    
    public String generateJwt(Users users) {
        
        Date issueDate = new Date();
        
        Claims claims = Jwts.claims()
                .setIssuer(users.getId().toString())
                .setIssuedAt(issueDate);
        
        return Jwts.builder().setClaims(claims).compact();
    }
}
