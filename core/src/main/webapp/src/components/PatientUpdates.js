import React, { Component } from "react";
import Accordion from "./Accordion";
import * as api from "../utils/api";

class PatientUpdates extends Component {
  constructor() {
    super();
    this.state = {
      feeds: [],
      width: window.innerWidth,
    };
    this.getDoctorFeed = this.getDoctorFeed.bind(this);
    // this.handleChildUnmount = this.handleChildUnmount.bind(this);
  }

  updateDimensions = () => {
    this.setState({ width: window.innerWidth });
  };

  componentWillUnmount() {
    window.removeEventListener("resize", this.updateDimensions);
  }

  componentDidMount() {
    window.addEventListener("resize", this.updateDimensions);
    this.getDoctorFeed();
  }
  handleChildUnmount = (feedId) => {
    this.setState({
      feeds: [...this.state.feeds.filter((feed) => feed.id !== feedId.id)],
    });
    //this does delete items but not the correct one i dont know why there is problem with setState
    // console.log(feedId);
    // const newFeeds = this.state.feeds.filter((feed) => feed.id !== feedId);
    // console.log(newFeeds);
    // var index = this.state.feeds.indexOf(feedId);
    // this.state.feeds.splice(index, 1);
    // // this.state.feeds.pop(feedId);
    // // this.setState({ feeds: newFeeds });
    // this.setState(this.state.feeds);
  };

  getDoctorFeed() {
    api.getDoctorFeed().then((json) => {
      this.setState({ feeds: json });
    });
    //dummy data
    // this.setState({
    //   feeds: [
    //     {
    //       id: 1,
    //       name: "Majed Nuss",
    //       type: "Answer",
    //       date: "4/27/2020 @ 5:49:07 PM",
    //       question: "Question : Do you have fever?",
    //     },
    //     {
    //       id: 2,
    //       name: "Ahmad Nuss",
    //       type: "Answer",
    //       date: "4/27/2020 @ 5:49:07 PM",
    //       question: "Question : Do you have fever?",
    //     },
    //     {
    //       id: 3,
    //       name: "Ahmad Nuss",
    //       type: "Answer",
    //       date: "4/27/2020 @ 5:49:07 PM",
    //       question: "Question : Do you have fever?",
    //     },
    //   ],
    // });
  }

  render() {
    if (this.state.width <= 1115) {
      return (
        <div className="col-sm-12 mt-4">
          <h5>Last Activities</h5>
          <div className="mt-4">
            <table className="table">
              <thead className="bg-info text-white">
                <tr>
                  <th></th>
                  <th scope="col">Patient Name</th>
                  <th scope="col">Update Type</th>
                </tr>
              </thead>
              {this.state.feeds.map((feed) => (
                <Accordion
                  onDelete={this.handleChildUnmount.bind(this)}
                  key={this.state.feeds.indexOf(feed)}
                >
                  {feed}
                </Accordion>
              ))}
            </table>
          </div>
        </div>
      );
    } else {
      return (
        <div className="col-sm-12 mt-4">
          <h5>Last Activities</h5>
          <div className="mt-4">
            <table className="table">
              <thead className="bg-info text-white">
                <tr>
                  <th scope="col" className="text-left">
                    Patient Name
                  </th>
                  <th scope="col" className="text-center">
                    Update Type
                  </th>
                  <th scope="col" className="text-center">
                    Time
                  </th>
                  <th scope="col" className="text-center">
                    Details
                  </th>
                  <th scope="col" className="text-right"></th>
                </tr>
              </thead>
              {this.state.feeds.map((feed) => (
                <Accordion
                  onDelete={this.handleChildUnmount.bind(this)}
                  key={this.state.feeds.indexOf(feed)}
                >
                  {feed}
                </Accordion>
              ))}
            </table>
          </div>
        </div>
      );
    }
  }
}

export default PatientUpdates;
