<template>
  <div>
    <el-row type="flex">
      <el-col :span="6">
        <div>
          <el-input
            placeholder="请输入内容"
            v-model="searchInput"
          >
            <el-button
              slot="append"
              icon="el-icon-search"
            ></el-button>
          </el-input>
        </div>
      </el-col>
      <el-col :span="6">
        <div>
        </div>
      </el-col>
      <el-col :span="6">
        <div>
        </div>
      </el-col>
      <el-col
        :span="6"
        style="text-align:right"
      >
        <div>
          <el-button
            v-if="mixButtonAuthenticate(parameter.buttonList,'${objectNameLower}-add')"
            type="success"
            icon="el-icon-plus"
            circle
            @click="addRow()"
          ></el-button>
          <el-button
            v-if="mixButtonAuthenticate(parameter.buttonList,'${objectNameLower}-edit')"
            type="warning"
            icon="el-icon-edit"
            circle
            @click="editRow()"
          ></el-button>
          <el-button
            v-if="mixButtonAuthenticate(parameter.buttonList,'${objectNameLower}-delete')"
            type="danger"
            icon="el-icon-delete"
            circle
            @click="deleteRow()"
          ></el-button>
          <el-button
            v-if="mixButtonAuthenticate(parameter.buttonList,'${objectNameLower}-detail')"
            type="info"
            icon="el-icon-document"
            circle
            @click="detailRow()"
          ></el-button>
          <el-button
            type="primary"
            icon="el-icon-refresh"
            circle
            @click="refreshTable"
          ></el-button>
        </div>
      </el-col>
    </el-row>
    <el-table
      :data="tableData"
      ref="singleTable"
      highlight-current-row
      @current-change="handleCurrentChange"
    >
<#list fieldList as var>
      <#if var[4] == "否">
      <el-table-column label="${var[2]}">
        <template slot-scope="scope">
          <span>{{scope.row.${var[0]}}}</span>
        </template>
      </el-table-column>
      </#if>
</#list>
    </el-table>
    <div style="text-align:right">
      <el-pagination
        @size-change="handlePageSizeChange"
        @current-change="handlePageCurrentChange"
        :current-page="pageInfo.pageNum"
        :page-sizes="[10,15,30,50]"
        :page-size="pageInfo.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="pageInfo.total"
      >
      </el-pagination>
    </div>
    <el-dialog
      :title="dialogContent.title"
      :visible.sync="dialogVisible"
      width="50%"
      @closed="dialogClosed"
    >
      <component
        :is="dialogContent.componentName"
        :ref="dialogContent.name"
        v-bind:parameter="dialogContent.parameter"
        @closeDialog="closeDialog"
        @refreshTable="refreshTable"
      ></component>
    </el-dialog>
  </div>
</template>
<script>
import ${objectName}Form from "@/components/${packageName}/${objectNameLower}/${objectName}Form"
export default {
  components: {
    ${objectName}Form
  },
  props: {
    parameter: {},
    value: {}
  },
  data() {
    return {
      tableData: [],
      pageInfo: {
        pageNum: 1,
        pageSize: 10,
        total: 0,
      },
      currentRow: null,
      searchInput: "",
      currentPage: 1,
      dialogVisible: false,
      dialogContent: {
        formTag: "",
        title: "",
        parameter: {},
        componentName: ""
      },
      formToken:{
        token:''
      },
    }
  },
  methods: {
    refreshTable() {
      this.mixPost('/${objectNameLower}/listPage', {
        pageNum: this.pageInfo.pageNum,
        pageSize: this.pageInfo.pageSize,
      }).then(res => {
        this.pageInfo = res.pageInfo
        this.tableData = res.pageInfo.list
        this.replaceAttribute()
        this.mixSetFormToken(this.formToken)
      }).catch(err => { })
    },
    addRow() {
      this.openDialog("${objectNameLower}-form", { formTag: "add", currentRow: this.currentRow }, "添加")
    },
    editRow() {
      if (this.currentRow != null) {
        this.openDialog("${objectNameLower}-form", { formTag: "edit", currentRow: this.currentRow }, "编辑")
      } else {
        this.$message({
          message: '请选择数据',
          type: 'warning'
        });
      }
    },
    deleteRow() {
      if (this.currentRow != null) {
        this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.mixPost('/${objectNameLower}/delete', {
              <#list fieldList as var>
                <#if var[4] == "是">
            ${var[0]}: this.currentRow.${var[0]},
                </#if>
              </#list>
            formToken: this.formToken.token,
          }).then(res => {
            this.$message({
              type: res.state,
              message: res.message
            });
            this.refreshTable()
          }).catch(err => { })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      } else {
        this.$message({
          message: '请选择数据',
          type: 'warning'
        });
      }
    },
    detailRow() {
      if (this.currentRow != null) {
        this.openDialog("${objectNameLower}-form", { formTag: "detail", currentRow: this.currentRow }, "查看")
      } else {
        this.$message({
          message: '请选择数据',
          type: 'warning'
        });
      }
    },
    openDialog(componentName, parameter, title) {
      this.dialogContent.componentName = componentName
      this.dialogContent.parameter = parameter
      this.dialogContent.title = title
      this.dialogVisible = true
    },
    closeDialog() {
      this.dialogVisible = false
    },
    dialogClosed() {
      this.dialogContent.componentName = ""
      this.dialogContent.parameter = {}
      this.dialogContent.title = ""
    },
    setCurrent(row) {
      this.$refs.singleTable.setCurrentRow(row);
    },
    handleCurrentChange(val) {
      this.currentRow = val
    },
    handlePageSizeChange(val) {
      this.pageInfo.pageSize = val
      this.refreshTable()
    },
    handlePageCurrentChange(val) {
      this.pageInfo.pageNum = val
      this.refreshTable()
    },
    replaceAttribute() {
      this.tableData.map(element => {
          <#list fieldList as var>
            <#if var[1] == "DateTime">
        element.${var[0]} = this.mixTimeStamp2String(element.${var[0]}, "YYYY-MM-DD HH:mm:ss")
            </#if>
            <#if var[1] == "Boolean">
        if (element.${var[0]} == true) {
          element.${var[0]} = '是'
        } else {
          element.${var[0]} = '否'
        }
            </#if>
          </#list>
      });
    }
  },
  mounted() {
    this.refreshTable()
  },
}
</script>

