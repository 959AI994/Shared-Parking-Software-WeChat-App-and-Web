<template>
  <div class="app-container">
    <div class="" style="padding-bottom: 18px">
      <el-row :gutter="20">
        <el-col
          :xs="6"
          :span="3"
        ><div class="grid-content bg-purple">
          <el-input
            v-model="searchFrom.orderId"
            placeholder="订单编号"
          /></div></el-col>
        <el-col
          :xs="6"
          :span="3"
        ><div class="grid-content bg-purple">
          <el-input
            v-model="searchFrom.nickName"
            placeholder="用户昵称"
          /></div></el-col>
        <el-col
          :xs="6"
          :span="3"
        ><div class="grid-content bg-purple">
          <el-input
            v-model="searchFrom.phone"
            placeholder="联系电话"
          /></div></el-col>

        <el-col
          :xs="6"
          :span="3"
        ><div class="grid-content bg-purple">
          <el-input
            v-model="searchFrom.carNumber"
            placeholder="车牌号"
          /></div></el-col>
        <el-col
          :xs="6"
          :span="3"
        ><div class="grid-content bg-purple">
          <el-input
            v-model="searchFrom.parkingSpaceNumber"
            placeholder="车位号"
          /></div></el-col>
        <el-col
          :xs="6"
          :span="3"
        ><div class="grid-content bg-purple">
          <el-input
            v-model="searchFrom.villageName"
            placeholder="小区名称"
          /></div></el-col>
        <el-col
          :xs="6"
          :span="3"
        ><div class="grid-content bg-purple">
          <el-select v-model="searchFrom.state" placeholder="请选择状态">
            <el-option label="进行中" value="进行中" />
            <el-option label="已完成" value="已完成" />
            <el-option label="预约中" value="预约中" />
          </el-select></div></el-col>
        <el-col :span="1.2">
          <el-button
            type="primary"
            icon="el-icon-search"
            @click="search()"
          >搜索</el-button>
        </el-col>
        <el-col :span="1">
          <el-button
            icon="el-icon-refresh-right"
            @click="resetSearch()"
          >重置</el-button>
        </el-col>
      </el-row>
    </div>

    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="订单编号" width="200">
        <template slot-scope="scope">
          {{ scope.row.orderId }}
        </template>
      </el-table-column>

      <el-table-column label="用户昵称" width="100" align="center">
        <template slot-scope="scope">
          {{ scope.row.nickName }}
        </template>
      </el-table-column>
      <el-table-column label="联系电话" width="120" align="center">
        <template slot-scope="scope">
          {{ scope.row.phone }}
        </template>
      </el-table-column>
      <el-table-column label="车位号" width="90" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.parkingSpaceNumber }}</span>
        </template>
      </el-table-column>

      <el-table-column label="车牌号" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.carNumber }}</span>
        </template>
      </el-table-column>
      <el-table-column label="停车场名称" width="120" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.villageName }}</span>
        </template>
      </el-table-column>

      <el-table-column label="进场时间" width="160" align="center">
        <template slot-scope="scope">
          {{ scope.row.approachTime }}
        </template>
      </el-table-column>

      <el-table-column label="实际出场时间" width="170 " align="center">
        <template slot-scope="scope">
          {{ scope.row.actualPlayingTime }}
        </template>
      </el-table-column>

      <el-table-column label="状态" width="70" align="center">
        <template slot-scope="scope">
          {{ scope.row.state }}
        </template>
      </el-table-column>
      <el-table-column label="创建时间" width="180" align="center">
        <template slot-scope="scope">
          {{ scope.row.createTime }}
        </template>
      </el-table-column>

      <el-table-column label="支付金额" width="150" align="center">
        <template slot-scope="scope">
          {{ scope.row.paymentAmount }}
          <span v-if="scope.row.paymentAmount != null">元</span>
        </template>
      </el-table-column>
      <el-table-column label="支付时间" width="160" align="center">
        <template slot-scope="scope">
          {{ scope.row.paymentTime }}
        </template>
      </el-table-column>

      <el-table-column label="操作" width="130" align="center">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="edit(scope.row)" />
          <el-button
            type="danger"
            icon="el-icon-delete"
            size="mini"
            @click="deleteItem(scope.row.orderId)"
          />
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      style="padding-top: 5px"
      :page-sizes="[10, 20, 30, 50, 100]"
      :page-size="this.searchFrom.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="this.total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />

    <el-dialog
      title="修改订单信息"
      :visible.sync="dialogFormVisible"
      :destroy-on-close="true"
      width="30%"
    >
      <el-form :model="form" class="demo-form-inline">
        <el-form-item label="订单状态" label-width="130px">
          <el-select v-model="form.state">
            <el-option label="进行中" value="进行中" />
            <el-option label="已完成" value="已完成" />
          </el-select>
        </el-form-item>

        <el-form-item label="支付状态" label-width="130px">
          <el-select v-model="form.paymentStatus">
            <el-option label="已支付" value="已支付" />
            <el-option label="未支付" value="未支付" />
          </el-select>
        </el-form-item>

        <el-form-item label="金额" label-width="130px">
          <el-input
            v-model="form.paymentAmount"
            style="width: 60%"
            type="number"
            placeholder="元"
          />
        </el-form-item>

        <el-form-item label="停车时长" label-width="130px">
          <el-input
            v-model="form.parkingDuration"
            style="width: 60%"
            type="text"
          />
        </el-form-item>

        <el-form-item label="进场时间" label-width="130px">
          <el-date-picker
            v-model="form.approachTime"
            type="datetime"
            placeholder="选择进场时间"
          />

        </el-form-item>

        <el-form-item label="出场时间" label-width="130px">
          <el-date-picker
            v-model="form.actualPlayingTime"
            type="datetime"
            placeholder="选择出场时间"
          />

        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submiUpdatetData">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getPageList, deleteOrder, update } from '@/api/order'

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
      dialogFormVisible: false,
      form: {
        orderId: '',
        state: '',
        approachTime: '',
        paymentAmount: '',
        paymentStatus: '',
        actualPlayingTime: '',
        parkingDuration: ''

      },
      searchFrom: {
        orderId: '',
        nickName: '',
        phone: '',
        villageName: '',
        parkingSpaceNumber: '',
        carNumber: '',
        pageSize: 10,
        current: 1
      },
      total: null,
      list: [],
      listLoading: true
    }
  },
  created() {
    this.getInitTableData()
  },
  methods: {
    edit(e) {
      this.form = e
      this.dialogFormVisible = true
    },
    // update
    submiUpdatetData() {
      var intTime = this.formatTime(this.form.approachTime)
      var outTime = this.formatTime(this.form.actualPlayingTime)
      this.form.approachTime = intTime
      this.form.actualPlayingTime = outTime
      console.log(this.form, 'sefse')
      update(this.form).then((res) => {
        if (res.code == 200) {
          this.dialogFormVisible = false
          this.getInitTableData()
          Object.keys(this.form).map((key) => (this.form[key] = ''))
        }
      })
    },
    formatTime(timeMills) {
      var date = new Date(timeMills)
      var timeStr = date.getFullYear() + '-'
      if (date.getMonth() < 9) {
        // 月份从0开始的
        timeStr += '0'
      }
      timeStr += date.getMonth() + 1 + '-'
      timeStr += date.getDate() < 10 ? '0' + date.getDate() : date.getDate()
      timeStr += ' '
      timeStr += date.getHours() < 10 ? '0' + date.getHours() : date.getHours()
      timeStr += ':'
      timeStr +=
        date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()
      timeStr += ':'
      timeStr +=
        date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds()
      return timeStr
    },
    getInitTableData() {
      this.listLoading = true
      getPageList(this.searchFrom).then((res) => {
        this.list = res.data.records
        this.total = res.data.total
        this.searchFrom.current = res.data.current
        this.searchFrom.pageSize = res.data.size
        this.listLoading = false
      })
    },
    // 重置
    resetSearch() {
      Object.keys(this.searchFrom).map((key) => (this.searchFrom[key] = ''))
      this.searchFrom.current = 1
      this.searchFrom.pageSize = 10
    },
    // 搜索
    search() {
      var phone = this.searchFrom.phone
      if (phone.length == 11 || phone == '') {
        this.searchFrom.current = 1
        this.searchFrom.pageSize = 10
        this.getInitTableData()
      } else {
        this.$message({
          message: '电话输入错误',
          type: 'warning'
        })
      }
    },
    // 删除
    deleteItem(e) {
      this.$confirm('确认删除订单:' + e, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          deleteOrder(e)
            .then((res) => {
              if (res.code == 200) {
                this.$message({
                  message: '删除成功',
                  type: 'success'
                })
                this.getInitTableData() // 重新加载表格数据
              } else {
                this.$message.error('删除失败')
              }
            })
            .catch((err) => {
              console.log(err)
            })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    },
    // 触发页大小
    handleSizeChange(e) {
      this.searchFrom.pageSize = e
      this.getInitTableData()
    },
    // 触发当前页面
    handleCurrentChange(e) {
      this.searchFrom.current = e
      this.getInitTableData()
    }
  }
}
</script>
