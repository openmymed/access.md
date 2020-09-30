<template id="patient-question-recurrance">
    <tr>
        <th scope="row" class="text-left">{{index}}</th>
        <td  class="text-center">
            <input v-on:change="timeChanged" type="time" class="recurrance-time" ></input>
        </td>
        <td class="text-right">
            <button v-on:click="deleteRecurrance" class="btn bt-primary btn-sm">
                <i class="fa fa-times"></i>
            </button>
        </td>
    </tr>
</template>
<script>
    Vue.component("patient-question-recurrance", {
        template: "#patient-question-recurrance",
        props: ["recurrance", "index"],
        data: () => ({
                timeInput: "00:00"
            }),
        created() {
            if (this.recurrance.hourOfDay && this.recurrance.minuteOfHour) {
                this.timeInput = ("" + this.recurrance.hourOfDay).padStart(2, '0') + ":" + ("" + this.recurrance.minuteOfHour).padStart(2, '0');
            }
        },
        methods: {
            timeChanged: function () {
                let splits = this.timeInput.split(":");
                this.recurrance.hourOfDay = parseInt(splits[0]);
                this.recurrance.minuteOfHour = parseInt(splits[1]);
                this.$emit("update-recurrance", {index: this.index, recurrance: this.recurrance});
            },
            deleteRecurrance: function () {
                this.$emit("delete-recurrance", {index: this.index, recurrance: this.recurrance});
            }
        }
    });
</script>
