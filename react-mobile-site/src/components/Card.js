import React, { Component } from "react";
import "../App.css";

class Card extends Component {
  render() {
    return (
      <div className="col-lg-4 col-sm-12 p-2 text-center">
        <div className="card card-common rounded-lg bg-white border border-2 border-info">
          <div className="card-body">
            <div className="d-flex justify-content-center">
              <div className="flex-column">
                <h6 className="font-weight-bold">{this.props.title}</h6>
                <h1 className="pt-2 text-info font-weight-bold">
                  {this.props.number}
                </h1>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
export default Card;
