<template>
	<div>
		<div style="margin-left: 5%;margin-right: 5%;">
			<el-row>
				<el-col :span="15">
					<el-carousel class="elcarousel1" indicator-position="none">
						<el-carousel-item class="image" v-for="item in images1" :key="item.id">
							<img :src="item.url" width="100%" height="100%" alt="无图片" />
						</el-carousel-item>
					</el-carousel>

				</el-col>


				<el-col :span="8" :offset="1">
					<div class="block">
						<el-carousel class="elcarousel2" height="150px">
							<el-carousel-item v-for="item in images2" :key="item.id">
								<img :src="item.url" width="100%" height="100%" alt="无图片" style="" />
							</el-carousel-item>
						</el-carousel>
					</div>


					<el-row>
						<el-button id="createbutton" @click="gotoinstaltyCreate" style="color: white;">创作</el-button>
					</el-row>

					<el-row>
						<el-col :span="12">
							<el-button id="shopping" @click="gotoshopping_trollery">
								购物车

							</el-button>
						</el-col>
						<el-col :span="12">
							<el-button id="find" @click="gotoshopping_trollery">发现</el-button>
						</el-col>
					</el-row>

				</el-col>
			</el-row>



			<!-- 绘画-->
			<el-row>
				<div style="margin-top: 30px;">
					<span>
						<font style="margin-left: 45px;font-size: 28px;font-family: 华文新魏;">绘画</font>
					</span>
					<el-button @click="gotopaint()" type="text" style="margin-left: 57%;">更多></el-button>
				</div>

				<div class="item" v-for="v in PaintPictureList" :key="v.value" @click="gotogoodspage(v.artId)">
					<el-col :span="5" style="margin-left: 3%;" class="image__place">
						<el-card :body-style="{ padding: '15px' }" style="height:335px">
							<div id="homeimage">
								<el-image v-bind:src="apiUrl+v.showImage" alt=""> </el-image>
							</div>
							<h4>{{v.artName}}</h4>
							<p style="font-size: 14px;font-family: 微软雅黑;font-weight:200;">简介:{{v.artIntroduction}}</p>
						</el-card>
					</el-col>
				</div>



				<el-col :span="5">
					<el-card class="home_box-card" style="width: 300px;height: 313px;margin-left: 40px;">
						<div slot="header" class="clearfix">
							<span style="color: black;font-size: 24px;font-family:微软雅黑;">热度排行榜</span>
							<span style="margin-left: 65px;">
								<img src="../../assets/Images/logo/hand002.png"
									style="margin-top: 0px;width:30px;height:30px;" />
							</span>
							<el-button @click="gotoAuction" style="float: right; padding: 3px 0" type="text">去拍卖
							</el-button>
						</div>

						<div class="cardtype" style="height: 50px;">
							<el-row style="cursor: pointer;border-radius:10px;padding-top: 16px; ">
								<el-col :span="7" style="color: black;font-size: 15px;font-family:微软雅黑;">
									<span>1.睡莲</span>
								</el-col>
								<el-col :span="7" style="color: black;font-size: 15px;font-family: 微软雅黑;">
									<span>莫奈</span>
								</el-col>

								<el-col :span="10">
									<img src="../../assets/Images/logo/flame.png"
										style="margin-top: 0px;width: 16px;height:16px;" />
									<span style="margin-left: 10px;font-family: 微软雅黑;">热度：240</span>
								</el-col>
							</el-row>
						</div>
						<div class="cardtype" style="height: 50px;">
							<el-row style="cursor: pointer;border-radius:10px;padding-top: 16px;">
								<el-col :span="7" style="color: black;font-size: 15px;font-family:微软雅黑;">
									<span>2.夏夜晚风</span>
								</el-col>
								<el-col :span="7" style="color: black;font-size: 15px;font-family: 微软雅黑;">
									<span>伍佰</span>
								</el-col>

								<el-col :span="10">
									<img src="../../assets/Images/logo/flame.png"
										style="margin-top: 0px;width: 16px;height:16px;" />
									<span style="margin-left: 10px;font-family: 微软雅黑;">热度：170</span>
								</el-col>
							</el-row>
						</div>

						<div class="cardtype" style="height: 50px;">
							<el-row style="cursor: pointer;border-radius:10px; padding-top: 16px;">
								<el-col :span="7" style="color: black;font-size: 15px;font-family:微软雅黑;">
									<span>3.想去海边</span>
								</el-col>
								<el-col :span="7" style="color: black;font-size: 15px;font-family: 微软雅黑;">
									<span>夏日入侵</span>
								</el-col>

								<el-col :span="10">
									<img src="../../assets/Images/logo/flame.png"
										style="margin-top: 0px;width: 16px;height:16px;" />
									<span style="margin-left: 10px;font-family: 微软雅黑;">热度：157</span>
								</el-col>
							</el-row>
						</div>
						<div class="cardtype" style="height: 50px;">
							<el-row style="cursor: pointer;border-radius:10px; padding-top: 16px;">
								<el-col :span="7" style="color: black;font-size: 15px;font-family:微软雅黑;">
									<span>4.老翁</span>
								</el-col>
								<el-col :span="7" style="color: black;font-size: 15px;font-family: 微软雅黑;">
									<span>大白</span>
								</el-col>

								<el-col :span="10">
									<img src="../../assets/Images/logo/flame.png"
										style="margin-top: 0px;width: 16px;height:16px;" />
									<span style="margin-left: 10px;font-family: 微软雅黑;">热度：134</span>
								</el-col>
							</el-row>
						</div>
					</el-card>
				</el-col>





			</el-row>
			<!-- 书法 -->
			<el-row>
				<!-- <h2 style="margin-left: 20px;">书法</h2> -->
				<div style="margin-top: 30px;">
					<span>
						<font style="margin-left: 45px;font-size: 28px;font-family: 华文新魏;">书法</font>
					</span>
					<el-button type="text" @click="gotocalligraphy" style="margin-left: 92%;">更多></el-button>
				</div>
				<div v-for="v in CalligraphyList" :key="v.value" @click="gotogoodspage(v.artId)">
					<el-col :span="5" style="margin-left: 3%;" class="image__place">
						<el-card :body-style="{ padding: '15px' }" style="height: 315px;">
							<div id="homeimage">
								<img style="height: 100%;width: 100%;" v-bind:src="apiUrl+v.showImage" alt="">
							</div>
							<h4>{{v.artName}}</h4>
							<p style="font-size: 14px;font-family: 微软雅黑;font-weight:200;">简介:{{v.artIntroduction}}</p>

						</el-card>
					</el-col>
				</div>
			</el-row>

			<!-- 摄影 -->
			<el-row>
				<div style="margin-top: 30px;">
					<span>
						<font style="margin-left: 45px;font-size: 28px;font-family: 华文新魏;">摄影</font>
					</span>
					<el-button type="text" @click="gotophotography" style="margin-left: 92%;">更多></el-button>
				</div>
				<div v-for="v in photography" :key="v.value" @click="gotogoodspage(v.artId)">
					<el-col :span="5" style="margin-left: 3%;" class="image__place">
						<el-card :body-style="{ padding: '15px' }" style="height: 260px;">
							<div id="homeimage">
								<img style="height: 100%;width: 100%;" v-bind:src="apiUrl+v.showImage" alt="">
							</div>
							<h4>{{v.artName}}</h4>
							<p style="font-size: 14px;font-family: 微软雅黑;font-weight:200;">简介:{{v.artIntroduction}}</p>
						</el-card>
					</el-col>
				</div>


			</el-row>
			<!-- 模型 -->
			<el-row>
				<div style="margin-top: 30px;">
					<span>
						<font style="margin-left: 45px;font-size: 28px;font-family: 华文新魏;">模型</font>
					</span>
					<el-button type="text" style="margin-left: 92%;">更多></el-button>
				</div>
				<!-- 模型盒子 -->
				<div class="item">
					<!-- 第一个模型 -->
					<el-col :span="5" style="margin-left: 3%;width: 236px;" class="image__place">
						<el-card :body-style="{ padding: '13px' }" style="height:350px;">
							<div style="width: 200px;margin-left: 4px;height: 200px;padding: 0px;background-color: #ffffff;">
								<model-obj :lights="lights" mtl="static/models/mode1/BigWhite.mtl"
									:backgroundColor="bgColor" :rotation="rotation" @on-load="onLoad"
									src="static/models/mode1/BigWhite.obj">
								</model-obj>
							</div>
							<h4>大白</h4>
							<p style="font-size: 14px;font-family: 微软雅黑;font-weight:200;">简介:美国动画《超能陆战队》中机器人-大白</p>
						</el-card>
					</el-col>
					<!-- 第二个模型 -->
					<el-col :span="5" style="margin-left: 3%;width: 236px;" class="image__place">
						<el-card :body-style="{ padding: '13px' }" style="height:350px;">
							<div style="width: 200px;margin-left: 4px;height: 200px;padding: 0px;background-color: #ffffff;">
								<model-obj :lights="lights" :rotation="rotation" @on-load="onLoad"
									:backgroundColor="bgColor" src="static/models/mode2/Museum.unity_16.obj"
									mtl="static/models/mode2/Museum.unity_16.mtl"></model-obj>
								<!-- :lights="lights" -->
							</div>
							<h4>龙猫皮卡丘</h4>
							<p style="font-size: 14px;font-family: 微软雅黑;font-weight:200;">简介:以皮卡丘和龙猫为原型设计的肥皮卡丘</p>
						</el-card>
					</el-col>
					<!-- 第三个模型 -->
					<el-col :span="5" style="margin-left: 3%;width: 236px;" class="image__place">
						<el-card :body-style="{ padding: '13px' }" style="height:350px;">
							<div style="width: 200px;margin-left: 4px;height: 200px;padding: 0px;background-color: #ffffff;">
								<model-obj :lights="lights" :rotation="rotation" @on-load="onLoad"
									src="static/models/mode3/Museum.unity_3.obj" :backgroundColor="bgColor"
									mtl="static/models/mode3/Museum.unity_3.mtl">
								</model-obj>
							</div>
							<h4>大白</h4>
							<p style="font-size: 14px;font-family: 微软雅黑;font-weight:200;">简介:两只白鳍豚相互追逐，竞相起舞的画面</p>
						</el-card>
					</el-col>
					<!-- 第四个模型 -->
					<el-col :span="5" style="margin-left: 3%;width: 236px;" class="image__place">
						<el-card :body-style="{ padding: '13px' }" style="height:350px;">
							<div style="width: 200px;margin-left: 4px;height: 200px;padding: 0px;background-color: #ffffff;">
								<!-- 尺寸不能缩小，否则会转动角度过大 -->
								<model-obj :lights="lights" :rotation="rotation" @on-load="onLoad"
									src="static/models/mode4/Museum.unity_1.obj" :backgroundColor="bgColor"
									mtl="static/models/mode4/Museum.unity_1.mtl">
								</model-obj>
							</div>
							<h4>大白</h4>
							<p style="font-size: 14px;font-family: 微软雅黑;font-weight:200;">简介:人物造型陈设品，铜质复古摆件装饰品模型</p>
						</el-card>
					</el-col>
				</div>
			
			</el-row>

			<!-- 音乐 -->
			<el-row>
				<div style="margin-top: 30px;">
					<span>
						<font style="margin-left: 45px;font-size: 28px;font-family: 华文新魏;">音乐</font>
					</span>
					<el-button type="text" style="margin-left: 92%;">更多></el-button>
				</div>
				<div v-for="v in musiclist" :key="v.value">
					<el-col :span="5" style="margin-left: 3%;" class="image__place">
						<el-card :body-style="{ padding: '2px' }" style="height: 260px;">
							<!-- 32 -->
							<aplayer style="width:100%;margin-left: 0px;margin-top: 15px;" :music="{
									title:v.title,
									artist:v.artist,
									url:v.url,
									pic:v.pic
									}">
							</aplayer>
							<!-- 								<div style="width: 100px;">
									<img :src="MusicImg111" style="width: 180px; height: 150px;" />
								</div>
								<audio :src="MusicUrl" autoplay="autoplay" controls="controls"></audio>
 -->

							<h4>{{v.artName}}</h4>
							<!-- <span>简介:</span> -->
							<p style="font-size: 14px;font-family: 微软雅黑;font-weight:200;padding: 13px;">简介：{{v.artIntroduction}}</p>

						</el-card>
					</el-col>
				</div>
			</el-row>






			<!-- 游戏 -->
			<el-row>
				<div style="margin-top: 30px;">
					<span>
						<font style="margin-left: 45px;font-size: 28px;font-family: 华文新魏;">游戏</font>
					</span>
					<el-button type="text" style="margin-left: 92%;">更多></el-button>
				</div>
				<div v-for="v in gameList" :key="v.value" @click="gotogoodspage(v.artId)">
					<el-col :span="5" style="margin-left: 3%;" class="image__place">
						<el-card :body-style="{ padding: '15px' }" style="height: 350px;">
							<div id="homeimage">
								<img style="height: 100%;width: 100%;" v-bind:src="apiUrl+v.showImage" alt="">
							</div>
							<h4>{{v.artName}}</h4>
							<p style="font-size: 14px;font-family: 微软雅黑;font-weight:200;">简介:{{v.artIntroduction}}</p>

						</el-card>
					</el-col>
				</div>

			</el-row>



			<!-- 影视 -->
			<el-row>
				<div style="margin-top: 30px;">
					<span>
						<font style="margin-left: 45px;font-size: 28px;font-family: 华文新魏;">影视</font>
					</span>
					<el-button type="text" style="margin-left: 92%;">更多></el-button>

				</div>
				<div class="item" v-for="v in teleList" :key="v.value" @click="gotogoodspage(v.artId)">
					<el-col :span="5" style="margin-left: 3%;width: 236px;" class="image__place">
						<el-card :body-style="{ padding: '15px' }" style="height:460px;">
							<div id="homeimage">
								<el-image v-bind:src="apiUrl+v.showImage" alt=""> </el-image>
							</div>
							<h4>{{v.artName}}</h4>
							<p style="font-size: 14px;font-family: 微软雅黑;font-weight:200;">简介:{{v.artIntroduction}}</p>
						</el-card>
					</el-col>
				</div>
			</el-row>


			
			<div style="height: 100px;margin-left: 200px;">


			</div>



		</div>
	</div>
</template>

<script>
	import Aplayer from 'vue-aplayer'
	import {
		ModelObj,
		ModelThree,
		ModelFbx,
		ModelCollada,
		ModelStl,
		ModelPly,
		ModelGltf
	} from "vue-3d-model";

	export default {
		// name: "Carousel",
		name: 'Music',
		components: {
			'aplayer': Aplayer,
			ModelObj,
			ModelThree,
			ModelFbx,
			ModelCollada,
			ModelStl,
			ModelPly,
			ModelGltf

		},
		data() {
			return {

				// MusicUrl: require("../../static/001.mp3"),

				bgColor: "#efefef",
				rotation: {
					// x: -Math.PI / 2,
					x: 0,
					y: 0,
					z: 0
				},
				lights: [{
						type: 'HemisphereLight',
						position: {
							x: 0,
							y: 1,
							z: 0
						},
						// 0x808080
						skyColor: 0xffffff,
						groundColor: 0x808080, // 此代码为灯光后颜色
						intensity: 1,
					},
					{
						type: 'DirectionalLight',
						position: {
							x: 1,
							y: 1,
							z: 1
						},
						// 0xADD8E6
						color: 0xffffff,
						intensity: .2,
					}
				],
				Hometoken: '',
				Hometype: 1,
				Homenum: 3,

				//获取到的数组图片
				PaintPictureList: [],
				CalligraphyList: [],
				photography: [],
				musiclist: [{
						title: '千与千寻',
						artist: '久石让',
						url: require("../../static/001.mp3"),
						pic: require("../../static/千与千寻.jpg"),
						lrc: '[00:00.00] (,,•́ . •̀,,) 刚刚开始学钢琴弹的hhhh',
						artIntroduction: '宫崎骏的动漫往往都体现着那种魅力，每一个人物都是鲜活的。勇敢如千寻，也在变透明时蜷缩着逃避事实，在昏暗的夜里难眠......',
					},
					{
						title: '仲夏夜的猫',
						artist: 'Hea2t',
						url: require("../../static/Hea2t - 仲夏夜的猫.mp3"),
						pic: require("../../static/仲夏夜的猫.jpg"),
						lrc: '[00:00.00] (,,•́ . •̀,,) 刚刚开始学钢琴弹的hhhh',
						artIntroduction: '那些看似波澜不惊的日复一日，在未来的某一天你会看到它的意义！',
					},
					{
						title: '遥远的地方',
						artist: 'UM',
						url: require("../../static/UM - 遥远的地方.mp3"),
						pic: require("../../static/遥远的地方.jpg"),
						lrc: '[00:00.00] (,,•́ . •̀,,) 刚刚开始学钢琴弹的hhhh',
						artIntroduction: '在紧张忙碌的工作之余，多给自己留点空隙，来细细体味有朋友的感觉吧！这会让您的生活充满阳光和温暖！',
					},
					{
						title: '开普勒的小星星',
						artist: '蟹老板的罐头鱼',
						url: require("../../static/蟹老板的罐头鱼 - 开普勒的小星星.mp3"),
						pic: require("../../static/开普勒的小星星.jpg"),
						lrc: '[00:00.00] (,,•́ . •̀,,) 刚刚开始学钢琴弹的hhhh',
						artIntroduction: '活的真诚、独特、潇洒，那么这样活当然很美。不过，首先就是要活得自在，才谈得上这些。',
					}

				],


				gameList: [],
				teleList: [],
				moduol: [],

				images1: [{
						id: 1,
						url: require("../../assets/Images/lo001.jpg")
					},
					{
						id: 3,
						url: require("../../assets/Images/home.png")
					},
					{
						id: 2,
						url: require("../../assets/Images/center.png")
					}

				],
				images2: [{
						id: 1,
						url: require("../../assets/Images/Activity/2020.png")
					},
					{
						id: 2,
						url: require("../../assets/Images/Activity/qingfu.png")
					}
				],
			};
		},
		mounted() {


		},
		methods: {
			onLoad() {
				this.rotate();
			},
			rotate() {
				this.rotation.y += 0.003;
				requestAnimationFrame(this.rotate);
			},

			getHomePaintList() {
				var that = this
				this.Hometoken = localStorage.getItem('token')
				this.$axios.post(
						this.apiUrl + "/palette/mainPage/getRecommendArt", {
							token: this.Hometoken,
							type: 1,
							num: 3
						}
					)
					.then(response => {
						if (response.data.code == 200) {
							that.PaintPictureList = response.data.data.art;

						} else {
							console.log(response.data);
						}
					})
					.catch(function(error) {});

			},
			getHomeCalligraphyList() {
				var that = this
				this.mytoken = localStorage.getItem('token')
				this.$axios.post(
						this.apiUrl + "/palette/mainPage/getRecommendArt", {
							token: this.Hometoken,
							type: 2,
							num: 4
						}
					)
					.then(response => {
						if (response.data.code == 200) {
							//console.log(response.data.data.art);
							that.CalligraphyList = response.data.data.art;
							// console.log(that.CalligraphyList);


						} else {
							console.log(response.data);

						}
					})
					.catch(function(error) {});

			},
			photographylist() {
				var that = this
				this.mytoken = localStorage.getItem('token')
				this.$axios.post(
						this.apiUrl + "/palette/mainPage/getRecommendArt", {
							token: this.Hometoken,
							type: 3,
							num: 4
						}
					)
					.then(response => {
						if (response.data.code == 200) {
							//console.log(response.data.data.art);
							that.photography = response.data.data.art;
							// console.log(that.photography);


						} else {
							console.log(response.data);

						}
					})
					.catch(function(error) {});

			},

			gotomusiclist() {
				var that = this
				this.mytoken = localStorage.getItem('token')
				this.$axios.post(
						this.apiUrl + "/palette/mainPage/getRecommendArt", {
							token: this.Hometoken,
							type: 4,
							num: 4
						}
					)
					.then(response => {
						if (response.data.code == 200) {
							// console.log(response.data.data.art);
							that.musiclist = response.data.data.art;
							// console.log(音乐+that.photography);


						} else {
							console.log(response.data);

						}
					})
					.catch(function(error) {});

			},
			getgameList() {
				var that = this
				this.mytoken = localStorage.getItem('token')
				this.$axios.post(
						this.apiUrl + "/palette/mainPage/getRecommendArt", {
							token: this.Hometoken,
							type: 5,
							num: 4
						}
					)
					.then(response => {
						if (response.data.code == 200) {
							//console.log(response.data.data.art);
							that.gameList = response.data.data.art;
							// console.log(that.photography);


						} else {
							console.log(response.data);

						}
					})
					.catch(function(error) {});

			},

			getteleList() {
				var that = this
				this.mytoken = localStorage.getItem('token')
				this.$axios.post(
						this.apiUrl + "/palette/mainPage/getRecommendArt", {
							token: this.Hometoken,
							type: 7,
							num: 4
						}
					)
					.then(response => {
						if (response.data.code == 200) {
							that.teleList = response.data.data.art;
						} else {
							console.log(response.data);

						}
					})
					.catch(function(error) {});

			},


			gotogoodspage(id) {
				console.log("artid" + id);
				this.$router.push({
					path: '/productdetails',
					query: {
						details: id
					}
				});
			},
			//绘画更多
			gotopaint() {
				this.$router.replace('/painting')
			},


			//书法更多
			gotocalligraphy() {
				this.$router.replace('/calligraphy')
			},

			//摄影更多
			gotophotography() {
				this.$router.replace('/photography')
			},


			gotoshopping_trollery() {
				// console.log(localStoragegetItem('token'));
				//指定跳转到购物车
				this.$router.replace('/shopping_trollery')
			},

			gotoinstaltyCreate() {
				//指定跳转到创作
				this.$router.replace('/instantlyCreate')
			},
			gotoAuction() {
				//指定跳转到
				this.$router.replace('/auction')
			},
			//作品详情
			gotoProductdeils() {
				this.$router.replace('/productdetails')
			}


		},

		created() {
			this.getHomePaintList();
			this.getHomeCalligraphyList();
			this.photographylist();
			// this.gotomusiclist();
			this.getgameList();
			this.getteleList();
			console.log("URL：" + this.apiUrl);
		}
	};
</script>

<style>
	.image {
		/* width: 100%;
		display: block; */
	}

	#homeimage {
		width: 100%;
		height: 100%;
		display: table-cell;
		vertical-align: middle;
		/* margin-top: 30px; */
	}


	.clearfix:before,
	.clearfix:after {
		display: table;
		content: "";

	}

	.clearfix:after {
		clear: both
	}


	/* 卡片样式 */

	.image__place:hover {
		-webkit-box-shadow: 0 0 30px rgba(0, 0, 0, 0.1);
		box-shadow: 10 10 30px rgba(0, 0, 0, 0.15);
		-webkit-transform: translate3d(0, 0px, -2px);
		transform: translate3d(0, 3px, -2px);

		/* -webkit-transform: translateY(-3px); */
		-moz-transform: translateY(-3px);
		-ms-transform: translateY(-3px);
		/* transform: translateY(-3px); */
	}

	/* 拍卖卡片样式 */
	.home_text {
		font-size: 4px;
		/* height: 500px; */
	}

	.home_item {
		padding: 6px;
	}

	.home_box-card {
		width: 80%;
		height: 100%;
		margin-left: 10%;
		margin-right: 10%;
	}

	#picture,
	#music {
		/* margin-left: 20px; */
	}

	#createbutton {
		font-size: 20px;
		width: 100%;
		height: 65px;
		color: white;
		background-color: #F0A1A8;
		border-radius: 5px;
		margin-top: 10px;
	}

	#shopping {
		font-size: 20px;
		width: 95%;
		height: 65px;
		color: white;
		background-color: #8ABCDE;
		border-radius: 5px;
		margin-top: 10px;
	}

	#find {
		font-size: 20px;
		height: 65px;
		width: 100%;
		color: white;
		background-color: #F4CE69;
		border-radius: 5px;
		margin-top: 10px;
	}

	#inputSearch {
		height: 45px;
		width: 95%;
		border-radius: 10px;
		margin-left: 2.5%;
		margin-top: 10px;

	}

	#inputSearchButton {
		margin-left: 90%;
		margin-top: 8px;
	}

	.el-input--placeholder {
		text-align: center;
	}


	.el-carousel__item h3 {
		color: #475669;
		font-size: 18px;
		opacity: 0.75;
		line-height: 300px;
		margin: 0;
	}

	.el-carousel__item:nth-child(2n) {
		background-color: #99a9bf;
	}

	.el-carousel__item:nth-child(2n+1) {
		background-color: #d3dce6;
	}

	.el-button--danger {
		color: #00ff00;
		background-color: #FC813B;
		border-color: #FC813B;
	}

	.elcarousel1 {
		height: 300px;
		width: 100%;
		margin-top: 10px;
		margin-left: 20px;
		/* margin-right: 450px; */
		border-radius: 5px;
	}

	.elcarousel2 {
		margin-top: 10px;
		border-radius: 5px;
		height: 150px;
	}

	.cardtype:hover {
		/* 236,245,255 */
		background-color: #ecf5ff;
		-webkit-transform: translateY(-3px);
		-ms-transform: translateY(-3px);
		transform: translateY(-3px);
		border-radius: 5px;
		/* -webkit-box-shadow: 0 0 6px #999; */
		/* box-shadow: 0 0 6px #999; */
		-webkit-transition: all .5s ease-out;
		transition: all .5s ease-out;
	}
</style>
