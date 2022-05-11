<template>
	<div>
		<div style="font-size: 20px;margin-top: 4px;font-weight: 500;">
			<span style="display: flex;align-items: center;justify-content: center;">我的虎虎币</span>
			<span id="MT_Turnover">资产流转记录</span>

		</div>


		<hr style="width: 1100px;margin: 0 auto;
						border: 0;
						height: 0;
						border-top: 1px solid rgba(0, 0, 0, 0.1);
						border-bottom: 1px solid rgba(255, 255, 255, 0.3);
				" />
		<div>
			<div style="display: flex ;align-items: center;justify-content: center;">
				<el-button type="text" style="font-size: 15px;" @click="TopupDialogVisible = true">充值</el-button>
			</div>
			<hr style="width: 1100px;margin: 0 auto;
							border: 0;
							height: 0;
							border-top: 1px solid rgba(0, 0, 0, 0.1);
							border-bottom: 1px solid rgba(255, 255, 255, 0.3);
					" />
		</div>
		<div>
			<div style="display: flex ;align-items: center;justify-content: center;">
				<el-button type="text" style="font-size: 15px;" @click="DepositdialogVisible = true">提现</el-button>
			</div>
			<hr style="width: 1100px;margin: 0 auto;
							border: 0;
							height: 0;
							border-top: 1px solid rgba(0, 0, 0, 0.1);
							border-bottom: 1px solid rgba(255, 255, 255, 0.3);
					" />
		</div>



		<!-- 充值弹框 -->
		<el-dialog title="充值" :visible.sync="TopupDialogVisible" width="30%" :before-close="handleClose">
			<div>
				<span>充值金额</span>
				<el-input clearable v-model="input" placeholder="" style="width: 200px;margin-left: 30px;">
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
				<el-button type="primary" @click="TopupDialogVisible = false">确 定</el-button>
			</span>
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
					<el-radio v-model="radio" label="1">微信</el-radio>
					<el-radio v-model="radio" label="2">支付宝</el-radio>
				</span>
			</div>
			
			<span slot="footer" class="dialog-footer">
				<el-button @click="DepositdialogVisible = false">取 消</el-button>
				<el-button type="primary" @click="DepositdialogVisible = false">确 定</el-button>
			</span>
		</el-dialog>

	</div>
</template>

<script>
	export default {
		data() {
			return {
				radio: '1',
				DepositdialogVisible: false,
				TopupDialogVisible: false,
				input: ''
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
	#MT_RechargeButton {

		color: red;
		background-color: #ffaa7f;
	}


	#MT_WithdrawalButton {

		color: green;
		background-color: #e2e4ff;
	}



	#MT_Turnover {
		margin-left: 90%;
		font-size: 12px;
		color: #6ab2ff;
	}
</style>
