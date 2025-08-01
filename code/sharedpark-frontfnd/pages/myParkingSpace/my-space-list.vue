<template>
	<view>
		<view>
			<u-button type="primary" @click="addPersonalSpace">认证车位</u-button>
		</view>
		<view v-show="!spaceList">
			<u-empty text="无申请车位" mode="list"></u-empty>
		</view>
		
		<uni-list>
			<view @click="listClick(item)" v-for="(item,index) in spaceList" :key="index">
				<uni-list-item  :title="`${item.parkingSpaceNumber}  ${item.state }`" :note="item.parkingInfoId"></uni-list-item>
			</view>
		</uni-list>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				spaceList:[],
			}
		},
		methods: {
			listClick(e){
				console.log("点击了list",e);
				uni.navigateTo({
					url: "/pages/setShareTime/setShareTime?sId="+e.parkingSpaceId
				})
			},
			//添加车位
			addPersonalSpace(){
				console.log("添加车位")
				uni.navigateTo({
					url: "/pages/myParkingSpace/addParkingSpace"
				})
			},
			//获取我的车位列表
			getMySpaceList(){
				this.$http.parkingSpace.getMySpaceList().then(res => {
					console.log("获取我的车位列表",res.data)
					if (res.code == 200) {
						this.spaceList = res.data;
					}
				})
			}
			
		},
		created() {
			this.getMySpaceList();
		}
	}
</script>

<style>

</style>
