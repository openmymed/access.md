<template id="doctor-layout">
    <div class="container-fluid">
        <div class="row">
            <top-nav></top-nav>
            <doctor-sidebar></doctor-sidebar>
        </div>
        <div class="offset-2 offset-xl-2 offset-lg-3 offset-md-4 offset-sm-0 col-xl-10 col-sm-12 col-md-8 col-lg-9">
            <slot></slot>
        </div>
    </div>;
</template>
<script>
    Vue.component('doctor-layout', {
        template: "#doctor-layout"
    });
</script>