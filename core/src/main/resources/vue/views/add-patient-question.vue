<template id="add-patient-question">
    <doctor-layout>
        <patient-question v-bind:patient-id="patientId"></patient-question>
    </doctor-layout>
</template>
<script>
    Vue.component('add-patient-question', {
        template: "#add-patient-question",
        data: () => ({
                patientId: null
            }),
        created() {
            this.patientId = this.$javalin.pathParams['patientId'];
        }
    })
</script>
