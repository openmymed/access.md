import React, { Component } from "react";
import { Modal, Button, Form } from "react-bootstrap";

class AddPatient extends Component {
  constructor(props) {
    super(props);
    this.state = { code: "", show: props.show };
    this.handleChange = this.handleChange.bind(this);
    this.handleClose = this.handleClose.bind(this);
    this.handleShow = this.handleShow.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }
  handleChange(e) {
    e.preventDefault();
    this.setState({
      code: e.target.value,
    });
  }
  handleSubmit(e) {
    e.preventDefault();
    console.log(this.state.code);
  }
  handleClose() {
    this.setState({
      show: false,
    });
  }
  handleShow() {
    this.setState({
      show: true,
    });
  }

  render() {
    return (
      <Modal show={this.state.show} onHide={this.handleClose} animation={false}>
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
          <Button variant="secondary" onClick={this.handleClose}>
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
