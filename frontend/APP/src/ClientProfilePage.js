import react from 'react';
import Client from './Client';
import { Helmet } from 'react-helmet';
import './user.css';

class ClientProfilePage extends react.Component{
    constructor(props){
        super(props);
        this.state={
            client:JSON.parse(localStorage.user).user,
            reset:false,
            newUsername:'',
            newProfilePhotoLink:'',
            searchLoc:''
        };
        this.client=new Client(this.state.client.username,this.state.client.email,this.state.client.profilePic,this.state.client.auth);
        this.notif=this.client.getNotifications();
        this.history=this.client.view_history();
    }

    deleteNotif= (id) => {
        var res=this.client.delNotifications(id);
        this.notif=this.client.getNotifications();
        this.setState({reset:true});
    }

    deleteHistory = (id) => {
        var res=this.client.delete_history(id);
        this.history=this.client.view_history();
        this.setState({reset:true});
    }

    deleteAllHistory = () => {
        for(const [index,history] of this.history.entries()){
            this.deleteHistory(history.id);
        }
        this.history=this.client.view_history();
        this.setState({reset:true});
    }

    update = () => {
        var res=this.client.updateProfile(this.state.newUsername,this.state.newProfilePhotoLink);
        alert(res.message);
        this.client.username=this.state.newUsername;
        this.client.profilePic=this.state.newProfilePhotoLink;
        localStorage.setItem("user",JSON.stringify({user:this.client}));
        this.setState({reset:true});
    }

    myChangeHandler = (event) => {
        let nam = event.target.name;
        let val = event.target.value;
        this.setState({[nam] : val});
    }

    search = (event) =>{
        if(event.keyCode!=13){
            return;
        }
        window.location="/search?location="+this.state.searchLoc;
    }

    render(){
        var notifHtml=[];

        for(const [index,notif] of this.notif.entries()){
            notifHtml.push(
                <p>
                    <span>{notif.message}</span>
                    <span style={{cursor:'pointer',float:'right',color:'red'}} onClick={() => {this.deleteNotif(notif.id)}}>X</span>
                </p>
            );
        }

        var historyHtml=[];

        for(const [index,history] of this.history.entries()){
            const url="/search?location="+history.searchedLocation;
            historyHtml.push(
                <p>
                    <a href={url}>{history.searchedLocation}</a>
                    <span style={{cursor:'pointer',float:'right',color:'red'}} onClick={() => {this.deleteHistory(history.id)}}>X</span>
                </p>
            );
        }


        return(
<div class="client-profile">
    <div class="topnav">
        <a class="active" href="/">Home</a>
        <a href="/statistics">Statistics</a>
        <a href="/login">Sign Out</a>
        <a href="/fav">Favorites</a>
        <input type="text" placeholder="Search location" name="searchLoc" onKeyUp={this.search} onChange={this.myChangeHandler}/>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-4">
                <div class="text-center card-box">
                    <div class="member-card">
                        <div class="thumb-xl member-thumb m-b-10 center-block">
                            <img src={this.client.profilePic} class="img-circle img-thumbnail" alt="profile-image" />
                        </div>

                        <div class="">
                            <h4 style={{fontSize:'20px'}} class="m-b-5">{this.client.username}</h4>
                            <p class="text-muted">{this.client.email}</p>
                        </div>


                        <div class="text-left m-t-40">
                            <p class="text-muted font-13"><strong>Name :</strong> <span class="m-l-15">{this.client.username}</span></p>
                            <p class="text-muted font-13"><strong>Email :</strong> <span class="m-l-15">{this.client.email}</span></p>
                        </div>

                        
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
                                <a href="#notif" data-toggle="tab" aria-expanded="true">
                                    <span class="visible-xs"><i class="fa fa-photo"></i></span>
                                    <span class="hidden-xs">Notifications</span>
                                </a>
                            </li>
                            <li class="">
                                <a href="#history" data-toggle="tab" aria-expanded="false">
                                    <span class="visible-xs"><i class="fa fa-cog"></i></span>
                                    <span class="hidden-xs">Searched Locations</span>
                                </a>
                            </li>
                            <li class="">
                                <a href="#settings" data-toggle="tab" aria-expanded="false">
                                    <span class="visible-xs"><i class="fa fa-cog"></i></span>
                                    <span class="hidden-xs">Settings</span>
                                </a>
                            </li>
                        </ul>
                        <div class="tab-content">

                            <div class="tab-pane active" id="notif">
                                {notifHtml}
                            </div>
                            <div class="tab-pane" id="history">
                                {historyHtml}
                                <p><span style={{cursor:'pointer',color:'red'}} onClick={this.deleteAllHistory}>Delete History</span></p>
                            </div>
                            <div class="tab-pane" id="settings">
                                <div role="form">
                                    <div class="form-group">
                                        <label for="FullName">Username</label>
                                        <input style={{fontSize:'20px'}} type="text" onChange={this.myChangeHandler} name="newUsername" id="FullName" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="Email">Profile photo link</label>
                                        <input style={{fontSize:'20px'}} type="email" onChange={this.myChangeHandler} name="newProfilePhotoLink" id="Email" class="form-control" />
                                    </div>
                                    <button style={{fontSize:'20px'}} onClick={this.update} class="btn btn-primary waves-effect waves-light w-md" type="submit">Save</button>
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

export default ClientProfilePage;