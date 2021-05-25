package com.bfourclass.euopendata.user.auth;

import com.bfourclass.euopendata.user.UserModel;
import com.bfourclass.euopendata.user.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Autentificare simpla, in-memory, nu in baza de date
 */
public class InMemorySecurityContext implements SecurityContext {
    private static final String SECRET_KEY= "secret";
    private final Map<String, String> tokens;

    public InMemorySecurityContext() {
        tokens = new HashMap<>();
    }

    @Override
    public String extractUsername(String token){
        return tokens.getOrDefault(token, "");
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    @Override
    public boolean isValid(String token, UserService userService) {
        String username = extractUsername(token);
        return userService.userExists(username);
    }

    @Override
    public boolean exists(String token) {
        return tokens.containsKey(token);
    }

    @Override
    public String generateToken(UserModel user){
        Map<String, Object> claims = new HashMap<>();
        final String role = user.isOwner() ? "owner" : user.isAdmin() ? "admin" : "user";
        claims.put("role", role);
        return Jwts.builder().setSubject(user.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+ 1000*60*60*12))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact();
    }

    @Override
    public String authenticateUserReturnToken(UserModel user) {
        String token = generateToken(user);
        tokens.put(token, user.getUsername());
        return token;
    }

    @Override
    public void removeToken(String token) {
        tokens.remove(token);
    }
}
