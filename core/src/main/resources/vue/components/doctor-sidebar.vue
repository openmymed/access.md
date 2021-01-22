<template id="doctor-sidebar">
      <div  class="col-xl-2 col-lg-3 col-md-4 sidebar fixed-top text-center">
            <a href="/dashboard"class="navbar-brand text-white bg-blue-primary d-block mx-auto text-center py-4 mb-5">
                ACCESS.MD
            </a>
            <div class="row">
                <ul class="navbar-nav flex-column text-left col-centered">
                    <li class="nav-item">
                        <a href="/dashboard" class="sidebar-link active nav-link p-3 mb-2">
                            <i class="fa fa-home fa-lg mr-3"></i>Dashboard
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="/patient" class="sidebar-link nav-link p-3 mb-2 ">
                            <i class="fa fa-user fa-lg mr-3"></i>My Patients
                        </a>
                    </li>
                </ul>
            </div>
            <button type="button" class="btn btn-primary btn-md col-centered mt-5" v-on:click="showPatientModal">
                <i class="fa fa-plus fa-lg mr-3"></i>Add Patient
            </button>
          <add-patient-modal ref="addPatient"></add-patient-modal>
        </div>;
</template>
<script>
    Vue.component("doctor-sidebar",{
        template:"#doctor-sidebar",
        methods:{
            showPatientModal:function(){
                this.$refs.addPatient.show();
            }
        }
    });
</script>