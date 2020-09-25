<template id="patient-list">
    <doctor-layout>
        <h4 class="ml-4">Patient List</h4>
        <div class="col-12">
            <table class="table" this="table">
                <thead>
                <th scope="col" class="text-left">
                    Name
                </th>
                <th scope="col" class="text-center">
                    Number
                </th>
                <th scope="col" class="text-right">
                    Profile
                </th>
                </thead>
                <tbody>
                <template v-for="(patient,index) in patients" :key="index">
                    <patient-list-item  v-bind:patient="patient"></patient-list-item>
                </template>
                </tbody>
            </table>
        </div>
    </doctor-layout>
</template>
<script>
    Vue.component("patient-list", {
        template: "#patient-list",
        data: () => ({
                patients: []
            }),
        created() {
            window.apiService.getPatients().then(json => {
                this.patients = json
            });
        }

    })

</script>