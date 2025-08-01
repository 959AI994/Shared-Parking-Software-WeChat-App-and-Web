const msg = (title, duration = 2000, mask = false, icon = 'none') => {
	//统一提示方便全局修改
	if (Boolean(title) === false) {
		return;
	}
	uni.showToast({
		title,
		duration,
		mask,
		icon
	});
};
const toast = (title, duration = 2000) => {
	if (Boolean(title) === false) {
		return;
	}
	uni.showToast({
	    title: title,
	    duration: duration
	});
}



const toassst = (title, duration = 1500) => {
	uni.showToast({
		title: '提交成功',
		duration: 2000
	});
}

export default{
	msg,
	toast
}