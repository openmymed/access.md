<template id="doctor-home">
    <doctor-layout>
        <patients-overview></patients-overview>
        <doctor-feed></doctor-feed>
    </doctor-layout>
</template>
<script>
    Vue.component("doctor-home", {
        template: "#doctor-home"
    })
</script>