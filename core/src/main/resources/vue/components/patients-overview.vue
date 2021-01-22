<template id="patients-overview">
    <div class="container-fluid">
        <h4>Overview</h4>
        <div class="row">
            <div class="col-sm-12 col-xl-11 col-lg-9 col-md-8">
                <div class="ml-5 row pt-md-2">
                    <div class="col-lg-4 col-sm-12 p-2">
                        <card title="Total Patients" v-bind:number="totalPatients"></card>
                    </div>
                    <div class="col-lg-4 col-sm-12 p-2">
                        <card title="Unseen Symptoms" v-bind:number="unseenSymptoms"></card>
                    </div>
                    <div class="col-lg-4 col-sm-12 p-2">
                        <card title="Unseen Answers" v-bind:number="unseenAnswers"></card>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    Vue.component("patients-overview", {
        template: "#patients-overview",
        data: () => ({
                totalPatients: 0,
                unseenSymptoms: 0,
                unseenAnswers: 0
            }),
        methods: {
            refresh: function () {
                window.apiService.getPatientsCount().then((json) => {
                    this.totalPatients = json.count;
                });
                window.apiService.getUnarchivedSymptomsCount().then((json) => {
                    this.unseenSymptoms = json.count;
                });
                window.apiService.getUnarchivedAnswersCount().then((json) => {
                    this.unseenAnswers = json.count;
                });
            }
        },
        created() {
            this.refresh();
        }
    })
</script>