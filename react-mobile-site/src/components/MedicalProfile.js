import React, { Component } from "react";
import "./../App.css";

class MedicalProfile extends Component {
  render() {
    return (
      <div className="pl-2">
        <h5 className="d-block mx-auto p-2 mt-3">Medical Flags</h5>
        <div className="col-12 py-1">
          <input
            disabled="disabled"
            type="checkbox"
            id="G6PD"
            name="G6PD"
            value="G6PD Deficiency"
            checked={this.props.g6pdDeficiency}
          ></input>
          <label className="ml-1" htmlFor="G6PD">
            G6PD Deficiency
          </label>
          <br></br>
          <input
            disabled="disabled"
            type="checkbox"
            id="respiratory"
            name="respiratory"
            value="Respiratory Diseases"
            checked={this.props.respiratoryDiseases}
          ></input>
          <label className="ml-1" htmlFor="respiratory">
            Respiratory Diseases
          </label>
          <br></br>
          <input
            disabled="disabled"
            type="checkbox"
            id="diabetes"
            name="diabetes"
            value="Diabetes"
            checked={this.props.diabetes}
          ></input>
          <label className="ml-1" htmlFor="diabetes">
            Diabetes
          </label>
          <br></br>
          <input
            disabled="disabled"
            type="checkbox"
            id="cardio"
            name="cardio"
            value="Cardiovascular Diseases"
            checked={this.props.cardiovascularDiseases}
          ></input>
          <label className="ml-1" htmlFor="cardio">
            Cardiovascular Diseases
          </label>
          <br></br>
          <input
            disabled="disabled"
            type="checkbox"
            id="obesity"
            name="obesity"
            value="Obesity"
            checked={this.props.obesity}
          ></input>
          <label className="ml-1" htmlFor="obesity">
            Obesity
          </label>
          <br></br>
        </div>
        <h5 className="d-block mx-auto p-2 mt-3">Current Medications</h5>
        <div className="col-12">
          <ul>
            {this.props.medications.map((medication) => (
              <li key={this.props.medications.indexOf(medication)}>
                {medication}
              </li>
            ))}
          </ul>
        </div>
      </div>
    );
  }
}
export default MedicalProfile;
