<template>
  <div>
    <h2>공지사항</h2>
    <noticelist type="Notice"></noticelist>
  </div>
</template>

<script>
import noticelist from "@/components/Board/includes/list.vue";
export default {
  name: "notice",
  components: {
    noticelist,
  },
};
</script>

<style></style>
