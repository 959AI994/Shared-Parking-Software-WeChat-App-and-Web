<template>
	<view class="server-place">
		<map id='map' :show-compass="true"  ref='map' v-bind:style="{height: mapH + 'px'}" style="width: 100%;" :latitude="latitude"
			show-location :longitude="longitude" :controls='controls' :markers="markers" @regionchange='mapChange'
			@markertap='markertapFun'>     <!--点击地图时的回调信息-->
		</map>

		<!-- #ifndef APP-PLUS -->
<!-- 		<cover-image v-bind:style="{left: controlsLeft + 'px', top: controlsTop + 'px'}" class="controls-location"
			:src="positionIcon"></cover-image> -->
			
			
			<image @click="toMyLocation" style="width: 35px;height: 35px; position: absolute; left: 85%;top: 85%; " :src="myPositionIcon" mode=""></image>
			
			
			
		<!-- #endif -->
		
		<view>
			<u-popup mode="bottom" :mask="false" v-model="popupShow" length="60%" >
				
					<scroll-view scroll-y="true" style="height: 800rpx;">
						<view class="paringItem">
							<view  class="parkingTitle">
								{{parkingName}}
							</view>
							<!-- <u-line color="info" /> -->
							<view style="padding-bottom: 10rpx;" v-for="(item,index) in ParkingSpaceData" :key="index" >
								<view class="padingLeft">
									<view class="">
										<u-row gutter="16">
											<u-col span="10">
												<view class="" style="color: #55aaff;">
													车位号：{{item.parkingSpaceNumber}}
												</view>
											</u-col>
											<u-col span="2">
												<view class="">
													<u-button type="primary" @click="ewit(item.parkingSpaceId)" size="mini">预约</u-button>
													<!-- <navigator url="/navigate/navigate?title=navigate" hover-class="navigator-hover"> -->
													    <!-- <u-icon name="arrow-right"></u-icon> -->
													<!-- </navigator> -->
												</view>
											</u-col>
										</u-row>
									</view>
									<view class="">
										收费：{{item.price}}/小时
									</view>
									<view class="">
										开始共享时间：{{item.shareStartTime}} 
									</view>
									<view class="">
										结束共享时间：{{item.shareEndTime}}
									</view>
								</view>
								<u-line color="info" />
							</view>
						</view>
					</scroll-view>
			</u-popup>
		</view>
	</view>

</template>

<script>  //这边是接入腾讯地图api，这个api需要去官网注册key
	const app = getApp()
	var QQMapWX = require('./qqmap-wx-jssdk.min.js')
	var qqmapsdk = new QQMapWX({
		key: 'LXCBZ-NNIKD-UZ64F-H6AFI-UNJLH-OCFGE'
	})
	export default {
		props: {
			tipText: {
				type: String,
				default: '选择位置'
			},
			descText: {
				type: String,
				default: '使用当前定位或在地图上标记位置'
			},
			positionIcon: {
				type: String,
				default: 'https://s2.ax1x.com/2020/03/10/8CvKmt.png'
			},
			myPositionIcon: {
				type: String,
				default: '/static/mapPostion2.png'
				// default: 'https://s2.ax1x.com/2020/03/10/8CjxSJ.png'
			}
		},
		mounted: function() {
			this.getDbMapdata();//获取数据
			this.$nextTick(function() {
				this.myMap = uni.createMapContext("map", this); // 得到map对象
				this.getLocation()
				this.initMapH()
				this.initPositionIcon()
			})
		},
		data() {
			return {
				myMap: '',
				mapH: 0, // 地图高度，可在initMapH()中设置高度
				mapW: 0, // 屏幕宽度
				longitude: 0, // 初始经度
				latitude: 0, // 初始纬度
				myAddress: '', // 初始地址信息
				addressObj: { // 地图选点信息
					longitude: '',
					latitude: '',
					address: '请选择集合地点'
				},
				popupShow:false,
				// cover-image的默认 超出屏幕外 不然会有闪烁出现
				controlsLeft: 1000,
				controlsTop: 1000,
				controls: [], // 地图中心点图标, 可更换iconPath, 详情见官方文档关于map组件的介绍
				markers: [],//地图标记
				parkingName:'',//停车场名称
				ParkingSpaceData:[],//车位信息

			};
		},

		methods: {
			//获取后台小区场位置信息
			getDbMapdata(){
				this.$http.parkinginfo.getParkingInfoData(null).then(res => {
					
					console.log(666666,res.data)
					//这里就是定义一个数组然后遍历，将小区位置信息都放到数组里
					var array=[];
					for (var i = 0; i < res.data.length; i++) {
						var item={
							id:res.data[i].parkingInfoId,
							longitude:res.data[i].longitude,
							latitude: res.data[i].latitude,
							iconPath: '/static/markersIco.png',
							content:"22",
							width: 40,
							height: 40,
							label:{
								content:res.data[i].villageName,
								color:'#2f89ff',
								padding:7,
								textAlign: 'center',
								borderRadius:10,
							},
						
							callout: {
								content: res.data[i].spaceNumber,
								color:'#ffffff',
								textAlign: 'center',
								// bgColor:'#ffffff',
								bgColor:"#ff737a",
								borderRadius: 50,
								padding: 4,
								display: 'ALWAYS'
							}
						};
						array.push(item); //将小区信息放到数组里
					}
					this.markers=array;  //将地图信息赋给markers
				}).catch(err=>{
					console.log(err);
				})
			},
			
			// 初始化地图中心位置的定位图片
			initPositionIcon() {
				setTimeout(() => {
					// H5 微信小程序 使用<cover-image>
					// #ifndef APP-PLUS
					this.controlsLeft = this.mapW / 2 - 10
					this.controlsTop = this.mapH / 2
					// #endif

					// App使用map的controls
					// #ifdef APP-PLUS
					var controls = {
						id: '1',
						iconPath: this.positionIcon,
						position: {
							left: this.mapW / 2 - 10,
							top: this.mapH / 2 - 100,
							width: 30,
							height: 30,
						},
						clickable: false
					}
					this.controls.push(controls)
					console.log(this.controls, '==========');
					// #endif
				}, 100)
			},
			// 地图重新定位
			reposition() {
				console.log("地图重新定位")
				uni.createMapContext("isMap", this).moveToLocation({
					longitude: this.longitude,
					latitude: this.latitude,
				});
			},

			// 查询现在的位置
			getLocation() {
				let this_ = this
				uni.getLocation({
					type: 'gcj02', // 返回国测局坐标
					geocode: true,
					success: function(res) {

						this_.initMap(res)

						console.log(res);
					},
					fail: function(e) {
						uni.showToast({
							icon: 'none',
							title: '获取地址失败, 请看看定位权限开没开哈^_^'
						})
					}
				})
			},

			// 初始化我的位置
			async initMap(res) {
				this.longitude = res.longitude;
				this.latitude = res.latitude;
				this.myAddress = await this.getAddressName(res);

				this.addressObj = Object.assign({}, this.addressObj, {
					longitude: res.longitude,
					latitude: res.latitude,
					address: this.myAddress
				})
			},

			// 地图选择位置后 查询地点名称
			async checkMap(res) {
				this.addressObj = Object.assign({}, this.addressObj, {
					longitude: res.longitude,
					latitude: res.latitude,
					address: await this.getAddressName(res)
				})
				console.log('当前位置:' + res.longitude + '|' + res.latitude);
			},
			
			//点击选择车位
			ewit(e){
				this.$emit('getSpaceId', e)
			},

			// 监听地图位置变化
			mapChange(e) {
				let that = this
				clearTimeout(this.timer)
				this.timer = setTimeout(() => {
					if (e.type == 'regionchange' || e.type == 'end') {
						//that.mapCtx = uni.createMapContext('map', this)
						that.myMap.getCenterLocation({
							success: res => {
								this.checkMap(res)
							},
							fail: err => {
								console.log(err);
							}
						})
					}
				}, 200)
			},
			
			//点击标记点时触发，触发响应键后就会跳到这一步来
			markertapFun(e) {
				this.popupShow=true;  //如果能查到信息，就调用popupshow弹框来显示信息，然后把查到的信息渲染出来
				//用这个回调的markerid去查当前车位具体有多少信息，停车场的名字，日期等
				this.$http.parkinginfo.getSpaceServiceByIdList(e.detail.markerId).then(res => {
					if(res.data.length>0){  //如果查到信息了，就赋给parkinginfo
						this.parkingName=res.data[0].villageName;
						this.ParkingSpaceData=res.data;
					}else{
						this.parkingName="暂无共享车位";
						this.ParkingSpaceData=null;
					}
				}).catch(err=>{
					console.log(err);
				})
			
			},
			
			// 查询地图中心点的名称
			getAddressName(addressObj) {
				return new Promise((res) => {
					// #ifdef APP-PLUS
					qqmapsdk.reverseGeocoder({
						location: {
							latitude: addressObj.latitude,
							longitude: addressObj.longitude
						},
						get_poi: 1,
						poi_options: "page_size=1;page_index=1",
						output: 'jsonp',
						success: (e) => {
							console.log(e + ".....................")
							res(e.result.formatted_addresses.recommend);
						},
						fail: err => {
							res(err);
						}
					})
					// #endif

					// #ifdef H5
					// ======================== jsonp跨域 ======================== 
					const KEY = 'LXCBZ-NNIKD-UZ64F-H6AFI-UNJLH-OCFGE'
					let locationObj = addressObj.latitude + ',' + addressObj.longitude
					let url =
						'https://apis.map.qq.com/ws/geocoder/v1?coord_type=5&get_poi=1&output=jsonp&poi_options=page_size=1;page_index=1';
					this.$jsonp(url, {
							key: KEY,
							location: locationObj
						}).then(e => {
							res(e.result.formatted_addresses.recommend);
						})
						.catch(err => {
							res(err);
						})
					// #endif

					// #ifdef MP-WEIXIN
					console.log(addressObj, '======================');
					qqmapsdk.reverseGeocoder({
						location: {
							latitude: addressObj.latitude,
							longitude: addressObj.longitude
						},
						get_poi: 1,
						poi_options: "page_size=1;page_index=1",
						output: 'jsonp',
						success: (e) => {
							// console.log('--------------------->>>'+JSON.stringify(e))
							res(e.result.formatted_addresses.recommend);
						},
						fail: err => {
							res(err);
						}
					})
					// #endif


				})

			},
			
			// 计算地图的高度
			initMapH() {
				// #ifdef APP-PLUS
				this.mapW = uni.getSystemInfoSync().windowWidth
				this.mapH = uni.getSystemInfoSync().windowHeight - 210;
				// #endif
				// #ifndef APP-PLUS
				this.mapW = uni.getSystemInfoSync().windowWidth
				this.mapH = uni.getSystemInfoSync().windowHeight - 170;
				// #endif

				// #ifdef MP-WEIXIN
				this.mapW = uni.getSystemInfoSync().windowWidth
				this.mapH = uni.getSystemInfoSync().windowHeight+50;
				// #endif
			},
			
			// 移动到我的位置
			toMyLocation() {
				this.myMap.moveToLocation();

			},
			
			// 提交
			submitAdress() {
				this.controls = []
				setTimeout(() => {
					this.$emit('updateAddress', this.addressObj)
				}, 100)
			}
		},
	}
</script>

<style lang="scss" scoped>
	.controlszidin {
		height: 30px;
		width: 20px;
		position: absolute;
	}
	
	.controls-location {
		height: 30px;
		width: 30px;
		position: absolute;
	}
	.server-place {
		position: fixed;
		left: 0;
		top: 0;
		height: 100vh;
		width: 100%;
		background: #ffffff;
		z-index: 500;

		.map-tools {
			position: fixed;
			width: 100%;
			// bottom: 30rpx;
			left: 0;
			display: flex;
			justify-content: center;
			align-items: center;
			flex-direction: column;
			background-color: #fde7ff;

			.my-location {
				width: 90%;
				margin: 0 auto;
				height: 120rpx;
				box-shadow: 0px 3px 20px rgba(0, 0, 0, 0.2);
				background: #fff;
				border-radius: 20rpx;
				display: flex;
				justify-content: flex-start;
				align-items: center;
				overflow: hidden;

				.left {
					background: #3384ff;
					// flex: 20%;
					width: 120rpx;
					height: 100%;
				}

				.right {
					margin-left: 20rpx;
					color: #111;
					display: flex;
					justify-content: center;
					align-items: flex-start;
					flex-direction: column;

					.title {
						font-size: 28rpx;
						font-weight: bold;
					}

					.text {
						font-size: 26rpx;
						width: 500rpx;
						overflow: hidden;
						white-space: nowrap;
						text-overflow: ellipsis;
						color: #3384FF;
						margin-top: 10rpx;
					}
				}
			}

			.start-place {
				width: 85%;
				margin: 0 auto;
				margin: 0 auto;
				margin-top: 20rpx;
				box-shadow: 0px 3px 20px rgba(0, 0, 0, 0.2);
				background: #fff;
				border-radius: 20rpx;
				padding: 20rpx;

				.place {
					display: flex;
					justify-content: flex-start;
					align-items: center;

					.title {
						font-size: 28rpx;
						font-weight: bold;
						color: #111;
					}

					.text {
						font-size: 28rpx;
						color: #3384FF;
						font-weight: bold;
						width: 450rpx;
						display: inline-block;
						overflow: hidden;
						white-space: nowrap;
						text-overflow: ellipsis;
					}
				}

				.tip {
					font-size: 22rpx;
					color: #666;
					margin-top: 15rpx;
				}

				.sure {
					margin-top: 15rpx;
					color: #FFFFFF;
					background: #212121;
					font-weight: blod;
					font-size: 32rpx;
				}

			}
		}
		
		
		
	}
	.parkingTitle{
		padding: 6px;
		font-size: 15px;
		font-weight: 700;
	}
	.padingLeft{
		padding-left: 20rpx;
	}
</style>
