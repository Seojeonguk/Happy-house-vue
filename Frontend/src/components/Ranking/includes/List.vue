<template>
    <div v-if="getRanking.length">
        <table>
            <colgroup>
                <col style="width: 25%" />
                <col style="width: 50%" />
                <col style="width: 25%" />
            </colgroup>

            <thead>
                <tr>
                    <th>동</th>
                    <th>아파트</th>
                    <th>가격</th>
                </tr>
            </thead>

            <tbody>
                <list-row v-for="(item, index) in getRanking" :key="index" :item="item"></list-row>
            </tbody>
        </table>
    </div>
    <div v-else>데이터 없음</div>
</template>

<script>
import { mapGetters } from "vuex";
import ListRow from "@/components/Ranking/includes/listrow.vue";
export default {
    name: "rankinglist",
    computed: {
        ...mapGetters(["getRanking"]),
    },
    components: {
        ListRow,
    },
};
</script>

<style></style>
