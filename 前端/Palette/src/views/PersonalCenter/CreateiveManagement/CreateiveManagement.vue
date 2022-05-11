<template>
	<div>
		<div style="margin-top: 30px;margin-left: 21px;">
			<el-button style="margin-left: 30px;" type="text">图像</el-button>
			<el-button type="text">音频</el-button>
			<el-button type="text">文档</el-button>
			<el-button type="text">文件</el-button>
			<el-button type="text">实体</el-button>
			<el-button style="margin-left: 55%;background-color: #f0b3c6;height: 40px;
		border-radius: 8px;" @click="gotoinstaltyCreate">＋</el-button>
		</div>

		<div style="margin-left: 30px;">
			<el-row>
				<el-col :span="4">
					<el-row>
						<div class="image__plac">
							<div id="picture" style="width: 100px;height: 60px;margin-left: 20px;"
								@click="gotproduct01()">
								<el-image :src="src"  ></el-image>
							</div>
						</div>
					</el-row>

					<el-row>
						<el-card id="PC_card">
							<div>
								作品一
							</div>
						</el-card>
					</el-row>
				</el-col>

				<el-col :span="4">
					<el-row>
						<div class="image__plac">
							<div id="picture" style="width: 100px;height: 60px; margin-left: 20px;"
								@click="gotproduct02()">
								<el-image :src="src2"></el-image>
							</div>
						</div>
					</el-row>

					<el-row>
						<el-card id="PC_card">
							作品二
						</el-card>
					</el-row>
				</el-col>

			</el-row>
		</div>
	</div>
</template>

<script>
	export default {
		// name: "Carousel",
		data() {
			return {
				src: require("../../../assets/Images/supermarket/廖宝国《雪山之路》.jpg"),
				src2: require("../../../assets/Images/supermarket/shuilian3.png"),

			};
		},
		mounted() {},
		methods: {
			gotproduct01() {
				this.$router.replace('/product01')
			},

			gotproduct02() {
				this.$router.replace('/product02')
			},
			gotoinstaltyCreate() {
				//指定跳转到创作
				this.$router.replace('/instantlyCreate')
			}
		}
	};
</script>

<style>
	#PC_card {
		width: 100px;
		/* height: 50px; */
		margin-top: 30px;
		margin-left: 20px;
	}
</style>
