<template>
	<div style="margin-left: 5%;">
		<el-row class="tacp">
			<el-col>
				<el-menu router default-active="$route.path" @open="handleOpen" @close="handleClose">
					<div
						style="margin-left: 35px;font-size: 20px;margin-top: 15px;font-weight: bold;padding-bottom: 5px;">
						<span style="margin-right: 18px;font-family: 微软雅黑;font-weight: 500;">个人中心</span>
						<!-- <span style="font-family: 微软雅黑;font-weight: 500;">中心</span> -->
					</div>

					<!-- <el-menu-item index="/personalCenter/myTigressMoney">
					<i class="el-icon-menu"></i>
					<span slot="title">我的虎虎币</span>
				</el-menu-item> -->
				<el-menu-item index="/personalCenter/dynamicIssue">
					<i class="el-icon-user-solid"></i>
					<span slot="title">个人详情</span>
				</el-menu-item>
					<el-menu-item index="/personalCenter/createiveManagement">
						<i class="el-icon-menu"></i>
						<span slot="title">创作管理</span>
					</el-menu-item>

					<el-menu-item index="/personalCenter/myOrder">
						<i class="el-icon-s-goods"></i>
						<span slot="title">我的订单</span>
					</el-menu-item>
					<el-menu-item index="/personalCenter/personCollection">
						<i class="el-icon-star-on"></i>
						<span slot="title">个人馆藏</span>
					</el-menu-item>
					

				</el-menu>

			</el-col>
		</el-row>
	</div>
</template>

<style scoped>
	.tacp {
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
