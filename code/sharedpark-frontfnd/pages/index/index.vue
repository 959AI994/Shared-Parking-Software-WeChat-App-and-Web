<template>
	<view class="container">


		<u-tabbar :list="tabbar"></u-tabbar>
		<!-- <button type="default" @click="testApi()">testApi</button> -->
	</view>
</template>

<script>
	import API from '../../api/parkinginfoApi.js'
	export default {
		data() {
			return {
				tabbar: '',
				formState: true,
				cur: 0,
				password: '',
				form: {
					name: '',
					intro: '',
					sex: ''
				},
			}
		},
		methods: {
			login() {
				uni.switchTab({
					url: "/pages/my/index"
				})
			},
			testApi() {
				// console.log('-------------------------------->>>'+this.serviceUrl);
				// var then=this;
				API.getParkingInfoData(null).then(res => {
					console.log(res);
				}).catch(err => {
					console.log(err);
				})
			}
		},
		onLoad() {
			/**
			 * 示例中为每个tabbar页面都写了一遍tabbar变量，您可以将tabbar数组写入到vuex中，这样可以全局引用
			 */
			this.tabbar = [{
					iconPath: "/static/template.png",
					selectedIconPath: "/static/templateHL.png",
					text: '首页',
					count: 2,
					isDot: true,
					pagePath: "/pages/index/index"
				},
				{
					iconPath: "/static/parkfinder.png",
					selectedIconPath: "/static/parkfinderHL.png",
					text: '找车位',
					midButton: true,
					pagePath: "/pages/parkfinder/index"
				},
				{
					iconPath: "/static/component.png",
					selectedIconPath: "/static/componentHL.png",
					text: '共享订单',
					pagePath: "/pages/order/index"
				},
				{
					iconPath: "/static/extui.png",
					selectedIconPath: "/static/extuiHL.png",
					text: '个人中心',
					pagePath: "/pages/my/index"
				},
			]
		}
	}
</script>

<style lang="scss">
	.container {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.topDiv {
		text-align: center;
		padding-top: 6rpx;
		color: #FFFFFF;
		width: 100%;
		height: 500rpx;
		background-color: #5464e2;
		// background-color: #5464e2 ;
	}

	.boutDiv {
		text-align: center;
		width: 100%;
		height: 700rpx;
		background-color: #FFFFFF;
		// background-color: #5464e2 ;
	}

	.containerDiv {
		margin: 0 auto;
		margin-top: -35%;
		width: 92%;
		height: 120%;
		border-radius: 9px;
		position: relative;
		background-color: #faf4f4;
	}

	.textHead {
		text-align: left;
		// background-color: #9572aa;
		line-height: 80rpx;
	}

	.textHead text {
		font-size: 35rpx;
		padding-left: 20px;
	}

	.titleImgDiv {
		position: absolute;
		top: -23%;
		right: 5%;
	}

	.titleImgDiv image {
		width: 340rpx;
		height: 340rpx;
	}

	// .bg {
	// 	position: relative;
	// 	width: 750rpx;
	// 	height: 400rpx;
	// }

	// .tab {
	// 	position: absolute;
	// 	top: 250rpx;
	// 	left: 20rpx;
	// 	right: 20rpx;
	// 	height: 150rpx;
	// 	padding: 0 50rpx;
	// 	background-color: #fff;
	// 	border-top-left-radius: 20rpx;
	// 	border-top-right-radius: 20rpx;

	// 	&-bg {
	// 		position: absolute;
	// 		top: -200rpx;
	// 		right: -50rpx;
	// 		width: 440rpx;
	// 		height: 285rpx;
	// 	}
	// }

	// .line {
	// 	width: 25rpx;
	// 	height: 7rpx;
	// }

	// .login, .register {
	// 	padding: 0 60rpx;
	// }

	// .input {
	// 	width: 630rpx;
	// 	height: 90rpx;
	// 	padding: 0 30rpx;
	// 	background-color: rgba(80, 100, 235, 0.1);
	// 	border-radius: 2%;

	// 	&-icon {
	// 		width: 30rpx;
	// 		height: 38rpx;
	// 	}

	// 	&-placeholder {
	// 		color: #5064eb;
	// 	}
	// }

	// .button {
	// 	width: 630rpx;
	// 	height: 90rpx;
	// 	margin-left: 60rpx;
	// 	border-radius: 3%;
	// }

	// .separator {
	// 	height: 2rpx;
	// 	margin: 0 30rpx;
	// 	background-color: #f5f5f5;
	// }

	// .other {
	// 	&-items {
	// 		padding: 0 200rpx;
	// 	}

	// 	&-icon {
	// 		width: 50rpx;
	// 		height: 50rpx;
	// 	}
	// }
</style>
