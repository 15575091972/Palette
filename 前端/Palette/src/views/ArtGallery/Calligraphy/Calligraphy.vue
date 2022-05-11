<template>
	<div style="margin-left:4%">
	<div>
		<!-- 搜索框 -->
		<el-row>
			<el-col :span="6">
				<div style="font-size:28px;font-family:华文新魏;margin-top:24px;margin-left:24px;">艺术长廊 &nbsp;&nbsp;书法</div>
			</el-col>
	
		
			<el-col :span="2" style="margin-left:250px">
				<el-button style="margin-top: 20px;" icon="el-icon-search"></el-button>
	
			</el-col>
	
			<el-col :span="5">
				<el-select id="Paintselect1" v-model="Paintvalue1" filterable placeholder="请选择">
					<el-option v-for="item in options1" :key="item.value1" :label="item.label1"
						:value="item.value1">
					</el-option>
				</el-select>
			</el-col>
			<el-col :span="5">
				<el-select id="Paintselect2" v-model="Paintvalue2" filterable placeholder="请选择">
					<el-option v-for="item in options2" :key="item.value2" :label="item.label2" :value="item.value2">
					</el-option>
				</el-select>
			</el-col>
			
		</el-row>
	</div>

		<!-- 图片列表 -->
		<el-row  >
			<div v-for="v in calligraphyList" :key="v.value" >
				<el-col :span="5" style="margin-left: 3%;" class="image__place">
					<el-card :body-style="{ padding: '15px' }" style="height: 300px;margin-top: 30px;">
						<div id="homeimage">
							<el-image v-bind:src="apiUrl+v.showImage" alt=""> </el-image>
						</div>
						<h4>{{v.artName}}</h4>
						<p>简介:{{v.artIntroduction}}</p>
					</el-card>
				</el-col>
			</div>
		</el-row>
	</div>
</template>

<script>
	export default {
		name: "Carousel",
		data() {
			return {

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
				Hometype: 1,
				Homenum: 3,
				
				//获取到的数组图片
				calligraphyList: [],
				
				
			};
		},
		mounted() {},
		methods: {
			//获取图片
			getaclligraphyList() {
				var that = this
				this.mytoken = localStorage.getItem('token')
				this.$axios.post(
						this.apiUrl+"/palette/mainPage/getRecommendArt", {
							token: this.Hometoken,
							type: 2,
							num: 10
						}
					)
					.then(response => {
						if (response.data.code == 200) {
							console.log(response.data.data);
							that.calligraphyList = response.data.data.art;
							//console.log(that.calligraphyList);
			// 				this.$message({
			// 					type: 'info',
			// 					message: '获取首页数据成功'
			// 				});
			
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
			this.getaclligraphyList();
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
