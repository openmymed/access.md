import React, { Component } from "react";
import Navbar from "../../components/Navbar";
import { Button } from "react-bootstrap";
import { withRouter } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlus, faPen } from "@fortawesome/free-solid-svg-icons";
import AddEditQuestion from "../../components/AddEditQuestion";
import * as api from "../../utils/api";

class PatientQuestions extends Component {
  constructor(props) {
    super(props);
    this.state = {
      patientId: this.props.match.params.id,
      questions: [],
      showModal: false,
    };
    this.loadQuestions = this.loadQuestions.bind(this);
  }

  handleModalClose() {
    this.setState({ showModal: false });
  }

  componentDidMount() {
    this.loadQuestions();
  }

  loadQuestions() {
    api.getPatientQuestions(this.state.patientId).then((json) => {
      this.setState({ questions: json });
    });
    //dummy data
    this.setState({
      questions: [
        {
          id: 1,
          question: "Do you have a fever?",
          repetition: "none",
          recurrance: [],
          recurring: false,
          startDate: "",
          endDate: "",
        },
        {
          id: 3,
          question: "Do you have a fever?",
          type: "Scale",
          recurrance: [],
          recurring: false,
          startDate: "",
          endDate: "",
        },
        {
          id: 2,
          question: "Do you have a fever?",
          type: "Scale",
          recurrance: [],
          recurring: false,
          startDate: "",
          endDate: "",
        },
        {
          id: 4,
          question: "Do you have a fever?",
          type: "Scale",
          recurrance: [
            { hourOfDay: 0, minuteOfHour: 0 },
            { hourOfDay: 2, minuteOfHour: 2 },
          ],
          recurring: true,
          startDate: "15/7/2019",
          endDate: "15/7/2019",
        },
      ],
    });
  }

  handleAddQuestion() {
    this.setState({ showModal: true });
  }

  render() {
    return (
      <Navbar>
        <div className="col-sm-12 mt-4">
          <div className="d-flex justify-content-between">
            <h4>Questions</h4>
            <Button
              className="btn btn-primary btn-sm"
              onClick={this.handleAddQuestion.bind(this)}
            >
              <FontAwesomeIcon icon={faPlus} />
            </Button>
            <AddEditQuestion
              patientId={this.state.patientId}
              show={this.state.showModal}
              onClose={this.handleModalClose.bind(this)}
            />
          </div>
          <div className="mt-4">
            <table className="table" this="table">
              <thead>
                <tr>
                  <th scope="col" className="text-left">
                    Question
                  </th>
                  <th scope="col" className="text-center">
                    Repetitions
                  </th>
                  <th scope="col" className="text-right"></th>
                </tr>
              </thead>
              {this.state.questions.map((question) => (
                <Question
                  patientId={this.state.patientId}
                  key={this.state.questions.indexOf(question)}
                >
                  {question}
                </Question>
              ))}
            </table>
          </div>
        </div>
      </Navbar>
    );
  }
}
export default withRouter(PatientQuestions);

class Question extends Component {
  constructor(props) {
    super(props);
    let repetitionText = "None";
    if (this.props.children.recurring) {
      repetitionText = this.props.children.recurrance.length + " Times";
    }
    this.state = {
      question: this.props.children,
      showModal: false,
      repetition: repetitionText,
    };
    this.handleEdit = this.handleEdit.bind(this);
  }
  handleEdit() {
    this.setState({ showModal: true });
  }
  handleModalClose() {
    this.setState({ showModal: false });
  }

  render() {
    return (
      <tbody>
        <tr>
          <td className="text-left">{this.state.question.question}</td>
          <td className="text-center">{this.state.repetition}</td>
          <td className="text-right">
            <Button className="btn-sm" onClick={this.handleEdit}>
              <FontAwesomeIcon icon={faPen} />
            </Button>
            <AddEditQuestion
              data={this.props.children}
              patientId={this.props.patientId}
              show={this.state.showModal}
              onClose={this.handleModalClose.bind(this)}
            />
          </td>
        </tr>
      </tbody>
    );
  }
}
