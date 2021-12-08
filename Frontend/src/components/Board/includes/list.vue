<template>
    <div>
        <div v-if="boards.length">
            <table>
                <colgroup>
                    <col style="width: 50%" />
                    <col style="width: 25%" />
                    <col style="width: 25%" />
                </colgroup>

                <thead>
                    <tr>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>작성일</th>
                    </tr>
                </thead>

                <tbody>
                    <list-row v-for="(item, index) in boards" :key="index" :item="item"></list-row>
                </tbody>
            </table>
        </div>
        <div v-else>게시글이 없습니다.</div>
        <div style="text-align: right">
            <button class="btn list_btn" @click="regbtnclick">등록</button>
        </div>
    </div>
</template>

<script>
import { mapGetters } from "vuex";
import ListRow from "@/components/Board/includes/listrow.vue";
export default {
    name: "BoardList",
    props: {
        type: { type: String },
    },
    computed: {
        ...mapGetters(["boards"]),
    },
    created() {
        this.$store.dispatch("getBoards", this.type);
    },
    components: {
        ListRow,
    },
    methods: {
        regbtnclick() {
            this.$router.push({ name: "BoardCreate", params: { category: this.type } });
        },
    },
};
</script>

<style>
table {
    border-collapse: separate;
    border-spacing: 0;
    width: 100%;
    margin-top: 20px;
    text-align: center;
}

table tr th,
table tr td {
    border-bottom: 1px solid rgb(252, 214, 131);
    padding: 15px;
}
table tr th:first-child,
table tr th:last-child {
    border-top: solid 1px rgb(252, 214, 131);
}

table tr th {
    background: rgb(252, 214, 131);
}

table.Info tr th,
table.Info tr:first-child td {
    border-top: 1px solid rgb(252, 214, 131);
}

/* top-left border-radius */
table tr:first-child th:first-child,
table.Info tr:first-child td:first-child {
    border-top-left-radius: 6px;
    border-bottom-left-radius: 6px;
}

/* top-right border-radius */
table tr:first-child th:last-child,
table.Info tr:first-child td:last-child {
    border-top-right-radius: 6px;
    border-bottom-right-radius: 6px;
}

.list_btn {
    margin-top: 20px;
}
</style>
