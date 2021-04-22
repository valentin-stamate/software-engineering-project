package com.bfourclass.euopendata;

import com.bfourclass.euopendata.user.UserModel;
import com.bfourclass.euopendata.user.UserService;
import com.bfourclass.euopendata.user.auth.InMemorySecurityContext;
import com.bfourclass.euopendata.user.auth.SecurityContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceTests {

    @Test
    public void checkTokenIsValid() {
        UserService userService = new UserService();
        SecurityContext context = new InMemorySecurityContext();
        String token = context.authenticateUserReturnToken("user");
        Assert.isTrue(userService.checkTokenIsValid(token));
    }

    @Test
    public void sendUserActivationEmail() {
        UserService userService = new UserService();
        UserModel userModel = new UserModel("markel","emi@ta.com","emithau","");
        Assert.isTrue(userService.sendUserActivationEmail(userModel));
    }
    @Test
    public void userExists() {
        UserService userService = new UserService();
        String login = "user";
        Assert.isTrue(userService.userExists(login));
    }

    @Test
    public void getUserFromToken() {
        UserService userService = new UserService();
        SecurityContext context = new InMemorySecurityContext();
        UserModel userModel = new UserModel("user","emi@ta.com","emithau","");
        String token = context.authenticateUserReturnToken("user");
        assertEquals(userModel,userService.getUserFromToken(token));
    }
}

