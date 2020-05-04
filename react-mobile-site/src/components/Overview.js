import React, { Component } from "react";
import * as api from "../utils/api";
import Card from "./Card";

class Overview extends Component {
  constructor(props) {
    super(props);
    this.state = {
      totalPatients: "0",
      totalSymptoms: "0",
      totalAnswers: "0",
    };
    this._updateTotalAnswers = this._updateTotalAnswers.bind(this);
    this._updateTotalSymptoms = this._updateTotalSymptoms.bind(this);
    this._updateTotalPatients = this._updateTotalPatients.bind(this);
  }

  componentDidMount() {
    //uncomment these when api is ready
    // this._updateTotalAnswers();
    // this._updateTotalSymptoms();
    // this._updateTotalPatients();
  }

  render() {
    return (
      <div className="mt-5 ">
        <h5 className="col-12">Overview</h5>
        <div className="mt-2 d-flex row text-center">
          <div className="col-12">
            <Card
              title="Total Patients"
              number={this.state.totalPatients}
            ></Card>
          </div>
          <div className="col-12">
            <Card
              title="Unseen Symptoms"
              number={this.state.totalSymptoms}
            ></Card>
          </div>
          <div className="col-12">
            <Card
              title="Unseen Answers"
              number={this.state.totalAnswers}
            ></Card>
          </div>
        </div>
      </div>
    );
  }

  _updateTotalPatients() {
    api.getPatientsCount().then((json) => {
      this.setState({
        totalPatients: json.count,
      });
    });
  }

  _updateTotalSymptoms() {
    api.getUarchivedSymptomsCount().then((json) => {
      this.setState({
        totalSymptoms: json.count,
      });
    });
  }

  _updateTotalAnswers() {
    api.getUnarchivedAnswersCount().then((json) => {
      this.setState({
        totalAnswers: json.count,
      });
    });
  }
}
export default Overview;
