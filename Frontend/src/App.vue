<template>
    <div id="app">
        <headernav></headernav>
        <div class="container">
            <router-view />
        </div>
        <footernav></footernav>
    </div>
</template>

<script>
import headernav from "@/include/HeaderNav.vue";
import footernav from "@/include/FooterNav.vue";

export default {
    name: "app",
    components: {
        headernav,
        footernav,
    },
};
</script>

<style>
:root {
    --main-color: rgb(251, 243, 220);
    --main-second-color: rgb(252, 214, 131);
    --main-third-color: rgb(253, 183, 121);
    --main-fourth-color: rgb(185, 222, 191);
    --main-fifth-color: rgb(107, 131, 143);
    --main-sixth-color: rgb(244, 233, 188);
}
#app {
  position: relative;
  margin: 0;
  font-family: "경기천년제목 Light", Helvetica, sans-serif;
}
.container {
  min-height: 120vh;
}

.title {
  text-align: center;
}

.wrapper {
  position: absolute;
  left: 50%;
  transform: translate(-50%);
  text-align: left;
  width: 30%;
  margin-top: 20px;
}

label {
  font-size: 15px;
  font-weight: 700;
}

.box_ {
  margin-bottom: 15px;
}
.box {
  border-radius: 10px;
  display: block;
  width: 100%;
  height: 51px;
  border: solid 1px #dadada;
  padding: 10px 14px 10px 14px;
  margin-top: 7px;
  box-sizing: border-box;
  background: #fff;
  position: relative;
}

input {
  display: block;
  position: relative;
  width: 100%;
  height: 29px;
  border: none;
  background: #fff;
  font-size: 15px;
}

.btnJoin,
.btnLogin {
  width: 100%;
  padding: 15px 0 17px;
  border-radius: 10px;

  border: 0;
  cursor: pointer;
  color: #fff;
  background-color: rgb(252, 214, 131);
  font-size: 20px;
  font-weight: 400;
  margin-top: 10px;
}

.btn {
  width: 70px;
  padding: 10px 0 10px;

  border: 0;
  cursor: pointer;
  color: #fff;
  background-color: rgb(252, 214, 131);
  font-size: 20px;
  font-weight: 400;
  margin: 5px;
  border-radius: 20px;
}

.board {
  margin: 2% 20% 5% 20%;
}
</style>
