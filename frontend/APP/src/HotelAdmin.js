import User from './User'
import Hotel from './Hotel'
import FetchData from './FetchData';

class HotelAdmin extends User{
    constructor(username,email,profilePic,auth){
        super(username,email,profilePic,auth);
    }


    addHotel(hotel) {
        var data = {
            name: hotel.hotelName,
            location: hotel.locationName,
            identifier: hotel.hotelUrl, 
            photoLink: hotel.photoLink,
            description: hotel.description, 
            price: hotel.price
        };

        alert(JSON.stringify(data));

        var response = FetchData.makeAuthRequest("https://euopendata.herokuapp.com/hotel/add_hotel", "POST", data, this.auth);
        return response; // return response.message;
    }

    getAllHotels() {
        var data = {};

        var response = FetchData.makeAuthRequest("https://euopendata.herokuapp.com/owner/hotels", "GET", data, this.auth);
        return response;
    }

    removeHotel(hotel){
        var index=this.hotels.indexOf(hotel);
        if(index==-1){
            throw new Error("Hotel does not belong to this hotel admin");
        }
        this.hotels.split(index,1);
    }

}

export default HotelAdmin;