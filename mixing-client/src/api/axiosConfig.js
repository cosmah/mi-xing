import axios from 'axios';

export default axios.create({
    baseURL:'https://cdc1-41-75-190-66.ngrok-free.app',
    headers:{"ngrok-skip-browser-warning":"true"}
});