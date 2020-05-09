import React, { Component } from "react";
import Navbar from "../../components/Navbar";
import { Link } from "react-router-dom";
import * as api from "../../utils/api";

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
          <h4>My Patients</h4>
          <div className="mt-4">
            <table className="table" this="table">
              <thead>
                <tr>
                  <th scope="col" className="text-left">
                    Name
                  </th>
                  <th scope="col" className="text-center">
                    Number
                  </th>
                  <th scope="col" className="text-right">
                    Profile
                  </th>
                </tr>
              </thead>
              {this.state.patients.map((patient) => (
                <PatientEntry key={this.state.patients.indexOf(patient)}>
                  {patient}
                </PatientEntry>
              ))}
            </table>
          </div>
        </div>
      </Navbar>
    );
  }

  getPatientList() {
    api.getPatients().then((json) => {
      this.setState({ patients: json });
    });
    //dummy data
    this.setState({
      patients: [
        {
          id: 1,
          name: "Majed Nuss",
          number: "123",
        },
        {
          id: 2,
          name: "Ahmad Nuss",
          number: "123",
        },
        {
          id: 3,
          name: "Mutaz Nuss",
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
      id: this.props.children.id,
    };
  }
  render() {
    let url = "/patient/" + this.state.id;
    return (
      <tbody>
        <tr>
          <td className="text-left">{this.state.patient.name}</td>
          <td className="text-center">{this.state.patient.number}</td>
          <td className="text-right">
            <Link to={url} className="btn btn-primary btn-sm">
              Profile
            </Link>
          </td>
        </tr>
      </tbody>
    );
  }
}
