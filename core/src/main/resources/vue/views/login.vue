<!DOCTYPE html>
<template id="login">
  <div>
     <div this="el">
            <div class="sidenav">
                <div class="login-main-text">
                    <h1>
                        ACCESS.MD
                    </h1>
                    <img src="/static/img/openmymed.png"></img>
                </div>
            </div>
         <login-form></login-form>
        </div>;
  </div>
</template>
<script>
Vue.component("login", {
  template: "#login",
});
</script>