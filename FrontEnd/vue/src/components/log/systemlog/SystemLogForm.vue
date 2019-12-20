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
          label="系统标签"
          prop="systemTag"
        >
          <el-input
            v-model="form.systemTag"
            :readonly="readOnlyFlag"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="请求时间"
          prop="requestTime"
        >
          <el-date-picker
            v-model="form.requestTime"
            :readonly="readOnlyFlag"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择日期时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item
          label="响应时间"
          prop="responseTime"
        >
          <el-date-picker
            v-model="form.responseTime"
            :readonly="readOnlyFlag"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择日期时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item
          label="IP"
          prop="ip"
        >
          <el-input
            v-model="form.ip"
            :readonly="readOnlyFlag"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="用户id"
          prop="userId"
        >
          <el-input
            v-model="form.userId"
            :readonly="readOnlyFlag"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="用户名"
          prop="userName"
        >
          <el-input
            v-model="form.userName"
            :readonly="readOnlyFlag"
          ></el-input>
        </el-form-item>
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
          label="行为注释"
          prop="annotation"
        >
          <el-input
            v-model="form.annotation"
            :readonly="readOnlyFlag"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="请求路径"
          prop="requestPath"
        >
          <el-input
            v-model="form.requestPath"
            :readonly="readOnlyFlag"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="请求参数"
          prop="requestParameter"
        >
          <el-input
            v-model="form.requestParameter"
            :readonly="readOnlyFlag"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="响应内容"
          prop="responseBody"
        >
          <el-input
            v-model="form.responseBody"
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
      fromTag: '',
      formToken: {
        token: ''
      },
      readOnlyFlag: false,
      submitUrl: '/systemLog/',
      form: {
        logId: 0,
        systemTag: '',
        requestTime: '',
        responseTime: '',
        ip: '',
        userId: '',
        userName: '',
        account: '',
        annotation: '',
        requestPath: '',
        requestParameter: '',
        responseBody: '',
      },
      rules: {
        logId: [
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
      this.mixPost('/systemLog/findById', {
        logId: this.parameter.currentRow.logId,
        tableName:this.parameter.currentRow.tableName
      }).then(res => {
        this.form = res.data
        this.replaceAttribute();
        this.mixSetFormToken(this.formToken);
      }).catch(err => { })
    },
    replaceAttribute() {
      this.form.requestTime = this.mixTimeStamp2String(this.form.requestTime, "YYYY-MM-DD HH:mm:ss")
      this.form.responseTime = this.mixTimeStamp2String(this.form.responseTime, "YYYY-MM-DD HH:mm:ss")
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

