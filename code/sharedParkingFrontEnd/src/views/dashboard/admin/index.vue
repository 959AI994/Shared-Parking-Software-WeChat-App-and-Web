<template>
  <div class="dashboard-editor-container">
    <!-- <github-corner class="github-corner" /> -->

    <panel-group @handleSetLineChartData="handleSetLineChartData" />

    <el-row>
      <!-- <line-chart :chart-data="lineChartData" /> -->

      <div id="1">
        <histogram-item
          my-chart-id="name1"
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

    <el-row :gutter="32">

      <!-- <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <raddar-chart />
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <pie-chart />
        </div>
      </el-col> -->
      <!-- <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <bar-chart />
        </div>
      </el-col> -->
    </el-row>

    <!-- <el-row :gutter="8">
      <el-col :xs="{span: 24}" :sm="{span: 24}" :md="{span: 24}" :lg="{span: 12}" :xl="{span: 12}" style="padding-right:8px;margin-bottom:30px;">
        <transaction-table />
      </el-col>
      <el-col :xs="{span: 24}" :sm="{span: 12}" :md="{span: 12}" :lg="{span: 6}" :xl="{span: 6}" style="margin-bottom:30px;">
        <todo-list />
      </el-col>
      <el-col :xs="{span: 24}" :sm="{span: 12}" :md="{span: 12}" :lg="{span: 6}" :xl="{span: 6}" style="margin-bottom:30px;">
        <box-card />
      </el-col>
    </el-row> -->
  </div>
</template>

<script>
import {
  orderReport
} from '@/api/report'
import GithubCorner from '@/components/GithubCorner'
import HistogramItem from '@/components/echarts/HistogramItem.vue'
import PanelGroup from './components/PanelGroup'
import LineChart from './components/LineChart'
import RaddarChart from './components/RaddarChart'
import PieChart from './components/PieChart'
import BarChart from './components/BarChart'
import TransactionTable from './components/TransactionTable'
import TodoList from './components/TodoList'
import BoxCard from './components/BoxCard'

const lineChartData = {
  newVisitis: {
    expectedData: [100, 120, 161, 134, 105, 160, 165],
    actualData: [120, 82, 91, 154, 162, 140, 145]
  },
  messages: {
    expectedData: [200, 192, 120, 144, 160, 130, 140],
    actualData: [180, 160, 151, 106, 145, 150, 130]
  },
  purchases: {
    expectedData: [80, 100, 121, 104, 105, 90, 100],
    actualData: [120, 90, 100, 138, 142, 130, 130]
  },
  shoppings: {
    expectedData: [130, 140, 141, 142, 145, 150, 160],
    actualData: [120, 82, 91, 154, 162, 140, 130]
  }
}

export default {
  name: 'DashboardAdmin',
  components: {
    GithubCorner,
    HistogramItem,
    PanelGroup,
    LineChart,
    RaddarChart,
    PieChart,
    BarChart,
    TransactionTable,
    TodoList,
    BoxCard
  },
  data() {
    return { isEditFalse: false,
      isEditTrue: true,
      titleName: '共享停车订单数量图表统计',
      labelnames: '订单数',
      ydataValues: [],
      xdataNames: [],
      lineChartData: lineChartData.newVisitis
    }
  },
  created() {
    this.init()
  },
  methods: {
    slectParking(e) {
      this.getIncomeStatement(e)
    },
    slectYear(e) {
      this.getIncomeStatement(e)
    },

    handleSetLineChartData(type) {
      // console.log(type)
      // this.lineChartData = lineChartData[type]
      if (type == 'feed') {
        this.$router.push('/feedback')
      }
      if (type == 'order') {
        this.$router.push('/order/orderList')
      }
      if (type == 'SpaceInfo') {
        this.$router.push('/parkingSpaceList')
      }
    },

    // 初始化图表数据
    init() {
      var ye = new Date().getFullYear

      var parm = {
        year: new Date().getFullYear(),
        parkingId: ''
      }
      this.getIncomeStatement(parm)
    },

    getIncomeStatement(e) {
      orderReport(e).then((res) => {
        console.log('resData', res)
        this.ydataValues = res.data.yData
        this.xdataNames = res.data.xData
      })
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

@media (max-width:1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
