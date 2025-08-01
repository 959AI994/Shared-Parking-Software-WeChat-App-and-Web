<template>
  <div class="app-container">
    <div class="" style="padding-bottom: 18px">
      <el-row :gutter="20">
        <el-col :xs="6" :span="3"
          ><div class="grid-content bg-purple">
            <el-input
              v-model="searchFrom.ownerAuthId"
              placeholder="业主编号"
            ></el-input></div
        ></el-col>
        <el-col :xs="6" :span="3"
          ><div class="grid-content bg-purple">
            <el-input
              v-model="searchFrom.ownerName"
              placeholder="业主名称"
            ></el-input></div
        ></el-col>
        <el-col :xs="6" :span="3"
          ><div class="grid-content bg-purple">
            <el-input
              v-model="searchFrom.phone"
              placeholder="联系电话"
            ></el-input></div
        ></el-col>
        <el-col :xs="6" :span="3"
          ><div class="grid-content bg-purple">
            <el-input
              v-model="searchFrom.parkingTileNumber"
              placeholder="车位产权证编号"
            ></el-input></div
        ></el-col>
        <el-col :xs="6" :span="3"
          ><div class="grid-content bg-purple">
            <el-input
              v-model="searchFrom.villageName"
              placeholder="小区名称"
            ></el-input></div
        ></el-col>
        <el-col :xs="6" :span="3"
          ><div class="grid-content bg-purple">
            <el-select v-model="searchFrom.state" placeholder="请选择状态">
              <el-option label="正常" value="正常"></el-option>
              <el-option label="待审核" value="待审核"></el-option>
              <el-option label="禁用" value="禁用"></el-option>
            </el-select></div
        ></el-col>
         <el-col :sm="6" :span="2"
          ><div class="grid-content bg-purple">
            <el-button
              type="primary"
              icon="el-icon-search"
              @click="searchClick()"
              >搜索</el-button
            >
            <el-button icon="el-icon-refresh-right" @click="resetSearch()">
              重置
            </el-button>
          </div></el-col
        >
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
      <el-table-column align="center" label="业主编号" width="95">
        <template slot-scope="scope">
          {{ scope.row.ownerAuthId }}
        </template>
      </el-table-column>
      <el-table-column label="业主名称" width="120" align="center">
        <template slot-scope="scope">
          {{ scope.row.ownerName }}
        </template>
      </el-table-column>
      <el-table-column label="性别" width="80" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.gender | formatSexStatus }}</span>
        </template>
      </el-table-column>

      <el-table-column label="电话" width="180" align="center">
        <template slot-scope="scope">
          {{ scope.row.phone }}
        </template>
      </el-table-column>
      <el-table-column label="状态" width="90" align="center">
        <template slot-scope="scope">
          {{ scope.row.state }}
        </template>
      </el-table-column>
      <el-table-column label="身份证号" width="200" align="center">
        <template slot-scope="scope">
          {{ scope.row.idNumber }}
        </template>
      </el-table-column>
      <el-table-column label="认证小区" width="200" align="center">
        <template slot-scope="scope">
          {{ scope.row.villageName }}
        </template>
      </el-table-column>
      <el-table-column label="认证车位" width="130" align="center">
        <template slot-scope="scope">
          {{ scope.row.parkingSpaceNumber }}
        </template>
      </el-table-column>
      <el-table-column label="车位产权证编号" width="180" align="center">
        <template slot-scope="scope">
          {{ scope.row.parkingTileNumber }}
        </template>
      </el-table-column>
      <el-table-column label="证明材料" width="110" align="center">
        <template slot-scope="scope">
          <el-image
            style="width: 40px; height: 40px"
            :src="scope.row.imgUrl"
            :preview-src-list="[scope.row.imgUrl]"
          >
          </el-image>
        </template>
      </el-table-column>
      <el-table-column label="不通过原因" width="130" align="center">
        <template slot-scope="scope">
          {{ scope.row.notThroughReason }}
        </template>
      </el-table-column>

      <el-table-column label="操作" width="150" align="center">
        <template slot-scope="scope">
         
          <el-button type="danger" @click="arrceClick(scope.row)" icon="el-icon-s-custom" size="mini"></el-button>
          <!-- <el-button type="primary" @click="deleteItem(scope.row)" icon="el-icon-edit" size="mini"></el-button> -->
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      style="padding-top: 5px"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :page-sizes="[10, 20, 30, 50, 100]"
      :page-size="this.searchFrom.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="this.total"
    >
    </el-pagination>

    <el-dialog
      title="业主信息通过"
      :visible.sync="dialogState"
      :destroy-on-close="true"
    >
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="业主名称" label-width="130px">
          <el-input
            type="text"
            v-model="diaoForm.ownerName"
          ></el-input>
        </el-form-item>
        <el-form-item label="性别" label-width="130px">
          <el-input
            type="text"
            v-model="diaoForm.gender"
          ></el-input>
        </el-form-item>
        <el-form-item label="电话" label-width="130px">
          <el-input
            type="text"
            v-model="diaoForm.phone"
          ></el-input>
        </el-form-item>
        <el-form-item label="身份证号" label-width="130px">
              <el-input
                type="text"
                v-model="diaoForm.idNumber"
              ></el-input>
        </el-form-item>

        <el-form-item label="车位产权证编号" label-width="130px">
              <el-input
                type="text"
                v-model="diaoForm.parkingTileNumber"
              ></el-input>
        </el-form-item>

        <el-form-item label="小区名称" label-width="130px">
              <el-input
                type="text"
                v-model="diaoForm.villageName"
              ></el-input>
        </el-form-item>

        <el-form-item label="是否通过" label-width="140px">
          <el-select v-model="form.state" placeholder="请选择审核状态">
            <el-option label="通过" value="正常"></el-option>
            <el-option label="不通过" value="不通过"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item
          label="不通过原因"
          label-width="130px"
          v-if="this.form.state != '正常'"
        >
          <el-input
            v-model="form.notThroughReason"
            autocomplete="off"
            class="input-width"
          ></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogState = false">取 消</el-button>
        <el-button type="primary" @click="subAuthData">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { geOwnerList, arrces } from "@/api/owner";

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: "success",
        draft: "gray",
        deleted: "danger",
      };
      return statusMap[status];
    },
    formatSexStatus(value) {
      if (value == 0) {
        return "男";
      } else if (value == 1) {
        return "女";
      }
    },
  },
  data() {
    return {
      diaoForm:{
        ownerName: "",
        phone: "",
        gender:"",
        idNumber: "",
        villageName: "",
        parkingTileNumber: "",
        state: "",
        notThroughReason:""
      },
      dialogState: false,
      form: {
        ownerAuthId: "",
        state: "正常",
        notThroughReason: "",
      },
      searchFrom: {
        ownerAuthId: "",
        ownerName: "",
        phone: "",
        idNumber: "",
        villageName: "",
        parkingTileNumber: "",
        state: "",
        pageSize: 10,
        current: 1,
      },
      total: null,
      list: [],
      listLoading: true,
      dialogTitle :"确认业主信息认证通过"
    };
  },
  created() {
    this.getInitTableData();
  },
  methods: {
    arrceClick(e) {
      this.diaoForm=e
      if(e.gender==1){
       this.diaoForm.gender="女"
      }else{
      this.diaoForm.gender="男"
      }

      if(e.state=='正常'||e.state=='未审核'){
         this.form.ownerAuthId = e.ownerAuthId;
          this.dialogTitle="是否给【"+e.ownerName+"】通过业主认证?"
         this.form.state="正常"
        this.form.notThroughReason=""
        this.dialogState = true;
      }else{
        this.form.ownerAuthId = e.ownerAuthId;
        this.dialogTitle="是否给【"+e.ownerName+"】通过业主认证?"
         this.form.state="不通过"
        this.form.notThroughReason=e.notThroughReason
        this.dialogState = true;
      }
     
    },
    subAuthData() {
      if (this.form.state == "不通过") {

        if (this.form.notThroughReason == ""|| this.form.notThroughReason==null ) {
              this.$message({
              message: "请输入不通过原因",
              type: "warning",
            });
        }else{
          this. authSubmit() 
        }
      }else{
        this.form.notThroughReason=''
        this. authSubmit() 
      }
    },

    //认证提交
    authSubmit() {
      arrces(this.form).then((res) => {
        this.dialogState = false;
        this.form.notThroughReason = "";
        this.form.state = "正常";
        this.$message({
          message: "操作成功！",
          type: "success",
        });
        this.getInitTableData();
      });
    },

    getInitTableData() {
      this.listLoading = true;
      geOwnerList(this.searchFrom).then((res) => {
        this.list = res.data.records;
        this.total = res.data.total;
        this.searchFrom.current = res.data.current;
        this.searchFrom.pageSize = res.data.size;
        this.listLoading = false;
      });
    },
    //搜索
    searchClick() {
      var phone = this.searchFrom.phone;
      if (phone.length == 11 || phone == "") {
        this.searchFrom.current = 1;
        this.searchFrom.pageSize = 10;
        this.getInitTableData();
      } else {
        this.$message({
          message: "电话输入错误",
          type: "warning",
        });
      }
    },
    //重置
    resetSearch() {
      Object.keys(this.searchFrom).map((key) => (this.searchFrom[key] = ""));
      this.searchFrom.current = 1;
      this.searchFrom.pageSize = 10;
    },
    //删除
    deleteItem(e) {},
    //触发页大小
    handleSizeChange(e) {
      this.searchFrom.pageSize = e;
      this.getInitTableData();
    },
    //触发当前页面
    handleCurrentChange(e) {
      this.searchFrom.current = e;
      this.getInitTableData();
    },
  },
};
</script>
