import React, { Component } from "react";
import { Button, Collapse } from "react-bootstrap";
import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCaretDown, faCaretUp } from "@fortawesome/free-solid-svg-icons";

class Accordion extends Component {
  constructor(props) {
    super(props);
    this.state = {
      width: window.innerWidth,
      feed: this.props.children,
      open: false,
      icon: faCaretDown,
    };

    this.setOpen = this.setOpen.bind(this);
  }

  updateDimensions = () => {
    this.setState({ width: window.innerWidth });
  };

  componentWillUnmount() {
    window.removeEventListener("resize", this.updateDimensions);
  }

  componentDidMount() {
    window.addEventListener("resize", this.updateDimensions);
  }

  render() {
    let url = "/patient/" + this.props.children.id;
    if (this.state.width <= 1115) {
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
                      <Link to={url} className="mr-1 btn btn-primary">
                        Profile
                      </Link>
                      <Button
                        variant="danger"
                        onClick={() => this.props.onDelete(this.props.children)}
                      >
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
    } else {
      return (
        <tbody>
          <tr>
            <td className="text-left">{this.state.feed.name}</td>
            <td className="text-center">{this.state.feed.type}</td>
            <td className="text-center">{this.state.feed.date}</td>
            <td className="text-center">{this.state.feed.question}</td>
            <td className="text-right">
              <Button
                className="btn btn-danger btn-sm mr-1"
                onClick={() => this.props.onDelete(this.props.children)}
              >
                Dismiss
              </Button>
              <Link to={url} className="btn btn-primary btn-sm">
                Profile
              </Link>
            </td>
          </tr>
        </tbody>
      );
    }
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
}

export default Accordion;
