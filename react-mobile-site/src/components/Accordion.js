import React, { Component } from "react";
import { Button, Collapse } from "react-bootstrap";
import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCaretDown, faCaretUp } from "@fortawesome/free-solid-svg-icons";

class Accordion extends Component {
  constructor(props) {
    super(props);
    this.state = {
      feed: this.props.children,
      open: false,
      icon: faCaretDown,
    };

    this.setOpen = this.setOpen.bind(this);
    this.handleClose = this.handleClose.bind(this);
  }

  render() {
    return (
      <tbody>
        <tr
          onClick={() => this.setOpen(!this.state.open)}
          aria-controls="patientActivities"
          aria-expanded={this.state.open}
        >
          <td>
            <FontAwesomeIcon icon={this.state.icon} />
          </td>
          <td>{this.state.feed.name}</td>
          <td>{this.state.feed.type}</td>
        </tr>
        <tr>
          <td colSpan="3" className="p-0">
            <Collapse in={this.state.open}>
              <div id="patientActivities">
                <div className="card card-body">
                  <p>{this.state.feed.name}</p>
                  <p>{this.state.feed.date}</p>
                  <p>{this.state.feed.question}</p>
                  <div className="d-flex">
                    <Link to="/patient" className="mr-1 btn btn-primary">
                      Profile
                    </Link>
                    <Button variant="danger" onClick={this.handleClose}>
                      Close
                    </Button>
                  </div>
                </div>
              </div>
            </Collapse>
          </td>
        </tr>
      </tbody>
    );
  }
  setOpen(value) {
    if (value) {
      this.setState({
        open: value,
        icon: faCaretUp,
      });
    } else {
      this.setState({
        open: value,
        icon: faCaretDown,
      });
    }
  }
  handleClose(e) {
    e.preventDefault();
  }
}
export default Accordion;
