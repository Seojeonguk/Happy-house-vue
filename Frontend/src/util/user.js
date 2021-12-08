import axios from "axios";

export default axios.create({
    baseURL: "http://localhost:9999/happyhouse/user",
    headers: {
        "Content-type": "application/json",
    },
});
