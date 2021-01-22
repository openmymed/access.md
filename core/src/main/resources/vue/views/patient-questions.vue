<template id="patient-questions">
    <doctor-layout>
        <patient-questions-list v-bind:patient-id="patientId"></patient-questions-list>
    </doctor-layout>
</template>
<script>
    Vue.component('patient-questions', {
        template: "#patient-questions",
        data: () => ({
                patientId: null
            }),
        created() {
            this.patientId = this.$javalin.pathParams["patientId"];
        }
    });
</script>