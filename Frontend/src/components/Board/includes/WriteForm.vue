<template>
    <div>
        <div class="boardwriteform">
            <input type="hidden" name="userid" v-model="userid" />
            <input type="hidden" name="category" v-model="category" />
            <label for="title">제목</label>
            <input type="text" name="title" id="title" v-model="title" ref="title" />
            <label for="content">내용</label>
            <textarea id="content" name="content" v-model="content" ref="content"></textarea>
        </div>
        <div class="btn-div">
            <button class="btn" v-if="type == 'create'" @click="checkValue">등록</button>
            <button class="btn" v-else @click="checkValue">수정</button>
            <button class="btn" @click="moveList">목록</button>
        </div>
    </div>
</template>

<script>
import { mapGetters } from "vuex";
import http from "@/util/happyhouse";
export default {
    name: "writeform",
    props: {
        type: { type: String },
    },
    computed: {
        ...mapGetters(["getUserInfo", "boardtype"]),
    },
    data() {
        return {
            title: "",
            content: "",
            userid: "",
            category: "",
        };
    },
    created() {
        this.userid = this.getUserInfo.userid;
        this.category = this.boardtype;
        console.log(this.userid);
        if (this.type === "create") {
            console.log("생성");
        } else if (this.type === "modify") {
            console.log("수정");
            console.log(this.$route.params.no);
            http.get(`/board/${this.$route.params.no}`)
                .then(({ data }) => {
                    if (data.userid != this.userid) {
                        alert("작성자와 일치하지 않습니다.");
                        this.$router.push("/");
                    } else {
                        this.title = data.title;
                        this.content = data.content;
                    }
                })
                .catch(() => {
                    console.log("게시판 수정을 위해 데이터를 불러오는 도중 에러가 발생하였습니다.");
                });
        }
    },
    methods: {
        checkValue() {
            let err = true;
            let msg = "";
            !this.title && ((msg = "제목을 입력해주세요"), (err = false), this.$refs.title.focus());
            err && !this.content && ((msg = "내용을 입력해주세요"), (err = false), this.$refs.content.focus());

            if (!err) alert(msg);
            // 만약, 내용이 다 입력되어 있다면 registBook 호출
            else this.type == "create" ? this.createboard() : this.modifyBoard();
        },
        createboard() {
            http.post("/board", {
                content: this.content,
                category: this.category,
                title: this.title,
                userid: this.userid,
            })
                .then(({ data }) => {
                    console.log(data);
                    console.log("등록 성공");
                    this.$router.push(`/Board/${this.boardtype}`);
                })
                .catch(() => {
                    console.log("등록실패");
                });
        },
        modifyBoard() {
            http.put(`/board/${this.$route.params.no}`, {
                no: this.$route.params.no,
                content: this.content,
                category: this.category,
                title: this.title,
                userid: this.userid,
            })
                .then(({ data }) => {
                    if (data === "success") {
                        console.log("데이터를 정상적으로 수정하였습니다");

                        this.$router.push(`/Board/${this.boardtype}`);
                    } else {
                        alert("데이터 수정중 오류가 발생하였습니다");
                    }
                })
                .catch(() => {
                    console.log("게시글 수정을 하는 도중 에러가 발생했습니다.");
                });
        },
        moveList() {
            this.$router.push(`/Board/${this.boardtype}`);
        },
    },
};
</script>

<style>
.boardwriteform label {
    display: block;
    margin: 10px;
}

.boardwriteform input,
.boardwriteform textarea {
    width: 95%;
    border-radius: 30px;
    border: 1px solid #ddd;
    padding: 10px 20px 10px;
}

.boardwriteform textarea {
    height: 400px;
}

.btn-div {
    text-align: right;
}
</style>
