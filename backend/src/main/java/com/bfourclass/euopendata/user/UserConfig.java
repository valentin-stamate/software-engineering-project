package com.bfourclass.euopendata.user;

import com.bfourclass.euopendata.hotel.HotelModel;
import com.bfourclass.euopendata.hotel.HotelRepository;
import com.bfourclass.euopendata.hotel_review.HotelReviewModel;
import com.bfourclass.euopendata.hotel_review.HotelReviewRepository;
import com.bfourclass.euopendata.security.SimpleHashingAlgo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, HotelRepository hotelRepository, HotelReviewRepository hotelReviewRepository) {
        return args -> {
            UserModel userModelA = new UserModel("ValentinSt", "valentinstamate@gmail.com", SimpleHashingAlgo.hash("dcy3w8r7ds4lr329"), "~/vali.png");
            UserModel userModelB = new UserModel("Lorenzo", "lorenzo@gmail.com", SimpleHashingAlgo.hash("dasdasd"), "~/lorenzo.png");

            HotelModel hotelModelA = new HotelModel("Unirea", "Iași");
            HotelModel hotelModelB = new HotelModel("Grand Hotel Continental", "București");
            HotelModel hotelModelC = new HotelModel("Apollonia", "Brașov");
            HotelModel hotelModelD = new HotelModel("Hotel Orient", "Brăila");

            userModelA.activateUser();
            userModelB.activateUser();

            userModelA.addHotel(hotelModelA);
            userModelA.addHotel(hotelModelB);

            userModelB.addHotel(hotelModelB);
            userModelB.addHotel(hotelModelC);

            hotelRepository.save(hotelModelA);
            hotelRepository.save(hotelModelB);
            hotelRepository.save(hotelModelC);
            hotelRepository.save(hotelModelD);

            userRepository.save(userModelA);
            userRepository.save(userModelB);

            /* Review */
            HotelReviewModel hotelReviewModelA = new HotelReviewModel(userModelA, hotelModelB, "I like it", 9);
            HotelReviewModel hotelReviewModelB = new HotelReviewModel(userModelB, hotelModelB, "Not great, not terrible", 10);

            hotelReviewRepository.save(hotelReviewModelA);
            hotelReviewRepository.save(hotelReviewModelB);

            userRepository.save(userModelA);
            userRepository.save(userModelB);

            hotelRepository.save(hotelModelB);

        };
    }

}
