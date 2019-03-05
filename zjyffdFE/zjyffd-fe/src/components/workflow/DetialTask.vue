<template>
  <div>
    <div>
      <img v-if="taskInfo.id" :src="mixBasePath+'api/testProcess/getProcessDiagram?taskId='+taskInfo.id+'&token='+mixCurrentUser.token">
    </div>
    <div v-for="formItem in taskInfo.formProperties">
      <el-form>
        <form-element v-bind:parameter="formItem" v-model="formItem.value"></form-element>
      </el-form>
    </div>
    <div style="text-align: right;">
      <el-button type="primary" @click="finish()">办结</el-button>
      <el-button @click="closeSelfTab()">关闭</el-button>
    </div>
    <div>
      <el-collapse v-model="activeNames">
        <el-collapse-item :title="taskInfo.name" :name="taskInfo.id">

        </el-collapse-item>
      </el-collapse>
    </div>
  </div>
</template>
<script>
import FormElement from "@/components/workflow/FormElement"
export default {
  components:{
    FormElement
  },
  props: {
    parameter: {}
  },
  data() {
    return {
      taskInfo: {},
      processDiagram: {},
      activeNames: []
    }
  },
  methods: {
    closeSelfTab() {
      let tab = {
        name: this.parameter.tabName
      }
      this.$emit("closeSelfTab", tab);
    },
    finish() {
      this.$confirm('您是否要办结该任务?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.mixPost('api/testProcess/finishTask',this.createFormData()).then(res => {
          this.$message({
            type: res.state,
            message: res.message
          });
          this.$emit("refreshTab",{name:'1-2'})
          this.closeSelfTab()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消办结'
        });
      });
    },
    createFormData(){
      let fromData={}
      this.$set(fromData,'taskId',this.taskInfo.id);
      this.taskInfo.formProperties.forEach(element => {
        this.$set(fromData,element.id,element.value);
      });
      return fromData;
    },
  },
  mounted() {
    this.mixPost('api/testProcess/getTaskInfo', { taskId: this.parameter.taskId }).then(res => {
      this.taskInfo = res.data.taskInfo;
      this.taskInfo.formProperties.forEach(element => {
        let propertyName=element.id
        switch(element.type.name){
          case 'String':
            this.$set(element,'value','')
            break;
          case 'boolean':
            this.$set(element,'value',true)
            break;
          case 'DateTime':
            this.$set(element,'value','')
            break;
        }
      });
    })
    this.activeNames.push(this.parameter.taskId)
  }
}
</script>
