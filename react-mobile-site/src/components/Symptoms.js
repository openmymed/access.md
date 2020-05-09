import React, { Component } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCalendar, faClock } from "@fortawesome/free-solid-svg-icons";
import { getTitle } from "../utils/icpc";
import * as api from "../utils/api";

class Symptoms extends Component {
  constructor(props) {
    super(props);
    this.state = { symptoms: [] };
    this.getSymptoms = this.getSymptoms.bind(this);
  }

  componentDidMount() {
    this.getSymptoms();
  }

  getSymptoms() {
    api.getPatientSymptoms(this.props.patientId).then((data) => {
      this.setState({ symptoms: data });
    });

    this.setState({
      symptoms: [
        {
          id: 1,
          name: "Do you have fever? ",
          answerText: "123",
          date: "4/27/2020",
          time: "5:49:07 PM",
        },
        {
          id: 1,
          name: "Do you have fever?",
          answerText: "123",
          date: "4/27/2020",
          time: "5:49:07 PM",
        },
        {
          id: 1,
          name: "Do you have fever?",
          note: "123",
          date: "4/27/2020",
          time: "5:49:07 PM",
        },
      ],
    });
  }

  render() {
    return (
      <div>
        <div className="mx-2">
          <h5 className="text-info font-weight-bold p-2 mt-3">
            Patient Symptoms
          </h5>
          <div className="col-12 mb-2">
            {this.state.symptoms.map((symptom) => (
              <Symptom key={this.state.symptoms.indexOf(symptom)}>
                {symptom}
              </Symptom>
            ))}
          </div>
        </div>
      </div>
    );
  }
}
export default Symptoms;

class Symptom extends Component {
  constructor(props) {
    super(props);
    this.state = { symptom: props.children };
    this.handleDismiss = this.handleDismiss.bind(this);
  }

  handleDismiss() {
    //    this._dismiss().then((res) => {
    //      unmount(this.el.parentNode, this);
    //    });
    //      _dismiss() {
    //     return api.archiveSymptom(this.patientId, this.data.id);
    //   }
  }

  render() {
    return (
      <div className="row mt-2">
        <div className="symptom-card card card-common rounded-lg w-100">
          <div className="card-header d-flex justify-content-start">
            <h6>{this.state.symptom.name}</h6>
          </div>
          <div className="card-body w-100">
            <div className="symptom-note">{this.state.symptom.note}</div>
            <div className="d-flex justify-content-between mt-2 w-100">
              <div>
                <div>
                  <FontAwesomeIcon className="mr-1" icon={faCalendar} />
                  {this.state.symptom.date}
                </div>
                <div>
                  <FontAwesomeIcon className="mr-1" icon={faClock} />
                  {this.state.symptom.time}
                </div>
              </div>
              <div>
                <button
                  this="dismiss"
                  className="btn btn-danger btn-sm"
                  onClick={this.handleDismiss}
                >
                  Dismiss
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
