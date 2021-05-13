import react from 'react';
import { Helmet } from 'react-helmet';
import Client from './Client';
import './favorites.css';

class FavoritesPage extends react.Component{
    constructor(props){
        super(props);
        this.state={
            client:JSON.parse(localStorage.user).user,
            items:[],
            hotels:[],
            requestMade:false
        };
        this.client=new Client(this.state.client.username,this.state.client.email,this.state.client.profilePic,this.state.client.auth);
    }

    removeHotel = (id) => {
        var index=0;
        for(var i=0;i<this.state.hotels.length;++i){
            if(this.state.hotels[i].id==id){
                index=i;
                break;
            }
        }
        var hotel=this.state.hotels[index];
        this.client.removeFromFavorites(hotel);
        var hotels=this.state.hotels,items=this.state.items;
        this.splice(hotels,index);
        this.splice(items,index);
        this.setState({items:items,hotels:hotels});
    }

    splice = (arr,index) =>{
        var newArr=arr;
        for(var i=index+1;i<newArr.length;++i){
            newArr[i-1]=newArr[i];
        }
        newArr.pop();

        return newArr;
    }

    render(){
        if(!this.state.requestMade){
            this.state.hotels=this.client.getAllFavorites();

            this.state.items=[];
            for(const [index,hotel] of this.state.hotels.entries()){
                this.state.items.push(
        <div id="element" class="col-md-4 ftco-animate fadeInUp ftco-animated">
            <div class="block-7">
                <img class="img" src={require('./images/hotel1.jpg').default}></img>
                <div class="text-center p-4">
                    <span class="excerpt d-block"><a href={hotel.hotelUrl} target="_blank">{hotel.hotelName}</a></span>
                    <span class="price"><sup>$</sup> <span class="number">37</span> <sub>/night</sub></span>
                    <ul class="pricing-text mb-5">
                        <li><span><i class="fa fa-star" style={{color :'orange'}}></i><i class="fa fa-star" style={{color: 'orange'}}></i><i class="fa fa-star" style={{color: 'orange'}}></i></span></li>
                        <li><span><i class="fa fa-map-marker" aria-hidden="true">{hotel.locationName}</i></span></li>
                        <li><span class="fa fa-check mr-2"></span>Average rating : {hotel.averageRating}</li>
                        <li><span class="fa fa-check mr-2"></span>Votes : {hotel.votes}</li>
                    </ul>
                    <a href={hotel.hotelUrl} class="btn btn-primary d-block px-2 py-3">See More</a> <button href="#" onClick={() => {this.removeHotel(hotel.id);}} class="btn btn-outline-primary px-2 py-3" style={{marginTop: '5px'}}>Remove From Favorites</button>
                </div>
            </div>
        </div>)
        }
        this.state.requestMade=true;
    }


        return(
<div class="fav-page">
<div class="topnav">
        <a class="active" href="#home">Home</a>
        <a href="#">See Recommendation</a>
        <a href="#">My Profile</a>
        <input type="text" placeholder="Search location"/>
    </div>
    <section class="ftco-section bg-light">
        <div class="container">
            <div class="row justify-content-center pb-5 mb-3">
                <div class="col-md-7 heading-section text-center ftco-animate fadeInUp ftco-animated">
                    <h2>Favorite Locations List</h2>
                </div>
            </div>
            <div class="row">
                {this.state.items}
            </div>
        </div>
    </section>
    <footer class="footer">
        <div class="footer-container">
            <div>
                <h2>About Us </h2>
                <p>We are a group of students that want to make your travel experience easier. Here you can find the best places to book and useful info about different locations in coulourful charts.</p>
                <ul class="social-icons">
                    <li class="flex">
                        <i class="fa fa-twitter fa-2x"></i>
                    </li>
                    <li class="flex">
                        <i class="fa fa-facebook fa-2x"></i>
                    </li>
                    <li class="flex">
                        <i class="fa fa-instagram fa-2x"></i>
                    </li>
                </ul>
            </div>

            <div>
                <h2>Useful Links</h2>
                <a href="#">FAQ</a>
                <a href="#">About us</a>
                <a href="#">Join us</a>
            </div>

            <div>
                <h2>Tell us about you</h2>
                <a href="#">Survey</a>
                <a href="#">Contact Us</a>
            </div>

            <div>
                <h2>Have A Question</h2>
                <div class="contact-item">
                    <span>
                        <i class = "fas fa-map-marker-alt"></i>
                    </span>
                    <span>
                        16, General Henri Mathias Berthelot Street, Iași, România
                    </span>
                </div>
                <div class="contact-item">
                    <span>
                        <i class = "fas fa-phone-alt"></i>
                    </span>
                    <span>
                        +84545 37534 48
                    </span>
                </div>
                <div class="contact-item">
                    <span>
                        <i class = "fas fa-envelope"></i>
                    </span>
                    <span>
                        info@domain.com
                    </span>
                </div>
            </div>
        </div>
    </footer>
</div>
        );
    }
}

export default FavoritesPage;