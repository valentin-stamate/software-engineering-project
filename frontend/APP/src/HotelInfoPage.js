import react from 'react';
import './login_style.css';
import Credentials from './Credentials';
import ClientLogin from './ClientLogin';
import Client from './Client';
import Hotel from './Hotel';
import FetchHotelInfo from './FetchHotelInfo';
import './hotelInfo.css';

class HotelInfoPage extends react.Component{
    constructor(props){
        super(props);
        this.state={
            client:JSON.parse(localStorage.user).user,
            reviewMessage:'',
            reviewRating:10,
            refreshReviews:true
        };
        var item=JSON.parse(localStorage.hotel).hotel;
        this.client=new Client(this.state.client.username,this.state.client.email,this.state.client.profilePic,this.state.client.auth);
        this.hotel=new Hotel(item.id,item.identifier,item.hotelName,item.locationName,item.averageRating,item.votes,item.hotelUrl,item.photoLink,item.description,item.price);
        this.items=[];

        this.hotelInfo=FetchHotelInfo.fetchInfo(this.hotel.hotelUrl+".html");

        this.images=[];
        this.indicators=[];

        this.images.push(
            <div class="item active">
                    <img style={{width:"400px",height:"300px"}} src={this.hotelInfo.img[0]} class="img-responsive" alt="error" />
                </div>
        );
        this.indicators.push(
            <li data-target="#myCarousel-2" data-slide-to="0" class="active"></li>
        );

        for(const [index,url] of this.hotelInfo.img.entries()){
            if(index==0){
                continue;
            }
            this.indicators.push(
                <li data-target="#myCarousel-2" data-slide-to={index} class=""></li>
            );
            this.images.push(
                <div class="item">
                    <img style={{width:"400px",height:"300px"}} src={url} class="img-responsive" alt="error" />
                </div>
            );
        }
    }

    myChangeHandler = (event) => {
        if(!event.target.validity.valid){
            return;
        }
        let nam = event.target.name;
        let val = event.target.value;
        this.setState({[nam] : val});
    }

    addReview = () => {
        this.client.review_add(this.hotel,this.state.reviewMessage,this.state.reviewRating);
        this.setState({reviewMessage:'',refreshReviews:true});
    }

    deleteReview = (id) => {
        this.client.review_delete(id);
        this.setState({refreshReviews:true});
    }

    render(){
        if(this.state.refreshReviews){
            this.items=[];
            this.state.refreshReviews=false;
            var reviews=this.client.get_reviews(this.hotel.identifier);

            for(const [index,item] of reviews.entries()){
            this.items.push(
                <li class="message" style={{fontSize:'large'}}>
                    <img src={require("./images/pers0.jpg").default} class="online" />
                    <span class="message-text">
                    <a href="javascript:void(0);" class="username">
                        {item.userName}
                        <span style={{border:'1px solid red',color:'red'}} class="pull-right" onClick={() => {this.deleteReview(item.id);}}>
                            X
                        </span>
                        <span class="pull-right">
                            Rating : {item.userRating} / 10
                        </span>
                        <small class="text-muted pull-right ultra-light"> Posted on : {item.reviewDate} </small>
                    </a>
                    <h5> {item.reviewMessage}</h5>
                    </span>
                </li>
                );
            }
        }

        


        return(

<div class="hotel-info">
<div class="topnav">
        <a class="active" href="#home">Home</a>
        <a href="#">See Favorites </a>
        <a href="#">Sign Out</a>
        <input type="text" placeholder="Search location"/>
    </div>
<div class="col-sm-12 col-md-12 col-lg-12">
    <div class="product-content product-wrap clearfix product-deatil">
        <div class="row">
            <div class="col-md-5 col-sm-12 col-xs-12">
                <div class="product-image">
                    <div id="myCarousel-2" class="carousel slide">
                        <ol class="carousel-indicators">
                            {this.indicators}
                        </ol>
                        <div class="carousel-inner">
                            {this.images}
                        </div>
                        <a class="left carousel-control" href="#myCarousel-2" data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"></span> </a>
                        <a class="right carousel-control" href="#myCarousel-2" data-slide="next"> <span class="glyphicon glyphicon-chevron-right"></span> </a>
                    </div>
                </div>
                
            </div>
            
                    <div class="btn-group pull-right">
                        <button class="btn btn-white btn-default" onClick={() => {this.client.addToFavorites(this.hotel);}}> <i class="far fa-heart"></i>Add to favorites</button>
                        
                    </div>
            <div class="content col-md-6 col-md-offset-1 col-sm-12 col-xs-12">
                
                <h2 class="name">
                    <a href={this.hotel.hotelUrl}>
                    {this.hotel.hotelName}
                    <small><h5><i class="fas fa-map-marker-alt" ></i> {this.hotel.locationName} </h5></small>
                    <i class="fas fa-star fa-2x "></i>
                    <i class="fas fa-star fa-2x "></i>
                    <i class="fas fa-star fa-2x "></i>
                    <i class="fas fa-star fa-2x "></i>
                    <i class="fas fa-star fa-2x "></i>
                    <span class="fa fa-2x"><h5>{this.hotel.votes} Votes</h5></span>
                    <span>Average rating : {this.hotel.averageRating}</span>
                    </a>
                </h2>
                
                <hr />
                <span class="badge">Most Popular Facilities:</span>
                <div class="certified">
                    <ul>
                        <li>
                            <i class="fas fa-utensils"><span>{this.hotelInfo.facilities}</span></i>
                        </li>
                        <li>
                            <i class="fas fa-swimming-pool"><span> Swimming Pool</span></i>
                        </li>
                        <li>
                            <i class="fas fa-dumbbell"><span>Fitness Center</span></i>
                        </li>
                        <li>
                            <i class="fas fa-paw"><span>Pet friendly</span></i>
                        </li>
                        
                        
                    </ul>
                    
                </div>
                <div class="row">
                    <div class="col-sm-12 col-md-6 col-lg-6">
                        <a href="javascript:void(0);" class="btn btn-primary btn-lg">Book Now</a>
                    </div>
                    </div>
                
                <hr />
                <div class="description description-tabs">
                    <ul id="myTab" class="nav nav-pills">
                        <li class="active"><a href="#more-information" data-toggle="tab" class="no-margin"> Description </a></li>
                        <li class=""><a href="#weather" data-toggle="tab">Weather</a></li>
                        <li class=""><a href="#specifications" data-toggle="tab">Covid-19</a></li>
                        <li class=""><a href="#reviews" data-toggle="tab">Reviews</a></li>
                    </ul>
                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane fade active in" id="more-information">
                            <br />
                            <strong>Hotel Description</strong>
                            {this.hotelInfo.desc}
                        </div>
                        <div class="tab-pane fade" id="specifications">
                            <br />
                            <dl class="" >
                                <dt>Curent Covid Restrictions</dt>
                                <ul type="square">
                                <li>Personal care premises like hairdressers and nail salons are open.</li>
                                <li>Public buildings like libraries and community centres are open.</li>
                                <li>Outdoor hospitality can reopen with table service only. This means you can have a drink or a meal in a beer garden or outdoor space. If you want to meet with those you don’t live with then it must be as two households or in a
                    group no bigger than six.</li>
                                <li>Many outdoor attractions and events like zoos, fetes, fairgrounds, and theme parks can reopen.</li>
                                <li>Indoor leisure and sports facilities can reopen. But you can only attend them on your own, with the people you live with, or those who are in your support bubble.</li>
                                <li>Weddings, civil partnership ceremonies and wakes can take place with a limit of 15 people. Wedding receptions can also take place with the same limit, but they must be outdoors (not including private gardens).</li>
                                <li>Childcare and supervised activities will be allowed indoors (as well as outdoors) for all children. Parent and child groups can take place both indoors and outdoors for up to 15 people.</li>
                                </ul>
                                <br />
                            </dl>
                        </div>
                        <div class="tab-pane fade" id="weather">
                            <br />
                            <dl class="">
                                <dt>Curent Weather</dt>
                                <dd>Detailed Forecast of the location you're booking.</dd>
                                <div class="card-box">
                                  <div class="table-responsive">
                                    <table class="table">
                                      <thead>
                                        <tr>
              
                                          <th>Day</th>
                                          <th>Temperature</th>
                                          <th>Humidity</th>
                                          <th>Pressure</th>
                                          <th>Vissibility</th>
                                          <th>Wind</th>
              
                                        </tr>
                                      </thead>
                                      <tbody>
                                        <tr>
                                          <td>Monday</td>
                                          <td>16°</td>
                                          <td>53%</td>
                                          <td>1032.8 mb</td>
                                          <td>9.66 km</td>
                                          <td>13 km/h</td>
                                        </tr>
                                        <tr>
                                          <td>Tuesday</td>
                                          <td>24°</td>
                                          <td>30%</td>
                                          <td>800.9 mb</td>
                                          <td>10.43 km</td>
                                          <td>7 km/h</td>
                                        </tr>
                                        <tr>
                                          <td>Wednesday</td>
                                          <td>23°</td>
                                          <td>35%</td>
                                          <td>912.7 mb</td>
                                          <td>10.33 km</td>
                                          <td>7.5 km/h</td>
                                        </tr>
                                        <tr>
                                          <td>Thursday</td>
                                          <td>19°</td>
                                          <td>42.5%</td>
                                          <td>955.6 mb</td>
                                          <td>9.03 km</td>
                                          <td>8.2 km/h</td>
                                        </tr>
                                        <tr>
                                          <td>Friday</td>
                                          <td>19°</td>
                                          <td>40.3%</td>
                                          <td>927.7 mb</td>
                                          <td>7.13 km</td>
                                          <td>5.2 km/h</td>
                                        </tr>
                                        <tr>
                                          <td>Saturday</td>
                                          <td>20°</td>
                                          <td>41.5%</td>
                                          <td>917.8 mb</td>
                                          <td>10.23 km</td>
                                          <td>6.4 km/h</td>
                                        </tr>
                                        <tr>
                                          <td>Sunday</td>
                                          <td>22°</td>
                                          <td>42.6%</td>
                                          <td>959.9 mb</td>
                                          <td>8.23 km</td>
                                          <td>6.1 km/h</td>
                                        </tr>
                                      </tbody>
                                    </table>
                                  </div>
                                </div>
                                <br/>
                            </dl>
                        </div>

                        

                        <div class="tab-pane fade" id="reviews">
                            <br />
                            <div class="well padding-bottom-10" >
                                <textarea style={{fontSize:'large'}} rows="2" class="form-control" value={this.state.reviewMessage} onChange={this.myChangeHandler} name="reviewMessage" placeholder="Write a review"></textarea>
                                <span>Rating (0-10):</span><input type="text" onChange={this.myChangeHandler} pattern="[0-9]|10" name="reviewRating" value={this.state.reviewRating}/>
                                <div class="margin-top-10">
                                    <button type="submit" style={{fontSize:'large'}} class="btn btn-sm btn-primary pull-right" onClick={this.addReview}>
                                        Submit Review
                                    </button>
                                </div>
                            </div>

                            <div class="chat-body no-padding profile-message">
                                <ul>
                                    {this.items}
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                
                    
                </div>
            </div>
        </div>
    </div>
</div>
        );
    }
}

export default HotelInfoPage;