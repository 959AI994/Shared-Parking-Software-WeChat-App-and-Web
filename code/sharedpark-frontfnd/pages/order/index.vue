<!-- 滑动切换选项卡演示(标准写法) -->
<!--  此demo使用了uView的tabsSwiper全屏选项卡 https://uviewui.com/components/tabsSwiper.html -->
<template>
	<view class="">


		<!-- 使用z-paging-swiper为根节点可以免计算高度 -->
		<z-paging-swiper>
			<!-- 此处代码复制了uView中tabsSwiper全屏选项卡的代码 -->
			<!-- 需要固定在顶部不滚动的view放在slot="top"的view中 -->
			<view slot="top">
				<u-tabs-swiper ref="uTabs" :list="tabList" :current="current" @change="tabsChange" :is-scroll="false"
					swiperWidth="750"></u-tabs-swiper>
			</view>
			<!-- swiper必须设置height:100%，因为swiper有默认的高度，只有设置高度100%才可以铺满页面  -->
			<swiper class="swiper" :current="swiperCurrent" @transition="transition" @animationfinish="animationfinish">
				<swiper-item class="swiper-item">
					<swiper-inthe-item :stateVal="timer" :currentIndex="swiperCurrent"
						@itemGetOrderId="itemGetOrderId()" @canceOrderId="canceOrderId()"
						@finishOrder="toFinishOrder()">
					</swiper-inthe-item>
				</swiper-item>
				<swiper-item class="swiper-item">
					<swiper-finish-item :stateVal="timer" :currentIndex="swiperCurrent">
					</swiper-finish-item>
				</swiper-item>
				<swiper-item class="swiper-item">
					<swiper-all-item :stateVal="timer" :currentIndex="swiperCurrent">
					</swiper-all-item>
				</swiper-item>
			</swiper>
		</z-paging-swiper>
		<u-tabbar :list="tabbar"></u-tabbar>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				timer: null, //利用更新子组件
				tabbar: '',
				tabList: [{
					name: '进行中'
				}, {
					name: '已完成'
				}, {
					name: '全部'
				}],
				// 因为内部的滑动机制限制，请将tabs组件和swiper组件的current用不同变量赋值
				current: 0, // tabs组件的current值，表示当前活动的tab选项
				swiperCurrent: 0, // swiper组件的current值，表示当前那个swiper-item是活动的
				allListData: [], //全部
				ongoListData: [], //进行中
				finishListData: [], //已完成
			};
		},
		methods: {
			// tabs通知swiper切换
			tabsChange(index) {
				console.log("tabs通知swiper切换")
				this.swiperCurrent = index;
			},
			// swiper-item左右移动，通知tabs的滑块跟随移动
			transition(e) {
				let dx = e.detail.dx;
				this.$refs.uTabs.setDx(dx);
			},
			// 由于swiper的内部机制问题，快速切换swiper不会触发dx的连续变化，需要在结束时重置状态
			// swiper滑动结束，分别设置tabs和swiper的状态
			animationfinish(e) {
				let current = e.detail.current;
				this.$refs.uTabs.setFinishCurrent(current);
				this.swiperCurrent = current;
				this.current = current;
			},
			//重新加载子组件
			handleLoad() {
				this.timer = new Date().getTime()
			},

			//查找全部的订单
			queryMyReservationInfo() {
				this.$http.parkingOrder.getMyReservationInfo().then(res => {
					this.allListData = res.data
				}).catch(err => {
					console.log(err);
				})

			},
			//查找进行中的订单
			queryMyForReservation() {
				this.$http.parkingOrder.findMyForReservation().then(res => {
					this.ongoListData = res.data
				}).catch(err => {
					console.log(err);
				})
			},
			//查找已完成的订单
			queryMyFinishReservation() {
				this.$http.parkingOrder.findMyFinishReservation().then(res => {
					this.finishListData = res.data
				}).catch(err => {
					console.log(err);
				})
			},
			//确认入场
			itemGetOrderId(orderId) {
				this.$http.parkingOrder.confirmApproach(orderId).then(res => {
					if (res.code == 200) {
						this.$tool.toast("进场成功")
						this.handleLoad()

					}
				}).catch(err => {
					console.log(err);
				})
			},
			//取消预约
			canceOrderId(orderId) {
				let _this = this
				uni.showModal({
					content: '确定取消预约',
					success: function(res) {
						if (res.confirm) {
							_this.$http.parkingOrder.cancellation(orderId).then(res => {
								if (res.code == 200) {
									_this.$tool.toast("取消预约成功")
									_this.handleLoad()
								}
							}).catch(err => {
								console.log(err);
							})
						}
					}
				});
			},
			//结束订单生成二维码
			toFinishOrder(e) {
				uni.navigateTo({
					url: "/pages/showQrcode/showQrcode?orderId=" + e,
				});
				
				//是否入场
				// this.$http.parkingOrder.whetherAdmission(e).then(res => {
				// 	if (res.data) {
				// 	} else {
				// 		this.$tool.toast("该订单还没入场")
				// 	}
				// }).catch(err => {
				// 	console.log(err);
				// })
			},
		},
		onLoad() {
			// this.tabbar = this.$store.state.tabbar; //从Vuex总取tabbar
			// this.isLogin()
			// console.log("测试2", uni.getStorageSync('token'))
			// this.initUserInfo()
			this.tabbar = this.$store.state.tabbar; //从Vuex总取tabbar
		},
		onShow() {
			if (!this.hasLogin) {
				this.handleLoad()
			} else {
				uni.navigateTo({
					url: "/pages/login/login"
				})
			}
		}
	}
</script>

<style>
	.swiper {
		height: 92%;
	}
</style>
