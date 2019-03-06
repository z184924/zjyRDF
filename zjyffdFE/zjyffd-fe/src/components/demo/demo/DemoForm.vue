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
          label="文本1"
          prop="demoText1"
        >
          <el-input
            v-model="form.demoText1"
            :readonly="readOnlyFlag"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="文本2"
          prop="demoText2"
        >
          <el-input
            v-model="form.demoText2"
            :readonly="readOnlyFlag"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="数字1"
          prop="demoNumber1"
        >
          <el-input-number
            v-model="form.demoNumber1"
            :disabled="readOnlyFlag"
          ></el-input-number>
        </el-form-item>
        <el-form-item
          label="数字2"
          prop="demoNumber2"
        >
          <el-input-number
            v-model="form.demoNumber2"
            :disabled="readOnlyFlag"
          ></el-input-number>
        </el-form-item>
        <el-form-item
          label="布尔1"
          prop="demoBoolean1"
        >
          <el-switch
            v-model="form.demoBoolean1"
            active-color="#13ce66"
            inactive-color="#ff4949"
            active-text="是"
            inactive-text="否"
            :disabled="readOnlyFlag"
          >
          </el-switch>
        </el-form-item>
        <el-form-item
          label="布尔2"
          prop="demoBoolean2"
        >
          <el-switch
            v-model="form.demoBoolean2"
            active-color="#13ce66"
            inactive-color="#ff4949"
            active-text="是"
            inactive-text="否"
            :disabled="readOnlyFlag"
          >
          </el-switch>
        </el-form-item>
        <el-form-item
          label="时间1"
          prop="demoTime1"
        >
          <el-date-picker
            v-model="form.demoTime1"
            :readonly="readOnlyFlag"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择日期时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item
          label="时间2"
          prop="demoTime2"
        >
          <el-date-picker
            v-model="form.demoTime2"
            :readonly="readOnlyFlag"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择日期时间"
          >
          </el-date-picker>
        </el-form-item>
      </el-form>
    </div>
    <div style="text-align:right">
      <el-button
        size="medium"
        @click="closeDialog"
      >取消</el-button>
      <el-button
        v-if="!readOnlyFlag"
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
      fromTag: "",
      readOnlyFlag: false,
      submitUrl: 'api/demo/',
      form: {
        demoId: '',
        demoText1: '',
        demoText2: '',
        demoNumber1: 0,
        demoNumber2: 0,
        demoBoolean1: false,
        demoBoolean2: false,
        demoTime1: '',
        demoTime2: '',
      },
      rules: {
        demoId: [
          { required: true, message: '该数据为必填项', trigger: 'blur' },
        ],
        demoText1: [
          { required: true, message: '该数据为必填项', trigger: 'blur' },
        ],
        demoText2: [
          { required: true, message: '该数据为必填项', trigger: 'blur' },
        ],
        demoNumber1: [
          { required: true, message: '该数据为必填项', trigger: 'blur' },
        ],
        demoNumber2: [
          { required: true, message: '该数据为必填项', trigger: 'blur' },
        ],
        demoBoolean1: [
          { required: true, message: '该数据为必填项', trigger: 'blur' },
        ],
        demoBoolean2: [
          { required: true, message: '该数据为必填项', trigger: 'blur' },
        ],
        demoTime1: [
          { required: true, message: '该数据为必填项', trigger: 'blur' },
        ],
        demoTime2: [
          { required: true, message: '该数据为必填项', trigger: 'blur' },
        ],
      }
    }
  },
  methods: {
    submit() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          this.mixPost(this.submitUrl, this.form).then(res => {
            this.$emit("refreshTable")
            this.$emit("closeDialog")
          })
        } else {
          return false;
        }
      })
    },
    closeDialog() {
      this.$emit("closeDialog")
    },
    getFormData() {
      this.mixPost('api/demo/findById', {
        demoId: this.parameter.currentRow.demoId
      }).then(res => {
        this.form = res.data
        this.replaceAttribute();
      })
    },
    replaceAttribute() {
      this.form.demoTime1 = this.mixTimeStamp2String(this.form.demoTime1, "YYYY-MM-DD HH:mm:ss")
      this.form.demoTime2 = this.mixTimeStamp2String(this.form.demoTime2, "YYYY-MM-DD HH:mm:ss")
    }
  },
  mounted() {
    this.formTag = this.parameter.formTag;
    switch (this.formTag) {
      case 'add':
        this.readOnlyFlag = false;
        this.submitUrl = this.submitUrl + 'save';
        break;
      case 'edit':
        this.readOnlyFlag = false;
        this.submitUrl = this.submitUrl + 'edit';
        this.getFormData();
        break;
      case 'detail':
        this.readOnlyFlag = true;
        this.getFormData();
        break;
      default:
        this.$message({
          message: '错误的formTag',
          type: 'error'
        });
    }
  }
}
</script>

