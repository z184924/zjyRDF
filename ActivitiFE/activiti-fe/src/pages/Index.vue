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
            <el-menu default-active="1-4-1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" :collapse="isCollapse">
              <el-submenu index="1">
                <template slot="title">
                  <i class="el-icon-location"></i>
                  <span slot="title">导航一</span>
                </template>
                <el-menu-item-group>
                  <span slot="title">分组一</span>
                  <el-menu-item index="1-1">选项1</el-menu-item>
                  <el-menu-item index="1-2">选项2</el-menu-item>
                </el-menu-item-group>
                <el-menu-item-group title="分组2">
                  <el-menu-item index="1-3">选项3</el-menu-item>
                </el-menu-item-group>
                <el-submenu index="1-4">
                  <span slot="title">选项4</span>
                  <el-menu-item index="1-4-1">选项1</el-menu-item>
                </el-submenu>
              </el-submenu>
              <el-menu-item index="2">
                <i class="el-icon-menu"></i>
                <span slot="title">导航二</span>
              </el-menu-item>
              <el-menu-item index="3" disabled>
                <i class="el-icon-document"></i>
                <span slot="title">导航三</span>
              </el-menu-item>
              <el-menu-item index="4">
                <i class="el-icon-setting"></i>
                <span slot="title">导航四</span>
              </el-menu-item>
            </el-menu>
          </el-aside>
          <el-main>
            <el-tabs v-model="editableTabsValue" type="card" closable @tab-remove="removeTab">
              <el-tab-pane v-for="(item) in editableTabs" :key="item.name" :label="item.title" :name="item.name">
                {{item.content}}
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
export default {
  data() {
    return {
      isCollapse: false,
      editableTabsValue: '2',
      editableTabs: [
        {
          title: 'Tab 1',
          name: '1',
          content: 'Tab 1 content'
        },
        {
          title: 'Tab 2',
          name: '2',
          content: 'Tab 2 content'
        }
      ],
      tabIndex: 2,
      screenWidth: document.body.clientWidth,
      mainHeight: $(window).height() - 60 - 60 - 16
    }
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    addTab(targetName) {
      let newTabName = ++this.tabIndex + '';
      this.editableTabs.push({
        title: 'New Tab',
        name: newTabName,
        content: 'New Tab content'
      });
      this.editableTabsValue = newTabName;
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