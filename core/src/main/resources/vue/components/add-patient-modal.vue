<template id="add-patient-modal">
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content container-fluid">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Add Patient</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body container-fluid">
                    <div class="col-12">
                        <div class="form-group">
                            <label for="code-field">Secret Code</label>
                            <input class="form-control" id="code-field" type="text" v-model="code"></input>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button class="btn btn-primary" data-dismiss="modal" v-on:click="addPatient">Add</button>
                </div>
            </div>
        </div>
    </div>;
</template>
<script>
  Vue.component("add-patient-modal",{
      template:"#add-patient-modal",
      data:()=>({
          code:""
      }),
      methods:{
          show:function(){
               $(this.$el).modal({backdrop:false});
          },
          addPatient:function(){
              window.apiService.assignPatientToMyself(this.code);
          }
      }
  })
</script>
