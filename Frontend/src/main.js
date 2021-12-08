import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

Vue.config.productionTip = false;

import * as VueGoogleMaps from "vue2-google-maps";

Vue.use(VueGoogleMaps, {
    load: {
        key: "AIzaSyBrz3i2Zfzk5QTlHdpz4fhqNsZg5CKKS9c",
        libraries: "places",
    },
});

import { library } from "@fortawesome/fontawesome-svg-core";
import {
    faAngleRight as fasAngleRight,
    faAngleLeft as fasAngleLeft,
    faSearch as fasSearch,
    faHome as fasHome,
    faStar as fasStar,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

library.add(fasAngleRight, fasAngleLeft, fasSearch, fasHome, fasStar);

Vue.component("font-awesome-icon", FontAwesomeIcon);

new Vue({
    router,
    store,
    render: (h) => h(App),
}).$mount("#app");
