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

    public void createHotelIfNotExists(HotelModel hotelModel) {
        if (!locationExists(hotelModel.getHotelName())) {
            hotelRepository.save(hotelModel);
        }
    }

    private boolean locationExists(String hotelName) {
        return hotelRepository.getHotelByName(hotelName) != null;
    }

    public HotelModel getHotelByName(String hotelName) {
        return hotelRepository.getHotelByName(hotelName);
    }
}
