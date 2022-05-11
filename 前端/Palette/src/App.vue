<template>
	<div>
		<Top class="top"></Top>
		<Bottom class="bottom"></Bottom>
	</div>
</template>
<script>
	import Top from './components/Top.vue'
	import Bottom from './components/Bottom.vue'
	export default {
		name: 'App',
		components: {
			Top,
			Bottom
		}
	}
</script>

<style>
	#app {
		
		font-family: Avenir, Helvetica, Arial, sans-serif;
		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale;
		text-align: center;
		color: #2c3e50;
		/* margin-top: 0px; */
	}

	.top {
		width: 100%;
		height: 60px;
		background-color: white;
		margin: 0px;
	}

	.bottom {
		width: 100%;
		height:400px;
		background-color: white;
		margin: 0px;
	}

	/* .left{
		width: 20%;
		height: 300px;
		background-color: antiquewhite;
		margin: 0px;
	}
		
	.right{
		width: 80%;
		height: 300px;
		background-color: aquamarine;
		margin: 0px;
	} */
</style>
