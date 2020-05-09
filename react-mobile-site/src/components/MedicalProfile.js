import React, { Component } from "react";
import { Form } from "react-bootstrap";
import "./../App.css";

class MedicalProfile extends Component {
  render() {
    return (
      <div>
        <div className="col-12 py-1">
          <Form.Group>
            <Form.Check
              disabled
              type="checkbox"
              label="G6PD Deficiency"
              checked={this.props.g6pdDeficiency}
            />
          </Form.Group>
          <Form.Group>
            <Form.Check
              disabled
              type="checkbox"
              label="Respiratory Diseases"
              checked={this.props.respiratoryDiseases}
            />
          </Form.Group>

          <Form.Group>
            <Form.Check
              disabled
              type="checkbox"
              label="Diabetes"
              checked={this.props.diabetes}
            />
          </Form.Group>

          <Form.Group>
            <Form.Check
              disabled
              type="checkbox"
              label="Cardiovascular Diseases"
              checked={this.props.cardiovascularDiseases}
            />
          </Form.Group>
          <Form.Group>
            <Form.Check
              disabled
              type="checkbox"
              label="Obesity"
              checked={this.props.obesity}
            />
          </Form.Group>
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
