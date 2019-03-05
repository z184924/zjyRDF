<template>
  <div>
    <div style="text-align:right">
      <el-button type="primary" icon="el-icon-refresh" circle @click="refresh()"></el-button>
    </div>
    <el-table :data="processList">
      <el-table-column label="流程ID">
        <template slot-scope="scope">
          {{scope.row.id}}
        </template>
      </el-table-column>
      <el-table-column label="流程名称">
        <template slot-scope="scope">
          {{scope.row.name}}
        </template>
      </el-table-column>
      <el-table-column label="流程版本">
        <template slot-scope="scope">
          {{scope.row.version}}
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="startProcess(scope.row.id,scope.row.name)">启动流程</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
export default {
  data(){
    return{
      processList:[]
    }
  },
  mounted(){
    this.mixPost('api/testProcess/getAllResource',{}).then(res=>{
      this.processList=res.data.resultList;
    })
  },
  methods:{
    startProcess(processId,processName){
       this.$confirm('您是否要启动'+processName+'?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.mixPost('api/testProcess/startProcess',{'processId':processId}).then(res=>{
            this.$message({
                type: res.state,
                message: res.message
            });
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消启动'
          });          
        });
    }
  }
  
}
</script>
