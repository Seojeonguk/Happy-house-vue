<template>
    <div>
        <img src="@/assets/housemain.png" />
        <div>
            <div class="mainranking">
                <div class="ranklist">
                    <div class="list-title">
                        <h2>비싼 순위<span class="top5">Top5</span></h2>
                        <router-link to="/Ranking/cheapest">더보기</router-link>
                    </div>
                    <div class="list-content">
                        <table>
                            <thead>
                                <tr>
                                    <th>순번</th>
                                    <th>아파트명</th>
                                    <th>위치</th>
                                    <th>가격</th>
                                </tr>
                            </thead>

                            <tbody>
                                <tr v-for="(item, idx) in cheapesttop" :key="idx">
                                    <td>{{ idx + 1 }}</td>
                                    <td>{{ item.aptName }}</td>
                                    <td>{{ item.dong }}</td>
                                    <td>{{ item.dealAmount }}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="ranklist">
                    <div class="list-title">
                        <h2>값싼 순위<span class="top5">Top5</span></h2>
                        <router-link to="/Ranking/expensive">더보기</router-link>
                    </div>
                    <div class="list-content">
                        <table>
                            <thead>
                                <tr>
                                    <th>순번</th>
                                    <th>아파트명</th>
                                    <th>위치</th>
                                    <th>가격</th>
                                </tr>
                            </thead>

                            <tbody>
                                <tr v-for="(item, idx) in mostExpensivetop" :key="idx">
                                    <td>{{ idx + 1 }}</td>
                                    <td>{{ item.aptName }}</td>
                                    <td>{{ item.dong }}</td>
                                    <td>{{ item.dealAmount }}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import http from "@/util/happyhouse";
export default {
    name: "index",
    data: function () {
        return {
            cheapesttop: [],
            mostExpensivetop: [],
        };
    },
    created() {
        console.log("통계 데이터 가져오는 중");
        http.get("/stats/rankingtop5")
            .then(({ data }) => {
                console.log("데이터 성공적으로 불러옴");
                console.log(data);
                this.cheapesttop = data.cheapesttop;
                this.mostExpensivetop = data.mostExpensivetop;
            })
            .catch(() => {
                console.log("데이터 가져오는 중 에러 발생");
            });
    },
};
</script>

<style>
img {
    width: 100%;
    object-fit: cover;
}

body {
    background-color: rgb(251, 243, 220);
    font-family: "경기천년제목 Light", Helvetica, sans-serif;
}

.ranking-title {
    text-align: center;
}

.mainranking {
    display: flex;
    align-items: center;
    justify-items: center;
    justify-content: center;
    flex-wrap: wrap;
    margin: auto;
}

.ranklist {
    width: 45%;
    max-width: 300px;
    margin: 20px 80px 20px;
    position: relative;
    border: 1px solid #ddd;
    border-top: 2px solid var(--main-fifth-color);
}

.list-title {
    border-bottom: 1px solid #ddd;
    position: relative;
    padding: 0 20px;
}

.list-title h2 {
    text-align: left;
    display: inline-block;
    font-size: 20px;
    line-height: 44px;
    height: 44px;
    letter-spacing: -1px;
}

.list-title h2 .top5 {
    margin: 5px;
    font-size: 14px;
    color: red;
}

.list-title a {
    position: absolute;
    text-decoration: none;
    text-align: right;
    right: 4px;
    color: #999;
    font-size: 11px;
    letter-spacing: -1px;
    height: 44px;
    line-height: 44px;
}

.list-content table {
    width: 100%;
    border-collapse: collapse;
    border-spacing: 0;
    text-align: center;
}
.list-content table thead tr {
    height: 30px;
}

.list-content table tbody tr {
    height: 36px;
}

.list-content table tbody tr > td:first-child {
    font-size: 20px;
    font-weight: 700;
    color: var(--main-fifth-color);
}
</style>
