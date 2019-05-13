<template>
  <div>
    <div v-if="parameter.type.name=='String'">
      <el-form-item :label="parameter.name">
        <el-input type="textarea" autosize placeholder="请输入内容" v-model="textValue"></el-input>
      </el-form-item>
    </div>
    <div v-if="parameter.type.name=='boolean'">
      <el-form-item :label="parameter.name">
        <el-switch v-model="switchValue" active-color="#13ce66" inactive-color="#ff4949" active-text="是" inactive-text="否"></el-switch>
      </el-form-item>
    </div>
    <div v-if="parameter.type.name=='DateTime'">
      <el-form-item :label="parameter.name">
        <el-date-picker v-model="dateValue" type="datetime" placeholder="选择日期时间"></el-date-picker>
      </el-form-item>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    parameter: {},
    value: {}
  },
  watch: {
    textValue(){
      this.$emit("input", this.textValue);
    },
    switchValue() {
      this.$emit("input", this.switchValue);
    },
    textValue() {
      this.$emit("input", this.textValue);
    }
  },
  data() {
    return {
      switchValue: true,
      dateValue: '',
      textValue: ''
    }
  },
  created() {
    switch(this.parameter.type.name){
      case 'String':
        this.textValue=this.value;
        break;
      case 'boolean':
        this.switchValue=this.value;
        break;
      case 'DateTime':
        this.dateValue=this.value;
        break;
    }
  }
}
</script>

