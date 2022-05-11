<template>
	<div>
		<hr style="width: 1100px;margin: 0 auto;
						border: 0;
						height: 0;
						border-top: 0px solid rgba(0, 0, 0, 0.1);
						border-bottom: 1px solid rgba(255, 255, 255, 0.3);
				" />
		<div style="display: flex ;align-items: center;justify-content: center;margin-top: 70px;size: a3;">
			<el-avatar :size="100" :src="touxiangUrl"></el-avatar>
		</div>

		<div style="font-size: 20px;margin-top: 7px;font-weight: 500;">
			<span style="display: flex;align-items: center;justify-content: center;">大白</span>

		</div>
		<div style="display: flex;align-items: center;justify-content: center;color: lightgrey;margin-top: 30px;">
			苟富贵勿相忘
		</div>
		<div style="display: flex;align-items: center;justify-content: center;margin-top: 20px;">
			<span>
				电子邮箱:
			</span>
			<span>
				********5847@qq.com
			</span>
		</div>

		<div style="display: flex ;align-items: center;justify-content: center;margin-top: 20px;">
			<el-button type="primary" icon="el-icon-edit" style="height: 34px;" @click="editorDialogVisible = true">
			</el-button>
		</div>

		<!-- 编辑框 -->
		<el-dialog title="编辑资料" :visible.sync="editorDialogVisible" width="30%" :before-close="handleClose">
			<el-form>

				<div style="margin-left: 30px;">
					<el-form-item style="margin-top: 20px;">
						<el-avatar :size="50" :src="touxiangUrl"></el-avatar>
						<span >
							点击重新上传头像
						</span>
					</el-form-item>

					<el-form-item label="新用户名:">
						<el-input style="width: 220px" v-model="input" placeholder="请输入新用户名"></el-input>
					</el-form-item>
					<el-form-item label="作品简介:">
						<el-input style="width: 220px ; height=50px  " type="textarea" placeholder="字数不多于150字"
							maxlength="150" show-word-limit></el-input>
					</el-form-item>

					<el-form-item label="电子邮箱:">
						<el-input style="width: 220px" v-model="input" placeholder="请输入新邮箱"></el-input>
					</el-form-item>

					<div style="margin-left: 70px;margin-top: 20px;">
						<span>
							<el-button @click="editorDialogVisible = false">取 消</el-button>
							<el-button type="primary" @click="editorDialogVisible = false">确 定</el-button>
						</span>
					</div>
				</div>
			</el-form>
		</el-dialog>


	</div>
</template>

<script>
	export default {
		data() {
			return {
				touxiangUrl: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
				input: '',
				textarea: '',
				editorDialogVisible: false,
			};
		},
		methods: {
			handleClose(done) {
				this.$confirm('确认关闭？')
					.then(_ => {
						done();
					})
					.catch(_ => {});
			}
		}
	};
</script>

<style>
</style>
