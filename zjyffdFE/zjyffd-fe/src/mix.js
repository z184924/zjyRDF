import $ from "jquery"
import moment from "moment"
export default {
  computed: {
    mixBasePath() {
      return this.$store.state.basePath;
    },
    mixCurrentUser() {
      return JSON.parse(sessionStorage.getItem('sessionUser'))
    },
  },
  methods: {
    // 登录
    mixLogin(inputAccount, inputPassword) {
      var account;
      var password;
      account = inputAccount;
      password = inputPassword;
      // console.log(username);
      return new Promise((resolve, reject) => {
        this.mixPost("/oauth/token", {
          grant_type: 'password',
          username: account,
          password: password,
          scope: 'all',
        }).then(res => {
          //登录成功
          let currentUser = {
            account: res.user.account,
            userName: res.user.userName,
            access_token: res.access_token,
            refresh_token: res.refresh_token,
            moreInfo: res.user,
          }
          sessionStorage.setItem("sessionUser", JSON.stringify(currentUser));
          localStorage.setItem("account", account);
          localStorage.setItem("password", password);
          resolve(res);
        }).catch(res => {
          this.$router.replace("/");
          reject(res);
        });
      })
    },
    mixLogout() {
      this.$store.commit("logout");
      this.mixPost("/oauth/logout");
      this.$router.replace("/");
    },
    mixPost(api, data = {}, param = {}) {
      let headers = {}
      if (param.isShowLoading === undefined) {
        param.isShowLoading = false;
      }
      if (param.loadingMaxTime === undefined) {
        param.loadingMaxTime = 15000;
      }
      if (api == "/oauth/token") {
        headers.Authorization = 'Basic Y2xpZW50OnNlY3JldA=='
      }else{
        headers.Authorization = 'Bearer ' + this.mixCurrentUser.access_token
      }
      
      return new Promise((resolve, reject) => {
        let url = this.mixApi(api);
        let vue = this;
        if (param.isShowLoading) {
          this.$store.commit("setLoading", true);
          setTimeout(() => {
            this.$store.commit("setLoading", false);
            // this.mxLoading = false;
          }, param.loadingMaxTime)
        }
        $.ajax({
          url,
          type: "POST",
          data,
          dataType: "json",
          headers,
          success(res) {
            if (param.isShowLoading) {
              vue.$store.commit("setLoading", false);
            }
            // this.mxLoading = false;
            resolve(res);
          },
          error(err) {
            console.log(JSON.stringify(err))
            switch (err.status) {
              case 400:
              case 401:
                vue.$store.commit("logout");
                vue.$router.replace("/")
              case 403:
                vue.$alert(err.message, err.state, {
                  confirmButtonText: '确定'
                });
                break;
              default:
                vue.$message.error('无法连接服务器,请稍候重试');
            }
            if (param.isShowLoading) {
              vue.$store.commit("setLoading", false);
            }
            reject(err);
          }
        })
      });
    },
    mixApi(api) {
      let url = this.mixBasePath;
      url += api;
      return url;
    },
    mixTimeStamp2String(timeStamp, format) {
      return moment(timeStamp).utc().format(format)
    },
    mixButtonAuthenticate(buttonList, buttonCode) {
      for (let i = 0; i < buttonList.length; i++) {
        if (buttonList[i].rightsCode == buttonCode) {
          return true
        }
      }
      return false
    },
    mixCreateTreeData(idName, data, parentId) {
      let result = []
      data.forEach(element => {
        if (element.parentId == parentId) {
          let e = element
          if (this.mixFindChild(data, e[idName])) {
            e.children = this.mixCreateTreeData(idName, data, e[idName]);
          } else {
            e.children = []
          }
          result.push(e)
        }
      })
      return result
    },
    mixFindChild(data, parentId) {
      for (let i = 0; i < data.length; i++) {
        if (data[i].parentId == parentId) {
          return true
        }
      }
      return false
    }
  }
}