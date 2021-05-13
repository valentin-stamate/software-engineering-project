import User from './User';
import FavoritesList from './FavoritesList'
import Hotel from './Hotel';
import FetchData from './FetchData';

class Client extends User{
    constructor(username,email,profilePic,auth){
        super(username,email,profilePic,auth);
        this.favorites=new FavoritesList();
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
        var data=FetchData.makeAuthRequest('https://euopendata.herokuapp.com/user/add_hotels', 'POST', data, this.auth);
        alert(data.error);
    }

    getSearchHotelsByLocation(locationName) {
        var data = {
            query : locationName
        };
        var hotels = FetchData.makeAuthRequest('https://euopendata.herokuapp.com/search_hotel', 'GET', data, this.auth);
        return hotels;
    }


    removeFromFavorites(hotel){
        var idHotel = hotel.id;
        var data = {
            hotel_id : idHotel
        };
        var data=FetchData.makeAuthRequest('https://euopendata.herokuapp.com/user/delete_hotel', 'DELETE', data, this.auth);
        alert(data.message);
    }

    review_add(hotel,text,number){
        var data = {
            hotelId:hotel.id,
            message: text,
            rating: number
        };
        var result=FetchData.makeAuthRequest('https://euopendata.herokuapp.com/hotel/add_review', 'POST', data, this.auth);
    }
    review_delete(idReview){

        var data = {
            review_id : idReview
        };
        FetchData.makeAuthRequest('https://euopendata.herokuapp.com/hotel/delete_review', 'DELETE', data, this.auth);

    }
    review_update(idReview,text,number,date){
        var data = {
            id:idReview,
            message: text,
            rating: number,
            dateAdded:date
        };
        FetchData.makeAuthRequest('https://euopendata.herokuapp.com/hotel/update_review', 'POST', data, this.auth);

    }

    get_reviews(id){
        var data={
            hotel_id:id
        };
        var result=FetchData.makeAuthRequest('https://euopendata.herokuapp.com/hotel/reviews', 'GET', data, this.auth);
        return result;
    
    }
    
}

export default Client;