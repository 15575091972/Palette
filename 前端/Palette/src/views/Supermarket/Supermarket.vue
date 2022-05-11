<template>
	<div>
		<el-card class="box-card">


			<el-row>
				<el-col :span="16">
					<el-row>
						<h2 style="margin-left: 18px;font-family: 华文中宋;">正在拍卖：</h2>
					</el-row>
					<!-- 236,245,255 -->
					<!-- <el-button> -->
						<div @click="openDetail()" class="product1" style="height: 45px;padding-top:25px;">
							<el-row
								style="cursor: pointer;background-color: ;padding-left: 20px;width: 650px;border-radius:10px;">
								<div>
									<el-col :span="4" style="color: black;font-size: 15px;font-family:微软雅黑;">
										<span>睡莲</span>
									</el-col>
									<el-col :span="4" style="color: black;font-size: 15px;font-family: 微软雅黑;">
										<span>莫奈</span>
									</el-col>
									<el-col :span="8" style="color: black;font-size: 15px;font-family:微软雅黑;">
										<span>结束时间: 2022-2-22 6:00</span>
									</el-col>
								</div>
								<el-col :span="6">
									<img src="../../assets/Images/logo/flame.png"
										style="margin-top: 0px;width: 16px;height:16px;" />
									<span style="margin-left: 10px;font-family: 微软雅黑;color: #cb2d01;">热度：240</span>
								</el-col>
							</el-row>
						
						</div>
					<!-- </el-button> -->
					



					<!-- 弹出睡莲拍卖信息 -->
					<el-dialog class="priceTitle" title="拍卖" :visible.sync="shuiLianDialogVisible" width="80%" center
						style="">
						<el-card>
							<el-container direction="horizontal">
								<el-header>
									<!-- 头 -->
								</el-header>
								<!-- 侧边图片部分 -->
								<el-aside style="width: 320px;">
									<!-- 点击图片出现详细信息 -->
									<el-image style="width: 300px; height: 300px;cursor: pointer;"
										:src="auction_url_shuilian" @click="openDetailForShuiLian()"></el-image>
								</el-aside>
								<!-- 主题部分 -->
								<el-main>
									<el-row type="flex" gutter="20" justify="start">
										<el-col :span="12" style="margin-left: 70px;font-size: 17px;font-family: 微软雅黑;">
											<span>作品：睡莲</span>
										</el-col>
										<el-col :span="12" style="font-size: 17px;font-family: 微软雅黑;">
											<span>作者：莫奈</span>
										</el-col>
									</el-row>
									<el-row style="margin-top: 25px;">
										<el-col :span="24"
											style="padding-left: 70px;font-size: 17px;font-family: 微软雅黑;">
											<span>开始时间：2022-2-22 6:00</span>
										</el-col>
									</el-row>
									<el-row style="margin-top: 25px;">
										<el-col :span="24"
											style="padding-left: 70px;font-size: 17px;font-family: 微软雅黑;">
											<span>结束时间：2022-2-22 6:00</span>
										</el-col>
									</el-row>
									<el-row style="margin-top: 25px;">
										<el-col :span="24"
											style="padding-left: 70px;font-size: 17px;font-family: 微软雅黑;">
											<span>当前已竞拍人数：</span><span style="color: red;">24</span>
										</el-col>
									</el-row>
									<el-row style="margin-top: 25px;" type="flex">
										<el-col :span="12"
											style="padding-left: 70px;font-size: 17px;font-family: 微软雅黑;">
											<span>起拍价格: </span><span style="color: red;font-size: 18px;">800000</span>
										</el-col>
										<el-col :span="12"
											style="padding-left: 40px;font-size: 17px;font-family: 微软雅黑;">
											<span>最低加价: </span><span style="color: red;font-size: 18px;">1000</span>
										</el-col>
									</el-row>
									<!-- 出价信息 -->
									<el-row style="margin-top: 25px;" type="flex">
										<el-col :span="2"
											style="margin-left: 70px;margin-right: 18px;font-family: 微软雅黑;">
											<div>
												<el-button size="small" type="danger" @click="judgePrice()">出价
												</el-button>
											</div>
										</el-col>
										<el-col :span="10" style="margin-top: 1px;">
											<div>
												<el-input size="small" v-model="input_price" placeholder="请输入价格"
													style=""></el-input>
											</div>
										</el-col>

									</el-row>
								</el-main>
								<!-- 脚 -->
								<el-footer>

								</el-footer>
							</el-container>

						</el-card>
					</el-dialog>
					<!-- 弹出睡莲拍卖详细信息 -->
					<el-dialog title="睡莲" :visible.sync="shuiLianDialogDetail" width="50%" :before-close="handleClose"
						style="padding-top: 20px;">
						<div style="flex-direction: row;display: flex;">
							<div style="margin-left: 20px;width: 200px;">
								<el-image style="width: 180px; height: 180px;cursor: pointer;"
									:src="auction_url_shuilian"></el-image>
							</div>

							<div
								style="margin-top: 0px;margin-left: 20px;font-size: 18px;font-family: 微软雅黑;line-height: 36px;text-indent:2em;">
								《池塘·睡莲》创作于1918至1919年间。是莫奈以睡莲为主题的重要晚期作品之一。这幅油画倾注了莫奈极大的创作热情，他对于光和影的运用在该作品中达到登峰造极的地步，远远超出了对物体本身的描绘。
							</div>
						</div>
						<div style="height: 20px;"></div>

					</el-dialog>



				<div class="product1" style="height: 54px;padding-top:15px;">
					<el-row
						style="cursor: pointer;background-color: ;padding-left: 20px;width: 650px;margin-top: 10px;border-radius:10px;">
						<el-col :span="4" style="color: black;font-size: 15px;font-family:微软雅黑;">
							<span>夏夜晚风</span>
						</el-col>
						<el-col :span="4" style="color: black;font-size: 15px;font-family:微软雅黑;">
							<span>伍佰</span>
						</el-col>
						<el-col :span="8" style="color: black;font-size: 15px;font-family:微软雅黑;">
							<span>结束时间: 2022-2-22 8:00</span>
						</el-col>
						<el-col :span="6">
							<img src="../../assets/Images/logo/flame.png"
								style="margin-top: 0px;width: 16px;height:16px;" />
							<span style="margin-left: 10px;font-family: 微软雅黑;color: #cb2d01;">热度：170</span>
						</el-col>
					</el-row>
				</div>

					<div @click="openDetail2()" class="product1" style="height: 53px;padding-top:17px;">
						<el-row
							style="cursor: pointer;padding-left: 20px;width: 650px;margin-top: 10px;border-radius:10px;">
							<el-col :span="4" style="color: black;font-size: 15px;font-family:微软雅黑;">
								<span>想去海边</span>
							</el-col>
							<el-col :span="4" style="font-size: 15px;font-family:微软雅黑;">
								<span>夏日入侵</span>
							</el-col>
							<el-col :span="8" style="font-size: 16px;font-family:微软雅黑;">
								<span>结束时间: 2022-2-22 9:00</span>
							</el-col>
							<el-col :span="6" style="color:#cb2d01">
								<img src="../../assets/Images/logo/flame.png"
									style="width: 16px;height:16px;" />
								<span style="margin-left: 10px;font-family: 微软雅黑;">热度：150</span>
							</el-col>
						</el-row>
					</div>

				</el-col>

				<!-- 弹出想去海边拍卖详细信息 -->
				<el-dialog class="priceTitle" title="拍卖" :visible.sync="seaDialogVisible" width="80%" center style="">
					<el-card>
						<el-container direction="horizontal">
							<el-header>
								<!-- 头 -->
							</el-header>
							<!-- 侧边图片部分 -->
							<el-aside style="width: 320px;">
								<!-- 点击图片出现详细信息 -->
								<el-image style="width: 300px; height: 300px;cursor: pointer;" :src="auction_url_sea"
									@click="openDetailForSea()"></el-image>
							</el-aside>
							<!-- 主题部分 -->
							<el-main>
								<el-row type="flex" gutter="20" justify="start">
									<el-col :span="12" style="margin-left: 70px;font-size: 17px;font-family: 微软雅黑;">
										<span>作品：想去海边</span>
									</el-col>
									<el-col :span="12" style="font-size: 17px;font-family: 微软雅黑;">
										<span>作者：夏日入侵</span>
									</el-col>
								</el-row>
								<el-row style="margin-top: 25px;">
									<el-col :span="24" style="padding-left: 70px;font-size: 17px;font-family: 微软雅黑;">
										<span>开始时间：2022-2-22 6:00</span>
									</el-col>
								</el-row>
								<el-row style="margin-top: 25px;">
									<el-col :span="24" style="padding-left: 70px;font-size: 17px;font-family: 微软雅黑;">
										<span>结束时间：2022-2-22 6:00</span>
									</el-col>
								</el-row>
								<el-row style="margin-top: 25px;">
									<el-col :span="24" style="padding-left: 70px;font-size: 17px;font-family: 微软雅黑;">
										<span>当前已竞拍人数：</span><span style="color: red;">17</span>
									</el-col>
								</el-row>
								<el-row style="margin-top: 25px;" type="flex">
									<el-col :span="12" style="padding-left: 70px;font-size: 17px;font-family: 微软雅黑;">
										<span>起拍价格: </span><span style="color: red;font-size: 18;">16000</span>
									</el-col>
									<el-col :span="12" style="padding-left: 40px;font-size: 17px;font-family: 微软雅黑;">
										<span>最低加价: </span><span style="color: red;font-size: 18;">100</span>
									</el-col>
								</el-row>
								<!-- 出价信息 -->
								<el-row style="margin-top: 25px;" type="flex">
									<el-col :span="2" style="margin-left: 70px;margin-right: 18px;font-family: 微软雅黑;">
										<div>
											<el-button size="small" type="danger" @click="judgePrice()">出价</el-button>
										</div>
									</el-col>
									<el-col :span="10" style="margin-top: 1px;">
										<div>
											<el-input size="small" v-model="input_price" placeholder="请输入价格" style="">
											</el-input>
										</div>
									</el-col>

								</el-row>
							</el-main>
							<!-- 脚 -->
							<el-footer>

							</el-footer>
						</el-container>

					</el-card>
				</el-dialog>



				<!-- 弹出睡莲拍卖详细信息 -->
				<el-dialog title="睡莲" :visible.sync="seaDialogDetail" width="60%" :before-close="handleClose"
					style="padding-top: 20px;">
					<div style="flex-direction: row;display: flex;">
						<div style="margin-left: 20px;width: 200px;">
							<el-image style="width: 180px; height: 180px;cursor: pointer;" :src="auction_url_sea">
							</el-image>
						</div>

						<div
							style="margin-top: 0px;margin-left: 20px;font-size: 18px;font-family: 微软雅黑;line-height: 36px;text-indent:2em;">
							等一个自然而然的晴天，
							我想要带你去海边，
							去留住这个瞬间在来不及挽回之前，
							其实不需要深刻的语言，
							趁现在还有一点时间，
							就当是最后一次，
							再一次和我去冒险，
							不经意划过发尾的指尖，
							还有冰镇汽水的甜，
							猜不到你给谁写，
							带着海风的明信片，
							哦可不可以再专心一点 </div>
					</div>
					<div style="height: 20px;"></div>

				</el-dialog>



				<span slot="footer" class="dialog-footer">
					<el-button @click="seaDialogDetail = false">取 消</el-button>
					<el-button type="primary" @click="seaDialogDetail = false">确 定</el-button>
				</span>
				</el-dialog>

				<el-col :span="8">
					<el-row style="margin-top: 50px;">
						<el-carousel class="elcarousel3" indicator-position="none">
							<el-carousel-item class="image3" v-for="item in images3" :key="item.id"
								style="width:200px;height:200px;margin-top:25px;margin-left:30px;">
								<img :src="item.url" width="100%" height="100%" alt="无图片" />
							</el-carousel-item>
						</el-carousel>
					</el-row>
				</el-col>
			</el-row>

		</el-card>




		<div id="SuperSlect">
			<div id="SuperSlect2">
				<el-button style="margin-right:60px;margin-left: 40px;" slot="append" icon="el-icon-search"></el-button>
				<el-select style="width: 150px;margin-right: 60px;" v-model="Supermarket_priceSelect" slot="append"
					placeholder="全部">
					<el-option label="$0-100" value="1"></el-option>
					<el-option label="$100-1000" value="2"></el-option>
					<el-option label="$1000-5000" value="3"></el-option>
					<el-option label="$>5000" value="3"></el-option>
				</el-select>
				<el-select style="width: 150px;margin-right:60px;" v-model="Supermarket_typeSelect" slot="prepend"
					placeholder="全部">
					<el-option label="绘画" value="1"></el-option>
					<el-option label="书法" value="2"></el-option>
					<el-option label="影视" value="3"></el-option>
					<el-option label="摄影" value="4"></el-option>
					<el-option label="音乐" value="5"></el-option>
					<el-option label="模型" value="6"></el-option>
					<el-option label="游戏" value="7"></el-option>
				</el-select>
				<el-select style="width: 150px;margin-right: 60px;" v-model="Supermarket_fieldSelect" slot="prepend"
					placeholder="全部">
					<el-option label="图像" value="1"></el-option>
					<el-option label="音频" value="2"></el-option>
					<el-option label="文件" value="3"></el-option>
					<el-option label="文档" value="3"></el-option>
					<el-option label="实体" value="3"></el-option>
				</el-select>


			</div>


			<el-row>
				<el-col :span="8" style="margin-left: 0px;cursor: pointer;" class="image_place">
					<el-row>
						<div>
							<div id="picture">
								<el-image :src="auction_url_shuilian" style="width: 220px;height: 250px;"></el-image>
							</div>
						</div>
					</el-row>

					<el-row>
						<el-card style="width: 220px;margin-left: 44px;">
							<div style="margin-left: 55px;font-family: agency fb;">
								睡莲|莫奈
							</div>
						</el-card>
					</el-row>
				</el-col>

				<el-col :span="8" style="cursor: pointer;" class="image_place">
					<el-row>
						<div>
							<div id="picture">
								<el-image :src="auction_url_summer" style="width: 220px;height: 250px;"></el-image>
							</div>
						</div>
					</el-row>
					<el-row>
						<el-card style="width: 220px;margin-left: 44px;">
							<div style="margin-left: 36px;font-family: agency fb;">
								夏日晚风|伍佰
							</div>
						</el-card>
					</el-row>
				</el-col>

				<el-col :span="4" style="cursor: pointer;" class="image_place">
					<el-row>
						<div>
							<div id="picture">
								<el-image :src="src" style="width: 220px;height: 250px;"></el-image>
							</div>
						</div>
					</el-row>
					<el-row>
						<el-card style="width: 220px;margin-left: 44px;font-family: agency fb;">
							<div style="margin-left: 20px;">
								想去海边|夏日入侵
							</div>
						</el-card>
					</el-row>
				</el-col>





			</el-row>


		</div>
		<div style="margin-top: 100px;"></div>
	</div>
</template>

<script>
	export default {
		// name: "Carousel",
		data() {
			return {
				// seen1: false,
				// seen2: false,
				// seen3: false,
				falme_url: require("../../assets/Images/logo/flame.png"),
				seaDialogDetail: false,
				seaDialogVisible: false,
				shuiLianDialogDetail: false,
				auction_url_summer: require("../../assets/Images/supermarket/2.png"),
				auction_url_shuilian: require("../../assets/Images/supermarket/shuilian.png"),
				auction_url_sea: require("../../assets/Images/artpicture/sea.png"),
				shuiLianDialogVisible: false,
				Supermarket_priceSelect: '',
				Supermarket_typeSelect: '',
				Supermarket_fieldSelect: '',
				Supermarket_Select: '',
				select: '',
				src: require("../../assets/Images/artpicture/sea.png"),
				images3: [{
						id: 1,
						url: require("../../assets/Images/supermarket/2.png")
					},
					{
						id: 2,
						url: require("../../assets/Images/supermarket/shuilian.png")
					},
					{
						id: 3,
						url: require("../../assets/Images/artpicture/sea.png")
					}
				],
			};
		},
		mounted() {},
		methods: {
		
			openDetail2() {
				this.seaDialogVisible = true;
			},
			openDetail() {
				this.shuiLianDialogVisible = true;
			},
			openDetailForShuiLian() {
				this.shuiLianDialogDetail = true;
			},
			openDetailForSea() {
				this.seaDialogDetail = true;
			}

		}
	};
</script>

<style>
	.product1:hover{
		/* 236,245,255 */
		background-color: #ecf5ff;
		 -webkit-transform: translateY(-3px);
		    -ms-transform: translateY(-3px);
		    transform: translateY(-3px);
			border-radius: 5px;
		    /* -webkit-box-shadow: 0 0 6px #999; */
		    /* box-shadow: 0 0 6px #999; */
		    -webkit-transition: all .5s ease-out;
		    transition: all .5s ease-out;
	}
	.image_place:hover {
		cursor: pointer;
		-webkit-box-shadow: 0 0 30px rgba(0, 0, 0, 0.1);
		box-shadow: 10 10 30px rgba(0, 0, 0, 0.15);
		-webkit-transform: translate3d(0, 0px, -2px);
		transform: translate3d(0, 3px, -2px);

		/* -webkit-transform: translateY(-3px); */
		-moz-transform: translateY(-3px);
		-ms-transform: translateY(-3px);
		/* transform: translateY(-3px); */
	}

	.el-dialog__title {
		font-size: 18px;
		font-family: "微软雅黑";
		/* font-style:italic; */
	}

	.text {
		font-size: 14px;
	}

	/* .item {
	    padding: 18px ;
	  } */


	.box-card {
		width: 70%;
		height: 350px;
		margin-left: 15%;
		margin-right: 15%;


	}

	#SuperSlect {
		margin-left: 15%;
		margin-right: 15%;
		margin-top: 30px;
		/* width: 70%; */
	}
</style>
