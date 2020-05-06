import React, { Component } from "react";
import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";

//this class is used for pc window..
class Feed extends Component {
  constructor(props) {
    super(props);
    this.state = { feed: this.props.children };
  }

  render() {
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
            <Link to="/patient" className="btn btn-primary btn-sm">
              Profile
            </Link>
          </td>
        </tr>
      </tbody>
    );
  }
}
export default Feed;
