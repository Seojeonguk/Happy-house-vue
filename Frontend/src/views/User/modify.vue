<template>
  <div>
    <h2 class="title">회원 수정 화면</h2>
    <modifyform type="modify" />
  </div>
</template>

<script>
import modifyform from "@/components/User/Form.vue";
export default {
  name: "modifypage",
  components: {
    modifyform,
  },
};
</script>

<style></style>
