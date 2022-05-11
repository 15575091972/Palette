<template>
	<div style="margin-left: 20%;" id="Productdetails_head" class="containerPro">
		<div>
			<h2>作品详情</h2>
		</div>

		<div>
			<el-row>
				<el-col :span="16">
					<el-row>
						<el-col :span="12">
							<el-image style="width: 230px; height: 270px" v-bind:src="apiUrl+showImage" alt="">
							</el-image>
						</el-col>
						<el-col :span="12" style="color: black;font-size: 15px;font-family: 微软雅黑;">
							<p>作品名称: &nbsp;&nbsp;{{artname}} </p>
							<p>创作者: &nbsp;&nbsp;{{auther}}</p>
							<p>所属领域: &nbsp;&nbsp; 绘画</p>
							<p>发布时间: &nbsp;&nbsp; 2020-11-06</p>
							<p>作品简介: &nbsp;&nbsp; {{artIntroduction}}</p>
						</el-col>

					</el-row>
				</el-col>

				<el-col :span="5">
					<el-row style="margin-left: 120px;">
						<img src="../../assets/Images/icon/talk02.png"
							style="margin-top: 0px;width:60px;height:60px;" />
					</el-row>
					<el-row>
						<el-card class="pro_box-card" style="width: 150px;height: 180px;margin-left: 40px;">
							<!-- <div class="clearfix"> -->
							<span style="font-size: 16px;font-family:微软雅黑;margin-left: 25px;">相似推荐</span>
							<!-- </div> -->

							<div style="height: 50px;margin-left: 2px;">
								<el-col class="cardtype"
									style="margin-top: 10px;font-size:15px  ;font-weight: 300;text-align: center;">

									<!-- <span>Myjinzi-橘子海</span> -->
									<span>日初印象-莫奈</span>
								</el-col>

								<el-col class="cardtype"
									style="margin-top: 10px;font-size:15px ;font-weight: 300;text-align: center;">
									<!-- <div>沙发上的白日梦 -灵魂沙发</div> -->
									<div>春天果树-阿尔弗莱德·西斯莱</div>
								</el-col>

								<el-col class="cardtype"
									style="margin-top: 10px;font-size:15px ;font-weight: 300;text-align: center;">
									<!-- <span>驰Timelapse-CORSAK胡梦舟</span> -->
									<span>村落·冬天的印象-毕沙罗</span>
								</el-col>
							</div>
						</el-card>
					</el-row>


				</el-col>

				<el-col :span="22">
					<el-table class="goodsinformationlist" ref="multipleTable" :data="goodsinformationlist"
						tooltip-effect="dark" style="width: 100%;margin-top: 30px;">
						<el-table-column prop="goodsId" label="商品编号" width="180">
						</el-table-column>
						<el-table-column prop="goodsSeller.userName" label="卖家" width="100">
							<template slot-scope="scope">
								<p>神秘人</p>
							</template>
						</el-table-column>

						<el-table-column prop="goodsSeller" label="头像" width="90">
							<template slot-scope="scope">
								<img style="width: 40px;height:40px;"
									src="../../assets/Images/HeadImage/青石巷.jpg"><img>
							</template>
						</el-table-column>

						<el-table-column prop="resalePrice" label="价格" width="100">
						</el-table-column>
						<el-table-column prop="goodsQuantity" label="份额" width="100">
						</el-table-column>

						<el-table-column label="操作" style="align" >
							<template slot-scope="scope">
								<el-button type="primary" size="mini" @click='gettocar(scope.row.goodsId)'>购物车
								</el-button>
								<el-button type="danger" size="mini" @click='shopping(scope.row)'>购&nbsp;&nbsp;买
								</el-button>
							</template>
						</el-table-column>
					</el-table>

				</el-col>
			</el-row>
		</div>
		
		
		<div style="margin-left: 0px;width: 750px;">
			<div style="margin-top:30px">
				交易记录
			</div> 
			 
			<div >
				<el-table :data="tableData" stripe style="width: 100%">
					<el-table-column prop="date" label="时间" width="180">
					</el-table-column>
					<el-table-column prop="name" label="发起方" width="120">
					</el-table-column>
					<el-table-column prop="operation" label="操作" width="120">
					</el-table-column>
					<el-table-column prop="price" label="价格" width="120">
					</el-table-column>
					<el-table-column prop="Blockhash_value" label="哈希区块值" width="210">
					</el-table-column>
				</el-table>
			</div>
		</div>
		
		
		<div style="margin-left: 0px;width: 750px;margin-top: 30px;">
		    <div v-clickoutside="hideReplyBtn" @click="inputFocus" class="my-reply" style="font-family: 微软雅黑;color:black">
		        <el-avatar class="header-img" :size="40" :src="myHeader"></el-avatar>
		        <div class="reply-info" >
		            <div 
		            tabindex="0" 
		            contenteditable="true" 
		            id="replyInput" 
		            spellcheck="false" 
		            placeholder="输入评论..." 
		            class="reply-input" 
		            @focus="showReplyBtn"  
		            @input="onDivInput($event)"
		            >
		            </div>
		        </div>
		        <div class="reply-btn-box" v-show="btnShow">
		            <el-button class="reply-btn" size="medium" @click="sendComment" type="primary">发表评论</el-button>
		        </div>
		    </div>
		    <div v-for="(item,i) in comments" :key="i" class="author-title reply-father" style="font-family: 微软雅黑;">
		        <el-avatar class="header-img" :size="40" :src="item.headImg"></el-avatar>
		        <div class="author-info">
		            <span class="author-name">{{item.name}}</span>
		            <span class="author-time">{{item.time}}</span>
		        </div>
		        <div class="icon-btn">
		            <span @click="showReplyInput(i,item.name,item.id)"><i class="iconfont el-icon-s-comment"></i>{{item.commentNum}}</span>
		            <i class="iconfont el-icon-caret-top"></i>{{item.like}}
		        </div>
		        <div class="talk-box">
		            <p>
		                <span class="reply">{{item.comment}}</span>
		            </p>
		        </div>
		        <div class="reply-box">
		            <div v-for="(reply,j) in item.reply" :key="j" class="author-title">
		                <el-avatar class="header-img" :size="40" :src="reply.fromHeadImg"></el-avatar>
		                <div class="author-info">
		                    <span class="author-name">{{reply.from}}</span>
		                    <span class="author-time">{{reply.time}}</span>
		                </div>
		                <div class="icon-btn">
		                    <span @click="showReplyInput(i,reply.from,reply.id)"><i class="iconfont el-icon-s-comment"></i>{{reply.commentNum}}</span>
		                    <i class="iconfont el-icon-caret-top"></i>{{reply.like}}
		                </div>
		                <div class="talk-box">
		                    <p>
		                        <span>回复 {{reply.to}}:</span>
		                        <span class="reply">{{reply.comment}}</span>
		                    </p>
		                </div>
		                <div class="reply-box">
		 
		                </div>
		            </div>
		        </div>
		        <div  v-show="_inputShow(i)" class="my-reply my-comment-reply">
		            <el-avatar class="header-img" :size="40" :src="myHeader"></el-avatar>
		            <div class="reply-info" >
		                <div tabindex="0" contenteditable="true" spellcheck="false" placeholder="输入评论..."   @input="onDivInput($event)"  class="reply-input reply-comment-input"></div>
		            </div>
		            <div class=" reply-btn-box">
		                <el-button class="reply-btn" size="medium" @click="sendCommentReply(i,j)" type="primary">发表评论</el-button>
		        </div>
		    </div>
		    </div>
		</div>
		
		
		<div style="height: 200px;">
			
			
		</div>
	</div>
</template>

<script>
	const clickoutside = {
	    // 初始化指令
	    bind(el, binding, vnode) {
	    function documentHandler(e) {
	    // 这里判断点击的元素是否是本身，是本身，则返回
	        if (el.contains(e.target)) {
	            return false;
	        }
	    // 判断指令中是否绑定了函数
	        if (binding.expression) {
	            // 如果绑定了函数 则调用那个函数，此处binding.value就是handleClose方法
	            binding.value(e);
	        }
	    }
	    // 给当前元素绑定个私有变量，方便在unbind中可以解除事件监听
	    el.vueClickOutside = documentHandler;
	    document.addEventListener('click', documentHandler);
	    },
	    update() {},
	    unbind(el, binding) {
	    // 解除事件监听
	    document.removeEventListener('click', el.vueClickOutside);
	    delete el.vueClickOutside;
	  },
	};
	
	
	
	
	export default {
		data() {
			return {
				btnShow: false,
				index:'0',
				replyComment:'',
				myName:'魔云连洲',
				myHeader:'https://img-blog.csdnimg.cn/48a2bc68bb7e4bbc9d48914e2c5b76ea.png',
				myId:19870621,
				to:'',
				toId:-1,
				comments:[
				    {
				        name:'魔云连洲 ',
				        id:19870621,
				        headImg:'https://img-blog.csdnimg.cn/48a2bc68bb7e4bbc9d48914e2c5b76ea.png',
				        comment:'我发布一张新艺术品,大家快来看啊',
				        time:'2019年9月16日 18:43',
				        commentNum:2,
				        like:15,
				        inputShow:false,
				        reply:[
				            {
				                from:'Taylor',
				                fromId:19891221,
				                fromHeadImg:'https://img-blog.csdnimg.cn/d51797c39f584d49a71198c6be64834a.png',
				                to:'魔云连洲',
				                toId:19870621,
				                comment:'我很喜欢你的新作品！！',
				                time:'2022年4月11日 18:43',
				                commentNum:1,
				                like:15,
				                inputShow:false
				            },
				            {
				                from:'Ariana',
				                fromId:1123,
				                fromHeadImg:'https://img-blog.csdnimg.cn/a251e876796e425aaa02c1ae3f75daba.png',
				                to:'魔云连洲',
				                toId:19870621,
				                comment:'别忘记宣传我们的合作艺术品啊',
				                time:'2022年4月11日 18:43',
				                commentNum:0,
				                like:5,
				                inputShow:false
				 
				            }
				        ]
				    },
				    {
				        name:'Taylor ',
				        id:19891221,
				        headImg:'https://img-blog.csdnimg.cn/d51797c39f584d49a71198c6be64834a.png',
				        comment:'我发布了我的新作品',
				        time:'2022年4月11日 18:43',
				        commentNum:1,
				        like:5,
				        inputShow:false,
				        reply:[
				            {
				                from:'魔云连洲',
				                fromId:19870621,
				                fromHeadImg:'https://img-blog.csdnimg.cn/48a2bc68bb7e4bbc9d48914e2c5b76ea.png',
				                to:'Taylor',
				                toId:19891221,
				                comment:'你的作品也好厉害！',
				                time:'2022年4月11日 18:43',
				                commentNum:25,
				                like:5,
				                inputShow:false
				 
				            }
				        ]
				    },
				],
					
				 
				
				
				Product_url: require("../../assets/Images/supermarket/shuilian.png"),
				productdetails: [],
				owner: [],

				goodsinformationlist: [],

				//详情属性
				artname: '',
				auther: '',
				artIntroduction: '',
				showImage: '',
				// mytoken: '',



				tableData: [{
					date: '2021-05-23',
					name: '刘涛',
					operation: ' 购入',
					price: '500000',
					Blockhash_value: '**********094'

				}, {
					date: '2018-03-05',
					name: '洛风',
					operation: ' 购入',
					price: '12000',
					Blockhash_value: '**********123'
				}, {
					date: '2017-07-02',
					name: '王成可',
					operation: ' 购入',
					price: '5000',
					Blockhash_value: '**********745'
				}, {
					date: '2016-05-02',
					name: '王小虎',
					operation: ' 购入',
					price: '3000',
					Blockhash_value: '**********348'
				}]
			}
		},


directives: {clickoutside},
		methods: {
			
			inputFocus(){
			    var replyInput = document.getElementById('replyInput');
			    replyInput.style.padding= "8px 8px"
			    replyInput.style.border ="2px solid gry"
			    replyInput.focus()
			},  
			showReplyBtn(){
			    this.btnShow = true
			},
			hideReplyBtn(){
			    this.btnShow = false
			    replyInput.style.padding= "10px"
			    replyInput.style.border ="none"
			},
			showReplyInput(i,name,id){
			    this.comments[this.index].inputShow = false
			    this.index =i
			    this.comments[i].inputShow = true
			    this.to = name
			    this.toId = id
			},
			_inputShow(i){
			    return this.comments[i].inputShow 
			},
			sendComment(){
			    if(!this.replyComment){
			         this.$message({
			            showClose: true,
			            type:'warning',
			            message:'评论不能为空'
			        })
			    }else{
			        let a ={}
			        let input =  document.getElementById('replyInput')
			        let timeNow = new Date().getTime();
			        let time= this.dateStr(timeNow);
			        a.name= this.myName
			        a.comment =this.replyComment
			        a.headImg = this.myHeader
			        a.time = time
			        a.commentNum = 0
			        a.like = 0
			        this.comments.push(a)
			        this.replyComment = ''
			        input.innerHTML = '';
			 
			    }
			},
			sendCommentReply(i,j){
			    if(!this.replyComment){
			         this.$message({
			            showClose: true,
			            type:'warning',
			            message:'评论不能为空'
			        })
			    }else{
			        let a ={}
			        let timeNow = new Date().getTime();
			        let time= this.dateStr(timeNow);
			        a.from= this.myName
			        a.to = this.to
			        a.fromHeadImg = this.myHeader
			        a.comment =this.replyComment
			        a.time = time
			        a.commentNum = 0
			        a.like = 0
			        this.comments[i].reply.push(a)
			        this.replyComment = ''
			        document.getElementsByClassName("reply-comment-input")[i].innerHTML = ""
			    }
			},
			onDivInput: function(e) {
			    this.replyComment = e.target.innerHTML;
			},
			dateStr(date){
			    //获取js 时间戳
			    var time=new Date().getTime();
			    //去掉 js 时间戳后三位，与php 时间戳保持一致
			    time=parseInt((time-date)/1000);
			    //存储转换值 
			    var s;
			    if(time<60*10){//十分钟内
			        return '刚刚';
			    }else if((time<60*60)&&(time>=60*10)){
			        //超过十分钟少于1小时
			        s = Math.floor(time/60);
			        return  s+"分钟前";
			    }else if((time<60*60*24)&&(time>=60*60)){ 
			        //超过1小时少于24小时
			        s = Math.floor(time/60/60);
			        return  s+"小时前";
			    }else if((time<60*60*24*30)&&(time>=60*60*24)){ 
			        //超过1天少于30天内
			        s = Math.floor(time/60/60/24);
			        return s+"天前";
			    }else{ 
			        //超过30天ddd
			        var date= new Date(parseInt(date));
			        return date.getFullYear()+"/"+(date.getMonth()+1)+"/"+date.getDate();
			    }
			},


			getsearchList() {
				// var that = this

				this.$axios.post(
						this.apiUrl + "/palette/art/getDetailsById", {
							artId: this.$route.query.details
						}
					)
					.then(response => {
						if (response.data.code == 200) {
							this.artname = response.data.data.art.artName;
							this.auther = response.data.data.art.artAuthor;
							this.artIntroduction = response.data.data.art.artIntroduction;
							this.showImage = response.data.data.art.showImage;
						} else {}
					})
					.catch(function(error) {});

			},

			//获取详细信息
			getinformation() {
				this.$axios.post(
						this.apiUrl + "/palette/art/getArtGoods", {
							artId: this.$route.query.details
						}
					)
					.then(response => {
						if (response.data.code == 200) {
							console.log(response.data.data.goods);
							this.goodsinformationlist = response.data.data.goods;
						} else {}
					})
					.catch(function(error) {});
			},

			//添加到购物车
			gettocar(id) {
				console.log(id);
				this.mytoken = localStorage.getItem('token')
				this.$axios.post(
						this.apiUrl + "/palette/user/setShoppingCar", {
							token: this.mytoken,
							goodsId: id
						}
					)
					.then(response => {
						if (response.data.code == 200) {
							this.$notify({
								title: '成功',
								message: '添加成功',
								type: 'success'
							});
						} else {this.$notify({
								title: '成功',
								message: '添加失败',
								type: 'success'
							});}
					})
					.catch(function(error) {

					});
			},
			shopping(id) {

			},



		},
		created() {
			//获取
			this.getsearchList();
			this.getinformation();
		},
	}
</script>

<style  lang="stylus" scoped>


.my-reply
    padding 10px
    background-color #fafbfc
    .header-img
        display inline-block
        vertical-align top
    .reply-info    
        display inline-block
        margin-left 5px
        width 90%
        @media screen and (max-width:1200px) {
            width 80%
        }
        .reply-input
            min-height 20px
            line-height 22px
            padding 10px 10px
            color #ccc
            background-color #fff
            border-radius 5px
            &:empty:before
                content attr(placeholder)
            &:focus:before
                content none
            &:focus
                padding 8px 8px
                border 2px solid blue
                box-shadow none
                outline none
    .reply-btn-box
        height 25px
        margin 10px 0
        .reply-btn
            position relative
            float right
            margin-right 15px
.my-comment-reply
    margin-left 50px
    .reply-input
        width flex
.author-title:not(:last-child)
    border-bottom: 1px solid rgba(178,186,194,.3)
.author-title
    padding 10px
    .header-img
        display inline-block
        vertical-align top
    .author-info
        display inline-block
        margin-left 5px
        width 60%
        height 40px
        line-height 20px
        >span 
            display block
            cursor pointer
            overflow hidden
            white-space nowrap
            text-overflow ellipsis
        .author-name
            color #000
            font-size 18px
            font-weight bold
        .author-time
            font-size 14px
    .icon-btn
        width 30%
        padding 0 !important 
        float right
        @media screen and (max-width : 1200px){
            width 20%
            padding 7px
        }
        >span 
            cursor pointer
        .iconfont 
            margin 0 5px
    .talk-box
        margin 0 50px
        >p
           margin 0
        .reply
            font-size 16px
            color #000
    .reply-box
        margin 10px 0 0 50px
        background-color #ffffff



	#containerPro {
		overflow-y: scroll;

	}

	#Productdetails_head {
		margin-left: 15%;
		margin-right: 15%;
	}
</style>
