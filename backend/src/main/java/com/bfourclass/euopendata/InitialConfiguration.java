package com.bfourclass.euopendata;

import com.bfourclass.euopendata.hotel.HotelModel;
import com.bfourclass.euopendata.hotel.HotelRepository;
import com.bfourclass.euopendata.hotel_review.HotelReviewModel;
import com.bfourclass.euopendata.hotel_review.HotelReviewRepository;
import com.bfourclass.euopendata.notification.NotificationModel;
import com.bfourclass.euopendata.notification.NotificationService;
import com.bfourclass.euopendata.security.SimpleHashingAlgo;
import com.bfourclass.euopendata.user.UserModel;
import com.bfourclass.euopendata.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitialConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, HotelRepository hotelRepository, HotelReviewRepository hotelReviewRepository, NotificationService notificationService) {
        return args -> {
            UserModel userModelA = new UserModel("ValentinSt", "valentinstamate@gmail.com", SimpleHashingAlgo.hash("dcy3w8r7ds4lr329"), "https://postimage.com/picture.png");
            UserModel userModelB = new UserModel("Lorenzo", "lorenzo@gmail.com", SimpleHashingAlgo.hash("dasdasd"), "https://postimage.com/picture.png");
            UserModel userModelC = new UserModel("plugin", "plugin@gmail.com", SimpleHashingAlgo.hash("plugintest99"), "https://postimage.com/picture.png");
            UserModel userModelD = new UserModel("web", "web@gmail.com", SimpleHashingAlgo.hash("webtest99"), "https://postimage.com/picture.png");
            UserModel owner = new UserModel("hotelowner", "hotel@owner.com", SimpleHashingAlgo.hash("owner"), "", true);


            HotelModel hotelModelA = new HotelModel("ro/arnia", "Hotel Arnia", "Iași");
            HotelModel hotelModelB = new HotelModel("ro/terra-iasi-valea-lupului", "Hotel Terra Iasi", "Iași");
            HotelModel hotelModelC = new HotelModel("hotel/ro/b-house-rooms", "B House Rooms", "Iași");

            userModelA.activateUser();
            userModelB.activateUser();
            userModelC.activateUser();
            userModelD.activateUser();
            owner.activateUser();

            hotelRepository.save(hotelModelA);
            hotelRepository.save(hotelModelB);
            hotelRepository.save(hotelModelC);

            userModelA.addHotel(hotelModelA);
            userModelA.addHotel(hotelModelB);

            userModelB.addHotel(hotelModelB);
            userModelB.addHotel(hotelModelC);


            userRepository.save(userModelA);
            userRepository.save(userModelB);
            userRepository.save(userModelC);
            userRepository.save(userModelD);
            userRepository.save(owner);

            NotificationModel notificationModel = new NotificationModel("Hello there, notifications are working");
            notificationService.save(notificationModel);
            notificationService.addUserNotification(userModelC, notificationModel);

            /* Review */
            HotelReviewModel hotelReviewModelA = new HotelReviewModel(userModelA, hotelModelB, "I like it", 9);
            HotelReviewModel hotelReviewModelB = new HotelReviewModel(userModelB, hotelModelB, "Not great, not terrible", 10);

            hotelReviewRepository.save(hotelReviewModelA);
            hotelReviewRepository.save(hotelReviewModelB);

            userRepository.save(userModelA);

            UserModel admin = new UserModel(
                    "admin",
                    "admin@gmail.com",
                    SimpleHashingAlgo.hash("adminis"),
                    "",
                    true,
                    true);
            userRepository.save(admin);

            userRepository.save(userModelB);

            hotelRepository.save(hotelModelB);

        };
    }

}
