<!DOCTYPE html>
<template id="second-view">
  <div>
    <h1>Watch Out for {{ userId }}</h1>
    <h1>Second View !!!!</h1>
  </div>
</template>
<script>
Vue.component("second-view", {
  template: "#second-view",
  data: () => ({
    userId: null,
  }),
  created() {
    this.userId = this.$javalin.pathParams["user"];
  },
});
</script>
