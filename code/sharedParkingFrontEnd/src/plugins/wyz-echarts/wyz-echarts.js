import Vue from 'vue' // 导入 Vue 和 echarts 模块：
import echarts from 'echarts'
import sweetLight from '../../plugins/wyz-echarts/theme/sweet-light.json' // 导入自定义的 echarts 主题配置文件 sweet-light.json 和 sweet-light-16.json：
import sweetLight16 from '../../plugins/wyz-echarts/theme/sweet-light-16.json'

echarts.registerTheme('sweet-light', sweetLight) // 注册主题配置到 echarts 中：
echarts.registerTheme('sweet-light-16', sweetLight16)
Vue.prototype.$echarts = echarts // 通过将 echarts 对象赋值给 Vue 实例的原型属性 $echarts，可以在 Vue 组件中通过 this.$echarts 来访问 echarts 对象，从而可以在组件中使用 echarts 创建和配置图表。
