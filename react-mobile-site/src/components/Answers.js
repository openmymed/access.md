import React, { Component } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCalendar, faClock } from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
import * as api from "../utils/api";

class Answers extends Component {
  constructor(props) {
    super(props);
    this.state = { answers: [], isVisible: window.innerWidth < 1080 };
    this.getAnswers = this.getAnswers.bind(this);
  }
  updateDimensions = () => {
    if (window.innerWidth < 1080) {
      this.setState({ width: window.innerWidth, isVisible: true });
    } else {
      this.setState({ width: window.innerWidth, isVisible: false });
    }
  };
  componentWillUnmount() {
    window.removeEventListener("resize", this.updateDimensions);
  }
  componentDidMount() {
    window.addEventListener("resize", this.updateDimensions);
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
    let askUrl = "/ask/" + this.props.patientId;
    return (
      <div className="mx-2">
        <div className="d-flex justify-content-between">
          <h5 className="text-info font-weight-bold p-2 mt-3 mb-0">
            Patient Answers
          </h5>
          <div className="mt-3">
            <Link
              className={`btn btn-primary btn-sm text-white ${
                this.state.isVisible ? "" : "invisible"
              }`}
              to={askUrl}
            >
              Questions
            </Link>
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
  handleDismiss() {
    //here we handle answers dismiss
  }
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
