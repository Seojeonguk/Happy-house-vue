<template>
  <div class="registerForm">
    <h2 class="title">회원가입 화면</h2>
    <regform type="register" />
  </div>
</template>

<script>
import regform from "@/components/User/Form.vue";
export default {
  name: "userregister",
  components: {
    regform,
  },
};
</script>
<style>
.registerForm {
  margin-top: 50px;
  text-align: center;
}
</style>
