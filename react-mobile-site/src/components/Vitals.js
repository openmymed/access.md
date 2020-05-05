import React, { Component } from "react";
import * as api from "../utils/api";
import { Chart } from "chart.js";
import "moment";
import "chartjs-adapter-moment";

class Vitals extends Component {
  constructor(props) {
    super(props);
    this.state = {
      oxygenationData: [],
      heartBeatsPerMinuteData: [],
      systolicData: [],
      diastolicData: [],
      breathingRateData: [],
    };
    this.getVitals = this.getVitals.bind(this);
    this.createBloodPressureGraph = this.createBloodPressureGraph.bind(this);
    this.createGraph = this.createGraph.bind(this);
  }
  render() {
    return (
      <div className="mt-3 col-12">
        <div className="mt-3 oxygenation-chart">
          <canvas this="oxygenationChart"></canvas>
        </div>
        <div className="mt-3 heart-beats-chart ">
          <canvas this="heartBeatsChart"></canvas>
        </div>

        <div className="mt-3 breathing-rate-chart">
          <canvas this="breathignRateChart"></canvas>
        </div>
        <div className="mt-3">
          <div className="row blood-pressure-chart">
            <canvas this="bloodPressureChart"></canvas>
          </div>
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
export default Vitals;
