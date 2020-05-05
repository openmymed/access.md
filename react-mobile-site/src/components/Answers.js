import React, { Component } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCalendar, faClock } from "@fortawesome/free-solid-svg-icons";
import * as api from "../utils/api";

class Answers extends Component {
  constructor(props) {
    super(props);
    this.state = { answers: [] };
    this.getAnswers = this.getAnswers.bind(this);
  }

  componentDidMount() {
    this.getAnswers();
  }
  getAnswers() {
    api.getPatientAnswers(this.props.patientId).then((data) => {
      this.setState({ answers: data });
    });
    this.setState({
      answers: [
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
          answerText: "123",
          date: "4/27/2020",
          time: "5:49:07 PM",
        },
      ],
    });
  }

  render() {
    return (
      <div className="mx-2">
        <div className="d-flex justify-content-between">
          <h5 className="p-2 mt-3">Patient Answers</h5>
          <div className="mt-3">
            <a this="questionsButton" className="btn btn-primary text-white">
              Add Questions
            </a>
          </div>
        </div>
        <div className="col-12 mb-2">
          {this.state.answers.map((answer) => (
            <Answer key={this.state.answers.indexOf(answer)}>{answer}</Answer>
          ))}
        </div>
      </div>
    );
  }
}
export default Answers;

class Answer extends Component {
  constructor(props) {
    super(props);
    this.state = { answer: props.children };
    this.handleDismiss = this.handleDismiss.bind(this);
  }
  handleDismiss() {}
  render() {
    return (
      <div className="row mt-2">
        <div className="answer-card card card-common rounded-lg w-100">
          <div className="card-header d-flex justify-content-start">
            <h6>{this.state.answer.name}</h6>
          </div>
          <div className="card-body w-100">
            <div className="answer-note">{this.state.answer.answerText}</div>

            <div className="d-flex justify-content-between mt-2 w-100">
              <div>
                <div>
                  <FontAwesomeIcon className="mr-1" icon={faCalendar} />
                  {this.state.answer.date}
                </div>
                <div>
                  <FontAwesomeIcon className="mr-1" icon={faClock} />
                  {this.state.answer.time}
                </div>
              </div>
              <div>
                <button
                  this="dismiss"
                  className="btn btn-danger"
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
