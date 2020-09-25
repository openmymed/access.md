<template id="patient-list-item">
    <tr >
        <th scope="row" class="text-left">{{name}}</th>
        <td  class="text-center">{{patient.telephoneNumber}}</td>
        <td class="text-right">
            <a class="btn btn-primary" v-bind:href="profileLink">
                Profile
            </a>
        </td>
    </tr>
</template>
<script>
    Vue.component('patient-list-item', {
        template: "#patient-list-item",
        props: ["patient"],
        computed: {
            profileLink: function () {
                return "/patient/" + this.patient.id;
            },
            name: function () {
                return this.patient.firstName + " " + this.patient.lastName;
            }
        }
    })
</script>