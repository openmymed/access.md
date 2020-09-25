<template id="patient-profile">
    <doctor-layout>
    <h4 class="ml-3">Patient Profile</h4>
    <div class="row m-3 bg-light">
        <PatientVitals this="patientVitals"/>
    </div>
    <div class="row m-3 border bg-light">
        <MedicalProfile this="medicalProfile" />
        <div class="col-lg-9 bg-light">
            <div class="row">
                <patient-symptoms v-bind:patient-id="patientId"></patient-symptoms>
                <patient-answers v-bind:patient-id="patientId"></patient-answers>
            </div>
        </div>
        <label this="patientLabel"></label>
    </div>
    </doctor-layout>
</template>
<script>
    Vue.component('patient-profile', {
        template: "#patient-profile",
        data: () => ({
                patientId: null
            }),
        created() {
            this.patientId = this.$javalin.pathParams['patientId']
        }
    })
</script>
