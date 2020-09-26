<template id="patient-vitals">
    <div class="d-block w-100">
        <div class="row patient-vitals-expander mx-auto p-2 mt-1" >                 
            <h5 class="d-block w-100 m-0" data-toggle="collapse" href="#patientVitals" role="button" aria-expanded="false" aria-controls="patientVitals"> <i class="fa fa-angle-down"></i>&nbsp;Vitals</h5>
        </div>
        <div class="row p-2" >        
            <div class="collapse container-fluid" id="patientVitals">
                <div class="col-12">
                    <div class="row">
                        <div class="col-6 oxygenation-chart">
                            <canvas ref="oxygenationChart"></canvas>
                        </div>
                        <div class="col-6 heart-beats-chart ">
                            <canvas ref="heartBeatsChart"></canvas>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-6 breathing-rate-chart">
                            <canvas ref="breathignRateChart"></canvas>
                        </div>
                        <div class="col-6">
                            <div class="row blood-pressure-chart">
                                <canvas ref="bloodPressureChart"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    Vue.component('patient-vitals', {
        template: "#patient-vitals",
        props: ["patientId"],
        methods: {
            createBloodPressureGraph: function (canvas, diastolicData, systolicData) {
                canvas.width = canvas.parentNode.offsetWidth;
                canvas.height = canvas.parentNode.offsetHeight;
                new Chart(canvas.getContext('2d'), {
                    type: 'scatter',
                    data: {
                        datasets: [
                            {label: "Systolic",
                                borderColor: "#ff0000",
                                data: systolicData,
                                fill: false,
                                showLine: true,
                                lineTension: 0
                            },
                            {
                                label: "Diastolic",
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
                                        displayFormats: {
                                            hour: 'MMM D hA'
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
            },
            createGraph: function (canvas, dataset, name, xLabel, yLabel, ) {
                canvas.width = canvas.parentNode.offsetWidth;
                canvas.height = canvas.parentNode.offsetHeight;
                new Chart(canvas.getContext('2d'), {
                    type: 'scatter',
                    data: {
                        datasets: [
                            {
                                label: name,
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
                                        displayFormats: {
                                            hour: 'MMM D hA'
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
        },
        created() {
            let oxygenationData = [];
            let heartBeatsPerMinuteData = [];
            let systolicData = [];
            let diastolicData = [];
            let breathingRateData = [];
            window.apiService.getPatientVitals(this.patientId).then((data) => {
                data.forEach((item) => {
                    this.oxygenationData.push({x: item.creationDate, y: item.bloodOxygenation, label: item.creationDate});
                    this.heartBeatsPerMinuteData.push({x: item.creationDate, y: item.heartBeatsPerMinute, label: item.creationDate});
                    this.systolicData.push({x: item.creationDate, y: item.systolicPressure, label: item.creationDate});
                    this.diastolicData.push({x: item.creationDate, y: item.diastolicPressure, label: item.creationDate});
                    this.breathingRateData.push({x: item.creationDate, y: item.breathingRate, label: item.creationDate});
                });
                this.createGraph(this.$refs.oxygenationChart, oxygenationData, "Blood Oxygenation", "Reading Time", "SpO2");
                this.createGraph(this.$refs.heartBeatsChart, heartBeatsPerMinuteData, "Beats Per Minute", "Reading Time", "BPM");
                this.createGraph(this.$refs.breathignRateChart, breathingRateData, "Breathing Rate", "Reading Time", "Breathing Rate");
                this.createBloodPressureGraph(this.$refs.bloodPressureChart, systolicData, diastolicData);
            });
        }
    });
</script>
