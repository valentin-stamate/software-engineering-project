package com.bfourclass.euopendata;

import com.bfourclass.euopendata.user.UserModel;
import com.bfourclass.euopendata.user.auth.InMemorySecurityContext;
import com.bfourclass.euopendata.user.auth.SecurityContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class UserModelTest {
    @Test
    public void propertiesSetTest() {
        UserModel model = new UserModel("user", "user@mail.com", "pass", "", true, false);
        Assert.notNull(model.getUsername(), "username set");
    }

    @Test
    public void addHotel() {
        UserModel model = new UserModel("user", "user@mail.com", "pass", "", true, false);
        Assert.notNull(model.getUsername(), "username set");
    }
}
