<template>
	<view class="page-login">
		<view v-if="canIUse||canIGetUserProfile" class="uni-flex uni-column">
			<view class="uni-flex uni-row uni-view-center">
				<view class="">
					<image style="width: 140rpx; height: 140rpx;" src="../../static/markersIco.png"></image>
				</view>

			</view>
			<view class="uni-flex uni-row uni-view-center" style="margin-bottom: 30rpx;">
				<text class="appname">登录共享车位Parking</text>
			</view>
			<view class='uni-flex uni-row login-box'>
				<!-- <view>申请获取以下权限</view> -->
				<view><text>获得你的公开信息(昵称、头像、地区等)</text></view>

			</view>

			<view class="login-box">
				<!--新版登录方式-->
				<button v-if="canIGetUserProfile" class='login-btn' style="background-color: #007AFF;" type='primary'
					@click="bindGetUserInfo"> 授权登录
				</button>
				<!--旧版登录方式-->
				<button v-else class='login-btn' type='primary' open-type="getUserInfo" withCredentials="true"
					lang="zh_CN" @getuserinfo="bindGetUserInfo"> 授权登录2 </button>
			</view>
		</view>
		<view v-else class="text-center">
			请升级微信版本
		</view>
	</view>
</template>
<script>
	import req from '../../api/user.js'
	import UTIL from "@/util/baseUrl.js"
	export default {
		data() {
			return {
				sessionKey: '',
				openId: '',
				nickName: null,
				avatarUrl: null,
				userInfo: {},
				canIUse: uni.canIUse('button.open-type.getUserInfo'),
				canIGetUserProfile: false,
			};
		},
		onLoad() {

			// console.log('------->>'+JSON.stringify(this.$http.parkingSpace.getMyParkingSpace()));

			var _this = this;
			//console.log(uni.getUserProfile);
			if (uni.getUserProfile) {
				this.canIGetUserProfile = true;
			}
			//判断若是版本不支持新版则采用旧版登录方式
			//查看是否授权
			if (!this.canIGetUserProfile) {
				uni.getSetting({
					success: function(res) {
						if (res.authSetting['scope.userInfo']) {
							uni.getUserInfo({
								provider: 'weixin',
								success: function(res) {
									console.log(res + "查看是否授权>>>>>>");
									_this.userInfo = res.userInfo;
									try {
										_this.login();
									} catch (e) {}
								},
								fail(res) {}
							});
						} else {
							// 用户没有授权
							console.log('用户还没有授权');
						}
					}
				});
			}
		},
		onShow() {

		},
		methods: {
			//登录授权
			bindGetUserInfo(e) {
				var _this = this;
				if (this.canIGetUserProfile) {
					//新版登录方式
					uni.getUserProfile({
						desc: '登录',
						success: (res) => {
							console.log("登录微信数据：", res);
							_this.userInfo = res.userInfo;
							try {
								_this.login(res.userInfo);
							} catch (e) {}
						},
						fail: (res) => {
							console.log(res)
						}
					});
				} else {
					//旧版登录方式
					if (e.detail.userInfo) {
						//用户按了允许授权按钮
						console.log('手动旧版登录方式');
						_this.userInfo = e.detail.userInfo;

						try {
							_this.login();
						} catch (e) {}
					} else {
						console.log('用户拒绝了授权');
						//用户按了拒绝按钮
					}
				}
			},
			//登录
			login(userInfo) {
				let _this = this;
				// 获取登录用户code
				uni.login({
					provider: 'weixin',
					success: function(res) {
						console.log("获取CODE——————：", res);
						uni.showLoading({
							title: '登录中...'
						});
						if (res.code) {
							let code = res.code;
							let pams = userInfo;
							pams["code"] = code;
							
							console.log("登录中Code",pams)
							// uni.request({
							//     url: 'http://192.168.31.248:8888/wx/user/login', //仅为示例，并非真实接口地址。
							//     data: pams,
							// 	method: "POST",
							//     header: {
							//         'custom-header': 'hello' //自定义请求头信息
							//     },
							//     success: (res) => {
							//         console.log("res.data"+res.data);
							//     }
							// });
							

							_this.$http.login(pams)   //这个登陆直接调的API的
							
								.then(res => {
									if (res.code == 200) {          //登陆成功后，就把用户信息（这个信息是从接口来的）写到用户仓库store中去
										console.log("KKKKKKKKKK",res)
										// _this.$store.state.userInfo = userInfo;
										_this.$store.state.userData = userInfo;
										_this.$store.state.hasLogin = true;
										_this.$store.state.Token = res.data;
										uni.setStorageSync('Token', res.data)
										_this.querMyInfo() //初始化用户信息
									}
									if (res.code == 202) {
										uni.setStorageSync('Token', res.data)
										uni.navigateTo({
											url: "/pages/perfectUserInfo/perfectUserInfo"
										})
									}
								})
						} else {
							uni.showToast({
								title: '登录失败！',
								duration: 2000
							});
							console.log('登录失败！' + res.errMsg)
						}
					},
				});

			},
			//初始化我的信息
			querMyInfo() {

				this.$http.user.getUserInfo()
					.then(res => {
						var dao = res.data;
						this.$store.state.userData = dao;
						uni.setStorageSync('nickName', dao.nickName)
						uni.setStorageSync('avatarUrl', dao.avatarUrl)

						// uni.navigateBack({
						// 	success: () => {
						// 		let page = getCurrentPages().pop(); //跳转页面成功之后
						// 		if (!page) {
						// 			return;
						// 		} else {
						// 			page.onLoad(page.options); // page自带options对象.
						// 		}
						// 	}
						// })

						//关闭页面回到主页
						uni.reLaunch({
							url: '/pages/my/index'
						});
						uni.hideLoading();

					})

			},
			//向后台更新信息
			updateUserInfo() {
				uni.showLoading({
					title: '登录中...'
				});


				let _this = this;
				var params = {
					openId: _this.openId,
					nickName: _this.userInfo.nickName,
					avatarUrl: _this.userInfo.avatarUrl,
					gender: _this.userInfo.gender,
					city: _this.userInfo.city,
					province: _this.userInfo.province,
					country: _this.userInfo.country,
					unionId: '',
				}

				uni.reLaunch({
					url: '/pages/my/index'
				});
				uni.hideLoading();
				//console.log('登录');
				//...后台登录的接口
				console.log("", this.$store.state.hasLogin)
			}
		}
	}
</script>
<style>
	.appname {
		font-size: 39rpx;
		font-weight: 500;
	}

	.login-box {
		padding: 0 25px;
		/* margin-top: 50rpx; */
	}
</style>