<template>
  <div>
    <div style="overflow-y:auto;max-height:500px;margin-bottom:20px">
      <el-form
        ref="form"
        :model="form"
        :rules="rules"
        label-width="100px"
      >
  <#list fieldList as var>
    <#if var[4] == "否">
        <el-form-item
          label="${var[2]}"
          prop="${var[0]}"
        >
        <#if var[1]=="String">
          <el-input
            v-model="form.${var[0]}"
            :readonly="readOnlyFlag"
          ></el-input>
        </#if>
        <#if var[1]=="Number">
          <el-input-number
            v-model="form.${var[0]}"
            :disabled="readOnlyFlag"
          ></el-input-number>
        </#if>
        <#if var[1]=="Boolean">
          <el-switch
            v-model="form.${var[0]}"
            active-color="#13ce66"
            inactive-color="#ff4949"
            active-text="是"
            inactive-text="否"
            :disabled="readOnlyFlag"
          >
          </el-switch>
        </#if>
        <#if var[1]=="DateTime">
          <el-date-picker
            v-model="form.${var[0]}"
            :readonly="readOnlyFlag"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择日期时间"
          >
          </el-date-picker>
        </#if>
        </el-form-item>
    </#if>
  </#list>
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
      submitUrl: '/${objectNameLower}/',
      form: {
        <#list fieldList as var>
          <#if var[1]=="String">
        ${var[0]}: '',
          <#elseif var[1]=="Number">
        ${var[0]}: 0,
          <#elseif var[1]=="Boolean">
        ${var[0]}: false,
          <#elseif var[1]=="DateTime">
        ${var[0]}: '',
          </#if>
        </#list>
      },
      rules: {
        <#list fieldList as var>
        ${var[0]}: [
          { required: true, message: '该数据为必填项', trigger: 'blur' },
        ],
        </#list>
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
      this.mixPost('/${objectNameLower}/findById', {
          <#list fieldList as var>
            <#if var[4] == "是">
        ${var[0]}: this.parameter.currentRow.${var[0]}
            </#if>
          </#list>
      }).then(res => {
        this.form = res.data
        this.replaceAttribute();
        this.mixSetFormToken(this.formToken);
      }).catch(err => { })
    },
    replaceAttribute() {
        <#list fieldList as var>
            <#if var[1] == "DateTime">
      this.form.${var[0]} = this.mixTimeStamp2String(this.form.${var[0]}, "YYYY-MM-DD HH:mm:ss")
            </#if>
        </#list>
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

