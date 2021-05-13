class Hotel{
    // constructor(id,identifier,hotelName,locationName,averageRating,votes,hotelUrl){
    //     this.id=id;
    //     this.identifier=identifier;
    //     this.hotelName=hotelName;
    //     this.locationName=locationName;
    //     this.averageRating=averageRating;
    //     this.votes=votes;
    //     this.hotelUrl=hotelUrl;
    // }

    constructor(id,identifier,hotelName,locationName,averageRating,votes,hotelUrl, 
            photoLink, description, price){
        this.id=id;
        this.identifier=identifier;
        this.hotelName=hotelName;
        this.locationName=locationName;
        this.averageRating=averageRating;
        this.votes=votes;
        this.hotelUrl=hotelUrl;

        this.photoLink = photoLink;
        this.description = description;
        this.price = price;
    }

    
}

export default Hotel; 