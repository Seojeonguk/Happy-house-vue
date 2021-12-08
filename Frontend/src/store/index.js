import Vue from "vue";
import Vuex from "vuex";
import searchhttp from "@/util/search";
import jwt_decode from "jwt-decode";
import { findById } from "@/api/user.js";
import http from "@/util/happyhouse.js";

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        address_list: [],
        dongcode: null,
        gugun_list: [],
        apt_list: [],

        //jwt 로그인 부분
        isLogin: false, // 로그인 여부
        userInfo: null,

        Boards: [],
        Board: {},
        BoardType: "",

        Comments: [],
        Ranking: [],
    },
    getters: {
        get_gugun(state) {
            return getUniqueObjectArray(state.address_list, "gugun");
        },
        get_dong(state) {
            if (state.gugun === null) {
                return state.address_list;
            } else {
                state.gugun_list = [];
                state.address_list.map((Item) => {
                    if (Item.dongcode == state.dongcode) {
                        state.gugun_list.push(Item);
                    }
                });
                console.log(state.gugun_list);
                return state.gugun_list;
            }
        },
        get_apt_list(state) {
            return state.apt_list;
        },
        boards(state) {
            let ret = [];
            state.Boards.forEach((element) => {
                if (element.category === state.BoardType) {
                    ret.push(element);
                }
            });
            return ret;
        },
        board(state) {
            return state.Board;
        },
        boardtype(state) {
            return state.BoardType;
        },
        getUserInfo(state) {
            return state.userInfo;
        },
        getComments(state) {
            return state.Comments;
        },
        getRanking(state) {
            return state.Ranking;
        },
    },
    mutations: {
        set_address_list(state, data) {
            state.address_list = data;
        },
        set_gugun(state, data) {
            console.log("gugun 변경");
            state.dongcode = data;
            console.log(state.dongcode);
        },
        set_apt_list(state, data) {
            state.apt_list = data;
        },

        // jwt 관련
        setIsLogined(state, isLogin) {
            state.isLogin = isLogin;
        },
        setUserInfo(state, userInfo) {
            state.isLogin = true;
            state.userInfo = userInfo;
        },
        LOGOUT(state) {
            state.isLogin = false;
            state.userInfo = null;
        },

        setBoards(state, data) {
            state.Boards = data;
        },

        setBoard(state, data) {
            state.Board = data;
        },
        setBoardType(state, type) {
            state.BoardType = type;
        },
        setCommnets(state, data) {
            state.Comments = data;
        },
        setRanking(state, data) {
            console.log("ranking설정");
            state.Ranking = data;
            console.log(state.Ranking);
        },
    },
    actions: {
        get_address(context) {
            console.log("주소 정보 불러오는 중");
            searchhttp
                .get("/get_baseaddress")
                .then(({ data }) => {
                    console.log(data);
                    context.commit("set_address_list", data);
                })
                .catch(() => {
                    console.log("주소 정보 불러오는 중 에러 발생");
                });
        },
        get_apt_list(context, { search_apt_name, gugun, dong }) {
            console.log("아파트 정보 가져오는 중");
            searchhttp
                .post("/get_houseinfo", {
                    search_apt_name: search_apt_name,
                    gugun: gugun,
                    dong: dong,
                })
                .then(({ data }) => {
                    console.log(data);
                    context.commit("set_apt_list", data);
                })
                .catch(() => {
                    console.log("아파트 정보 가져오는 중 에러 발생");
                });
        },

        //jwt 관련
        async GET_MEMBER_INFO({ commit }, token) {
            let decode = jwt_decode(token);

            await findById(
                decode.userid,
                (response) => {
                    if (response.data.message === "success") {
                        commit("setUserInfo", response.data.userInfo);
                        // router.push("/");
                        // router.go(router.currentRoute);
                    } else {
                        console.log("유저 정보 없음!!");
                    }
                },
                (error) => {
                    console.log(error);
                }
            );
        },
        LOGOUT({ commit }) {
            commit("LOGOUT");
            localStorage.removeItem("access-token");
            // axios.defaults.headers.common["auth-token"] = undefined;
        },

        getBoards({ commit }, type) {
            commit("setBoardType", type);

            http.get("/board")
                .then(({ data }) => {
                    console.log("게시판 정보 가져 오기 성공");
                    console.log(data);

                    commit("setBoards", data);
                })
                .catch(() => {
                    console.log("게시판 정보 가져오는 중 에러 발생");
                });
        },

        getBoard({ commit }, payload) {
            http.get(`/board${payload}`)
                .then(({ data }) => {
                    console.log("view데이터 가져오기 성공");
                    commit("setBoard", data);
                })
                .catch(() => {
                    console.log("게시판 상세보기 정보 가져오는중 에러 발생");
                });
        },

        getComments({ commit }, payload) {
            console.log(payload);
            http.get(`/search/get_comment/${payload}`)
                .then(({ data }) => {
                    console.log("comments 데이터 가져오기 성공");
                    console.log(data);
                    commit("setCommnets", data);
                })
                .catch(() => {
                    console.log("comments 데이터 가져오는 중 에러 발생");
                });
        },

        get_cheapest({ commit }) {
            http.get("/stats/cheapest")
                .then(({ data }) => {
                    console.log("가장 값싼 데이터 가져옴");
                    console.log(data);
                    commit("setRanking", data);
                })
                .catch(() => {
                    console.log("가장 값싼 데이터 가져오는 중 에러 발생");
                });
        },

        get_expensive({ commit }) {
            http.get("/stats/mostExpensive")
                .then(({ data }) => {
                    console.log("가장 비싼 데이터 가져옴");
                    console.log(data);
                    commit("setRanking", data);
                })
                .catch(() => {
                    console.log("가장 비싼 데이터 가져오는 중 에러 발생");
                });
        },
    },
});

function getUniqueObjectArray(array, key) {
    var tempArray = [];
    var resultArray = [];
    for (var i = 0; i < array.length; i++) {
        var item = array[i];
        if (tempArray.includes(item[key])) {
            continue;
        } else {
            resultArray.push(item);
            tempArray.push(item[key]);
        }
    }
    return resultArray;
}
