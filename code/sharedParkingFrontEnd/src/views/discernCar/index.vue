<template>
  <div class="app-container">
    <div class="center">
      <el-row type="flex" justify="center">
        <el-col :span="21">
          <el-row justify="center">
            <el-col
              :md="20"
              :lg="14"
              :xl="14"
              :span="15"
              style="background-color: #f6fffd"
            ><div class="grid-content bg-purple">
              <img
                width="100%"
                height="62%"
                src="@/assets/carImg2.png"
                class="sidebar-logo"
              ></div></el-col>
            <el-col
              :md="23"
              :lg="10"
              :xl="6"
              :span="6"
              style="background-color: #fafafa"
            >
              <div class="rightDiv">
                <div class="intDiv">
                  <el-row>
                    <el-col :span="12">
                      <div class="tileText">
                        <label for="进入操作">进入操作</label>
                      </div>
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-form
                      status-icon
                      label-width="60px"
                      class="demo-ruleForm"
                    >
                      <el-form-item label="车牌号" prop="pass">
                        <el-input
                          v-model="intCarVal"
                          autocomplete="off"
                          style="width: 70%"
                        />
                      </el-form-item>

                      <el-form-item>
                        <el-button
                          type="primary"
                          @click="submitIntTo"
                        >确认进入</el-button>
                        <el-button
                          type="success"
                          @click="intDistinguish"
                        >重新识别</el-button>
                      </el-form-item>
                    </el-form>
                  </el-row>
                  <el-row class="flasP">
                    <div>
                      <p>车牌号：{{ intCarNumber }}</p>
                    </div>
                    <div>
                      <p>订单号：{{ resIntData.orderId }}</p>
                    </div>
                    <div>
                      <p>进场时间：{{ resIntData.approachTime }}</p>
                    </div>
                  </el-row>
                </div>

                <div class="intDiv" style="border-top: 1px solid #e2e2e2">
                  <el-row>
                    <el-col :span="12">
                      <div class="tileText" style="padding-top: 4%">
                        <label for="离开操作">离开操作</label>
                      </div>
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-form
                      status-icon
                      label-width="60px"
                      class="demo-ruleForm"
                    >
                      <el-form-item label="车牌号" prop="pass">
                        <el-input
                          v-model="outCarVal"
                          autocomplete="off"
                          style="width: 70%"
                        />
                      </el-form-item>

                      <el-form-item>
                        <el-button
                          type="primary"
                          @click="submitOutTo"
                        >确认离开</el-button>
                        <el-button
                          type="success"
                          @click="outDistinguish"
                        >重新识别</el-button>
                      </el-form-item>
                    </el-form>
                  </el-row>
                </div>

                <el-row class="flasP">
                  <div>
                    <p>订单号：{{ resOutData.orderId }}</p>
                  </div>
                  <div>
                    <p>车牌号：{{ outCarNumber }}</p>
                  </div>
                  <div>
                    <p>进场时间：{{ resOutData.approachTime }}</p>
                  </div>
                  <div>
                    <p>离场时间：{{ resOutData.actualPlayingTime }}</p>
                  </div>
                  <div>
                    <p>停车时长：{{ resOutData.parkingDuration }}</p>
                  </div>
                </el-row>
              </div>
            </el-col>
          </el-row>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import {
  identificationPlateIntoThe,
  orderConfirmationForSettlement
} from '@/api/order'

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      intCarVal: '',
      outCarVal: '',
      intCarNumber: '',
      outCarNumber: '',
      resIntData: {},
      resOutData: {}
    }
  },
  created() {},
  methods: {
    arrceClick(e) {
      console.log(',,,', e)
      arrces(e.ownerAuthId).then((res) => {
        this.getInitTableData()
      })
    },
    // 进入
    submitIntTo() {
      identificationPlateIntoThe({ licensePlateNumber: this.intCarVal }).then(
        (res) => {
          if (res.code == 200) {
            // this.resIntData[carNumber] = this.intCarVal;
            this.intCarNumber = this.intCarVal
            this.resIntData = res.data
            this.$message({
              showClose: true,
              message: '进场成功',
              type: 'success'
            })
          } else {
            this.$message.error(res.msg)
          }
        }
      )
    },

    // 离开
    submitOutTo() {
      this.listLoading = true
      orderConfirmationForSettlement({
        licensePlateNumber: this.outCarVal
      }).then((res) => {
        if (res.code == 200) {
          this.resOutData = res.data
          // this.resOutData[carNumber] = this.intCarVal;
          this.outCarNumber = this.outCarVal
          this.$message({
            showClose: true,
            message: '成功离场',
            type: 'success'
          })
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    // 进入识别
    intDistinguish() {
      this.intCarVal = ''
      this.intCarNumber = ''
      this.resIntData = ''
    },

    // 出场识别
    outDistinguish() {
      this.outCarVal = ''
      this.outCarNumber = ''
      this.resOutData = ''
    }
  }
}
</script>
<style scoped>
.rightDiv {
  padding-left: 5%;
  width: 100%;
  height: 100%;
}
.intDiv {
  /* padding-bottom: 20%; */
}

.outDiv {
  padding-bottom: 20%;
}
.tileText {
  padding-bottom: 3%;
}
.flasP p {
  font-size: 0.9rem;
}
</style>
