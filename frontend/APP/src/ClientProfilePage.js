import react from 'react';
import { Helmet } from 'react-helmet';
import './user.css';

var data=require('./Data.js');

class ClientProfilePage extends react.Component{
    constructor(props){
        super(props);
        this.state={
            client:data.user
        };
    }

    render(){
        return(
<div class="client-profile">
    <p>Hello, {this.state.client.username}</p>
    <div class="topnav">
        <a class="active" href="#home">Home</a>
        <a href="#about">See Recommendation</a>
        <a href="#">Sign Out</a>
        <input type="text" placeholder="Search location" />
    </div>

    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-4">
                <div class="text-center card-box">
                    <div class="member-card">
                        <div class="thumb-xl member-thumb m-b-10 center-block">
                            <img src={require("./images/boy.jpeg").default} class="img-circle img-thumbnail" alt="profile-image" />
                        </div>

                        <div class="">
                            <h4 class="m-b-5">Ion</h4>
                            <p class="text-muted">ion@gmail.com</p>
                        </div>

                        <button type="button" class="btn btn-danger btn-sm w-sm waves-effect m-t-10 waves-light">History</button>
                        <button type="button" class="btn btn-danger btn-sm w-sm waves-effect m-t-10 waves-light">Favorites</button>

                        <div class="text-left m-t-40">
                            <p class="text-muted font-13"><strong>Full Name :</strong> <span class="m-l-15">Ion Mihai</span></p>
                            <p class="text-muted font-13"><strong>Mobile :</strong><span class="m-l-15">0788888888</span></p>
                            <p class="text-muted font-13"><strong>Email :</strong> <span class="m-l-15">ion@gmail.com</span></p>
                            <p class="text-muted font-13"><strong>Location :</strong> <span class="m-l-15">Iasi</span></p>
                        </div>

                        <ul class="social-links list-inline m-t-30">
                            <li>
                                <a title="" data-placement="top" data-toggle="tooltip" class="tooltips" href="" data-original-title="Facebook"><i class="fa fa-facebook"></i></a>
                            </li>
                            <li>
                                <a title="" data-placement="top" data-toggle="tooltip" class="tooltips" href="" data-original-title="Google"><i class="fa fa-google"></i></a>
                            </li>
                        </ul>
                    </div>
                </div>

                <div class="card-box">
                    <h4 class="m-t-0 m-b-20 header-title">Statistics</h4>
                    <div class="p-b-10">
                        <p>Booked Locations This Month</p>
                        <div class="progress progress-sm">
                            <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" width="80%">
                            </div>
                        </div>
                        <p>Saved Locations</p>
                        <div class="progress progress-sm">
                            <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" width="60%">
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="col-md-8 col-lg-9">
                <div class="">
                    <div class="">
                        <ul class="nav nav-tabs navtab-custom">
                            <li class="active">
                                <a href="#profile" data-toggle="tab" aria-expanded="true">
                                    <span class="visible-xs"><i class="fa fa-photo"></i></span>
                                    <span class="hidden-xs">BOOKED LOCATIONS</span>
                                </a>
                            </li>
                            <li class="">
                                <a href="#settings" data-toggle="tab" aria-expanded="false">
                                    <span class="visible-xs"><i class="fa fa-cog"></i></span>
                                    <span class="hidden-xs">SETTINGS</span>
                                </a>
                            </li>
                        </ul>
                        <div class="tab-content">

                            <div class="tab-pane active" id="profile">
                                <div class="row">
                                    <div class="col-sm-4">
                                        <div class="gal-detail thumb">
                                            <a href="#" class="image-popup" title="Screenshot-2">
                                                <a href="https://www.booking.com/hotel/pl/europejski-krakow.ro.html?aid=376393;label=bookings-name-d2AakcaZh5VCmomr9TOSVAS438094247978%3Apl%3Ata%3Ap1%3Ap22.563.000%3Aac%3Aap%3Aneg%3Afi%3Atikwd-65526620%3Alp9040250%3Ali%3Adec%3Adm%3Appccp%3DUmFuZG9tSVYkc2RlIyh9YcpDr58xwogAJgpBCuFL5yA;sid=5cc9b7057c52a97440d70a97eaf6c210;all_sr_blocks=4850208_86359718_0_2_0;checkin=2021-04-09;checkout=2021-04-10;dest_id=-510625;dest_type=city;dist=0;group_adults=2;group_children=0;hapos=52;highlighted_blocks=4850208_86359718_0_2_0;hpos=27;no_rooms=1;room1=A%2CA;sb_price_type=total;sr_order=popularity;sr_pri_blocks=4850208_86359718_0_2_0__18499;srepoch=1617896974;srpvid=fd806f46e4b4004a;type=total;ucfs=1&"
                                                    target="_blank"><img src={require("./images/first-loc.jpg").default} class="thumb-img" alt="work-thumbnail" /></a>
                                            </a>
                                            <h4 class="text-center">Hotel Europejski</h4>
                                            <div class="ga-border"></div>
                                            <p class="text-muted text-center"><small>1st Location</small></p>
                                        </div>
                                    </div>

                                    <div class="col-sm-4">
                                        <div class="gal-detail thumb">
                                            <a href="#" class="image-popup" title="Screenshot-2">
                                                <a href="https://www.booking.com/hotel/ro/continental-forum.ro.html?aid=376393;label=bookings-name-d2AakcaZh5VCmomr9TOSVAS438094247978%3Apl%3Ata%3Ap1%3Ap22.563.000%3Aac%3Aap%3Aneg%3Afi%3Atikwd-65526620%3Alp9040250%3Ali%3Adec%3Adm%3Appccp%3DUmFuZG9tSVYkc2RlIyh9YcpDr58xwogAJgpBCuFL5yA;sid=5cc9b7057c52a97440d70a97eaf6c210;all_sr_blocks=3985818_90929413_2_2_0;checkin=2021-04-09;checkout=2021-04-10;dest_id=-1170221;dest_type=city;dist=0;group_adults=2;group_children=0;hapos=3;highlighted_blocks=3985818_90929413_2_2_0;hpos=3;no_rooms=1;room1=A%2CA;sb_price_type=total;sr_order=popularity;sr_pri_blocks=3985818_90929413_2_2_0__32490;srepoch=1617897101;srpvid=15c56f86eeb300ec;type=total;ucfs=1&"
                                                    target="_blank"><img src={require("./images/second-loc.jpg").default} class="thumb-img" alt="work-thumbnail" /></a>
                                            </a>
                                            <h4 class="text-center">Continental Forum Sibiu</h4>
                                            <div class="ga-border"></div>
                                            <p class="text-muted text-center"><small>2nd Location</small></p>
                                        </div>
                                    </div>

                                    <div class="col-sm-4">
                                        <div class="gal-detail thumb">
                                            <a href="#" class="image-popup" title="Screenshot-2">
                                                <a href="https://www.booking.com/hotel/ro/pensiunea-green-horse.ro.html?aid=376393;label=bookings-name-d2AakcaZh5VCmomr9TOSVAS438094247978%3Apl%3Ata%3Ap1%3Ap22.563.000%3Aac%3Aap%3Aneg%3Afi%3Atikwd-65526620%3Alp9040250%3Ali%3Adec%3Adm%3Appccp%3DUmFuZG9tSVYkc2RlIyh9YcpDr58xwogAJgpBCuFL5yA;sid=5cc9b7057c52a97440d70a97eaf6c210;all_sr_blocks=678257805_276694623_2_1_0;checkin=2021-04-09;checkout=2021-04-10;dest_id=-1170221;dest_type=city;dist=0;group_adults=2;group_children=0;hapos=14;highlighted_blocks=678257805_276694623_2_1_0;hpos=14;no_rooms=1;room1=A%2CA;sb_price_type=total;sr_order=popularity;sr_pri_blocks=678257805_276694623_2_1_0__20900;srepoch=1617897101;srpvid=15c56f86eeb300ec;type=total;ucfs=1&#hotelTmpl"
                                                    target="_blank"><img src={require("./images/second-loc.jpg").default} class="thumb-img" alt="work-thumbnail" /></a>
                                            </a>
                                            <h4 class="text-center">Pensiunea Green Horse</h4>
                                            <div class="ga-border"></div>
                                            <p class="text-muted text-center"><small>3rd Location</small></p>
                                        </div>
                                    </div>

                                    <div class="col-sm-4">
                                        <div class="gal-detail thumb">
                                            <a href="#" class="image-popup" title="Screenshot-2">
                                                <img src={require("./images/fourth-loc.jpg").default} class="thumb-img" alt="work-thumbnail" />
                                            </a>
                                            <h4 class="text-center">Oblique - Forest & Spa</h4>
                                            <div class="ga-border"></div>
                                            <p class="text-muted text-center"><small>4th Location</small></p>
                                        </div>
                                    </div>


                                </div>
                            </div>
                            <div class="tab-pane" id="settings">
                                <form role="form">
                                    <div class="form-group">
                                        <label for="FullName">Full Name</label>
                                        <input type="text" value="name" id="FullName" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="Email">Email</label>
                                        <input type="email" value="email@example.com" id="Email" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="Username">Username</label>
                                        <input type="text" value="user" id="Username" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="Password">Password</label>
                                        <input type="password" placeholder="6 - 15 Characters" id="Password" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="RePassword">Re-Password</label>
                                        <input type="password" placeholder="6 - 15 Characters" id="RePassword" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="AboutMe">About Me</label>
                                        <textarea style={{height: '125px'}} id="AboutMe" class="form-control">Add some text here</textarea>
                                    </div>
                                    <button class="btn btn-primary waves-effect waves-light w-md" type="submit">Save</button>
                                </form>
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

export default ClientProfilePage;