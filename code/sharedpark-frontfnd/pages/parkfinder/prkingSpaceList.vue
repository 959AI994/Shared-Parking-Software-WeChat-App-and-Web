<template>

<view class="content">
	<view class="paringItem">
		<view  class="parkingTitle">
			{{parkingName}}
		</view>
		<view class="itemClass" style="padding: 15rpx;" v-for="(item,index) in ParkingSpaceData" :key="index" >
			<view class="padingLeft">
				<view class="">
					<u-row gutter="16">
						<u-col span="9">
							<view class="">
								车位号：{{item.parkingSpaceNumber}}
							</view>
						</u-col>
						<u-col span="3">
							<view class="">
								<u-button type="primary" @click="getSpaceId(item.parkingSpaceId)" size="mini">预约</u-button>
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
			<u-line color="#e5e5e5" />
			
		</view>
		
	</view>
</view>
</template>
<script>
	export default {
		mounted: function() {
			this.findByParkingSpaceList()
		},
		data() {
			return {
				parkingName:'',
				parkingInfoId:null,
				ParkingSpaceData:[],
			};
		},
		onLoad(option) {
			//获取车位列表传过来的车位ID
			this.parkingInfoId=option.id;
		},
		methods: {
			getSpaceId(da) {
				// this.mapShow = false;//关闭地图
				if (!this.hasLogin) {
					uni.navigateTo({
						url: "/pages/parkfinder/bookingSpace?id=" + da,
					});
				}else{
					uni.navigateTo({
						url: "/pages/login/login"
					})
				}
				
			},
			seeOrder() {
				uni.switchTab({
					url: "/pages/order/index"
				})
	
			},
			findByParkingSpaceList(){
				this.$http.parkinginfo.getSpaceServiceByIdList(this.parkingInfoId).then(res => {
					
					this.ParkingSpaceData=res.data;
					
				}).catch(err=>{
					console.log(err);
				})
			}
		}
	}
</script>
<style lang="scss">
	.itemClass{
		background-color: #FFFFFF;
	}
	.padingLeft{
		padding-left: 25rpx;
	}
	
</style>
