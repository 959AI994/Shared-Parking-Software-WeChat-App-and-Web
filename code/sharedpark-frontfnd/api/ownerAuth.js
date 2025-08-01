import $http from './config'
export default {
	// 添加业主认证
	addOwnerAuth(params) {
		return $http.post("/wx/ownerAuth/add", params) //表单作为参数，然后post传出去
	},
	// 查询业主
	
	getOwnerInfo(params) {
		return $http.get("/wx/ownerAuth/getOwnerAuth", params)
	},
}

