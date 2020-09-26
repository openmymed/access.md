<template id="patient-profile">
    <doctor-layout>
        <h4 class="ml-3">Patient Profile</h4>
        <div class="row m-3 bg-light">
            
        </div>
        <div class="row m-3 border bg-light">
            <div this="el" class="col-lg-3 p-0 border-right">
                <patient-medical-profile v-bind:patient-id="patientId"></patient-medical-profile>
            </div>
            <div class="col-lg-9 bg-light">
                <div class="row">
                    <div this="el" class="col-lg-6">
                        <patient-symptoms v-bind:patient-id="patientId"></patient-symptoms>
                    </div>
                    <div class="col-lg-6">
                        <patient-answers v-bind:patient-id="patientId"></patient-answers>
                    </div>
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
