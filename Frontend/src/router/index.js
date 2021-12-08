import Vue from "vue";
import VueRouter from "vue-router";
import store from "@/store";

Vue.use(VueRouter);

const onlyAuthUser = async (to, from, next) => {
    let token = localStorage.getItem("access-token");
    if (store.state.userInfo == null && token) {
        await store.dispatch("GET_MEMBER_INFO", token);
    }
    if (store.state.userInfo === null) {
        alert("로그인이 필요한 페이지입니다..");
        // next({ name: "login" });
        router.push({ name: "Login" });
    } else {
        next();
    }
};

const routes = [
    {
        path: "/",
        name: "Home",
        component: () => import("@/views/index.vue"),
    },
    {
        path: "/Userlogin",
        name: "Login",
        component: () => import("@/views/User/login.vue"),
    },
    {
        path: "/UserRegister",
        name: "Register",
        component: () => import("@/views/User/register.vue"),
    },
    {
        path: "/UserModify",
        name: "Modify",
        beforeEnter: onlyAuthUser,
        component: () => import("@/views/User/modify.vue"),
    },
    {
        path: "/Search",
        name: "Search",
        component: () => import("@/views/header_menu/search.vue"),
    },
    {
        path: "/Statistics",
        name: "Statistics",
        component: () => import("@/views/header_menu/statistics.vue"),
    },
    {
        path: "/Ranking",
        name: "Ranking",
        component: () => import("@/views/header_menu/ranking.vue"),
        children: [
            {
                path: "",
                name: "RankingIndex",
                component: () => import("@/components/Ranking/expensive.vue"),
            },
            {
                path: "expensive",
                name: "Expensive",
                component: () => import("@/components/Ranking/expensive.vue"),
            },
            {
                path: "cheapest",
                name: "Cheapest",
                component: () => import("@/components/Ranking/cheapest.vue"),
            },
        ],
        redirect: () => {
            return "/Ranking";
        },
    },
    {
        path: "/Board",
        name: "Board",
        component: () => import("@/views/header_menu/board.vue"),
        children: [
            {
                path: "",
                name: "index",
                component: () => import("@/components/Board/notice.vue"),
            },
            {
                path: "Free",
                name: "Free",
                component: () => import("@/components/Board/free.vue"),
            },
            {
                path: "Notice",
                name: "Notice",
                component: () => import("@/components/Board/notice.vue"),
            },
            {
                path: "Create",
                name: "BoardCreate",
                beforeEnter: onlyAuthUser,
                component: () => import("@/components/Board/includes/Create.vue"),
            },
            {
                path: "Modify",
                name: "BoardModify",
                beforeEnter: onlyAuthUser,
                component: () => import("@/components/Board/includes/Modify.vue"),
            },
            {
                path: "View/:no",
                name: "BoardView",
                component: () => import("@/components/Board/includes/View.vue"),
            },
        ],
        redirect: () => {
            return "/Board";
        },
    },
];

const router = new VueRouter({
    mode: "history",
    base: process.env.BASE_URL,
    routes,
});

export default router;
