<template>
	<div>

		<!-- 	<div>
			
		</div> -->

		<div class="examination">
			<el-card style="margin-left: 23%;margin-right: 23%;">
				<p
					style=" display: flex ;align-items: center;justify-content: center;font-family: 微软雅黑;font-size: 20px;">
					审核官答题测试</p>
				<ul style="margin-top: 30px;" v-for="(item,i) in examinationData" :key="i">
					<div>{{i+1}}.{{item.question}}</div>
					<li style="list-style-type: none;" v-for="(son,index) in item.answer" :key="index">
						<el-radio v-model="radio[i]" :label="son.value" @change="getIputValue(i)">
						</el-radio>
					</li>
				</ul>

				<el-button @click="daticlose()" style="margin-left: 500px;" type="success" size="medium">提交</el-button>

				<el-dialog :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
					<div>
						<span style="font-size: 16px;font-family: 微软雅黑;">
							合格分数：
						</span>
						<span style="font-size: 16px;font-family: 微软雅黑;">
							60分
						</span>
					</div>
					<div style="margin-top: 20px;">
						<span style="font-size: 16px;font-family: 微软雅黑;">你的成绩：</span>
						<span style="font-size: 28px;color: red;">80分</span>
					</div>
					<div style="font-size: 16px;margin-top: 30px;  font-family: 微软雅黑;">恭喜通过测评，您将成为一名大众审核员！！！</div>
					<span slot="footer" class="dialog-footer">
						<!-- <el-button @click="dialogVisible = false">取 消</el-button> -->
						<el-button type="primary" size="small" @click="gotopreson()">确 定</el-button>
					</span>
				</el-dialog>
			</el-card>
		</div>


		<!-- 考试 -->
	</div>
</template>

<script>
	export default {

		data() {
			return {
				dialogVisible: false,
				examinationData: [{
						id: "1",
						name: "one",
						question: "如果你在另一个网站发现了本艺术品，你会怎么做？",
						answer: [{
								value: "A.不予通过"
							},
							{
								value: "B.联系卖主"
							},
							{
								value: "C.给予通过 "
							},
							
						]
					},
					{
						id: "1",
						name: "two",
						question: "什么是评判高质量的艺术品最重要的标准？",
						answer: [{
								value: "A.画风轻快"
							},
							{
								value: "B.色彩绚烂 "
							},
							{
								value: "C.让人耳目一新 "
							}
							
						]
					},
					{
						id: "1",
						name: "three",
						question: "艺术品上架最基本的要求是什么？",
						answer: [{
								value: "A.不涉及违法及不良内容"
							},
							{
								value: "B.一定要是艺术家创作"
							},
							{
								value: "C.作者在艺术圈内有名气"
							}
							
						]
					},
					{
						id: "1",
						name: "four",
						question: "如果有卖主想让你帮其上架艺术品你会怎么做？",
						answer: [{
								value: "A.欣然答应"
							},
							{
								value: "B.委婉拒绝,秉公执法"
							},
							{
								value: "C.暗示其需要好处"
							}
							
						]
					},
					{
						id: "1",
						name: "five",
						question: "如何判断本网站是否已有此艺术品收录，下列哪一项不正确？",
						answer: [{
								value: "A.关键词搜索 "
							},
							{
								value: "B.凭直觉"
							},
							{
								value: "C.按类别查询"
							},
							
						]
					},
					{
						id: "1",
						name: "six",
						question: "若发现艺术品上传信息明显错误，下列做法错误的是？",
						answer: [{
								value: "A.反馈给网站管理员 "
							},
							{
								value: "B.不予通过并写出理由"
							},
							{
								value: "C.不予理会，直接通过"
							},
							
						]
					},
					{
						id: "1",
						name: "seven",
						question: "什么是评判高质量的艺术品最重要的标准？",
						answer: [{
								value: "A.画风轻快"
							},
							{
								value: "B.色彩绚烂 "
							},
							{
								value: "C.让人耳目一新 "
							}
							
						]
					},
					{
						id: "1",
						name: "eigtn",
						question: "艺术品上架最基本的要求是什么？",
						answer: [{
								value: "A.不涉及违法及不良内容"
							},
							{
								value: "B.一定要是艺术家创作"
							},
							{
								value: "C.作者在艺术圈内有名气"
							}
							
						]
					},
				],
				allRadio: [], //提交给后台的真题数据
				radio: [] //单选真题答案
			}
		},
		methods: {

			daticlose() {
				this.dialogVisible = true;
			},

			gotopreson() {
				this.dialogVisible = false;
				this.$router.replace('/personalCenter/dynamicIssue');
			},
			handleClose(done) {
				this.$confirm('确认关闭？')
					.then(_ => {
						done();
						this.$router.replace('/personalCenter/dynamicIssue');
					})
					.catch(_ => {});
			},
			getIputValue(index) {
				// console.log(index + 1 + "题" + this.radio);
				this.allRadio[index] = this.radio[index]; // 将数据存入提交给后台的数据中
				console.log(this.allRadio);
				// console.log(this.radio)
			}

		}

	}
</script>

<style>
	/* //使用css样式穿透,input框的样式就没了 */
	.paperview-input-text>>>.el-input__inner {
		/* -webkit-appearance: none;
	  background-color: #fff;
	  background-image: none;
	  border-radius: 4px; */
		border: 5px;
		width: 100%;
	}
