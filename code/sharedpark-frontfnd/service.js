import store from '@/store/index';
const token = store.state.Token;
const TOKEN_KEY = 'Token';
// 登录状态

const getToken = function () {
    let ret = uni.getStorageSync(TOKEN_KEY);
    return ret;
}
const addToken = function (token) {
    uni.setStorageSync(TOKEN_KEY, token);
}
export default {
    getToken,
    addToken
}
