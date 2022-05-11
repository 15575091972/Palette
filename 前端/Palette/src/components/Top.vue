<template>
	<div>
		<div class="navbar">
			<el-menu router default-active="$route.path" class="el-menu-demo" mode="horizontal" @select="handleSelect">
				<el-row style="flex-direction: row;display: flex;">
					<div style="margin-top: 0px;">
						<el-col style="flex-direction: row;display: flex;">
							<!-- <el-image  :src="logourl" style="width: 60px;height: 60px;margin-top: 0px;"></el-image> -->
							<el-image :src="logourl_p"
								style="width: 30px;height: 30px;margin-top: 8px;margin-bottom: 20px;"></el-image>
							<span
								style="text-align: center;margin-top: 20px;font-size: 20px;font-weight: bold;font-family:华文新魏;">alette</span>
						</el-col>
					</div>

					<div>
						<el-col style="width: 420px;margin-left: 40px;margin-top: 6px;">
							<el-input placeholder="请输入内容" v-model="inputSearch"><i
									style="margin-top: 12px;margin-right: 10px;" class="el-icon-search" slot="suffix"
									@click="gotosearch"></i>
							</el-input>
						</el-col>
					</div>

					<el-col style="margin-left: 20px;">
						<el-menu-item index="/" background-color="#1c213f" style="font-family: 微软雅黑;font-size: 15px;">首页
						</el-menu-item>
					</el-col>

					<el-col>
						<el-tooltip content="艺术展厅" placement="bottom" effect="dark">
							<el-menu-item index="/travelWorld"><i class="el-icon-video-camera"
									style="font-size: 20px;color: #000000;"></i></el-menu-item>
						</el-tooltip>
					</el-col>

					<el-col>
						<el-dropdown trigger="hover">
							<el-menu-item>
								<i class="el-icon-view" style="font-size: 20px;color: #000000;"></i>
							</el-menu-item>
							<el-dropdown-menu slot="dropdown" style="width: 150px;">
								<el-menu-item index="/painting">绘画</el-menu-item>
								<el-menu-item index="/calligraphy">书法</el-menu-item>
								<el-menu-item index="/music">音乐</el-menu-item>
								<el-menu-item index="/photography">摄影</el-menu-item>
								<el-menu-item index="/game">游戏</el-menu-item>
								<el-menu-item index="/file_television">影视</el-menu-item>
								<el-menu-item index="/model">模型</el-menu-item>
							</el-dropdown-menu>
						</el-dropdown>
					</el-col>

					<el-col>
						<el-dropdown trigger="hover">
							<el-menu-item>
								<i class="el-icon-goods" style="font-size: 20px;color: #000000;"></i>
							</el-menu-item>
							<el-dropdown-menu slot="dropdown" style="width: 150px;">
								<el-menu-item index="/supermarket">拍卖市场</el-menu-item>
								<el-menu-item index="/indbox">盲盒市场</el-menu-item>
							</el-dropdown-menu>
						</el-dropdown>
					</el-col>




					 <el-col>
						<el-tooltip content="即刻创作" placement="bottom" effect="dark">
							<el-menu-item @click="createDrawerLoad()">
								<i class="el-icon-edit-outline" style="font-size: 20px;color: #000000;"></i>
							</el-menu-item>
						</el-tooltip>
					</el-col>



					<el-col>
						<el-tooltip content="个人中心" placement="bottom" effect="dark">
							<div v-if="token==null">
								<!-- <el-menu-item index="/login"><i class="el-icon-user-solid"></i></el-menu-item> -->
								<el-menu-item  @click="loginDrawerLoad()"><i class="el-icon-user-solid"></i></el-menu-item>
							</div>
							<div v-else>
								<el-menu-item index="/personalCenter/dynamicIssue"><i class="el-icon-user-solid"></i></el-menu-item>
							</div>
						</el-tooltip>
					</el-col>
					<div v-if="showCreate">
						<Create :key="timerCreate"></Create>
					</div>
					
					<div v-if="showLogin">
						<Login :key="timerLogin"></Login>
					</div>
					

					
					<el-col>
						<el-tooltip content="设置" placement="bottom" effect="dark">
							<el-menu-item index="/set/preference"><i class="el-icon-setting"
									style="font-size: 20px;color: #000000;"></i></el-menu-item>
						</el-tooltip>
					</el-col>
					<el-col>
						<el-tooltip content="消息通知" placement="bottom" effect="dark">
							<el-menu-item index="/messagenotification/version1"><i class="el-icon-message"
									style="font-size: 20px;color: #000000;"></i></el-menu-item>
						</el-tooltip>
					</el-col>
				</el-row>
			</el-menu>
				

		</div>


	</div>
	</div>


</template>

<script>
	import Login from "../views/Login/login.vue"
	import Create from "../views/InstantlyCreate/InstantlyCreate.vue"
	export default {
		components:{
			Login,
			Create
		},
		data() {

			return {
				showCreate:false,
				showLogin:false,
				timerCreate:'',
				timerLogin:'',
				drawerVisible:false,//控制登录界面抽屉的显示
				//搜索框
				inputSearch: '',

				activeName: 'second',
				logourl: require("../assets/Images/logo/logo_blod.png"),
				logourl_p: require("../assets/Images/logo/p.png"),
				activeIndex: '1',
				activeIndex2: '1',
				flag: true,
			};
		},
		methods: {
			createDrawerLoad () {
				this.showCreate = true;
			    this.timerCreate = new Date().getTime()
			},
			loginDrawerLoad () {
				this.showLogin = true;
			    this.timerLogin = new Date().getTime()
			},
			
			// showLoginDrawer(){//点击按钮，显示按钮
			// 	this.drawerVisible = true;	
			// },
			instantCreateDrawerJudge(){
							 // window.location.reload();
							console.log(this.Global.timer);
							this.Global.timer = new Date().getTime();
							this.Global.instantCreateDrawer = true;
							console.log(this.Global.instantCreateDrawer);
						},
			handleSelect(key, keyPath) {
				console.log(key, keyPath);
			},
			// 跳转到搜索页面
			gotosearch() {
				this.$router.push({
					path: '/search',
					query: {
						input: this.inputSearch
					}
				});
				//console.log(this.inputSearch);
			},


		},
		created() {
			this.token = localStorage.getItem('token');
			console.log("token：" + this.token);
		}
	}
</script>

<style>
	.navbar {
		text-align: center;
		margin-left: 5%;
		margin-right: 5%;

	}

	.navbar el-menu {
		display: inline-block;
	}

	.el-menu-item.is-active {
		background-color: rgb(255, 255, 255) !important;
	}
</style>
