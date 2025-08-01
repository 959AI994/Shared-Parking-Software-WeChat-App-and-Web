<template>
  <div class="app-container">
    <div class="" style="padding-bottom: 18px">
      <el-row :gutter="20">
        <el-col
          :xs="6"
          :span="3"
        ><div class="grid-content bg-purple">
          <el-input
            v-model="searchFrom.parkingInfoId"
            type="number"
            placeholder="停车场编号"
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
          <el-input
            v-model="searchFrom.address"
            placeholder="地址"
          /></div></el-col>

        <el-col
          :xs="6"
          :span="3"
        ><div class="grid-content bg-purple">
          <el-select v-model="searchFrom.state" placeholder="请选择状态">
            <el-option label="正常" value="正常" />
            <el-option label="下线" value="下线" />
          </el-select></div></el-col>
        <el-col
          :xs="6"
          :span="3"
        ><div class="grid-content bg-purple">
          <el-input
            v-model="searchFrom.phone"
            placeholder="小区电话"
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
        <el-col :xs="2" :span="1.5">
          <el-button
            plain
            icon="el-icon-circle-plus-outline"
            @click="addParking"
          >添加</el-button>
        </el-col>
      </el-row>
    </div>
    <!-- <div class="">
      <el-row>
        <el-button icon="el-icon-search" circle></el-button>

        <el-button type="success" icon="el-icon-check" circle></el-button>
        <el-button type="info" icon="el-icon-message" circle></el-button>
        <el-button type="warning" icon="el-icon-star-off" circle></el-button>
        <el-button type="danger" icon="el-icon-delete" circle></el-button>
      </el-row>
    </div> -->

    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="停车小区编号" width="110">
        <template slot-scope="scope">
          {{ scope.row.parkingInfoId }}
        </template>
      </el-table-column>
      <el-table-column label="名称" width="200" align="center">
        <template slot-scope="scope">
          {{ scope.row.villageName }}
        </template>
      </el-table-column>
      <!-- <el-table-column label="状态" width="100" align="center">
        <template slot-scope="scope">
          {{ scope.row.state }}
        </template>
      </el-table-column> -->
      <el-table-column
        align="center"
        prop="state"
        label="状态"
        width="100"
        :filters="[{ text: '正常', value: '正常' }, { text: '下线', value: '下线' }]"
        :filter-method="filterTag"
        filter-placement="bottom-end"
      >
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.state === '正常' ? 'primary' : 'success'"
            disable-transitions
          >{{ scope.row.state }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column label="联系电话" width="140" align="center">
        <template slot-scope="scope">
          {{ scope.row.phone }}
        </template>
      </el-table-column>
      <el-table-column label="经度" width="180" align="center">
        <template slot-scope="scope">
          {{ scope.row.longitude }}
        </template>
      </el-table-column>
      <el-table-column label="纬度" width="180" align="center">
        <template slot-scope="scope">
          {{ scope.row.latitude }}
        </template>
      </el-table-column>
      <el-table-column label="地址" width="365" align="center">
        <template slot-scope="scope">
          {{ scope.row.province }}{{ scope.row.city }}{{ scope.row.area }}{{ scope.row.address }}
        </template>
      </el-table-column>
      <el-table-column label="操作" width="100" align="center">
        <template slot-scope="scope">
          <el-button
            type="primary"
            icon="el-icon-edit"
            size="mini"
            @click="editClick(scope.row)"
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
      :title="showState?'修改小区停车场':'添加小区停车场'"
      :visible.sync="dialogFormVisible"
      :destroy-on-close="true"
      width="55%"
    >
      <el-form :inline="true" :model="form" class="demo-form-inline">
        <el-input
          v-show="false"
          v-model="form.parkingInfoId"
          type="hidden"
        />
        <el-form-item label="小区名称" label-width="130px">
          <el-input
            v-model="form.villageName"
            autocomplete="off"
            class="input-width"
          />
        </el-form-item>

        <el-form-item label="地区" label-width="130px">
          <v-distpicker :province="form.province" :city="form.city" :area="form.area" @selected="onSelected" />
        </el-form-item>

        <el-form-item label="地址" label-width="130px">
          <el-input
            v-model="form.address"
            autocomplete="off"
            class="input-width"
            placeholder="例：中国地质大学（武汉）"
            @blur="addressFocus"
          />
        </el-form-item>
        <el-form-item label="联系电话" label-width="130px">
          <el-input
            v-model="form.phone"
            autocomplete="off"
            class="input-width"
          />
        </el-form-item>

        <el-form-item label="经度" label-width="130px">
          <el-input
            v-model="form.longitude"
            autocomplete="off"
            class="input-width"
          />
        </el-form-item>
        <el-form-item label="纬度" label-width="130px">
          <el-input
            v-model="form.latitude"
            autocomplete="off"
            class="input-width"
          />
        </el-form-item>

        <el-form-item v-show="showState" label="状态" label-width="140px">
          <el-select v-model="form.state" placeholder="请选择停车场状态">
            <el-option label="正常" value="正常" />
            <el-option label="下线" value="下线" />
          </el-select>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitData">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import VDistpicker from 'v-distpicker'
import {
  getParkingList,
  getLonLat,
  parkingInfoAdd,
  getParkingInfo
} from '@/api/parking'

export default {
  components: { VDistpicker },
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
      showState: false, // 弹出框的状态是否显示
      dialogFormVisible: false,
      province: '',
      city: '',
      area: '',
      form: {
        province: '',
        city: '',
        area: '',
        parkingInfoId: '',
        villageName: '',
        address: '',
        state: '',
        phone: '',
        longitude: '',
        latitude: ''
      },
      formLabelWidth: '120px',
      searchFrom: {
        parkingSpaceNumber: '',
        villageName: '',
        ownerName: '',
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
    this.getParkingListData()
  },
  methods: {
    addParking() {
      Object.keys(this.form).map((key) => (this.form[key] = ''))
      this.dialogFormVisible = true
      this.showState = false
    },
    // 选择地区
    onSelected(data) {
      this.form.province = data.province.value
      this.form.city = data.city.value
      this.form.area = data.area.value
      console.log('选择地区', this.form)
    },
    // 获取数据
    getParkingListData() {
      this.listLoading = true

      getParkingList(this.searchFrom).then((res) => {
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
    searchClick() {
      var phone = this.searchFrom.phone
      if (phone.length == 11 || phone == '') {
        this.searchFrom.current = 1
        this.searchFrom.pageSize = 10
        this.getParkingListData()
      } else {
        this.$message({
          message: '电话输入错误',
          type: 'warning'
        })
      }
    },
    // 获取地址的经纬度
    addressFocus(e) {
      console.log('llll', this.form)
      if (this.form.address == null || this.form.address == '') {
        this.$message({
          message: '请输入地址!',
          type: 'error'
        })
      } else {
        // 地址拼接
        getLonLat(
          this.form.province +
            this.form.city +
            this.form.area +
            this.form.address
        ).then((res) => {
          //  console.log(res)
          this.form.longitude = res.data.longitude
          this.form.latitude = res.data.latitude
        })
      }
    },

    // 提交添加数据
    submitData() {
      console.log('test', this.form)
      // 前端要调用api，来获取数据，parkingInfoAdd函数里面放的其实就是前端到后端的API，从这个API可以想后端发送http请求
      parkingInfoAdd(this.form).then((res) => {
        if (res.code == 200) {
          this.dialogFormVisible = false
          this.showState = false
          Object.keys(this.form).map((key) => (this.form[key] = ''))
          this.getParkingListData()
          this.$message({
            message: '操作成功！',
            type: 'success'
          })
        }
      })
    },
    filterTag(value, row) {
      return row.state === value
    },

    // 编辑查询
    editClick(e) {
      this.showState = true // 显示编辑状态的标识。
      getParkingInfo(e.parkingInfoId).then((res) => {
        console.log('chakan', res) // 打印出响应结果
        this.form = res.data // 将响应数据的内容赋值给 this.form
        this.dialogFormVisible = true // 显示编辑对话框的标识
      })
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
      this.current = e
      this.getParkingListData()
    }
  }
}
</script>

<style scoped>
.input-width {
  width: 120%;
}
</style>
