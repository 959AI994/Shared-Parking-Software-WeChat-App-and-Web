<template>
  <div class="app-container">
    <div class="" style="padding-bottom: 18px">
      <el-row :gutter="20">
        <el-col
          :xs="6"
          :span="3"
        ><div class="grid-content bg-purple">
          <el-input
            v-model="searchFrom.openId"
            placeholder="用户编号"
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
          <el-select v-model="searchFrom.state" placeholder="请选择状态">
            <el-option label="正常" value="正常" />
            <el-option label="禁用" value="禁用" />
          </el-select></div></el-col>
        <el-col
          :xs="3"
          :span="2"
        ><div class="grid-content bg-purple">
          <el-button
            icon="el-icon-refresh-right"
            @click="resetSearch()"
          >重置</el-button>
        </div></el-col>

        <el-col
          :xs="3"
          :span="2"
        ><div class="grid-content bg-purple">
          <el-button type="primary" @click="searchClick()">搜索</el-button>

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
      <el-table-column
        prop="userId"
        header-align="center"
        align="center"
        width="80"
        label="ID"
      />
      <el-table-column
        prop="username"
        header-align="center"
        align="center"
        label="用户名"
      />
      <el-table-column
        prop="email"
        header-align="center"
        align="center"
        label="邮箱"
      />
      <el-table-column
        prop="mobile"
        header-align="center"
        align="center"
        label="手机号"
      />
      <el-table-column
        prop="status"
        header-align="center"
        align="center"
        label="状态"
      >
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status === 0" size="small" type="danger">禁用</el-tag>
          <el-tag v-else size="small">正常</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        width="180"
        label="创建时间"
      />
      <el-table-column label="操作" width="100" align="center">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="deleteItem(scope.row)" />
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
import { getSysUserList } from '@/api/system'

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        deleted: 'danger'
      }
      return statusMap[status]
    },
    formatSexStatus(value) {
      if (value == 0) {
        return '男'
      } else if (value == 1) {
        return '女'
      }
    }

  },
  data() {
    return {
      searchFrom: {
        openId: '',
        nickName: '',
        gender: '',
        email: '',
        state: '',
        phone: '',
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
    getInitTableData() {
      this.listLoading = true
      getSysUserList(this.searchFrom).then((res) => {
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
    deleteItem(e) {},
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
