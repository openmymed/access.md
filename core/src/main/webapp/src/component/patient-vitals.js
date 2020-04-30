/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import { el, text, mount, list, unmount} from 'redom';
import * as api from  "../utils/api";
import {Chart} from "chart.js"
import 'moment'
import 'chartjs-adapter-moment';
export class PatientVitals {
    constructor(attr, text) {
        <div class="col-12 p-0" this="el">
            <div class="row patient-vitals-expander mx-auto p-2 mt-1" >                 
                <h5 class="d-block w-100 m-0" data-toggle="collapse" href="#patientVitals" role="button" aria-expanded="false" aria-controls="patientVitals"> <i class="fa fa-angle-down"></i>&nbsp;Vitals</h5>
            </div>
            <div class="row p-2" >        
                <div class="collapse container-fluid" id="patientVitals">
                    <div class="col-12">
                        <div class="row">
                            <div class="col-6 oxygenation-chart">
                                <canvas this="oxygenationChart"></canvas>
                            </div>
                            <div class="col-6 heart-beats-chart ">
                                <canvas this="heartBeatsChart"></canvas>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-6 breathing-rate-chart">
                                <canvas this="breathignRateChart"></canvas>
                            </div>
                            <div class="col-6">
                                <div class="row blood-pressure-chart">
                                    <canvas this="bloodPressureChart"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>;
    }

    update(patientId) {
        this.patientId = patientId;
        this.oxygenationData = [];
        this.heartBeatsPerMinuteData = [];
        this.systolicData = [];
        this.diastolicData = [];
        this.breathingRateData = [];
        api.getPatientVitals(patientId).then((data) => {
            this.data = data;
            data.forEach((item) => {
                this.oxygenationData.push({x: item.creationDate, y: item.bloodOxygenation, label: item.creationDate})
                this.heartBeatsPerMinuteData.push({x: item.creationDate, y: item.heartBeatsPerMinute, label: item.creationDate})
                this.systolicData.push({x: item.creationDate, y: item.systolicPressure, label: item.creationDate})
                this.diastolicData.push({x: item.creationDate, y: item.diastolicPressure, label: item.creationDate})
                this.breathingRateData.push({x: item.creationDate, y: item.breathingRate, label: item.creationDate})
            });
            this.createGraph(this.oxygenationChart, this.oxygenationData,"Blood Oxygenation","Reading Time","SpO2");
            this.createGraph(this.heartBeatsChart, this.heartBeatsPerMinuteData,"Beats Per Minute","Reading Time","BPM");
            this.createGraph(this.breathignRateChart, this.breathingRateData,"Breathing Rate","Reading Time","Breathing Rate");
            this.createBloodPressureGraph(this.bloodPressureChart,this.systolicData,this.diastolicData);
        });
    }

    createBloodPressureGraph(canvas, diastolicData, systolicData){
         canvas.width = canvas.parentNode.offsetWidth;
        canvas.height = canvas.parentNode.offsetHeight;
        new Chart(canvas.getContext('2d'), {
            type: 'scatter',
            data: {
                datasets: [
                    {   label:"Systolic",
                        borderColor: "#ff0000",
                        data: systolicData,
                        fill: false,
                        showLine: true,
                        lineTension: 0
                    },
                     {
                        label:"Diastolic",
                        borderColor: "#ff0000",
                        data: diastolicData,
                        fill: false,
                        showLine: true,
                        lineTension: 0
                    }
                ]
            },
            options: {
                responsive: true,
                title: {
                    display: true,
                    text: "Blood Pressure"
                },
                scales: {
                    xAxes: [
                        {type: 'time',
                            time: {
                                displayFormats  :{
                                    hour : 'MMM D hA'
                                },
                                unit: 'hour'
                            }
                        }
                    ],
                    x: {

                        display: true,
                        scaleLabel: {
                            display: true,
                            labelString: "Reading Time"
                        }
                    },
                    y: {
                        display: true,
                        scaleLabel: {
                            display: true,
                            labelString: "mmHg"
                        }
                    }
                }
            }
        });
    }
    createGraph(canvas, dataset, name, xLabel, yLabel, ) {
        canvas.width = canvas.parentNode.offsetWidth;
        canvas.height = canvas.parentNode.offsetHeight;
        new Chart(canvas.getContext('2d'), {
            type: 'scatter',
            data: {
                datasets: [
                    {
                        label:name,
                        borderColor: "#ff0000",
                        data: dataset,
                        fill: false,
                        showLine: true,
                        lineTension: 0
                    }
                ]
            },
            options: {
                responsive: true,
                title: {
                    display: true,
                    text: name
                },
                scales: {
                    xAxes: [
                        {type: 'time',
                            time: {
                                displayFormats  :{
                                    hour : 'MMM D hA'
                                },
                                unit: 'hour'
                            }
                        }
                    ],
                    x: {

                        display: true,
                        scaleLabel: {
                            display: true,
                            labelString: xLabel
                        }
                    },
                    y: {
                        display: true,
                        scaleLabel: {
                            display: true,
                            labelString: yLabel
                        }
                    }
                }
            }
        });
    }
}

