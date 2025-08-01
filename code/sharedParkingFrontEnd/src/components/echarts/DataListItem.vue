<template>
  <div class="item" style="text-align: left">
    <p>
      <el-row>
        <el-col
          :span="20"
        ><div class="grid-content bg-purple">
          <el-link v-show="!isActive" type="primary" @click="ativeItem">{{
            item
          }}</el-link>

          <el-link v-show="isActive" type="primary">
            <el-input
              v-model="content"
              size="mini"
              type="text"
              @blur="inactiveItem"
            />
          </el-link></div></el-col>
        <el-col
          :span="2"
        ><div class="">
          <el-link type="info">
            <i class="el-icon-circle-close" @click="deletelistItem" />
          </el-link></div></el-col>
      </el-row>
    </p>
  </div>
</template>
<script>
export default {
  name: 'TodoListItem',
  props: ['item', 'index'],
  data() {
    return {
      content: '',
      isActive: false
    }
  },
  methods: {
    ativeItem() {
      this.isActive = true
      this.content = this.item
    },
    inactiveItem() {
      if (this.content != '') {
        this.$emit('modifyItem', this.content, this.index)
        this.isActive = false
      }
    },
    deletelistItem(index) {
      this.$emit('deleteItem', index)
    }
  }
}
</script>
