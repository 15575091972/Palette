<template>
	<div style="margin-left:4%">
		<div>
			<!-- 搜索框 -->

			<el-row>

				<el-col :span="5" style="margin-left: 550px;">
					<el-input placeholder="请输入内容" style="margin-top: 20px;" v-model="inputperson" clearable>
					</el-input>

				</el-col>
				<el-col :span="2" style="margin-left: 30px;">
					<el-button style="margin-top: 20px;" icon="el-icon-search"></el-button>

				</el-col>

			</el-row>
		</div>

		<!-- 图片列表 -->
		<el-row>
			<div v-for="v in PaintPictureList" :key="v.value">
				<el-col :span="5" style="margin-left: 2%;" class="image__place">
					<el-card :body-style="{ padding: '15px' }" style="height: 280px;margin-top: 30px;">
						<div id="homeimage">
							<el-image v-bind:src="v.showImage" alt=""> </el-image>
						</div>
						<h4>{{v.artName}}</h4>
						<p style="font-size: 14px;font-family: 微软雅黑;font-weight:200;">简介:{{v.artIntroduction}}</p>
					</el-card>
				</el-col>
			</div>
		</el-row>
		<div style="height:200px;"></div>
	</div>
</template>

<script>
	export default {
		name: "Carousel",
		data() {
			return {
				inputperson: '',
				input: '向日葵',
				src: require("../../../assets/Images/artpicture/sea.png"),
				options1: [{
						value1: '选项1',
						label1: '全部'
					}, {
						value1: '选项2',
						label1: '按交易次数排序'
					},
					{
						value1: '选项3',
						label1: '按发布时间排序'
					}
				],
				options2: [{
						value2: '选项1',
						label2: '全部'
					}, {
						value2: '选项2',
						label2: '音频'
					},
					{
						value2: '选项3',
						label2: '图像'
					},

					{
						value2: '选项4',
						label2: '文件'
					},
					{
						value2: '选项5',
						label2: '文档'
					},
					{
						value2: '选项6',
						label2: '实体'
					}
				],

				Paintvalue1: '选项1',
				Paintvalue2: '选项1',

				Hometoken: '',
				Hometype: 2,
				Homenum: 3,

				//获取到的数组图片
				PaintPictureList: [{
						artName: '  带刺的小绵羊  ',

						// erwerma: require("../../../assets/Images/pay.png"),
						showImage: require("../../../assets/Images/候义萍《带刺的小绵羊》.jpg"),
						artIntroduction: ' 爱情中生气爆炸都是必然的，可是只要那个人一哄，小绵羊就温顺柔软起来。',
					},
					{
						artName: '雪山之路',
						showImage: require("../../../assets/Images/supermarket/廖宝国《雪山之路》.jpg"),
							artIntroduction: '雪山美景，漫漫长路，望向远方。',
						},
					],


				};
			},
			mounted() {},
				methods: {
					//获取图片
					getPaintList() {
						var that = this
						this.mytoken = localStorage.getItem('token')
						this.$axios.post(
								this.apiUrl + "/palette/mainPage/getRecommendArt", {
									token: this.Hometoken,
									type: 2,
									num: 2
								}
							)
							.then(response => {
								if (response.data.code == 200) {
									console.log(response.data.data);
									that.PaintPictureList = response.data.data.art;
									console.log(that.PaintPictureList);

								} else {
									console.log(response.data);
									this.$message({
										type: 'info',
										message: '获取数据失败'
									});
								}
							})
							.catch(function(error) {});

					},

				},
				created() {
					// this.value = this.options[0].key
					// this.getPaintList();
				}
		};
</script>

<style>
	#search {
		width: 100px;
	}

	.box-card1 {
		margin-left: 25%;
		margin-top: 10px;
		height: 60px;
		width: 50%;
	}

	#Paintselect1 {
		margin-top: 20px;
	}

	#Paintselect2 {
		margin-top: 20px;
	}

	#artH1 {
		margin-top: 30px;
		margin-left: 20px;
	}

	#picture {
		width: 220px;
		height: 100%;
		margin-left: 45px;
		margin-top: 20px;
	}

	.text {
		font-size: 14px;
	}
</style>
