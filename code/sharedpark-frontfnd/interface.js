import _config from './config'; // 导入私有配置
// 这里service是自己做的本地信息存储封装的一个配置文件，方便管理使用
import service from './service'; // 这里是之前项目配置本地存储账号信息的，不需要可以删除

export default function $http(options) {
	// 进行url字符串拼接，_config.url是再config中配置要请求的域名或者id+端口号这样方便管理，
	// options.url是index中请求配置的，完美拼接
	options.url = _config.url + options.url;
	return new Promise((resolve, reject) => {
		// 拦截请求
		var TOKEN = service.getToken()
		if (TOKEN) {
			console.log("interFace-TOken", TOKEN)
			_config.header.Token = TOKEN //这里是调用本地存储配置文件的方法不需要可以改下面这种方式
			//_config.header.Authorization=uni.getStorageSync(STATE_KEY);
		}
		// else{
		// 	console.log("无TOKEN")
		// 	uni.navigateTo({
		// 		url: '/pages/login/login'
		// 	})
		// }
		_config.complete = (response) => {
			// 登录失效这边后台是返回403看情况
			if (response.data.code === 403) {
				//返回登录界面
				uni.navigateTo({
					url: '/pages/login/login'
				})
				uni.showToast({
					icon: 'none',
					title: '登录已失效'
				});
				//清空token
				service.addToken('')
			}
			//  request請求访问成功  这里返回的时候response.data才是请求返回的结果
			if (response.data.code === 200) {
				// 接口请求成功
				resolve(response.data);
			} else if (response.data.code === 401){
				console.log("401")
			}else{
				// 接口请求失败
				resolve(response.data)
				// 处理catch 请求，不在本页面之外处理，统一在这里处理
				if (options.handle) {
					reject(response)
				} else {
					try {
						Promise.reject(response).catch(err => {
							// console.error("ereeee",err);  
							_page_error(response.statusCode || response.errMsg);
						});
					} catch (e) {
						console.log(e)
					}
				}
			}

		}
		// 开始请求
		uni.request(Object.assign({}, _config, options));
	})
}

// 接口错误
function _error(err, msg = '') {
	switch (err) {
		case 400:
			console.error("400",msg)
			// 错误码400的处理方式
			break;
	}
}
// request 错误
function _page_error(err) {
	switch (err) {
		case 401:
			// 错误码404的处理方式
			console.error("401请求背拒绝")
			break;
		case 404:
			// 错误码404的处理方式
			console.error("404没有找到页面")
			break;
		case 405:
			console.error("405错误的请求")
			break;
	}
}
