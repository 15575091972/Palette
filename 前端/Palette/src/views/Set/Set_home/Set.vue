<template>
	
	<div style="display: flex;flex-direction: row;"> 
			<Left class="left"></Left>
			<Right class="right"></Right>
	</div>
</template>

<style scoped>
/* 	html,	
	body{
		width: 100%;
	} */
	.left {
		background-color: white;
		width: 15%;
	}

	.right {
		background-color: white;
		width: 85%;
	}
</style>


<script>
	import Left from '../../../components/SetComponents/Left.vue'
	import Right from '../../../components/SetComponents/Right.vue'
	export default {
		name: 'App',
		components: {
			Left,
			Right
		}
	}
</script>
