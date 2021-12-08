<template>
    <div id="chart"></div>
</template>

<script>
import axios from "axios";
import * as d3 from "d3";
import * as topojson from "topojson-client";
import sampledata from "@/assets/marker.json";
export default {
    name: "chart",
    data: function () {
        return {
            avgDong: [],
            buildYearDong: [],
        };
    },
    created() {
        console.log("통계 데이터 가져 오는중");
        axios
            .get("http://localhost:9999/happyhouse/stats/show")
            .then(({ data }) => {
                console.log(data);
                console.log("통계 데이터 성공적으로 가져옴");
                this.avgDong = data.avgDong;
                this.buildYearDong = data.buildYearDong;
                this.cheapest = data.cheapest;
                this.mostExpensive = data.mostExpensive;
                console.log(this.avgDong);
                console.log("데이터 저장");
                this.drawing();
            })
            .catch(() => {
                console.log("통계 데이터를 가져 오는중 에러발생");
            });
    },
    methods: {
        drawing() {
            console.log("drawing시작");
            let avg = this.avgDong;
            let build = this.buildYearDong;
            const svgWidth = 1000;
            const svgHeight = 800;
            let svg = d3.select("#chart").append("svg").attr("width", svgWidth).attr("height", svgHeight);
            var map = svg.append("g").attr("id", "map").attr("width", svgWidth).attr("height", svgHeight);
            var projection = d3
                .geoMercator()
                .center([126.9895, 37.5651])
                .scale(130000)
                .translate([svgWidth / 2, svgHeight / 2]);
            var path = d3.geoPath().projection(projection);
            console.log(sampledata.objects.seoul_municipalities_geo);
            var features = topojson.feature(sampledata, sampledata.objects.seoul_municipalities_geo).features;
            console.log("feat");
            console.log(features);
            map.selectAll("path")
                .data(features)
                .enter()
                .append("path")
                .attr("class", function (d) {
                    console.log();
                    return "municipality c" + d.properties.SIG_CD;
                })
                .attr("d", path);

            console.log(build);
            // map.selectAll("text")
            //                 .data(features)
            //                 .enter()
            //                 .append("text")
            //                 .attr("transform", function (d) {
            //                     return "translate(" + path.centroid(d) + ")";
            //                 })
            //                 .attr("dy", ".35em")
            //                 .attr("class", "municipality-label")
            //                 .text(function (d) {
            //                     let str = d.properties.SIG_KOR_NM;
            //                     str += "\n  평균 매물가 : ";
            //                     avg.forEach((element) => {
            //                         if (element.code == d.properties.SIG_CD) {
            //                             str += element.dealAmount;
            //                         }
            //                     });

            //                     str += "\n 평균 건축연도 : ";
            //                     build.forEach((element) => {
            //                         if (element.code == d.properties.SIG_CD) {
            //                             str += element.buildYear;
            //                         }
            //                     });

            //                     return str;
            //                 });
            console.log("text1");
            map.selectAll("text")
                .data(features)
                .enter()
                .append("text")
                .attr("transform", function (d) {
                    return "translate(" + path.centroid(d) + ")";
                })
                .attr("dy", "-0.65em")
                .attr("dx", "3.0em")
                .attr("class", "municipality-label")
                .text(function (d) {
                    let str = d.properties.SIG_KOR_NM;

                    return str;
                })
                .append("tspan")
                .attr("dy", "1.85em")
                .attr("dx", "-5.0em")
                .text(function (d) {
                    let str = "";
                    str += "\n  평균 매물가 : ";
                    avg.forEach((element) => {
                        if (element.code == d.properties.SIG_CD) {
                            str += element.dealAmount;
                        }
                    });

                    return str;
                })
                .append("tspan")
                .attr("dy", "1.85em")
                .attr("dx", "-10.8em")
                .text(function (d) {
                    let str = "";
                    str += "\n  평균 건축연도 : ";
                    build.forEach((element) => {
                        if (element.code == d.properties.SIG_CD) {
                            str += element.buildYear;
                        }
                    });

                    return str;
                });
        },
        return_list() {
            return this.avgDong;
        },
    },
};
</script>

<style>
svg circle {
    fill: orange;
    opacity: 0.5;
    stroke: white;
}
svg circle:hover {
    fill: red;
    stroke: #333;
}
svg text {
    pointer-events: none;
}
svg .municipality {
    fill: #efefef;
    stroke: #fff;
}
svg .municipality-label {
    font-weight: 300;
    text-anchor: middle;
}
svg #map text {
    color: black;
    font-size: 10px;
    text-anchor: middle;
    -webkit-user-modify: read-write-plaintext-only;
}
svg #places text {
    color: #777;
    font: 10px sans-serif;
    text-anchor: start;
}
#title {
    font-family: sans-serif;
}
#title p {
    font-size: 10pt;
}

svg {
    transform: translate(40%);
}

svg .c11680,
svg .c11650 {
    fill: rgb(250, 140, 140);
}

svg .c11170,
svg .c11710 {
    fill: rgb(250, 150, 150);
}

svg .c11200,
svg .c11440,
svg .c11215 {
    fill: rgb(250, 160, 160);
}

svg .c11590,
svg .c11470 {
    fill: rgb(250, 170, 170);
}

svg .c11140,
svg .c11110,
svg .c11560,
svg .c11740 {
    fill: rgb(250 180 180);
}

svg .c11410 {
    fill: rgb(250 190 190);
}

svg .c11500,
svg .c11290,
svg .c11230,
svg .c11620,
svg .c11380,
svg .c11530 {
    fill: rgb(250 200 200);
}

svg .c11305,
svg .c11260,
svg .c11350,
svg .c11545,
svg .c11320 {
    fill: rgb(250 210 210);
}
</style>
