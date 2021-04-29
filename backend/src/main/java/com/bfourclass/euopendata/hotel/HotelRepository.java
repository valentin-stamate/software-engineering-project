package com.bfourclass.euopendata.hotel;

import com.bfourclass.euopendata.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<HotelModel, Long> {
    @Query("SELECT h FROM HotelModel h WHERE h.hotelName = ?1")
    HotelModel getHotelByName(String hotelName);

    @Query("SELECT h FROM HotelModel h")
    List<HotelModel> getAll();

    @Query("SELECT h FROM HotelModel h WHERE h.identifier = ?1")
    HotelModel findByIdentifier(String identifier);

    @Query("SELECT h FROM HotelModel h WHERE h.ownerId = ?1")
    List<HotelModel> findByOwnerId(Long ownerId);
}
