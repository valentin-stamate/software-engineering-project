package com.bfourclass.euopendata.user;

import com.bfourclass.euopendata.hotel.HotelModel;
import com.bfourclass.euopendata.hotel.HotelRepository;
import com.bfourclass.euopendata.security.SimpleHashingAlgo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, HotelRepository hotelRepository) {
        return args -> {
            UserModel userModelA = new UserModel("ValentinSt", "valentinstamate@gmail.com", SimpleHashingAlgo.hash("dcy3w8r7ds4lr329"), "~/vali.png");
            HotelModel hotelModel = new HotelModel("Unirea", "Iasi");

            userModelA.activateUser();
            userModelA.addHotel(hotelModel);
            hotelRepository.save(hotelModel);

            userRepository.save(userModelA);

            UserModel admin = new UserModel(
                    "admin",
                    "admin@gmail.com",
                    SimpleHashingAlgo.hash("adminis"),
                    "",
                    true,
                    true);
            userRepository.save(admin);
        };
    }

}
