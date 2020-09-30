<template id="patient-questions-list">
    <div class="row d-block">
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
                    <patient-questions-list-item v-bind:patient-id="patientId" v-bind:question="question"></patient-questions-list-item>
                </template>
                </tbody>
            </table>
        </div>
    </div>
</template>
<template id="patient-questions-list-item">
    <tr >
        <th scope="row" class="text-left"></th>
        <td class="text-center"></td>
        <td class="text-center"></td>
        <td class="text-right">
            <a class="btn btn-primary" v-bind:href="questionLink">
                <i class="fa fa-pencil" ></i>
            </a>
        </td>
    </tr>
</template>
<script>
    Vue.component("patient-questions-list-item", {
        template: "#patient-questions-list-item",
        props: ["question", "index", "patientId"],
        computed: {
            questionLink: function () {
                return "/patient/" + this.patientId + "/question/" + this.question.id;
            }

        }
    });
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
            }
        },
        computed: {
            addQuestionLink: function () {
                return "/patient/" + this.patientId + "/question/new"
            }
        }

    });
</script>