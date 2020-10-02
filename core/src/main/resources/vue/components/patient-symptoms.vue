<template id="patient-symptoms">
    <div class="container-fluid ">
        <div class="col-12">
            <h5 class="p-3 text-info font-weight-bold">Patient Symptoms</h5>
        </div>
        <div class="col-12">
            <div v-if="symptoms && symptoms.length>0"class="container-fluid">
                <template v-for="(symptom,index) in symptoms" :key="index">
                    <patient-symptom v-bind:symptom="symptom" v-on:dismiss="dismiss(index)"></patient-symptom>
                </template>
            </div>
        </div>
    </div>
</template>
<script>
    Vue.component("patient-symptoms", {
        template: "#patient-symptoms",
        props: ["patientId"],
        data: () => ({
                symptoms: []
            }),
        methods: {
            dismiss: function (index) {
                this.symptoms.splice(index, 1);
            }
        },
        created() {
            window.apiService.getPatientSymptoms(this.patientId).then(data => {
                this.symptoms = data;
            })
        }
    })
</script>
