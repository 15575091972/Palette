<template>
	<div style="margin-left: 5%;margin-right: 5%;">
		<div>
			<el-row>
				<el-col :span="4">
					<div class="shopping_trolleryStyle">购物车</div>
				</el-col>
				<el-col :span="14" :offset="1">
					<div style="margin-top: 15px;">
						<el-input placeholder="搜索作品" v-model="Shoppinginput">
							<el-button slot="append" icon="el-icon-search"></el-button>
						</el-input>
					</div>
				</el-col>
			</el-row>
		</div>
		<div style="margin-left: 20%;">
			<el-row>
				<el-col :span="17">
					<el-table class="St_table" ref="multipleTable" :data="SttableData" tooltip-effect="dark"
						style="width: 100%" @selection-change="handleSelectionChange">
						<el-table-column type="selection" width="50">
						</el-table-column>
						<el-table-column prop="goodsId" label="编号" width="180">
						</el-table-column>
						<el-table-column prop="simpleArt.showImage" label="作品" width="150">
							<template slot-scope="scope">
								<el-image :src=" apiUrl+scope.row.simpleArt.showImage"></el-image>
							</template>
						</el-table-column>
						<el-table-column prop="simpleArt.artName" label="名称" width="150">
						</el-table-column>
						<el-table-column prop="simpleArt.artAuthor" label="作者" width="130">
						</el-table-column>
						<el-table-column prop="resalePrice" label="价格" width="130">
						</el-table-column>
					</el-table>
				</el-col>
			</el-row>
		</div>

		<div style="margin-top: 30px;">

			<div  style="width: 100%;">
				<el-button size="small" style="margin-left: 250px;" @click="toggleSelection()">全选
				</el-button>

				<el-button size="small" style="margin-left: 10px;">删除
				</el-button>
				
					<span style="margin-left: 20%; font-size:20px"> 合计：</span>
						<span style="color: red;font-size: 22px;width: 200px;">{{totalMoney}}</span>
				

				<el-button id="S_tPurchaseButton"
					style="width: 80px; margin-left: 100px;  background-color: #ff5c34;color: white;" @click="purchase()">
					结算</el-button>
					
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				producet_src: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
				SttableData: [],
				multipleSelection: [],
				Shoppinginput: '',

				mytoken: '',

				Shsort: 2,

				//合计
				totalMoney: ' ',

				//余额
				balance: ' ',

				goodsid: '',

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
				let total = 0;
				this.multipleSelection.map((item, index) => {
					total += item.resalePrice;
				})
				// return total
				// this.totalMoney=total;
				console.log(total);
				this.totalMoney = total;

				let goodid = 0;
				this.multipleSelection.map((item, index) => {
					goodid += item.goodsId;
				})
				this.goodsid = goodid

			},


			//选中购物车
			toggleSelection() {

			},
			//购买
			purchase() {
				if (this.totalMoney >this.balance) {
					this.$message.error('余额不足，请充值');

					// var time;
					// //创建定时器
					// clearTimeout(time)
					// time = setTimeout(function() {
					// 	//延迟加载处理的方法
					// 	location.href=('/dynamicIssue');
					// }, 3000);

				} else {

					this.$confirm('你将继续交易', '提示', {
						confirmButtonText: '确定',
						cancelButtonText: '取消',
						type: 'warning'
					}).then(() => {

						// 确定交易
						this.updatelist();
						this.$message({
							type: 'success',
							message: '交易成功!',

						});
					}).catch(() => {
						this.$message({
							type: 'info',
							message: '已取消交易'
						});
					});
				}

			},


			//获取购物车列表
			getShoppingTrollery() {	
				var that = this
				this.mytoken = localStorage.getItem('token')
				this.$axios.post(
						this.apiUrl + "/palette/user/getShoppingCar", {
							token: this.mytoken,
							sort: this.Shsort
						}
					)
					.then(response => {
						if (response.data.code == 200) {
							// console.log(response.data.data.goods);
							this.SttableData = response.data.data.goods;
							// console.log(this.mytoken);

						} else {

						}
					})
					.catch(function(error) {});

			},
			//获取余额
			getBalance() {
				var that = this
				// this.mytoken = localStorage.getItem('token')
				this.$axios.post(
						this.apiUrl + "/palette/user/getBalance", {
							token: this.mytoken,
						}
					)
					.then(response => {
						if (response.data.code == 200) {
							console.log("余额：" + response.data.data.balance);
							this.balance = response.data.data.balance;
						} else {

						}
					})
					.catch(function(error) {});

			},
			//更新
			updatelist() {
				var that = this
				this.mytoken = localStorage.getItem('token')
				console.log(this.mytoken);
				console.log(this.goodsid);
				this.$axios.post(
						this.apiUrl + "/palette/user/deleteShoppingCar", {
							token: this.mytoken,
							goodsId: this.goodsid
						}
					)
					.then(response => {
						if (response.data.code == 200) {
							//删除成功更新列表
							this.getShoppingTrollery();
						} else {

						}
					})
					.catch(function(error) {});
			}

		},

		created() {
			this.getShoppingTrollery();
			this.getBalance();
		}
	}
</script>

<style>
	.shopping_trolleryStyle {
		font-size: 30px;
		margin-top: 8px;
		margin-left: 30px;
	}

	.St_table {
		/* margin-left: 200px;

		margin-right: 60px; */
		text-align: center;
	}


	#S_tSelectButton {}

	#S_tPurchaseButton {}
</style>
