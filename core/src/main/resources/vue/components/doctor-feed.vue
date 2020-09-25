<template id="doctor-feed">
    <div  class="container-fluid mt-3">
        <h4>Last Activities</h4>
        <div class="col-12">
            <table class="table" this="table">
                <thead>
                <th scope="col" class="text-left">Patient Name</th>
                <th scope="col" class="text-center">Update Type</th>
                <th scope="col" class="text-center">Time</th>
                <th scope="col" class="text-center">Details</th>
                <th scope="col" class="text-right"></th>
                </thead>
                <tbody>
                <template v-for="(update, index) in updates" :key="update.entityId">
                    <doctor-feed-item v-bind:update="update" v-on:dismiss="dismissRow(index)"></doctor-feed-item>
                </template>
                </tbody>
            </table>
        </div>
    </div>
</template>
<script>
    Vue.component("doctor-feed", {
        template: "#doctor-feed",
        data: () => ({
                updates: []
            }),
        methods: {
            dismissRow: function (index) {
                this.updates.splice(index, 1);
                this.$emit("dismiss");
            }
        },
        created() {
            window.apiService.getDoctorFeed().then((json) => {
                this.updates = json;
            });
        }
    })
</script>