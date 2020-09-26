<template id="patient-personal-info">
    <div class="row d-block">
        <div class="d-flex row mt-4 pl-5">
            <img alt="patient-profile-img" class="profile-img" src="https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/Circle-icons-profile.svg/768px-Circle-icons-profile.svg.png" ></img>
            <div class="ml-2">
                <h5 class="mb-0">{{patientName}}</h5>
                <h6 class="text-secondary">{{personalData.age}} Years</h6>
            </div>
        </div>
    </div>
</template>
<script>
    Vue.component('patient-personal-info', {
        template: "#patient-personal-info",
        props: ["personalData"],
        computed: {
            patientName: function () {
                return this.personalData.firstName + " " + this.personalData.lastName;
            }
        },
    })
</script>