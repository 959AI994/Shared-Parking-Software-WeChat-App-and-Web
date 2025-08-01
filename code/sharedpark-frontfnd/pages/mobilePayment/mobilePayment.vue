<template>
	<view>
		<page-head title="view"></page-head>
		<view class="uni-padding-wrap">
			<view class="uni-flex uni-column" style="padding-top: 10px;">
				<view class="flex-item-V uni-bg-wait iconPad" style="padding-left: 15px;">
					<view class="parkname">
						<u-icon name="coupon" color="#2979ff" size="28"></u-icon><text>幸会园停车场</text>
					</view>
					<u-line color="#b5b5b5"></u-line>
					<view>
						<u-icon name="bookmark" color="#ff5c26" size="28"></u-icon><text>27</text>
					</view>
					<view>
						<u-icon name="coupon" color="#5500ff" size="28"></u-icon><text>桂K 5527G</text>
					</view>
				</view>
				<view class="uni-bg-wait timecenter flex-item-V">
					<view class="uni-flex uni-row floatRight">
						<view class="text">创建时间</view>
						<view class="text">2023-09-10 14:25:56</view>
					</view>
					<view class="uni-flex uni-row floatRight">
						<view class="text">进场时间</view>
						<view class="text">2023-09-10 14:25:56</view>
					</view>
					<view class="uni-flex uni-row floatRight">
						<view class="text">出场时间</view>
						<view class="text">2023-09-10 14:25:56</view>
					</view>
				</view>
				<view class="uni-bg-wait flex-item-V ">
					<view class="uni-flex uni-row floatRight">
						<view class="text">停车费用</view>
						<view class="text">￥15</view>
					</view>
				</view>
				<view class="flex-item-V ">
					<u-button type="primary" @click="">立即结算</u-button>
				</view>
			</view>


		</view>
	</view>
</template>
<script>
	export default {
		data() {
			return {}
		}
	}
</script>

<style>
	.flex-item {
		width: 33.3%;
		/* height: 200rpx; */
		text-align: center;
		line-height: 200rpx;
	}

	.flex-item-V {
		width: 100%;
		margin-bottom: 20px;
		/* height: 350rpx; */
		/* text-align: center; */
		/* line-height: 150rpx; */
	}

	.timecenter {
		width: 100%;
		height: 200rpx;
		text-align: center;
		line-height: 200rpx;
	}

	.iconPad text {
		margin-left: 10rpx;
	}

	.parkname text {
		font-size: 30rpx;
	}

	.floatRight {
		-webkit-justify-content: space-between;
		justify-content: space-between;
	}

	.text {
		margin: 15rpx 10rpx;
		padding: 0 20rpx;
		/* background-color: #ebebeb; */
		height: 40rpx;
		line-height: 40rpx;
		text-align: center;
		color: #777;
		font-size: 26rpx;
	}

	.flex-pc {
		display: flex;
		justify-content: center;
	}
</style>
