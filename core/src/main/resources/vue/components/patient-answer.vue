<template id="patient-answer">
    <div class="row">
        <div class="answer-card card card-common rounded-lg w-100">
            <div class="card-header d-flex justify-content-start">
                <h6>{{answer.question.question}}</h6>
            </div>
            <div class="card-body w-100">
                <div class="answer-note">{{answer.answer}}</div>
                <p>
                <div class="d-flex justify-content-between w-100">
                    <div>
                        <div>{{answer.creationDate}}</div>
                        <div class="d-flex">
                            <i class="fa fa-clock"></i>&nbsp;
                            <div>{{answer.creationDate}}</div>
                        </div>
                    </div>
                    <div>
                        <button v-on:click="dismiss" class="btn btn-danger">Dismiss</button>
                    </div>
                </div>
                </p>
            </div>
        </div>
    </div>
</template>
<script>
    Vue.component("patient-answer", {
        template: "patient-answer",
        props: ["answer"],
        methods: {
            dismiss: function () {
                window.apiService.archiveAnswer(this.answer.patientId, this.answer.id).then((res) => {
                    this.$emit("dismiss");
                });
            }
        }
    })
</script>
