<template>
  <div>
    <div style="overflow-y:auto;max-height:500px;margin-bottom:20px">
      <el-form
        ref="form"
        :model="form"
        :rules="rules"
        label-width="100px"
      >
        <el-form-item
          label="旧密码"
          prop="oldPassword"
        >
          <el-input
            type="password"
            v-model="form.oldPassword"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="新密码"
          prop="newPassword"
        >
          <el-input
            type="password"
            v-model="form.newPassword"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="确认密码"
          prop="checkPassword"
        >
          <el-input
            type="password"
            v-model="form.checkPassword"
          ></el-input>
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
  </div>
</template>
<script>
export default {
  props: {
    parameter: {},
  },
  data() {
    var validateNewPassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入新密码'));
      } else {
        if (this.form.checkPassword !== '') {
          this.$refs.form.validateField('checkPassword');
        }
        callback();
      }
    };
    var checkNewPassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.form.newPassword) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      form: {
        oldPassword: '',
        newPassword: '',
        checkPassword: '',
      },
      rules: {
        oldPassword: [
          { required: true, message: '该数据为必填项', trigger: 'blur' },
        ],
        newPassword: [
          { validator: validateNewPassword, trigger: 'blur' },
        ],
        checkPassword: [
          { validator: checkNewPassword, trigger: 'blur' },
        ],
      },

    }
  },
  methods: {
    submit() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          this.mixPost('/user/editPassword', this.form).then(res => {
            switch (res.state) {
              case "success":
                res.message="修改成功,请重新登录!"
                this.mixLogout()
              case "error":
              default:
                this.$alert(res.message, res.state, {
                  confirmButtonText: '确定'
                });
            }
            this.$emit("closeDialog")
          }).catch(err => { })
        } else {
          return false;
        }
      })
    },
    closeDialog() {
      this.$emit("closeDialog")
    },
  }
}
</script>

