<template>
	<view style="padding: 8rpx 0rpx 0rpx 25rpx;">
		<u-row gutter="16">
			<u-col span="3" text-align="right">
				<view class="">
					订单编号：
				</view>
			</u-col>
			<u-col span="7">
				<view class="">
					{{orderInfo.orderId}}
				</view>
			</u-col>
		</u-row>
		<u-row gutter="16">
			<u-col span="3" text-align="right">
				<view class="">
					预约车位：
				</view>
			</u-col>
			<u-col span="8">
				<view class="">
					{{orderInfo.parkingSpaceNumber}}
				</view>
			</u-col>
		</u-row>

		<u-row gutter="16">
			<u-col span="3" text-align="right">
				<view class="">
					停车小区：
				</view>
			</u-col>
			<u-col span="8">
				<view class="">
					{{orderInfo.villageName}}
				</view>
			</u-col>
		</u-row>
		<u-row gutter="16">
			<u-col span="3" text-align="right">
				<view class="">
					车位价格：
				</view>
			</u-col>
			<u-col span="8">
				<view class="">
					{{orderInfo.price}}元/小时
				</view>
			</u-col>
		</u-row>
		<u-row gutter="16" v-if="orderInfo.parkingDuration!=null">
			<u-col span="3" text-align="right">
				<view class="">
					停车用时：
				</view>
			</u-col>
			<u-col span="8">
				<view class="">
					{{orderInfo.parkingDuration}}
				</view>
			</u-col>
		</u-row>
		<u-row gutter="16" v-if="orderInfo.paymentAmount!=null">
			<u-col span="3" text-align="right">
				<view class="">
					支付金额：
				</view>
			</u-col>
			<u-col span="8">
				<view class="">
					{{orderInfo.paymentAmount}}元
				</view>
			</u-col>
		</u-row>
		<u-row gutter="16">
			<u-col span="3" text-align="right">
				<view class="">
					创建时间：
				</view>
			</u-col>
			<u-col span="8">
				<view class="">
					{{orderInfo.createTime}}
				</view>
			</u-col>
		</u-row>
		<u-row gutter="16" v-if="orderInfo.approachTime!=null">
			<u-col span="3" text-align="right">
				<view class="">
					进场时间：
				</view>
			</u-col>
			<u-col span="8">
				<view class="">
					{{orderInfo.approachTime}}
				</view>
			</u-col>
		</u-row>

		<u-row gutter="16" v-if="orderInfo.actualPlayingTime!=null">
			<u-col span="3" text-align="right">
				<view class="">
					出场时间：
				</view>
			</u-col>
			<u-col span="8">
				<view class="">
					{{orderInfo.actualPlayingTime}}
				</view>
			</u-col>
		</u-row>
		<u-row gutter="16">
			<u-col span="3" text-align="right">
				<view class="">
					所在地区：
				</view>
			</u-col>
			<u-col span="9">
				<view class="">
					{{orderInfo.province}}{{orderInfo.city}}{{orderInfo.area}}
				</view>
			</u-col>
		</u-row>
		<u-row gutter="16">
			<u-col span="3" text-align="right">
				<view class="">
					具体地址：
				</view>
			</u-col>
			<u-col span="9">
				<view class="">
					{{orderInfo.address}}
				</view>
			</u-col>
		</u-row>

		<u-row gutter="12">
			<view class="" v-if="orderInfo.approachTime!=null">
				
				<u-col span="12" v-if="orderInfo.actualPlayingTime!=null">
					
					<u-button type="primary" v-if="orderInfo.paymentStatus!='已支付'"  @click="paymentClick(orderInfo.paymentAmount,orderInfo.orderId)">立即支付
					</u-button>
					
					
				</u-col>
				
				<u-col span="12" v-else>
					<u-button type="primary">订单进行中</u-button>
				</u-col>
			</view>

			<view class="" v-if="orderInfo.approachTime==null">
				<u-button type="primary" @click="canceOrderId(orderInfo.orderId)">取消订单</u-button>
			</view>

			<u-col span="12" v-if="orderInfo.paymentStatus=='已支付'">
				<u-button type="primary">已支付</u-button>
			</u-col>

		</u-row>


	</view>
</template>

<script>
	export default {
		data() {
			return {
				orderInfo: {}, //订单信息
			}
		},
		methods: {

			//修改状态结算金额
			commitOrder(e) {
				this.$http.parkingOrder.submitSettlementOrder(e).then(res => {
					if (res.code == 200) {
						// this.$tool.toast("订单信息")
						this.orderInfo = res.data
					} else {
						this.$tool.toast(res.msg)
					}
				}).catch(err => {
					console.log(err);
				})
			},

			//查看订单
			seeOrder(e) {

				this.$http.parkingOrder.getAppointmentOrderInfo(e).then(res => {
					if (res.code == 200) {
						// this.$tool.toast("订单信息")
						this.orderInfo = res.data
					} else {
						this.$tool.toast(res.msg)
					}
				}).catch(err => {
					console.log(err);
				})
			},

			//判断查看还是结算
			isState(option) {
				if (option.state == "see") {
					this.seeOrder(option.orderId);
				} else {
					//结算订单
					this.commitOrder(option.orderId);
				}
			},
			//取消预约
			canceOrderId(orderId) {
				let _this = this
				uni.showModal({
					content: '确定取消预约',
					success: function(res) {
						if (res.confirm) {
							_this.$http.parkingOrder.cancellation(orderId).then(res => {
								if(res.code==200){
									_this.$tool.toast("取消预约成功")
									
									uni.switchTab({
										url: "/pages/order/index"
									})
									// uni.navigateBack({
									// 	delta: 1,
									// 	success: function() {
									// 		let page = getCurrentPages().pop(); //跳转页面成功之后
									// 		if (!page) return;
									// 		page.onLoad(); //如果页面存在，则重新刷新页面
									// 	}
									// });
								}else{
									_this.$tool.toast(res.msg)
								}
								
								
							}).catch(err => {
								console.log(err);
							})
						}
					}
				});
			},
			//模拟支付
			paymentClick(e, orderId) {
				let _this = this
				uni.showModal({
					title: '支付订单',
					content: e + "元",
					success: function(res) {
				  if (res.confirm) {
							_this.$http.parkingOrder.paymentApi(orderId).then(res => {
								if (res.code == 200) {
									_this.$tool.toast("支付成功！")
									uni.switchTab({
										url: "/pages/order/index"
									})
									// uni.navigateBack({
									// 	delta: 1,
									// 	success: function() {
									// 		let page = getCurrentPages().pop(); //跳转页面成功之后
									// 		if (!page) return;
									// 		page.onLoad(); //如果页面存在，则重新刷新页面
									// 	}
									// });
									
									_this.$tool.toast("支付成功！")
								}else{
									_this.$tool.toast(res.msg)
								}

							}).catch(err => {
								console.log(err);
							})
							console.log('用户点击确认支付');
						} else if (res.cancel) {
				   console.log('用户点击取消支付');
				  }
					}
				});
			}
		},
		onLoad(option) {
			this.isState(option)
		}
	}
</script>

<style>

</style>
