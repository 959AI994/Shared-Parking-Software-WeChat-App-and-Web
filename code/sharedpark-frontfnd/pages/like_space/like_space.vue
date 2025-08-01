<template>
	<view>
		<view class="" v-if="isOwnerState">
			<uni-list>
				<view class="uni-list-cell" hover-class="uni-list-cell-hover" v-for="(item, key) in likeListData"
					:key="key">
					<view class="uni-media-list">
						<u-row gutter="16">
							<u-col span="9">
								<view class="">
									停车场：{{item.parkingSpaceId}}
								</view>
							</u-col>
							<u-col span="3">
								<view class="" style="color: #55aaff;">
									{{item.parkingSpaceId}}
								</view>
							</u-col>
						</u-row>
						<u-row gutter="16">
							<u-col span="10">
								<view class="">
									车位号：{{item.parkingSpaceId}}
								</view>
							</u-col>
						</u-row>
						<u-row gutter="16" v-if="item.parkingSpaceId!=null">
							<u-col span="10">
								<view class="">
									地址：{{item.openId}}
								</view>
							</u-col>
						</u-row>
					</view>
				</view>
			</uni-list>
		</view>
		<view class="" v-if="!isOwnerState">
			<u-empty text="暂收藏记录" mode="data" margin-top="30"></u-empty>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				isOwnerState: false,
				likeListData: [],
			}
		},
		methods: {
			queryShareList() {
				this.$http.parkingSpace.getLikeSpaceList().then(res => {
					
					
					if (res.data.length > 0) {
						this.isOwnerState = true
						this.likeListData = res.data
					}

				})
			}
		},
		onLoad() {
			this.queryShareList()

		}
	}
</script>
<style lang="scss">
	page {
		background-color: #FFFFFF;
	}

	.uni-media-list {
		display: flex;
		flex-direction: column;
		margin-left: 32rpx;
		margin-right: 32rpx;
		margin-top: 15rpx;

		.uni-media-list-logo {
			width: 180rpx;
			height: 140rpx;
		}


		.uni-media-list-body {
			flex-direction: row;
			height: auto;
			margin-left: 20rpx;
			margin-right: 20rpx;
			justify-content: space-around;
		}

		.uni-media-list-text-top {
			height: 74rpx;
			font-size: 28rpx;
			overflow: hidden;
		}

		.uni-media-list-text-bottom {
			display: flex;
			flex-direction: row;
			margin-top: 20rpx;
			margin-right: 20rpx;
			font-size: 27rpx;
			color: #999999;
		}
	}
</style>
