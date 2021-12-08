<template>
    <div class="content">
        <searchandresult></searchandresult>
        <googlemap></googlemap>
    </div>
</template>
<script>
import searchandresult from "@/components/Search/search_result.vue";
import googlemap from "@/components/Search/Map.vue";
export default {
    name: "Map",

    components: {
        searchandresult,
        googlemap,
    },
};
</script>

<style></style>
