<template id="patient-medical-flags">
    <div class="row d-block">
        <h5 class="bg-blue-primary text-white d-block mx-auto p-2 mt-3">
            Medical Flags
        </h5>
        <div class="col-12 py-1">
            <input disabled="disabled"  type="checkbox" id="G6PD" v-bind:checked="medicalFlags.g6pdDeficiency" name="G6PD" value="G6PD Deficiency" ></input>
            <label class="ml-1" for="G6PD">
                G6PD Deficiency
            </label>
            <br></br>
            <input disabled="disabled"  type="checkbox" id="respiratory" name="respiratory" v-bind:checked="medicalFlags.respiratoryDiseases" value="Respiratory Diseases"></input>
            <label class="ml-1" for="respiratory">
                Respiratory Diseases
            </label>
            <br ></br>
            <input disabled="disabled" type="checkbox" id="diabetes" name="diabetes" v-bind:checked="medicalFlags.diabetes" value="Diabetes"></input>
            <label class="ml-1" for="diabetes">
                Diabetes
            </label>
            <br ></br>
            <input disabled="disabled" type="checkbox" id="cardio" name="cardio" v-bind:checked="medicalFlags.cardiovascularDiseases" value="Cardiovascular Diseases" ></input>
            <label class="ml-1" for="cardio">
                Cardiovascular Diseases
            </label>
            <br ></br>
            <input disabled="disabled" type="checkbox" id="obesity" name="obesity" v-bind:checked="medicalFlags.obesity" value="Obesity"></input>
            <label class="ml-1" for="obesity">
                Obesity
            </label>
            <br></br>
        </div>
    </div>
</template>
<script>
    Vue.component('patient-medical-flags', {
        template: "#patient-medical-flags",
        props: ["medicalFlags"]
    })
</script>
