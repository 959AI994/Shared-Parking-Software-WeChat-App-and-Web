// #ifndef VUE3
import Vue from 'vue'
import App from './App'
import uView from "uview-ui"; // 导入uView UI库，用于界面的样式和组件。
import store from './store' // 导入Vuex的store实例，用于状态管理。
import http from './api/common_min.js'//添加全部路由
import tool from './util/tool.js' // 导入工具函数或方法
import BASEURLApp from "@/util/baseUrl.js" //  导入后台服务器地址的配置。

Vue.use(uView); // 在Vue实例中注册uView插件。
import { VueJsonp } from 'vue-jsonp/dist'
Vue.use(VueJsonp) // 在Vue实例中注册VueJsonp插件，用于进行JSONP请求。



Vue.config.productionTip = false

App.mpType = 'app' // 设置App组件的类型为'app'。

// import request from '@/api/api.js';
// Vue.prototype.req = request;
Vue.prototype.serviceUrl = "http://localhost:8888";
// 在Vue原型上添加名为BASEURL的属性，用于定义后端服务器的基础URL。

//后台服务器地址
Vue.prototype.BASEURL = BASEURLApp.BAST_URL;


Vue.prototype.$http = http
Vue.prototype.$tool =tool


// 挂载到Vue实例
Vue.prototype.$store = store;

const app = new Vue({
    ...App,
	store
})
app.$mount()
// #endif

// #ifdef VUE3
import { createSSRApp } from 'vue'
import App from './App.vue'
export function createApp() {
  const app = createSSRApp(App)
  return {
    app  // 创建Vue实例，将App组件作为根组件，并传入store。
  }
}
// #endif

