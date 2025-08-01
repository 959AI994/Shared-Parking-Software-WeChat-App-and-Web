import $http from './config'
export default {

	// 查询小区停车场全部信息
	getParkingInfoData(params) {
		return $http.get("/wx/parkingInfo/getListMap", "GET", params)
	},
	// 获取对应ID停车场车位
	getSpaceServiceByIdList(params) {
		return $http.get("/wx/parkingSpace/getSpaceServiceByIdList?id="+params, null)
	},
	// 获取车位信息
	getSpaceServiceById(params) {
		return $http.get("/wx/parkingSpace/getSpaceServiceById?id="+params, null)
	},
	
	//列表附近小区停车场
	getNearbyList(params) {
		return $http.post("/wx/parkingInfo/getNearbyList",params)
	},
	
	//查询停车场列表——选择器
	pickerInfo(params) {
		return $http.get("/wx/parkingInfo/pickerInfo",params)
	},
	
}

