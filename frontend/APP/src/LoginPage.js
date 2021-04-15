import react from 'react';
import './login_style.css';
import Credentials from './Credentials';
import ClientLogin from './ClientLogin';
import Client from './Client';

var data=require('./Data.js');

class LoginPage extends react.Component{
    constructor(props){
        super(props);
        this.state={
            username : '',
            password : ''
        };
    }

    myChangeHandler = (event) => {
        let nam = event.target.name;
        let val = event.target.value;
        this.setState({[nam] : val});
    }

    login = () =>{
        var credentials=new Credentials(this.state.username,this.state.password);
        var result=ClientLogin.login(credentials);
        if(typeof(result)==="string"){
          alert(result);
        }
        else{
          alert("Login succesfull");
          data.user=result;
          window.location="/profile";
        }
    }

    changePassword = () => {
        alert('Change password request not yet implemented!');
    }

    render(){
        return <div class="login-page">
      <div class="loginBox">
          <div class="box loginAction">
            <div class="loginElements">
              <h2>Log In as a User or Company.</h2>
              <label>
                <h4>Username</h4>
                <input type="text" name="username" onChange={this.myChangeHandler}/>
              </label>
              <label>
                <h4>Password</h4>
                <input type="password" name="password" onChange={this.myChangeHandler}/>
              </label>
              <button type="button" name="button" class="loginButton btn-Login" onClick={this.login}>Log in</button>
                <button type="button" name="button" class="forgotButton btn-Login" onClick={this.changePassword}>New password</button>
            </div>

            <div class="registerSegment">
                <div class="registerTitle">
                  <div class="register-Text">
                    <h2>You don't have an account yet?</h2>
                    <p>Register and start traveling</p>
                  </div>
                  <div class="registerButton">
                    <button type="button" name="button" class="registerButton btn-register"><a href="/signup">Register</a></button>
                  </div>

                </div>
            </div>
          </div>
      </div>
    </div>;
    }
}

export default LoginPage;