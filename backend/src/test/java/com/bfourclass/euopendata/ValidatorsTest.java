package com.bfourclass.euopendata;

import com.bfourclass.euopendata.user.forms.FormValidator;
import com.bfourclass.euopendata.user.json.UserRegisterJSONRequest;
import org.junit.jupiter.api.Test;

public class ValidatorsTest {
    @Test
    void userRegistrationFormTest() {
        UserRegisterJSONRequest userRegisterJSONRequest = new UserRegisterJSONRequest("ValentinSt", "stamatevalentin125@gmail.com", "123456789", "http://dsaljkslka.com/picture.png");
        System.out.println(userRegisterJSONRequest.isValid());

        System.out.println("");

        System.out.println(FormValidator.isValidUsername("ValentinSt"));
        System.out.println(FormValidator.isValidEmail("stamatevalentin125@gmail.com"));
        System.out.println(FormValidator.isValidPassword("Asd32409,.,09[$%]"));
        System.out.println(FormValidator.isValidLink("https://dsaljkslka.com/picture.png"));

    }
}
