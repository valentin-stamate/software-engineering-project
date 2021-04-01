import User from './User';
import FavoritesList from './FavoritesList'

class Client extends User{
    constructor(username,password){
        super(username,password);
        this.favorites=new FavoritesList();
    }

    login(){
        //TO DO ...
    }

    signup(){
        //TO DO ...
    }

    makeBooking(){
        //TO DO ...
    }

    addToFavorites(hotel){
        this.favorites.addHotel(hotel);
    }

    removeFromFavorites(hotel){
        this.favorites.removeFromFavorites(hotel);
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
}

export default Client;