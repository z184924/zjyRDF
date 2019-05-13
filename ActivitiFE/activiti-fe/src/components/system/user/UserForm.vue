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
          label="账号"
          prop="account"
        >
          <el-input
            v-model="form.account"
            :readonly="readOnlyFlag"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="密码"
          prop="password"
        >
          <el-input
            v-model="form.password"
            :readonly="readOnlyFlag"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="账号名称"
          prop="userName"
        >
          <el-input
            v-model="form.userName"
            :readonly="readOnlyFlag"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="是否锁定"
          prop="locked"
        >
          <el-switch
            v-model="form.locked"
            active-color="#13ce66"
            inactive-color="#ff4949"
            active-text="是"
            inactive-text="否"
            :disabled="readOnlyFlag"
          >
          </el-switch>
        </el-form-item>
        <el-form-item
          label="是否禁用"
          prop="disable"
        >
          <el-switch
            v-model="form.disable"
            active-color="#13ce66"
            inactive-color="#ff4949"
            active-text="是"
            inactive-text="否"
            :disabled="readOnlyFlag"
          >
          </el-switch>
        </el-form-item>
        <el-form-item
          label="特殊角色"
          prop="specialRole"
        >
          <el-input
            v-model="form.specialRole"
            :readonly="readOnlyFlag"
          ></el-input>
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
      submitUrl: '/user/',
      form: {
        userId: '',
        account: '',
        password: '',
        userName: '',
        locked: false,
        disable: false,
        specialRole: '',
      },
      rules: {
        userId: [
          { required: true, message: '该数据为必填项', trigger: 'blur' },
        ],
        account: [
          { required: true, message: '该数据为必填项', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '该数据为必填项', trigger: 'blur' },
        ],
        userName: [
          { required: true, message: '该数据为必填项', trigger: 'blur' },
        ],
        locked: [
          { required: true, message: '该数据为必填项', trigger: 'blur' },
        ],
        disable: [
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
      this.mixPost('/user/findById', {
        userId: this.parameter.currentRow.userId
      }).then(res => {
        this.form = res.data
        this.replaceAttribute();
        this.mixSetFormToken(this.formToken);
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

