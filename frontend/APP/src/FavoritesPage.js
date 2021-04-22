import react from 'react';
import { Helmet } from 'react-helmet';
import './favorites.css';

class FavoritesPage extends react.Component{
    constructor(props){
        super(props);
        this.state={
            client:JSON.parse(localStorage.user).user
        };
    }

    render(){
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
                <div id="element" class="col-md-4 ftco-animate fadeInUp ftco-animated">
                    <div class="block-7">
                        <img class="img" src={require('./images/hotel1.jpg').default}></img>
                        <div class="text-center p-4">
                            <span class="excerpt d-block"><a href="https://www.booking.com/hotel/ro/vila-verde-iasi.ro.html?aid=376393;label=bookings-name-d2AakcaZh5VCmomr9TOSVAS438094247978%3Apl%3Ata%3Ap1%3Ap22.563.000%3Aac%3Aap%3Aneg%3Afi%3Atikwd-65526620%3Alp1011828%3Ali%3Adec%3Adm%3Appccp%3DUmFuZG9tSVYkc2RlIyh9YcpDr58xwogAJgpBCuFL5yA;sid=0d04848ae3c10f09d38b63d4344911bf;all_sr_blocks=228680615_213000770_2_0_0;checkin=2021-05-11;checkout=2021-05-12;dest_id=-1161664;dest_type=city;dist=0;group_adults=2;group_children=0;hapos=1;highlighted_blocks=228680615_213000770_2_0_0;hpos=1;no_rooms=1;room1=A%2CA;sb_price_type=total;sr_order=popularity;sr_pri_blocks=228680615_213000770_2_0_0__14899;srepoch=1619089586;srpvid=f4624e1926b6016f;type=total;ucfs=1&#hotelTmpl" target="_blank">Hotel La Verde</a></span>
                            <span class="price"><sup>$</sup> <span class="number">37</span> <sub>/night</sub></span>
                            <ul class="pricing-text mb-5">
                                <li><span><i class="fa fa-star" style={{color :'orange'}}></i><i class="fa fa-star" style={{color: 'orange'}}></i><i class="fa fa-star" style={{color: 'orange'}}></i></span></li>
                                <li><span><i class="fa fa-map-marker" aria-hidden="true">str. Ciric Nr 12, 700334 Iaşi, România</i></span></li>
                                <li><span class="fa fa-check mr-2"></span>Free WiFi and parking</li>
                                <li><span class="fa fa-check mr-2"></span>Terrace & Bar</li>
                                <li><span class="fa fa-check mr-2"></span>Includes breakfast if wanted</li>
                            </ul>
                            <a href="#" class="btn btn-primary d-block px-2 py-3">See More</a> <a href="#" class="btn btn-outline-primary px-2 py-3" style={{marginTop: '5px'}}>Remove From Favorites</a>
                        </div>
                    </div>
                </div>

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