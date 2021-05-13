import SignUpPage from './SignUpPage';
import LoginPage from './LoginPage';
import NotFoundPage from './NotFoundPage';
import HomePage from './HomePage';
import AdminProfilePage from './AdminProfilePage';
import ClientProfilePage from './ClientProfilePage';
import HotelPage from './HotelPage';
import SearchLocationPage from './SearchLocationPage';
import HotelInfoPage from './HotelInfoPage';
import FavoritesPage from './FavoritesPage';
import ChangeHotelInfoPage from './ChangeHotelInfoPage';
import StatisticsPage from './StatisticsPage';
import { BrowserRouter as Router, Route, Switch, Link } from "react-router-dom";
import React from 'react';
import './App.css';
import Client from './Client';

class App extends React.Component{
  static user=new Client(null,null,null,null);
  constructor(props){
    super(props);
  }

  render(){
    return (
      <Router>
        <Switch>
        <Route exact path="/" component={HomePage} />
        <Route exact path="/login" render={(props) => {return <LoginPage {...props}/>}} />
        <Route exact path="/signup" component={SignUpPage} />
        <Route exact path="/profile" render={(props) => {return <ClientProfilePage {...props}/>}} />
        <Route exact path="/adminProfile" component={AdminProfilePage} />
        <Route exact path="/hotel" component={HotelPage} />
        <Route exact path="/search" component={SearchLocationPage} />
        <Route exact path="/search/:location" component={SearchLocationPage} />
        <Route exact path="/hotelInfo" component={HotelInfoPage} />
        <Route exact path="/fav" component={FavoritesPage} />
        <Route exact path="/changeHotelInfo" component={ChangeHotelInfoPage} />
        <Route exact path="/statistics" component={StatisticsPage} />
        <Route component={NotFoundPage} /> 
        </Switch>
      </Router>
    );
  }
}

export default App;