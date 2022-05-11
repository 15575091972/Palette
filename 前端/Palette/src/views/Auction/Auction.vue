<template>
	<div>
		<!-- 标题和搜索框 -->
		<el-row id="auction_head" type="flex">
			<!-- 标题 -->
			<div>
				<h2 style="font-family: 微软雅黑;">一口价拍卖会场</h2>
			</div>
			<div style="margin-left: 10px;">
				<img src="../../assets/Images/logo/flame.png" style="margin-top: 25px;width: 20px;height:20px;" />
			</div>

		</el-row>
		<div style="flex-direction: row;display: flex;">
			<span style="margin-left: 190px;margin-top: 0px;font-family: 微软雅黑;">最热拍卖品</span>
			<el-button type="text" style="margin-left: 60%;" @click="go_supermarket()">更多拍卖品 ></el-button>
		</div>

		<!-- 拍卖卡片主题	 -->
		<el-card id="auction_box-card1">
			<el-container direction="horizontal">
				<el-header>
					<!-- 头 -->
				</el-header>
				<!-- 侧边图片部分 -->
				<el-aside style="width: 320px;margin-top: 15px;">
					<!-- 点击图片出现详细信息 -->
					<el-image style="width: 300px; height: 300px;cursor: pointer;" :src="auction_url"
						@click="dialogVisible = true"></el-image>
				</el-aside>
				<!-- 主题部分 -->
				<el-main>
					<el-row type="flex" gutter="20" justify="start">
						<el-col :span="12" style="margin-left: 80px;font-size: 17px;font-family: 微软雅黑;">
							<span>作品：睡莲</span>
						</el-col>
						<el-col :span="12" style="font-size: 17px;font-family: 微软雅黑;">
							<span>作者：莫奈</span>
						</el-col>
					</el-row>
					<el-row style="margin-top: 20px;">
						<el-col :span="24" style="padding-left: 80px;font-size: 17px;font-family: 微软雅黑;">
							<span>开始时间：2022-4-12 18:00</span>
						</el-col>
					</el-row>
					<el-row style="margin-top: 20px;">
						<el-col :span="24" style="padding-left: 80px;font-size: 17px;font-family: 微软雅黑;">
							<span>结束时间：2022-4-12 22:00</span>
						</el-col>
					</el-row>
					<el-row style="margin-top: 20px;">
						<el-col :span="24" style="padding-left: 80px;font-size: 17px;font-family: 微软雅黑;">
							<span>当前已竞拍人数：</span><span style="color: red;">24</span>
						</el-col>
					</el-row>
					<el-row style="margin-top: 20px;" type="flex">
						<el-col :span="24" style="padding-left: 80px;font-size: 17px;font-family: 微软雅黑;">
							<span>起拍价格: </span><span style="color: red;font-size: 18px;">{{startPrice}}</span>
						</el-col>
					</el-row>
					<el-row style="margin-top: 20px;">
						<el-col :span="24" style="padding-left: 80px;font-size: 17px;font-family: 微软雅黑;">
							<span>最低加价: </span><span style="color: red;font-size: 18px;">{{minimalAddPrice}}</span>
						</el-col>
					</el-row>
					<!-- 出价信息 -->
					<el-row style="margin-top: 15px;" type="flex">
						<el-col :span="2" style="margin-left: 80px;margin-right: 24px;font-family: 微软雅黑;">
							<div v-if="isOfferPrice==true">
								<el-button size="small" type="danger" @click="judgePrice()">出价</el-button>
							</div>
							<div v-else>
								<el-button size="small" type="danger" @click="judgePrice()" disabled>出价</el-button>
							</div>
						</el-col>
						<el-col :span="12" style="margin-top: 1px;">
							<div v-if="isOfferPrice==true">
								<el-input size="small" v-model="input_price" placeholder="请输入价格" style=""></el-input>
							</div>
							<div v-else>
								<el-input size="small" v-model="input_price" placeholder="请输入价格" style="" disabled>
								</el-input>
							</div>
						</el-col>

					</el-row>
					<el-row>
						<el-col :span="20"
							style="margin-top: 10px;margin-left: 80px;color: #67c23a;font-size: 16px;font-family: 微软雅黑;">
							<div v-if="isOfferPrice==false">
								你已出价：{{currentPrice}}
							</div>
						</el-col>
					</el-row>
				</el-main>
				<!-- 脚 -->
				<el-footer>

				</el-footer>
			</el-container>

		</el-card>
		<!-- 出价提示信息 -->
		<el-dialog class="priceTitle" title="警告" :visible.sync="centerDialogVisible" width="25%" center
			style="padding-top: 100px;">
			<span style="font-size: 18px;margin-left: 110px;font-family: 微软雅黑;color: #000000;">出价失败！</span>
			<span slot="footer" class="dialog-footer">
				<el-button size="mini" @click="centerDialogVisible = false">取 消</el-button>
				<el-button size="mini" type="primary" @click="centerDialogVisible = false">确 定</el-button>
			</span>
		</el-dialog>

		<!--拍卖艺术品提示信息  -->
		<el-dialog title="睡莲" :visible.sync="dialogVisible" width="50%" :before-close="handleClose"
			style="padding-top: 20px;">
			<div style="flex-direction: row;display: flex;">
				<div style="margin-left: 20px;width: 200px;">
					<el-image style="width: 180px; height: 180px;cursor: pointer;" :src="auction_url"></el-image>
				</div>
				
				<div style="margin-top: 0px;margin-left: 20px;font-size: 18px;font-family: 微软雅黑;line-height: 36px;text-indent:2em;">
					《池塘·睡莲》创作于1918至1919年间。是莫奈以睡莲为主题的重要晚期作品之一。这幅油画倾注了莫奈极大的创作热情，他对于光和影的运用在该作品中达到登峰造极的地步，远远超出了对物体本身的描绘。
				</div>
			</div>
			<div style="height: 20px;"></div>
			
		</el-dialog>

	</div>
</template>

<script>
	export default {
		data() {
			return {
				isOfferPrice: true, //是否不可以出价
				input_price: '',
				auction_url: require("../../assets/Images/supermarket/shuilian.png"),
				Acution_select: '',
				dialogVisible: false,
				startPrice: 800000,
				minimalAddPrice: 1000,
				judgeResult: false,
				centerDialogVisible: false,
				currentPrice: 0
			}
		},
		methods: {
			//绘画更多
			go_supermarket() {
				this.$router.replace('/supermarket')
			},
			judgePrice() {
				if (this.input_price != "") {
					this.currentPrice = parseInt(this.input_price)
					if (isNaN(this.input_price) || this.currentPrice <= this.startPrice || (this.currentPrice - this
							.startPrice) < this.minimalAddPrice) {
						this.judgeResult = false;
					} else {
						this.judgeResult = true;
					}
				} else {
					this.judgeResult = false;
				}
				if (this.judgeResult == false) {
					this.centerDialogVisible = true;
				} else {
					// 出价成功提示信息弹出
					this.$notify({
						title: '成功',
						message: '出价成功！等待好消息吧！',
						type: 'success'
					});
					this.isOfferPrice = false;
				}
				console.log(this.judgeResult)
			}

		}
	}
</script>

<style>
	.el-input__inner {
		height: 40px;
	}

	#auction_head {
		margin-left: 15%;
		margin-right: 15%;
	}

	#auction_box-card1 {
		margin-left: 15%;
		margin-right: 15%;
	}

	.el-dialog__title {
		margin-top: 20px;
		font-size: 18px;
		font-family: 微软雅黑;
		margin-left: 18px;
	}

	.priceTitle .el-dialog__title {
		margin-top: 20px;
		font-size: 18px;
		font-family: 微软雅黑;
		margin-left: 18px;
	}
</style>
