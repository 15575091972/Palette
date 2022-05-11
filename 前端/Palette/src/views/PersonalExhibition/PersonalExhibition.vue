<template>
	<div>
		<!-- 搜索框 -->
		<el-row>
			<el-col :span="6">
				<h1 id="PersonExhibition_head">个人展馆</h1>
			</el-col>
			<el-col :span="3">
				<el-button  style="margin-top: 20px;" icon="el-icon-search"></el-button>
				
			</el-col>
			<el-col :span="5">
				<el-select id="select" v-model="value" filterable placeholder="请选择">
					<el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
					</el-option>
					<el-button slot="append" icon="el-icon-search"></el-button>
				</el-select>
				
			</el-col>
			<el-col :span="5">
				<el-select id="select" v-model="value" filterable placeholder="请选择">
					<el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
					</el-option>
				</el-select>
			</el-col>
			<el-col :span="5">
				<el-select id="select" v-model="value" filterable placeholder="请选择">
					<el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
					</el-option>
				</el-select>
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
							伍佰
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
				src: require("../../assets/Images/supermarket/2.png"),
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
	h1 {
		text-decoration: underline
	}

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

	#PersonExhibition_head {
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
