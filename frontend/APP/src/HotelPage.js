import './hotel.css';
import React from 'react';
import { Helmet } from 'react-helmet';

class HotelPage extends React.Component{
    constructor(props){
        super(props);
    }


    render(){
        return(
<div class="hotel-page">
<div class="content">
    <div class="container">
      <div class="row">
          <div class="col-sm-12">
              <div class="profile-user-box card-box bg-custom">
                  <div class="row">
                      <div class="col-sm-6"><span class="float-left mr-3"><img src={require("./images/34712772.jpg").default} alt="hotel profile image" class="thumb-lg rounded-circle"/></span>
                          <div class="media-body" style={{marginTop:'2%'}}>
                              <h2 class="mt-1 mb-1">Hotel Corque</h2>
                              <p class="mb-0" style={{fontSize: '90%'}}>Solvang, United States</p>

                              <div class="bui-review-score c-score bui-review-score--end">
                                  <div class="bui-review-score__badge" aria-label="Scored 9.3 " style={{display: 'inline-block', paddingLeft:'1%'}}> 9.3 </div>
                                  <p style={{display: 'inline-block' ,fontSize:'100%'}}>Superb, 1447 reviews</p>
                              </div>


                          </div>
                      </div>
                      <div class="col-sm-6" style={{marginTop:'3%'}}>
                          <div class="text-right">
                              <button type="button" class="btn btn-light waves-effect"><i class="mdi mdi-account-settings-variant mr-1"></i> Edit Profile</button>
                          </div>
                      </div>
                  </div>
              </div>
          </div>
      </div>
        <div class="row">
            <div class="col-xl-4">
                <div class="card-box">
                    <h4 class="header-title mt-0">Hotel Information</h4>
                    <div class="panel-body">
                        <p class="text-muted font-13">Located 5 minutes’ walk away from Old Mission Santa Ines, this boutique hotel offers a restaurant on site. It features an outdoor pool and hot tub and spacious room with free WiFi.</p>
                        <p class="text-muted font-13">A flat-screen cable satellite TV with pay-per-view channels is offered in each guest room at Hotel Corque. All guest rooms include an iPod docking station, a refrigerator, a coffee machine and an en suite bathroom with free
                            toiletries. Select rooms include views of Solvang Village or the Santa Ynez Hills.</p>
                        <p class="text-muted font-13">A business centre with fax and photocopying services is also available on site. Chumash Casino is 6 minutes’ drive away from this hotel. Santa Barbara Wine Country is 6.4 km away. Solvang town centre is 1 minutes’ drive away.
                            Couples particularly like the location — they rated it 9.7 for a two-person trip. We speak your language!</p>
                        <hr/>
                        <div class="text-left">
                            <p class="text-muted font-13"><strong>Mobile :</strong><span class="m-l-15">(+12) 123 1234 567</span></p>
                            <p class="text-muted font-13"><strong>Email :</strong> <span class="m-l-15">coderthemes@gmail.com</span></p>
                            <p class="text-muted font-13"><strong>Adress :</strong> <span class="m-l-15">400 Alisal Road, Solvang, CA 93464, United States</span></p>
                            <p class="text-muted font-13"><strong>Nerby Locations :</strong>
                                <span>Shoestring Winery</span>
                                <span>Solvang Bakery</span>
                                <span>Mosby Winery</span>
                                <span>Lincourt Vineyards</span>
                                <span><a href="#" style={{textDecoration: 'none'}}>More</a></span>
                            </p>
                            <p class="text-muted font-13"><strong>Top attractions :</strong>
                                <span>Roblar Winery</span>
                                <span>Vincent Winery</span>
                                <span>Carina Cellars</span>
                                <span>Neverland</span>
                                <span><a href="#" style={{textDecoration: 'none'}}>More</a></span>
                            </p>
                        </div>
                        <ul class="social-links list-inline mt-4 mb-0">
                            <li class="list-inline-item"><a title="" data-placement="top" data-toggle="tooltip" class="tooltips" href="" data-original-title="Facebook"><i class="fa fa-facebook"></i></a></li>
                            <li class="list-inline-item"><a title="" data-placement="top" data-toggle="tooltip" class="tooltips" href="" data-original-title="Twitter"><i class="fa fa-twitter"></i></a></li>
                            <li class="list-inline-item"><a title="" data-placement="top" data-toggle="tooltip" class="tooltips" href="" data-original-title="Skype"><i class="fa fa-skype"></i></a></li>
                        </ul>
                    </div>
                </div>
                <div class="card-box ribbon-box">
                    <div class="ribbon ribbon-primary">Reviews</div>
                    <div class="clearfix"></div>
                    <div class="inbox-widget">
                        <a href="#">
                            <div class="inbox-item">
                                <div class="inbox-item-img"><img src={require("./images/pers0.jpg").default} class="rounded-circle" alt=""/></div>
                                <p class="inbox-item-author">Aleksei</p>
                                <p class="inbox-item-text">We had a wonderful stay here.</p>
                                <p class="inbox-item-date">
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                </p>
                            </div>
                        </a>
                        <a href="#">
                            <div class="inbox-item">
                                <div class="inbox-item-img"><img src={require("./images/pers7.jpg").default} class="rounded-circle" alt=""/></div>
                                <p class="inbox-item-author">Jessica</p>
                                <p class="inbox-item-text"> Can’t wait to return!</p>
                                <p class="inbox-item-date">
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star"></span>
                                </p>
                            </div>
                        </a>
                        <a href="#">
                            <div class="inbox-item">
                                <div class="inbox-item-img"><img src={require("./images/pers8.jpg").default} class="rounded-circle" alt=""/></div>
                                <p class="inbox-item-author">Ana-Maria</p>
                                <p class="inbox-item-text">The hotel stay was very pleasant...</p>
                                <p class="inbox-item-date">
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                </p>
                            </div>
                        </a>
                        <a href="#">
                            <div class="inbox-item">
                                <div class="inbox-item-img"><img src={require("./images/pers10.jpeg").default} class="rounded-circle" alt=""/></div>
                                <p class="inbox-item-author">Alin</p>
                                <p class="inbox-item-text"> Rooms are exceptionally nice and quiet...</p>
                                <p class="inbox-item-date">
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star"></span>  
                                </p>
                            </div>
                        </a>
                        <a href="#">
                            <div class="inbox-item">
                                <div class="inbox-item-img"><img src={require("./images/pers6.jpg").default} class="rounded-circle" alt=""/></div>
                                <p class="inbox-item-author">Melody</p>
                                <p class="inbox-item-text">Will definitely stay at this hotel for our next trip </p>
                                <p class="inbox-item-date">
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                </p>
                            </div>
                        </a>
                        <a href="#">
                            <div class="inbox-item">
                                <div class="inbox-item-img"><img src={require("./images/Woman_1.jpg").default} class="rounded-circle" alt="" /></div>
                                <p class="inbox-item-author">Lucinda</p>
                                <p class="inbox-item-text">The location is great!!!</p>
                                <p class="inbox-item-date">
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                </p>
                            </div>
                        </a>
                        
                    </div>
                </div>
            </div>
            <div class="col-xl-8">
                <div class="row">
                    <div class="col-sm-4">
                        <div class="card-box tilebox-one"><i class="icon-layers float-right text-muted"></i>
                            <h6 class="text-muted text-uppercase mt-0">Reservations</h6>
                            <h2 class="" data-plugin="counterup">1,587</h2><span class="badge badge-custom">+11% </span><span class="text-muted">From previous period</span></div>
                    </div>
                    <div class="col-sm-4">
                        <div class="card-box tilebox-one"><i class="icon-paypal float-right text-muted"></i>
                            <h6 class="text-muted text-uppercase mt-0">Revenue</h6>
                            <h2 class="">$<span data-plugin="counterup">46,782</span></h2><span class="badge badge-danger">-29% </span><span class="text-muted">From previous period</span></div>
                    </div>
                    <div class="col-sm-4">
                        <div class="card-box tilebox-one"><i class="icon-rocket float-right text-muted"></i>
                            <h6 class="text-muted text-uppercase mt-0">5 star Reviews</h6>
                            <h2 class="" data-plugin="counterup">1,123</h2><span class="badge badge-custom">+89% </span><span class="text-muted">Last year</span></div>
                    </div>
                </div>
                <div class="card-box">
                    <h4 class="header-title mt-0 mb-3">Statistics</h4>
                    <div class="">
                        <div class="">
                            <h5 class="text-custom">Our customer experience</h5>
                            <p><b>2010-2021</b></p>
                            <p class="text-muted font-13 mb-0">Our customers have consistently grown both in numbers and ratings. More then 50% of the returning customers have said that they would love to return next year and more then 70% of the customers have said that they for sure will make a reservation here the next year too.</p>
                        </div>
                        <hr/>
                        <div class="">
                            <h5 class="text-custom">Covid Restrictions!</h5>
                            <p class="text-muted font-13 mb-0">The last year and the curent time has taught us to be more careful around. All of our employee are strictly taught to wear a mask and gloves our the customers of the hotel as well as the customer's goods. All of the rooms and the contents of the room are disinfected each day using only the best suplies that the market has to offer.Also the the customer has to wear a mask outside of his own rooma to ensure his own safety but also the people around him. With all of these rules we can ensure the safest travel you will have at our hotel</p>
                        </div>
                    </div>
                </div>
                <div class="card-box">
                    <h4 class="header-title mb-3">  Booking</h4>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    
                                    <th>S.No.</th>
                                    <th>Name</th>
                                    <th>Room No.</th>
                                    <th>Checkin-Date</th>
                                    <th>Checkout-Date</th>
                                    <th>Total price</th>
                                    
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Ginger Smith</td>
                                    <td>401</td>
                                    <td>06/05/2021</td>
                                    <td>10/05/2021</td>
                                    <td>850</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Ava Wilson</td>
                                    <td>402</td>
                                    <td>03/06/2021</td>
                                    <td>13/06/2021</td>
                                    <td>1450</td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>Alexei Mihailov</td>
                                    <td>405</td>
                                    <td>24/07/2021</td>
                                    <td>30/07/2021</td>
                                    <td>956</td>
                                </tr>
                                <tr>
                                    <td>4</td>
                                    <td>Sasha Müller</td>
                                    <td>308</td>
                                    <td>30/08/2021</td>
                                    <td>07/09/2021</td>
                                    <td>1290</td>
                                </tr>
                                <tr>
                                    <td>5</td>
                                    <td>Stefan Muresan</td>
                                    <td>301</td>
                                    <td>06/07/2021</td>
                                    <td>09/07/2021</td>
                                   <td>560</td>
                                </tr>
                            </tbody>
                        </table>
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

export default HotelPage;