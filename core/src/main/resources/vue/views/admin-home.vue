<!DOCTYPE html>
<template id="admin-home">
    <admin-layout>
        <h1>ADMIN HOME</h1>
    </admin-layout>
</template>
<script>
    Vue.component("admin-home", {
        template: "#admin-home",
        data: () => ({
                userId: null,
            }),
        created() {
            this.userId = "test"
        },
    });
</script>
