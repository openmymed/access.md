<template id="medical-profile">
    <div this="el" class="col-lg-3 p-0 border-right">
        <div class="d-flex row mt-4 pl-5">
            <img alt="patient-profile-img" class="profile-img" src="https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/Circle-icons-profile.svg/768px-Circle-icons-profile.svg.png" ></img>
            <div class="ml-2">
                <h5 this="name" class="mb-0"></h5>
                <h6 this="age" class="text-secondary"></h6>
            </div>
        </div>
        <h5 class="bg-blue-primary text-white d-block mx-auto p-2 mt-3">
            General Info
        </h5>
        <div class="row text-center p-3">
            <div class="col-4">
                <h6>Sex</h6>
                <h4 this="sex" class="text-info">
                </h4>
            </div>
            <div class="col-4">
                <h6>Height</h6>
                <div class="d-inline-flex text-info">
                    <h4 this="height"></h4>
                    <p class="ml-1">cm</p>
                </div>
            </div>
            <div class="col-4">
                <h6>Weight</h6>
                <div class="d-inline-flex text-info">
                    <h4 this="weight"></h4>
                    <p class="ml-1">kg</p>
                </div>
            </div>
        </div>
        <h5 class="bg-blue-primary text-white d-block mx-auto p-2 mt-3">
            Medical Flags
        </h5>
        <div class="col-12 py-1">
            <input disabled="disabled"  type="checkbox" id="G6PD" this="g6pdDeficiency" name="G6PD" value="G6PD Deficiency" ></input>
            <label class="ml-1" for="G6PD">
                G6PD Deficiency
            </label>
            <br></br>
            <input disabled="disabled"  type="checkbox" id="respiratory" name="respiratory" value="Respiratory Diseases" this="respiratoryDiseases"></input>
            <label class="ml-1" for="respiratory">
                Respiratory Diseases
            </label>
            <br ></br>
            <input disabled="disabled" type="checkbox" id="diabetes" name="diabetes" value="Diabetes" this="diabetes"></input>
            <label class="ml-1" for="diabetes">
                Diabetes
            </label>
            <br ></br>
            <input disabled="disabled" type="checkbox" id="cardio" name="cardio" value="Cardiovascular Diseases" this="cardiovascularDiseases"></input>
            <label class="ml-1" for="cardio">
                Cardiovascular Diseases
            </label>
            <br ></br>
            <input disabled="disabled" type="checkbox" id="obesity" name="obesity" value="Obesity" this="obesity"></input>
            <label class="ml-1" for="obesity">
                Obesity
            </label>
            <br></br>
        </div>
        <h5 class="bg-blue-primary text-white d-block mx-auto p-2 mt-3">
            Current Medications
        </h5>
        <div class="p-1">
            {this.medications = list('ul', Medication)}
        </div>
    </div>
</template>
<script>
    Vue.component("medical-profile", {
        template: "#medical-profile",
        props: ["patientId"],
        data: () => ({
                medications: [],
                medicalData: {},
                personaldata: {}
            }),
        created() {

        }
    })
</script>
