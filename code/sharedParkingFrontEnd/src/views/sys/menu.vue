<template>
  <div class="mod-menu">
    <el-form :inline="true" :model="dataForm">
      <el-form-item>
        <!-- <el-button type="primary" @click="addOrUpdateHandle()">新增</el-button> -->
      </el-form-item>
    </el-form>

    <el-table
      style="margin-left: 50px;"
      :data="dataList"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
      row-key="menuId"
    >
      <el-table-column
        prop="name"
        header-align="center"
        min-width="150"
        width="190"
        label="名称"
      />
      <el-table-column
        prop="parentName"
        header-align="center"
        align="center"
        width="120"
        label="上级菜单"
      />
      <el-table-column
        header-align="center"
        align="center"
        width="140"
        label="图标"
      >
        <template slot-scope="scope">
          <!-- <icon-svg :name="scope.row.icon || ''"></icon-svg> -->
          <i :class="scope.row.icon || ''" />
        </template>
      </el-table-column>
      <el-table-column
        prop="type"
        header-align="center"
        align="center"
        width="140"
        label="类型"
      >
        <template slot-scope="scope">
          <el-tag v-if="scope.row.type === 0" size="small">目录</el-tag>
          <el-tag v-else-if="scope.row.type === 1" size="small" type="success">菜单</el-tag>
          <el-tag v-else-if="scope.row.type === 2" size="small" type="info">按钮</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="orderNum"
        header-align="center"
        width="140"
        align="center"
        label="排序号"
      />
      <el-table-column
        prop="component"
        header-align="center"
        align="center"
        width="150"
        :show-overflow-tooltip="true"
        label="页面地址"
      />
      <!-- <el-table-column
        prop="perms"
        header-align="center"
        align="center"
        width="150"
        :show-overflow-tooltip="true"
        label="授权标识">
      </el-table-column> -->
      <el-table-column
        header-align="center"
        align="center"
        width="150"
        label="操作"
      >
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.menuId)">修改</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row.menuId)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList" />
  </div>
</template>

<script>
import AddOrUpdate from './menu-add-or-update'
import { treeDataTranslate } from '@/utils'
import {
  getMenList
} from '@/api/system'
export default {
  components: {
    AddOrUpdate
  },
  data() {
    return {
      dataForm: {},
      dataList: [],
      dataListLoading: false,
      addOrUpdateVisible: false
    }
  },
  created() {
    this.getDataList()
  },
  methods: {
    // 获取数据列表
    getDataList() {
      this.dataListLoading = true
      // this.$http({
      //   url: this.$http.adornUrl('/sys/menu/list'),
      //   method: 'get',
      //   params: this.$http.adornParams()
      // }).then(({data}) => {
      //   this.dataList = treeDataTranslate(data, 'menuId')
      //   this.dataListLoading = false
      // })

      getMenList().then((res) => {
        console.log(res)
        // this.dataList=res.data;
        this.dataList = treeDataTranslate(res.data, 'menuId')
        this.listLoading = false
      })
    },
    // 新增 / 修改
    addOrUpdateHandle(id) {
      this.addOrUpdateVisible = true
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id)
      })
    },
    // 删除
    deleteHandle(id) {
      this.$confirm(`确定对[id=${id}]进行[删除]操作?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl(`/sys/menu/delete/${id}`),
          method: 'post',
          data: this.$http.adornData()
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.$message({
              message: '操作成功',
              type: 'success',
              duration: 1500,
              onClose: () => {
                this.getDataList()
              }
            })
          } else {
            this.$message.error(data.msg)
          }
        })
      }).catch(() => {})
    }
  }
}
</script>
