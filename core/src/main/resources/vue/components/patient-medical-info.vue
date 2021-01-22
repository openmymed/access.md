<template id="patient-medical-info">
    <div class="container-fluid">
        <h5 class="bg-blue-primary text-white d-block mx-auto p-2 mt-3">
            General Info
        </h5>
        <div class="row text-center p-3">
            <div class="col-4">
                <h6>Sex</h6>
                <h4 class="text-info">{{patientSex}} </h4>
            </div>
            <div class="col-4">
                <h6>Height</h6>
                <div class="d-inline-flex text-info">
                    <h4>{{medicalData.height}}</h4>
                    <p class="ml-1">cm</p>
                </div>
            </div>
            <div class="col-4">
                <h6>Weight</h6>
                <div class="d-inline-flex text-info">
                    <h4>{{medicalData.weight}}</h4>
                    <p class="ml-1">kg</p>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    Vue.component('patient-medical-info', {
        template: "#patient-medical-info",
        props: ["medicalData"],
        computed: {
            patientSex: function () {
                if (this.medicalData.sex === 'Male') {
                    return "Male"
                } else {
                    return "Female"
                }
            }
        }
    })
</script>
