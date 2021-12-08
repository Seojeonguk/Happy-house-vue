<template>
    <div>
        <view-detail />
    </div>
</template>

<script>
import ViewDetail from "@/components/Board/includes/ViewDetail.vue";
export default {
    name: "viewdatatil",
    components: {
        ViewDetail,
    },
    created() {
        this.$store.dispatch("getBoard", `/${this.$route.params.no}`);
    },
};
</script>

<style></style>
