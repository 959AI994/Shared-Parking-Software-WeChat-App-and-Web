import $http from './config'
export default {
	// 业主开启共享时间
	turnSharing(params) {
		return $http.post("/wx/parkingSpace/turnSharing",params)
	},
	// 业主关闭共享车位
	closeSharing(params) {
		return $http.post("/wx/parkingSpace/closeSharing",params)
	},
	
	//我的车位信息
	getMyParkingSpace(params) {
		return $http.get("/wx/parkingSpace/getMyParkingSpace", params)
	},
}
