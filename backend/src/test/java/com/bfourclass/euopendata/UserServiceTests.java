package com.bfourclass.euopendata;

import com.bfourclass.euopendata.user.UserModel;
import com.bfourclass.euopendata.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = SmartBookingApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class UserServiceTests {

    @Autowired
    UserService userService;

    @Test
    public void checkTokenIsValid() {
        String token = userService.loginUserReturnToken("user");
        Assert.isTrue(userService.checkTokenIsValid(token), "");
    }

    @Test
    public void sendUserActivationEmail() {
        UserModel userModel = new UserModel("markel","emi@ta.com","emithau","");
        Assert.isTrue(userService.sendUserActivationEmail(userModel), "");
    }
    @Test
    public void userExists() {
        String login = "user";
        Assert.isTrue(userService.userExists(login), "");
    }

    @Test
    public void getUserFromToken() {
        UserModel userModel = new UserModel("user","emi@ta.com","emithau","");
        String token = userService.loginUserReturnToken("user");
        assertEquals(userModel.getUsername(), userService.getUserFromToken(token).getUsername());
    }
}

