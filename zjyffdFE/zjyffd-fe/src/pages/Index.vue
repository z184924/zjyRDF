<template>
  <div>
    <el-container>
      <el-header style="background-color:#67C23A;height:60px;">
        <el-row type="flex" class="row-bg" justify="space-between" style="margin-top:15px;">
          <el-col :span="6"><div style="font-size:20px;color:#FFF;">Vue</div></el-col>
          <el-col :span="6"><div></div></el-col>
          <el-col :span="6"><div style="text-align:right;color:#FFF;"><a @click="logout">logout</a></div></el-col>
        </el-row>
      </el-header>
      <el-main style="padding:0px" v-bind:style="{height:mainHeight+'px'}">
        <el-container style="padding:0px;height:100%;">
          <el-aside width="auto">
            <el-button :icon="menuStateButtonIcon" @click="changeMenuState"></el-button>
            <el-menu default-active="1-3" class="el-menu-vertical-demo" :collapse="isCollapse">
              <el-submenu index="1">
                <template slot="title">
                  <i class="el-icon-tickets"></i>
                  <span slot="title">流程管理</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="1-1" @click="addTab('新建流程','1-1','start-process')">新建流程</el-menu-item>
                  <el-menu-item index="1-2" @click="addTab('我的任务','1-2','my-task')">我的任务</el-menu-item>
                  <el-menu-item index="1-3" @click="addTab('模板样例','1-3','demo-list')">模板样例</el-menu-item>
                </el-menu-item-group>
              </el-submenu>
              <el-submenu index="2">
                <template slot="title">
                  <i class="el-icon-setting"></i>
                  <span slot="title">系统管理</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="2-1" @click="addTab('用户管理','2-1','user-list')">用户管理</el-menu-item>
                  <el-menu-item index="2-2" @click="addTab('角色管理','2-2','role-list')">角色管理</el-menu-item>
                  <el-menu-item index="2-3" @click="addTab('菜单管理','2-3','rights-list')">菜单管理</el-menu-item>
                </el-menu-item-group>
              </el-submenu>
            </el-menu>
          </el-aside>
          <el-main>
            <el-tabs v-model="editableTabsValue" type="card" closable @tab-remove="removeTab">
              <el-tab-pane v-for="(item) in editableTabs" :key="item.name" :label="item.title" :name="item.name">
                <component :is="item.content" :ref="item.name" v-bind:parameter="item.parameter" @openSubTab="openSubTab" @closeSelfTab="closeSelfTab" @refreshTab="refreshTab"></component>
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
  </div>
</template>
<script>
import $ from "jquery"
import StartProcess from "@/components/workflow/StartProcess"
import MyTask from "@/components/workflow/MyTask"
import DetialTask from "@/components/workflow/DetialTask"
import UserList from "@/components/system/user/UserList"
import RoleList from "@/components/system/role/RoleList"
import RightsList from "@/components/system/rights/RightsList"
import DemoList from "@/components/demo/demo/DemoList"
export default {
  components:{
    StartProcess,
    MyTask,
    DetialTask,
    UserList,
    RoleList,
    RightsList,
    DemoList,
  },
  data() {
    return {
      isCollapse: false,
      menuStateButtonIcon:'el-icon-d-arrow-left',
      editableTabsValue: '',
      editableTabs: [],
      tabInitData:{},
      screenWidth: document.body.clientWidth,
      mainHeight: $(window).height() - 60 - 60 - 16
    }
  },
  methods: {
    changeMenuState(){
      this.isCollapse=(!this.isCollapse)
      if(this.menuStateButtonIcon=='el-icon-d-arrow-left'){
        this.menuStateButtonIcon='el-icon-d-arrow-right'
      }else{
        this.menuStateButtonIcon='el-icon-d-arrow-left'
      }
    },
    addTab(title,targetName,content,parameter) {
      let sameFlag=false
      this.editableTabs.forEach(element => {
        if(element.name==targetName){
          sameFlag=true      
        }
      });
      if(sameFlag){
        this.editableTabsValue = targetName;
        return
      }
      this.editableTabs.push({
        title: title,
        name: targetName,
        content: content,
        parameter:parameter
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
    logout(){
      this.mixLogout();
    },
    openSubTab(tab){
      this.addTab(tab.title,tab.name,tab.content,tab.parameter);
    },
    closeSelfTab(tab){
      this.removeTab(tab.name);
    },
    refreshTab(tab){
      console.log(this.$refs);
      console.log(tab);
      this.$refs[tab.name][0].refresh();
    }
  },
  mounted() {
    this.addTab('模板样例','1-3','demo-list')
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
