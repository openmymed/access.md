<template id="edit-patient-question">
    <doctor-layout>
        <patient-question v-bind:patient-id="patientId" v-bind:question-id="questionId"></patient-question>
    </doctor-layout>
</template>
<script>
    Vue.component('edit-patient-question', {
        template: "#edit-patient-question",
        data: () => ({
                patientId: null,
                questionId: null
            }),
        created() {
            this.patientId = this.$javalin.pathParams['patientId'];
            this.questionId = this.$javalin.pathParams['questionId'];
        }
    })
</script>