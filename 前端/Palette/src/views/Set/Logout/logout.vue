<template>
	<div style=" display: flex ;align-items: center;justify-content: center;margin-top: 200px;">
		<el-button @click="exit" type="danger" plain>退出登录</el-button>
		<!-- <button >退出登录</button> -->
	</div>
</template>

<script>
	import {
		mapMutations
	} from 'vuex';

	export default {
		name: "manager_infor",
		data() {
			return {};
		},

		methods: {

			...mapMutations(['delToken']),
			exit() {
				var that = this
				this.delToken({
					token: ''
				});
				location.reload();
				this.$router.push('/');
				// this.$router.push('/managerLogin');

			}
		}
	};
</script>
