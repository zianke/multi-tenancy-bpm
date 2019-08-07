import React from 'react';
import { BrowserRouter as Router, Route } from "react-router-dom";
import './App.css';
import LoginForm from "./LoginForm";
import CustomerHome from "./CustomerHome";
import StaffHome from "./StaffHome";
import AdminHome from "./AdminHome";

class App extends React.Component {
  render() {
    return (
      <Router>
        <div>
          <Route exact path="/" component={LoginForm}/>
          <Route path="/customer" component={CustomerHome}/>
          <Route path="/staff" component={StaffHome}/>
          <Route path="/admin" component={AdminHome}/>
        </div>
      </Router>
    );
  }
}

export default App;