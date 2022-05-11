
<template>
	<div class="container">
		<div class="main">

			<div class="box">
				<span style="">智能客服</span>
				<div class="title">
					<img src="" alt class="logo" />
<!-- require("../../../assets/Images/lo001.jpg") -->
				</div>
				<div id="content" class="content">
					<div v-for="(item,index) in info" :key="index">
						<div class="info_r info_default" v-if="item.type == 'leftinfo'">
							<span class="circle circle_r"></span>
							<div class="con_r con_text">
								<div style="margin-left: 12px;margin-bottom: 5px;">{{item.content}}</div>
								<div v-for="(item2,index) in item.question" :key="index">
									<div class="con_que" @click="clickRobot(item2.content,item2.id)">
										<div class="czkj-question-msg">
											{{item2.index}}
											{{item2.content}}
										</div>
									</div>
								</div>
							</div>
							<div class="time_r">{{item.time}}</div>
						</div>

						<div class="info_l" v-if="item.type == 'rightinfo'">
							<div class="con_r con_text">
								<span class="con_l">{{item.content}}</span>
								<!-- <span style="margin-left: 0px;" class="circle circle_l"> -->
									<img style="width: 30px;height:30px;padding-left: 0px;padding-right: 0px;" src="https://img-blog.csdnimg.cn/48a2bc68bb7e4bbc9d48914e2c5b76ea.png" class="pic_l" />
								<!-- </span> -->
							</div>
							<div class="time_l">{{item.time}}</div>
						</div>
					</div>
				</div>

				<div class="setproblem">
					<textarea placeholder="请输入您的问题..."
						style="height: 80px;width: 100%;resize:none;padding-right:80px;outline: none;border-color:#aaaaaa;border-radius:5px;"
						id="text" v-model="customerText" @keyup.enter="sentMsg()">
		 <!-- <button @click="sentMsg()" class="setproblems">
		    <span style="vertical-align: 4px;">发 送</span>
		  </button> -->
		  </textarea>
					<button @click="sentMsg()" class="setproblems">
						<span style="vertical-align: 4px;">发 送</span>
					</button>
				</div>
			</div>
		</div>
	</div>
</template>
<script>
	export default {
		name: "onlineCustomer",
		components: {},
		computed: {},
		data() {
			return {
				customerText: "",
				info: [{
					type: "leftinfo",
					time: this.getTodayTime(),
					name: "robot",
					content: "您好，欢迎使用Palette客服管家，为保证账户安全，在会话过程中请勿透露您的账号！",
					question: [],
				}, ],
				timer: null,
				robotQuestion: [{
						id: 1,
						content: "怎么确保作品的安全性",
						index: 1
					},
					{
						id: 2,
						content: "修改资料审批一直不通过",
						index: 2
					},
					{
						id: 3,
						content: "提现需不需要手续费",
						index: 3
					},
					{
						id: 4,
						content: "注册网站时需要一次填写完所有的客户资料吗",
						index: 4,
					},
					{
						id: 5,
						content: "创作上传审批需要多长时间",
						index: 5
					},
				],
				robotAnswer: [{
						id: 1,
						content: "答案客户资料完善后是由谁来审批,答案客户资料完善后是由谁来审批,答案客户资料完善后是由谁来审批",
						index: 1,
					},
					{
						id: 2,
						content: "测试",
						index: 2
					},
					{
						id: 3,
						content: "",
						index: 3
					},
					{
						id: 4,
						content: "3333333",
						index: 4,
					},
					{
						id: 5,
						content: "审核员将会在一个小时之内进行审核完毕，请耐心等待",
						index: 5
					},
				],
			};
		},
		created() {
			this.showTimer();
		},
		watch: {},
		methods: {
			// 用户发送消息
			sentMsg() {
				clearTimeout(this.timer);
				this.showTimer();
				let text = this.customerText.trim();
				if (text != "") {
					var obj = {
						type: "rightinfo",
						time: this.getTodayTime(),
						content: text,
					};
					this.info.push(obj);
					this.appendRobotMsg(this.customerText);
					this.customerText = "";
					this.$nextTick(() => {
						var contentHeight = document.getElementById("content");
						contentHeight.scrollTop = contentHeight.scrollHeight;
					});
				}
			},
			// 机器人回答消息
			appendRobotMsg(text) {
				clearTimeout(this.timer);
				this.showTimer();
				text = text.trim();
				let answerText = "";
				let flag;
				for (let i = 0; i < this.robotAnswer.length; i++) {
					if (this.robotAnswer[i].content.indexOf(text) != -1) {
						flag = true;
						answerText = this.robotAnswer[i].content;
						break;
					}
				}
				if (flag) {
					let obj = {
						type: "leftinfo",
						time: this.getTodayTime(),
						name: "robot",
						content: answerText,
						question: [],
					};
					this.info.push(obj);
				} else {
					answerText = "您可能想问：";
					let obj = {
						type: "leftinfo",
						time: this.getTodayTime(),
						name: "robot",
						content: answerText,
						question: this.robotQuestion,
					};
					this.info.push(obj);
				}
				this.$nextTick(() => {
					var contentHeight = document.getElementById("content");
					contentHeight.scrollTop = contentHeight.scrollHeight;
				});
			},
			sentMsgById(val, id) {
				clearTimeout(this.timer);
				this.showTimer();

				let robotById = this.robotAnswer.filter((item) => {
					return item.id == id;
				});
				let obj_l = {
					type: "leftinfo",
					time: this.getTodayTime(),
					name: "robot",
					content: robotById[0].content,
					question: [],
				};
				let obj_r = {
					type: "rightinfo",
					time: this.getTodayTime(),
					name: "robot",
					content: val,
					question: [],
				};
				this.info.push(obj_r);
				this.info.push(obj_l);
				this.$nextTick(() => {
					var contentHeight = document.getElementById("content");
					contentHeight.scrollTop = contentHeight.scrollHeight;
				});
			},
			// 点击机器人的单个问题
			clickRobot(val, id) {
				this.sentMsgById(val, id);
			},
			// 结束语
			endMsg() {
				let happyEnding = {
					type: "leftinfo",
					time: this.getTodayTime(),
					content: "退下吧",
					question: [],
				};
				this.info.push(happyEnding);
				this.$nextTick(() => {
					var contentHeight = document.getElementById("content");
					contentHeight.scrollTop = contentHeight.scrollHeight;
				});

			},
			showTimer() {
				this.timer = setTimeout(this.endMsg, 1000 * 60);
			},
			getTodayTime() {
				// 获取当前时间
				var day = new Date();
				let seconds = day.getSeconds();
				if (seconds < 10) {
					seconds = "0" + seconds;
				} else {
					seconds = seconds;
				}
				let minutes = day.getMinutes();
				if (minutes < 10) {
					minutes = "0" + minutes;
				} else {
					minutes = minutes;
				}
				let time =
					day.getFullYear() +
					"-" +
					(day.getMonth() + 1) +
					"-" +
					day.getDate() +
					" " +
					day.getHours() +
					":" +
					minutes +
					":" +
					seconds;
				return time;
			},
		},
		mounted() {},
		props: {},
		destroyed() {},
	};
</script>
<style lang="scss">
	.main {
		// margin-left: 35%;
		width: 100%;
		// width: 900px;
		height: 100%;
		;
		// height: 100px;
		background: linear-gradient(180deg,
				rgba(149, 179, 212, 1) 0%,
				rgba(74, 131, 194, 1) 100%);
		overflow: hidden;

		.box {
			width: 100%;
			height: 100%;
			background-color: #fafafa;
			position: relative;
			padding: 1.25rem;

			#content {
				height: 350px;
				overflow-y: scroll;
				font-size: 14px;
				width: 100%;

				.circle {
					display: inline-block;
					width: 34px;
					height: 34px;
					border-radius: 50%;
					// background-color: #eff1f3;
					background-image: url("../../../assets/Images/icon/customerservice.png");
				}

				//客服字体
				.con_text {
					color: #ffffff;
					margin-bottom: 5px;
					font-size: 15px;
				}

				//客服自定义问题框
				.con_que {
					color: #1c88ff;
					height: 30px;
					line-height: 30px;
					cursor: pointer;
				}

				.info_r {
					position: relative;

					.circle_r {
						position: absolute;
						border-radius: 100%;
						background-color: yellow;
					}

					.pic_r {
						width: 17px;
						height: 17px;
						margin: 8px;
					}

					//客服对话框颜色
					.con_r {
						display: inline-block;
						/* max-width: 253px; */
						width: 55%;
						min-height: 55px;
						/* min-height: 20px; */
						background-color: #8abcde;
						border-radius: 6px;
						padding: 10px;
						margin-left: 40px;
					}

					.time_r {
						margin-left: 45px;
						color: #999999;
						font-size: 12px;
					}
				}

				.info_l {
					text-align: right;
					margin-right: 20px;
					// margin-right: 20px;
					color: #ffffff;
					color: #3163C5;
					margin-top: 10px;

					// .circle_l {
					//   // vertical-align: -10px;
					// }
					.pic_l {
						width: 13px;
						height: 17px;
						margin: 8px 10px;
					}

					.time_l {
						margin-right: 45px;
						color: #999999;
						font-size: 12px;
						margin-top: 5px;
					}

					//用户对话框颜色
					.con_l {
						display: inline-block;
						width: 220px;
						min-height: 51px;
						background-color: #f0a0a8;
						border-radius: 6px;
						padding: 10px;
						text-align: left;
						color: #fff;
						margin-right: 5px;
					}
				}

				#question {
					cursor: pointer;
				}
			}
		}
	}

	.setproblem {
		flex-direction: row;
		display: flex;
		width: 95%;
		height: 90px;
		background-color: #ffffff;
		position: relative;
		margin-top: 35px;

	}

	.setproblem textarea {
		width: 500px;
		font-size: 15px;
		height: 10px;
		color: #999999;
		padding: 10px;
		border-color: brown;
		border: 1px solid;
	}

	.setproblem button {
		width: 3.875rem;
		height: 2.0rem;
		line-height: 2.0rem;
		background: #8abcde;
		opacity: 1;
		border-radius: 4px;
		font-size: 10px;
		color: #ffffff;
		position: absolute;
		right: 1%;
		top: 60%;
		cursor: pointer;
		border: none;
	}

	.czkj-item-title {
		line-height: 25px;
		border-bottom: 1px solid #ccc;
		padding-bottom: 5px;
		margin-bottom: 5px;
	}

	.czkj-item-question {
		cursor: pointer;
		display: block;
		padding: 8px;
		position: relative;
		border-bottom: 1px dashed #ccc;
		line-height: 20px;
		min-height: 20px;
		overflow: hidden;
	}

	.czkj-question-msg {
		float: left;
		font-size: 15px;
		color: #ffffff;
	}
</style>
