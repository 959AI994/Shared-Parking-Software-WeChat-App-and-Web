<template>
	<view>
		<view class="content">
			<view class="name">请输入反馈的内容</view>

			<view class="" style="background-color: #faf8f8;">
				<textarea v-model="from.content" placeholder="请输入内容"></textarea>
			</view>
			<view class="">
				<u-button type="primary" @click="SubmitOnclick()">提交</u-button>
			</view>

			<!-- <view class="hr"></view> -->
		</view>
	</view>
</template>

<script>
	export default {
		name: "FeedBack",
		data() {
			return {
				from: {
					content: '',
				}

			};
		},
		methods: {
			SubmitOnclick() {
				var thar = this.from.content
				if (thar == null || thar == '') {
					uni.showToast({
						title: '内容不能为空 ',
						duration: 2000,
						icon:'none'
					});
				} else {
					this.$http.feedBack.feedBackAdd(this.from).then(res => {
						if (res.code == 200) {
							uni.showToast({
								title: '反馈成功',
								duration: 2000
							});
							this.from.content = ''
						}
					}).catch(err => {
						console.log(err);
					})
				}
			}
		}
	}
</script>

<style lang="scss">
	.textarea {
		border: solid 1px red;
	}

	.content {
		padding: 10px 0;
		padding-left: 5%;
		padding-right: 5%;

		.name {
			font-size: 14px;
			color: #333;
			font-weight: bold;
			height: 40px;
			line-height: 40px;

		}

		.inputinfo {
			height: 25px;
			line-height: 25px;

			input {
				font-size: 12px;

			}
		}

		.hr {
			width: 95%;
			height: 1px;
			background-color: #919191;

		}
	}
</style>
