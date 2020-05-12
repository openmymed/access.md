import React, { Component } from "react";
import { Row, Button, Form } from "react-bootstrap";
import { Redirect } from "react-router-dom";
import "./Signin.css";
import Logo from "./../../images/openmymed.png";
import { loadIcpc } from "../../utils/icpc";
import * as api from "../../utils/api";

class Signin extends Component {
  constructor(props) {
    super(props);
    this.state = { width: window.innerWidth };
  }

  updateDimensions = () => {
    this.setState({ width: window.innerWidth });
  };
  componentDidMount() {
    window.addEventListener("resize", this.updateDimensions);
  }
  componentWillUnmount() {
    window.removeEventListener("resize", this.updateDimensions);
  }

  render() {
    if (this.state.width <= 790) {
      return (
        <div className="Login">
          <img src={Logo} alt="logo" />
          <h1 className="mt-5 text-info">Access.md</h1>
          <h2 className="text-info">Sign In</h2>
          <SignForm history={this.props.history} />
        </div>
      );
    } else {
      return (
        <div>
          <Row className="m-0">
            <div className=" col-4 vh-100 bg-info">
              <div className="sidebar text-white text-center ">
                <h1>ACCESS.MD</h1>
                <img src={Logo} alt="logo"></img>
              </div>
            </div>
            <div className="col-8">
              <div className="w-50 log ml-5">
                <SignForm history={this.props.history} />
              </div>
            </div>
          </Row>
        </div>
      );
    }
  }
}

export default Signin;

class SignForm extends Component {
  constructor(props) {
    super(props);
    this.state = { username: "", password: "" };
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }
  handleSubmit(e) {
    e.preventDefault();
    console.log(this.state.username, this.state.password, "Hello");
    api.login(this.state.username, this.state.password).then((json) => {
      sessionStorage.setItem("role", json.userRole);
      let fullName = json.firstName + " " + json.lastName;
      if (json.userRole === "ROLE_DOCTOR") {
        sessionStorage.setItem("auth", true);
        loadIcpc();
        sessionStorage.setItem("name", fullName);
        this.setState({ username: "", password: "" });
        this.props.history.push("/home");
      } else if (json.userRole === "ROLE_ADMIN") {
        sessionStorage.setItem("auth", true);
        sessionStorage.setItem("name", fullName);
        this.setState({ username: "", password: "" });
        this.props.history.push("/admin");
      } else {
        sessionStorage.setItem("auth", false);
        alert("You do not have the authorization for this page");
      }
    });
  }
  handleChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  render() {
    return (
      <Form onSubmit={this.handleSubmit}>
        <Form.Group controlId="formBasicEmail">
          <Form.Label>Username</Form.Label>
          <Form.Control
            name="username"
            value={this.state.username}
            type="text"
            onChange={this.handleChange}
            placeholder="Username"
          />
        </Form.Group>
        <Form.Group controlId="formBasicPassword">
          <Form.Label>Password</Form.Label>
          <Form.Control
            name="password"
            value={this.state.password}
            type="password"
            onChange={this.handleChange}
            placeholder="Password"
          />
        </Form.Group>
        <Form.Control.Feedback type="invalid">
          Incorrect Username or Password.
        </Form.Control.Feedback>
        <Button type="submit" className="btn btn-info" variant="info">
          Login
        </Button>
      </Form>
    );
  }
}
