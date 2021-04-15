import User from './User';
import FavoritesList from './FavoritesList'

class Client extends User{
    constructor(username,email,profilePic,auth){
        super(username,email,profilePic,auth);
        this.favorites=new FavoritesList();
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

    copyInto(other){
        other=this;
    }
}

export default Client;