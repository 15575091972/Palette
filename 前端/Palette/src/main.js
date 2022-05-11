import Vue from 'vue'
import App from './App.vue'
import router from './router'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(Element)
Vue.config.productionTip = false

import axios from 'axios'
Vue.prototype.$axios = axios

import global from './components/Global.vue'
Vue.prototype.Global = global



import apiUrl from './components/head.vue'
Vue.prototype.apiUrl = apiUrl.apiUrl;

// axios.default.withCredentials=true

new Vue({
	router,
	store,
	render: h => h(App)
}).$mount('#app')


// // 添加请求拦截器，在请求头中加token
// axios.interceptors.request.use(
//   config => {
//     if (localStorage.getItem('Authorization')) {
//       config.headers.Authorization = localStorage.getItem('Authorization');
//     }

//     return config;
//   },
//   error => {
//     return Promise.reject(error);
//   });



// import axios from "axios";
import store from './store/index';
// Vue.prototype.$ajax = axios

// new Vue({
//   el: '#app',
//   router,
//   store,     //store要加进来
//   components: { App },
//   template: '<App/>'
// })

//请求拦截器
axios.interceptors.request.use(config => {
	//判断是否存在token，如果存在将每个页面的header都添加token
	if (store.state.token) {

		config.headers.common['XX-Token'] = store.state.token //此处的XX-Token要根据登录接口中请求头的名字来写
	}

	return config;
}, error => {
	// 请求错误
	return Promise.reject(error);
});

//respone拦截器
axios.interceptors.response.use(
	response => {
		return response;
	},

	error => { //默认除了2XX之外都为错误
		if (error.response) {
			switch (error.response.status) {
				case 401:
					store.commit('delToken');
					router.replace({ //跳转到登录页面
						path: '/',
						query: {
							redirect: router.currentRoute.fullPath
						} // 将跳转的路由path作为参数，登录成功后跳转到该路由
					});
			}
		}
		return Promise.reject(error.response);
	}
);
