<template>
  <div>
    <h2>자유게시판</h2>
    <freelist type="Free"></freelist>
  </div>
</template>

<script>
import freelist from "@/components/Board/includes/list.vue";
export default {
  name: "free",
  components: {
    freelist,
  },
};
</script>

<style></style>
