<!-- 在这个文件对每个tab对应的列表进行渲染 -->
<template>
	<view class="content">
		<!-- 这里设置了z-paging加载时禁止自动调用reload方法，自行控制何时reload（懒加载）-->
		<!--  :enable-back-to-top="currentIndex===tabIndex" 在微信小程序上可以多加这一句，因为默认是允许点击返回顶部的，但是这个页面有多个scroll-view，会全部返回顶部，所以需要控制是当前index才允许点击返回顶部 -->
		<z-paging ref="paging" :enable-back-to-top="currentIndex===tabIndex" v-model="dataList" @query="queryList"
			:fixed="false" :auto="false">
			<!-- 如果希望其他view跟着页面滚动，可以放在z-paging标签内 -->
			<view>
				<!-- <u-empty v-if="dataList.length==0" text="数据为空" mode="data" margin-top="100"></u-empty> -->
				<view class="listItem" v-for="(item,index) in dataList" :key="index">
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
							<u-row v-if="item.actualPlayingTime!=null">
								<u-col span="3">
									<view>出场时间：</view>
								</u-col>
								<u-col span="8" style="text-align: left;">
									<view>{{item.actualPlayingTime}}</view>
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
							<u-row>
								<u-col span="3">
									<view>创建时间:</view>
								</u-col>
								<u-col span="7">
									{{item.createTime}}
								</u-col>
							</u-row>
						</view>
						<view v-if="item.paymentAmount!=null? true:false">
							<u-row>
								<u-col span="3">
									<view>支付金额：</view>
								</u-col>
								<u-col span="3" style="text-align: left;">
									<view>
									<text style="font-size:smaller; color: #007AFF;"> {{item.paymentAmount}}元</text>
									<text :style="item.paymentStatus=='未支付'?'font-size:smaller;font-weight: 700;color: #ff5356; padding-left: 5%;':'font-size:smaller;font-weight: 700;color: #4174ff; padding-left: 5%;'" >{{item.paymentStatus}}</text>
									</view>
								</u-col>
								
							</u-row>	
						</view>
						<view v-if="onState">
							<view v-if="item.state=='进行中'">		
								<u-row>
									<u-col span="12" text-align="right">
										<view class="" style="padding-right: 25rpx;">
											<!-- <u-button type="primary" size="mini" v-if="item.approachTime==null"
												@click="approach(item.orderId)">确认进场</u-button> -->
											<u-button type="primary" size="mini"
												@click="NavigationAddress(item.longitude,item.latitude)">路线规划</u-button>
										<!-- 	<u-button type="info" size="mini" v-if="item.approachTime==null"
												@click="cancelReservation(item.orderId)">取消预约
											</u-button>
											-->
											<u-button type="info" size="mini" v-if="item.paymentAmount==null"
												@click="finishOrder(item.orderId)">生成二维码</u-button> 
										</view>
									</u-col>
								</u-row>
							</view>
						</view>
					</view>
				</view>
			</view>
		</z-paging>

	</view>

</template>

<script>
	export default {
		name: "swiper-all-item",
		data() {
			return {
				//v-model绑定的这个变量不要在分页请求结束中自己赋值！！！
				endState: true,
				onState: true,
				dataList: [],
				firstLoaded: false
			}
		},
		props: {
			//当前组件的index，也就是当前组件是swiper中的第几个
			tabIndex: {
				type: Number,
				default: function() {
					return 0
				}
			},
			//当前swiper切换到第几个index
			currentIndex: {
				type: Number,
				default: function() {
					return 0
				}
			},
			//数据更新标识
			stateVal: {
				type: Number,
				default: function() {
					return 0
				}
			}
		},
		watch: {
			currentIndex: {
				handler(newVal) {
					if (newVal === this.tabIndex) {
						//懒加载，当滑动到当前的item时，才去加载
						if (!this.firstLoaded) {
							setTimeout(() => {
								this.$refs.paging.reload();
							}, 5);
						}
					}
				},
				immediate: true
			},
			stateVal: {
				handler(newVal) {
					if(newVal!=null){
						// this.$refs.paging.reload();
						this.queryList(1,10)
					}
					
				},
				immediate: true
			},
		},
		methods: {
			queryList(pageNo, pageSize) {
				//组件加载时会自动触发此方法，因此默认页面加载时会自动触发，无需手动调用
				//这里的pageNo和pageSize会自动计算好，直接传给服务器即可
				//模拟请求服务器获取分页数据，请替换成自己的网络请求
				// type: this.tabIndex + 1
				const params = {
					current: pageNo,
					pageSize: pageSize
				}
				//获取数据
				this.$http.parkingOrder.findMyForReservation(params).then(res => {
					console.log("dddd",res)
					this.$refs.paging.complete(res.data.records);
					this.firstLoaded = true;
				}).catch(err => {
					this.$refs.paging.complete(false);
					console.log(err);
				})
			},
			//导航
			NavigationAddress(jd, wd) {
				console.log("nbuer", Number(jd), Number(wd))
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
					url: "/pages/orderSettlement/orderSettlement?orderId=" + e + "&state=see",
				});
			}
		},
		created() {
			console.log('重新加载啦')
		}
	}
</script>

<style lang="scss">
	/* 注意:父节点需要固定高度，z-paging的height:100%才会生效 */
	.content {
		height: 100%;
	}

	/* 	.item {
		position: relative;
		height: 150rpx;
		display: flex;
		align-items: center;
		justify-content: space-between;
		padding: 0rpx 30rpx;
	}

	.item-detail {
		padding: 5rpx 15rpx;
		border-radius: 10rpx;
		font-size: 28rpx;
		color: white;
		background-color: #007AFF;
	}

	.item-line {
		position: absolute;
		bottom: 0rpx;
		left: 0rpx;
		height: 1px;
		width: 100%;
		background-color: #eeeeee;
	} */
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
