import react from 'react';
import { Helmet } from 'react-helmet';
import Hotel from './Hotel';
import Client from './Client';
import './search.css';
import { useLocation, useParams } from 'react-router';


class SearchLocationPage extends react.Component{
    constructor(props){
        super(props);
        this.state={
            client:JSON.parse(localStorage.user).user,
            location:'',
            hotels:[]
        };
        this.client=new Client(this.state.client.username,this.state.client.email,this.state.client.profilePic,this.state.client.auth);
        this.hotels=[];
        var params=new URLSearchParams(window.location.search);
        if(params.get('location')===null){
            return;
        }
        this.state.location=params.get('location');
        this.searchHotels();
    }

    myChangeHandler = (event) => {
        let nam = event.target.name;
        let val = event.target.value;
        this.setState({[nam] : val});
    }

    goToHotel = (hotel) =>{
        localStorage.setItem("hotel",JSON.stringify({hotel:hotel}));
        window.location="/hotelInfo";
    }

    searchHotels= () => {
        var list=this.client.getSearchHotelsByLocation(this.state.location)["hotelList"];
        this.hotels=[];
        for(const [index,item] of list.entries()){
            this.hotels.push(
        <tr>
            <td class="rating">
            <a href="#"><i class="fas fa-circle"></i></a>
            <a href="javascript:void();"><i class="fab fa-gratipay" onClick={() => (this.client.addToFavorites(new Hotel(item.id,item.identifier,item.hotelName,item.locationName,item.averageRating,item.votes,item.hotelUrl)))}></i></a> </td>
            <td class="number text-center">{index+1}</td>
            <td class="image"><img src={require("./images/img1.jpg").default} alt=""/></td>
            <a onClick={() => {this.goToHotel(item);}} style={{textDecoration:'none'}}><td class="product"><strong>{item.hotelName}</strong><br/>Offering a terrace, La Verde is located in Iasi. This 3-star hotel has a bar. WiFi is free.</td></a>
            <td class="rate text-right" style={{width: '100px'}}><span><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span></td>
            <td class="price text-right">$37</td>
        </tr>);
        }
        this.setState({hotels:this.hotels});
    }

    render(){
        


        return(
<div class="search-page">
<div class="topnav">

<a class="active" href="/">Home</a>
<a href="#about">See Statistics</a>
<a href="/profile" class="user"> <i class="fas fa-user"></i></a>
</div>
<div class="container">
<div class="row">
    <div class="col-md-12">
        <div class="grid search">
            <div class="grid-body">
                <div class="row">
                    <div class="col-md-3">
                        <h2 class="grid-title"><i class="fa fa-filter"></i> Filters</h2>
                        <hr/>

                        <h4>Categories:</h4>
                        <div class="checkbox">
                            <label><input type="checkbox" class="icheck" /> Hotel</label>
                        </div>
                        <div class="checkbox">
                            <label><input type="checkbox" class="icheck"/> Motel</label>
                        </div>
                        <div class="checkbox">
                            <label><input type="checkbox" class="icheck"/> Apartament</label>
                        </div>
                        <div class="checkbox">
                            <label><input type="checkbox" class="icheck"/> Pension</label>
                        </div>
                        <div class="checkbox">
                            <label><input type="checkbox" class="icheck"/> House</label>
                        </div>

                        <div class="padding"></div>

                        <h4>Date:</h4>

                        <div class="form-item">
                            <label for="checkin-date">Check In Date: </label>
                            <input type="date" id="chekin-date"/>
                        </div>
                        <div class="form-item">
                            <label for="checkout-date">Check Out Date: </label>
                            <input type="date" id="chekout-date"/>
                        </div>

                        <div class="padding"></div>
                        <h4>Price (per night):</h4>

                        <div class="checkbox">
                            <label><input type="checkbox" class="icheck"/> &lt; 50$ </label>
                        </div>
                        <div class="checkbox">
                            <label><input type="checkbox" class="icheck"/> 50-100$</label>
                        </div>
                        <div class="checkbox">
                            <label><input type="checkbox" class="icheck"/> 101-200$</label>
                        </div>
                        <div class="checkbox">
                            <label><input type="checkbox" class="icheck"/> 201-500$</label>
                        </div>
                        <div class="checkbox">
                            <label><input type="checkbox" class="icheck"/> 501-1000$</label>
                        </div>
                        <div class="checkbox">
                            <label><input type="checkbox" class="icheck"/> &gt;1000$</label>
                        </div>
                    </div>
                    <div class="col-md-9">
                        <h2><i class="fa fa-bed" aria-hidden="true"></i> Result</h2>
                        <hr/>
                        <div class="input-group">
                            <input type="text" class="form-control" name="location" placeholder="search location" onChange={this.myChangeHandler} style={{fontSize:'large'}}/>
                            <span class="input-group-btn">
        <button class="btn btn-primary" type="button" onClick={this.searchHotels} style={{fontSize:'large'}}><i class="fa fa-search"></i></button>
      </span>
                        </div>
                        <p>Showing all results matching your search</p>

                        <div class="padding"></div>

                        <div class="row">
                            <div class="col-sm-6">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
            Order by <span class="caret"></span>
          </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="#">Price</a></li>
                                        <li><a href="#">Name</a></li>
                                        <li><a href="#">Rating</a></li>
                                        <li><a href="#">Statistics</a></li>
                                    </ul>
                                </div>
                            </div>

                    
                        </div>

                        <div class="table-responsive">
                            <table class="table table-hover">
                                <tbody>
                                    {this.hotels}
                                </tbody>
                            </table>
                        </div>
                        <ul class="pagination">
                            <li class="disabled"><a href="#">«</a></li>
                            <li class="active"><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#">4</a></li>
                            <li><a href="#">5</a></li>
                            <li><a href="#">»</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>


<footer class = "footer">
<div class = "footer-container">
    <div>
        <h2>About Us </h2>
        <p>We are a group of students that want to make your travel experience easier. Here you can find the best places to book and useful info about different locations in coulourful charts.</p>
        <ul class = "social-icons">
            <li class = "flex">
                <i class = "fa fa-twitter fa-2x"></i>
            </li>
            <li class = "flex">
                <i class = "fa fa-facebook fa-2x"></i>
            </li>
            <li class = "flex">
                <i class = "fa fa-instagram fa-2x"></i>
            </li>
        </ul>
    </div>

    <div>
        <h2>Useful Links</h2>
        <a href = "#">FAQ</a>
        <a href = "#">About us</a>
        <a href = "#">Join us</a>
    </div>

    <div>
        <h2>Tell us about you</h2>
        <a href = "#">Survey</a>
        <a href = "#">Contact Us</a>
    </div>

    <div>
        <h2>Have A Question</h2>
        <div class = "contact-item">
            <span>
                <i class = "fas fa-map-marker-alt"></i>
            </span>
            <span>
                16, General Henri Mathias Berthelot Street, Iași, România
            </span>
        </div>
        <div class = "contact-item">
            <span>
                <i class = "fas fa-phone-alt"></i>
            </span>
            <span>
                +84545 37534 48
            </span>
        </div>
        <div class = "contact-item">
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

export default SearchLocationPage;