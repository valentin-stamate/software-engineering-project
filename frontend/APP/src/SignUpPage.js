import './signup.css';
import ClientInfo from './UserSignUp';
import ClientSignUp from './UserSignUp';
import HotelAdminSignUp from './HotelAdminSignUp';
const react = require("react");

class SignUpPage extends react.Component{
    constructor(props){
        super(props);
        this.state={
            username:'',
            email:'',
            password:'',
            confirmPassword:'',
            profilePhotoLink:'',
            switchSignup:0
        }

        this.userSignup=<div class="form sign-admin">
        <h2>Sign Up as a User</h2>
        <label>
        <span>Username</span>
        <input type="text" name="username" onChange={this.myChangeHandler}/>
        </label>
        <label>
        <span>Email</span>
        <input type="email" name="email" onChange={this.myChangeHandler} />
        </label>
        <label>
        <span>Password</span>
        <input type="password" name="password" onChange={this.myChangeHandler}/>
        </label>
        <label>
        <span>Confirm Password</span>
        <input type="password" name="confirmPassword" onChange={this.myChangeHandler}/>
        </label>
        <label>
        <span>Profile Photo Link</span>
        <input type="text" name="profilePhotoLink" onChange={this.myChangeHandler}/>
        </label>
        <button class="submit" type="button" onClick={this.signupClient}>Register Now</button>
     </div>;

     this.userDescr=<div class="img-text">
     <h2>You're a hotel manager?</h2>
     <p>Register and let people see your offers!</p>
     <button onClick={() => {this.changeSignup(this.adminSignup,this.adminDescr);}}>Hotel Admin</button>
  </div>;

  this.adminDescr=<div class="img-text">
  <h2>You're someone interested in visiting a specific place?</h2>
  <p>Register and see what every city has for you !</p>
  <button onClick={() => {this.changeSignup(this.userSignup,this.userDescr);}}>User</button>
</div>;

     this.adminSignup=<div class="form sign-user">
     <h2>Sign Up as a Hotel Admin</h2>
     <label>
     <span>Username</span>
     <input type="text" name="username" onChange={this.myChangeHandler} />
     </label>
     <label>
     <span>Email</span>
     <input type="email" name="email" onChange={this.myChangeHandler} />
     </label>
     <label>
     <span>Password</span>
     <input type="password" name="password" onChange={this.myChangeHandler} />
     </label>
     <label>
     <span>Confirm Password</span>
     <input type="password" name="confirmPassword" onChange={this.myChangeHandler} />
     </label>
     <label>
     <span>Photo Link</span>
     <input type="text" name="profilePhotoLink" onChange={this.myChangeHandler} />
     </label>
     <button onClick={this.signupHotelAdmin} type="button" class="submit">Register Now</button>
    </div>;

    this.currentSignup=this.userSignup;
    this.currentDescr=this.userDescr;
    }

    changeSignup = (signup,descr) => {
        this.currentSignup=signup;
        this.currentDescr=descr;
        this.setState({switchSignup:0});
    }

    myChangeHandler = (event) => {
        let nam = event.target.name;
        let val = event.target.value;
        this.setState({[nam] : val});
    }

    signupClient = () => {
        if(this.state.password != this.state.confirmPassword){
            alert("Passwords don t match!");
        }
        var info=new ClientInfo(this.state.username,this.state.email,this.state.password,this.state.profilePhotoLink);
        info.username=this.state.username;
        info.email=this.state.email;
        info.password=this.state.password;
        info.profilePhotoLink=this.state.profilePhotoLink;
        var message=ClientSignUp.signup(info);
        alert(message);
        if(message.includes("Success")){
            window.location="/login";
        }
    }

    signupHotelAdmin = () => {
        if(this.state.password != this.state.confirmPassword){
            alert("Passwords don t match!");
        }
        var info=new ClientInfo(this.state.username,this.state.email,this.state.password,this.state.profilePhotoLink);
        info.username=this.state.username;
        info.email=this.state.email;
        info.password=this.state.password;
        info.profilePhotoLink=this.state.profilePhotoLink;
        var message=HotelAdminSignUp.signup(info);
        alert(message);
        if(message.includes("Success")){
            window.location="/login";
        }
    }

    render(){
        return( 
        <div class="sign-up-page-container">
        <div class="sign-up-page">
           {this.currentSignup}
           <div class="sub-cont">
              <div class="img">
                 {this.currentDescr}
              </div>
              
            </div>
        </div>
        </div>);
    }
}

export default SignUpPage;
