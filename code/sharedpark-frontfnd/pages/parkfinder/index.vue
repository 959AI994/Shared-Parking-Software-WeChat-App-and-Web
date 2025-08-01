<template>
	<view class="content">
		<view class="topfloat" style="width: 100%;">
			<view class="searchDiv">
				<view style="display: flex;flex-direction: row;width: 100%;justify-content: center;align-items: center;">
					<text>{{thisCity}}</text>
					<u-search style="flex: 1;" placeholder="搜索附近小区" v-model="villageName" @blur="searchBlur" @change="searchChange"
							:show-action="false">
						</u-search>
						<u-button type="primary" icon="map" size="mini" @click="searchMap()">地图</u-button>
				</view>
			</view>
			<view class="dropdownDiv" style="z-index: 9999999; padding-top: 10rpx;">
				<!-- <u-dropdown>
					<u-dropdown-item v-model="dropdownvalue" title="全部地区" :options="options1"></u-dropdown-item>
					<u-dropdown-item v-model="dropdownvalue" title="综合排序" :options="options1"></u-dropdown-item>
					<u-dropdown-item v-model="dropdownvalue" title="已开通" :options="options1"></u-dropdown-item>
				</u-dropdown> -->
			</view>
		</view>
		<view class="listDiv">

			<u-empty v-if="dataLsit.length==0" text="数据为空" mode="list"></u-empty>

			<view class="itemClass" v-for="(item,index) in dataLsit" :key="index"
				style="border-bottom: 1px solid #e5e5e5;">
				<!-- <navigator url="/pages/parkfinder/prkingSpaceList?id=2" hover-class="other-navigator-hover"> -->
				<u-row>
					<u-col span="10">
						<view class="" @click="findByparkingSpace(item.parkingInfoId)">
							<view class="padingBot">
								<label class="parktitle">{{item.villageName}}</label>|<text
									class="textStyle">{{item.distance}}km</text>
							</view>
							<view class="padingBot">
								<u-icon name="car" color="#2979ff" size="26"></u-icon>
								<text class="textStyle">剩余空位数</text> <text
									class="parknum">{{item.remainingSpace}}</text>
							</view>
							<view class="">
								<u-icon name="map" color="#2979ff" size="30"></u-icon>
								<!-- {{item.province}}{{item.city}}{{item.area}} -->
								<text class="textStyle">{{item.area}}{{item.address}}</text>
							</view>
						</view>
					</u-col>

					<u-col span="2" align="centre">

						<view class="pricetext">
							<!-- {{item.sharePrice}}元/h -->
						</view>
					</u-col>
				</u-row>
				<!-- </navigator> -->
			</view>

		</view>

		<!-- <u-modal v-model="show" :content="positionObj"></u-modal> -->

		<mi-map v-if="mapShow" ref="miMap" tipText="mi-Map11" @updateAddress="updateAddress" @getSpaceId="getSpaceId">

		</mi-map>

		<u-tabbar :list="tabbar"></u-tabbar>
	</view>
</template>


<script>
	var QQMapWX = require("@/components/mi-map/qqmap-wx-jssdk.min.js")
	var qqmapsdk = new QQMapWX({
		key: 'LXCBZ-NNIKD-UZ64F-H6AFI-UNJLH-OCFGE'
	})
	import miMap from '../../components/mi-map/mi-map.vue'
	export default {
		components: {
			miMap
		},
		data() {
			return {
				thisCity:'',
				show: false,
				mapShow: false,
				dropdownvalue: '',
				positionObj: {},
				villageName: '', //搜索框值
				tabbar: '',
				dataLsit: [],
				thisLongitude: '',
				thisLatitude: '',
				options1: [{
						label: '默认排序',
						value: 1,
					},
					{
						label: '距离优先',
						value: 2,
					},
					{
						label: '价格优先',
						value: 3,
					}
				],
			}
		},
		methods: {
			//对应小区的停车位
			findByparkingSpace(e) {
				uni.navigateTo({
					url: "/pages/parkfinder/prkingSpaceList?id=" + e
				})
			},
			// 打开地图
			searchMap() {
				uni.navigateTo({
					url: "/pages/maps/mapIdex"
				})
			},
			// 更新地址并关闭地图
			updateAddress(addressObj) {
				this.mapShow = false;
				this.positionObj = addressObj;
				this.show = true;
			},
			//获取到车位Id
			getSpaceId(da) {
				uni.navigateTo({
					url: "/pages/parkfinder/bookingSpace?id=" + da,
				});
			},
			//加载停车场
			getParkingInfoList() {
				var parm = {
					longitude: this.thisLongitude,
					latitude: this.thisLatitude,
					villageName: this.villageName
				}
				this.$http.parkinginfo.getNearbyList(parm).then(res => {
					this.dataLsit = res.data;
				}).catch(err => {
					console.log(err);
				})
			},
			//获取当前位置
			getLocalTion() {
				var that_ = this
				uni.getLocation({
					type: 'gcj02',
					success: function(res) {
						console.log('当前位置的经度：' + res.longitude);
						console.log('当前位置的纬度：' + res.latitude);
						that_.thisLatitude = res.latitude
						that_.thisLongitude = res.longitude
						that_.getParkingInfoList()
						
						var loc = res.latitude + "," + res.longitude;
						qqmapsdk.reverseGeocoder({
							location: loc,
							success: function(res) {
								that_.thisCity=res.result.address_component.city
								// console.log('->>>获取当前城市坐标', res.result.address_component.city);
							},
							fail: function(error) {
								console.error(	);
							},
							complete: function(res) {
								console.log(res);
							}
						});
					},
					// fail: function() {
					// 	uni.showToast({
					// 		title: '获取地址失败，请查看是否开启位置信息',
					// 		icon: 'none'
					// 	});
					// }
				});
			},
			
			
			//搜索
			searchChange(e) {
				console.log(e)
				this.villageName = e;
				this.getLocalTion()
			},
			//失去焦点
			searchBlur(e) {
				console.log("获取", e)
				if (e == null || e == "") {
					this.getLocalTion()
				}
			}


		},
		mounted: function() {

		},
		onLoad() {
			this.tabbar = this.$store.state.tabbar; //从Vuex总取tabbar

		},
		onShow() {
			this.getLocalTion()
		}
	}
</script>

<style>
	page {
		background: #f0f0f0;
	}

	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.topfloat {
		position: sticky;
		/* #ifdef H5 */
		top: 44px;
		/* #endif */
		/* #ifndef H5 */
		top: 0;
		/* #endif */
		z-index: 999;
		flex: 1;
		flex-direction: column;
		overflow: hidden;
		background-color: #fafafa;
	}

	.searchDiv {
		margin: 0 auto;
		padding-top: 10rpx;
		width: 95%;
	}

	.listDiv {
		width: 100%;
		height: 920rpx;
	}

	.dropdownDiv {
		margin: 0 auto;
		width: 100%;
	}

	.padingBot {
		padding-bottom: 6px;
	}

	.textStyle {
		padding-left: 10rpx;
		font-size: 26rpx;
	}

	.parktitle {
		padding-right: 15rpx;
		font-size: 30rpx;
		font-weight: 600;
	}

	.parknum {
		padding-left: 20rpx;
		font-weight: 600;
	}

	.itemClass {
		padding: 10px 20px;
		border-bottom: 1px #ececec solid;
	}

	.pricetext {
		height: 40rpx;
		line-height: 40rpx;
		margin: 0 auto;
		text-align: center;
		font-size: 33rpx;
	}
</style>
