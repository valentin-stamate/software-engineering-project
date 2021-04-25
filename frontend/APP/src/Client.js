import User from './User';
import FavoritesList from './FavoritesList'
import Hotel from './Hotel';
import FetchData from './FetchData';

class Client extends User{
    constructor(username,email,profilePic,auth){
        super(username,email,profilePic,auth);
        this.favorites=new FavoritesList();
    }
    makeBooking(){
        //TO DO ...
    }

    getAllFavorites() {
        var data={};
        var favorites = FetchData.makeAuthRequest("https://euopendata.herokuapp.com/user/hotels", "GET", data, this.auth);

        return favorites;
    }


    addToFavorites(hotel){
        var data = [{
            identifier:hotel.identifier,
            hotelName:hotel.hotelName,
            locationName:hotel.locationName
        }];
        FetchData.makeAuthRequest('https://euopendata.herokuapp.com/user/add_hotels', 'POST', data, this.auth);

    }


    removeFromFavorites(hotel){
        var idHotel = hotel.id;
        var data = {
            id_hotel : idHotel
        };
        FetchData.makeAuthRequest('https://euopendata.herokuapp.com/user/delete_hotel', 'DELETE', data, this.auth);

    }

    removeAllFavorites(){
        this.favorites.removeAll();
    }

    showFavorites(){
        return this.favorites.showList();
    }

    cancelReservation(){
        //TO DO ...
    }

    showBookings(){
        //TO DO ...
    }


    writeReview(){
        //TO DO ...
    }

    copyInto(other){
        other=this;
    }
}

export default Client;