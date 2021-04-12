import './signup.css';
import ClientInfo from './UserSignUp';
import ClientSignUp from './UserSignUp';
const react = require("react");

class SignUpPage extends react.Component{
    constructor(props){
        super(props);
        this.state={
            fname : '',
            lname : '',
            email : '',
            tel : '' ,
            password : '',
            confirmPassword : ''
        }
    }

    myChangeHandler = (event) => {
        let nam = event.target.name;
        let val = event.target.value;
        this.setState({[nam] : val});
    }

    signupClient = () => {
        alert('Not yet implemented!');
        if(this.state.password != this.state.confirmPassword){
            alert("Passwords don t match!");
        }

        ClientSignUp.signup(new ClientInfo(this.state.fname,this.state.lname,this.state.password,this.state.email,this.state.tel));
        //TO DO ...

    }

    render(){
        return( 
        <div class="sign-up-page-container">
        <div class="sign-up-page">
           <div class="form sign-user">
              <h2>Sign Up as a User</h2>
              <label>
              <span>First Name</span>
              <input type="text" name="fname" onChange={this.myChangeHandler}/>
              </label>
              <label>
              <span>Last Name</span>
              <input type="text" name="lname" onChange={this.myChangeHandler} />
              </label>
              <label>
              <span>Email</span>
              <input type="email" name="email" onChange={this.myChangeHandler} />
              </label>
              <label>
              <span>Phone Number</span>
              <input type="tel" name="tel" onChange={this.myChangeHandler} />
              </label>
              <label>
              <span>Password</span>
              <input type="password" name="password" onChange={this.myChangeHandler}/>
              </label>
              <label>
              <span>Confirm Password</span>
              <input type="password" name="confirmPassword" onChange={this.myChangeHandler}/>
              </label>
              <button class="submit" type="button" onClick={this.signupClient}>Register Now</button>
           </div>
           <div class="sub-cont">
              <div class="img">
                 <div class="img-text m-up">
                    <h2>You're a hotel manager?</h2>
                    <p>Register and let people see your offers!</p>
                 </div>
                 <div class="img-text m-in">
                    <h2>You're someone interested in visiting a specific place?</h2>
                    <p>Register and see what every city has for you !</p>
                 </div>
                 <div class="img-btn">
                    <span class="m-up">Hotel Admin</span>
                    <span class="m-in">User</span>
                 </div>
              </div>
              <div class="form sign-admin">
                 <h2>Sign Up as a Hotel Admin</h2>
                 <label>
                 <span>First Name</span>
                 <input type="text" />
                 </label>
                 <label>
                 <span>Last Name</span>
                 <input type="text" />
                 </label>
                 <label>
                 <span>Email</span>
                 <input type="email" name="email" />
                 </label>
                 <label>
                 <span>Phone Number</span>
                 <input type="tel" name="tel" />
                 </label>
                 <label>
                 <span>Company name</span>
                 <input type="text" />
                 </label>
                 <label>
                 <span>Location</span>
                 <input type="text" />
                 </label>
                 <label>
                 <span>Password</span>
                 <input type="password" />
                 </label>
                 <label>
                 <span>Confirm Password</span>
                 <input type="password" />
                 </label>
                 <button type="button" class="submit">Register Now</button>
                </div>
            </div>
        </div>
        </div>);
    }
}

export default SignUpPage;
