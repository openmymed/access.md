import React, { Component } from "react";
import { Link } from "react-router-dom";
import { Button } from "react-bootstrap";
import AddPatient from "./AddPatient";
import Sidebar from "react-sidebar";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faHome,
  faSignOutAlt,
  faUser,
} from "@fortawesome/free-solid-svg-icons";

const mql = window.matchMedia(`(min-width: 800px)`);
class Navbar extends Component {
  constructor(props) {
    super(props);
    this.state = {
      showModal: false,
      fullname: sessionStorage.getItem("name"),
      fullname: "Majed Nuseibeh",
      sidebarDocked: mql.matches,
      sidebarOpen: false,
      width: window.innerWidth,
    };
    this.mediaQueryChanged = this.mediaQueryChanged.bind(this);
    this.onSetSidebarOpen = this.onSetSidebarOpen.bind(this);
  }

  updateDimensions = () => {
    if (window.innerWidth <= 650) {
      this.setState({ width: window.innerWidth, sidebarDocked: false });
    } else {
      this.setState({ width: window.innerWidth, sidebarDocked: true });
    }
  };
  componentDidMount() {
    window.addEventListener("resize", this.updateDimensions);
  }
  componentWillUnmount() {
    window.removeEventListener("resize", this.updateDimensions);
  }

  handleModalClose() {
    this.setState({ showModal: false });
  }

  onShowModal() {
    this.setState({ sidebarOpen: false, showModal: true });
  }

  onSetSidebarOpen(open) {
    this.setState({ sidebarOpen: open });
  }

  mediaQueryChanged() {
    this.setState({ sidebarDocked: mql.matches, sidebarOpen: false });
  }

  render() {
    if (this.state.width <= 650) {
      return (
        <Sidebar
          sidebar={
            <div>
              {/* {this.ModalRef} */}
              <h5 className="text-white bg-info py-4 pl-4 pr-5">
                Dr. {this.state.fullname}
              </h5>
              <div className="text-left text-white pl-4 pr-5">
                <ul className="navbar-nav mt-5">
                  <li className="d-block nav-item">
                    <Link className=" text-info nav-link" to="/home">
                      <FontAwesomeIcon className="mr-1" icon={faHome} />
                      Home
                    </Link>
                  </li>
                  <li className="nav-item">
                    <Link className="text-info nav-link" to="/patients">
                      <FontAwesomeIcon className="mr-1" icon={faUser} />
                      My Patients
                    </Link>
                  </li>
                  <li className="nav-item">
                    <Link className="text-info nav-link" to="/">
                      <FontAwesomeIcon className="mr-1" icon={faSignOutAlt} />
                      Logout
                    </Link>
                  </li>
                </ul>
              </div>
              <Button
                className="ml-4 mt-5"
                onClick={this.onShowModal.bind(this)}
              >
                Add Patient
              </Button>
              <AddPatient
                onClose={this.handleModalClose.bind(this)}
                show={this.state.showModal}
              />
            </div>
          }
          open={this.state.sidebarOpen}
          docked={this.state.sidebarDocked}
          onSetOpen={this.onSetSidebarOpen}
          styles={{ sidebar: { zIndex: 9998, background: "white" } }}
        >
          <div className="d-flex justify-content-between bg-info">
            <Button
              className="navbar-dark navbar-toggler"
              onClick={() => this.onSetSidebarOpen(true)}
            >
              <span className="navbar-toggler-icon"></span>
            </Button>
            <h4 className="mt-2 text-white">Access.md</h4>
            {/* this 2 paragraph tags are used to give space to the title */}
            <p></p>
            <p></p>
          </div>
          {this.props.children}
        </Sidebar>
      );
    } else {
      return (
        <Sidebar
          sidebar={
            <div>
              {/* {this.ModalRef} */}
              <h5 className="text-white bg-info py-4 pl-4 pr-5">
                Dr. {this.state.fullname}
              </h5>
              <div className="text-left text-white pl-4 pr-5">
                <ul className="navbar-nav mt-5">
                  <li className="d-block nav-item">
                    <Link className=" text-info nav-link" to="/home">
                      <FontAwesomeIcon className="mr-1" icon={faHome} />
                      Home
                    </Link>
                  </li>
                  <li className="nav-item">
                    <Link className="text-info nav-link" to="/patients">
                      <FontAwesomeIcon className="mr-1" icon={faUser} />
                      My Patients
                    </Link>
                  </li>
                  <li className="nav-item">
                    <Link className="text-info nav-link" to="/">
                      <FontAwesomeIcon className="mr-1" icon={faSignOutAlt} />
                      Logout
                    </Link>
                  </li>
                </ul>
              </div>
              <Button
                className="ml-4 mt-5"
                onClick={this.onShowModal.bind(this)}
              >
                Add Patient
              </Button>
              <AddPatient
                onClose={this.handleModalClose.bind(this)}
                show={this.state.showModal}
              />
            </div>
          }
          open={this.state.sidebarOpen}
          docked={this.state.sidebarDocked}
          onSetOpen={this.onSetSidebarOpen}
          styles={{ sidebar: { zIndex: 9999, background: "white" } }}
        >
          <div className="d-flex justify-content-center border-bottom">
            <h4 className="mt-2 text-info font-weight-bold">Access.md</h4>
          </div>
          {this.props.children}
        </Sidebar>
      );
    }
  }
}
export default Navbar;
