<template>
	<div>
		<div>
			<!-- 内容盒子 -->
			<div style="width:100%;float:left;margin-left: 2%;">
				<!-- 第一部分用户信息 -->
				<div style="flex-direction: row;display: flex;margin-top: 3%;">
					<!-- 头像 -->
					<div style="flex-direction: row;display: flex;">
						<el-row class="demo-avatar demo-basic">
							<el-col>
								<!-- <div class="sub-title">circle</div> -->
								<div class="demo-basic--circle">
									<div class="block">
										<el-avatar :size="80" :src="circleUrl"></el-avatar>
									</div>
								</div>
							</el-col>
						</el-row>
						<!-- 用户昵称 -->
						<div style="flex-direction: column;display: flex;">
							<div style="flex-direction: row;display: flex;">
								<span style="width: 90px;margin-top: 4%;margin-left: 5%;font-size: 20px;">魔云连洲</span>

								<div
									style="display: flex ;align-items: center;justify-content: center;margin-top: 10px;margin-left: 1px;">
									<el-button type="primary" icon="el-icon-edit"
										style="height: 20px;width: 15px;padding-top: 2px;padding-left: 5px;border-radius: 10%;"
										@click="editorDialogVisible = true"></el-button>
								</div>


							</div>
							<span
								style="width: 270px;margin-top: 10px;margin-left: 5%;font-size: 15px;">电子邮箱：1606941916@qq.com</span>
							<div style="margin-top: 3%;width: 370px;margin-left: 5%;">
								<span style="font-size: 15px;">我关注的&nbsp;</span>
								<span style="color: #ff8181;">10</span>
								<el-divider direction="vertical"></el-divider>
								<span style="font-size: 15px;">&nbsp;粉丝数量&nbsp;</span>
								<span style="color: #ff8181;">10</span>
								<!-- <el-tag size="big" style="margin-left: 20px;font-family: 微软雅黑;">审核官
								</el-tag> -->
								<el-button @click="gotoshenhe" type="text" style=" margin-left: 20px; font-family: 微软雅黑;" >申请审核资质</el-button>
							</div>
						</div>
					</div>
					<!-- 评论、回复 -->
					<div style="flex-direction: row;display: flex;margin-top: 5%;">
						<el-badge :value="12" class="item" style="margin-left: 350px;">
							<el-button size="small">评论</el-button>
						</el-badge>
						<el-badge :value="3" class="item">
							<el-button size="small">回复</el-button>
						</el-badge>
					</div>

				</div>

				<div style="flex-direction: row;display: flex;">
					<div style="margin-top: 12px;height: 32px;border-left: 3.5px solid #3d61ff;"></div>
					<div style="margin-top:18px;margin-left: 10px;font-family:黑体;font-weight: 500;font-size: 20px;">资产管理
					</div>
				</div>

				<hr style="opacity: 15%;margin-top: 10px;margin-bottom: 25px;">

				<div style="flex-direction: row;display: flex;">
					<div class="border_money">
						<span class="text_money" style="margin-top: 0px;">{{money}}</span>
						<span style="margin-top: 10px;font-size: 14px;">我的喵凸币</span>
					</div>
					<div class="border_money" style="margin-left: 60px;">
						<span class="text_money">¥</span>
						<span class="text_title" style="margin-top: 10px;font-size: 14px;">提现余额</span>
					</div>

					<div class="border_money" style="margin-left: 60px;" @mouseleave="leaveSendMoney"
						@mouseenter="enterSendMoney">
						<span class="text_money">0</span>
						<span class="text_title" style="margin-top: 10px;font-size: 15px;">转账记录</span>
						<div v-show="seenSendMoney"
							style="margin-top: 10px;width: 30px;margin-left: 36%;border-bottom: 3.5px solid #3d61ff;">
						</div>
					</div>
					<div class="border_money" style="margin-left: 60px;" @mouseleave="leaveMyComponet"
						@mouseenter="enterMyComponet">
						<span class="text_money">1</span>
						<span class="text_title" style="margin-top: 10px;font-size: 15px;">我的创作</span>
						<div v-show="seenMyComponet"
							style="margin-top: 10px;width: 30px;margin-left: 36%;border-bottom: 3.5px solid #3d61ff;">
						</div>
					</div>
					<div class="border_money" style="margin-left: 60px;" @mouseleave="leaveMyOrder"
						@mouseenter="enterMyOrder">
						<span class="text_money">1</span>
						<span class="text_title" style="margin-top: 10px;font-size: 15px;">我的订单</span>
						<div v-show="seenMyOrder"
							style="margin-top: 10px;width: 30px;margin-left: 36%;border-bottom: 3.5px solid #3d61ff;">
						</div>
					</div>
					<div style="flex-direction: column;display: flex;margin-left: 50px;margin-top: 10px;">
						<div>
							<el-button type="primary" style="font-size: 15px;width: 100px;"
								@click="TopupDialogVisible = true">
								充值</el-button>
						</div>
						<div>
							<el-button type="primary" style="font-size: 15px;margin-top: 10px;width: 100px;"
								@click="DepositdialogVisible = true">提现</el-button>
						</div>
					</div>
				</div>

				<div style="flex-direction: row;display: flex;margin-top: 10px;">
					<div style="margin-top: 12px;height: 32px;border-left: 3.5px solid #3d61ff;"></div>
					<div style="margin-top:18px;margin-left: 10px;font-family:黑体;font-weight: 500;font-size: 20px;">账户管理
					</div>
				</div>

				<hr style="opacity: 15%;margin-top: 10px;margin-bottom: 25px;">

				<div class="function_item">

					<div class="border_testify" style="margin-left: 0px;">
						<span>
							<el-link :underline="false" style="font-size: 18px;font-weight: bold;">实名认证</el-link>
						</span>
					</div>

					<div class="border_testify" style="margin-left: 60px;">
						<span>
							<el-link :underline="false" style="font-size: 18px;font-weight: bold;">更换手机</el-link>
						</span>
					</div>
					<div class="border_testify" style="margin-left: 60px;">
						<span>
							<el-link :underline="false" style="font-size: 18px;font-weight: bold;">修改密码</el-link>
						</span>
					</div>
				</div>


				<!-- <el-empty description="暂无专辑信息" ></el-empty> -->

				<!-- 编辑框 -->
				<el-dialog title="编辑资料" :visible.sync="editorDialogVisible" width="28%" :before-close="handleClose">
					<el-form>
						<div style="margin-left: 30px;">
							<div>
								<div style="margin-top: 5px;margin-left: 130px;flex-direction: column;display: flex;">
									<el-avatar :size="80" :src="circleUrl" sytle="margin-left:80px;"></el-avatar>
								</div>
								<el-link :underline="false" type="primary"
									style="font-size: 15px;margin-top: 5px;margin-left: 110px;margin-bottom: 5px;">
									点击重新上传图像
								</el-link>
							</div>

							<el-form-item label="新用户名:">
								<el-input style="width: 220px" v-model="newUserName" placeholder="请输入新用户名"></el-input>
							</el-form-item>
							<el-form-item label="作品简介:">
								<el-input style="width: 220px ; height=50px" v-model="textInput" type="textarea"
									placeholder="字数不多于150字" maxlength="150" show-word-limit></el-input>
							</el-form-item>

							<el-form-item label="电子邮箱:">
								<el-input style="width: 220px" v-model="newEmail" placeholder="请输入新邮箱"></el-input>
							</el-form-item>

							<div style="margin-left: 70px;margin-top: 20px;">
								<span>
									<el-button @click="editorDialogVisible = false" style="margin-right: 30px;">取 消
									</el-button>
									<el-button type="primary" @click="open2">确 定</el-button>
									<!-- <el-button type="primary" :loading="true">加载中</el-button> -->
								</span>
							</div>
						</div>
					</el-form>
				</el-dialog>

				<!-- 充值弹框 -->
				<el-dialog title="充值" :visible.sync="TopupDialogVisible" width="30%" :before-close="handleClose">
					<div>
						<span>充值金额</span>
						<el-input clearable v-model="addinput" placeholder="" style="width: 200px;margin-left: 30px;">
						</el-input>
					</div>
					<p>
					<div>
						<span>选择支付方式</span>

						<span style="margin-left: 30px;">
							<el-radio v-model="radio" label="1">微信</el-radio>
							<el-radio v-model="radio" label="2">支付宝</el-radio>
						</span>
					</div>

					<span slot="footer" class="dialog-footer">
						<el-button @click="TopupDialogVisible = false">取 消</el-button>
						<el-button type="primary" @click="openerweima()">确 定</el-button>
					</span>
				</el-dialog>


				<!-- 二维码 -->
				<el-dialog :visible.sync="erweimaDialogVisible" center :before-close="handleClose"
					style="width:400px;margin-left: 500px;">
					<div>
						<el-image style="width: 150px; height: 190px;cursor: pointer;" :src="erwerma"></el-image>
						<!-- <img style="height: 100%;width: 100%;" v-bind:src="erwerma" alt=""/> -->
					</div>
				</el-dialog>




				<!-- 提现弹框 -->
				<el-dialog title="提现" :visible.sync="DepositdialogVisible" width="30%" :before-close="handleClose">
					<div>
						<span>提现金额</span>
						<el-input clearable v-model="input" placeholder="" style="width: 200px;margin-left: 30px;">
						</el-input>
					</div>
					<p>
					<div>
						<span>选择提现渠道</span>
						<span style="margin-left: 30px;">
							<el-radio v-model="radio" label="1">支付宝</el-radio>
							<el-radio v-model="radio" label="2">微信</el-radio>

						</span>
					</div>

					<span slot="footer" class="dialog-footer">
						<el-button @click="DepositdialogVisible = false">取 消</el-button>
						<el-button type="primary">确 定</el-button>
					</span>
				</el-dialog>

				<div style="margin-top: 20px;">
					<el-skeleton :rows="3" />
				</div>

			</div>
		</div>

	</div>
</template>

<style>
	.el-dialog {
		display: flex;
		flex-direction: column;
		margin: 0 !important;
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
	}

	.el-dialog .el-dialog__body {
		flex: 1;
		overflow: auto;
	}

	.border_money {
		cursor: pointer;
		text-align: center;
		width: 120px;
		height: 30px;
		/* margin-left: 15%; */
		/* margin-right: 35%; */
		border-radius: 10px;
		/* margin-top: 50%; */
		padding-top: 3%;
		padding-bottom: 5%;
		line-height: 25px;
		color: #000000;
		flex-direction: column;
		display: flex;
		box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
	}

	/* 账户管理样式 */
	.border_testify {
		text-align: center;
		width: 120px;
		height: 10px;
		/* margin-left: 15%; */
		/* margin-right: 35%; */
		border-radius: 10px;
		/* margin-top: 50%; */
		padding-top: 3%;
		padding-bottom: 3%;
		line-height: 15px;
		color: #000000;
		flex-direction: column;
		display: flex;
		box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
	}



	.text_money {
		/* font-weight: bold; */
		font-size: 18px;
		color: #5093ff;
	}


	/* 功能框 */
	.function_item {
		flex-direction: row;
		display: flex;
	}

	.item {
		margin-top: 10px;
		margin-right: 40px;
	}
</style>


<script>
	export default {
		data() {
			return {
				money: '0',
				erwerma: require("../../../assets/Images/pay002.png"),
				addinput: '',
				erweimaDialogVisible: false,
				newUserName: '',
				textInput: '',
				newEmail: '',
				editorDialogVisible: false,
				circleUrl: "https://img-blog.csdnimg.cn/48a2bc68bb7e4bbc9d48914e2c5b76ea.png",
				radio: '1',
				DepositdialogVisible: false,
				TopupDialogVisible: false,
				input: '',
				seenSendMoney: false,
				seenMyComponet: false,
				seenMyOrder: false
			}
		},
		methods: {
			// 去审核
			gotoshenhe(){
				this.$router.replace('/checkpeopel');
			},
			handleClose(done) {
				this.$confirm('确认关闭？')
					.then(_ => {
						done();
					})
					.catch(_ => {});
				// this.openerweima();
			},
			open2() {
				this.editorDialogVisible = false;
				this.$message({
					showClose: true,
					message: '修改成功！',
					type: 'success'
				});
			},
			//获取充值二维码
			openerweima() {
				this.erweimaDialogVisible = true;
				this.TopupDialogVisible = false;

				this.addpay();
				// clearTimeout(this.timer);  //清除延迟执行 

				this.timer = setTimeout(() => { //设置延迟执行
					console.log('ok');
					this.erweimaDialogVisible = !this.erweimaDialogVisible;

				}, 5000);
				console.log("66666666666")
			},
			//获取余额
			getBalance2() {
				// this.money=3000;
				var that = this
				this.mytoken = localStorage.getItem('token')
				console.log(this.mytoken)
				this.$axios.post(
						this.apiUrl + "/palette/user/getBalance", {
							token: this.mytoken,
						}
					)
					.then(response => {
						if (response.data.code == 200) {
							console.log("余额：" + response.data.data.balance);
							this.money = response.data.data.balance;
						} else {

						}
					})
					.catch(function(error) {});

			},
			//充值接口
			addpay() {
				var that = this
				this.mytoken = localStorage.getItem('token')
				console.log(this.addinput)
				this.$axios.post(
						this.apiUrl + "/palette/user/chargeMTB", {
							token: this.mytoken,
							MTBAmount: this.addinput,
							chargeEvidence: "lll",
						}
					)
					.then(response => {
						if (response.data.code == 200) {
							// console.log("余额：" + response.data.data.balance);
							// this.money = response.data.data.balance;
							console.log("充值成功");
						} else {

						}
					})
					.catch(function(error) {});

			},
			enterSendMoney() {
				this.seenSendMoney = true;
			},
			leaveSendMoney() {
				this.seenSendMoney = false;
			},
			enterMyComponet() {
				this.seenMyComponet = true;
			},
			leaveMyComponet() {
				this.seenMyComponet = false;
			},
			enterMyOrder() {
				this.seenMyOrder = true;
			},
			leaveMyOrder() {
				this.seenMyOrder = false;
			}

		},
		created() {
			this.getBalance2();
		}
	}
</script>
