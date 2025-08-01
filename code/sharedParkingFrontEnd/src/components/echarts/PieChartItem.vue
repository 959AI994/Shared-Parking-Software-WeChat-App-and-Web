<template>
  <div class="pie-chart-container">
    <div class="chart-title">{{ titleName }}</div>
    <div class="chart-wrapper">
      <div class="pie-chart" ref="pieChart"></div>
    </div>
  </div>
</template>

<script>
import echarts from 'echarts'

export default {
  name: 'PieChartItem',
  props: {
    titleName: {
      type: String,
      required: true
    },
    labelName: {
      type: String,
      required: true
    },
    data: {
      type: Array,
      required: true
    }
  },
  mounted() {
    this.renderChart()
  },
  methods: {
    renderChart() {
      const chartDom = this.$refs.pieChart
      const chart = echarts.init(chartDom)

      const option = {
        title: {
          text: this.titleName
        },
        tooltip: {
          trigger: 'item',
          formatter: '{b} : {c} ({d}%)'
        },
        legend: {
          orient: 'vertical',
          left: 'right',
          data: this.data.map(item => item.name)
        },
        series: [
          {
            name: this.labelName,
            type: 'pie',
            radius: '55%',
            center: ['50%', '50%'],
            data: this.data
          }
        ]
      }

      chart.setOption(option)
    }
  }
}
</script>

<style scoped>
.pie-chart-container {
  width: 100%;
  height: 100%;
}

.chart-title {
  text-align: center;
  font-weight: bold;
  margin-bottom: 10px;
}

.chart-wrapper {
  width: 100%;
  height: 300px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.pie-chart {
  width: 80%;
  height: 80%;
}
</style>
