package com.bfourclass.euopendata.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            User userA = new User("ValentinSt", "valentinstamate@gmail.com", "dcy3w8r7ds4lr329");
            User userB = new User("Lorem", "loremipsum@gmail.com", "dsankd5465tf4tkal");
            userRepository.save(userA);
            userRepository.save(userB);
        };
    }

}
