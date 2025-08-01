import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

const store = new Vuex.Store({

	// {
	// 	iconPath: "/static/template.png",
	// 	selectedIconPath: "/static/templateHL.png",
	// 	text: '首页',
	// 	count: 2,
	// 	isDot: true,
	// 	pagePath: "/pages/index/index"
	// },

	state: {  //一个仓库，存了各种各样信息的
		tabbar: [{
			iconPath: "/static/parkfinder.png",
			selectedIconPath: "/static/parkfinderHL.png",
			text: '找车位',
			midButton: true,
			pagePath: "/pages/parkfinder/index"
		}, {
			iconPath: "/static/component.png",
			selectedIconPath: "/static/componentHL.png",
			text: '订单',
			pagePath: "/pages/order/index"
		}, {
			iconPath: "/static/extui.png",
			selectedIconPath: "/static/extuiHL.png",
			text: '个人中心',
			pagePath: "/pages/my/index"
		}, ],

		userInfo: null, //用户信息
		userData: null, //后台查询的用户数据
		hasLogin: false, //登录状态
		openID: null, //用户唯一标识
		Token: null,
		placeStateInfo: {}, //用户车位信息
	},

	mutations: { //登陆的时候用的这个mutations是个同步的方法
		login(state, provider) {
			state.hasLogin = true;
			state.userInfo = provider;
			uni.setStorageSync('userInfo', provider)
		},
		logout(state) {
			state.hasLogin = false;
			state.userInfo = {};
			uni.removeStorageSync('userInfo')
		}
	},

	actions: {
		// 退出
		logout({
			commit,
			state
		}) {

			state.userInfo = null
			state.hasLogin = null

			// return new Promise((resolve, reject) => {
			// 	uni.request({
			// 		url: 'http://localhost:8081/loginout',
			// 		method: 'GET',
			// 		header: {
			// 			'content-type': 'application/x-www-form-urlencoded',
			// 		},
			// 		success: function(res) {
			// 			state.userInfo = null
			// 			state.hasLogin = null
			// 			uni.reLaunch({
			// 				url: '/pages/index/index'
			// 			});
			// 		},
			// 		fail: function(error) {
			// 			console.log(error);
			// 		}
			// 	})
			// })
		},
	},
	getters: {
		getUserInfo(state) {
			return state.userInfo
		}
	}

});

export default store
