<template id="patient-question-recurrance">
    <tr>
        <th scope="row" class="text-left">{{index}}</th>
        <td  class="text-center">
            <input v-model="timeInput" v-on:keyup="timeChanged" type="time" class="recurrance-time" ></input>
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
                timeInput: "12:00"
            }),
        created() {
            if (this.recurrance.hourOfDay && this.recurrance.minuteOfHour) {
                this.timeInput = ("" + this.recurrance.hourOfDay).padStart(2, '0') + ":" + ("" + this.recurrance.minuteOfHour).padStart(2, '0');
            }
        },
        methods: {
            timeChanged: function () {
                let splits = this.timeInput.split(":");
                console.log(splits);
                this.$emit("update-recurrance", {index: this.index, recurrance:{
                        hourOfDay:parseInt(splits[0]),
                        minuteOfHour: parseInt(splits[1])
                }});
            },
            deleteRecurrance: function () {
                this.$emit("delete-recurrance", {index: this.index, recurrance: this.recurranceValue});
            }
        }
    });
</script>
