import React, { Component } from "react";
import { Link } from "react-router-dom";
import Sidebar from "react-sidebar";

const mql = window.matchMedia(`(min-width: 800px)`);
class Navbar extends Component {
  constructor(props) {
    super(props);
    this.state = {
      fullname: sessionStorage.getItem("name"),
      fullname: "Majed Nuseibeh",
      title: this.props.title,
      sidebarDocked: mql.matches,
      sidebarOpen: false,
    };
    this.mediaQueryChanged = this.mediaQueryChanged.bind(this);
    this.onSetSidebarOpen = this.onSetSidebarOpen.bind(this);
  }

  onSetSidebarOpen(open) {
    this.setState({ sidebarOpen: open });
  }

  mediaQueryChanged() {
    this.setState({ sidebarDocked: mql.matches, sidebarOpen: false });
  }

  render() {
    return (
      <Sidebar
        sidebar={
          <div className="text-white pt-4 pl-4 pr-5">
            <h6>Dr. {this.state.fullname}</h6>
            <ul className="navbar-nav mt-5">
              <li className="nav-item">
                <Link className="text-white nav-link" to="/home">
                  Home
                </Link>
              </li>
              <li className="nav-item">
                <Link className="text-white nav-link" to="/patients">
                  Patient List
                </Link>
              </li>
              <li className="nav-item">
                <Link className="text-white nav-link" to="/">
                  Logout
                </Link>
              </li>
            </ul>
          </div>
        }
        open={this.state.sidebarOpen}
        docked={this.state.sidebarDocked}
        onSetOpen={this.onSetSidebarOpen}
        styles={{ sidebar: { background: "#158498" } }}
      >
        <button
          className="navbar-light navbar-toggler"
          onClick={() => this.onSetSidebarOpen(true)}
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <b>{this.state.title}</b>
        {this.props.children}
      </Sidebar>
    );
  }
}
export default Navbar;
