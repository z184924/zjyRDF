<template>
  <div>
    <el-container>
      <el-header style="background-color:#67C23A;height:60px;">
        <el-row
          type="flex"
          class="row-bg"
          justify="space-between"
          style="margin-top:15px;"
        >
          <el-col :span="6">
            <div style="font-size:20px;color:#FFF;">Vue</div>
          </el-col>
          <el-col :span="6">
            <div></div>
          </el-col>
          <el-col :span="6">
            <div style="text-align:right;color:#FFF;">
              <el-dropdown @command="handleCommand">
                <span style="cursor: pointer;color: #FFF;">
                  {{mixCurrentUser.userName}}<i class="el-icon-arrow-down el-icon--right"></i>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item command="editPassword">修改密码</el-dropdown-item>
                  <el-dropdown-item command="logout">注销</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </div>
          </el-col>
        </el-row>
      </el-header>
      <el-main
        style="padding:0px"
        v-bind:style="{height:mainHeight+'px'}"
      >
        <el-container style="padding:0px;height:100%;">
          <el-aside width="auto">
            <el-button
              :icon="menuStateButtonIcon"
              @click="changeMenuState"
            ></el-button>
            <el-menu
              :default-active="defaultActiveMenu"
              class="el-menu-vertical-demo"
              :collapse="isCollapse"
            >
              <el-submenu index="process">
                <template slot="title">
                  <i class="el-icon-tickets"></i>
                  <span slot="title">流程管理</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item
                    index="start-process"
                    @click="addTab('发起流程','start-process','start-process')"
                  >发起流程</el-menu-item>
                  <el-menu-item
                    index="my-task"
                    @click="addTab('我的任务','my-task','my-task')"
                  >我的任务</el-menu-item>
                  <el-menu-item
                    index="create-process"
                    @click="addTab('创建流程','create-process','create-process')"
                  >创建流程</el-menu-item>
                </el-menu-item-group>
              </el-submenu>
              <el-submenu
                v-for="(menu,index) in menuData"
                :index="menu.rightsId.toString()"
                :key="index"
              >
                <template slot="title">
                  <i :class="menu.icon"></i>
                  <span slot="title">{{menu.rightsName}}</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item
                    v-for="(child,childIndex) in menu.children"
                    :key="childIndex"
                    :index="child.rightsId.toString()"
                    @click="addTab(child.rightsName,child.rightsId,child.rightsCode,{buttonList:child.children})"
                  >{{child.rightsName}}</el-menu-item>
                </el-menu-item-group>
              </el-submenu>
            </el-menu>
          </el-aside>
          <el-main>
            <el-tabs
              v-model="editableTabsValue"
              type="card"
              closable
              @tab-remove="removeTab"
            >
              <el-tab-pane
                v-for="(item) in editableTabs"
                :key="item.name"
                :label="item.title"
                :name="item.name"
              >
                <component
                  :is="item.content"
                  :ref="item.name"
                  v-bind:parameter="item.parameter"
                  @openSubTab="openSubTab"
                  @closeSelfTab="closeSelfTab"
                  @refreshTab="refreshTab"
                ></component>
              </el-tab-pane>
            </el-tabs>
          </el-main>
        </el-container>
      </el-main>
      <el-footer style="background-color:#909399;height:60px;">
        <div style="margin-top:15px;text-align:center;font-size:20px;color:#606266">
          Powered by zjy
        </div>
      </el-footer>
    </el-container>
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
      ></component>
    </el-dialog>
  </div>
</template>
<script>
import $ from "jquery"
import EditPassword from "@/components/system/EditPassword"
import StartProcess from "@/components/workflow/StartProcess"
import MyTask from "@/components/workflow/MyTask"
import DetialTask from "@/components/workflow/DetialTask"
import UserList from "@/components/system/user/UserList"
import RoleList from "@/components/system/role/RoleList"
import RightsList from "@/components/system/rights/RightsList"
import DemoList from "@/components/demo/demo/DemoList"
import CreateProcess from "@/components/workflow/CreateProcess"
export default {
  components: {
    EditPassword,
    StartProcess,
    MyTask,
    DetialTask,
    UserList,
    RoleList,
    RightsList,
    DemoList,
    CreateProcess,
  },
  computed: {
    mixCurrentUser() {
      return JSON.parse(sessionStorage.getItem("sessionUser"))
    }
  },
  data() {
    return {
      dialogVisible: false,
      dialogContent: {
        title: "",
        parameter: {},
        componentName: ""
      },
      isCollapse: false,
      menuStateButtonIcon: 'el-icon-d-arrow-left',
      editableTabsValue: '',
      editableTabs: [],
      tabInitData: {},
      menuData: {},
      defaultActiveMenu: '',
      screenWidth: document.body.clientWidth,
      mainHeight: $(window).height() - 60 - 60 - 16
    }
  },
  methods: {
    changeMenuState() {
      this.isCollapse = (!this.isCollapse)
      if (this.menuStateButtonIcon == 'el-icon-d-arrow-left') {
        this.menuStateButtonIcon = 'el-icon-d-arrow-right'
      } else {
        this.menuStateButtonIcon = 'el-icon-d-arrow-left'
      }
    },
    addTab(title, targetName, content, parameter) {
      targetName = targetName.toString()
      let sameFlag = false
      this.editableTabs.forEach(element => {
        if (element.name == targetName) {
          sameFlag = true
        }
      });
      if (sameFlag) {
        this.editableTabsValue = targetName;
        return
      }
      this.editableTabs.push({
        title: title,
        name: targetName,
        content: content,
        parameter: parameter
      });
      this.editableTabsValue = targetName;
    },
    removeTab(targetName) {
      let tabs = this.editableTabs;
      let activeName = this.editableTabsValue;
      if (activeName === targetName) {
        tabs.forEach((tab, index) => {
          if (tab.name === targetName) {
            let nextTab = tabs[index + 1] || tabs[index - 1];
            if (nextTab) {
              activeName = nextTab.name;
            }
          }
        });
      }
      this.editableTabsValue = activeName;
      this.editableTabs = tabs.filter(tab => tab.name !== targetName);
    },
    openSubTab(tab) {
      this.addTab(tab.title, tab.name, tab.content, tab.parameter);
    },
    closeSelfTab(tab) {
      this.removeTab(tab.name);
    },
    refreshTab(tab) {
      this.$refs[tab.name][0].refresh();
    },
    openDefaultTab(menuList, defaultTabId) {
      this.defaultActiveMenu = defaultTabId.toString()
      menuList.forEach(element => {
        if (element.rightsId == defaultTabId) {
          this.addTab(element.rightsName, element.rightsId, element.rightsCode, { buttonList: element.children })
          return
        }
        if (element.children.length > 0) {
          this.openDefaultTab(element.children, defaultTabId)
        }
      })
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
    handleCommand(command) {
      switch (command) {
        case "editPassword":
          this.openDialog("edit-password", {}, "修改密码")
          break;
        case "logout":
          this.mixLogout();
          break;
        default:
          console.error("command:" + command + "未定义")
      }
    },
  },
  mounted() {
    this.mixPost('/role/listUserRights', {}).then(res => {
      this.menuData = this.mixCreateTreeData('rightsId', res.data, 0)
      this.openDefaultTab(this.menuData, 18)
    }).catch(err => { })
    const that = this
    window.onresize = () => {
      return (() => {
        window.screenWidth = document.body.clientWidth
        this.mainHeight = $(window).height() - 60 - 60 - 16
        that.screenWidth = window.screenWidth
      })()
    }
  },
}
</script>
<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>
