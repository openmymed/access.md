import React, { Component } from "react";
import { Collapse } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCaretDown, faCaretUp } from "@fortawesome/free-solid-svg-icons";
import { Chart } from "chart.js";
import "moment";
import "chartjs-adapter-moment";

class PatientVitals extends Component {
  constructor(props) {
    super(props);
    this.state = { open: false, icon: faCaretDown };
    this.setOpen = this.setOpen.bind(this);
    this.getVitals = this.getVitals.bind(this);
    this.createBloodPressureGraph = this.createBloodPressureGraph.bind(this);
    this.createGraph = this.createGraph.bind(this);
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

  render() {
    return (
      <div className="col-12 p-0">
        <div
          className="d-flex bg-info mx-auto px-2 py-1"
          onClick={() => this.setOpen(!this.state.open)}
        >
          <FontAwesomeIcon
            icon={this.state.icon}
            className="text-white align-bottom"
          />
          <h5
            className="w-100 m-0 ml-2 text-white"
            aria-expanded={this.state.open}
            aria-controls="patientVitals"
          >
            Vitals
          </h5>
        </div>
        <div className="row p-2">
          <Collapse
            in={this.state.open}
            className="container-fluid"
            id="patientVitals"
          >
            <div className="col-12">
              <div className="row">
                <div className="col-6 oxygenation-chart">
                  <canvas this="oxygenationChart"></canvas>
                </div>
                <div className="col-6 heart-beats-chart ">
                  <canvas this="heartBeatsChart"></canvas>
                </div>
              </div>
              <div className="row">
                <div className="col-6 breathing-rate-chart">
                  <canvas this="breathignRateChart"></canvas>
                </div>
                <div className="col-6">
                  <div className="row blood-pressure-chart">
                    <canvas this="bloodPressureChart"></canvas>
                  </div>
                </div>
              </div>
            </div>
          </Collapse>
        </div>
      </div>
    );
  }

  getVitals() {
    //     api.getPatientVitals(this.props.patientId).then((data) => {
    //       this.data = data;
    //       data.forEach((item) => {
    //         this.oxygenationData.push({
    //           x: item.creationDate,
    //           y: item.bloodOxygenation,
    //           label: item.creationDate,
    //         });
    //         this.heartBeatsPerMinuteData.push({
    //           x: item.creationDate,
    //           y: item.heartBeatsPerMinute,
    //           label: item.creationDate,
    //         });
    //         this.systolicData.push({
    //           x: item.creationDate,
    //           y: item.systolicPressure,
    //           label: item.creationDate,
    //         });
    //         this.diastolicData.push({
    //           x: item.creationDate,
    //           y: item.diastolicPressure,
    //           label: item.creationDate,
    //         });
    //         this.breathingRateData.push({
    //           x: item.creationDate,
    //           y: item.breathingRate,
    //           label: item.creationDate,
    //         });
    //       });
    //       this.createGraph(
    //         this.oxygenationChart,
    //         this.state.oxygenationData,
    //         "Blood Oxygenation",
    //         "Reading Time",
    //         "SpO2"
    //       );
    //       this.createGraph(
    //         this.heartBeatsChart,
    //         this.state.heartBeatsPerMinuteData,
    //         "Beats Per Minute",
    //         "Reading Time",
    //         "BPM"
    //       );
    //       this.createGraph(
    //         this.breathignRateChart,
    //         this.state.breathingRateData,
    //         "Breathing Rate",
    //         "Reading Time",
    //         "Breathing Rate"
    //       );
    //       this.createBloodPressureGraph(
    //         this.bloodPressureChart,
    //         this.systolicData,
    //         this.state.diastolicData
    //       );
    //     });
    //   }
  }

  createBloodPressureGraph(canvas, diastolicData, systolicData) {
    canvas.width = canvas.parentNode.offsetWidth;
    canvas.height = canvas.parentNode.offsetHeight;
    new Chart(canvas.getContext("2d"), {
      type: "scatter",
      data: {
        datasets: [
          {
            label: "Systolic",
            borderColor: "#ff0000",
            data: systolicData,
            fill: false,
            showLine: true,
            lineTension: 0,
          },
          {
            label: "Diastolic",
            borderColor: "#ff0000",
            data: diastolicData,
            fill: false,
            showLine: true,
            lineTension: 0,
          },
        ],
      },
      options: {
        responsive: true,
        title: {
          display: true,
          text: "Blood Pressure",
        },
        scales: {
          xAxes: [
            {
              type: "time",
              time: {
                displayFormats: {
                  hour: "MMM D hA",
                },
                unit: "hour",
              },
            },
          ],
          x: {
            display: true,
            scaleLabel: {
              display: true,
              labelString: "Reading Time",
            },
          },
          y: {
            display: true,
            scaleLabel: {
              display: true,
              labelString: "mmHg",
            },
          },
        },
      },
    });
  }
  createGraph(canvas, dataset, name, xLabel, yLabel) {
    canvas.width = canvas.parentNode.offsetWidth;
    canvas.height = canvas.parentNode.offsetHeight;
    new Chart(canvas.getContext("2d"), {
      type: "scatter",
      data: {
        datasets: [
          {
            label: name,
            borderColor: "#ff0000",
            data: dataset,
            fill: false,
            showLine: true,
            lineTension: 0,
          },
        ],
      },
      options: {
        responsive: true,
        title: {
          display: true,
          text: name,
        },
        scales: {
          xAxes: [
            {
              type: "time",
              time: {
                displayFormats: {
                  hour: "MMM D hA",
                },
                unit: "hour",
              },
            },
          ],
          x: {
            display: true,
            scaleLabel: {
              display: true,
              labelString: xLabel,
            },
          },
          y: {
            display: true,
            scaleLabel: {
              display: true,
              labelString: yLabel,
            },
          },
        },
      },
    });
  }
}
export default PatientVitals;
