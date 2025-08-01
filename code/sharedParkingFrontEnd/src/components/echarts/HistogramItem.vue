<template>
  <div class="MyAchievement">
    <div class="MyAchievement-echart">
      <!-- {{ titlenamedata }} -->
      <div class="echart-title">
        <el-row :gutter="20">
          <el-col :span="4">
            <el-select
              v-model="reportFrom.year"
              size="mini"
              placeholder="年份"
              @change="yearChange"
            >
              <el-option label="2021" value="2021" />
              <el-option label="2022" value="2022" />
              <el-option label="2023" value="2023" />
            </el-select>
          </el-col>
          <el-col :span="5">
            <el-select
              v-model="reportFrom.parkingId"
              size="mini"
              placeholder="选择小区停车场"
              clearable
              @change="repParkingChange"
            >
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </el-col>
        </el-row>
      </div>

      <div class="echart-content">
        <div :id="myChartId" :style="{ width: '100%', height: '460px' }" />
      </div>

      <div style="margin-top: 20px">
        <el-form
          ref="ruleForm"
          :inline="true"
          :rules="rules"
          :model="formInline"
          class="demo-form-inline demo-ruleForm"
        >
          <el-form-item label="" style="margin-right: 10px">
            <!-- <el-link
              style="margin-right: 10px"
              type="primary"
              @click="EchartInfo = true"
              >修改直方图属性信息
            </el-link> -->
            <el-link
              v-if="edit"
              type="primary"
              @click="dialogEchartInfoVisible = true"
            >
              修改直方图数据信息</el-link>
          </el-form-item>

          <el-form-item v-if="edit" label="x轴值" prop="xAxis">
            <el-input
              v-model="formInline.xAxis"
              placeholder="请输入名称"
            />
          </el-form-item>
          <el-form-item v-if="edit" label="y轴值" prop="yAxis">
            <el-input
              v-model="formInline.yAxis"
              type="number"
              placeholder="请输入数量"
            />
          </el-form-item>
          <el-form-item v-if="edit">
            <el-button type="primary" @click="submitForm()">添加</el-button>
          </el-form-item>
        </el-form>
      </div>

      <div>
        <el-drawer
          ref="drawer"
          title="直方图数据信息"
          :visible.sync="dialogEchartInfoVisible"
          direction="ltr"
          custom-class="demo-drawer"
        >
          <div class="demo-drawer__content">
            <el-row style="margin-bottom: 10px">
              <el-col :span="12">
                <div>
                  <ul>
                    <li v-for="(item, index) in xAxisData" :key="index">
                      <DataListItem
                        :item="item"
                        :index="index"
                        @deleteItem="xydeleteItem"
                        @modifyItem="xmodifyItem"
                      />
                    </li>
                  </ul>
                </div>
              </el-col>
              <el-col :span="12">
                <div>
                  <ul>
                    <li v-for="(item, index) in yAxisData" :key="index">
                      <DataListItem
                        :item="item"
                        :index="index"
                        @deleteItem="xydeleteItem"
                        @modifyItem="ymodifyItem"
                      />
                    </li>
                  </ul>
                </div>
              </el-col>
            </el-row>
          </div>
        </el-drawer>
      </div>

      <div>
        <el-dialog
          style="height: 1200px"
          title="直方图属性信息"
          :visible.sync="EchartInfo"
          class="abow_dialog"
        >
          <el-form :model="echasSxInfo">
            <el-form-item v-if="edit" label="标题名称">
              <el-row :gutter="40">
                <el-col
                  :span="10"
                ><div class="grid-content">
                  <el-input
                    v-model="echasSxInfo.titleName"
                    placeholder="自定义标题名称"
                  /></div></el-col>
                <el-col
                  :span="10"
                ><div class="grid-content">
                  <el-input
                    v-model="echasSxInfo.labelname"
                    placeholder="自定义标签名称"
                  /></div></el-col>
              </el-row>
            </el-form-item>

            <el-form-item label="直方图属性">
              <el-checkbox-group v-model="echasSxInfo.type">
                <el-checkbox
                  label="柱子上显示值"
                  checked="checked"
                  name="type"
                />
                <el-checkbox
                  label="显示线"
                  name="type"
                  checked="checked"
                />
                <el-checkbox
                  label="刻度"
                  checked="checked"
                  name="type"
                />
                <el-checkbox label="分割线" name="type" />
              </el-checkbox-group>
            </el-form-item>
            <el-form-item label="直方图颜色属性">
              <lable style="padding-left: 20px">分割线的颜色</lable>
              <vcolorpicker
                v-model="echasSxInfo.fengeXianColor"
                default-color="#e7e7e7"
              />

              <lable style="padding-left: 20px">柱子颜色</lable>
              <colorPicker
                v-model="echasSxInfo.zhuziColor"
                default-color="#00abf7"
              />

              <lable style="padding-left: 20px">值颜色</lable>
              <colorPicker
                v-model="echasSxInfo.dataCsolor"
                default-color="#00abf7"
              />

              <lable style="padding-left: 20px">坐标值颜色</lable>
              <colorPicker
                v-model="echasSxInfo.xDataColo"
                default-color="#3a6186"
              />
            </el-form-item>

            <div class="dialog-footer">
              <el-button @click="EchartInfo = false">取 消</el-button>
              <el-button
                type="primary"
                @click="dialogEchartInfoVsc()"
              >确 定</el-button>
            </div>
          </el-form>
        </el-dialog>
      </div>
    </div>
  </div>
</template>

<script>
import DataListItem from '@/components/echarts/DataListItem.vue'
import { getpickerInfo } from '@/api/parking'
export default {
  name: 'HistogramItem',
  components: {
    // eslint-disable-next-line vue/no-unused-components
    DataListItem
  },
  //組件chuan 參，watch 监听改变数据
  props: ['titlename', 'labelname', 'edit', 'myChartId', 'xData', 'yData'],
  data() {
    return {
      // 下拉选项值
      options: [],
      // 条件
      reportFrom: {
        year: '',
        parkingId: ''
      },
      region: '',
      xAxisData: [], // x轴数据
      yAxisData: [], // y轴数据
      // myChartId: "", //ID
      dialogEchartInfoVisible: false,
      EchartInfo: false,
      dialogEchartInfoForm: {
        name: ''
      },
      titlenamedata: '', // 实例中的标题名称
      labelnamedata: '', // 实例中的标签名称
      titless: '', // 图标标题
      shouValue: true, // 柱子上显示值
      shouBord: true, //   显示线
      kedu: true, //   刻度
      zhuziColor: '', //   柱子颜色
      Iswanggexiang: false, //  去除网格分割线
      fengeXianColor: '', //  分割线的颜色
      dataCsolor: '', // 数据值颜色

      formInline: {
        xAxis: '',
        yAxis: ''
      },
      echasSxInfo: {
        titleName: '',
        labelname: '',
        type: [],
        shouValue: '', // 柱子上显示值
        shouBord: '', //   显示线
        kedu: '', //   刻度
        zhuziColor: '#00abf7', //   柱子颜色
        Iswanggexiang: '', //  去除网格分割线
        fengeXianColor: '#e7e7e7', //  分割线的颜色
        dataCsolor: '#00abf7', // 数据值颜色
        xDataColo: '#3a6186' // 坐标值颜色
      },
      rules: {
        xAxis: [
          { required: true, message: '请输入x轴名称值', trigger: 'blur' }
        ],
        yAxis: [
          { required: true, message: '请输入y轴数量值', trigger: 'blur' }
        ]
      }
      //   xAxisData: [],
      //   yAxisData: [],
    }
  },
  watch: {
    xData: {
      immediate: true,
      handler(val) {
        this.xAxisData = val
        if (val.length > 0) {
          (this.EchartInfo = false), this.drawLine() // 重新加载echar方法
        }
      }
    },
    yData: {
      immediate: true,
      handler(val) {
        this.yAxisData = val
        if (val.length > 0) {
          (this.EchartInfo = false), this.drawLine() // 重新加载echar方法
        }
      }
    },
  },
  created() {
    this.addDate()
    this.selectPickerInfo()
    // console.log("BBBBBB", this.xAxisData);
  },
  mounted() {
    if (this.titlename == '') {
      this.titlenamedata = '标题名称'
    } else {
      this.titlenamedata = this.titlename
    }
    if (this.labelname == '') {
      this.labelnamedata = '标签名称'
    } else {
      this.labelnamedata = this.labelname
    }
    this.drawLine()
  },
  methods: {
    // 选择停车场
    repParkingChange(val) {
      this.reportFrom.parkingId = val
      this.$emit('slectParking', this.reportFrom)
    },
    // 选择年份
    yearChange(val) {
      this.reportFrom.year = val
      this.$emit('slectYear', this.reportFrom)
    },

    // 小区下拉列表小区
    selectPickerInfo() {
      getpickerInfo().then((res) => {
        console.log('ddddd', res)
        this.options = res.data
      })
    },

    // 初始化时间
    addDate() {
      const nowDate = new Date()
      const date = {
        year: nowDate.getFullYear(),
        month: nowDate.getMonth() + 1,
        date: nowDate.getDate()
      }
      // console.log(date);
      const systemDate = date.year + '-' + 0 + date.month + '-' + 0 + date.date
      this.reportFrom.year = date.year
    },

    headleChangeColor() {
      this.echasSxInfo.fengeXianColor
    },

    uptateTile() {
      this.drawLine() // 重新加载echart方法
    },

    xydeleteItem(index) {
      this.xAxisData.splice(index, 1)
      this.yAxisData.splice(index, 1)
      this.drawLine() // 重新加载echar方法
    },
    ymodifyItem(newContent, index) {
      this.yAxisData.splice(index, 1, newContent)
      this.drawLine() // 重新加载echar方法
    },
    xmodifyItem(newContent, index) {
      this.xAxisData.splice(index, 1, newContent)
      this.drawLine() // 重新加载echar方法
    },

    submitForm() {
      // debugger
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          this.xAxisData.push(this.formInline.xAxis)
          this.yAxisData.push(this.formInline.yAxis)

          this.drawLine() // 重新加载echar方法
          //   this.formInline.xAxis = ""; //清空用户输入填充值
          //   this.formInline.yAxis = ""; //清空用户输入填充值
        } else {
          return false
        }
      })
    },
    dialogEchartInfoVsc() {
      //   this.titless = this.echasSxInfo.titleName;

      this.shouValue = false
      this.shouBord = false
      this.kedu = false
      this.Iswanggexiang = false
      for (var i = 0; i < this.echasSxInfo.type.length; i++) {
        if (this.echasSxInfo.type[i] == '柱子上显示值') {
          this.shouValue = true
        }
        if (this.echasSxInfo.type[i] == '分割线') {
          this.Iswanggexiang = true
        }
        if (this.echasSxInfo.type[i] == '显示线') {
          this.shouBord = true
        }
        if (this.echasSxInfo.type[i] == '刻度') {
          this.kedu = true
        }
      }

      this.titlenamedata = this.echasSxInfo.titleName // 获取标题值
      this.labelnamedata = this.echasSxInfo.labelname; // 获取标签值

      (this.EchartInfo = false), this.drawLine() // 重新加载echar方法
    },

    drawLine() {
      // console.log("LLLLLLL",document.getElementById("myChart"))
      var myChart = this.$echarts.init(document.getElementById(this.myChartId)) // 获取容器元素
      var option = {
        tooltip: {
          trigger: 'axis'
        },
        grid: {
          left: '6%',
          right: '6%',
          bottom: '6%',
          containLabel: true
        },
        legend: {
          // data:['订单数量','订单数量2'],
          // data:this.titleT,
          left: '6%',
          top: 'top',
          itemWidth: 15, // 图例图标的宽
          itemHeight: 15, // 图例图标的高
          textStyle: {
            color: '#3a6186',
            fontSize: 20
          }
        },
        toolbox: {
          show: true,
          feature: {
            // "stack"
            magicType: { show: true, type: ['line', 'bar'] }
          },
          dataView: {
            // 数据视图
            show: true
          },
          restore: {
            // 重置
            show: true
          },
          dataZoom: {
            // 数据缩放视图
            show: true
          },
          saveAsImage: {
            // 保存图片
            show: true
          },
          right: '6%'
        },
        calculable: true,
        xAxis: [
          {
            type: 'category',
            data: this.xAxisData,
            splitLine: { show: this.Iswanggexiang }, // 去除网格分割线
            // splitLine: {show: false},//去除网格分割线
            axisTick: {
              // 刻度
              show: this.kedu // 不显示刻度线
            },
            axisLine: {
              // 坐标线
              lineStyle: {
                type: 'solid',
                color: '#e7e7e7', // 轴线的颜色
                width: '2' // 坐标线的宽度
              }
            },
            axisLabel: {
              textStyle: {
                color: this.echasSxInfo.xDataColo // 坐标值的具体的颜色
              }
            }
          }
        ],
        yAxis: [
          {
            type: 'value',
            axisLine: {
              // 显示线
              show: this.shouBord
            },
            axisTick: {
              // 刻度
              show: this.kedu
            },
            axisLabel: {
              textStyle: {
                color: this.echasSxInfo.xDataColo // 坐标值的具体的颜色
              }
            },
            splitLine: {
              lineStyle: {
                color: [this.echasSxInfo.fengeXianColor] // 分割线的颜色
              }
            }
          }
        ],
        series: [
          {
            name: this.labelnamedata,
            type: 'bar',
            barWidth: 20,
            data: this.yAxisData,
            itemStyle: {
              normal: {
                color: this.echasSxInfo.zhuziColor, // 设置柱子颜色
                label: {
                  show: this.shouValue, // 柱子上显示值
                  position: 'top', // 值在柱子上方显示
                  textStyle: {
                    color: this.echasSxInfo.dataCsolor, // 值的颜色
                    fontSize: 16
                  }
                }
              }
            }
          }
        ]
      }
      // 防止越界，重绘canvas
      window.onresize = myChart.resize
      myChart.setOption(option) // 设置option
    }
  }
}
</script>

<style>
li {
  list-style: none;
}
.MyAchievement {
  margin-bottom: 107px;
  display: flex;
  flex-direction: column;
  /* padding: 0px 90px; */
}
.MyAchievement .MyAchievement-echart {
  width: 100%;
  height: 570px;
  border-radius: 10px;
  border: 1px solid #d3d9e9;
  box-shadow: 4px 6px 10px -2px #d3d9e9;
  background-color: #fff;
  display: flex;
  flex-direction: column;
}
.MyAchievement-echart .echart-title {
  width: 100%;
  height: 70px;
  padding-left: 24%;
  background-color: #ffffff;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
  font-size: 26px;
  color: #fff;
  text-align: center;
  line-height: 75px;
}
.MyAchievement-echart .echart-content {
  width: 100%;
  height: 500px;
  display: flex;
  align-items: center;
  justify-content: center;
}
.echart-content #myChart {
  margin-top: 35px;
}

/* .abow_dialog {
  display: flex;
  justify-content: center;
  align-items: Center;
  overflow: hidden;
  .el-dialog {
    margin: 0 auto !important;
    height: 90%;
    overflow: hidden;
    .el-dialog__body {
      position: absolute;
      left: 0;
      top: 100px;
      bottom: 0;
      right: 0;
      padding: 0;
      z-index: 1;
      overflow: hidden;
      overflow-y: auto;
    }
  }
}*/
</style>
