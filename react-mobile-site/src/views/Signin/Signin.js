import React, { Component } from "react";
import { Button, Form } from "react-bootstrap";
import "./Signin.css";
import Logo from "./../../images/openmymed.png";

class Signin extends Component {
  constructor(props) {
    super(props);
    this.state = { username: "", password: "" };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  render() {
    return (
      <div className="Login">
        <img src={Logo} alt="logo" />
        <h1 className="mt-5 text-info">Access.md</h1>
        <h2 className="text-info">Sign In</h2>
        <Form className="mt-5 form" onSubmit={this.handleSubmit}>
          <Form.Group controlId="formBasicEmail">
            <Form.Label>Username</Form.Label>
            <Form.Control
              value={this.state.username}
              type="text"
              onChange={this.handleChange}
              placeholder="Username"
            />
          </Form.Group>
          <Form.Group controlId="formBasicPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control
              value={this.state.password}
              type="password"
              onChange={this.handleChange}
              placeholder="Password"
            />
          </Form.Group>
          <Form.Control.Feedback type="invalid">
            Incorrect Username or Password.
          </Form.Control.Feedback>
          <Button className="mt-4 btn-block" variant="info" type="submit">
            Submit
          </Button>
        </Form>
      </div>
    );
  }

  handleSubmit(e) {
    e.preventDefault();
    //if successfully logged in
    this.props.history.push("/home");
  }
  handleChange(e) {
    if (e.target.type === "text") {
      this.setState({ username: e.target.value });
    } else if (e.target.type === "password") {
      this.setState({ password: e.target.value });
    }
  }
}

export default Signin;
