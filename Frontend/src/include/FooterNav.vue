<template>
    <footer>
        <div class="footer">
            <div class="footerdiv">
                SSAFY<br />
                정지원(jiiiwon21@gmail.com)<br />
                서정욱(junguk7880@naver.com)<br />
            </div>
            <div class="footerdiv">
                contact<br />
                <img src="@/assets/instagram.png" />
                <img src="@/assets/facebook.png" />
                <img src="@/assets/twitter.png" />
            </div>
        </div>
    </footer>
</template>

<script>
export default {
    name: "footernav",
};
</script>

<style>
footer {
    position: absolute;
    left: 0;
    bottom: 0;
    width: 100%;
    padding: 5px 0;
    height: 13vh;
    background: rgb(244, 233, 188);
}

.footer {
    padding: 0 10% 0 10%;
    text-align: center;
}

.footerdiv {
    width: 40%;
    display: inline-block;
    margin: 10px;
    color: darkgray;
    text-align: center;
}

.footerdiv > img {
    width: 30px;
    margin: 10px;
}

.footermenu > a {
    text-decoration: none;
    color: darkgray;
}

.footermenu {
    display: inline-block;
    margin: 10px;
}
</style>
