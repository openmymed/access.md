import React, { Component } from "react";
import { Tabs, Tab } from "react-bootstrap";
import { Link, withRouter } from "react-router-dom";
import Navbar from "../../components/Navbar";
import Answers from "./../../components/Answers";
import Symptoms from "./../../components/Symptoms";
import Vitals from "./../../components/Vitals";
import MedicalProfile from "../../components/MedicalProfile";
import Profile from "../../components/Profile";
import "./../../App.css";
import * as api from "../../utils/api";

class PatientDetails extends Component {
  constructor(props) {
    super(props);
    this.state = {
      patientId: this.props.match.params.id,
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
      width: window.innerWidth,
      key: "flags",
    };
    this.setKey = this.setKey.bind(this);
    this.getPatientProfile = this.getPatientProfile.bind(this);
  }

  setKey(number) {
    this.setState({ activeTab: number });
  }
  updateDimensions = () => {
    this.setState({ width: window.innerWidth });
  };
  componentWillUnmount() {
    window.removeEventListener("resize", this.updateDimensions);
  }
  componentDidMount() {
    this.getPatientProfile(this.state.patientId);
    window.addEventListener("resize", this.updateDimensions);
  }

  getPatientProfile(patientId) {
    api.getPatientMedicalProfile(patientId).then((json) => {
      let sex = "";
      if (json.sex === "Male") {
        sex = "Male";
      } else {
        sex = "Female";
      }
      this.setState({
        patientId: patientId,
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
    api.getPatientPersonalProfile(patientId).then((json) => {
      this.setState({ name: json.firstName + " " + json.lastName });
    });

    this.setState({
      patientId: patientId,
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
    let askUrl = "/ask/" + this.state.patientId;
    if (this.state.width <= 1080) {
      return (
        <Navbar>
          <div className="p-0 border-right">
            <div className="d-flex p-3">
              <img
                alt="patient-profile-img"
                className=" patient-profile-img"
                src="https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/Circle-icons-profile.svg/768px-Circle-icons-profile.svg.png"
              ></img>
              <div className=" ml-4 mt-2">
                <h5 className="mb-1">{this.state.name}</h5>
                <h6 className="text-secondary">{this.state.age} Years Old</h6>
              </div>
            </div>
            <div className="border-top border-bottom row m-0 text-center px-2 pt-2">
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
            </div>
            <div>
              <Tabs
                className="font-weight-bold"
                id="controlled-tab-example"
                activeKey={this.state.key}
                onSelect={(key) => this.setState({ key })}
              >
                <Tab eventKey="flags" title=" Medical Flags">
                  <div className="pl-2">
                    <h5 className="d-block mx-auto p-2 mt-3">Medical Flags</h5>
                    <MedicalProfile
                      respiratoryDiseases={this.state.respiratoryDiseases}
                      g6pdDeficiency={this.state.g6pdDeficiency}
                      diabetes={this.state.diabetes}
                      cardiovascularDiseases={this.state.cardiovascularDiseases}
                      obesity={this.state.obesity}
                      medications={this.state.medications}
                    ></MedicalProfile>
                  </div>
                </Tab>
                <Tab eventKey="symptoms" title="Symptoms">
                  <Symptoms patientId={this.state.patientId} />
                </Tab>
                <Tab eventKey="answers" title="Answers">
                  <Answers patientId={this.state.patientId} />
                </Tab>
                <Tab eventKey="vitals" title="Vitals">
                  <Vitals patientId={this.state.patientId} />
                </Tab>
              </Tabs>
            </div>
          </div>
        </Navbar>
      );
    } else {
      return (
        <Navbar>
          <div className="d-flex justify-content-between">
            <h4 className="ml-3 mt-5">Patient Profile</h4>
            <Link
              className="btn btn-primary mr-3 mt-5 btn-sm text-white"
              to={askUrl}
            >
              Questions
            </Link>
          </div>
          <div className="row m-3 bg-light">
            <Vitals patientId={this.state.patientId} />
          </div>
          <div className="row m-3 border bg-light">
            <div className="col-lg-4 p-0 border-right">
              <Profile patientId={this.state.patientId} />
            </div>
            <div className="col-lg-8 bg-light">
              <div className="row ">
                <div className="col-lg-6">
                  <Symptoms patientId={this.state.patientId} />
                </div>
                <div className="col-lg-6">
                  <Answers patientId={this.state.patientId} />
                </div>
              </div>
            </div>
          </div>
        </Navbar>
      );
    }
  }
}
export default withRouter(PatientDetails);
