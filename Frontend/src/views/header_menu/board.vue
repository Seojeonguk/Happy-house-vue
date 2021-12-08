<template>
    <div class="board">
        <div class="board_menu">
            <h1>뉴스 및 커뮤니티</h1>
            <span>
                <router-link to="/Board/Notice">공지사항</router-link>
            </span>
            <span>
                <router-link to="/Board/Free">자유게시판</router-link>
            </span>
        </div>
        <router-view />
    </div>
</template>

<script>
export default {
    name: "board",
};
</script>

<style>
.board_menu {
    margin: 10px 0 30px 0;
    text-align: center;
}
.board_menu > h1 {
    margin: 0 0 15px 0;
}
.board_menu > span {
    margin: 0 10px 0 10px;
}
</style>
