<template id="doctor-home">
    <doctor-layout>
        <patients-overview ref="patientOverview"></patients-overview>
        <doctor-feed v-on:dismiss="feedChanged"></doctor-feed>
    </doctor-layout>
</template>
<script>
    Vue.component("doctor-home", {
        template: "#doctor-home",
        methods:{
            feedChanged:function(){
                this.$refs.patientOverview.refresh();
            }
        }
    })
</script>