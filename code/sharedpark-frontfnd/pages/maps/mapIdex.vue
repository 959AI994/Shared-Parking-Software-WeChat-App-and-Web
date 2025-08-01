<template>
	<view class="content">
		<mi-map v-if="mapShow" ref="miMap" tipText="mi-Map11" @updateAddress="updateAddress" @getSpaceId="getSpaceId">
		
		</mi-map>

	</view>
</template>


<script>
	import req from '../../api/parkinginfoApi.js'
	import miMap from '../../components/mi-map/mi-map.vue'
	export default {
		components: {
			miMap
		},
		data() {
			return {
				show: false,
				mapShow: true,
				dropdownvalue: '',
				positionObj: {},
				dataLsit: [],
				thisLongitude:'',
				thisLatitude:'',
				
			}
		},
		methods: {
			
			// 更新地址并关闭地图
			updateAddress(addressObj) {
				this.mapShow = false;
				this.positionObj = addressObj;
				this.show=true;
				// console.log(">>>>>", this.positionObj)
			},
			//获取到车位Id
			getSpaceId(da){
				// this.mapShow = false;//关闭地图
				uni.navigateTo({
				    url: "/pages/parkfinder/bookingSpace?id="+da,
				});
			},
			//加载停车场
			getParkingInfoList(){
				var parm={longitude: this.thisLongitude,latitude:this.thisLatitude}
				req.getNearbyList(parm).then(res => {
					this.dataLsit=res.data.data;
				}).catch(err=>{
					console.log(err);
				})
			},
			//获取当前位置
			getLocalTion(){
				var that_=this
				uni.getLocation({
				    type: 'gcj02',
				    success: function (res) {
				        console.log('当前位置的经度：' + res.longitude);
				        console.log('当前位置的纬度：' + res.latitude);
						that_.thisLatitude=res.latitude
						that_.thisLongitude=res.longitude
						that_.getParkingInfoList()
				    }
				});
			}
			

		},
		mounted: function() {
			// this.getLocalTion()
			
		},
		onLoad() {
					
		}
	}
</script>

<style>
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
		background-color: #ffffff;
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
