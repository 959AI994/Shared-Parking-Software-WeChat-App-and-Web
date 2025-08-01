<template>
	<view class="center">
		<view class="" v-if="isPlacePage">
			<view class="title">
			{{placeStateInfo.state=='共享中'?'关闭车位共享':'设置车位共享'}}
			</view>
			<view class="shareFrom">
				<view class="flexRow">
					<view class="">
						车位号：
					</view>
					<view class="">
						{{placeStateInfo.parkingSpaceNumber}}
					</view>
				</view>
				<view class="flexRow">
					<view class="">
						小区：
					</view>
					<view class="">
						{{placeStateInfo.villageName}}
					</view>
				</view>
				<view class="flexRow">
					<view class="">
						使用状态：
					</view>
					<view class="">
						{{placeStateInfo.isTakeUp|formatStatus}}
					</view>
				</view>
			</view>
			<view class="shareFrom" v-if="placeStateInfo.state=='未共享'">
				<yu-datetime-picker ref="dateTime" startYear="2021" value="" :isAll="false" :current="false"
					@confirm="onConfirm">
				</yu-datetime-picker>
				<yu-datetime-picker ref="dateTimeEnd" startYear="2021" value="" :isAll="false" :current="false"
					@confirm="onConfirmEnd">
				</yu-datetime-picker>
				<view class="">
					<view class="flexRow">
						<view class="">
							开始共享时间：
						</view>
						<view style="color: #0084ff;" @tap="toggleTab()">
							{{form.shareStartTime}}
						</view>
					</view>
					<view class="flexRow">
						<view class="">
							结束共享时间：
						</view>
						<view style="color: #0084ff;" @tap="toggleTabEnd()">
							{{form.shareEndTime}}
						</view>
					</view>
					<view class="flexRow">
						<view class="" style="padding-top: 5rpx;">
							价格（小时）：
						</view>
						<view class="">
							<u-input v-model="form.price" type="number" placeholder="请输入共享价格" />
						</view>
					</view>
			
				</view>
				<view class="">
					<u-button type="primary" @click="confirmSharing()">确认共享</u-button>
				</view>
			</view>
			<view class="shareFrom" v-if="placeStateInfo.state=='共享中'">
				<view class="">
			
					<view class="flexRow">
						<view class="">
							开始共享时间：
						</view>
						<view class="">
							{{placeStateInfo.shareStartTime}}
						</view>
					</view>
					<view class="flexRow">
						<view class="">
							结束共享时间：
						</view>
						<view class="">
							{{placeStateInfo.shareEndTime}}
						</view>
					</view>
					<view class="flexRow">
						<view class="">
							价格（小时）：
						</view>
						<view class="">
							{{placeStateInfo.price}}
						</view>
					</view>
				</view>
				<u-button type="primary" @click="cancelSharing">取消共享</u-button>
			</view>
			<view class="shareFrom" v-if="placeStateInfo.state=='禁用'">
				车位被禁用
			</view>
		</view>
		
		<view class="" v-if="!isPlacePage">
			<u-empty text="您暂无车位信息" mode="data" margin-top="30"></u-empty>
		</view>
		
		
		
	</view>
</template>

<script>
	import yuDatetimePicker from "@/components/yu-datetime-picker/yu-datetime-picker.vue"
	import reqPlace from '../../api/parkingSpace.js'
	export default {
		components: {
			yuDatetimePicker
		},
		filters: {
			// 是否占用，0：占用，1：未占用"
			formatStatus(value) {
				if (value == 1) {
					return "车位已占用";
				} else if (value == 0) {
					return "车位未占用";
				}
			},
		},
		data() {
			return {
				isPlacePage:false,//用户是否有车位
				form: {
					price: '',
					shareStartTime: '请选择开始时间',
					shareEndTime: '请选择结束时间'
				},
				shareState: true,
				noShareState: false,
				placeStateInfo: {

				},

			}
		},
		methods: {
			toggleTab(item, index) {
				this.$refs.dateTime.show();
			},
			toggleTabEnd(item, index) {
				this.$refs.dateTimeEnd.show();
			},
			onConfirm(val) {
				this.form.shareStartTime = val.selectRes;
			},
			onConfirmEnd(val) {
				this.form.shareEndTime = val.selectRes;
			},
			//确认共享
			confirmSharing() {
				let _this = this
				uni.showModal({
					content: '确认共享',
					success: function(res) {
						if (res.confirm) {
							
							if(_this.form.shareStartTime=="请选择开始时间"){
								_this.$tool.msg("请选择开始时间")
							}else if(_this.form.shareEndTime=="请选择结束时间"){
								_this.$tool.msg("请选择结束时间")
							}else{
								_this.$http.parkingSpace.turnSharing(_this.form)
								.then(res => {
									if (res.code == 200) {
										uni.showToast({
											title: "共享成功",
											duration: 2000
										});

										uni.navigateBack({
											success: () => {
												let page = getCurrentPages().pop(); //跳转页面成功之后
												if (!page) {
													return;
												} else {
													page.onLoad(page
														.options); // page自带options对象.
												}
											}
										})
									} else {
										uni.showToast({
											title: res.msg,
											icon: 'none',
											duration: 2000
										});
									}
								}).catch(err => {
									console.log(err);
								})
							}
							
							
						}
					}
				});


			},
			//取消共享
			cancelSharing() {
				let _this = this
				uni.showModal({
					content: '确认取消共享',
					success: function(res) {
						if (res.confirm) {
							_this.$http.parkingSpace.closeSharing()
								.then(res => {
									if (res.data) {
										uni.navigateBack({
											success: () => {
												let page = getCurrentPages().pop(); //跳转页面成功之后
													console.log(page,44444444411100)
												if (!page) {
													return;
												} else {
													page.onLoad(page
														.options); // page自带options对象.
												}
											}
										})

									} else {
										uni.showToast({
											title: '车位以在用，暂不能取消',
											icon: 'none',
											duration: 2000
										});
									}
								}).catch(err => {
									console.log(err);
								})
						} else if (res.cancel) {
							console.log('取消');
						}
					}
				});





			},
			//初始化车位信息
			initPlace() {
				// this.placeStateInfo = this.$store.state.placeStateInfo
				this.$http.parkingSpace.getMyParkingSpace()
					.then(res => {
						if(res.data!=null){
							this.isPlacePage=true
							this.placeStateInfo = res.data
						}
						else{
							this.isPlacePage=false
						}
						
						
					}).catch(err => {
						console.log(err);
					})
			}

		},
		onLoad() {
			this.initPlace()
		}
	}
</script>

<style lang="scss">
	.center {
		padding: 10rpx;
	}

	page {
		background-color: #FFFFFF;
	}

	.flexRow {
		display: flex;
		flex-direction: row;
		margin-bottom: 15rpx;
	}

	.shareFrom {
		padding-left: 30rpx;
		padding-right: 30rpx;
	}

	.title {
		margin: 0 auto;
		font-size: 18px;
		text-align: center;
	}

	.fontWei {
		font-weight: 800;
	}
</style>
