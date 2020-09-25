<template id="admin-layout">
    <div class="row">
        <div class="col-12">
            <top-nav></top-nav>
            <admin-sidebar></admin-sidebar>
        </div>
        <div class="offset-2 offset-xl-2 offset-lg-3 offset-md-4 offset-sm-0 col-xl-10 col-sm-12 col-md-8 col-lg-9">
            <slot></slot>
        </div>
    </div>;
</template>
<script>
    Vue.component('admin-layout', {
        template: "#admin-layout"
    });
</script>