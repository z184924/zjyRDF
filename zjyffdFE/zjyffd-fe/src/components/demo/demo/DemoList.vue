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
            v-if="mixButtonAuthenticate(parameter.buttonList,'demo-add')"
            type="success"
            icon="el-icon-plus"
            circle
            @click="addRow()"
          ></el-button>
          <el-button
            v-if="mixButtonAuthenticate(parameter.buttonList,'demo-edit')"
            type="warning"
            icon="el-icon-edit"
            circle
            @click="editRow()"
          ></el-button>
          <el-button
            v-if="mixButtonAuthenticate(parameter.buttonList,'demo-delete')"
            type="danger"
            icon="el-icon-delete"
            circle
            @click="deleteRow()"
          ></el-button>
          <el-button
            v-if="mixButtonAuthenticate(parameter.buttonList,'demo-detail')"
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
      <el-table-column label="文本1">
        <template slot-scope="scope">
          <span>{{scope.row.demoText1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="文本2">
        <template slot-scope="scope">
          <span>{{scope.row.demoText2}}</span>
        </template>
      </el-table-column>
      <el-table-column label="数字1">
        <template slot-scope="scope">
          <span>{{scope.row.demoNumber1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="数字2">
        <template slot-scope="scope">
          <span>{{scope.row.demoNumber2}}</span>
        </template>
      </el-table-column>
      <el-table-column label="布尔1">
        <template slot-scope="scope">
          <span>{{scope.row.demoBoolean1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="布尔2">
        <template slot-scope="scope">
          <span>{{scope.row.demoBoolean2}}</span>
        </template>
      </el-table-column>
      <el-table-column label="时间1">
        <template slot-scope="scope">
          <span>{{scope.row.demoTime1}}</span>
        </template>
      </el-table-column>
      <el-table-column label="时间2">
        <template slot-scope="scope">
          <span>{{scope.row.demoTime2}}</span>
        </template>
      </el-table-column>
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
import DemoForm from "@/components/demo/demo/DemoForm"
export default {
  components: {
    DemoForm
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
    }
  },
  methods: {
    refreshTable() {
      this.mixPost('/demo/listPage', {
        pageNum: this.pageInfo.pageNum,
        pageSize: this.pageInfo.pageSize,
      }).then(res => {
        this.pageInfo = res.pageInfo
        this.tableData = res.pageInfo.list;
        this.replaceAttribute();
      })
    },
    addRow() {
      this.openDialog("demo-form", { formTag: "add", currentRow: this.currentRow }, "添加")
    },
    editRow() {
      if (this.currentRow != null) {
        this.openDialog("demo-form", { formTag: "edit", currentRow: this.currentRow }, "编辑")
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
          this.mixPost('/demo/delete', {
            demoId: this.currentRow.demoId
          }).then(res => {
            this.$message({
              type: res.state,
              message: res.message
            });
            this.refreshTable()
          })
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
        this.openDialog("demo-form", { formTag: "detail", currentRow: this.currentRow }, "查看")
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
        if (element.demoBoolean1 == true) {
          element.demoBoolean1 = '是'
        } else {
          element.demoBoolean1 = '否'
        }
        if (element.demoBoolean2 == true) {
          element.demoBoolean2 = '是'
        } else {
          element.demoBoolean2 = '否'
        }
        element.demoTime1 = this.mixTimeStamp2String(element.demoTime1, "YYYY-MM-DD HH:mm:ss")
        element.demoTime2 = this.mixTimeStamp2String(element.demoTime2, "YYYY-MM-DD HH:mm:ss")
      });
    }
  },
  mounted() {
    this.refreshTable()
  },
}
</script>

