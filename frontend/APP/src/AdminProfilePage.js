import './adminProfile.css';
import React from 'react';
import { Helmet } from 'react-helmet';

class AdminProfilePage extends React.Component{
    constructor(props){
        super(props);
    }

    render(){
        return(
<div class="admin-profile-page">
<hr/>
<div class="container bootstrap snippets bootdey">


<div class="row">
    <div class="col-sm-10">
        <h1>Administrator</h1>
        <h2>User: joedoe6</h2></div>
    <div class="col-sm-2">
        <a href="#" class="pull-right"><img title="profile image" class="img-circle img-responsive" src={require("./images/admi.png").default} /></a>
    </div>
</div>
<div class="row">
    <div class="col-sm-3">

        <ul class="list-group">
            <li class="list-group-item text-muted">Profile</li>
            <li class="list-group-item text-right"><span class="pull-left"><strong>Joined</strong></span> 15.02.2020</li>
            <li class="list-group-item text-right"><span class="pull-left"><strong>Last seen</strong></span> Yesterday</li>
            <li class="list-group-item text-right"><span class="pull-left"><strong>Real name</strong></span> Joseph Doe</li>
            <li class="list-group-item text-right"><span class="pull-left"><strong>Nickname</strong></span> Joe</li>
        </ul>

        <ul class="list-group">
            <li class="list-group-item text-muted">Contact</li>
            <li class="list-group-item text-right"><span class="pull-left"><strong>E-mail</strong></span> joedoe@gmail.com</li>
            <li class="list-group-item text-right"><span class="pull-left"><strong>Mobile</strong></span>0712345678</li>
            <li class="list-group-item text-right"><span class="pull-left"><strong>Phone</strong></span> 0312345678</li>
            <li class="list-group-item text-right"><span class="pull-left"><strong>Adress</strong></span> Pascani, Iasi, Romania</li>
            <li class="list-group-item text-right"><span class="pull-left"><strong>Website</strong></span> <a href = "#" style = {{textDecoration : 'none'}}>www.joedoe.com</a></li>
        </ul>



        <ul class="list-group">
            <li class="list-group-item text-muted">Statistics<i class="fa fa-dashboard fa-1x"></i></li>
            <li class="list-group-item text-right"><span class="pull-left"><strong>Reviews</strong></span> 4.6 / 5</li>
            <li class="list-group-item text-right"><span class="pull-left"><strong>Hotels</strong></span> 3</li>
            <li class="list-group-item text-right"><span class="pull-left"><strong>Total Rooms</strong></span> 25</li>
            <li class="list-group-item text-right"><span class="pull-left"><strong>Booked rooms</strong></span> 13</li>
            <li class="list-group-item text-right"><span class="pull-left"><strong>Free rooms</strong></span> 12</li>
            <li class="list-group-item text-right"><span class="pull-left"><strong>Reservations this month</strong></span> 67</li>
            <li class="list-group-item text-right"><span class="pull-left"><strong>Reservations this year</strong></span> 188</li>
        </ul> 
        

    </div>
    <div class="col-sm-9">

        <ul class="nav nav-tabs" id="myTab">
            <li class="active"><a href="#notif" data-toggle="tab">Notifications</a></li>
            <li><a href="#hotels" data-toggle="tab">My Hotels</a></li>
            <li><a href="#settings" data-toggle="tab">Settings</a></li>
        </ul>

        <div class="tab-content">
            <div class="tab-pane active" id="notif">
                <h2></h2>

                <ul class="list-group">
                    <li class="list-group-item text-muted">Notifications</li>
                    <li class="list-group-item">One person booked in at <a>Grand Hotel Continental</a></li>
                    <li class="list-group-item">One person canceled a booking at <a>Hotel Bavaria Blu</a></li>
                    <li class="list-group-item">One person rated <a>Hotel Bavaria Blu</a></li>
                    <li class="list-group-item">One person canceled a booking at <a>Pensiunea Poiana Izvoarelor</a></li>
                </ul>

            </div>
            <div class="tab-pane" id="hotels">

                <h2></h2>

                <ul class="list-group">
                    <li class="list-group-item text-muted">Hotels</li>
                    <li class="list-group-item"><h1>Grand Hotel Continental</h1><img class="img-rounded" src={require("./images/hotel1.webp").default}/><a href="/hotel" class="pull-right btn btn-info btn-lg" style={{position: 'absolute',right: '0',bottom: '0'}}>View Hotel</a></li>
                    <li class="list-group-item"><h1>Pensiunea Poiana Izvoarelor</h1><img class="img-rounded" src={require("./images/hotel2.webp").default}/><a href="#" class="pull-right btn btn-info btn-lg" style={{position: 'absolute',right: '0',bottom: '0'}}>View Hotel</a></li>
                    <li class="list-group-item"><h1>Hotel Bavaria Blu</h1><img class="img-rounded" src={require("./images/hotel3.webp").default}/><a href="#" class="pull-right btn btn-info btn-lg" style={{position: 'absolute',right: '0',bottom: '0'}}>View Hotel</a></li>

                </ul>

            </div>
            <div class="tab-pane" id="settings">

                <form class="form" action="##" method="post" id="registrationForm">
                    <div class="form-group">

                        <div class="col-xs-6">
                            <label for="first_name">
                                <h4>First name</h4></label>
                            <input type="text" class="form-control" name="first_name" id="first_name" placeholder="first name" title="enter your first name if any." />
                        </div>
                    </div>
                    <div class="form-group">

                        <div class="col-xs-6">
                            <label for="last_name">
                                <h4>Last name</h4></label>
                            <input type="text" class="form-control" name="last_name" id="last_name" placeholder="last name" title="enter your last name if any." />
                        </div>
                    </div>

                    <div class="form-group">

                        <div class="col-xs-6">
                            <label for="phone">
                                <h4>Phone</h4></label>
                            <input type="text" class="form-control" name="phone" id="phone" placeholder="enter phone" title="enter your phone number if any." />
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-6">
                            <label for="mobile">
                                <h4>Mobile</h4></label>
                            <input type="text" class="form-control" name="mobile" id="mobile" placeholder="enter mobile number" title="enter your mobile number if any." />
                        </div>
                    </div>
                    <div class="form-group">

                        <div class="col-xs-6">
                            <label for="email">
                                <h4>Email</h4></label>
                            <input type="email" class="form-control" name="email" id="email" placeholder="you@email.com" title="enter your email." />
                        </div>
                    </div>
                    <div class="form-group">

                        <div class="col-xs-6">
                            <label for="email">
                                <h4>Location</h4></label>
                            <input type="email" class="form-control" id="location" placeholder="somewhere" title="enter a location" />
                        </div>
                    </div>
                    <div class="form-group">

                        <div class="col-xs-6">
                            <label for="password">
                                <h4>Password</h4></label>
                            <input type="password" class="form-control" name="password" id="password" placeholder="password" title="enter your password." />
                        </div>
                    </div>
                    <div class="form-group">

                        <div class="col-xs-6">
                            <label for="password2">
                                <h4>Verify</h4></label>
                            <input type="password" class="form-control" name="password2" id="password2" placeholder="password2" title="enter your password2." />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <br/>
                            <button class="btn btn-lg btn-success" type="submit"><i class="glyphicon glyphicon-ok-sign"></i> Save</button>
                            <button class="btn btn-lg" type="reset"><i class="glyphicon glyphicon-repeat"></i> Reset</button>
                        </div>
                    </div>
                </form>
            </div>

        </div>
    </div>

</div>
</div>

</div>
        );
    }
}

export default AdminProfilePage;