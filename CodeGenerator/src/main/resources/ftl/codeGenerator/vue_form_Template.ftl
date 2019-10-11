<template>
  <div>
    <div style="overflow-y:auto;max-height:500px;margin-bottom:20px">
      <el-form
        ref="form"
        :model="form"
        :rules="rules"
        label-width="100px"
      >
  <#list columnList as column>
    <#if column.isPK == "NO">
        <el-form-item
          label="${column.columnComment}"
          prop="${column.columnName}"
        >
        <#if column.dataType=="String">
          <el-input
            v-model="form.${column.columnName}"
            :readonly="readOnlyFlag"
          ></el-input>
        </#if>
        <#if column.dataType=="Number">
          <el-input-number
            v-model="form.${column.columnName}"
            :disabled="readOnlyFlag"
          ></el-input-number>
        </#if>
        <#if column.dataType=="Boolean">
          <el-switch
            v-model="form.${column.columnName}"
            active-color="#13ce66"
            inactive-color="#ff4949"
            active-text="是"
            inactive-text="否"
            :disabled="readOnlyFlag"
          >
          </el-switch>
        </#if>
        <#if column.dataType=="DateTime">
          <el-date-picker
            v-model="form.${column.columnName}"
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
      submitUrl: '/${objectName}/',
      form: {
        <#list columnList as column>
          <#if column.dataType=="String">
        ${column.columnName}: '',
          <#elseif column.dataType=="Number">
        ${column.columnName}: 0,
          <#elseif column.dataType=="Boolean">
        ${column.columnName}: false,
          <#elseif column.dataType=="DateTime">
        ${column.columnName}: '',
          </#if>
        </#list>
      },
      rules: {
        <#list columnList as column>
        <#if column.isNullable == "NO">
        ${column.columnName}: [
          { required: true, message: '该数据为必填项', trigger: 'blur' },
        ],
        </#if>
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
      this.mixPost('/${objectName}/findById', {
        ${primaryKeyColumn.columnName}: this.parameter.currentRow.${primaryKeyColumn.columnName}
      }).then(res => {
        this.form = res.data
        this.replaceAttribute();
        this.mixSetFormToken(this.formToken);
      }).catch(err => { })
    },
    replaceAttribute() {
        <#list columnList as column>
            <#if column.dataType == "DateTime">
      this.form.${column.columnName} = this.mixTimeStamp2String(this.form.${column.columnName}, "YYYY-MM-DD HH:mm:ss")
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

