<template id="patient-questions-list">
    <div class="container-fluid">
        <div class="col-12 d-flex justify-content-between">
            <h4 class="ml-4">Questions</h4>
            <a class="btn btn-primary btn-sm" v-bind:href="addQuestionLink">
                <i class="fa fa-plus"></i>&nbsp; Add Question
            </a>
        </div>
        <div class="col-12">
            <table class="table" this="table">
                <thead>
                <th scope="col" class="text-left">
                    Question
                </th>
                <th scope="col" class="text-center">
                    Type
                </th>
                <th scope="col" class="text-center">
                    Repetition
                </th>
                <th scope="col" class="text-right">

                </th>
                </thead>
                <tbody v-if="questions && questions.length>0">
                <template v-for="(question, index) in questions">
                    <tr >
                        <th scope="row" class="text-left">{{question.question}}</th>
                        <td class="text-center">{{question.type}}</td>
                        <td class="text-center">{{question.recurring}}</td>
                        <td class="text-right">
                            <a class="btn btn-primary" :href="getQuestionLink(question.id)">
                                <i class="fa fa-pencil" ></i>&nbsp;Edit
                            </a>
                        </td>
                    </tr>
                </template>
                </tbody>
            </table>
        </div>
    </div>
</template>
<template id="patient-questions-list-item">

</template>
<script>
    Vue.component("patient-questions-list", {
        template: "#patient-questions-list",
        props: ["patientId"],
        data: () => ({
                questions: []
            }),
        created() {
            this.loadQuestions();
        },
        methods: {
            loadQuestions: function () {
                window.apiService.getPatientQuestions(this.patientId).then(res => {
                    this.questions = res;
                });
            },
            getQuestionLink: function (questionId) {
                return "/patient/" + this.patientId + "/question/" + questionId;
            }
        },
        computed: {
            addQuestionLink: function () {
                return "/patient/" + this.patientId + "/question/new"
            }
        }

    });
</script>