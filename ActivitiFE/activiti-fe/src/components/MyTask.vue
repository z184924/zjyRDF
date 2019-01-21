<template>
  <div>
    <el-table :data="taskList">
      <el-table-column label="任务ID">
        <template slot-scope="scope">
          {{scope.row.id}}
        </template>
      </el-table-column>
      <el-table-column label="任务名称">
        <template slot-scope="scope">
          {{scope.row.name}}
        </template>
      </el-table-column>
      <el-table-column label="创建时间">
        <template slot-scope="scope">
          {{mixTimeStamp2String(scope.row.createTime,'YYYY-MM-DD HH:mm:ss')}}
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
export default {
  data(){
    return{
      taskList:[]
    }
  },
  mounted(){
    this.mixPost('api/testProcess/getMyTask',{}).then(res=>{
      this.taskList=res.data.taskList;
    })
  },
}
</script>
