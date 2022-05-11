// import Vue from 'vue';
// import Vuex from 'vuex';
// Vue.use(Vuex);
 
// const store = new Vuex.Store({
 
//   state: {
//     // 存储token
//     Authorization: localStorage.getItem('Authorization') ? localStorage.getItem('Authorization') : ''
//   },
 
//   mutations: {
//     // 修改token，并将token存入localStorage
//     changeLogin (state, user) {
//       state.Authorization = user.Authorization;
//       localStorage.setItem('Authorization', user.Authorization);
//     }
//   },
  
// });

// const originalPush = VueRouter.prototype.push
// VueRouter.prototype.push = function push(location, onResolve, onReject) {
//     if (onResolve || onReject) return originalPush.call(this, location, onResolve, onReject)
//     return originalPush.call(this, location).catch(err => err)
// }


 
// export default store;

import Vue from 'vue';
import Vuex from 'vuex';
 
Vue.use(Vuex);
 
export default new Vuex.Store({
  state: {
    token: localStorage.getItem('token') ? localStorage.getItem('token') : ''
  },
  mutations: {
    setToken (state,token) {
      state.token =token;
      localStorage.setItem("token",token.token);     //存储token
    },
    delToken (state) {
      state.token = '';
      localStorage.removeItem("token");    //删除token
    }
  }
})