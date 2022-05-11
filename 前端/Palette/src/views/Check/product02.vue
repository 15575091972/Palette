<template>
	<div>
		<el-row id="auction_head">
			<!-- 标题 -->
			<el-col :span="6">
				<h2 style="font-family: 华文中宋;">我的创作</h2>
			</el-col>

		</el-row>

		<el-card id="auction_box-card1">
			<span style="margin-left: 750px;color: green;font-size: 16px;">已审核</span>
			<el-container direction="horizontal">

				<el-aside style="width: 350px;">
					<!-- 点击图片出现详细信息 -->
					<el-image style="width: 350px; height: 350px;cursor: pointer;" :src="auction_url"
						@click="dialogVisible = true"></el-image>
				</el-aside>
				<!-- </div> -->
				<el-main>
					<el-row type="flex" gutter="20" justify="start">
						<el-col :span="12" style="margin-left: 80px;font-size: 17px;font-family: 华文中宋;">
							<span>作品：睡莲</span>
						</el-col>
					</el-row>

					<el-row type="flex" gutter="20" justify="start" style="margin-top: 20px;">
						<el-col :span="12" style="margin-left: 80px;font-size: 17px;font-family: 华文中宋;">
							<span>作者：莫奈</span>
						</el-col>
					</el-row>

					<el-row style="margin-top: 20px;">
						<el-col :span="24" style="padding-left: 80px;font-size: 17px;font-family: 华文中宋;">
							<span>创作时间：1919-2</span>
						</el-col>
					</el-row>

					<el-row style="margin-top: 20px;">
						<el-col :span="24" style="padding-left: 80px;font-size: 17px;font-family: 华文中宋;">
							<span>作品类型：绘画</span>
						</el-col>
					</el-row>

					<el-row style="margin-top: 20px;">
						<el-col :span="24" style="padding-left: 80px;font-size: 17px;font-family: 华文中宋;">
							<span>作品简介：《池塘·睡莲》创作于1918至1919年间。是莫奈以睡莲为主题的重要晚期作品之一。这幅油画倾注了莫奈极大的创作热情，他对于光和影的运用在该作品中达到登峰造极的地步，远远超出了对物体本身的描绘。</span>
						</el-col>
					</el-row>

				</el-main>

			</el-container>

		</el-card>

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

	#action_slect {
		margin-top: 30px;
	}

	#auction_head {
		margin-left: 15%;
		margin-right: 15%;
	}

	#auction_box-card1 {
		margin-left: 15%;
		margin-right: 15%;
		margin-top: 30px;
	}

	.el-dialog__title {
		margin-top: 20px;
		font-size: 28px;
		font-family: 华文新魏;
		margin-left: 18px;
	}

	.priceTitle .el-dialog__title {
		margin-top: 20px;
		font-size: 20px;
		font-family: 微软雅黑;
		margin-left: 18px;
	}
</style>
