<template xlang="wxml">
	<view class="container">
		<view class="qrimg">
			<view class="qrimg-i">
				<tki-qrcode cid="qrcode2" ref="qrcode2" :val="val" :size="size" :onval="onval"
				 :loadMake="loadMake" :usingComponents="true" @result="qrR" />
			</view>
		</view>
		<view class="">
			<u-row>
				<u-col span="12" text-alin="center" >
					<u-button type="info"  >可通过扫码查看订单</u-button>
				</u-col>
			</u-row>
		</view>
		
	</view>
</template>
<script>
import tkiQrcode from '@/components/tki-qrcode/tki-qrcode.vue'
export default {
	data() {
		return {
			val: '', // 要生成的二维码值
			size: 400, // 二维码大小
			unit: 'upx', // 单位
			background: '#b4e9e2', // 背景色
			foreground: '#309286', // 前景色
			pdground: '#32dbc6', // 角标色
			icon: '', // 二维码图标
			iconsize: 40, // 二维码图标大小
			lv: 3, // 二维码容错级别 ， 一般不用设置，默认就行
			onval: false, // val值变化时自动重新生成二维码
			loadMake: true, // 组件加载完成后自动生成二维码
			src: '' // 二维码生成后的图片地址或base64
		}
	},
	methods: {
		qrR(res) {
			this.src = res
		},
		//结算订单
		toShared_records(){
			uni.navigateTo({
				url: "/pages/orderSettlement/orderSettlement?orderId=" + this.val+"&state=count",
			});
		}
	},
	components: {
		tkiQrcode
	},
	onLoad(option) { 
		// this.pinjieUrl(option.orderId)
		this.val=option.orderId
	},
}
</script>

<style>
/* @import "../../../common/icon.css"; */
.container {
	display: flex;
	flex-direction: column;
	width: 100%;
}

.qrimg {
	display: flex;
	justify-content: center;
}
.qrimg-i{
	margin-right: 10px;
}

slider {
	width: 100%;
}

input {
	width: 100%;
	margin-bottom: 20upx;
}

.btns {
	display: flex;
	flex-direction: column;
	width: 100%;
}

button {
	width: 100%;
	margin-top: 10upx;
}
</style>
