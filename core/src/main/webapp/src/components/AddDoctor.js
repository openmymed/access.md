import React, { Component } from "react";
import { Modal, Button, Form } from "react-bootstrap";
import * as api from "../utils/api";

class AddDoctor extends Component {
  constructor(props) {
    super(props);
    this.state = { username: "", password: "", firstName: "", lastName: "" };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }
  handleChange(e) {
    let id = e.target.id;
    if (id === "username") {
      this.setState({ username: e.target.value });
    } else if (id === "password") {
      this.setState({ password: e.target.value });
    } else if (id === "firstname") {
      this.setState({ firstName: e.target.value });
    } else if (id === "lastname") {
      this.setState({ lastName: e.target.value });
    }
  }
  handleSubmit() {
    api
      .addDoctor({
        username: this.state.username,
        password: this.state.password,
        firstName: this.state.firstName,
        lastName: this.state.lastName,
      })
      .then(this.props.onClose());
  }

  render() {
    return (
      <Modal
        show={this.props.show}
        onHide={this.props.onClose}
        animation={false}
      >
        <Modal.Header closeButton>
          <Modal.Title>Add Doctor</Modal.Title>
        </Modal.Header>

        <Modal.Body>
          <Form.Group>
            <Form.Label>Username</Form.Label>
            <Form.Control
              id="username"
              value={this.state.username}
              type="text"
              onChange={this.handleChange}
            />
          </Form.Group>
          <Form.Group>
            <Form.Label>Password</Form.Label>
            <Form.Control
              id="password"
              value={this.state.password}
              type="password"
              onChange={this.handleChange}
            />
          </Form.Group>
          <Form.Group>
            <Form.Label>First Name</Form.Label>
            <Form.Control
              id="firstname"
              value={this.state.firstName}
              type="text"
              onChange={this.handleChange}
            />
          </Form.Group>
          <Form.Group>
            <Form.Label>Last Name</Form.Label>
            <Form.Control
              id="lastname"
              value={this.state.lastName}
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
export default AddDoctor;
