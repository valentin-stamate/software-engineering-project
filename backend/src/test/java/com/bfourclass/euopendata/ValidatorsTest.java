package com.bfourclass.euopendata;

import static org.assertj.core.api.Assertions.assertThat;

import com.bfourclass.euopendata.user.forms.FormValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ValidatorsTest {

    @Autowired
    FormValidator formValidator;

    @Test
    void usernamePatternGood1() {
        assertThat(formValidator.isValidUsername("booboo_123")).isEqualTo(true);
    }
    @Test
    void usernamePatternGood2() {
        assertThat(formValidator.isValidUsername("AYAY")).isEqualTo(true);
    }
    @Test
    void usernamePatternBad1() {
        assertThat(formValidator.isValidUsername("notG()()Dyo")).isEqualTo(false);
    }
    @Test
    void usernamePatternBad2() {
        assertThat(formValidator.isValidUsername("AYA")).isEqualTo(false);
    }

    @Test
    void passPatternGood1() {
        assertThat(formValidator.isValidPassword("_@@@@@_")).isEqualTo(true);
    }

    @Test
    void passPatternBad1() {
        assertThat(formValidator.isValidPassword("_@@@@")).isEqualTo(false);
    }
}
