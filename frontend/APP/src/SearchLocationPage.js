import react from 'react';
import { Helmet } from 'react-helmet';
import './search.css';

class SearchLocationPage extends react.Component{
    constructor(props){
        super(props);
        this.state={
            client:JSON.parse(localStorage.user).user
        };
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
                            <input type="text" class="form-control" value="search location"/>
                            <span class="input-group-btn">
        <button class="btn btn-primary" type="button"><i class="fa fa-search"></i></button>
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
                                    <tr>
                                        <td class="rating">
                                            <a href="#"><i class="fas fa-circle"></i></a>
                                            <a href="javascript:void();"><i class="fab fa-gratipay" onclick="liked(0)"></i></a> </td>
                                        <td class="number text-center">1</td>
                                        <td class="image"><img src={require("./images/img1.jpg").default} alt=""/></td>
                                        <a href="/hotelInfo" style={{textDecoration:'none'}}><td class="product"><strong>La Verde</strong><br/>Offering a terrace, La Verde is located in Iasi. This 3-star hotel has a bar. WiFi is free.</td></a>
                                        <td class="rate text-right" style={{width: '100px'}}><span><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span></td>
                                        <td class="price text-right">$37</td>

                                    </tr>
                                    <tr>
                                        <td class="rating">
                                            <a href="#"><i class="fas fa-circle"></i></a>
                                            <a href="javascript:void();"><i class="fab fa-gratipay" onclick="liked(1)"></i></a> </td>
                                        <td class="number text-center">2</td>
                                        <td class="image"><img src={require("./images/img2.jpg").default} alt=""/></td>
                                        <td class="product"><strong>Unirea Hotel & Spa</strong><br/>Unirea Hotel & Spa is located in Unirii Square, in the center of Iași, a few steps from Alexandru Ioan Cuza University. It offers free access to an indoor pool, hot
                                            tub and fitness center.</td>
                                        <td class="rate text-right"><span><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span></td>
                                        <td class="price text-right">$84</td>
                                    </tr>
                                    <tr>
                                        <td class="rating">
                                            <a href="#"><i class="fas fa-circle"></i></a>
                                            <a href="javascript:void();"><i class="fab fa-gratipay" onclick="liked(2)"></i></a> </td>
                                        <td class="number text-center">3</td>
                                        <td class="image"><img src={require("./images/img3.jpg").default} alt=""/></td>
                                        <td class="product"><strong>Grand Hotel Traian</strong><br/>Built in 1882 by Gustave Eiffel, this historic luxury hotel enjoys an exceptional location in Unirii Square in Iasi, a 10-minute walk from the National Theater, the
                                            Metropolitan Church and the city’s main attractions.</td>
                                        <td class="rate text-right"><span><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span></td>
                                        <td class="price text-right">$75</td>
                                    </tr>
                                    <tr>
                                        <td class="rating">
                                            <a href="#"><i class="fas fa-circle"></i></a>
                                            <a href="javascript:void();"><i class="fab fa-gratipay" onclick="liked(3)"></i></a> </td>
                                        <td class="number text-center">4</td>
                                        <td class="image"><img src={require("./images/img5.jpg").default} alt=""/></td>
                                        <td class="product"><strong>Bucium Motel& SPA</strong><br/>Bucium Motel is a 15-minute drive from Iași city center and offers an à la carte restaurant with a terrace, as well as a sauna and an indoor pool. Free private parking
                                            and free WiFi are available throughout the building.</td>
                                        <td class="rate text-right"><span><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span></td>
                                        <td class="price text-right">$51</td>
                                    </tr>
                                    <tr>
                                        <td class="rating">
                                            <a href="#"><i class="fas fa-circle"></i></a>
                                            <a href="javascript:void();"><i class="fab fa-gratipay" onclick="liked(4)"></i></a> </td>
                                        <td class="number text-center">5</td>
                                        <td class="image"><img src="./images/img4.jpg" alt=""/></td>
                                        <td class="product"><strong>Ramada Iasi City Center</strong><br/>Ramada City Center Iasi offers a quiet location in the heart of the city, just opposite the Palas Complex shopping center. It is just 15 minutes from the airport
                                            and 7 minutes from the main train station.</td>
                                        <td class="rate text-right"><span><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span></td>
                                        <td class="price text-right">$94</td>
                                    </tr>
                                    <tr>
                                        <td class="rating">
                                            <a href="#"><i class="fas fa-circle"></i></a>
                                            <a href="javascript:void();"><i class="fab fa-gratipay" onclick="liked(5)"></i></a> </td>
                                        <td class="number text-center">6</td>
                                        <td class="image"><img src={require("./images/263872329.webp").default} alt=""/></td>
                                        <td class="product"><strong>Hotel Astoria City Center</strong><br/>Centrally-located in Iasi, within a 10-minute walk from the National Theatre and the most important landmarks of the city, Hotel Astoria features free WiFi access
                                            and a restaurant serving traditional Romanian and international cuisine.</td>
                                        <td class="rate text-right"><span><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span></td>
                                        <td class="price text-right">$64</td>
                                    </tr>
                                    <tr>
                                        <td class="rating">
                                            <a href="#"><i class="fas fa-circle"></i></a>
                                            <a href="javascript:void();"><i class="fab fa-gratipay" onclick="liked(6)"></i></a> </td>
                                        <td class="number text-center">7</td>
                                        <td class="image"><img src={require("./images/270120850.webp").default} alt=""/></td>
                                        <td class="product"><strong>GRAND VIEW Hotel & Suites Copou</strong><br/>Centrally-located in Iasi, within a 10-minute walk from the National Theatre and the most important landmarks of the city, Hotel Astoria features free
                                            WiFi access and a restaurant serving traditional Romanian and international cuisine.</td>
                                        <td class="rate text-right"><span><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span></td>
                                        <td class="price text-right">$149</td>
                                    </tr>
                                    <tr>
                                        <td class="rating">
                                            <a href="#"><i class="fas fa-circle"></i></a>
                                            <a href="javascript:void();"><i class="fab fa-gratipay" onclick="liked(7)"></i></a> </td>
                                        <td class="number text-center">8</td>
                                        <td class="image"><img src={require("./images/270100151.webp").default} alt=""/></td>
                                        <td class="product"><strong>Hotel Select</strong><br/>Centrally-located in Iasi, within a 10-minute walk from the National Theatre and the most important landmarks of the city, Hotel Astoria features free WiFi access and a restaurant
                                            serving traditional Romanian and international cuisine.</td>
                                        <td class="rate text-right"><span><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span></td>
                                        <td class="price text-right">$120</td>
                                    </tr>
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