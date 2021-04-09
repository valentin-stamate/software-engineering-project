package com.bfourclass.euopendata.user;

import com.bfourclass.euopendata.security.SimpleHashingAlgo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            UserModel userModelA = new UserModel("ValentinSt", "valentinstamate@gmail.com", SimpleHashingAlgo.hash("dcy3w8r7ds4lr329"), "~/vali.png", true);
            UserModel userModelB = new UserModel("Lorem", "loremipsum@gmail.com", SimpleHashingAlgo.hash("dsankd5465tf4tkal"), "~/lorem.jpg", true);
            UserModel userModelC = new UserModel("user", "nomail@mail.com", SimpleHashingAlgo.hash("password"), "", true);
            userRepository.save(userModelA);
            userRepository.save(userModelB);
            userRepository.save(userModelC);
        };
    }

}
