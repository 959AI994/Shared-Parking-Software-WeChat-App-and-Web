
module.exports = {
	devServer:{
		port:'8081',
		disableHostCheck:true,
		proxy:{
			'/dpc':{
				target:'http://localhost:8888',
				changeOrigin:true,
				pathRewrite:{
					'^/dpc': ''
				}
			}
		}
	}
}