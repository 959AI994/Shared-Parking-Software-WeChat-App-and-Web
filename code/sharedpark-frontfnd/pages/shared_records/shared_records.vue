<template>
	<view>
		<view class="" v-if="isOwnerState">
			<uni-list>

				<view class="uni-list-cell" hover-class="uni-list-cell-hover" v-for="(item, key) in sharedData"
					:key="key">
					<view class="uni-media-list">
						<!-- <view>
							<image class="uni-media-list-logo" :src="value.cover"></image>
						</view>
						<view class="uni-media-list-body">
							<view class="uni-media-list-text-top">{{ value.title }}</view>
							<view class="uni-media-list-text-bottom">
								<text>{{ value.author_name }}</text>
								<text style="margin-left: 30rpx;margin-top: 6rpx;">{{ value.published_at }}</text>
							</view>
						</view> -->
						<u-row gutter="16">
							<u-col span="9">
								<view class="">
									订单号：{{item.orderId}}
								</view>
							</u-col>
							<u-col span="3">
								<view class="" style="color: #55aaff;">
									{{item.state}}
								</view>
							</u-col>

						</u-row>
						<u-row gutter="16">
							<u-col span="10">
								<view class="">
									创建时间：{{item.createTime}}
								</view>
							</u-col>
						</u-row>
						<u-row gutter="16" v-if="item.approachTime!=null">
							<u-col span="10">
								<view class="">
									进场时间：{{item.approachTime}}
								</view>
							</u-col>
						</u-row>
						<u-row gutter="16" v-if="item.actualPlayingTime!=null">
							<u-col span="10">
								<view class="">
									出场时间：{{item.actualPlayingTime}}
								</view>
							</u-col>
						</u-row>
						<u-row gutter="16" v-if="item.paymentAmount!=null">

							<u-col span="5">
								<view class="">
									收益金额：{{item.paymentAmount}}元
								</view>
							</u-col>
						</u-row>
					</view>
				</view>
			</uni-list>
		</view>
		<view class="" v-if="!isOwnerState">
			<u-empty text="暂无共享记录" mode="data" margin-top="30"></u-empty>
		</view>


	</view>
</template>

<script>
	export default {
		data() {
			return {
				isOwnerState: false,
				sharedData: [],
			}
		},
		methods: {
			queryShareList() {
				this.$http.parkingOrder.iSharedRecords().then(res => {
					if (res.data.length > 0) {
						this.isOwnerState = true
						this.sharedData = res.data
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
