package com.bfourclass.euopendata;

import com.bfourclass.euopendata.user.forms.FormValidator;
import com.bfourclass.euopendata.user.forms.UserRegisterForm;
import org.junit.jupiter.api.Test;

public class ValidatorsTest {
    @Test
    void userRegistrationFormTest() {
        UserRegisterForm userRegisterForm = new UserRegisterForm("ValentinSt", "stamatevalentin125@gmail.com", "123456789", "http://dsaljkslka.com/picture.png");
        System.out.println(userRegisterForm.isValid());

        System.out.println("");

        System.out.println(FormValidator.isValidUsername("ValentinSt"));
        System.out.println(FormValidator.isValidEmail("stamatevalentin125@gmail.com"));
        System.out.println(FormValidator.isValidPassword("Asd32409,.,09[$%]"));
        System.out.println(FormValidator.isValidLink("https://dsaljkslka.com/picture.png"));

    }
}
