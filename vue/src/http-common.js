import axios from "axios";

export default axios.create({
    baseURL: 'http://192.168.78.251:8090/api',
    headers: {
        "Content-type": "application/json",
    },
});