<template>
  <div class="app-container">
    <div class="" style="padding-bottom: 18px">
      <el-row :gutter="20">
        <el-col
          :xs="6"
          :span="3"
        ><div class="grid-content bg-purple">
          <el-input
            v-model="searchFrom.ownerAuthId"
            placeholder="用户编号"
          /></div></el-col>
        <el-col
          :xs="6"
          :span="3"
        ><div class="grid-content bg-purple">
          <el-input
            v-model="searchFrom.ownerName"
            placeholder="相关内容"
          /></div></el-col>
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
      <el-table-column align="center" label="反馈编号" width="80">
        <template slot-scope="scope">
          {{ scope.row.feedBackId }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="用户编号" width="350">
        <template slot-scope="scope">
          {{ scope.row.openId }}
        </template>
      </el-table-column>
      <el-table-column label="反馈内容" width="400" align="center">
        <template slot-scope="scope">
          {{ scope.row.content }}
        </template>
      </el-table-column>
      <el-table-column label="反馈时间" width="200" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>

      <el-table-column label="操作" width="100" align="center">
        <template slot-scope="scope">
          <el-button type="danger" icon="el-icon-delete" size="mini" @click="deleteItem(scope.row)" />
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
import { getList, deleteFeedBack } from '@/api/feedBack'

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
        ownerAuthId: '',
        ownerName: '',
        phone: '',
        idNumber: '',
        villageName: '',
        parkingTileNumber: '',
        state: '',
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
      getList(this.searchFrom).then((res) => {
        console.log(res.data)
        this.list = res.data.records
        this.total = res.data.total
        this.searchFrom.current = res.data.current
        this.searchFrom.pageSize = res.data.size
        this.listLoading = false
      })
    },
    // 搜索
    searchClick() {
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
    // 重置
    resetSearch() {
      Object.keys(this.searchFrom).map((key) => (this.searchFrom[key] = ''))
      this.searchFrom.current = 1
      this.searchFrom.pageSize = 10
    },
    // 删除
    deleteItem(e) {
      console.log(44444, e.feedBackId)
      deleteFeedBack(e.feedBackId).then((res) => {
        this.$message({
          message: '删除成功',
          type: 'success'
        })
        this.getInitTableData()
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
