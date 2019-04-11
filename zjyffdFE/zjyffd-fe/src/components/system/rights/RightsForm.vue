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
          label="URL"
          prop="url"
        >
          <el-input
            v-model="form.url"
            :readonly="readOnlyFlag"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="权限代码"
          prop="rightsCode"
        >
          <el-input
            v-model="form.rightsCode"
            :readonly="readOnlyFlag"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="权限名称"
          prop="rightsName"
        >
          <el-input
            v-model="form.rightsName"
            :readonly="readOnlyFlag"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="权限类型"
          prop="rightsType"
        >
          <el-input-number
            v-model="form.rightsType"
            :disabled="readOnlyFlag"
          ></el-input-number>
        </el-form-item>
        <el-form-item
          label="上级Id"
          prop="parentId"
        >
          <el-input-number
            v-model="form.parentId"
            :readonly="readOnlyFlag"
            :min="0"
          ></el-input-number>
        </el-form-item>
        <el-form-item
          label="图标"
          prop="icon"
        >
          <el-input
            v-model="form.icon"
            :readonly="readOnlyFlag"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="排序编号"
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
      formToken: {
        token: ''
      },
      readOnlyFlag: false,
      submitUrl: '/rights/',
      form: {
        rightsId: '',
        url: '',
        rightsCode: '',
        rightsName: '',
        rightsType: 0,
        parentId: '',
        icon: '',
        sorting: 0,
      },
      rules: {
        rightsId: [
          { required: true, message: '该数据为必填项', trigger: 'blur' },
        ],
        url: [
          { required: true, message: '该数据为必填项', trigger: 'blur' },
        ],
        rightsCode: [
          { required: true, message: '该数据为必填项', trigger: 'blur' },
        ],
        rightsName: [
          { required: true, message: '该数据为必填项', trigger: 'blur' },
        ],
        rightsType: [
          { required: true, message: '该数据为必填项', trigger: 'blur' },
        ],
        parentId: [
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
          this.form.formToken = this.formToken.token
          this.mixPost(this.submitUrl, this.form).then(res => {
            this.$emit("refreshTable")
            this.$emit("closeDialog")
          }).catch(err => { })
        } else {
          return false;
        }
      })
    },
    closeDialog() {
      this.$emit("closeDialog")
    },
    getFormData() {
      this.mixPost('/rights/findById', {
        rightsId: this.parameter.currentRow.rightsId
      }).then(res => {
        this.form = res.data
        this.replaceAttribute()
        this.mixSetFormToken(this.formToken)
      }).catch(err => { })
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
        this.mixSetFormToken(this.formToken);
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

