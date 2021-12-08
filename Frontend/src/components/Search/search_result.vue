<template>
    <div class="search_div" :class="{ hiding: hiding_option }" id="search_div">
        <div class="search_form">
            <form class="form" id="form" @submit.prevent="search">
                <div class="form_select_div">
                    <select id="gugun" name="gugun" ref="gugun" @change="gugun_list" v-model="gugun">
                        <option v-for="(item, idx) in get_gugun" :key="idx" :value="item.dongcode">
                            {{ item.gugun }}
                        </option>
                    </select>
                    <select id="dong" name="dong" v-model="dong">
                        <option value="">------</option>
                        <option v-for="(item, idx) in get_dong" :key="idx" :value="item.dong">
                            {{ item.dong }}
                        </option>
                    </select>
                </div>
                <div class="form_text_btn_div">
                    <input type="text" placeholder="아파트명 검색" name="search_apt_name" v-model="search_apt_name" />
                    <a href="#" id="search-btn" @click="search">
                        <font-awesome-icon :icon="['fas', 'search']" />
                    </a>
                </div>
            </form>
        </div>
        <div id="search_info">
            <div v-for="(item, idx) in get_apt_list" :key="idx">
                <p @click="get_housedeal(item)">{{ item.aptName }}</p>
                <p>{{ item.dong }} {{ item.jibun }}</p>
                <p>건축연도 : {{ item.buildYear }}</p>
            </div>
        </div>
        <a href="#" class="folder_btn" id="folder_btn" @click="folder_btn_hiding">
            <font-awesome-icon :icon="['fas', 'angle-left']" :class="{ displaynone: hiding_option }" />
            <font-awesome-icon :icon="['fas', 'angle-right']" :class="{ displaynone: !hiding_option }" />
        </a>

        <!-- 아파트 거래내역 dialog창 -->
        <div class="diaglog">
            <v-easy-dialog v-model="scrollDialog">
                <div class="overflow-y-auto">
                    <div>
                        <button class="X_button" @click="scrollDialog = false">X</button>
                    </div>
                    <div class="housedeal_title">
                        <h4>{{ select_item.aptName }}</h4>
                        <span>{{ select_item.dong }} {{ select_item.jibun }}번지</span>
                    </div>

                    <div>
                        <button class="comment_button" @click="comment_btn_click">COMMENT</button>
                    </div>

                    <div class="housedeal_item" v-for="(item, idx) in housedeal_list" :key="idx">
                        <p>{{ item.dealYear }}년 {{ item.dealMonth }}월 {{ item.dealDay }}일</p>
                        <p>{{ item.floor }}층 {{ Math.round(item.area / 3.3) }}평형 {{ item.dealAmount }} 만원</p>
                    </div>
                </div>
            </v-easy-dialog>
        </div>

        <!-- 아파트에 대한 코멘트창 -->
        <v-easy-dialog v-model="seconddialog">
            <div class="overflow-y-auto dialog">
                <button class="X_button" @click="seconddialog = false">X</button>
                <div class="housedeal_title">
                    <h3>{{ select_item.aptName }}</h3>
                </div>

                <div class="housedeal_item">
                    <div class="grade_div">
                        <select
                            name="greade"
                            id="grade"
                            v-model="grade"
                            style="display: inline-block; margin-right: 10px"
                        >
                            평점
                            <option v-for="n in 5" :value="n" :key="n">{{ n }}</option>
                        </select>
                        <input
                            style="display: inline-block; width: 80%"
                            type="text"
                            name="comment"
                            id="comment"
                            v-model="comment"
                            @keyup.enter="comment_reg"
                        />
                    </div>
                </div>

                <div class="comment_list">
                    <div class="comment-item" v-for="(item, idx) in getComments" :key="`comments_${idx}`">
                        <div class="comment-info">
                            <span class="comment-star">
                                <font-awesome-icon
                                    :icon="['fas', 'star']"
                                    v-for="(n, idx) in item.grade"
                                    :key="`star_${idx}`"
                                />
                                <font-awesome-icon
                                    class="not-star"
                                    :icon="['fas', 'star']"
                                    v-for="(n, idx) in 5 - item.grade"
                                    :key="`notstar_${idx}`"
                                />
                            </span>
                            <span class="comment-userid">{{ item.userid }}</span>
                        </div>

                        <div class="comment-content">{{ item.comments }}</div>
                        <div class="comment-date">{{ item.date }}</div>
                    </div>
                </div>
            </div>
        </v-easy-dialog>
    </div>
</template>
// form 이랑 결과 창 컴포넌트로 따로 만들기!
<script>
import { mapGetters } from "vuex";
import searchhttp from "@/util/search";
import VEasyDialog from "v-easy-dialog";
export default {
    name: "searching",
    components: {
        VEasyDialog,
    },
    data: function () {
        return {
            hiding_option: false,
            gugun: "11110",
            search_apt_name: "",
            dong: "",
            scrollDialog: false,
            housedeal_list: [],
            select_item: {},
            seconddialog: false,
            comment: "",
            grade: 5,
        };
    },
    created() {
        this.$store.dispatch("get_address").then(() => {
            this.$store.commit("set_gugun", this.gugun);
        });
        //
    },
    computed: {
        ...mapGetters(["get_gugun", "get_dong", "get_apt_list", "getUserInfo", "getComments"]),
    },
    methods: {
        folder_btn_hiding() {
            this.hiding_option = !this.hiding_option;
        },
        gugun_list() {
            this.$store.commit("set_gugun", this.$refs.gugun.value);
            this.dong = "";
        },
        search() {
            this.$store.dispatch("get_apt_list", {
                search_apt_name: this.search_apt_name,
                gugun: this.gugun,
                dong: this.dong,
            });
        },
        get_housedeal(item) {
            this.select_item = item;
            this.seconddialog = false;
            searchhttp
                .get(`/get_housedeal/${item.no}`)
                .then(({ data }) => {
                    this.housedeal_list = data;
                    console.log(data);
                    this.scrollDialog = true;
                })
                .catch(() => {
                    console.log("아파트 거래 정보 가져 오는 중 에러 발생");
                });
        },
        comment_reg() {
            console.log("------comment send------");
            console.log(this.select_item);
            console.log(this.getUserInfo);
            if (this.getUserInfo == null) {
                alert("로그인이 필요한 페이지 입니다");
                this.$router.push("/Userlogin");
            } else {
                console.log("comment 서버 전송");
                searchhttp
                    .post("/insert_comment", {
                        userid: this.getUserInfo.userid,
                        comments: this.comment,
                        aptno: this.select_item.no,
                        grade: this.grade,
                    })
                    .then(({ data }) => {
                        console.log(data);
                        if (data === "success") {
                            this.$store.dispatch("getComments", this.select_item.no);
                        }
                    })
                    .catch(() => {
                        console.log("코멘트 작성중 오류 발생");
                    });
            }
        },
        comment_btn_click() {
            // comment list get
            this.$store.dispatch("getComments", this.select_item.no);
            this.seconddialog = true;
        },
    },
};
</script>

<style>
* {
    margin: 0px;
    padding: 0px;
    list-style: none;
}

html,
body {
    height: 100%;
}

.content {
    height: 110vh;
    width: 100%;
}

#map {
    height: 100%;
    width: 100%;
}

.content .search_div {
    position: absolute;
    height: 106vh;
    width: 450px;
    z-index: 2;
    background-color: #ffffff;
    transition: 0.5s;
}

.content #search-btn {
    position: relative;
    display: inline-block;
    background-color: #6f6f6f;
    color: #b8f3b8;
    width: 50px;
    height: 50px;
    font-weight: 500;
    font-size: 20px;
    line-height: 50px;
    text-align: center;
    border-radius: 5px;
}

#sido {
    width: 130px;
}

#gugun {
    width: 160px;
}

#dong {
    width: 110px;
}

.search_div .search_form select,
.search_div .search_form input {
    padding: 5px;
    margin: 5px;
    border: 3px solid #6f6f6f;
    border-radius: 5px;
    height: 38px;
}

.search_div .search_form input {
    width: 38vh;
}

.search_div .search_form .form_text_btn_div {
    align-items: center;
    display: flex;
    justify-items: center;
}

.w3-top {
    position: static !important;
    border-bottom: 2px solid #ffffff;
}

.folder_btn {
    position: absolute;
    top: 50%;
    left: 100%;
    width: 24px;
    height: 48px;
    line-height: 48px;
    text-align: center;
    border: 1px solid #f1eeeb;
    background-color: white;
    border-radius: 0 5px 5px 0;
    color: #454545;
}

#search_info {
    height: calc(100% - 115px);
    overflow: auto;
    -ms-overflow-style: none;
    scrollbar-width: none;
}

#search_info::-webkit-scrollbar {
    display: none;
}

.item {
    border-top: 1px solid #ecf0f2;
    padding: 10px;
}

.item .apt-name {
    font-size: 25px;
    font-weight: 800;
    cursor: pointer;
}

.item .apt-dong {
    font-size: 1rem;
    color: #424242;
}

.item .apt-buildyear {
    font-size: 0.8reml;
    color: #8f8f8f;
}

.hiding {
    transform: translateX(-100%);
}

.displaynone {
    display: none;
}

.interest-btn {
    color: #999;
}

.interest {
    color: #fd4;
}

.interest-btn input {
    display: none;
}

.v-easy-dialog--backdrop-btn[data-v-4b225c4b] {
    background-color: rgb(251, 243, 220) !important;
    position: relative;
}

.v-easy-dialog--backdrop {
    width: 40% !important;
    height: 70% !important;
    position: absolute;
    left: 30% !important;
    top: 10% !important;
    align-items: baseline !important;
    font-family: "경기천년제목 Light", Helvetica, sans-serif !important;
    text-align: center;
}

.v-easy-dialog--content-container::-webkit-scrollbar {
    display: none;
}

.overflow-y-auto {
    position: relative;
}

.housedeal_title {
    margin: 20px;
    background-color: rgb(252, 214, 131);
    border-radius: 30px;
    text-align: center;
}

.housedeal_item {
    margin: 10px;
}
.comment_button {
    position: absolute;
    right: 0;
    top: 80px;

    padding: 12px 0 12px;
    width: 60px;
    border: 0;
    cursor: pointer;
    background-color: var(--main-third-color);
    font-size: 10px;
    font-weight: 400;
    border-radius: 20px;
}

.X_button {
    padding: 5px;

    border: 0;
    margin-top: 10px;
    cursor: pointer;
    background-color: rgb(252, 214, 131);
    font-size: 5px;
    font-weight: 400;
    border-radius: 10px;

    position: absolute;
    right: 0;
    top: 9px;
}

.grade_div {
    width: 100%;
    float: left;
}

.comment_list {
    margin-top: 10px;
    text-align: left;
    width: 85%;
    margin: auto;
    margin-top: 70px;
}

#search_info div {
    border-bottom: 1px solid #ddd;
}

#search_info div p {
    padding: 5px;
    height: 25px;
    line-height: 25px;
}

#search_info div p:first-child {
    cursor: pointer;
    font-size: 25px;
    font-weight: 700;
}

.comment-star {
    width: 100px;
    display: inline-block;
}

.fa-star {
    color: var(--main-third-color);
}

.not-star {
    color: #ddd;
}

.comment-item {
    margin-bottom: 20px;
}

.comment-item > div {
    margin-bottom: 5px;
    word-break: break-all;
}

.comment-userid {
    font-size: 13px;
    font-weight: 700;
}

.comment-date {
    font-size: 13px;
}
</style>
