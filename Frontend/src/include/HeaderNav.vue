<template>
    <header>
        <div>
            <div class="logo">
                <router-link to="/">
                    <font-awesome-icon :icon="['fas', 'home']" />
                </router-link>
            </div>

            <div class="login">
                <ul v-if="userInfo !== null">
                    <li><router-link to="/UserModify">정보수정</router-link></li>
                    <li><a href="#" @click.prevent="onClickLogout">로그아웃</a></li>
                </ul>

                <ul v-else>
                    <li><router-link to="/UserRegister">회원가입</router-link></li>
                    <li><router-link to="/Userlogin">로그인</router-link></li>
                </ul>
            </div>

            <nav>
                <ul>
                    <li><router-link to="/">Home</router-link></li>
                    <li><router-link to="/Search">검색</router-link></li>
                    <li><router-link to="/Statistics">통계</router-link></li>
                    <li><router-link to="/Board">게시판</router-link></li>
                    <li><router-link to="/Ranking">랭킹</router-link></li>
                </ul>
            </nav>
        </div>
    </header>
</template>

<script>
import { mapState } from "vuex";
export default {
    name: "headernav",
    computed: {
        ...mapState(["userInfo", "isLogin"]),
    },
    methods: {
        onClickLogout() {
            this.$store
                .dispatch("LOGOUT")
                .then(() => {
                    // this.$router.push({ name: "" });
                    if (this.$route.path !== "/") this.$router.replace("/");
                })
                .catch(() => {
                    console.log("로그아웃 문제!!!");
                });
        },
    },
};
</script>

<style>
* {
    margin: 0;
    padding: 0;
}

header {
    position: relative;
    margin: 0;
    height: 7vh;
    padding: 0 100px;
    background: rgb(185, 222, 191);
    width: 100%;
    box-sizing: border-box;
}

header .logo {
    color: #fff;
    height: 7vh;
    line-height: 7vh;
    font-size: 24px;
    float: left;
    font-weight: bold;
}

header .logo a:active,
header .logo a:link,
header .logo a:visited {
    text-decoration: none;
    color: #fff;
}

header .logo i {
    height: 100%;
    width: 70px;
    text-decoration: none;
}

header .login,
header nav {
    float: right;
}

header .login ul,
header nav ul {
    margin: 0;
    padding: 0;
    display: flex;
}

header .login ul li,
header nav ul li {
    list-style: none;
}

header .login ul li a,
header nav ul li span,
header nav ul li a {
    height: 7vh;
    line-height: 7vh;
    padding: 0 20px;
    color: #fff;
    text-decoration: none;
    display: block;
}

header .login ul li span {
    font-size: 10px;
    color: rgb(102, 99, 99);
}

header nav ul li span.active,
header nav ul li span:hover,
header nav ul li a:hover {
    color: #fff;
    background: rgb(244, 233, 188);
}
</style>
