<template>
  <div class="app-container">
    <div class="" style="padding-bottom: 18px">
      <el-row :gutter="20">
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
          <el-input
            v-model="searchFrom.parkingSpaceNumber"
            placeholder="车位号"
          /></div></el-col>

        <el-col
          :xs="6"
          :span="3"
        ><div class="grid-content bg-purple">
          <el-input
            v-model="searchFrom.ownerName"
            placeholder="业主名称"
          /></div></el-col>
        <el-col
          :xs="6"
          :span="3"
        ><div class="grid-content bg-purple">
          <el-select v-model="searchFrom.state" placeholder="请选择车位状态">
            <el-option label="共享中" value="共享中" />
            <el-option label="未共享" value="未共享" />
            <el-option label="禁用" value="禁用" />
          </el-select></div></el-col>
        <el-col
          :xs="6"
          :span="3"
        ><div class="grid-content bg-purple">
          <el-select
            v-model="searchFrom.isTakeUp"
            placeholder="请选择使用状态"
          >
            <el-option label="占用" value="0" />
            <el-option label="未占用" value="1" />
          </el-select></div></el-col>
        <el-col
          :sm="8"
          :span="6"
        ><div class="grid-content bg-purple">
          <el-button
            type="primary"
            icon="el-icon-search"
            @click="searchClick()"
          >搜索</el-button>
          <el-button icon="el-icon-refresh-right" @click="resetSearch()">
            重置
          </el-button>
        </div></el-col>
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
      <el-table-column align="center" label="车位号" width="95">
        <template slot-scope="scope">
          {{ scope.row.parkingSpaceNumber }}
        </template>
      </el-table-column>
      <el-table-column label="小区名称" width="200" align="center">
        <template slot-scope="scope">
          {{ scope.row.villageName }}
        </template>
      </el-table-column>
      <el-table-column label="业主名称" width="150" align="center">
        <template slot-scope="scope">
          {{ scope.row.ownerName }}
        </template>
      </el-table-column>
      <el-table-column label="共享状态" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.state }}</span>
        </template>
      </el-table-column>
      <el-table-column label="使用状态" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.isTakeUp | formatStatus }}</span>
        </template>
      </el-table-column>
      <el-table-column label="开始共享时间" width="180" align="center">
        <template slot-scope="scope">
          {{ scope.row.shareStartTime }}
        </template>
      </el-table-column>
      <el-table-column label="结束共享时间" width="180" align="center">
        <template slot-scope="scope">
          {{ scope.row.shareEndTime }}
        </template>
      </el-table-column>
      <el-table-column label="价钱" width="150" align="center">
        <template slot-scope="scope"> {{ scope.row.price }}元/小时 </template>
      </el-table-column>

      <el-table-column label="操作" width="100" align="center">
        <template slot-scope="scope">
          <el-button
            type="primary"
            icon="el-icon-edit"
            size="mini"
            @click="editClick(scope.row.parkingSpaceId)"
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
      title="修改车位信息"
      :visible.sync="dialogFormVisible"
      :destroy-on-close="true"
      width="45%"
    >
      <el-form :model="form" class="demo-form-inline">
        <el-form-item label="共享状态" label-width="130px">
          <el-select v-model="form.state" placeholder="请选择停车场状态">
            <el-option label="未共享" value="未共享" />
            <el-option label="共享中" value="共享中" />
          </el-select>
        </el-form-item>

        <el-form-item label="使用状态" label-width="130px">
          <el-select v-model="isTakeUp" placeholder="请选择停车场状态">
            <el-option label="未占用" value="未占用" />
            <el-option label="占用" value="占用" />
          </el-select>
        </el-form-item>

        <el-form-item label="价钱" label-width="130px">
          <el-input
            v-model="form.price"
            style="width: 60%"
            type="number"
            placeholder="元"
          />
        </el-form-item>

        <el-form-item label="共享时间" label-width="130px">
          <el-date-picker
            v-model="parkingTime"
            format="yyyy-MM-dd HH:mm:ss"
            value-format="yyyy-MM-dd HH:mm:ss"
            type="datetimerange"
            range-separator="至"
            start-placeholder="开始时间"
            end-placeholder="结束时间"
          />
        </el-form-item>
        <!-- <el-form-item label="结束共享时间" label-width="130px">
          <el-input
            v-model="form.phone"
            autocomplete="off"
            class="input-width"
          ></el-input>
        </el-form-item> -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitData">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  getParkingSpaceList,
  getSpaceServiceById,
  update
} from '@/api/parkingSpace'

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
  filters: {
    // 是否占用，0：占用，1：未占用"
    formatStatus(value) {
      if (value == 0) {
        return '未占用'
      } else if (value == 1) {
        return '占用'
      }
    }
  },
  data() {
    return {
      testTime: '',
      parkingTime: [],
      // parkingTime:[new Date(2000, 10, 10, 10, 10), new Date(2000, 10, 11, 10, 10)],
      dialogFormVisible: false,
      isTakeUp: null,
      form: {
        parkingSpaceId: null,
        state: '',
        isTakeUp: '',
        price: null,
        shareStartTime: '',
        shareEndTime: ''
      },
      searchFrom: {
        parkingInfoId: '',
        villageName: '',
        address: '',
        isTakeUp: '',
        pageSize: 10,
        current: 1
      },
      total: null,
      list: [],
      listLoading: true
    }
  },
  created() {
    this.getParkingListData()
  },
  methods: {
    submitData() {
      this.form.shareStartTime = this.formatTime(this.parkingTime[0])
      this.form.shareEndTime = this.formatTime(this.parkingTime[1])
      if (this.isTakeUp == '占用') {
        this.form.isTakeUp = 1
      } else {
        this.form.isTakeUp = 0
      }
      update(this.form).then((res) => {
        this.dialogFormVisible = false
        this.getParkingListData()
        this.$message({
          message: '修改成功',
          type: 'success'
        })
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
    getParkingListData() {
      this.listLoading = true
      getParkingSpaceList(this.searchFrom).then((res) => {
        if (res.data) {
          this.list = res.data.records
          this.total = res.data.total
          this.searchFrom.current = res.data.current
          this.searchFrom.pageSize = res.data.size
        }
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
    searchClick() {
      this.searchFrom.current = 1
      this.searchFrom.pageSize = 10
      this.getParkingListData()
    },
    // 编辑
    editClick(e) {
      this.dialogFormVisible = true
      getSpaceServiceById(e).then((res) => {
        this.form = res.data
        this.parkingTime = [
          new Date(res.data.shareStartTime),
          new Date(res.data.shareEndTime)
        ]
        if (res.data.isTakeUp == 1) {
          this.isTakeUp = '占用'
        } else {
          this.isTakeUp = '未占用'
        }
      })

      console.log('chakan')
    },
    // 触发页大小
    handleSizeChange(e) {
      this.searchFrom.pageSize = e
      this.getParkingListData()
    },
    // 触发当前页面
    handleCurrentChange(e) {
      this.searchFrom.current = e
      this.getParkingListData()
    },
    // 删除数据
    delectClick(e) {
      this.getParkingListData()
    }
  }
}
</script>
