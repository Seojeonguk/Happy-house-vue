<template>
    <div>
        <h2 class="ranking-title">비싼 순</h2>
        <expensivelist></expensivelist>
    </div>
</template>

<script>
import expensivelist from "@/components/Ranking/includes/List.vue";
export default {
    name: "expensivepage",
    components: {
        expensivelist,
    },
    created() {
        this.$store.dispatch("get_expensive");
    },
};
</script>

<style></style>
