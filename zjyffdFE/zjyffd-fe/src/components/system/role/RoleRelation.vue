<template>
  <div>
    <div style="overflow-y:auto;max-height:500px;margin-bottom:20px">
      <el-tree
        ref="tree"
        :data="treeData"
        show-checkbox
        default-expand-all
        :node-key="noedKey"
        :props="treeProps"
        @check-change="handleCheckChange"
      ></el-tree>
    </div>
    <div style="text-align:right">
      <el-button
        size="medium"
        @click="closeDialog"
      >取消</el-button>
      <el-button
        type="primary"
        size="medium"
        @click="submit"
      >确定</el-button>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    parameter: {},
  },
  data() {
    return {
      getDataUrl: '',
      submitUrl: '',
      noedKey: '',
      treeData: [],
      checkedKeys: [],
      treeProps: {
        children: 'children',
        label: ''
      }
    };
  },
  methods: {
    submit() {
      this.mixPost(this.submitUrl, {
        checkedKeys: this.checkedKeys.join(),
        roleId: this.parameter.currentRow.roleId
      }).then(res => {
        this.$emit("refreshTable")
        this.$emit("closeDialog")
      })
    },
    closeDialog() {
      this.$emit("closeDialog")
    },
    handleCheckChange(data, checked) {
      if (checked) {
        this.checkedKeys.push(data[this.noedKey])
      } else {
        this.checkedKeys.splice(this.checkedKeys.findIndex(item => item == data[this.noedKey]), 1)
      }
    },
  },
  created() {
    switch (this.parameter.formTag) {
      case 'User':
        this.noedKey = 'userId'
        this.treeProps.label = 'userName'
        this.getDataUrl = 'api/role/listUserRole'
        this.submitUrl = 'api/role/editUserRole'
        break;
      case 'Rights':
        this.noedKey = 'rightsId'
        this.treeProps.label = 'rightsName'
        this.getDataUrl = 'api/role/listRoleRights'
        this.submitUrl = 'api/role/editRoleRights'
        break;
      default:
        this.$message({
          message: '错误的formTag',
          type: 'error'
        });
    }
  },
  mounted() {
    this.mixPost(this.getDataUrl, {
      roleId: this.parameter.currentRow.roleId
    }).then(res => {
      switch (this.parameter.formTag) {
        case 'User':
          this.treeData = res.data.userList
          res.data.userRoleList.forEach(element => {
            this.checkedKeys.push(element[this.noedKey])
          });
          this.$refs.tree.setCheckedKeys(this.checkedKeys);
          break;
        case 'Rights':
          this.treeData = this.mixCreateTreeData('rightsId', res.data.rightsList, 0)
          res.data.roleRightsList.forEach(element => {
            this.checkedKeys.push(element[this.noedKey])
          });
          this.$refs.tree.setCheckedKeys(this.checkedKeys);
          break;
        default:
          this.$message({
            message: '错误的formTag',
            type: 'error'
          });
      }
    })

  }
}
</script>
