<template>
	<div>
		<hr style="width: 1100px;margin: 0 auto;
						border: 0;
						height: 0;
						border-top: 0px solid rgba(0, 0, 0, 0.1);
						border-bottom: 1px solid rgba(255, 255, 255, 0.3);
				" />
		<div style="display: flex ;align-items: center;justify-content: center;margin-top: 30px;size: a3;">
			<img src="../../../../assets/Images/HeadImage/zhangyuge.jpg"
				style="width: 100px;height:100px;border-radius:100px;">
		</div>


		<div style="font-size: 20px;margin-top: 10px;font-weight: 500;">
			<span style="display: flex;align-items: center;justify-content: center;">章鱼哥</span>
		</div>

		<div style="display: flex;align-items: center;justify-content: center;color: lightgrey;margin-top: 30px;">
			他什么也没说......
		</div>

		<div style="display: flex;align-items: center;justify-content: center;margin-top: 20px;">
			<el-row :gutter="15">
				<el-col :span="8">
					<el-card shadow="never">
						<div class="number_text">
							<el-col :span="8">
								7
							</el-col>
						</div>
						<br />
						<div class="text">
							粉丝
						</div>
					</el-card>
				</el-col>
				
				<el-col :span="8">
					<el-card shadow="never">
						<div class="number_text">
							<el-col :span="8">
								8
							</el-col>
						</div>
						<br />
						<div class="text">
							关注
						</div>
					</el-card>
				</el-col>
			 
				<el-col :span="8">
					<el-card shadow="never">
						<div class="number_text">
							<el-col :span="8">
								9
							</el-col>
						</div>
						<br />
						<div class="text">
							藏品
						</div>
					</el-card>
				</el-col>
			</el-row>
		</div>



	</div>
</template>

<script>
</script>

<style>
	.number_text{
		font-size: 20px;
		align-items: center;
	}
	.text{
		font-size: 10px;
		align-items: center;
		color: gray;
	}
</style>
