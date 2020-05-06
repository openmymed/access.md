import React, { Component } from "react";
import { Row, Form } from "react-bootstrap";
import "./../App.css";
import * as api from "./../utils/api";

class Profile extends Component {
  constructor(props) {
    super(props);
    this.state = {
      name: "",
      age: "",
      sex: "",
      height: "",
      weight: "",
      respiratoryDiseases: false,
      g6pdDeficiency: false,
      diabetes: false,
      cardiovascularDiseases: false,
      obesity: false,
      medications: [],
    };
    this.getPatientProfile = this.getPatientProfile.bind(this);
  }
  componentDidMount() {
    this.getPatientProfile();
  }
  getPatientProfile() {
    api.getPatientMedicalProfile(this.props.patientId).then((json) => {
      let sex = "";
      if (json.sex === "Male") {
        sex = "Male";
      } else {
        sex = "Female";
      }
      this.setState({
        age: json.age,
        height: json.height,
        weight: json.weight,
        sex: sex,
        g6pdDeficiency: json.g6pdDeficiency,
        respiratoryDiseases: json.respiratoryDiseases,
        cardiovascularDiseases: json.cardiovascularDiseases,
        diabetes: json.diabetes,
        obesity: json.obseity,
        medications: json.medications,
      });
    });
    api.getPatientPersonalProfile(this.props.patientId).then((json) => {
      this.setState({ name: json.firstName + " " + json.lastName });
    });
    this.setState({
      name: "Majed nuss",
      age: "23",
      sex: "Male",
      height: "175",
      weight: "85",
      respiratoryDiseases: false,
      g6pdDeficiency: false,
      diabetes: false,
      cardiovascularDiseases: false,
      obesity: false,
      medications: [],
    });
  }

  render() {
    return (
      <div className="p-0 border-right">
        <div className="d-flex row pt-4 pl-5">
          <img
            alt="patient-profile-img"
            className="profile-img m-0"
            src="https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/Circle-icons-profile.svg/768px-Circle-icons-profile.svg.png"
          ></img>
          <div className="ml-2">
            <h5 className="mb-0">{this.state.name}</h5>
            <h6 className="text-secondary">{this.state.age} Years Old</h6>
          </div>
        </div>
        <h5 className="bg-info text-white d-block mx-auto p-2 mt-3">
          General Info
        </h5>
        <Row className="text-center p-3">
          <div className="col-4">
            <h6>Sex</h6>
            <h4 className="text-info">{this.state.sex}</h4>
          </div>
          <div className="col-4">
            <h6>Height</h6>
            <div className="d-inline-flex text-info">
              <h4>{this.state.height}</h4>
              <p className="ml-1">cm</p>
            </div>
          </div>
          <div className="col-4">
            <h6>Weight</h6>
            <div className="d-inline-flex text-info">
              <h4>{this.state.weight}</h4>
              <p className="ml-1">kg</p>
            </div>
          </div>
        </Row>
        <h5 className="bg-info text-white d-block mx-auto p-2 mt-3">
          Medical Flags
        </h5>
        <div className="col-12 py-1">
          <Form.Group>
            <Form.Check
              disabled
              type="checkbox"
              label="G6PD Deficiency"
              checked={this.state.g6pdDeficiency}
            />
          </Form.Group>
          <Form.Group>
            <Form.Check
              disabled
              type="checkbox"
              label="Respiratory Diseases"
              checked={this.state.respiratoryDiseases}
            />
          </Form.Group>

          <Form.Group>
            <Form.Check
              disabled
              type="checkbox"
              label="Diabetes"
              checked={this.state.diabetes}
            />
          </Form.Group>

          <Form.Group>
            <Form.Check
              disabled
              type="checkbox"
              label="Cardiovascular Diseases"
              checked={this.state.cardiovascularDiseases}
            />
          </Form.Group>

          <Form.Group>
            <Form.Check
              disabled
              type="checkbox"
              label="Obesity"
              checked={this.state.obesity}
            />
          </Form.Group>
        </div>
        <h5 className="bg-info  text-white d-block mx-auto p-2 mt-3">
          Current Medications
        </h5>
        {/* <div className="p-1">{(this.medications = list("ul", Medication))}</div> */}
      </div>
    );
  }
}
export default Profile;
