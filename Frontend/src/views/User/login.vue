<template>
  <div class="loginForm">
    <h2 class="title">로그인 화면</h2>
    <loginform></loginform>
  </div>
</template>

<script>
import loginform from "@/components/User/loginForm.vue";
export default {
  name: "login",
  components: {
    loginform,
  },
};
</script>

<style>
.loginForm {
  margin-top: 10%;
  min-height: 600px;
  text-align: center;
}
</style>
