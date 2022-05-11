<template>
	<div style="margin-left: 5%;">
		<el-row class="tac">
			<el-col>
				<el-menu router :default-active="$route.path" class="el-menu-vertical-demo" @open="handleOpen"
					@close="handleClose">
					<div
						style="margin-left: 35px;font-size: 20px;margin-top: 15px;font-weight: bold;padding-bottom: 5px;">
						<span style="margin-right: 18px;font-family: 微软雅黑;font-weight: 500;">设</span>
						<span style="font-family: 微软雅黑;font-weight: 500;">置</span>
					</div>
			 	<!-- <hr style="opacity: 10%;"></hr> -->
					<el-menu-item index="/set/preference">
						<!-- <el-image style="width: 50px; height: 50px" :src="../assets/logo.png" :fit="fit"></el-image> -->
						<img src="../../assets/Images/icon/preference.png" style="width: 15px;height:15px">
						<span slot="title" style="margin-left: 5px;">偏好设置</span>
					</el-menu-item>
					<el-menu-item index="/set/security">
						<img src="../../assets/Images/icon/security.png" style="width: 15px;height:15px">
						<span slot="title" style="margin-left: 5px;">账户安全</span>
					</el-menu-item>
					<el-menu-item index="/set/authentication">
						<img src="../../assets/Images/icon/authentication.png" style="width: 15px;height:15px">
						<span slot="title" style="margin-left: 5px;">认证方式</span>
					</el-menu-item>
					<el-menu-item index="/set/help">
						<img src="../../assets/Images/icon/help.png" style="width: 15px;height:15px">
						<span slot="title" style="margin-left: 5px;">客服帮助</span>
					</el-menu-item>
					<el-menu-item index="/set/about">
						<img src="../../assets/Images/icon/about.png" style="width: 15px;height:15px">
						<span slot="title" style="margin-left: 5px;">关于Palette</span>
					</el-menu-item>
					<el-menu-item index="/set/logout">
						<img src="../../assets/Images/icon/about.png" style="width: 15px;height:15px">
						<span slot="title" style="margin-left: 5px;">退出登录</span>
					</el-menu-item>
				</el-menu>
			</el-col>

		</el-row>

	</div>
</template>

<style scoped>
	.tac {
		width: 164px;
	}

	.el-menu {
		height: 89vh;
	}
</style>


<script>
	export default {
		methods: {
			handleOpen(key, keyPath) {
				console.log(key, keyPath);
			},
		 handleClose(key, keyPath) {
				console.log(key, keyPath);
			}
		}
	}
</script>
