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
            v-if="mixButtonAuthenticate(parameter.buttonList,'user-add')"
            type="success"
            icon="el-icon-plus"
            circle
            @click="addRow()"
          ></el-button>
          <el-button
            v-if="mixButtonAuthenticate(parameter.buttonList,'user-edit')"
            type="warning"
            icon="el-icon-edit"
            circle
            @click="editRow()"
          ></el-button>
          <el-button
            v-if="mixButtonAuthenticate(parameter.buttonList,'user-delete')"
            type="danger"
            icon="el-icon-delete"
            circle
            @click="deleteRow()"
          ></el-button>
          <el-button
            v-if="mixButtonAuthenticate(parameter.buttonList,'user-detail')"
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
      <el-table-column label="账号">
        <template slot-scope="scope">
          <span>{{scope.row.account}}</span>
        </template>
      </el-table-column>
      <el-table-column label="密码">
        <template slot-scope="scope">
          <span>{{scope.row.password}}</span>
        </template>
      </el-table-column>
      <el-table-column label="账号名称">
        <template slot-scope="scope">
          <span>{{scope.row.userName}}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否锁定">
        <template slot-scope="scope">
          <span>{{scope.row.locked}}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否禁用">
        <template slot-scope="scope">
          <span>{{scope.row.disable}}</span>
        </template>
      </el-table-column>
      <el-table-column label="特殊角色">
        <template slot-scope="scope">
          <span>{{scope.row.specialRole}}</span>
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
import UserForm from "@/components/system/user/UserForm"
export default {
  components: {
    UserForm
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
      this.mixPost('api/user/listPage', {
        pageNum: this.pageInfo.pageNum,
        pageSize: this.pageInfo.pageSize,
      }).then(res => {
        this.pageInfo = res.pageInfo
        this.tableData = res.pageInfo.list;
        this.replaceAttribute();
      })
    },
    addRow() {
      this.openDialog("user-form", { formTag: "add", currentRow: this.currentRow }, "添加")
    },
    editRow() {
      if (this.currentRow != null) {
        this.openDialog("user-form", { formTag: "edit", currentRow: this.currentRow }, "编辑")
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
          this.mixPost('api/user/delete', {
            userId: this.currentRow.userId
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
        this.openDialog("user-form", { formTag: "detail", currentRow: this.currentRow }, "查看")
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
        if (element.locked == true) {
          element.locked = '是'
        } else {
          element.locked = '否'
        }
        if (element.disable == true) {
          element.disable = '是'
        } else {
          element.disable = '否'
        }
      });
    }
  },
  mounted() {
    this.refreshTable()
  },
}
</script>

