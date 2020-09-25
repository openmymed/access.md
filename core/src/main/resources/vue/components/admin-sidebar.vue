<template id="admin-sidebar">
    <div  class="col-xl-2 col-lg-3 col-md-4 sidebar fixed-top text-center" >
        <a href="/admin/dashboard" class="navbar-brand text-white bg-blue-primary d-block mx-auto text-center py-4 mb-5">
            Admin Dashboard
        </a>
        <div class="row">
            <ul class="navbar-nav flex-column text-left col-centered">
                <li class="nav-item">
                    <a href="/admin/dashboard" class="sidebar-link active nav-link p-3 mb-2">
                        <i class="fa fa-home fa-lg mr-3"></i>Home
                    </a>
                </li>
                <li class="nav-item">
                    <a href="/admin/doctors" class="sidebar-link nav-link p-3 mb-2 ">
                        <i class="fa fa-user fa-lg mr-3"></i>Doctor List
                    </a>
                </li>
            </ul>
        </div>
        <button type="button" class="btn btn-primary btn-md col-centered mt-5" v-on:click="showDoctorModal">
            <i class="fa fa-plus fa-lg mr-3"></i>Add Doctor
        </button>
        <add-doctor-modal ref="addDoctor"></add-doctor-modal>
    </div>;
</template>
<script>
    Vue.component("admin-sidebar", {
        template: "#admin-sidebar",
        methods: {
            showDoctorModal: function () {
                this.$refs.addDoctor.show();
            }
        }
    })

</script>