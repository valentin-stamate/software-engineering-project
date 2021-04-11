package com.bfourclass.euopendata.hotel;

import com.bfourclass.euopendata.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<HotelModel, Long> {
    @Query("SELECT h FROM HotelModel h WHERE h.hotelName = ?1")
    HotelModel getHotelByName(String hotelName);
}
