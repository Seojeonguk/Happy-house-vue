<template>
    <div>
        <h2 class="ranking-title">값싼 순</h2>
        <cheapestlist></cheapestlist>
    </div>
</template>

<script>
import cheapestlist from "@/components/Ranking/includes/List.vue";
export default {
    name: "cheapestpage",
    components: {
        cheapestlist,
    },
    created() {
        this.$store.dispatch("get_cheapest");
    },
};
</script>

<style></style>
