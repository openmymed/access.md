<template id="patient-question">
    <div class="container-fluid">
        <div class="row">
            <div class="col-12 form-group">
                <label for="question-text">Question</label>
                <input class="form-control" type="text" v-model="question.question"></input>
            </div>
        </div>
        <div class="row">
            <div class="col-6 form-group">
                <label for="question-type">Question Type</label>
                <select v-model="question.type" id="question-type" class="form-control">
                    <option value="" selected="true">Please Select</option>
                    <option value="Scale" >Scale</option>
                    <option value="Binary" >Binary</option>
                    <option value="Text" >Text</option>
                    <option value="Vitals">Vitals</option>
                </select>
            </div>
            <div class="col-6 form-group" >
                <label></label>
                <div class="d-flex justify-content-between mt-3">
                    <label class="checkbox-inline" for="question-type">Recurring?</label>
                    <input type="checkbox" v-model="question.recurring" id="recurring"></input>
                </div>
            </div>
        </div>

        <div v-if="question.recurring">
            <div class="row">
                <div class="col-6 form-group">
                    <label for="from-date-input">From Date</label>
                    <input v-model="question.startDate" id="from-date-input" type="text" class="form-control"/>
                </div>
                <div class="col-6 form-group">
                    <label for="to-date-input">To Date</label>
                    <input v-model="question.endDate" id="to-date-input" type="text" class="form-control"/>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <table class="table">
                        <thead>
                        <th scope="col" class="text-left">
                            #
                        </th>
                        <th scope="col" class="text-center">
                            Recurrence Time
                        </th>
                        <th scope="col" class="text-right">
                        </th>
                        </thead>
                        <tbody>
                        <template v-for="(recurrance,index) in recurrances" :key="index">
                            <patient-question-recurrance v-bind:recurrance="recurrance" v-bind:index="index" v-on:delete-recurrance="removeRecurrance" v-on:update-recurrance="updateRecurrance"></patient-question-recurrance>
                        </template>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="row">
                <div class="col-12 d-flex justify-content-end form-group">
                    <button v-on:click="addRecurrance" class="btn bt-primary btn-sm">
                        <i class="fa fa-plus"></i>
                    </button>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-12 form-group">
                <label></label>
                <button class="btn btn-primary" v-on:click="saveQuestion" >Save</button>
            </div>
        </div>
    </div>
</template>
<script>
    Vue.component("patient-question", {
        template: "#patient-question",
        data: () => ({
                question: {},
                recurrances: []
            }),
        props: ["questionId", "patientId"],
        created() {
            if (this.questionId) {
                loadQuestion();
            }
        },
        methods: {
            loadQuestion: function () {
                window.apiService.getPatientQuestion(this.patientId, this.questionId).then(data => {
                    this.question = data;
                    if (data.recurrance) {
                        this.recurrances = data.recurrance;
                    }
                })
            },
            saveQuestion: function () {
                if (this.questionId) {
                    window.apiService.updateQuestion(this.patientId, this.questionId, this.question);
                } else {
                    window.apiService.createQuestion(this.patientId, this.question);
                }
            },
            addRecurrance: function () {
                this.recurrances.push({});
            },
            removeRecurrance: function (data) {
                this.recurrances.splice(data.index, 1);
            },
            updateRecurrance: function (data) {
                this.recurrances[data.index] = data.recurrance;
            }

        }
    });

</script>