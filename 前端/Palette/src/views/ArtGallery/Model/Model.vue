<template>
	<div style="margin-left:4%">
		<div>
			<!-- 搜索框 -->
			<el-row>
				<el-col :span="6">
					<div style="font-size:32px;font-family:华文中宋;margin-top:24px;margin-left:24px;">艺术长廊—绘画</div>
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

		<el-row>
			<el-col :span="6">
				<el-row>
					<div class="image__place">
						<div id="picture">
							<el-image :src="src"></el-image>
						</div>
					</div>
				</el-row>
				<el-row>
					<el-card class="box-card1">
						<div class="text item">
							想去海边夏日入侵企业画
						</div>
					</el-card>
				</el-row>


			</el-col>

			<el-col :span="6">
				<el-row>
					<div class="image__place">
						<div id="picture">
							<el-image :src="src"></el-image>
						</div>
					</div>
				</el-row>
				<el-row>
					<el-card class="box-card1">
						<div class="text item">
							想去海边夏日入侵企业画
						</div>
					</el-card>
				</el-row>


			</el-col>
			<el-col :span="6">
				<el-row>
					<div class="image__place">
						<div id="picture">
							<el-image :src="src"></el-image>
						</div>
					</div>
				</el-row>
				<el-row>
					<el-card class="box-card1">
						<div class="text item">
							想去海边夏日入侵企业画
						</div>
					</el-card>
				</el-row>
			</el-col>
			<el-col :span="6">
				<el-row>
					<div class="image__place">
						<div id="picture">
							<el-image :src="src"></el-image>
						</div>
					</div>
				</el-row>
				<el-row>
					<el-card class="box-card1">
						<div class="text item">
							想去海边夏日入侵企业画
						</div>
					</el-card>
				</el-row>
			</el-col>
		</el-row>
		<el-row>
			<el-col :span="6">
				<el-row>
					<div class="image__place">
						<div id="picture">
							<el-image :src="src"></el-image>
						</div>
					</div>
				</el-row>
				<el-row>
					<el-card class="box-card1">
						<div class="text item">
							想去海边夏日入侵企业画
						</div>
					</el-card>
				</el-row>


			</el-col>

			<el-col :span="6">
				<el-row>
					<div class="image__place">
						<div id="picture">
							<el-image :src="src"></el-image>
						</div>
					</div>
				</el-row>
				<el-row>
					<el-card class="box-card1">
						<div class="text item">
							想去海边夏日入侵企业画
						</div>
					</el-card>
				</el-row>


			</el-col>
			<el-col :span="6">
				<el-row>
					<div class="image__place">
						<div id="picture">
							<el-image :src="src"></el-image>
						</div>
					</div>
				</el-row>
				<el-row>
					<el-card class="box-card1">
						<div class="text item">
							想去海边夏日入侵企业画
						</div>
					</el-card>
				</el-row>
			</el-col>
			<el-col :span="6">
				<el-row>
					<div class="image__place">
						<div id="picture">
							<el-image :src="src"></el-image>
						</div>
					</div>
				</el-row>
				<el-row>
					<el-card class="box-card1">
						<div class="text item">
							想去海边夏日入侵企业画
						</div>
					</el-card>
				</el-row>
			</el-col>
		</el-row>
		<el-row>
			<el-col :span="6">
				<el-row>
					<div class="image__place">
						<div id="picture">
							<el-image :src="src"></el-image>
						</div>
					</div>
				</el-row>
				<el-row>
					<el-card class="box-card1">
						<div class="text item">
							想去海边夏日入侵企业画
						</div>
					</el-card>
				</el-row>


			</el-col>

			<el-col :span="6">
				<el-row>
					<div class="image__place">
						<div id="picture">
							<el-image :src="src"></el-image>
						</div>
					</div>
				</el-row>
				<el-row>
					<el-card class="box-card1">
						<div class="text item">
							想去海边夏日入侵企业画
						</div>
					</el-card>
				</el-row>


			</el-col>
			<el-col :span="6">
				<el-row>
					<div class="image__place">
						<div id="picture">
							<el-image :src="src"></el-image>
						</div>
					</div>
				</el-row>
				<el-row>
					<el-card class="box-card1">
						<div class="text item">
							想去海边夏日入侵企业画
						</div>
					</el-card>
				</el-row>
			</el-col>
			<el-col :span="6">
				<el-row>
					<div class="image__place">
						<div id="picture">
							<el-image :src="src"></el-image>
						</div>
					</div>
				</el-row>
				<el-row>
					<el-card class="box-card1">
						<div class="text item">
							想去海边夏日入侵企业画
						</div>
					</el-card>
				</el-row>
			</el-col>
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
				options: [{
					value: '选项1',
					label: '按发布时间排序'
				}, {
					value: '选项2',
					label: '按交易次数排序'
				}],
				value: ''

			};
		},
		mounted() {},
		methods: {

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

	#select {
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

	/* .item {
		padding: 18px 0;
	} */
	/* 
	.box-card {
		width: 480px;
	} */
</style>
