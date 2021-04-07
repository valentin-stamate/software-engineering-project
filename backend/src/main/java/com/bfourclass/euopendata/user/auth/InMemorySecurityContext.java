package com.bfourclass.euopendata.user.auth;

/**
 * Autentificare simpla, in-memory, nu in baza de date
 */
public class InMemorySecurityContext implements SecurityContext {

    @Override
    public boolean isValid(String token) {
        return false;
    }

    @Override
    public String generateToken() {
        return null;
    }

    @Override
    public void authenticateUser(String username, String token) {

    }

    @Override
    public void removeToken() {

    }
}
