<template>
  <div>
    <el-form
      ref="form"
      :model="form"
      label-width="100px"
    >
      <el-form-item label="帐号">
        <el-input v-model="form.account"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.password"></el-input>
      </el-form-item>
      <el-form-item label="用户名">
        <el-input v-model="form.userName"></el-input>
      </el-form-item>
      <el-form-item label="是否锁定">
        <el-switch
          v-model="form.locked"
          active-color="#13ce66"
          inactive-color="#ff4949"
          active-text="是"
          inactive-text="否"
        >
        </el-switch>
      </el-form-item>
      <el-form-item label="是否禁用">
        <el-switch
          v-model="form.disable"
          active-color="#13ce66"
          inactive-color="#ff4949"
          active-text="是"
          inactive-text="否"
        >
        </el-switch>
      </el-form-item>
      <el-form-item label="特殊角色">
        <el-input v-model="form.specialRole"></el-input>
      </el-form-item>
    </el-form>
    <div style="text-align:right">
      <el-button
        size="medium"
        @click="closeDialog"
      >取消</el-button>
      <el-button
        type="primary"
        size="medium"
        @click="submit"
      >确定</el-button>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    parameter: {},
  },
  data() {
    return {
      form: {
        account: "",
        password: "",
        userName: "",
        locked: false,
        disable: false,
        specialRole: ""
      }
    }
  },
  methods: {
    submit() {
      this.mixPost('api/user/edit', this.form).then(res => {
        this.$emit("refreshTable")
        this.$emit("closeDialog")
      })
    },
    closeDialog() {
      this.$emit("closeDialog")
    }
  },
  mounted() {
    this.mixPost('api/user/findById', {
      userId: this.parameter.userId
    }).then(res => {
      this.form = res.data
    })
  }
}
</script>

