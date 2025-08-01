<template>
	<view class="content">
		<view class="bigdiv">
			<view class="caridItem">
				<view class="topfloatTitle">
					预约请在15分钟内入场，否则会自动取消订单！
					<!-- <u-notice-bar direction="row" text="预约请在15分钟内入场，否则会自动取消订单！"></u-notice-bar> -->
				</view>
				<view class="placeid">
					车位号：{{SpaceInfo.parkingSpaceNumber}}
				</view>
			</view>
			<view class="inttimeItem lineCenter pad">

				<uni-row>
					<uni-col>订单号：{{resSpaceInfo.orderId}}</uni-col>
				</uni-row>
				<u-row>
					<u-col span="3">
						<text>创建时间:</text>
					</u-col>
					<u-col span="7">
						<text>{{resSpaceInfo.createTime}}</text>
					</u-col>
				</u-row>
			
				<uni-row>
					<uni-col>小区名称：{{SpaceInfo.villageName}}</uni-col>
				</uni-row>
				<uni-row>
					<uni-col>地址：{{SpaceInfo.city}}{{SpaceInfo.area}}{{SpaceInfo.address}}</uni-col>
				</uni-row>
			</view>
			<view class="explainItem">

				<view class=""> <text class="tip">温馨提示</text> </view>
				<view class="">
					<p>1.为了避免影响他人停车，请在停车服务时间结束前提前立场，<u-link href="" :under-line="true">
							超过将收取额外的服务费</u-link>
					</p>
				</view>
			</view>


			<view class="boufrom">
				<u-button size="medium" type="info" @click="seeOrder()">查看订单</u-button>
				<u-button size="medium" type="primary" @click="NavigationAddress(SpaceInfo.longitude,SpaceInfo.latitude)" style="margin-left:15px ;">导航</u-button>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				reqOrderId:null,
				SpaceInfo: {},
				resSpaceInfo: {}//查询回来的订单信息
			};
		},
		methods: {
			seeOrder() {
				uni.switchTab({
					url: "/pages/order/index"
				})

			},
			getOrderInfoOnlasd() {
				this.$http.parkingOrder.getOrderInfo(this.reqOrderId).then(res => {
					this.resSpaceInfo=res.data
				})
			},
			//导航
			NavigationAddress(jd,wd) {
				console.log("Number",Number(jd),Number(wd))
				uni.getLocation({
					success(res) {
						uni.openLocation({
							latitude: Number(wd),
							longitude: Number(jd),
							scale: 15
						});
					}
				});
			}
		},
		mounted() {
				this.getOrderInfoOnlasd()
		},
		onLoad(option) {
			//获取车位列表传过来的车位ID
			this.SpaceInfo = option;
			this.reqOrderId=option.orderId
			console.log(option)
		}
	}
</script>

<style lang="scss">
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.bigdiv {
		width: 100%;
		padding: 20rpx;
	}

	page {
		background-color: #f4f4f4;
	}

	.caridItem {
		// width: 100%;
		height: 200rpx;
		line-height: 60rpx;
		text-align: center;
		margin: 0 auto;
		background-color: #ffffff;
	}

	.placeid {
		margin: 0 auto;
		font-size: 40rpx;
		font-weight: 600;
		padding-top: 20px;
	}

	.topfloatTitle {
		// margin: 0 auto;
		width: 100%;
		background-color: #f3e090;
	}

	.parkname {
		font-size: 33rpx;
		line-height: 90rpx;
	}

	.lineCenter {
		line-height: 40px;
	}

	.inttimeItem {
		background-color: #fdfdfd;
	}

	.pad {
		padding-left: 20rpx;
	}

	.explainItem {
		padding: 15px 10px;
		margin-top: 20rpx;
		line-height: 20px;
		background-color: #fdfdfd;
	}

	.formtext {
		color: #bebebe;
	}

	.tip {
		color: red;
	}

	.boufrom {
		padding-top: 20px;
		text-align: center;
	}
</style>
