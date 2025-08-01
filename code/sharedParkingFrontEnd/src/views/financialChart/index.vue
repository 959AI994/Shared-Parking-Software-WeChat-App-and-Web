
<template>
  <div class="dashboard-editor-container">
    <!-- <github-corner class="github-corner" /> -->

    <!-- <panel-group @handleSetLineChartData="handleSetLineChartData" /> -->

    <el-row>
      <!-- <line-chart :chart-data="lineChartData" /> -->
      <div id="1">
        <histogram-item
          my-chart-id="name222"
          :titlename="titleName"
          :labelname="labelnames"
          :edit="isEditFalse"
          :x-data="xdataNames"
          :y-data="ydataValues"
          @slectParking="slectParking"
          @slectYear="slectYear"
        />
      </div>
    </el-row>

  </div>
</template>

<script>
import { incomeStatement } from '@/api/report'
import HistogramItem from '@/components/echarts/HistogramItem.vue'
export default {
  name: 'DashboardAdmin11',
  components: {
    HistogramItem
  },
  data() {
    return {
      isEditFalse: false,
      isEditTrue: true,
      titleName: '共享车收入',
      labelnames: '金额',
      ydataValues: [],
      xdataNames: []
    }
  },
  created() {
    this.init()
  },
  methods: {
    slectParking(e) {
      this.getIncomeStatement(e) // 调用getIncomeStatement方法来获取相应的收入报表数据。
    },
    // slectParking和slectYear用于响应选择停车场和选择年份的事件，
    slectYear(e) {
      this.getIncomeStatement(e)
    },
    getIncomeStatement(e) {
      incomeStatement(e).then((res) => { // getIncomeStatement方法使用了incomeStatement API来获取数据，
        console.log('resData', res)
        this.ydataValues = res.data.yData // 并将结果赋值给ydataValues和xdataNames。
        this.xdataNames = res.data.xData
      })
    },
    // 初始化图表数据
    init() {
      var parm = {
        year: new Date().getFullYear(),
        parkingId: ''
      }
      this.getIncomeStatement(parm)
    }
  }
}
</script>

      <style lang="scss" scoped>
      .dashboard-editor-container {
        padding: 32px;
        background-color: rgb(240, 242, 245);
        position: relative;

        .github-corner {
          position: absolute;
          top: 0px;
          border: 0;
          right: 0;
        }

        .chart-wrapper {
          background: #fff;
          padding: 16px 16px 0;
          margin-bottom: 32px;
        }
      }

      @media (max-width: 1024px) {
        .chart-wrapper {
          padding: 8px;
        }
      }
      </style>

