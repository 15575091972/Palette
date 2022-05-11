<template>
	<div>
	<!-- 	<hr style="width: 1100px;margin: 0 auto;
						border: 0;
						height: 0;
						border-top: 0px solid rgba(0, 0, 0, 0.1);
						border-bottom: 1px solid rgba(255, 255, 255, 0.3);
				" /> -->
		<div style="display: flex;align-items: center;justify-content: center;margin-top: 20px;">
			<el-table :data="tableData" style="width: 100%;margin-left: 10px;" max-height="500">
				<el-table-column fixed prop="date" label="日期" width="200">
				</el-table-column>
				<el-table-column prop="amount" label="金额" width="200">
				</el-table-column>
				<el-table-column prop="detail" label="详情" width="400">
				</el-table-column>
				<el-table-column fixed="right" label="操作" width="120">
					<template slot-scope="scope">
						<el-button @click.native.prevent="deleteRow(scope.$index, tableData)" type="text" size="small">
							移除
			 		</el-button>
					</template>
				</el-table-column>
			</el-table>
		</div>



	</div>
</template>

<script>
	export default {
	    methods: {
	      deleteRow(index, rows) {
	        rows.splice(index, 1);
	      }
	    },
	    data() {
	      return {
	        tableData: [{
	          date: '2016-05-03',
	          amount:'+10000',
	          detail:'出售xxx画所得'
	        }, {
	          date: '2016-05-03',
	          amount:'-10000',
	          detail:'购买xxx画所得'
	        }, {
	          date: '2016-05-03',
	          amount:'+100',
	          detail:'出售xxx画所得'
	        }, {
	          date: '2016-05-03',
	          amount:'-5000',
	          detail:'购买xxx画所得'
	        }, {
	          date: '2016-05-03',
	          amount:'+10',
	          detail:'出售xxx画所得'
	        }, {
	          date: '2016-05-03',
	          amount:'-800',
	          detail:'购买xxx画所得'
	        }, {
	          date: '2016-05-03',
	          amount:'+8000',
	          detail:'出售xxx画所得'
	        },{
	          date: '2016-05-03',
	          amount:'+10000',
	          detail:'出售xxx画所得'
	        }, {
	          date: '2016-05-03',
	          amount:'+10000',
	          detail:'出售xxx画所得'
	        }]
	      }
	    }
	  }
</script>

<style>

<yle>