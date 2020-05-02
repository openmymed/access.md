import React, { Component } from "react";
import { BrowserRouter as Router, Route } from "react-router-dom";
import Home from "./views/Home/Home";
import PatientDetails from "./views/Patient-Details/PatientDetails";
import PatientQuestions from "./views/Patient-Questions/PatientQuestions";
import PatientList from "./views/Patient-List/PatientList";
import Signin from "./views/Signin/Signin";
import "./App.css";

class App extends Component {
  render() {
    return (
      <Router>
        <Route exact path="/" component={Signin} />
        <Route path="/patients" component={PatientList} />
        <Route path="/patient" component={PatientDetails} />
        <Route path="/ask" component={PatientQuestions} />
        <Route path="/home" component={Home} />
      </Router>
    );
  }
}

export default App;
