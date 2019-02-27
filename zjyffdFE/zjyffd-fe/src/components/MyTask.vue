<template>
  <div>
    <div style="text-align:right">
      <el-button type="primary" icon="el-icon-refresh" circle @click="refresh()"></el-button>
    </div>
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
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" type="primary" @click="detail(scope.row.id,scope.row.name)">查看</el-button>
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
  methods:{
    refresh(){
      this.mixPost('api/testProcess/getMyTask',{}).then(res=>{
        this.taskList=res.data.taskList;
      })
    },
    detail(id,name){
      let tab={
        title:name,
        name:id,
        content:'detial-task',
        parameter:{
          tabName:id,
          taskId:id,
        }
      }
      this.$emit("openSubTab",tab);
    }
  }
}
</script>
