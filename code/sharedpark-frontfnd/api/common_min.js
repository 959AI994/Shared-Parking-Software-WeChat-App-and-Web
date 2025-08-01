import $http from './config'

import ownerAuth from './ownerAuth.js';
import parkinginfo from './parkinginfoApi.js';
import parkingOrder from './parkingReserve.js';
import parkingSpace from './parkingSpace.js';
import user from './user.js';
import feedBack from './feedBack.js';
/**
 * handle [boolean] 如果需要自己处理 catch 请求 ，传入 true ，交给接口统一处理 ，请传如 false或不传
 */
export default {
	//用户登录
	login(params) {
		return $http.post('/wx/user/login', params)
	},
	deleteImg(params) {
		return $http.get('/wx/common/deleteImg?path='+params)
	},

	ownerAuth,//业主
	parkinginfo,//小区停车场
	parkingOrder,//订单
	parkingSpace,//车位
	user,//用户
	feedBack,//反馈
}
