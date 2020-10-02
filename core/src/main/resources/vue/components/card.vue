<template id="card">
    <div class="card card-common rounded-lg">
        <div class="card-body">
            <div class="d-flex justify-content-center">
                <div class="flex-column">
                    <h6 class="font-weight-bold">{{title}}</h6>
                    <h1 class="pt-2 text-info font-weight-bold">{{number}}</h1>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    Vue.component('card', {
        template: "#card",
        props: ["title", "number"]
    })
</script>
