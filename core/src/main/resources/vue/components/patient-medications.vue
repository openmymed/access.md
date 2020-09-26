<template id="patient-medications">
    <div class="d-block w-100">
        <h5 class="bg-blue-primary text-white d-block mx-auto p-2 mt-3">
            Current Medications
        </h5>
        <div class="p-1">
            <ul v-if="medications !=null && medications.length>0" v-for="(medication, index) in medications" :key="index">
                <li>{{medication}}</li>
            </ul>
        </div>
    </div>
</template>
<script>
    Vue.component('patient-medications', {
        template: "#patient-medications",
        props: ["medications"]
    })
</script>
