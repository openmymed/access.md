<template id="patient-symptom">
    <div class="row">
        <div class="symptom-card card card-common rounded-lg w-100">
            <div class="card-header d-flex justify-content-start">
                <h6>{{symptomName}}</h6>
            </div>
            <div class="card-body w-100">
                <div class="symptom-note">{{symptom.note}}</div>
                <p>
                <div class="d-flex justify-content-between w-100">
                    <div>
                        <div >{{symptom.creationDate}}</div>
                        <div class="d-flex">
                            <i class="fa fa-clock"></i>&nbsp;
                            <div >{{symptom.creationDate}}</div>
                        </div>
                    </div>
                    <div>
                        <button v-on:click="dismiss" class="btn btn-danger">Dismiss</button>
                    </div>
                </div>
                </p>
            </div>
        </div>
    </div>
</template>
<script>
    Vue.component("patient-symptom", {
        template: "#patient-symptom",
        props: ["symptom"],
        computed: {
            symptomName: function () {
                return window.icpcService.getTitle(this.symptom.symtomCode);
            }
        },
        methods: {
            dismiss: function () {
                return api.archiveSymptom(this.symptom.patientId, this.symptom.id).then(()=>{
                    this.$emit("dismiss")
                });
            }
        }
    })
</script>
