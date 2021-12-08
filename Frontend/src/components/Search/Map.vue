<template>
    <gmap-map
        v-if="get_apt_list.length"
        ref="mapRef"
        :center="{ lat: get_apt_list[0].lat * 1, lng: get_apt_list[0].lng * 1 }"
        :zoom="16"
        style="height: 100%; width: 100%"
    >
        <gmap-marker
            v-for="(item, idx) in get_apt_list"
            :position="{ lat: item.lat * 1, lng: item.lng * 1 }"
            :key="`item_` + idx"
            :clickable="true"
            @click="markerclick(item)"
        >
        </gmap-marker>
        <gmap-info-window :position="infovalue.position" :opened="infoWinOpen" @closeclick="infoWinOpen = false">
            <div v-html="infovalue.content"></div>
        </gmap-info-window>
    </gmap-map>

    <gmap-map v-else ref="mapRef" :center="center" :zoom="16" style="height: 100%; width: 100%">
        <gmap-marker
            v-for="(item, idx) in get_apt_list"
            :position="{ lat: item.lat * 1, lng: item.lng * 1 }"
            :key="`item_` + idx"
            :clickable="true"
            @click="markerclick(item)"
        >
        </gmap-marker>
        <gmap-info-window :position="infovalue.position" :opened="infoWinOpen" @closeclick="infoWinOpen = false">
            <div v-html="infovalue.content"></div>
        </gmap-info-window>
    </gmap-map>
</template>

<script>
import { mapGetters } from "vuex";
export default {
    name: "googlemap",
    data() {
        return {
            center: {
                lat: 37.5343722 * 1,
                lng: 126.9688505 * 1,
            },
            infoWinOpen: false,
            infovalue: {
                content: "",
                position: this.center,
            },
        };
    },
    computed: {
        ...mapGetters(["get_apt_list"]),
    },
    methods: {
        markerclick(item) {
            console.log("markerclick");
            console.log(item);
            this.infovalue.content = item.aptName;
            let position = {
                lat: item.lat * 1 + 0.0005,
                lng: item.lng * 1,
            };
            this.infovalue.position = position;
            this.infoWinOpen = true;
        },
    },
};
</script>

<style></style>
