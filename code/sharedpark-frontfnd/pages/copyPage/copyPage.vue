<template>
	<view>
		<view class="content" >
			<view class="name">名字</view>
			<view class="inputinfo"><input type="text" v-model="content" value="" placeholder="完善用户信息"/></view>
			<view class="hr"></view>
		</view>
	</view>
</template>

<script>
	export default {
		name:"UserInformation",
		data() {
			return {
				content:'',
			};
		}
	}
</script>

<style lang="scss"  scoped>
   .content{
	    padding: 10px 0;
	   padding-left: 5%;
	   
	   .name{
		   font-size: 14px;
		   color: #333;
		   font-weight: bold;
		   height: 40px;
		   line-height: 40px;
		   
	   }
	   .inputinfo{
		   height: 25px;
		   line-height: 25px;
		    input{
				font-size: 12px;
				
			}
	   }
	   .hr{
		   width: 95%;
		   height: 1px;
		   background-color: #919191;
		   
	   }
   }
</style>
