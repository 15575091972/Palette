<template>
	<div  style="display: flex;flex-direction: row;">
			<Left class="left"></Left>
			<Right class="right"></Right>
	</div>
</template>

<style>
	.left {
		background-color: white;
	}

	.right {
		/* background-color: palevioletred; */
		/* margin-left: 160px; */
	}
</style>


<script>
	import Left from '../../../components/personComponents/Left.vue'
	import Right from '../../../components/personComponents/Right.vue'
	export default {
		name: 'App',
		components: {
			Left,
			Right
		}
	}
</script>
