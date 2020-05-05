import React, { Component } from "react";
import { Modal, Button, Form, Row, Collapse } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlus } from "@fortawesome/free-solid-svg-icons";
import * as api from "../utils/api";

class AddEditQuestion extends Component {
  constructor(props) {
    super(props);
    if (typeof this.props.data !== "undefined") {
      let data = this.props.data;
      this.state = {
        questionText: data.question,
        questionType: data.type,
        recurrance: data.recurrance,
        fromDate: new Date(data.startDate).toLocaleDateString(),
        toDate: new Date(data.endDate).toLocaleDateString(),
        recurring: data.recurring,
        repetitions: [],
      };
    } else {
      this.state = {
        questionText: "",
        questionType: "",
        recurrance: [],
        fromDate: "",
        toDate: "",
        recurring: false,
        repetitions: [],
      };
    }
    this.handleChange = this.handleChange.bind(this);
  }

  handleSubmit() {
    api
      .addQuestion(this.props.patientId, {
        question: this.state.questionText,
        type: this.state.questionType,
        recurring: this.state.recurring,
        recurrance: this.state.recurrance,
        startDate: Date.parse(this.state.fromDate),
        endDate: Date.parse(this.state.toDate),
      })
      .then((res) => {
        this.props.onClose();
      });
  }
  handleChange(e) {
    let id = e.target.id;
    switch (id) {
      case "question-text-input":
        this.setState({ questionText: e.target.value });
        break;
      case "question-type-input":
        this.setState({ questionType: e.target.value });
        break;
      case "from-date-input":
        this.setState({ fromDate: e.target.value });
        break;
      case "to-date-input":
        this.setState({ toDate: e.target.value });
        break;
    }
  }
  handleRecurring() {
    this.setState({ recurring: !this.state.recurring });
  }
  handleRecurrence() {
    this.state.recurrance.push({ hourOfDay: 0, minuteOfHour: 0 });
    this.setState(this.state.recurrance);
  }
  render() {
    return (
      <Modal
        show={this.props.show}
        onHide={this.props.onClose}
        animation={false}
      >
        <Modal.Header closeButton>
          <Modal.Title>Add/Edit Question</Modal.Title>
        </Modal.Header>

        <Modal.Body>
          <Row>
            <Form.Group className="col">
              <Form.Label>Question</Form.Label>
              <Form.Control
                id="question-text-input"
                value={this.state.questionText}
                type="text"
                onChange={this.handleChange}
              />
            </Form.Group>
          </Row>
          <Row>
            <div className="col">
              <Form.Group>
                <Form.Label>Question Type</Form.Label>
                <Form.Control
                  id="question-type-input"
                  as="select"
                  value={this.state.questionType}
                  onChange={this.handleChange}
                >
                  <option value="Scale">Scale</option>
                  <option value="Binary">Binary</option>
                  <option value="Text">Text</option>
                  <option value="Vitals">Vitals</option>
                </Form.Control>
              </Form.Group>
              <Form.Group>
                <Form.Check
                  type="checkbox"
                  label="Recurring?"
                  checked={this.state.recurring}
                  onChange={this.handleRecurring.bind(this)}
                  aria-controls="recurrance-row"
                  aria-expanded={this.state.recurring}
                />
              </Form.Group>
            </div>
          </Row>
          <Collapse in={this.state.recurring}>
            <Row id="recurrance-row">
              {/* //className="collapse" */}
              <Form.Group className="col">
                <Form.Label>From Date</Form.Label>
                <Form.Control
                  onChange={this.handleChange}
                  id="from-date-input"
                  type="text"
                  value={this.state.fromDate}
                />
              </Form.Group>
              <Form.Group className="col">
                <Form.Label>To Date</Form.Label>
                <Form.Control
                  onChange={this.handleChange}
                  id="to-date-input"
                  type="text"
                  value={this.state.toDate}
                />
              </Form.Group>

              <div className="col-12">
                <table className="table" this="table">
                  <thead>
                    <tr>
                      <th scope="col" className="text-left">
                        #
                      </th>
                      <th scope="col" className="text-center">
                        Recurrence Time
                      </th>
                      <th scope="col" className="text-right"></th>
                    </tr>
                  </thead>
                  {this.state.repetitions.map((repetition) => (
                    <Repetition
                      key={this.state.repetitions.indexOf(repetition)}
                    >
                      {repetition}
                    </Repetition>
                  ))}
                </table>
              </div>
              <div className="col-12 d-flex justify-content-end">
                <Button
                  className="btn bt-primary btn-sm"
                  onClick={this.handleRecurrence.bind(this)}
                >
                  <FontAwesomeIcon icon={faPlus} />
                </Button>
              </div>
            </Row>
          </Collapse>
        </Modal.Body>

        <Modal.Footer>
          <Button variant="secondary" onClick={this.props.onClose}>
            Close
          </Button>
          <Button
            variant="primary"
            type="submit"
            onSubmit={this.handleSubmit.bind(this)}
          >
            Save changes
          </Button>
        </Modal.Footer>
      </Modal>
    );
  }
}
export default AddEditQuestion;

// this.repetitions.el.addEventListener("repetitionRemoved", (e) => {
//     this.repetitions.update(e.detail.newList)
// })

class Repetition extends Component {
  constructor() {
    this.state = {
      repitition: this.props.children,
      timeInput: "",
      indexText: "",
    };

    // this.timeInput.oninput = (e) => {
    //   if (e.target.value) {
    //     let splits = e.target.value.split(":");
    //     this.data.hourOfDay = parseInt(splits[0]);
    //     this.data.minuteOfHour = parseInt(splits[1]);
    //   }
    // };
  }
  onDelete() {}
  handleInputChange() {}
  render() {
    return (
      <tbody>
        <tr>
          <td scope="row" className="text-left" this="indexText">
            {this.state.indexText}
          </td>
          <td className="text-center">
            <input
              className="repetition-time"
              value={this.state.timeInput}
              onChange={this.handleInputChange.bind(this)}
              type="time"
            ></input>
          </td>
          <td className="text-right">
            <a onClick={this.onDelete.bind(this)}>
              {/* <i className="fa fa-times"></i> */}
            </a>
          </td>
        </tr>
      </tbody>
    );
  }

  _delete() {
    this.items.splice(this.index, 1);
    this.el.dispatchEvent(
      new CustomEvent("repetitionRemoved", {
        detail: { newList: this.items },
        bubbles: true,
      })
    );
  }

  update(data, index, items) {
    this.data = data;
    this.index = index;
    this.items = items;
    this.indexText.textContent = index + 1;
    this.timeInput.value =
      ("" + this.data.hourOfDay).padStart(2, "0") +
      ":" +
      ("" + this.data.minuteOfHour).padStart(2, "0");
  }
}
