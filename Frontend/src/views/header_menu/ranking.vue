<template>
    <div class="ranking">
        <div class="ranking_menu">
            <h1>랭킹페이지</h1>
            <span>
                <router-link to="/Ranking/expensive">비싼 순</router-link>
            </span>
            <span>
                <router-link to="/Ranking/cheapest">값싼 순</router-link>
            </span>
            <router-view />
        </div>
    </div>
</template>

<script>
export default {};
</script>

<style>
.ranking_menu {
    margin: 10px 0 30px 0;
    text-align: center;
}

.ranking {
    margin: 2% 20% 5% 20%;
}

table {
    width: 100%;
    text-align: center;
}

table {
    border-collapse: separate;
    border-spacing: 0;
    width: 100%;
    margin-top: 20px;
    text-align: center;
}

table tr th,
table tr td {
    border-bottom: 1px solid rgb(252, 214, 131);
    padding: 15px;
}
table tr th:first-child,
table tr th:last-child {
    border-top: solid 1px rgb(252, 214, 131);
}

table tr th {
    background: rgb(252, 214, 131);
}

table.Info tr th,
table.Info tr:first-child td {
    border-top: 1px solid rgb(252, 214, 131);
}

/* top-left border-radius */
table tr:first-child th:first-child,
table.Info tr:first-child td:first-child {
    border-top-left-radius: 6px;
    border-bottom-left-radius: 6px;
}

/* top-right border-radius */
table tr:first-child th:last-child,
table.Info tr:first-child td:last-child {
    border-top-right-radius: 6px;
    border-bottom-right-radius: 6px;
}

.list_btn {
    margin-top: 20px;
}

.ranking_menu > h1 {
    margin: 0 0 15px 0;
}
.ranking_menu > span {
    margin: 0 10px 0 10px;
}

.ranking-title {
    text-align: left;
}
</style>
