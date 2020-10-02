<template id="add-doctor-modal">
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" >
        <div class="modal-dialog" role="document">
            <div class="modal-content container-fluid">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">
                        Add Doctor
                    </h5>
                    <button type="button" class="close" data-dismiss="modal"aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body text-left container-fluid">
                    <div class="form-group container-fluid">
                        <label for="username">Username</label>
                        <input class="form-control" id="username" type="text" v-model="username"></input>
                    </div>
                    <div class="form-group container-fluid">
                        <label for="password">Password</label>
                        <input class="form-control" id="password" type="password" v-model="password"></input>
                    </div>
                    <div class="form-group container-fluid">
                        <label for="firstName">First Name</label>
                        <input class="form-control" id="firstName"  type="text"   v-model="firstName"></input>
                    </div>
                    <div class="form-group container-fluid">
                        <label for="lastName">Last Name</label>
                        <input class="form-control" id="lastName" type="text" v-model="lastName" ></input>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">
                        Close
                    </button>
                    <button class="btn btn-primary" data-dismiss="modal" v-on:click="addDoctor" >
                        Add
                    </button>
                </div>
            </div>
        </div>
    </div>;
</template>
<script>
    Vue.component('add-doctor-modal', {
        template: '#add-doctor-modal',
        data: () => ({
                username: "",
                password: "",
                firstName: "",
                lastName: ""
            }),
        methods: {
            addDoctor: function () {
                window.apiService.addDoctor({
                    username: this.username,
                    password: this.password,
                    firstName: this.firstName,
                    lastName: this.lastName
                });
            },
            show: function () {
                $(this.$el).modal({backdrop: false});
            }
        }
    });
</script>
