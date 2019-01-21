<template>
  <div>
    <el-container>
      <el-header style="background-color:#67C23A;height:60px;">
        <el-row type="flex" class="row-bg" justify="space-between" style="margin-top:15px;">
          <el-col :span="6"><div style="font-size:20px;color:#FFF;">Activiti</div></el-col>
          <el-col :span="6"><div></div></el-col>
          <el-col :span="6"><div style="text-align:right;color:#FFF;"><a @click="logout">logout</a></div></el-col>
        </el-row>
      </el-header>
      <el-main style="padding:0px" v-bind:style="{height:mainHeight+'px'}">
        <el-container style="padding:0px;height:100%;">
          <el-aside width="auto">
            <el-button :icon="menuStateButtonIcon" @click="changeMenuState"></el-button>
            <el-menu default-active="1-2" class="el-menu-vertical-demo" :collapse="isCollapse">
              <el-submenu index="1">
                <template slot="title">
                  <i class="el-icon-tickets"></i>
                  <span slot="title">流程管理</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="1-1" @click="addTab('新建流程','1-1','start-process')">新建流程</el-menu-item>
                  <el-menu-item index="1-2" @click="addTab('我的任务','1-2','my-task')">我的任务</el-menu-item>
                </el-menu-item-group>
              </el-submenu>
              <el-submenu index="2">
                <template slot="title">
                  <i class="el-icon-setting"></i>
                  <span slot="title">系统管理</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="2-1" @click="addTab('人员管理','2-1','人员管理')">人员管理</el-menu-item>
                  <el-menu-item index="2-2" @click="addTab('分组管理','2-2','分组管理')">分组管理</el-menu-item>
                </el-menu-item-group>
              </el-submenu>
            </el-menu>
          </el-aside>
          <el-main>
            <el-tabs v-model="editableTabsValue" type="card" closable @tab-remove="removeTab">
              <el-tab-pane v-for="(item) in editableTabs" :key="item.name" :label="item.title" :name="item.name">
                <component :is="item.content"></component>
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
import StartProcess from "@/components/StartProcess"
import MyTask from "@/components/MyTask"
export default {
  components:{
    StartProcess,
    MyTask
  },
  data() {
    return {
      isCollapse: false,
      menuStateButtonIcon:'el-icon-d-arrow-left',
      editableTabsValue: '',
      editableTabs: [],
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
    addTab(title,targetName,content) {
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
        content: content
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
    }
  },
  mounted() {
    this.addTab('我的任务','1-2','my-task')
    const that = this
    window.onresize = () => {
      return (() => {
        window.screenWidth = document.body.clientWidth
        that.screenWidth = window.screenWidth
      })()
    }
  },
  watch: {
    screenWidth: function () {
      this.mainHeight = $(window).height() - 60 - 60 - 16
      return document.body.clientWidth
    }
  }
}
</script>