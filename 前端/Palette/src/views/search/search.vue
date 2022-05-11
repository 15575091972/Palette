<template>
	<div style="margin-left: 5%;margin-right: 5%;">
		<div v-for="v in searchList" :key="v.value" style="margin-top: 30px;" @click="gotogoodspage(v.simpleArt.artId)">
			<el-col :span="5" style="margin-left: 3%;" class="image__place">
				<el-card :body-style="{ padding: '15px' }" style="height: 320px;">
					<div id="homeimage" >
						<el-image v-bind:src="apiUrl+v.simpleArt.showImage" alt=""> </el-image>
					</div>
					<h4>{{v.simpleArt.artName}}</h4>
					<p>简介:{{v.simpleArt.artIntroduction}}</p>
				</el-card>
			</el-col>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {

				reginForm: {
					rgin_username: '',
					regin_password: '',
					regin_password2: '',
					regin_payKey: ''

				},
				input01: '琉璃盏',
				searchList: [],
			};
		},
		methods: {


			getsearchList() {
				// var that = this

				//console.log(input01);
				this.$axios.post(
						this.apiUrl+"/palette/art/getArtFuzzy", {
							fuzzyStatement: this.$route.query.input
						}
					)
					.then(response => {
						if (response.data.code == 200) {
							this.searchList=response.data.data.goods;
							 console.log(response.data.data.goods);
							console.log("搜索成功");
						} else {
							// alert("88888")
						}
					})
					.catch(function(error) {});

			},
			gotogoodspage(id) {
				console.log("goods" + id);
				this.$router.push({
					path: '/productdetails',
					query: {
						details: id
					}
				});
			},

		},

		created() {
			this.getsearchList();
			 // console.log(this.$route.query.input);
		}
	}
</script>
</script>

<style>
</style>
