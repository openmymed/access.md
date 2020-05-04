import React, { Component } from "react";
import Navbar from "../../components/Navbar";
import Overview from "../../components/Overview";
import PatientUpdates from "../../components/PatientUpdates";

class Home extends Component {
  render() {
    return (
      <Navbar>
        <Overview></Overview>
        <PatientUpdates></PatientUpdates>
      </Navbar>
    );
  }
}
export default Home;
