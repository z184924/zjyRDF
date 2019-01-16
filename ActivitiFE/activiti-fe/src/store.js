import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);
export default new Vuex.Store({
  state: {
    basePath:"http://192.168.0.201:8886/",
    currentUser:{
      
    },
    loading: false,
  },
  mutations: {
    login(state, info) {
      state.currentUser = info;
      sessionStorage.setItem("sessionUser", JSON.stringify(info));
    },
    logout(state) {
      let info = {
        account: "",
        userName: "",
        token: "",
      };
      state.currentUser = info;
      sessionStorage.setItem("sessionUser", JSON.stringify(info));
    },
    setLoading(state, loadingState) {
      state.loading = loadingState;
    },
  }
});
