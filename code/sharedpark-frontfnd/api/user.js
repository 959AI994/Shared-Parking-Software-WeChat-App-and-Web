import $http from './config'
// import request from "@/common/request.js"
export default {
	// 用户登录
	// login(params) {
	// 	return request("/user/login", "POST", params)
	// },

	//获取用户信息
	getUserInfo(params) {
		return $http.get('/wx/user/getUser', params)
	},
	
	//获取用户数据
	getUserData(params) {
		return $http.get('/wx/user/getUser', params)
	},
	
	//用户完善信息
	userAuth(params) {
		return $http.post('/wx/user/userAuth', params)
	},
	
}
