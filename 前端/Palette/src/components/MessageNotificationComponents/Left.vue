<template>
	<div style="width: 340px;margin-left: 5%;">
		<el-menu router default-active="$route.path" class="el-menu-vertical-demo" @open="handleOpen"
			@close="handleClose">

			<div
				style="font-size: 20px;margin-top: 15px;font-weight: bold;margin-bottom: 15px;display: flex;align-items: center;justify-content: center;">
				消息通知
			</div>
		

			<el-submenu index="/messagenotification/systemannouncement">

				<template slot="title">
					<img src="../../assets/Images/icon/systemannouncement.png" style="width: 15px;height:15px">
					<span slot="title" style="margin-left: 5px;">系统公告</span>
				</template>

				<el-submenu index="/messagenotification/systemannouncement/versiondetails">
					<template slot="title">版本更新细则</template>
					<el-menu-item index="/messagenotification/version1">1.0版本</el-menu-item>
					<el-menu-item index="/messagenotification/version2">1.1版本</el-menu-item>
				</el-submenu>

				<el-menu-item index="/messageNotification/qingfuactivity">"青扶计划"活动说明</el-menu-item>
				<el-menu-item index="/messageNotification/profitbill">个人收益账单</el-menu-item>
				<el-menu-item index="/messageNotification/happyspringfestival">Palette祝您春节愉快！</el-menu-item>

			</el-submenu>

			<el-submenu index="">
				<template slot="title">
					<img src="../../assets/Images/icon/interestedme.png" style="width: 15px;height:15px">
					<span slot="title" style="margin-left: 5px;">关注我的</span>
				</template>
				<el-menu-item index="/messageNotification/interestedmeuser1">
					某某
				</el-menu-item>
				<el-menu-item index="/messagenotification/interestedmeuser2">某某某</el-menu-item>
			</el-submenu>

			<el-submenu index="/messagenotification/myinterest">
				<template slot="title">
					<img src="../../assets/Images/icon/myinterest.png" style="width: 15px;height:15px">
					<span slot="title" style="margin-left: 5px;">我的关注列表</span>
				</template>
				<el-submenu index="/messagenotification/myinterest/music">
					<template slot="title">音乐区</template>
					<el-menu-item index="/messagenotification/musicuser1">某某</el-menu-item>
					<el-menu-item index="/messagenotification/musicuser2">某某某</el-menu-item>
				</el-submenu>
				<el-submenu index="/messagenotification/myinterest/painting">
					<template slot="title">绘画区</template>
					<el-menu-item index="/messagenotification/paintinguser1">某某某某</el-menu-item>
					<el-menu-item index="/messagenotification/paintinguser2">某某某某某</el-menu-item>
				</el-submenu>
			</el-submenu>

		</el-menu>
	</div>
</template>

<style scoped>
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
