<template>
    <tr>
        <td>
            <router-link style="text-decoration: none" :to="{ name: 'BoardView', params: { no: item.no } }">{{
                item.title
            }}</router-link>
        </td>
        <td>{{ item.userid }}</td>
        <td>{{ item.regtime }}</td>
    </tr>
</template>

<script>
export default {
    name: "listrow",
    props: {
        item: { type: Object },
    },
};
</script>

<style>
table {
    width: 100%;
    text-align: center;
}
</style>
