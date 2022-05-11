<template>
	<div>
		<!-- <div>
			<div style=" display: flex ;align-items: center;justify-content: center;font-size: 20px;margin-top: 15px;margin-bottom: 15px;font-weight: 500;">
				<span >偏好设置</span>
			</div>
			<hr style="width: 100%;margin: 0 auto;
							border: 0;
							height: 0;
							border-top: 1px solid rgba(0, 0, 0, 0.1);
							border-bottom: 1px solid rgba(255, 255, 255, 0.3);
					" />
		</div> -->

		<div>
			<div style="display: flex;flex-direction: row;margin-left: 20px;margin-top: 10px;margin-bottom: 10px;">
				主题模式
				<el-switch v-model="value1" active-text="夜间模式" inactive-text="日间模式" style="margin-left: 700px;">
				</el-switch>

			</div>
			<hr style="width: 100%;margin: 0 auto;
							border: 0;
							height: 0;
							border-top: 1px solid rgba(0, 0, 0, 0.1);
							border-bottom: 1px solid rgba(255, 255, 255, 0.3);
					" />
		</div>

		<div>
			<div style="display: flex;flex-direction: row;margin-left: 20px;margin-top: 10px;margin-bottom: 10px;">
				新消息通知
				<el-tooltip :content="'Switch value: ' + value" placement="top">
					<el-switch v-model="value3" active-color="#409eff" inactive-color="#DCDFE6" active-value="100"
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
			<div style="display: flex;flex-direction: row;margin-left: 20px;margin-top: 10px;margin-bottom: 10px;">
				个性化推荐
				<el-tooltip :content="'Switch value: ' + value" placement="top">
					<el-switch v-model="value4" active-color="#409EFF" inactive-color="#DCDFE6" active-value="100"
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

	</div>

</template>

<script>
	export default {
		data() {
			return {
				Hometoken: '',
				value: true,
				value1: true,
				value2: true,
				value3: '100',
				value4: '100'
			}
		},
		methods: {

			getHomePaintList() {
				var that = this
				this.Hometoken = localStorage.getItem('token')
				this.$axios.post(
						"http://5na1qhit.dnat.tech/palette/mainPage/getRecommendArt", {
							token: 1510996651428855809,
							type: 1,
							num: 3
						}
					)
					.then(response => {
						if (response.data.code == 200) {
							// that.PaintPictureList = response.data.data.art;
							console.log(response.data);
						} else {
							console.log(response.data);
						}
					})
					.catch(function(error) {});

			},
		},
		created() {
			this.getHomePaintList();
		}
	};
</script>

<style>
</style>
