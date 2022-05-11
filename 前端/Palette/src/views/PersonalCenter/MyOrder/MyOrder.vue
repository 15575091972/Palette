<template>
	<div style="position:absolute;">

		<div>
			<el-table class="St_table" ref="multipleTable" :data="tableData" style="width: 100%;">
				<el-table-column prop="orderid" label="订单编号" width="150">
				</el-table-column>
				<el-table-column prop="product" label="作品" width="180">
					<el-image :src="producet_src"></el-image>
				</el-table-column>
				<el-table-column prop="name" label="名称" width="150">
				</el-table-column>
				<el-table-column prop="author" label="作者" width="150">
				</el-table-column>
				<el-table-column prop="time" label="时间" width="150">
				</el-table-column>
				<el-table-column prop="price" label="价格" width="150">
				</el-table-column>
			</el-table>
		</div>

	</div>
</template>

<script>
	export default {
		data() {
			return {
				producet_src: require("../../../assets/Images/候义萍《带刺的小绵羊》.jpg"),
				// producet_src: 'https://fuss10.elemecdn.com/9/bb/e27858e973f5d7d3904835f46abbdjpeg.jpeg',
				tableData: [{
					orderid: '83724875971',
					time: '2022-04-10',
					name: '带刺的小绵羊 ',
					author: '候义萍',
					price: '2000'
				}, ],
				multipleSelection: []
			}
		},

		methods: {
			toggleSelection(rows) {
				if (rows) {
					rows.forEach(row => {
						this.$refs.multipleTable.toggleRowSelection(row);
					});
				} else {
					this.$refs.multipleTable.clearSelection();
				}
			},
			handleSelectionChange(val) {
				this.multipleSelection = val;
			}
		}
	}
</script>

<style>
	/* .shopping_trolleryStyle {
		font-size: 30px;
		margin-top: 8px;
		margin-left: 30px;
	} */

	.St_table {
		margin-left: 20px;
		/* margin-right: 50px; */
		text-align: center;
	}


	/* 	#S_tSelectButton {
		margin-left: 300px;
		width: 120px;
	}

	#S_tPurchaseButton {
		margin-left: 150px;

		width: 120px;
		background-color: red;
		color: #ffffff;
	} */
</style>
