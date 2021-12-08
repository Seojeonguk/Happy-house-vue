<template>
  <div class="wrapper">
    <form @submit.prevent="checkValue">
      <div class="box_">
        <label for="userid">아이디</label>
        <span class="box">
          <input
            v-if="type == 'register'"
            type="text"
            name="userid"
            id="userid"
            ref="userid"
            v-model="userid"
          />
          <input
            v-else
            type="text"
            name="userid"
            id="userid"
            v-model="userid"
            ref="userid"
            readonly
          />
        </span>
      </div>
      <div class="box_">
        <label for="username">이름</label>
        <span class="box">
          <input type="text" name="username" id="username" ref="username" v-model="username" />
        </span>
      </div>
      <div class="box_">
        <label for="userpwd">비밀번호</label>
        <span class="box">
          <input type="password" name="userpwd" id="userpwd" ref="userpwd" v-model="userpwd" />
        </span>
      </div>
      <div class="box_">
        <label for="email">이메일</label>
        <span class="box">
          <input type="text" name="email" id="email" ref="email" v-model="email" />
        </span>
      </div>
      <div class="box_">
        <label for="address">주소</label>
        <span class="box">
          <input type="text" name="address" id="address" ref="address" v-model="address" />
        </span>
      </div>
      <button class="btnJoin">전송</button>
    </form>
  </div>
</template>

<script>
import userhttp from "@/util/user";
import { mapState } from "vuex";
export default {
  name: "Handler",
  data: function () {
    return {
      userid: "",
      username: "",
      userpwd: "",
      email: "",
      address: "",
    };
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "modify") {
      this.userid = this.userInfo.userid;
      this.username = this.userInfo.username;
      this.userpwd = this.userInfo.userpwd;
      this.email = this.userInfo.email;
      this.address = this.userInfo.address;
    }
  },
  methods: {
    checkValue() {
      let err = true;
      let msg = "";
      !this.userid && ((msg = "아이디를 입력해주세요"), (err = false), this.$refs.userid.focus());
      err &&
        !this.username &&
        ((msg = "이름을 입력해주세요"), (err = false), this.$refs.username.focus());
      err &&
        !this.userpwd &&
        ((msg = "비밀번호를 입력해주세요"), (err = false), this.$refs.userpwd.focus());
      err &&
        !this.email &&
        ((msg = "이메일을 입력해주세요"), (err = false), this.$refs.email.focus());
      err &&
        !this.address &&
        ((msg = "주소를 입력해주세요"), (err = false), this.$refs.address.focus());

      if (!err) alert(msg);
      else {
        let userinfo = {
          userid: this.userid,
          username: this.username,
          userpwd: this.userpwd,
          email: this.email,
          address: this.address,
        };
        this.type == "register" ? this.register(userinfo) : this.modify(userinfo);
      }
    },
    register(userinfo) {
      userhttp.post("/user", userinfo).then(({ data }) => {
        let msg = "등록 처리시 문제가 발생했습니다.";
        if (data === "success") {
          msg = "등록이 완료 되었습니다";
          this.$store.commit("setUserInfo", userinfo);
        }
        alert(msg);
        this.$router.push("/");
      });
    },
    modify(userinfo) {
      userhttp
        .put("/user", {
          userid: this.userid,
          userpwd: this.userpwd,
          email: this.email,
          address: this.address,
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료 되었습니다.";
            this.$store.commit("setUserInfo", userinfo);
          }
          alert(msg);
          this.$router.push("/");
        });
    },
  },
  computed: {
    ...mapState(["userInfo"]),
  },
};
</script>

<style></style>
