<template id="doctor-feed-item">
    <tr>
        <th scope="row" class="text-left"> 
            {{feedItem.patientName}}
        </th>
        <td this="updateType"  class="text-center">
            {{updateType}}
        </td>
        <td this="time"  class="text-center">
            {{feedItem.time}}
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
        props: ["feedItem"],
        computed: {
            profileLink: function () {
                return "/patient/" + this.feedItem.patientId;
            },
            updateType: function () {
                if (this.feedItem.type === "UNSEEN_SYMPTOM") {
                    return "Symptom";
                } else if (this.feedItem.type === "UNSEEN_ANSWER") {
                    return "Answer";
                }
            },
            content: function () {
                if (this.feedItem.type === "UNSEEN_SYMPTOM") {
                    return window.icpcService.getTitle(this.feedItem.value)
                } else if (this.feedItem.type === "UNSEEN_ANSWER") {
                    return "Question : " + this.feedItem.value;
                }
            }
        },
        methods: {
            dismiss: function () {
                if (this.feedItem.type === "UNSEEN_SYMPTOM") {
                    window.apiService.archiveSymptom(this.data.patientId, this.data.entityId);
                } else if (this.feedItem.type === "UNSEEN_ANSWER") {
                    window.apiService.archiveAnswer(this.data.patientId, this.data.entityId);
                }
                this.$emit("dismiss");
            }
        }
    })
</script>
