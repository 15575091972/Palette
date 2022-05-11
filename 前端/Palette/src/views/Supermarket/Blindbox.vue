<template>
	<div>
		<!-- 标题 -->
		<div>
			<h2 style="font-family: 微软雅黑;font-weight:400;margin-left: 15%;">盲盒市场</h2>
		</div>
		<!-- 整体卡片盒子 -->
		<el-card class="box_style">
			<!-- 盲盒列表盒子 -->
			<div
				style="flex-direction: column;display: flex;margin-top: 30px;margin-bottom: 30px;margin-left: 4%;margin-right:4% ;">
				<!-- 豪华盲盒卡片 -->
				<el-card>
					<!-- 整体盒子 -->
					<div style="margin-left: 40px;margin-top: 5px;flex-direction: row;display: flex;">
						<!-- 图片区域 -->
						<div style="width: 145px;height:145px;">
							<img @click="dialogVisible = true" src="../../assets/Images/supermarket/manghe1.png"
								id="manghe1" />
						</div>
						<!-- 内容区域 -->
						<div style="flex-direction: row;display: flex;">
							<div>
								<!-- 标签 -->
								<div style="flex-direction: row;display: flex;">
									<span id="mangheName"
										style="margin-left: 50px;margin-top: 28px;font-family: 微软雅黑;font-weight:500;">豪华盲盒</span>
									<el-tag size="small" style="margin-top: 27px;margin-left: 8px;font-family: 微软雅黑;">绘画
									</el-tag>
									<el-tag size="small" type="success"
										style="margin-top: 27px;margin-left: 5px;font-family: 微软雅黑;">书法</el-tag>
									<el-tag size="small" type="danger"
										style="margin-top: 27px;margin-left: 5px;font-family: 微软雅黑;">摄影</el-tag>
									<el-tag size="small" type="warning"
										style="margin-top: 27px;margin-left: 5px;font-family: 微软雅黑;">影视</el-tag>
								</div>
								<!-- 简介 -->
								<div style="flex-direction: row;display: flex;">
									<span style="margin-left: 50px;margin-top: 20px;font-family: 微软雅黑;">
										简介：豪华艺术品盲盒，内有惊喜哦......
									</span>
								</div>
								<!-- 价格 -->
								<div style="flex-direction: row;display: flex;">
									<span style="margin-left: 50px;margin-top: 20px;font-family: 微软雅黑;">价值：1888</span>
									<span style="margin-left: 30px;margin-top: 20px;font-family: 微软雅黑;">开盒时间:2022-04-12</span>
									</div>
							</div>
							<!-- 购买盒子 -->
							<div style="margin-top: 28px;margin-left: 80px;flex-direction: column;display: flex;">
								<div>
									<span style="font-family: 微软雅黑;">剩余量：</span>
									<span style="color: red;">{{remainAccount1}}</span>
								</div>
								<div style="margin-top: 20px;">
									<span style="font-family: 微软雅黑;">已售出：</span>
									<span style="color: green;">{{haveBuyAccount1}}</span>
								</div>

								<div v-if="ishaveBuy1==false">
									<el-button @click="judgeBuy1()" type="danger" size="mini"
										style="margin-top: 20px;width: 80px;">购买</el-button>
								</div>
								<div v-else>
									<el-button@click="judgeBuy1()" type="danger" size="mini"
										style="margin-top: 20px;width: 80px;" disabled>购买</el-button>
								</div>
								<!-- <el-button @click="judgeBuy1()" type="danger" size="mini" style="margin-top: 20px;">购买</el-button> -->
							</div>
						</div>
					</div>
				</el-card>

				<!-- 高级盲盒卡片 -->
				<el-card>
					<!-- 整体盒子 -->
					<div style="margin-left: 40px;margin-top: 5px;flex-direction: row;display: flex;">
						<!-- 图片区域 -->
						<div style="width: 145px;height:145px;">
							<img @click="dialogVisible = true" src="../../assets/Images/supermarket/manghe2.png"
								id="manghe1" />
						</div>
						<!-- 内容区域 -->
						<div>
							<!-- 标签 -->
							<div style="flex-direction: row;display: flex;">
								<span id="mangheName"
									style="margin-left: 50px;margin-top: 28px;font-family: 微软雅黑;font-weight:500;">高级盲盒</span>

								<el-tag size="small" style="margin-top: 27px;margin-left: 8px;font-family: 微软雅黑;">绘画
								</el-tag>
								<el-tag size="small" type="danger"
									style="margin-top: 27px;margin-left: 5px;font-family: 微软雅黑;">游戏</el-tag>
								<el-tag size="small" type="success"
									style="margin-top: 27px;margin-left: 5px;font-family: 微软雅黑;">书法</el-tag>
							</div>
							<!-- 简介 -->
							<div style="flex-direction: row;display: flex;">
								<span style="margin-left: 50px;margin-top: 20px;font-family: 微软雅黑;">
									简介：高级艺术品盲盒，内有惊喜哦......
								</span>
							</div>
							<!-- 价格 -->
							<div style="flex-direction: row;display: flex;">
								<span style="margin-left: 50px;margin-top: 20px;font-family: 微软雅黑;">价值：888</span>
							<span style="margin-left: 30px;margin-top: 20px;font-family: 微软雅黑;">开盒时间:2022-04-04</span>
							</div>
						</div>
						<!-- 购买盒子 -->
						<div style="margin-top: 28px;margin-left: 80px;flex-direction: column;display: flex;">
							<div>
								<span style="font-family: 微软雅黑;">剩余量：</span>
								<span style="color: red;">{{remainAccount2}}</span>
							</div>
							<div style="margin-top: 20px;">
								<span style="font-family: 微软雅黑;">已售出：</span>
								<span style="color: green;">{{haveBuyAccount2}}</span>
							</div>
							<div v-if="ishaveBuy2==false">
								<el-button @click="judgeBuy2()" type="danger" size="mini"
									style="margin-top: 20px;width: 80px;">购买</el-button>
							</div>
							<div v-else>
								<el-button@click="judgeBuy2()" type="danger" size="mini"
									style="margin-top: 20px;width: 80px;" disabled>购买</el-button>
							</div>
							<!-- <el-button @click="judgeBuy2()" type="danger" size="mini" style="margin-top: 20px;">购买</el-button> -->
						</div>
					</div>
				</el-card>

				<!-- 中级盲盒卡片 -->
				<el-card>
					<!-- 整体盒子 -->
					<div style="margin-left: 40px;margin-top: 5px;flex-direction: row;display: flex;">
						<!-- 图片区域 -->
						<div style="width: 145px;height:145px;">
							<img @click="dialogVisible = true" src="../../assets/Images/supermarket/manghe3.png"
								id="manghe1" />
						</div>
						<!-- 内容区域 -->
						<div>
							<!-- 标签 -->
							<div style="flex-direction: row;display: flex;">
								<span id="mangheName"
									style="margin-left: 50px;margin-top: 28px;font-family: 微软雅黑;font-weight:500;">中级盲盒</span>
								<el-tag size="small" style="margin-top: 27px;margin-left: 8px;font-family: 微软雅黑;">绘画
								</el-tag>

								<el-tag size="small" type="info"
									style="margin-top: 27px;margin-left: 5px;font-family: 微软雅黑;">音乐</el-tag>
							</div>
							<!-- 简介 -->
							<div style="flex-direction: row;display: flex;">
								<span style="margin-left: 50px;margin-top: 20px;font-family: 微软雅黑;">
									简介：中级艺术品盲盒，内有惊喜哦......
								</span>
							</div>
							<!-- 价格 -->
							<div style="flex-direction: row;display: flex;">
								<span style="margin-left: 50px;margin-top: 20px;font-family: 微软雅黑;">价值：288</span>
							<span style="margin-left: 30px;margin-top: 20px;font-family: 微软雅黑;">开盒时间:2022-04-17</span>
							</div>
						</div>
						<!-- 购买盒子 -->
						<div style="margin-top: 28px;margin-left: 80px;flex-direction: column;display: flex;">
							<div>
								<span style="font-family: 微软雅黑;">剩余量：</span>
								<span style="color: red;">{{remainAccount3}}</span>
							</div>
							<div style="margin-top: 20px;">
								<span style="font-family: 微软雅黑;">已售出：</span>
								<span style="color: green;">{{haveBuyAccount3}}</span>
							</div>
							<div v-if="ishaveBuy3==false">
								<el-button @click="judgeBuy3()" type="danger" size="mini"
									style="margin-top: 20px;width: 80px;">购买</el-button>
							</div>
							<div v-else>
								<el-button@click="judgeBuy3()" type="danger" size="mini"
									style="margin-top: 20px;width: 80px;" disabled>购买</el-button>
							</div>
							<!-- <el-button @click="judgeBuy3()" type="danger" size="mini" style="margin-top: 20px;">购买</el-button> -->
						</div>
					</div>
				</el-card>

				<!-- 迷你盲盒卡片 -->
				<el-card>
					<!-- 整体盒子 -->
					<div style="margin-left: 40px;margin-top: 5px;flex-direction: row;display: flex;">
						<!-- 图片区域 -->
						<div style="width: 145px;height:145px;">
							<img @click="dialogVisible = true" src="../../assets/Images/supermarket/manghe4.png"
								id="manghe1" />
						</div>
						<!-- 内容区域 -->
						<div>
							<!-- 标签 -->
							<div style="flex-direction: row;display: flex;">
								<span id="mangheName"
									style="margin-left: 50px;margin-top: 28px;font-family: 微软雅黑;font-weight:500;">迷你盲盒</span>
								<el-tag size="small" style="margin-top: 27px;margin-left: 8px;font-family: 微软雅黑;">绘画
								</el-tag>
								<el-tag type="warning" size="small" style="margin-top: 27px;margin-left: 8px;font-family: 微软雅黑;">书法
								</el-tag>
								<!-- 								<el-tag size="small" type="info"
									style="margin-top: 27px;margin-left: 5px;font-family: 微软雅黑;">音乐</el-tag> -->
							</div>
							<!-- 简介 -->
							<div style="flex-direction: row;display: flex;">
								<span style="margin-left: 50px;margin-top: 20px;font-family: 微软雅黑;">
									简介：迷你艺术品盲盒，内有惊喜哦......
								</span>
							</div>
							<!-- 价格 -->
							<div style="flex-direction: row;display: flex;">
								<span style="margin-left: 50px;margin-top: 20px;font-family: 微软雅黑;">价值：88</span>
							<span style="margin-left: 30px;margin-top: 20px;font-family: 微软雅黑;">开盒时间:2022-04-23</span>
							</div>
						</div>
						<!-- 购买盒子 -->
						<div style="margin-top: 28px;margin-left: 80px;flex-direction: column;display: flex;">
							<div>
								<span style="font-family: 微软雅黑;">剩余量：</span>
								<span style="color: red;">{{remainAccount4}}</span>
							</div>
							<div style="margin-top: 20px;">
								<span style="font-family: 微软雅黑;">已售出：</span>
								<span style="color: green;">{{haveBuyAccount4}}</span>
							</div>
							<div v-if="ishaveBuy4==false">
								<el-button @click="judgeBuy4()" type="danger" size="mini"
									style="margin-top: 20px;width: 80px;">购买</el-button>
							</div>
							<div v-else>
								<el-button@click="judgeBuy4()" type="danger" size="mini"
									style="margin-top: 20px;width: 80px;" disabled>购买</el-button>
							</div>
							<!-- <el-button @click="judgeBuy4()" type="danger" size="mini" style="margin-top: 20px;">购买</el-button> -->
						</div>
					</div>
	
				</el-card>



			</div>
			<!-- 转动盒子 -->
			<!-- <div style="margin-left: 50px;margin-top: 40px;">
				<img @click="dialogVisible = true" src="../../assets/Images/supermarket/rotation.png" id="rotate_box"
					style="" />
			</div> -->
			<!-- <el-button type="text" @click="dialogVisible = true">点击打开盲盒</el-button> -->
		</el-card>

		<!-- suprice弹出框 -->
		<el-dialog title="恭喜" :visible.sync="dialogVisible" width="30%">
			<div style="margin-left: 90px;">
				<img src="../../assets/Images/supermarket/boom1.gif" />
			</div>
			<span slot="footer" class="dialog-footer">
				<el-button size="small" @click="dialogVisible = false">取 消</el-button>
				<el-button size="small" type="primary" @click="dialogVisible = false">确 定</el-button>
			</span>
		</el-dialog>

		<!-- 确认购买豪华盲盒弹出框 -->
		<el-dialog title="提示" :visible.sync="isBuy1" width="25%">
			<span>你确认购买一个{{blindBoxName}}吗？</span>
			<span slot="footer" class="dialog-footer">
				<el-button size="small" @click="isBuy1 = false">取 消</el-button>
				<el-button size="small" type="primary" @click="buy1()">确 定</el-button>
			</span>
		</el-dialog>

		<!-- 确认购买高级盲盒弹出框 -->
		<el-dialog title="提示" :visible.sync="isBuy2" width="25%">
			<span>你确认购买一个{{blindBoxName}}吗？</span>
			<span slot="footer" class="dialog-footer">
				<el-button size="small" @click="isBuy2 = false">取 消</el-button>
				<el-button size="small" type="primary" @click="buy2()">确 定</el-button>
			</span>
		</el-dialog>

		<!-- 确认购买中级盲盒弹出框 -->
		<el-dialog title="提示" :visible.sync="isBuy3" width="25%">
			<span>你确认购买一个{{blindBoxName}}吗？</span>
			<span slot="footer" class="dialog-footer">
				<el-button size="small" @click="isBuy3 = false">取 消</el-button>
				<el-button size="small" type="primary" @click="buy3()">确 定</el-button>
			</span>
		</el-dialog>

		<!-- 确认购买迷你盲盒弹出框 -->
		<el-dialog title="提示" :visible.sync="isBuy4" width="25%">
			<span>你确认购买一个{{blindBoxName}}吗？</span>
			<span slot="footer" class="dialog-footer">
				<el-button size="small" @click="isBuy4 = false">取 消</el-button>
				<el-button size="small" type="primary" @click="buy4()">确 定</el-button>
			</span>
		</el-dialog>



	</div>
</template>

<script>
	export default {
		// name: "Carousel",
		data() {
			return {
				ishaveBuy1: false,
				ishaveBuy2: false,
				ishaveBuy3: false,
				ishaveBuy4: false,
				remainAccount1: 6,
				remainAccount2: 23,
				remainAccount3: 34,
				remainAccount4: 13,
				haveBuyAccount1: 24,
				haveBuyAccount2: 36,
				haveBuyAccount3: 66,
				haveBuyAccount4: 187,
				blindBoxName: '',
				isBuy1: false,
				isBuy2: false,
				isBuy3: false,
				isBuy4: false,
				dialogVisible: false,
				Supermarket_priceSelect: '',
				Supermarket_typeSelect: '',
				Supermarket_fieldSelect: '',
				Supermarket_Select: '',
				select: '',
				src: require("../../assets/Images/supermarket/manghe1.png"),
				images3: [{
						id: 1,
						url: require("../../assets/Images/supermarket/2.png")
					},
					{
						id: 2,
						url: require("../../assets/Images/supermarket/shuilian.png")
					}
				],
			};
		},
		mounted() {},
		methods: {
			judgeBuy1() {
				this.isBuy1 = true;
				this.blindBoxName = "豪华盲盒";
			},
			judgeBuy2() {
				this.isBuy2 = true;
				this.blindBoxName = "高级盲盒";
			},
			judgeBuy3() {
				this.isBuy3 = true;
				this.blindBoxName = "中级盲盒";
			},
			judgeBuy4() {
				this.isBuy4 = true;
				this.blindBoxName = "迷你盲盒";
			},
			buy1() {
				this.remainAccount1 = this.remainAccount1 - 1;
				this.haveBuyAccount1 = this.haveBuyAccount1 + 1;
				this.isBuy1 = false;
				this.ishaveBuy1 = true;
				this.$notify({
					title: '成功',
					message: '购买成功！',
					type: 'success'
				});
			},
			buy2() {
				this.remainAccount2 = this.remainAccount2 - 1;
				this.haveBuyAccount2 = this.haveBuyAccount2 + 1;
				this.isBuy2 = false;
				this.ishaveBuy2 = true;
				this.$notify({
					title: '成功',
					message: '购买成功！',
					type: 'success'
				});
			},
			buy3() {
				this.remainAccount3 = this.remainAccount3 - 1;
				this.haveBuyAccount3 = this.haveBuyAccount3 + 1;
				this.isBuy3 = false;
				this.ishaveBuy3 = true;
				this.$notify({
					title: '成功',
					message: '购买成功！',
					type: 'success'
				});
			},
			buy4() {
				this.remainAccount4 = this.remainAccount4 - 1;
				this.haveBuyAccount4 = this.haveBuyAccount4 + 1;
				this.isBuy4 = false;
				this.ishaveBuy4 = true;
				this.$notify({
					title: '成功',
					message: '购买成功！',
					type: 'success'
				});
			}

		}
	};
</script>

<style>
	/* 抽奖盒子 */
	#rotate_box {
		width: 200px;
		height: 200px;
		cursor: pointer;
		margin-left: 350px;
		transition: width 2s, height 2s, transform 2s;
	}

	#rotate_box:hover {
		width: 200px;
		height: 200px;
		transform: rotate(1800deg);
	}

	/* 盲盒1 */
	#manghe1 {
		width: 140px;
		height: 140px;
		cursor: pointer;
		transition: width 0.1s, height 0.1s;
	}

	#manghe1:hover {
		width: 143px;
		height: 143px;
	}

	/* 弹出框标题 */
	.el-dialog__title {
		font-family: 微软雅黑;
	}

	/* 整体盒子样式 */
	.box_style {
		width: 70%;
		margin-left: 15%;
		margin-right: 15%;
		margin-top: 10px;
	}
</style>
