import React, { Component } from "react";
import { Carousel, Row } from "react-bootstrap";
import * as api from "../utils/api";
import "./../App.css";
import Card from "./Card";

class Overview extends Component {
  constructor(props) {
    super(props);
    this.state = {
      width: window.innerWidth,
      totalPatients: "0",
      totalSymptoms: "0",
      totalAnswers: "0",
    };
    this._updateTotalAnswers = this._updateTotalAnswers.bind(this);
    this._updateTotalSymptoms = this._updateTotalSymptoms.bind(this);
    this._updateTotalPatients = this._updateTotalPatients.bind(this);
  }
  updateDimensions = () => {
    this.setState({ width: window.innerWidth });
  };
  componentDidMount() {
    window.addEventListener("resize", this.updateDimensions);

    //uncomment these when api is ready
    // this._updateTotalAnswers();
    // this._updateTotalSymptoms();
    // this._updateTotalPatients();
  }
  componentWillUnmount() {
    window.removeEventListener("resize", this.updateDimensions);
  }

  render() {
    if (this.state.width <= 992) {
      return (
        <Carousel>
          <Carousel.Item>
            <Card
              title="Total Patients"
              number={this.state.totalPatients}
            ></Card>
          </Carousel.Item>
          <Carousel.Item>
            <Card
              title="Unseen Symptoms"
              number={this.state.totalSymptoms}
            ></Card>
          </Carousel.Item>
          <Carousel.Item>
            <Card
              title="Unseen Answers"
              number={this.state.totalAnswers}
            ></Card>
          </Carousel.Item>
        </Carousel>
      );
    } else {
      return (
        <Row className="mt-4 mx-0">
          <div className="col-lg-12">
            <div className="ml-5 row pt-md-2">
              <Card
                title="Total Patients"
                number={this.state.totalPatients}
              ></Card>
              <Card
                title="Unseen Symptoms"
                number={this.state.totalSymptoms}
              ></Card>
              <Card
                title="Unseen Answers"
                number={this.state.totalAnswers}
              ></Card>
            </div>
          </div>
        </Row>
      );
    }
  }

  _updateTotalPatients() {
    api.getPatientsCount().then((json) => {
      this.setState({
        totalPatients: json.count,
      });
    });
  }

  _updateTotalSymptoms() {
    api.getUarchivedSymptomsCount().then((json) => {
      this.setState({
        totalSymptoms: json.count,
      });
    });
  }

  _updateTotalAnswers() {
    api.getUnarchivedAnswersCount().then((json) => {
      this.setState({
        totalAnswers: json.count,
      });
    });
  }
}
export default Overview;
