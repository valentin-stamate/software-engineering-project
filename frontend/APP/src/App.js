import SignUpPage from './SignUpPage';
import LoginPage from './LoginPage';
import NotFoundPage from './NotFoundPage';
import HomePage from './HomePage';
import AdminProfilePage from './AdminProfilePage';
import ClientProfilePage from './ClientProfilePage';
import HotelPage from './HotelPage';
import { BrowserRouter as Router, Route, Switch, Link } from "react-router-dom";
import React from 'react';
import './App.css';

class App extends React.Component{
  constructor(props){
    super(props);
  }

  render(){
    return (
      <Router>
        <Switch>
        <Route exact path="/" component={HomePage} />
        <Route exact path="/login" component={LoginPage} />
        <Route exact path="/signup" component={SignUpPage} />
        <Route exact path="/profile" component={ClientProfilePage} />
        <Route exact path="/adminProfile" component={AdminProfilePage} />
        <Route exact path="/hotel" component={HotelPage} />
        <Route component={NotFoundPage} /> 
        </Switch>
      </Router>
    );
  }
}

export default App;