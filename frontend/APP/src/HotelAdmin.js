import User from './User'
import Hotel from './Hotel'

class HotelAdmin extends User{
    constructor(username,password,profilePic,auth){
        super(username,password,profilePic,auth);
        hotels=[];
    }

    login(){

    }

    signup(){

    }

    showReviews(){

    }

    addHotel(hotel){
        this.hotels.push(hotel);
    }

    removeHotel(hotel){
        var index=this.hotels.indexOf(hotel);
        if(index==-1){
            throw new Error("Hotel does not belong to this hotel admin");
        }
        this.hotels.split(index,1);
    }

    updateInfo(){

    }

    getInfo(){
        return this.hotels;
    }
}

export default HotelAdmin;