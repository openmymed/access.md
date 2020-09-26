<template id="patient-medical-profile">
    <div>
        <patient-personal-info v-bind:personal-data="personalData"></patient-personal-info>
        <patient-medical-info v-bind:medical-data="medicalData"></patient-medical-info>
        <patient-medical-flags v-bind:medical-flags="medicalData"></patient-medical-flags>
        <patient-medications v-bind:medications="medications"></patient-medications>
    </div>
</template>
<script>
    Vue.component("patient-medical-profile", {
        template: "#patient-medical-profile",
        props: ["patientId"],
        data: () => ({
                medications: [],
                medicalData: {},
                personalData: {}
            }),
        created() {
            window.apiService.getPatientMedicalProfile(this.patientId).then((json) => {
                this.medicalData = json.profile;
                this.medications = json.profile.medications;

            });

            window.apiService.getPatientPersonalProfile(this.patientId).then((json) => {
                this.personalData = json;
                this.personalData.age = json.profile.age;
            });
        }
    })
</script>
