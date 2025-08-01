<template>
	<view class="content">
		<view class="bigdiv" style="width: 100%;">
			<view class="caridItem">
				<view class="carid">
					{{userData.carNumber}}
				</view>
			</view>
			<view class="inttimeItem lineCenter pad">
				<u-line color="#e4e7ed" style="margin: 16rpx 0;" />
				<u-row>
					<u-col span="3">
						<text>预约车位号：</text>
					</u-col>
					<u-col span="8">
						<!-- <view class="" @tap="toggleTabEnd()" style="color: #0084ff;">{{enddate}}</view> -->
						<view><text>{{findInfo.parkingSpaceNumber}}</text></view>
					</u-col>
				</u-row>
				<u-line color="#e4e7ed" style="margin: 16rpx 0;" />

				<u-row>
					<u-col span="4">
						<text>>10分钟收费：</text>
					</u-col>
					<u-col span="8">
						<view><text>{{findInfo.price}}元/小时</text></view>
					</u-col>
				</u-row>
				<u-row>
					<u-col span="4">
						<text>开始共享时间：</text>
					</u-col>
					<u-col span="8">
						<view><text>{{findInfo.shareStartTime}}</text></view>
					</u-col>
				</u-row>
				<u-row>
					<u-col span="4">
						<text>结束共享时间：</text>
					</u-col>
					<u-col span="8">
						<view><text>{{findInfo.shareEndTime}}</text></view>
					</u-col>
				</u-row>

				<u-line color="#e4e7ed" style="margin: 16rpx 0;" />
				<view class="pad" style="padding-bottom: 20rpx;">
					<text class="parkname">{{findInfo.villageName}}</text>
					<u-section @click="NavigationAddress(findInfo.longitude,findInfo.latitude)"
						:title="findInfo.city+findInfo.area+findInfo.address" :bold="false" :show-line="false" sub-title="导航"></u-section>
				</view>


			</view>
			<view class="explainItem">

				<view class=""> <text class="tip">温馨提示</text> </view>
				<view class=""> <text class="">1.15分钟内可免费取消,停车10分钟内收取3块钱费用</text> </view>
				<view class=""> <text class="">2.超出车位共享时间的部分按照单价3倍收取服务费</text> </view>
			</view>


			<view class="" style="padding: 0rpx 10rpx;">
				<!-- <navigator url="/pages/parkfinder/reserveSuccess" open-type="switchTab" hover-class="other-navigator-hover"> -->
				<u-button type="primary" @click="Submit()">立即预定</u-button>
				<!-- </navigator> -->
			</view>
			<yu-datetime-picker ref="dateTime" startYear="2021" value="" :isAll="false" :current="false"
				@confirm="onConfirm">
			</yu-datetime-picker>
			<yu-datetime-picker ref="dateTimeEnd" startYear="2021" value="" :isAll="false" :current="false"
				@confirm="onConfirmEnd">
			</yu-datetime-picker>
			<!-- <u-modal v-model="confirmShow" content="确认预约"></u-modal> -->
			<u-toast ref="uToast" />
		</view>

	</view>
</template>

<script>
	import yuDatetimePicker from "@/components/yu-datetime-picker/yu-datetime-picker.vue"
	import {
		mapState
	} from 'vuex';
	export default {
		components: {
			yuDatetimePicker
		},
		data() {
			return {
				confirmShow: false,
				spaceID: '', //车位ID
				findInfo: {},
				date: '',
				startdate: '请选择入场时间',
				enddate: '点击选择出场时间',
				form: {
					name: '',
					intro: '',
					sex: ''
				},
			};
		},
		onLoad(option) {
			//获取车位列表传过来的车位ID
			this.spaceID = option.id;
			this.getSpaceInfo(option.id);

		},
		computed: {
			...mapState(['hasLogin', 'userData', "userInfo"])
		},
		methods: {
			toggleTab(item, index) {
				this.$refs.dateTime.show();
			},
			toggleTabEnd(item, index) {
				this.$refs.dateTimeEnd.show();
			},
			onConfirm(val) {
				this.startdate = val.selectRes;
			},
			onConfirmEnd(val) {
				this.enddate = val.selectRes;
			},
			//查询车位信息
			getSpaceInfo(id) {
				this.$http.parkinginfo.getSpaceServiceById(id).then(res => {
					this.findInfo = res.data;
				}).catch(err => {
					console.log(err);
				})

			},

			//提交数据
			Submit(id) {
				this.confirmOrder(id);
			},
			//封装提示方法
			showToastFuntion(title) {
				uni.showToast({
					title: title,
					icon: 'none',
					duration: 2000
				});
			},

			//确认预约
			confirmOrder(id) {
				// "approachTime": this.startdate,
				// "playingTime": this.enddate
				var parm = {
					"parkingSpaceId": this.spaceID
				};
				var that = this;
				uni.showModal({
					title: '提示',
					content: '是否确定预约？',
					success(res) {
						if (res.confirm) {
							uni.showLoading({
								title: '确定中...',
							});
							that.$http.parkingOrder.addParkingReservation(parm).then(res => {
								if (res.code == 200) {
									var orderId = res.data
									console.log("orderId", orderId)
									console.log("this.spaceID", that.spaceID)
									uni.navigateTo({
										url: "/pages/parkfinder/reserveSuccess?parkingSpaceNumber=" +
											that
											.findInfo.parkingSpaceNumber +
											"&villageName=" + that.findInfo.villageName +
											"&address=" + that.findInfo.address+
											"&city=" + that.findInfo.city+
											"&area=" + that.findInfo.area + "&longitude=" +
											that.findInfo.longitude + "&latitude=" + that.findInfo
											.latitude + "&startdate=" + that.startdate +
											"&enddate=" + that.enddate + "&orderId=" + orderId
									})
								} else if(res.code == 407){
									uni.hideLoading()
									// that.$tool.msg(res.msg)
									
									uni.showModal({
									    title: '您有正在进行中的订单',
									    content: '是否查看',
									    success: function (res) {
									        if (res.confirm) {
												uni.switchTab({
													url: "/pages/order/index"
												})
									            console.log('用户点击确定');
									        } else if (res.cancel) {
									            console.log('用户点击取消');
									        }
									    }
									});

								}else{
									uni.hideLoading()
									that.$tool.msg(res.msg)
								}
								
							}).catch(err => {
								console.log(err);
							})
						} else if (res.cancel) {
							// that.$refs.uToast.show({
							// 	title: '取消确定',
							// 	duration: 2000
							// })
						}
					},
					fail() {
						that.$refs.uToast.show({
							title: '确认送达失败',
							duration: 2000
						})
					}
				})
			},
			//导航
			NavigationAddress(jd, wd) {
				console.log("Number", Number(jd), Number(wd))
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

	page {
		background-color: #f4f4f4;
	}

	.caridItem {
		// width: 100%;
		text-align: center;
		margin: 50rpx 0;
		// background-color: #F0AD4E;
	}

	.carid {
		width: 40%;
		margin: 0 auto;
		font-size: 50rpx;
		background-color: #637aff;
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

	.tip {
		color: red;
	}
</style>
