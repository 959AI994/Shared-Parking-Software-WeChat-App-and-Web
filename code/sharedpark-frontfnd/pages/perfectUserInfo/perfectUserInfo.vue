<template>
	<view>
		<view class="content">
			<view class="">
				<u-form :model="form">
					<u-form-item label="真实姓名:" label-width="140">
						<u-input v-model="form.realName" />
					</u-form-item>
					<u-form-item label="身份证号:" label-width="140">
						<u-input v-model="form.idNumber" />
					</u-form-item>
					<u-form-item label="联系电话:" label-width="140">
						<u-input v-model="form.phone" />
					</u-form-item>
					<u-form-item label="车牌号:" label-width="100" style="height: 20rpx;">
						<!-- <u-input v-model="form.carNumber" /> -->
						<view class="carinput" >
							<view class="carinput-input">
								<view class="carinput-input-i" :class="{'input-active':carIndex == i}" @tap="inputKey"
									:data-index="i" v-for="(v,i) in carInput" :key="i" v-show="i < 7">
									{{carInput[i].val}}
								</view>
								<view class="carinput-input-i" :class="{'input-active':carIndex == 7}" @tap="inputKey"
									data-index="7">
									{{carInput[7].val?carInput[7].val:'+'}}
								</view>
							</view>
							<view class="carinput-power">
								<checkbox-group class="checkbox-g" @change="powerChange">
									<label class="checkbox">
										<checkbox value="true" :checked="isPower" />
										新能源
									</label>
								</checkbox-group>
							</view>
						</view>
					</u-form-item>
					
					<!-- <view class="card-btn">
						<button class="btn" hover-class="hover-c" type="default" @tap="toBind">
							确定
						</button>
					</view> -->
					
					<u-button type="primary" @click="toBind">提交</u-button>
					<!-- <view class="card-btn">
						<button class="btn" hover-class="hover-c" type="default" @tap="toBind">
							确定
						</button>
					</view> -->
				</u-form>
			</view>


		</view>

		<tki-float-keyboard ref="keybd" :mode="'car'" :type="keyType" :title="'车牌键盘'" @del="keyCbDel" @val="keyCbVal"
			@hide="keyCbHide"></tki-float-keyboard>
	</view>
</template>

<script>
	import tkiFloatKeyboard from "@/components/tki-float-keyboard/tki-float-keyboard.vue";
	export default {
		name: "UserInformation",
		components: {
			tkiFloatKeyboard
		},
		data() {
			return {
				isDopt: false,
				content: '',
				form: {
					realName: '',
					gender: '男',
					idNumber: '',
					phone: '',
					carNumber: ''
				},
				carIndex: -1,
					carInput: [{
							type: 2,
							val: ""
						},
						{
							type: 4,
							val: ""
						},
						{
							type: 1,
							val: ""
						},
						{
							type: 1,
							val: ""
						},
						{
							type: 1,
							val: ""
						},
						{
							type: 1,
							val: ""
						},
						{
							type: 3,
							val: ""
						},
						{
							type: 1,
							val: ""
						}
					],
					keyType: 0,
					isPower: false, // 新能源
			};
		},
		methods: {
			//提交表单信息
			submit() {
				let _this = this
				this.$http.user.userAuth(this.form).then(res => {
					if (res.code == 200) {
						this.isDopt = true
						_this.$store.state.hasLogin = true;
						_this.$store.state.Token = res.data;

						uni.showToast({
							title: '完善成功',
							duration: 2000
						});
						//查询用户信息
						_this.getUserInfo()

						//关闭页面回到主页
						uni.reLaunch({
							url: '/pages/my/index'
						});
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
			},
			//获取用户信息
			getUserInfo() {
				this.$http.user.getUserInfo()
					.then(res => {
						// console.log("resss",res.data)
						if (res.code == 200) {
							this.$store.state.userData = res.data;
							uni.setStorageSync('nickName', res.data.nickName)
							uni.setStorageSync('avatarUrl', res.data.avatarUrl)
						} else {
							console.log("获取用户信息失败")
						}
					})
			},
			// 判定是否为空
			empty(v) {
				let tp = typeof v,
					rt = false;
				if (tp == "number" && String(v) == "") {
					rt = true
				} else if (tp == "undefined") {
					rt = true
				} else if (tp == "object") {
					if (JSON.stringify(v) == "{}" || JSON.stringify(v) == "[]" || v == null) rt = true
				} else if (tp == "string") {
					if (v == "" || v == "undefined" || v == "null" || v == "{}" || v == "[]") rt = true
				} else if (tp == "function") {
					rt = false
				}
				return rt
			},
			inputKey(e) {
				let that = this;
				let data = e.currentTarget.dataset;
				if (data.index >= 7) {
					if (!that.isPower) {
						return false;
					} else {
						that.carIndex = 7;
					}
				} else {
					that.carIndex = data.index;
				}
				that.upKeyType();
				that.keyShow();
			},
			keyCbVal(e) {
				let that = this;
				let index = Number(that.carIndex);
				if (index >= 0 && index < 6) {
					that.carInput[index].val = e;
					that.carIndex = index + 1;
				} else if (index == 6) {
					that.carInput[index].val = e;
					if (that.isPower) {
						// 新能源
						that.carIndex = 7;
					} else {
						// 不是新能源出输入结束
						that.keyHide();
						that.carIndex = -2;
						console.log('非新能源车输入完毕', index)
					}
				} else if (index == 7) {
					// 新能源车输入完毕
					that.carInput[index].val = e;
					that.keyHide();
					that.carIndex = -3;
					console.log('新能源车输入完毕', index)
				}
				that.upKeyType();
			},
			keyCbDel(e) {
				let index = this.carIndex;
				if (index > 0) {
					if (!this.empty(this.carInput[index].val)) {
						this.carInput[index].val = "";
						this.carIndex = index;
					} else {
						this.carInput[index - 1].val = "";
						this.carIndex = index - 1;
					}
				}
				this.upKeyType();
			},
			upKeyType() {
				if (!this.empty(this.carInput[this.carIndex])) {
					this.keyType = this.carInput[this.carIndex].type;
				}
			},
			keyShow() {
				this.$refs.keybd._keyShow();
			},
			keyHide() {
				this.$refs.keybd._keyHide();
			},
			keyCbHide() {
				if (this.carIndex != -3 || this.carIndex != -2) {
					this.carIndex = -1;
				}
			},
			powerChange(e) {
				let that = this
				let i = that.checkCar().i
				if (e.detail.value.length > 0) {
					that.isPower = true;
					if (i == -1) {
						that.carIndex = 7;
					} else {
						that.carIndex = i;
					}
					that.keyShow();
				} else {
					that.isPower = false;
					that.carInput[7].val = "";
					if (that.carIndex == 7) {
						that.keyHide();
						that.carIndex = -2;
					}
				}
				that.upKeyType();
			},
			checkCar() {
				// 检查车牌是否输入完成
				let that = this;
				let i = 7;
				let rt = {
					i: -1,
					isempty: false,
					val: ""
				};
				if (that.isPower) {
					i = 8;
				}
				for (let index = 0; index < i; index++) {
					const obj = that.carInput[index];
					if (this.empty(String(obj.val))) {
						rt.i = index;
						rt.isempty = true;
						rt.val = "";
						break;
					}
					rt.val += that.carInput[index].val;
				}
				return rt;
			},
			toBind() {
				let that = this
				let ck = that.checkCar();
				if (ck.i == -1 && !ck.isempty) {
					console.log('可以绑定车牌了', ck.val)
					this.form.carNumber=ck.val
					this.submit()
					
				} else {
					// 显示键盘输入
					uni.showToast({
						icon:"error",
						title: "车牌输入错误"
					})
					that.keyShow();
					that.carIndex = ck.i;
					that.keyType = that.carInput[ck.i].type
				}
			}
		}
	}
</script>

<style lang="scss">
	@import "./style.css";

	.content {
		padding: 10px 0;
		padding-left: 5%;

		.hr {
			width: 95%;
			height: 1px;
			background-color: #919191;

		}
	}
</style>
