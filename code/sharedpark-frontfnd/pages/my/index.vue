<template>
	<view>

		<!--主体-->
		<view class="meMain">
			<image src="../../static/icon/meBg.png"></image>
			<view class="mePosition">
				<view class="meMainBox">

					<!--头部-->
					<view class="meHead">
						<view class="meHeadAvatar">
							<image :src="userData.avatarUrl||'../../static/icon/avatar.png'"></image>
						</view>
						<view class="meHeadName">      <!--这个haslogin是下面用户仓拿上来的，如果登陆了就直接显示用户名，没登录就view立即登陆-->
							<text@click="login()">{{ hasLogin? userData.nickName  : '立即登录' }}</text>
						</view>
					</view>
					<!--头部-->

					<!--车牌-->
					<view class="meOverBg">
						<view class="meVisitor">
							<view class="meVisitorLt">                  <!--这个resUser就是从下面的data，return来的-->
								<view class="meVisitorTxt_02" style="font-size: 23px;">{{resUser.carNumber || '车辆未认证'}}
								</view>
								<!-- <view class="meVisitorTxt_01">模板1</view> -->
							</view>
							<view class="meVisitorLt">
								<view class="meVisitorTxt_02">{{resUser.balance || '0'}}</view>
								<view class="meVisitorTxt_01">余额</view>
							</view>
						</view>
					</view>


					<!--其他-->
					<view class="meOverBg">
						<view class="businessList" @click="toMyparkingSpace">
							<view class="businessListTxt">
								<image src="../../static/icon/chewei.png"></image>我的车位
							</view>
							<view class="businessListYou">
								<image src="../../static/icon/you.png"></image>
							</view>
						</view>

						<view class="businessList" @click="toSharedRecords">
							<view class="businessListTxt">
								<image src="../../static/icon/gongxaing.png"></image>共享记录
							</view>
							<view class="businessListYou">
								<image src="../../static/icon/you.png"></image>
							</view>
						</view>
					</view>
					<!--其他-->

					<!--其他-->
					<view class="meOverBg">
						<!-- 	<view class="businessList">
							<view class="businessListTxt">
								<image src="../../static/icon/meIcon_01.png"></image>月租缴费
							</view>
							<view class="businessListYou">
								<image src="../../static/icon/you.png"></image>
							</view>
						</view> -->
						<view class="businessList" @click="scanCode()">
							<view class="businessListTxt">
								<image src="../../static/icon/saoyisao.png"></image>扫一扫
							</view>
							<view class="businessListYou">
								<image src="../../static/icon/you.png"></image>
							</view>
						</view>
						<view class="businessList" @click="toApplyForOwnerPage()">
							<view class="businessListTxt">
								<image src="../../static/icon/yezhu.png"></image>申请业主
							</view>
							<view class="businessListYou">
								<image src="../../static/icon/you.png"></image>
							</view>
						</view>
						<view class="businessList" @click="toFeedBackVo">
							<view class="businessListTxt">
								<image src="../../static/icon/fankui.png"></image>用户反馈
							</view>
							<view class="businessListYou">
								<image src="../../static/icon/you.png"></image>
							</view>
						</view>
					</view>
				</view>
			</view>
		</view>

		<u-tabbar :list="tabbar"></u-tabbar>
	</view>
</template>

<script>
	import reqResever from '../../api/parkinginfoApi.js'
	import {
		mapState
	} from 'vuex';
	export default {
		data() {
			return {   //这些用户实例数据，return出去，然后上面的view页面就可以进行使用
				resUser:{},
				tabbar: '',
				avatarUrl: '../../static/icon/avatar.png', //头像
				nickName: '点击登录',
				token: '',
				loginState: '',
				placeState: false,
				placeStateInfo: {
					parkingSpaceNumber: '',
					isTakeUp: '',
				}

			}
		},
		onShow() {
			// this.token = uni.getStorageSync('token');

		},
		filters: {
			// 是否占用，0：占用，1：未占用"
			formatStatus(value) {
				if (value == 0) {
					return "共享中";
				} else if (value == 1) {
					return "未共享";
				}
			},
		},
		computed: {  //登陆成功后才会点击到这个js页面来，然后用这个computed，从数据仓库里把数据拿出来，然后页面就可以直接使用了
			...mapState(['hasLogin', 'userData', "userInfo"])
		},
		methods: {   //这个methods中定义的方法都是用来给上面代码用的，基本都是用来交互响应的东西
			//当用户点击不同的响应键时，随之调用不同的方法
			//扫一扫
			scanCode() {
				uni.scanCode({
					success: function(res) {
						console.log('条码类型：' + res.scanType);
						console.log('条码内容：' + res.result);
						if (res.result != null) {
							uni.navigateTo({
								url: "/pages/orderSettlement/orderSettlement?orderId=" + res.result+ "&state=see"
							});
						}

					}
				});
			},
			//共享记录
			toSharedRecords() {
				uni.navigateTo({
					url: "/pages/shared_records/shared_records"
				})
			},
			//登录页面
			login() {
				if (!this.hasLogin) {
					console.log("!this.hasLogin!this.hasLogin")
					uni.navigateTo({
						url: "/pages/login/login"
					})
				}
			},
			//申请业主
			toApplyForOwnerPage() {
				if (this.hasLogin) {
					uni.navigateTo({
						url: "/pages/toApplyForOwner/toApplyForOwner"
					})
				}
			},

			//我的车位
			toMyparkingSpace() {
				if (this.hasLogin) {
					uni.navigateTo({
						url: "/pages/myParkingSpace/myParkingSpace"
					})
				}

			},
			//用户反馈
			toFeedBackVo() {
				if (this.hasLogin) {
					uni.navigateTo({
						url: "/pages/feedBack/feedBack"
					})
				}
			},
			//查询我的车位状态
			queryMyReserveState() {
				this.$http.parkingSpace.getMyParkingSpace()
					.then(res => {
						if (res.data != null) {
							this.placeState = true
							this.placeStateInfo = res.data
							this.$store.state.placeStateInfo = res.data;
						}
					}).catch(err => {
						console.log(err);
					})

			},

			//用户是否登录
			isLogin() {  //自定义的判断用户是否登陆的函数
				// var sign = uni.getStorageSync('Token')
				var sign = this.$store.state.Token
				if (sign) {
					console.log("在登录状态1")
				}
			},
			
			//获取用户信息
			getUserIf() {
				var sign = this.$store.state.Token
				if (sign) {
					console.log("在登录状态2")
					this.$http.user.getUserData()
					.then(res => {
						this.resUser=res.data
					}).catch(err => {
						console.log(err);
					})
				}
			},
			
			//查询初始化用户信息
			initUserInfo() {
				// this.avatarUrl=this.$store.state.userInfo.avatarUrl
				// this.nickName=this.$store.state.userInfo.nickName
				// this.avatarUrl = uni.getStorageSync('avatarUrl')
				// this.nickName = uni.getStorageSync('nickName')
				// this.queryMyReserveState()//获取车位信息
			}
		},
		onLoad() {
			// this.tabbar = this.$store.state.tabbar; //从Vuex总取tabbar
			// this.isLogin()
			// console.log("测试2", uni.getStorageSync('token'))
			// this.initUserInfo()
			this.tabbar = this.$store.state.tabbar; //从Vuex总取tabbar
		},
		created() {//view实例创建以后先走这个created生命周期函数

		},
		onShow() {   //页面一点开，先加载这个钩子函数，然后从钩子函数里面调用不同的方法
			this.isLogin()
			this.getUserIf()
		}
	}
</script>

<style>
	page {
		background: #F5F5F5;
	}

	.meMain {
		width: 100%;
		position: relative;
	}

	.meMain>image {
		width: 100%;
		height: 220upx;
		display: block;
	}

	.meMainBox {
		width: 92%;
		margin: 0 auto;
	}

	.mePosition {
		position: absolute;
		top: 0;
		left: 0;
		width: 100%;
	}

	.meHead {
		overflow: hidden;
		position: relative;
	}

	.meHeadAvatar {
		float: left;
		width: 19%;
		margin-top: 10upx;
	}

	.meHeadAvatar image {
		width: 110upx;
		height: 110upx;
		display: block;
		border-radius: 50%;
	}

	.meHeadName {
		float: left;
		width: 81%;
		line-height: 120upx;
		color: #FFFFFF;
		font-size: 28upx;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
	}

	.meOverBg {
		background: #FFFFFF;
		overflow: hidden;
		border-radius: 12upx;
		margin-top: 30upx;
	}

	.meVisitor {
		display: flex;
		flex-direction: row;
	}

	.meVisitor2 {
		display: flex;
		flex-direction: column;
		padding-left: 25rpx;
		padding-bottom: 10rpx;
	}

	.meVisitorLt {
		width: 50%;
		text-align: center;
		margin: 30upx 0;
	}

	.meVisitorLt:nth-child(1) {
		border-left: 1px #F5F5F5 solid;
		border-right: 1px #F5F5F5 solid;
	}

	.meVisitorTxt_01 {
		font-size: 28upx;
		color: #666666;
	}

	.meVisitorTxt_01 image {
		width: 35upx;
		height: 35upx;
		vertical-align: middle;
		margin: 0 10upx 4upx 0;
	}

	.meVisitorTxt_02 {
		font-size: 30upx;
		color: #3B7ED5;
	}

	.meVisitorTitle {
		font-size: 30upx;
		color: #333333;
		border-bottom: 1px #F5F5F5 solid;
		padding: 20upx 30upx;
	}

	.meOrderLt {
		width: 25%;
		text-align: center;
		padding: 30upx 0;
		transition: all 0.4s;
	}

	.meOrderLt:active {
		background: #E2E2E2;
	}

	.meOrderTxt_01 {}

	.meOrderTxt_01 image {
		width: 40upx;
		height: 40upx;
		display: block;
		margin: 0 auto 6upx;
	}

	.meOrderTxt_02 {
		font-size: 24upx;
		color: #666666;
	}

	.businessList {
		overflow: hidden;
		padding: 30upx;
		border-bottom: 1px #F5F5F5 solid;
		transition: all 0.4s;
	}

	.businessList:active {
		background: #E2E2E2;
	}

	.businessList:last-child {
		border-bottom: none;
	}

	.businessListTxt {
		float: left;
		font-size: 28upx;
		color: #333333;
	}

	.businessListTxt image {
		width: 35upx;
		height: 35upx;
		vertical-align: middle;
		margin: 0 15upx 4upx 0;
	}

	.businessListYou {
		float: right;
		font-size: 28upx;
		color: #333333;
	}

	.businessListYou image {
		width: 20upx;
		height: 20upx;
		vertical-align: middle;
		margin: 0 0 4upx 10upx;
	}
</style>
