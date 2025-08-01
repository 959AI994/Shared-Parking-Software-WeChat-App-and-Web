import $http from './config'
export default {
	// 添加反馈
	feedBackAdd(params) {
		return $http.post("/wx/feed-back/add",params)
	},
	
}

