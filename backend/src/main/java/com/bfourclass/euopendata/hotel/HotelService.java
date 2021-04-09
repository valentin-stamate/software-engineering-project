package com.bfourclass.euopendata.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    @Autowired
    HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

}
