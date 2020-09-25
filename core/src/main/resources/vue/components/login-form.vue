<template id="login-form"> 
    <div class="main">
        <div class="col-md-6 col-sm-12">
            <div class="login-form">
                <form
                    @submit="login"
                    >
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input
                            v-model="username"
                            id="username"
                            type="text"
                            class="form-control"
                            placeholder="Username"
                            ></input>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input
                            v-model="password"
                            id="password"
                            type="password"
                            class="form-control"
                            placeholder="Password"
                            ></input>
                    </div>
                    <button type="submit" class="btn btn-black">
                        Login
                    </button>
                </form>
            </div>
        </div>
    </div>
</template>
<script>
    Vue.component("login-form", {
        template: "#login-form",
        data: () => ({
                username: "",
                password: ""
            }),
        methods: {
            login: function (e) {
                e.preventDefault();
                window.apiService.login(this.username, this.password)
                        .then((json) => {
                            sessionStorage.setItem("role", json.userRole);
                            let fullName = json.firstName + " " + json.lastName;
                            if (json.userRole == "ROLE_DOCTOR") {
                                sessionStorage.setItem("auth", true)
                                window.icpcService.loadIcpc();
                                sessionStorage.setItem("name", fullName);
                                window.location.href = "/dashboard";
                            } else if (json.userRole == "ROLE_ADMIN") {
                                sessionStorage.setItem("auth", true)
                                sessionStorage.setItem("name", fullName);
                                window.location.href = "/admin/dashboard";
                            } else {

                            }
                        })
                        .catch(error => {
                            sessionStorage.setItem("auth", false)
                            alert("You do not have the authorization for this page");
                        });
            }
        }
    });
</script>
