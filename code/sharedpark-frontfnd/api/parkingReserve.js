import $http from './config'
export default {

	// 查询我的预约信息
	getMyReservationInfo(params) {
		return $http.post("/wx/parkingOrder/getMyReservationInfo",params)
	},
	//进行中
	findMyForReservation(params) {
		return $http.post("/wx/parkingOrder/findMyForReservation",params)
	},
	//已完成
	findMyFinishReservation(params) {
		return $http.post("/wx/parkingOrder/findMyFinishReservation", params)
	},
	//添加车位预约
	addParkingReservation(params) {
		return $http.post("/wx/parkingOrder/add",params)
	},
	//查询一条预约订单
	getOrderInfo(params) {
		return $http.get("/wx/parkingOrder/getOrderInfo?orderId="+params)
	},
	//查询一条预约记录
	getAppointmentOrderInfo(params) {
		return $http.get("/wx/parkingOrder/getAppointmentOrderInfo?orderId="+params)
	},
	
	//确认进场
	confirmApproach(params) {
		return $http.get("/wx/parkingOrder/confirmApproach?orderId="+params)
	},
	//查询我的共享记录
	iSharedRecords(params) {
		return $http.get("/wx/parkingOrder/iSharedRecords",params)
	},
	//取消预约
	cancellation(params) {
		return $http.get("/wx/parkingOrder/cancellation?orderId="+params)
	},
	//是否进场
	whetherAdmission(params) {
		return $http.get("/wx/parkingOrder/whetherAdmission?orderId="+params)
	},
	//提交结算订单，返回订单信息
	submitSettlementOrder(params) {
		return $http.get("/wx/parkingOrder/submitSettlementOrder?orderId="+params)
	},
	//模拟支付接口
	paymentApi(params) {
		return $http.get("/wx/parkingOrder/payment?orderId="+params)
	},
}