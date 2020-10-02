<template id="patient-profile">
    <doctor-layout>
        <h4 class="ml-3">Patient Profile</h4>
        <div class="row m-3 bg-light">
            <patient-vitals v-bind:patient-id="patientId"></patient-vitals>
        </div>
        <div class="row  m-3 bg-light">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-3 p-0 ">
                        <patient-medical-profile v-bind:patient-id="patientId"></patient-medical-profile>
                    </div>
                    <div class="col-lg-9 p-0">
                        <div class="row">
                            <div class="col-lg-6 p-0">
                                <patient-symptoms v-bind:patient-id="patientId"></patient-symptoms>
                            </div>
                            <div class="col-lg-6 p-0">
                                <patient-answers v-bind:patient-id="patientId"></patient-answers>
                            </div>
                        </div>
                    </div>
                    <label this="patientLabel"></label>
                </div>
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
