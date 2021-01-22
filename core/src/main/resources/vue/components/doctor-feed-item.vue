<template id="doctor-feed-item">
    <tr>
        <th scope="row" class="text-left"> 
            {{update.patientName}}
        </th>
        <td this="updateType"  class="text-center">
            {{updateType}}
        </td>
        <td this="time"  class="text-center">
            {{update.time}}
        </td>
        <td this="value"  class="text-center">
            {{content}}
        </td>
        <td  class="text-right">
            <button class="btn btn-danger btn-sm" v-on:click="dismiss">Dismiss</button>
            <a class="btn btn-primary btn-sm" v-bind:href="profileLink">Profile</a>
        </td>
    </tr>
</template>
<script>
    Vue.component("doctor-feed-item", {
        template: "#doctor-feed-item",
        props: ["update"],
        computed: {
            profileLink: function () {
                return "/patient/" + this.update.patientId;
            },
            updateType: function () {
                if (this.update.type === "UNSEEN_SYMPTOM") {
                    return "Symptom";
                } else if (this.update.type === "UNSEEN_ANSWER") {
                    return "Answer";
                }
            },
            content: function () {
                if (this.update.type === "UNSEEN_SYMPTOM") {
                    return window.icpcService.getTitle(this.update.value)
                } else if (this.update.type === "UNSEEN_ANSWER") {
                    return "Question : " + this.update.value;
                }
            }
        },
        methods: {
            dismiss: function () {
                if (this.update.type === "UNSEEN_SYMPTOM") {
                    window.apiService.archiveSymptom(this.update.patientId, this.update.entityId);
                } else if (this.update.type === "UNSEEN_ANSWER") {
                    window.apiService.archiveAnswer(this.update.patientId, this.update.entityId);
                }
                this.$emit("dismiss");
            }
        }
    })
</script>
