class FavoritesList{
    constructor(){
        this.hotels=[];
    }

    addHotel(hotel){
        this.hotels.push(hotel);
    }

    removeHotel(hotel){
        var index=this.hotels.indexOf(hotel);
        if(index==-1){
            return;
        }
        this.hotels.splice(index,1);
    }

    removeAll(){
        this.hotels=[];
    }

    showList(){
        return this.hotels;
    }

}

export default FavoritesList;