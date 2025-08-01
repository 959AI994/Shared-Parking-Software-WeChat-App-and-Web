<template>
	<view>
		<u-empty v-if="itemData.length==0" text="数据为空" mode="data" margin-top="100"></u-empty>
		<view class="listItem" v-for="(item,index) in itemData" :key="index">
			<view class="" style="padding-left:18rpx ;" @click="seeOrder(item.orderId)">
				<view class="bordeItem">
					<u-row>
						<u-col span="10">
							<view @click="GenerateQR(item)">订单号：{{item.orderId}}</view>
						</u-col>
						<u-col span="2">
							<view>{{item.state}}</view>
						</u-col>
					</u-row>
				</view>
				<view class="bordeItem">

					<u-row>
						<u-col span="3">
							<view>车位号:</view>
						</u-col>
						<u-col span="7">
							<view>{{item.parkingSpaceNumber}}</view>
						</u-col>
					</u-row>
					<u-row>
						<u-col span="3">
							<view>小区名称：</view>
						</u-col>
						<u-col span="7" style="text-align: left;">
							<view>{{item.villageName}}</view>
						</u-col>
					</u-row>
					<u-row>
						<u-col span="3">
							<view>详细地址：</view>
						</u-col>
						<u-col span="8" style="text-align: left;">
							<view>{{item.address}}</view>
						</u-col>
					</u-row>
					<u-row v-if="item.approachTime!=null">
						<u-col span="3">
							<view>进场时间：</view>
						</u-col>
						<u-col span="7" style="text-align: left;">
							<view>{{item.approachTime}}</view>
						</u-col>
					</u-row>
					<u-row v-if="item.actualPlayingTime!=null">
						<u-col span="3">
							<view>出场时间：</view>
						</u-col>
						<u-col span="8" style="text-align: left;">
							<view>{{item.actualPlayingTime}}</view>
						</u-col>
					</u-row>
				</view>
				<view v-if="item.paymentAmount!=null? true:false">
					<u-row>
						<u-col span="3">
							<view>支付金额：</view>
						</u-col>
						<u-col span="6" style="text-align: left;">
							<view style="font-size:smaller; color: #007AFF;">{{item.paymentAmount}}元</view>
						</u-col>
					</u-row>
				</view>
				<view v-if="onState">
					<view v-if="item.state=='进行中'">
						<u-row>
							<u-col span="3">
								<view>创建时间:</view>
							</u-col>
							<u-col span="7">
								{{item.createTime}}
							</u-col>
						</u-row>
						<u-row>


							<u-col span="12" text-align="right">
								<view class="" style="padding-right: 25rpx;">
									<u-button type="primary" size="mini" v-if="item.approachTime==null"
										@click="approach(item.orderId)">确认进场</u-button>
									<u-button type="primary" size="mini" @click="NavigationAddress(item.longitude,item.latitude)">路线规划</u-button>
									<u-button type="info" size="mini" v-if="item.approachTime==null"
										@click="cancelReservation(item.orderId)">取消预约
									</u-button>
									<u-button type="info" size="mini" v-if="item.paymentAmount==null"
										@click="finishOrder(item.orderId)">结算订单</u-button>
								</view>
							</u-col>
						</u-row>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		name: "order-swiper-item",
		props: ["itemData", "tabState"],
		data() {
			return {
				endState: true,
				onState: true,
			};
		},
		methods: {
			//生成二维码
			GenerateQR(e){
				this.$emit('showQrcode', e)
			},
			//导航
			NavigationAddress(jd,wd) {
				console.log("nbuer",Number(jd),Number(wd))
				uni.getLocation({
					success(res) {
						uni.openLocation({
							latitude: Number(wd),
							longitude: Number(jd),
							scale: 15
						});
					}
				});
			},
			//确认进场
			approach(e) {
				this.$emit('itemGetOrderId', e)
			},
			//取消预约订单
			cancelReservation(e) {
				this.$emit('canceOrderId', e)
			},
			//结束订单
			finishOrder(e) {
				this.$emit('finishOrder', e)
			},
			//查看订单
			seeOrder(e) {
				uni.navigateTo({
					url: "/pages/orderSettlement/orderSettlement?orderId=" + e+"&state=see",
				});
			}
			
			
		},
		created() {

			if (this.tabState == '进行中') {
				this.endState = false;
			} else if (this.tabState == '已完成') {
				this.onState = false;
			} else if (this.tabState == '全部') {
				this.endState = true;
				this.onState = true;
			}

		}

	}
</script>

<style lang="scss">
	page {
		background: #f0f0f0;
	}

	.bordeItem {
		border-bottom: 1px #ececec solid;
		padding: 0 0 5rpx 0;
	}

	.item {
		border-bottom: 1px #ececec solid;
		padding: 20rpx 0;

	}

	// .top {
	// 	background: #FFFFFF;
	// 	height: 100rpx;
	// 	position: fixed;
	// 	left: 0;
	// 	top: 0rpx;
	// 	right: 0;
	// 	box-shadow: 0px 10px 10px -10px #efefef;
	// 	z-index: 999;
	// }

	.swiper-bottom {
		// margin-top: 1rpx;
		padding: 20rpx;
		width: 100%;
		height: 100%;
		position: relative;

	}

	.listItem {
		padding: 6px 0;
		margin-bottom: 10px;
		width: 100%;
		background-color: #fafafa;
	}

	.meniStyle {
		font-size: 16px;
		color: $u-type-primary;
	}
</style>
