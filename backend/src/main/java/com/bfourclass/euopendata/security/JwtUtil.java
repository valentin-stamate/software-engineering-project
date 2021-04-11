package com.bfourclass.euopendata.security;

import com.bfourclass.euopendata.user.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * create pom.xml before using and add these dependencies
 *
 *          <dependency>
 *             <groupId>io.jsonwebtoken</groupId>
 *             <artifactId>jjwt</artifactId>
 *             <version>0.9.1</version>
 *         </dependency>
 *         <dependency>
 *             <groupId>javax.xml.bind</groupId>
 *             <artifactId>jaxb-api</artifactId>
 *             <version>2.3.1</version>
 *         </dependency>
 *
 */

public class JwtUtil{
    private String SECRET_KEY= "secret";

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    public String generateToken(UserModel userModel){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userModel.getUsername());
    }
    private String createToken(Map<String,Object> claims,String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000*60*60*12))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact();
    }
    public Boolean validateToken(String token, UserModel userModel){
        final String username=extractUsername(token);
        return (username.equals(userModel.getUsername()) && !isTokenExpired(token));
    }
}