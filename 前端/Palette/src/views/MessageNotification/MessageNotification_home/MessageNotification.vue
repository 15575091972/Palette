<template>
	<div style="display: flex;flex-direction: row;"> 
			<Left class="left"></Left>

			<Right class="right"></Right>
	</div>
</template>

<style>
	.left {
		background-color: white;
	}

	.right {
		background-color: white;
		
		width: 100%;
	}
</style>


<script>
	import Left from '../../../components/MessageNotificationComponents/Left.vue'
	import Right from '../../../components/MessageNotificationComponents/Right.vue'
	export default {
		name: 'App',
		components: {
			Left,
			Right
		}
	}
</script>
