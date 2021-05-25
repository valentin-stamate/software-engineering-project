package com.bfourclass.euopendata;

import com.bfourclass.euopendata.user.UserModel;
import com.bfourclass.euopendata.user.auth.InMemorySecurityContext;
import com.bfourclass.euopendata.user.auth.SecurityContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class SecurityContextTest{

    @Test
    public void insertsIntoContext() {
        SecurityContext context = new InMemorySecurityContext();
        UserModel user = new UserModel();
        user.setUsername("user");
        user.setOwner(true);
        String token = context.authenticateUserReturnToken(user);
        Assert.isTrue(context.exists(token), "context inserts on login");
    }

    @Test
    public void removesFromContext() {
        SecurityContext context = new InMemorySecurityContext();
        UserModel user = new UserModel();
        user.setUsername("user");
        user.setOwner(true);
        String token = context.authenticateUserReturnToken(user);
        context.removeToken(token);
        Assert.isTrue(!context.exists(token), "context was removed");
    }

    @Test
    public void generatesToken() {
        SecurityContext context = new InMemorySecurityContext();
        UserModel user = new UserModel();
        user.setUsername("user");
        user.setOwner(true);
        String token = context.generateToken(user);
        Assert.isTrue(token != null && !token.equals(""), "context is not empty");
    }
}
