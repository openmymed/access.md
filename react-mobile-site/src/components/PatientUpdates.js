import React, { Component } from "react";
import Modal from "./Modal";

class PatientUpdates extends Component {
  constructor() {
    super();
    this.state = {
      feeds: [],
    };
    this.getDoctorFeed = this.getDoctorFeed.bind(this);
  }

  componentDidMount() {
    this.getDoctorFeed();
  }

  getDoctorFeed() {
    //change this with api fetch
    this.setState({
      feeds: [
        {
          id: 1,
          name: "Majed Nuss",
          type: "Answer",
          date: "4/27/2020 @ 5:49:07 PM",
          question: "Question : Do you have fever?",
        },
        {
          id: 2,
          name: "Ahmad Nuss",
          type: "Answer",
          date: "4/27/2020 @ 5:49:07 PM",
          question: "Question : Do you have fever?",
        },
        {
          id: 2,
          name: "Ahmad Nuss",
          type: "Answer",
          date: "4/27/2020 @ 5:49:07 PM",
          question: "Question : Do you have fever?",
        },
      ],
    });
  }

  render() {
    return (
      <div className="col-sm-12 mt-4">
        <h5>Last Activities</h5>
        <div className="mt-4">
          <table className="table">
            <thead className="bg-info text-white">
              <th></th>
              <th scope="col">Patient Name</th>
              <th scope="col">Update Type</th>
            </thead>
            {this.state.feeds.map((feed) => (
              <Modal>{feed}</Modal>
            ))}
          </table>
        </div>
      </div>
    );
  }
}

export default PatientUpdates;
