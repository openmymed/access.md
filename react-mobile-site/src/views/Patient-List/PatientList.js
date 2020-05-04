import React, { Component } from "react";
import Navbar from "../../components/Navbar";
import { Link } from "react-router-dom";

class PatientList extends Component {
  constructor(props) {
    super(props);
    this.state = { patients: [] };
    this.getPatientList = this.getPatientList.bind(this);
  }

  componentDidMount() {
    this.getPatientList();
  }

  render() {
    return (
      <Navbar>
        <div className="col-sm-12 mt-4">
          <h4>Patient List</h4>
          <div className="mt-4">
            <table className="table" this="table">
              <thead>
                <th scope="col" className="text-left">
                  Name
                </th>
                <th scope="col" className="text-center">
                  Number
                </th>
                <th scope="col" className="text-right">
                  Profile
                </th>
              </thead>
              {this.state.patients.map((patient) => (
                <PatientEntry>{patient}</PatientEntry>
              ))}
            </table>
          </div>
        </div>
      </Navbar>
    );
  }

  getPatientList() {
    //change this with api fetch
    this.setState({
      patients: [
        {
          id: 1,
          name: "Majed Nuss",
          number: "123",
        },
        {
          id: 1,
          name: "Majed Nuss",
          number: "123",
        },
        {
          id: 1,
          name: "Majed Nuss",
          number: "123",
        },
      ],
    });
  }
}
export default PatientList;

class PatientEntry extends Component {
  constructor(props) {
    super(props);
    this.state = {
      patient: this.props.children,
    };
  }
  render() {
    return (
      <tbody>
        <tr>
          <td className="text-left">{this.state.patient.name}</td>
          <td className="text-center">{this.state.patient.number}</td>
          <td className="text-right">
            <Link to="/patient" className="btn btn-primary">
              Profile
            </Link>
          </td>
        </tr>
      </tbody>
    );
  }
}
