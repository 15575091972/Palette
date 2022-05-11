<template>
	<div>


		<div>
			<div style="display: flex;flex-direction: row;margin-left: 20px;margin-top: 10px;margin-bottom: 10px;">
				登录检查
				<el-tooltip :content="'Switch value: ' + value" placement="top">
					<el-switch v-model="value" active-color="#409eff" inactive-color="#DCDFE6" active-value="100"
						inactive-value="0" style="margin-left: 750px;">
					</el-switch>
				</el-tooltip>

			</div>
			<hr style="width: 100%;margin: 0 auto;
							border: 0;
							height: 0;
							border-top: 1px solid rgba(0, 0, 0, 0.1);
							border-bottom: 1px solid rgba(255, 255, 255, 0.3);
					" />
		</div>

		<div>
			<div style="display: flex;flex-direction: row;margin-left: 20px;">
				<el-button type="text" style="font-size: 15px;">修改认证密码</el-button>
			</div>
			<hr style="width: 100%;margin: 0 auto;
							border: 0;
							height: 0;
							border-top: 1px solid rgba(0, 0, 0, 0.1);
							border-bottom: 1px solid rgba(255, 255, 255, 0.3);
					" />
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				value: '100'
			}
		}
	};
</script>

<style>
</style>
