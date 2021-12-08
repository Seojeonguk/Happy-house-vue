<template>
    <div class="boarddetail">
        <h2>상세보기</h2>
        <div>
            <div class="boardtitle">
                <label class="boardlabel" for="title">제목</label>
                <div class="contents_color title_contents">
                    {{ board.title }}
                </div>
            </div>

            <div class="boardinfo">
                <div class="bi_">
                    <label class="boardlabel" for="userid">작성자</label>
                    <div class="contents_color info_contents">
                        {{ board.userid }}
                    </div>
                </div>
                <div class="bi_">
                    <label class="boardlabel" for="regtime">작성일</label>
                    <div class="contents_color info_contents">
                        {{ board.regtime }}
                    </div>
                </div>
            </div>

            <div class="boardcontents">
                <div class="text">{{ board.content }}</div>
            </div>
        </div>

        <div class="boardbutton">
            <button class="btn" @click="modifyBoard">수정</button>
            <button class="btn" @click="deleteBoard">삭제</button>
            <button class="btn" @click="moveList">목록</button>
        </div>
    </div>
</template>

<script>
import http from "@/util/happyhouse";
import { mapGetters } from "vuex";
export default {
    name: "boardviewdetail",
    computed: {
        ...mapGetters(["board", "boardtype", "getUserInfo"]),
    },
    created() {},
    methods: {
        deleteBoard() {
            console.log(this.board.no);
            if (this.board.userid != this.getUserInfo.userid) {
                alert("작성자만 삭제할 수 있습니다.");
            } else {
                if (confirm("정말로 삭제하시겠습니까?")) {
                    http.delete(`/board/${this.board.no}`)
                        .then(({ data }) => {
                            alert("정상적으로 삭제에 성공했습니다.");
                            console.log(data);
                            this.$router.push(`/Board/${this.boardtype}`);
                        })
                        .catch(() => {
                            console.log("게시판 상세보기 삭제중 오류 발생");
                        });
                }
            }
        },
        modifyBoard() {
            if (this.board.userid != this.getUserInfo.userid) {
                alert("작성자만 수정할 수 있습니다.");
            } else {
                // this.$router.push({ name: "BoardCreate", params: { category: this.type } });
                this.$router.push({ name: "BoardModify", params: { no: this.board.no } });
            }
        },
        moveList() {
            this.$router.push(`/Board/${this.boardtype}`);
        },
    },
};
</script>

<style>
.contents_color {
    background-color: rgb(244, 233, 188);
    display: inline-block;
    border-radius: 10px;
    padding: 5px;
}

.boardtitle {
    margin: 20px 0 30px 0;
    font-size: 20pt;
}

.title_contents {
    padding-left: 15px;
    width: 89%;
}

.boardinfo {
    margin: 10px 0 30px 0;
}

.info_contents {
    padding-left: 15px;
    width: 75%;
}

.boardcontents {
    width: 100%;
    height: 450px;
}

.bi_ {
    display: inline-block;
    width: 50%;
}

.boardbutton {
    text-align: right;
    margin: 0 0 20px 0;
}

.boardlabel {
    font-size: 20px;
    background-color: rgb(252, 214, 131);
    border-radius: 10px;
    padding: 7px;
    margin: 20px;
}

.text {
    margin: 20px 0px 15px 20px;
    padding: 10px;
    height: 400px;
    background-color: rgb(244, 233, 188);
    border-radius: 20px;
}
</style>
