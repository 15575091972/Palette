(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-9a3bf860"],{"2b03":function(t,e,a){},ca7d:function(t,e,a){"use strict";a.r(e);var l=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticStyle:{"margin-left":"4%"}},[a("div",[a("el-row",[a("el-col",{attrs:{span:6}},[a("div",{staticStyle:{"font-size":"28px","font-family":"华文新魏","margin-top":"24px","margin-left":"24px"}},[t._v("艺术长廊   摄影")])]),a("el-col",{staticStyle:{"margin-left":"250px"},attrs:{span:2}},[a("el-button",{staticStyle:{"margin-top":"20px"},attrs:{icon:"el-icon-search"}})],1),a("el-col",{attrs:{span:5}},[a("el-select",{attrs:{id:"Paintselect1",filterable:"",placeholder:"请选择"},model:{value:t.Paintvalue1,callback:function(e){t.Paintvalue1=e},expression:"Paintvalue1"}},t._l(t.options1,(function(t){return a("el-option",{key:t.value1,attrs:{label:t.label1,value:t.value1}})})),1)],1),a("el-col",{attrs:{span:5}},[a("el-select",{attrs:{id:"Paintselect2",filterable:"",placeholder:"请选择"},model:{value:t.Paintvalue2,callback:function(e){t.Paintvalue2=e},expression:"Paintvalue2"}},t._l(t.options2,(function(t){return a("el-option",{key:t.value2,attrs:{label:t.label2,value:t.value2}})})),1)],1)],1)],1),a("el-row",t._l(t.PaintPictureList,(function(e){return a("div",{key:e.value,on:{click:function(a){return t.gotogoodspage(e.artId)}}},[a("el-col",{staticClass:"image__place",staticStyle:{"margin-left":"3%"},attrs:{span:5}},[a("el-card",{staticStyle:{height:"300px","margin-top":"30px"},attrs:{"body-style":{padding:"15px"}}},[a("div",{attrs:{id:"homeimage"}},[a("el-image",{attrs:{src:t.apiUrl+e.showImage,alt:""}})],1),a("h4",[t._v(t._s(e.artName))]),a("p",[t._v("简介:"+t._s(e.artIntroduction))])])],1)],1)})),0)],1)},n=[],i={name:"Carousel",data:function(){return{input:"向日葵",src:a("90b8"),options1:[{value1:"选项1",label1:"全部"},{value1:"选项2",label1:"按交易次数排序"},{value1:"选项3",label1:"按发布时间排序"}],options2:[{value2:"选项1",label2:"全部"},{value2:"选项2",label2:"音频"},{value2:"选项3",label2:"图像"},{value2:"选项4",label2:"文件"},{value2:"选项5",label2:"文档"},{value2:"选项6",label2:"实体"}],Paintvalue1:"选项1",Paintvalue2:"选项1",Hometoken:"",Hometype:1,Homenum:3,PaintPictureList:[]}},mounted:function(){},methods:{gotogoodspage:function(t){console.log("artid"+t),this.$router.push({path:"/productdetails",query:{details:t}})},getPaintList:function(){var t=this,e=this;this.mytoken=localStorage.getItem("token"),this.$axios.post(this.apiUrl+"/palette/mainPage/getRecommendArt",{token:this.Hometoken,type:3,num:20}).then((function(a){200==a.data.code?(console.log(a.data.data),e.PaintPictureList=a.data.data.art,console.log(e.PaintPictureList),t.$message({type:"info",message:"获取首页数据成功"})):(console.log(a.data),t.$message({type:"info",message:"获取数据失败"}))})).catch((function(t){}))}},created:function(){this.getPaintList()}},o=i,s=(a("d5a1"),a("2877")),c=Object(s["a"])(o,l,n,!1,null,null,null);e["default"]=c.exports},d5a1:function(t,e,a){"use strict";a("2b03")}}]);
//# sourceMappingURL=chunk-9a3bf860.9e082e3b.js.map