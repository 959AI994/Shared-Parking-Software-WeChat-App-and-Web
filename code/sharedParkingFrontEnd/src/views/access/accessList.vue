<template>
  <div class="app-container">
    <div class="" style="padding-bottom: 18px">
      <el-row :gutter="20">
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
            v-model="searchFrom.villageName"
            placeholder="停车小区名称"
          /></div></el-col>
        <el-col
          :xs="6"
          :span="3"
        ><div class="grid-content bg-purple">
          <el-select v-model="searchFrom.state" placeholder="停车状态">
            <el-option label="停车中" value="停车中" />
            <el-option label="已完成" value="已完成" />
          </el-select></div></el-col>
        <el-col
          :xs="6"
          :span="3"
        ><div class="grid-content bg-purple">
          <el-select v-model="searchFrom.type" placeholder="停车类型">
            <el-option label="共享车" value="共享车" />
            <el-option label="业主车" value="业主车" />
          </el-select></div></el-col>
        <el-col :xs="6" :span="3.2">
          <el-date-picker
            v-model="searchFrom.approachTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择进场日期时间"
          />
        </el-col>
        <el-col :xs="6" :span="3.2">
          <el-date-picker
            v-model="searchFrom.playingTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择出场日期时间"
          />
        </el-col>
        <el-col :xs="2" :span="1.5">
          <el-button
            type="primary"
            icon="el-icon-search"
            @click="searchClick()"
          >搜索</el-button>
        </el-col>
        <el-col :xs="2" :span="1.5">
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
      <el-table-column align="center" label="记录编号" width="95">
        <template slot-scope="scope">
          {{ scope.row.accessRecordsId }}
        </template>
      </el-table-column>
      <el-table-column label="车牌号" width="120" align="center">
        <template slot-scope="scope">
          {{ scope.row.carNumber }}
        </template>
      </el-table-column>
      <el-table-column label="小区停车场" width="300" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.villageName }}</span>
        </template>
      </el-table-column>

      <el-table-column label="状态" width="180" align="center">
        <template slot-scope="scope">
          {{ scope.row.state }}
        </template>
      </el-table-column>
      <el-table-column label="停车类型" width="90" align="center">
        <template slot-scope="scope">
          {{ scope.row.type }}
        </template>
      </el-table-column>
      <el-table-column label="进场时间" width="200" align="center">
        <template slot-scope="scope">
          {{ scope.row.approachTime }}
        </template>
      </el-table-column>
      <el-table-column label="出场时间" width="200" align="center">
        <template slot-scope="scope">
          {{ scope.row.playingTime }}
        </template>
      </el-table-column>

      <el-table-column label="操作" width="150" align="center">
        <template slot-scope="scope">
          <el-button
            type="text"
            size="small"
            @click="arrceClick(scope.row)"
          >修改</el-button>
          <el-button
            type="text"
            size="small"
            @click="deleteItem(scope.row)"
          >删除</el-button>
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
  </div>
</template>

<script>
import {
  getAccessPageList,
  addAccess,
  updateAccess,
  deleteAccess
} from '@/api/access'

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
      searchFrom: {
        villageName: '',
        carNumber: '',
        type: '',
        eNumber: '',
        state: '',
        approachTime: '',
        playingTime: '',
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
    arrceClick(e) {
      console.log(',,,', e)
      arrces(e.ownerAuthId).then((res) => {
        this.getInitTableData()
      })
    },
    getInitTableData() {
      this.listLoading = true
      console.log('ddd', this.searchFrom)
      getAccessPageList(this.searchFrom).then((res) => {
        this.list = res.data.records
        this.total = res.data.total
        this.searchFrom.current = res.data.current
        this.searchFrom.pageSize = res.data.size
        this.listLoading = false
      })
    },
    // 搜索
    searchClick() {
      this.getInitTableData()
    },
    // 重置
    resetSearch() {
      Object.keys(this.searchFrom).map((key) => (this.searchFrom[key] = ''))
      this.searchFrom.current = 1
      this.searchFrom.pageSize = 10
    },
    // 删除
    deleteItem(e) {
      this.$confirm('确认删除?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          deleteAccess({ accessRecordsId: e.accessRecordsId }).then((res) => {
            this.getInitTableData()
          })
          this.$message({
            type: 'success',
            message: '删除出入记录成功!'
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
