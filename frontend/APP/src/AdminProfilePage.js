import './adminProfile.css';
import React from 'react';
import { Helmet } from 'react-helmet';
import Client from './Client';
import HotelAdmin from './HotelAdmin';
import Hotel from './Hotel';

class AdminProfilePage extends React.Component{
    constructor(props){
        super(props);
        this.state={
            client:JSON.parse(localStorage.user).user,
            hotelName:'',
            hotelLocation:'',
            hotelIdentifier:'',
            hotelPhotoLink:'',
            hotelDescription:'',
            hotelPrice:'',
            dummy:0
        };
        this.client=new Client(this.state.client.username,this.state.client.email,this.state.client.profilePic,this.state.client.auth);
        this.admin=new HotelAdmin(this.client.username,this.client.email,this.client.profilePic,this.client.auth);
        this.addHotels=[];
    }

    myChangeHandler = (event) => {
        let nam = event.target.name;
        let val = event.target.value;
        this.setState({[nam] : val});
    }

    addHotel = () =>{
        /*var hotel=new Hotel(0,this.state.hotelIdentifier,this.state.hotelName,this.state.hotelLocation,0,0,this.state.hotelIdentifier,this.state.hotelPhotoLink,this.state.hotelDescription,parseFloat(this.state.hotelPrice));
        var res=this.admin.addHotel(hotel);
        alert(JSON.stringify(res));
        */
        alert("Hotel added!");
        this.addHotels.push({"hotelName":this.state.hotelName,"photoLink":this.state.hotelPhotoLink,"identifier":this.state.hotelIdentifier});
        this.setState({dummy:1});
    }

    render(){
        //var hotels=this.admin.getAllHotels();
        var items=[];


        /*for(const[index,hotel] of hotels.entries()){
            items.push(
            <li class="list-group-item"><h1>{hotel.hotelName}</h1><img style={{width:'50%',height:'50%'}} class="img-rounded" src={hotel.photoLink}/><a href={"https://www.booking.com/hotel/"+hotel.identifier+".html"} class="pull-right btn btn-info btn-lg" style={{fontSize:'large',height:'15%',width:'30%',position: 'absolute',right: '0',bottom: '0'}}>View Hotel</a></li>);
        }*/

        for(const[index,hotel] of this.addHotels.entries()){
            items.push(
            <li class="list-group-item"><h1>{hotel.hotelName}</h1><img style={{width:'50%',height:'50%'}} class="img-rounded" src={hotel.photoLink}/><a href={"https://www.booking.com/hotel/"+hotel.identifier+".html"} class="pull-right btn btn-info btn-lg" style={{fontSize:'large',height:'15%',width:'30%',position: 'absolute',right: '0',bottom: '0'}}>View Hotel</a></li>);
        }

        return(
<div class="admin-profile-page">
<hr/>
<div class="container bootstrap snippets bootdey">


<div class="row">
    <div class="col-sm-10">
        <h1>Hotel Administrator</h1>
        <h2>User: {this.client.username}</h2></div>
    <div class="col-sm-2">
        <a href="#" class="pull-right"><img title="profile image" class="img-circle img-responsive" src={this.client.profilePic} /></a>
    </div>
</div>
<div class="row">
    <div class="col-sm-3">

        <ul class="list-group">
            <li class="list-group-item text-muted">Contact</li>
            <li class="list-group-item text-right"><span class="pull-left"><strong>E-mail</strong></span> {this.client.email}</li>
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
            <li><a href="#addHotel" data-toggle="tab">Add Hotel</a></li>
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
                    {items}
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


            <div class="tab-pane" id="addHotel">

                <div class="form" action="##" method="post" id="addHotelForm">
                    <div class="form-group">

                        <div class="col-xs-6">
                            <label for="first_name">
                                <h4>Hotel Name</h4></label>
                            <input onChange={this.myChangeHandler} type="text" class="form-control" name="hotelName" id="first_name" placeholder="name" title="enter hotel name" />
                        </div>
                    </div>
                    <div class="form-group">

                        <div class="col-xs-6">
                            <label for="last_name">
                                <h4>Location</h4></label>
                            <input onChange={this.myChangeHandler} type="text" class="form-control" name="hotelLocation" id="last_name" placeholder="location" title="enter hotel location" />
                        </div>
                    </div>

                    <div class="form-group">

                        <div class="col-xs-6">
                            <label for="phone">
                                <h4>Identifier</h4></label>
                            <input onChange={this.myChangeHandler} type="text" class="form-control" name="hotelIdentifier" id="phone" placeholder="identifier" title="enter hotel identifier" />
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-6">
                            <label for="mobile">
                                <h4>Photo Link</h4></label>
                            <input onChange={this.myChangeHandler} type="text" class="form-control" name="hotelPhotoLink" id="mobile" placeholder="link" title="enter hotel photo link" />
                        </div>
                    </div>
                    <div class="form-group">

                        <div class="col-xs-6">
                            <label for="email">
                                <h4>Description</h4></label>
                            <input onChange={this.myChangeHandler} type="text" class="form-control" name="hotelDescription" id="email" placeholder="description" title="enter hotel description" />
                        </div>
                    </div>
                    <div class="form-group">

                        <div class="col-xs-6">
                            <label for="email">
                                <h4>Price</h4></label>
                            <input onChange={this.myChangeHandler} type="text" class="form-control" name="hotelPrice" id="email" placeholder="0" title="enter hotel price" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <br/>
                            <button onClick={this.addHotel} class="btn btn-lg btn-success" type="submit"><i class="glyphicon glyphicon-ok-sign"></i> Save</button>
                            <button class="btn btn-lg" type="reset"><i class="glyphicon glyphicon-repeat"></i> Reset</button>
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

export default AdminProfilePage;