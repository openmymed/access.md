<!DOCTYPE html>
<template id="login">
  <div>
    <h1 class="login">Hello, World!</h1>
    asdasdasd
    <h2>Does this work??????????????</h2>
    <a href="/second">go to second</a>
    <div v-if="stuff">
      <ul class="user-overview-list">
        <li v-bind:key="item.one" v-for="item in stuff">
          {{ item.one }} and {{ item.two }}
        </li>
      </ul>
    </div>
  </div>
</template>
<script>
Vue.component("login", {
  template: "#login",
  data: () => ({
    stuff: null,
  })
});
</script>
<style>
.login {
  color: goldenrod;
}
</style>