import React, { Component } from "react";
import { Modal, Button, Form } from "react-bootstrap";
import * as api from "../utils/api";

class AddPatient extends Component {
  constructor(props) {
    super(props);
    this.state = { code: "" };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }
  handleChange(e) {
    this.setState({
      code: e.target.value,
    });
  }
  handleSubmit() {
    api.assignPatientToMyself(this.state.code);
  }

  render() {
    return (
      <Modal
        show={this.props.show}
        onHide={this.props.onClose}
        animation={false}
      >
        <Modal.Header closeButton>
          <Modal.Title>Add Patient</Modal.Title>
        </Modal.Header>

        <Modal.Body>
          <Form.Group>
            <Form.Label>Secret Code</Form.Label>
            <Form.Control
              value={this.state.code}
              type="text"
              onChange={this.handleChange}
            />
          </Form.Group>
        </Modal.Body>

        <Modal.Footer>
          <Button variant="secondary" onClick={this.props.onClose}>
            Close
          </Button>
          <Button variant="primary" type="submit" onSubmit={this.handleSubmit}>
            Save changes
          </Button>
        </Modal.Footer>
      </Modal>
    );
  }
}
export default AddPatient;
