<template>
	<view class="bigDiv">
		<!-- <text class="textAlign">业主认证</text> -->
		<!--这个表单form可以直接用uView官网的表单，然后就可以自动排版了-->
		<view class="">
			<view class="">
				<u-form :model="form">
					<u-form-item label="业主姓名:" label-width="125">
						<u-input  v-model="form.ownerName" />
					</u-form-item>
					<u-form-item label="性 别:" label-width="125">
						<u-radio-group v-model="form.gender">
							<u-radio name="0">男</u-radio>
							<u-radio name="1">女</u-radio>
						</u-radio-group>
					</u-form-item>
					<u-form-item label="停车场名称:" label-width="165">
						<u-input v-model="villageName" placeholder="请选择停车场" @focus="illageNameSlectClick()" />
						<u-select v-model="VillageNameSlect" mode="single-column" :list="PickerList"
							@confirm="confirmVillageName"></u-select>
					</u-form-item>
					<u-form-item label="车位号:" label-width="125">
						<u-input v-model="form.parkingSpaceNumber" />
					</u-form-item>
					<u-form-item label="产权证编号:" label-width="160">
						<u-input v-model="form.parkingTileNumber" />
					</u-form-item>
					<u-form-item label="身份证号:" label-width="125">
						<u-input disabled="true" v-model="form.idNumber" />
					</u-form-item>
					<u-form-item label="联系电话:" label-width="125">
						<u-input disabled="true" v-model="form.phone" />
					</u-form-item>
					<u-form-item label="证件上传:" label-width="125">
						<u-upload ref="uUpload" @on-remove="onRemove" max-count="1" :action="action"
							@on-change="onChangeImg"></u-upload>
						<!-- <u-button type="default" size="mini" @click="onChoosImg">上传 </u-button> -->
					</u-form-item>
				</u-form>
				<view class="">
					<u-button type="primary" @click="submit">确定</u-button>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				VillageNameSlect: false,
				isDopt: true,
				villageName: '',
				parkingInfoId: '',
				form: {
					ownerAuthId:null,
					ownerName: '',
					intro: '',
					parkingInfoId: '',
					parkingTileNumber: '',
					villageName: '',
					imgUrl: '',
					phone: '',
					idNumber: '',
					parkingSpaceNumber: '',
					gender: '0'
				},
				// action: 'http://192.168.31.248:8888/ownerAuth/upload', 
				action: '',//上传地址
				fileList: [{

				}],
				resFileName: '',
				fileName: '', //删除的文件ming
				resOwnerData: {},
				PickerList: [], //选择车场
				SUBstate:false,

			}
		},
		methods: {
			//重新认证 
			chognxinAuto(){
					this.$http.ownerAuth.getOwnerInfo().then(res => {
						console.log("重新认证",res)
						if (res.code == 200) {
							this.form = res.data
							this.isDopt=false
						} 
					})
			},
			
			//停车场选择回调
			confirmVillageName(e) {
				console.log(e)
				this.villageName = e[0].label
				this.parkingInfoId = e[0].value
			},
			//选择列表是否显示
			illageNameSlectClick() {
				this.VillageNameSlect = true
			},
			//上传图片成功的回调
			onChangeImg(res, index, lists, name) {
				var resObj = JSON.parse(res.data)
				if (resObj.code == 200) {
					this.resFileName = resObj.data.filePath
					this.fileName = resObj.data.fileName
					uni.showToast({
						title: "上传成功",
						icon: 'none',
						duration: 2000
					});
				} else {
					uni.showToast({
						title: "上传失败",
						icon: 'none',
						duration: 2000
					});
				}
				console.log(this.resFileName)

			},
			//移除图片
			onRemove() {
				this.resFileName = ''
				this.$http.deleteImg(this.fileName).then(res => {
					if (res.code = 200) {
						console.log('图片已被移除')
					}
				})
			},
			//提交材料
			submit() {
				this.form.imgUrl = this.resFileName
				this.form.villageName = this.villageName
				//提交成功后调用接口，和后端进行交互
				this.$http.ownerAuth.addOwnerAuth(this.form).then(res => {
					if (res.code == 200) {
						uni.showToast({
							title: '提交材料成功',
							duration: 2000
						});
						uni.redirectTo({
							url: "/pages/myParkingSpace/my-space-list"
						})
					}

				}).catch(err => {
					console.log(err);
				})
			},
			
			
			//是否是业主
			// queryOwnerAuth() {
			// 	this.$http.ownerAuth.getOwnerInfo().then(res => {
			// 		if (res.data != null) {
			// 			this.isDopt = true
			// 			this.resOwnerData = res.data
			// 		} else {
			// 			this.isDopt = false
			// 			this.resOwnerData = ''
			// 		}
			// 	})
			// },
			
			
			//获取用户信息
			getUserInfo() {
				this.form = this.$store.state.userData
				this.form.gender = this.$store.state.userData.gender
				this.form.ownerName = this.$store.state.userData.realName
			},
			//选择停车场
			queryPickerInfo() {
				this.$http.parkinginfo.pickerInfo().then(res => {
					// console.log("sss",res)
					if (res.code == 200) {
						this.PickerList = res.data
					}
				})
			},
			onChoosImg() {
				uni.chooseImage({
					success: (res) => {
						let tempFilePaths = res.tempFilePaths;
						tempFilePaths.forEach((item) => {
							uniCloud.uploadFile({
								filePath: item,
								cloudPath: 'item.jpg',
								success(res) {
									console.log("suees", res)
								},
								fail(res) {
									console.log("fail", res)
								},
								complete() {

								}
							});
						})
					}
				});
			}

		},
		created() {
			this.getUserInfo()
			this.queryPickerInfo()
			this.action=this.BASEURL+"/ownerAuth/upload" //获取上传地址
		}
	}
</script>

<style lang="scss">
	page {
		background-color: #ffffff;
	}

	.bigDiv {
		width: 100%;
		padding: 25rpx;
	}

	.textAlign {
		text-align: center;
	}
</style>
