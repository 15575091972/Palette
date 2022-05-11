<template>
	<div>

		<el-drawer :visible.sync="drawer" :with-header="false" :before-close="handleClose">
			<div style="flex-direction: row;display: flex;margin-top: 5px;">
				<img src="../../assets/Images/logo/p.png"
					style="width: 35px;height:35px;margin-left: 10px;margin-top: 10px;">
				<div class="palette_style">即刻创作</div>
			</div>
			<hr style="margin-top:16px;
										border: 0;
										height: 0;
										border-top: 1px solid rgba(0, 0, 0, 0.1);
										border-bottom: 1px solid rgba(255, 255, 255, 0.3);
								" />
			<div class="form2" style="margin-left: 0px;">
				<el-form class="form" ref="form" :model="uploadform" label-width="100px">
					<el-form-item label="作品名称:">
						<el-input style="width: 250px;" v-model="uploadform.name" placeholder="请输入作品名称"></el-input>
					</el-form-item>

					<el-form-item label="第一作者:">
						<el-input style="width: 250px;" v-model="uploadform.auther" placeholder=""></el-input>
					</el-form-item>


					<el-form-item label="所属领域:" style="margin-top: 30px;">
						<el-select style="width: 250px;" v-model="uploadform.artType" placeholder="请选择作品领域" clearable>
							<el-option label="绘画" value="1"></el-option>
							<el-option label="书法" value="2"></el-option>
							<el-option label="摄影" value="3"></el-option>
							<el-option label="音乐" value="4"></el-option>
							<el-option label="游戏" value="5"></el-option>
							<el-option label="模型" value="6"></el-option>
							<el-option label="影视" value="7"></el-option>
							<el-option label="其他" value="8"></el-option>
						</el-select>
					</el-form-item>

					<!-- 			<el-form-item label="所属专辑:" style="margin-top: 30px;">
						<el-input style="width: 250px" v-model="uploadform.zhuanji"></el-input>

					</el-form-item> -->

					<el-form-item label="作品简介:" class="jianjie" style="margin-top: 30px;">
						<el-input :rows="6" style="width: 250px" type="textarea" placeholder="字数不多于150字" maxlength="150"
							show-word-limit v-model="uploadform.artIntroduction"></el-input>
					</el-form-item>
					<el-form-item label="密码:">
						<el-input style="width: 220px" show-password v-model="uploadform.password"
							placeholder="请输入交易密码"></el-input>
					</el-form-item>

					<el-form-item label="">
						<!-- <el-upload class="upload-demo" ref="upload" action :http-request="httpRequest" list-type="picture-card"
										:auto-upload="false">
										<i slot="default" class="el-icon-plus" :before-upload="beforeUpload" :on-exceed="handleExceed"
											:limit="1"></i> -->

						<!-- <div slot="file" slot-scope="{file}">
					
											<img class="el-upload-list__item-thumbnail" :src="file.url" alt="">
					
											<span class="el-upload-list__item-actions">
					
												<span class="el-upload-list__item-preview" @click="handlePictureCardPreview(file)">
													<i class="el-icon-zoom-in"></i>
												</span>
					
												<span v-if="!disabled" class="el-upload-list__item-delete" @click="handleRemove(file)">
													<i class="el-icon-delete"></i>
												</span>
					
											</span>
										</div> -->
						<!-- </el-upload> -->
						<!-- <el-dialog :visible.sync="dialogVisible">
										<img width="100%" :src="dialogImageUrl" alt="">
									</el-dialog> -->


						<!-- 	<el-upload class="upload-demo" ref="upload" :show-file-list="false" action :http-request="httpRequest"
										:before-upload="beforeUpload" :on-change="handleChange" :auto-upload="true" :data="ruleForm">
										<img v-if="ruleForm.imageUrl" :src="ruleForm.imageUrl" class="avatar">
										<i v-else class="el-icon-plus avatar-uploader-icon"></i>
									</el-upload>
					 -->
						<el-upload class="upload-demo" ref="upload" action :http-request="httpRequest"
							:before-upload="beforeUpload" :on-exceed="handleExceed" :limit="1">
							<el-button slot="trigger" size="small" type="primary">选取文件</el-button>

							<el-button style="margin-left: 80px;" size="small" type="success" @click="uploadFileFun">
								上传作品
							</el-button>
						</el-upload>
					</el-form-item>

				</el-form>
			</div>
		</el-drawer>


	</div>
</template>

<script>
	export default {
		data() {
			return {
				//token
				mytoken: '',

				// dialogImageUrl: '',
				// dialogVisible: false,
				// disabled: false,

				uploadform: {
					name: '',
					artType: '',
					auther: '',
					artIntroduction: '',
					password: ''

				},

				drawer: true,
				//存放上传文件
				fileList: [],

			}

		},
		methods: {

			handleClose() {
				this.drawer = false;
			},
			// 	console.log(file);
			// },
			//预览图片
			// handlePictureCardPreview(file) {
			// 	this.dialogImageUrl = file.url;
			// 	this.dialogVisible = true;
			// },

			// handleChange(file, fileList) {
			// 	this.uploadFiles = fileList
			// },

			// handleRemove(file) {
			// 	console.log(file);
			// },
			// onSubmit() {
			// 	console.log('submit!');
			// },




			// 覆盖默认的上传行为，可以自定义上传的实现，将上传的文件依次添加到fileList数组中,支持多个文件
			httpRequest(option) {
				this.fileList.push(option)
			},
			// 上传前处理
			beforeUpload(file) {
				let fileSize = file.size
				const FIVE_M = 20 * 1024 * 1024;
				//大于20M，不允许上传
				if (fileSize > FIVE_M) {
					this.$message.error("最大上传10M")
					return false
				}
				// //判断文件类型，必须是xlsx格式
				// let fileName = file.name
				// // let reg = /^.+(\.xlsx)$/
				// // if (!reg.test(fileName)) {
				// // 	this.$message.error("只能上传xlsx!")
				// // 	return false
				// // }
				return true
			},
			// 文件数量过多时提醒
			handleExceed() {
				this.$message({
					type: 'error',
					message: '最多支持1个附件上传'
				})
			},

			//导入Excel病种信息数据
			uploadFileFun() {

				this.mytoken = localStorage.getItem('token')
				// 使用form表单的数据格式
				const params = new FormData()
				// 将上传文件数组依次添加到参数paramsData中
				this.fileList.forEach((x) => {
					params.append('file', x.file)
				});
				params.append('artName', this.uploadform.name)
				params.append('artType', this.uploadform.artType)
				params.append('artAuthor', this.uploadform.auther)
				params.append('artIntroduction', this.uploadform.artIntroduction)
				params.append('token', this.mytoken)
				params.append('payKey', this.uploadform.password)
				var that = this
				this.$axios.post(
						this.apiUrl + "/palette/artManagement/uploadArt", params
					)
					.then(response => {
						if (response.data.code == 200) {
							this.$message({
								message: '上传成功',
								type: 'success'
							});
							this.drawer = false;
							console.log('上传成功');

							// that.$refs.uploadform.resetFields();
							// this.$refs.addbarbersFormRef.resetFields();

						} else {
							console.log(response.data);
							this.$message({
								type: 'info',
								message: '上传失败'
							});
						}

						// this.$message({
						// 	type: 'info',
						// 	message: '上传成功'
						// });
						// this.drawer = false;
						// console.log('上传成功');
					})
					.catch(function(error) {});
			}
		},



		created() {
			// this.Global.instantCreateDrawer = false;	
			// this.drawer = this.Global.instantCreateDrawer;

		}
	}
</script>

<style>
	<style>

	/* 文本框的高度 */
	.el-input__inner {
		height: 40px;
	}

	.form {
		margin-top: 20px;
		/* text-align:center; */
	}

	.from2 {
		width: 200px;
	}

	/* 表单label的样式 */
	.jianjie .el-form-item__label {
		font-size: 14px;
		font-family: 微软雅黑;
	}

	/* 表单label的样式 */
	.el-form-item__label {
		/* margin-top: 5px; */
		font-size: 14px;
		font-family: 微软雅黑;
	}


	.palette_style {
		font-family: 微软雅黑;
		margin-top: 20px;
		font-weight: 600;
		font-size: 15px;
	}
</style>
