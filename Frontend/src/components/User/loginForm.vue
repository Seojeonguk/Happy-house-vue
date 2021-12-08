<template>
  <div class="wrapper">
    <form @submit.prevent="confirm">
      <div class="box_">
        <label>아이디</label>
        <span class="box">
          <input type="text" v-model="user.userid" @keyup.enter="confirm" />
        </span>
      </div>
      <div class="box_">
        <label>비밀번호</label>
        <span class="box">
          <input type="password" v-model="user.userpwd" @keyup.enter="confirm" />
        </span>
      </div>
      <button class="btnLogin">로그인</button>
    </form>
  </div>
</template>

<script>
import { login } from "@/api/user.js";
export default {
  name: "loginform",
  data() {
    return {
      user: {
        userid: null,
        userpwd: null,
      },
      isLoginError: false,
    };
  },
  methods: {
    confirm() {
      localStorage.setItem("access-token", "");
      login(
        this.user,
        (response) => {
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            this.$store.commit("setIsLogined", true);
            localStorage.setItem("access-token", token);

            this.$store.dispatch("GET_MEMBER_INFO", token);
            this.$router.push("/");
          } else {
            this.isLoginError = true;
          }
        },
        (error) => {
          console.error(error);
          alert("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
      );
    },
  },
};
</script>

<style></style>
