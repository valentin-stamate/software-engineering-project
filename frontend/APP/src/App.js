import SignUpPage from './SignUpPage';
import LoginPage from './LoginPage';
import NotFoundPage from './NotFoundPage';
import HomePage from './HomePage';
import { BrowserRouter as Router, Route, Switch, Link } from "react-router-dom";
import React from 'react';

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
        <Route component={NotFoundPage} /> 
        </Switch>
      </Router>
    );
  }
}

export default App;