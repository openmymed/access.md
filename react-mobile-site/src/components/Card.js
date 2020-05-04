import React, { Component } from "react";
import "../App.css";

class Card extends Component {
  render() {
    return (
      <div class="col-lg-4 col-sm-12 p-2">
        <div class="card card-common rounded-lg bg-white border border-2 border-info">
          <div class="card-body">
            <div class="d-flex justify-content-center">
              <div class="flex-column">
                <h6 class="font-weight-bold">{this.props.title}</h6>
                <h1 class="pt-2 text-info font-weight-bold">
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
