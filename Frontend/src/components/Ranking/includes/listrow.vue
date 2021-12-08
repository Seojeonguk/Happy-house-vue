<template>
    <tr>
        <td>{{ item.dong }}</td>
        <td>{{ item.aptName }}</td>
        <td>{{ item.dealAmount }}만원</td>
    </tr>
</template>

<script>
export default {
    name: "rankinglistrow",
    props: {
        item: { type: Object },
    },
};
</script>

<style></style>
