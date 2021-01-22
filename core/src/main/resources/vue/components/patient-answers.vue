<template id="patient-answers">
    <div class="container-fluid">
            <div class="col-12 d-flex justify-content-between">
                <h5 class="py-3 text-info font-weight-bold">Patient Answers</h5>
                <div class="py-2">
                    <a v-bind:href="askLink" class="btn btn-primary">Questions</a>
                </div>
            </div>
            <div class="col-12">
                <div v-if="answers && answers.length>0" class="container-fluid">
                    <template v-for="(answer, index) in answers" :key="index">
                        <patient-answer v-bind:answer="answer" v-on:dismiss="dismiss(index)" ></patient-answer>
                    </template>
                </div>
            </div>
        </div>
</template>
<script>
    Vue.component('patient-answers', {
        template: "#patient-answers",
        props: ["patientId"],
        data: () => ({
                answers: []
            }),
        computed: {
            askLink: function () {
                return "/patient/" + this.patientId + "/question"
            }
        },
        methods: {
            dismiss: function (index) {
                this.answers.splice(index, 1);
            }
        },
        created() {
            window.apiService.getPatientAnswers(this.patientId).then((data) => {
                this.answers = data;
            });
        }
    })
</script>
