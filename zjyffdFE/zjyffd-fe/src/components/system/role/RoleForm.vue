<template>
  <div>
    <div style="overflow-y:auto;max-height:500px;margin-bottom:20px">
      <el-form
        ref="form"
        :model="form"
        :rules="rules"
        label-width="100px"
      >
        <el-form-item
          label="角色名称"
          prop="roleName"
        >
          <el-input
            v-model="form.roleName"
            :readonly="readOnlyFlag"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="排序编码"
          prop="sorting"
        >
          <el-input-number
            v-model="form.sorting"
            :disabled="readOnlyFlag"
          ></el-input-number>
        </el-form-item>
      </el-form>
    </div>
    <div style="text-align:right">
      <el-button
        size="medium"
        @click="closeDialog"
      >取消</el-button>
      <el-button
        v-if="!readOnlyFlag"
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
      fromTag: "",
      readOnlyFlag: false,
      submitUrl: 'api/role/',
      form: {
        roleId: '',
        roleName: '',
        sorting: 0,
      },
      rules: {
        roleId: [
          { required: true, message: '该数据为必填项', trigger: 'blur' },
        ],
        roleName: [
          { required: true, message: '该数据为必填项', trigger: 'blur' },
        ],
        sorting: [
          { required: true, message: '该数据为必填项', trigger: 'blur' },
        ],
      }
    }
  },
  methods: {
    submit() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          this.mixPost(this.submitUrl, this.form).then(res => {
            this.$emit("refreshTable")
            this.$emit("closeDialog")
          })
        } else {
          return false;
        }
      })
    },
    closeDialog() {
      this.$emit("closeDialog")
    },
    getFormData() {
      this.mixPost('api/role/findById', {
        roleId: this.parameter.currentRow.roleId
      }).then(res => {
        this.form = res.data
        this.replaceAttribute();
      })
    },
    replaceAttribute() {
    }
  },
  mounted() {
    this.formTag = this.parameter.formTag;
    switch (this.formTag) {
      case 'add':
        this.readOnlyFlag = false;
        this.submitUrl = this.submitUrl + 'save';
        break;
      case 'edit':
        this.readOnlyFlag = false;
        this.submitUrl = this.submitUrl + 'edit';
        this.getFormData();
        break;
      case 'detail':
        this.readOnlyFlag = true;
        this.getFormData();
        break;
      default:
        this.$message({
          message: '错误的formTag',
          type: 'error'
        });
    }
  }
}
</script>

