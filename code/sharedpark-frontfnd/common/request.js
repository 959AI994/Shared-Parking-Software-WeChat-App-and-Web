// 全局请求封装
import UTIL from "@/util/baseUrl.js"
import store from '@/store/index';


export default (url, method, params) => {
	const thet_ = this;
	// const token = uni.getStorageSync('Token')
	const token = store.state.Token;
	uni.showLoading({
		title: "加载中"
	});
	let header = !token ? {
		'content-type': 'application/x-www-form-urlencoded'
	} : {
		'content-type': 'application/x-www-form-urlencoded',
		'Token': token
	};
	return new Promise((resolve, reject) => {
		uni.request({
			url: UTIL.BAST_URL + url,
			method: method,
			header: header,
			data: {
				...params
			},
			success(res) {
				resolve(res);
			},
			fail(err) {
				reject(err);
			},
			complete() {
				uni.hideLoading();
			}
		});
	});


};
