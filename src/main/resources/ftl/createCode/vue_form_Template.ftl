<template>
  <div>
    <el-form
      ref="form"
      :model="form"
      label-width="100px"
    >
<#list fieldList as var>
  <#if var[4] == "否">
      <#if var[1]=="String">
      <el-form-item label="${var[2]}">
        <el-input
          v-model="form.${var[0]}"
          :readonly="readOnlyFlag"
        ></el-input>
      </el-form-item>
      </#if>
      <#if var[1]=="Boolean">
        <el-form-item label="${var[2]}">
        <el-switch
                v-model="form.${var[0]}"
                active-color="#13ce66"
                inactive-color="#ff4949"
                active-text="是"
                inactive-text="否"
                :disabled="readOnlyFlag"
        >
        </el-switch>
        </el-form-item>
      </#if>
  </#if>
</#list>
    </el-form>
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
      submitUrl:'api/${objectNameLower}/',
      form: {
        account: "",
        password: "",
        userName: "",
        locked: false,
        disable: false,
        specialRole: ""
      }
    }
  },
  methods: {
    submit() {
      this.mixPost(this.submitUrl, this.form).then(res => {
        this.$emit("refreshTable")
        this.$emit("closeDialog")
      })
    },
    closeDialog() {
      this.$emit("closeDialog")
    },
    getFormData() {
      this.mixPost('api/${objectNameLower}/findById', {
          <#list fieldList as var>
            <#if var[4] == "是">
          ${var[0]}: this.parameter.currentRow.${var[0]}
            </#if>
          </#list>
      }).then(res => {
        this.form = res.data
      })
    }
  },
  mounted() {
    this.formTag = this.parameter.formTag;
    switch(this.formTag){
      case 'add':
        this.readOnlyFlag=false;
        this.submitUrl=this.submitUrl+'save';
        break;
      case 'edit':
        this.readOnlyFlag=false;
        this.submitUrl=this.submitUrl+'edit';
        this.getFormData();
        break;
      case 'detail':
        this.readOnlyFlag=true;
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

