import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home/Home.vue'
import TravelWorld from '../views/TravelWorld/TraveIWorld.vue'
import Set from '../views/Set/Set_home/Set.vue'
import PersonalExhibition from '../views/PersonalExhibition/PersonalExhibition.vue'
//import MessageNotification from '../views/MessageNotification/MessageNotification.vue'
import Calligraphy from '../views/ArtGallery/Calligraphy/Calligraphy.vue'
import Music from '../views/ArtGallery/Music/Music.vue'
import About from '../views/Set/SetDetail/About.vue'
import Shopping_trollery from '../views/Shopping_trollery/Shopping_torllery.vue'
import Help from '../views/Set/help/help.vue'
import Authentication from '../views/Set/Authentication/Authentication.vue'
import Preference from '../views/Set/Preference/Preference.vue'
import Security from '../views/Set/Security/Security.vue'
import logout from '../views/Set/Logout/logout.vue'
import PersonalCenter from '../views/PersonalCenter/personCenter_home/PersonalCenter.vue'

import CreateiveManagement from '../views/PersonalCenter/CreateiveManagement/CreateiveManagement.vue'
import DynamicIssue from '../views/PersonalCenter/DynamicIssue/DynamicIssue.vue'
import HomeDetails from '../views/PersonalCenter/HomeDetails/HomeDetails.vue'
import MyOrder from '../views/PersonalCenter/MyOrder/MyOrder.vue'
import PersonCollection from '../views/PersonalCenter/personCollection/personCollection.vue'
import MyTigressMoney from '../views/PersonalCenter/MyTigressMoney/MyTigressMoney.vue'
import PersonalData from '../views/PersonalCenter/PersonalData/PersonalData.vue'

import MessageNotification from '../views/MessageNotification/MessageNotification_home/MessageNotification.vue'

import InterestedMeUser1 from '../views/MessageNotification/InterestedMe/InterestedMeUser1.vue'
import InterestedMeUser2 from '../views/MessageNotification/InterestedMe/InterestedMeUser2.vue'
import QingFuActivity from '../views/MessageNotification/SystemAnnouncement/QingFuActivity.vue'
import MusicUser1 from '../views/MessageNotification/MyInterest/Music/MusicUser1.vue'
import MusicUser2 from '../views/MessageNotification/MyInterest/Music/MusicUser2.vue'
import PaintingUser1 from '../views/MessageNotification/MyInterest/Painting/PaintingUser1.vue'
import PaintingUser2 from '../views/MessageNotification/MyInterest/Painting/PaintingUser2.vue'
import Version1 from '../views/MessageNotification/SystemAnnouncement/VersionDetails/Version1.vue'
import Version2 from '../views/MessageNotification/SystemAnnouncement/VersionDetails/Version2.vue'
import HappySpringFestival from '../views/MessageNotification/SystemAnnouncement/HappySpringFestival.vue'
import ProfitBill from '../views/MessageNotification/SystemAnnouncement/ProfitBill.vue'



import login from '../views/Login/login.vue'
Vue.use(VueRouter)

const routes = [
	// },
]

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location, onResolve, onReject) {
	if (onResolve || onReject) return originalPush.call(this, location, onResolve, onReject)
	return originalPush.call(this, location).catch(err => err)
}


const router = new VueRouter({
	routes: [{
			path: '/',
			name: 'home',
			component: Home
		},
		{
			path: '/login',
			name: 'login',
			component: () => import('../views/Login/login.vue'),
			meta: {
				title: '登录'
			}
		},
		{
			path: '/set',
			name: 'set',
			component: Set,
			children: [{
					path: 'about',
					components: {
						set_router: About
					},
				},

				{
					path: 'help',
					components: {
						set_router: Help
					}
				},
				{
					path: 'about',
					components: {
						set_router: About
					}
				},
				{
					path: 'help',
					components: {
						set_router: Help
					}
				},
				{
					path: 'authentication',
					components: {
						set_router: Authentication
					}
				},
				{
					path: 'preference',
					components: {
						set_router: Preference
					}
				},
				{
					path: 'security',
					components: {
						set_router: Security
					},

				},
				{
					path: 'logout',
					components: {
						set_router: logout
					}
				}
			]
		},
		{
			path: '/personalCenter',
			name: 'personalCenter',
			component: PersonalCenter,
			children: [{
					path: 'myOrder',
					components: {
						personalCenter_router: MyOrder
					},
				},

				{
					path: 'createiveManagement',
					components: {
						personalCenter_router: CreateiveManagement
					},
				},
				{
					path: 'dynamicIssue',
					components: {
						personalCenter_router: DynamicIssue
					},
				},
				{
					path: 'homeDetails',
					components: {
						personalCenter_router: HomeDetails
					},
				},


				{
					path: 'myTigressMoney',
					components: {
						personalCenter_router: MyTigressMoney
					},
				},
				{
					path: 'personCollection',
					components: {
						personalCenter_router: PersonCollection
					},
				},
			]
		},
		{
			path: '/personalExhibition',
			name: 'personalExhibition',
			component: PersonalExhibition
		},

		{
			path: '/messageNotification',
			name: 'messageNotification',
			component: MessageNotification,
			children: [{
					path: 'interestedmeuser1',
					component: InterestedMeUser1
				},
				{
					path: 'interestedmeuser2',
					component: InterestedMeUser2
				},
				{
					path: 'qingfuactivity',
					component: QingFuActivity
				},
				{
					path: 'musicuser1',
					component: MusicUser1
				},
				{
					path: 'musicuser2',
					component: MusicUser2
				},
				{
					path: 'paintinguser1',
					component: PaintingUser1
				},
				{
					path: 'paintinguser2',
					component: PaintingUser2
				},
				{
					path: 'version1',
					component: Version1
				},
				{
					path: 'version2',
					component: Version2
				},
				{
					path: 'happyspringfestival',
					component: HappySpringFestival
				},
				{
					path: 'profitbill',
					component: ProfitBill
				}
			]
		},
		{
			path: '/supermarket',
			name: 'supermarket',
			component: () =>
				import('../views/Supermarket/Supermarket.vue')
		},
		{
			path: '/indbox',
			name: 'blindbox',
			component: () =>
				import('../views/Supermarket/Blindbox.vue')
		},


		{
			path: '/instantlyCreate',
			name: 'instantlyCreate',
			component: () =>
				import('../views/InstantlyCreate/InstantlyCreate.vue')
		},

		{
			path: '/painting',
			name: 'painting',
			component: () =>
				import('../views/ArtGallery/Painting/Painting.vue')
		},
		{
			path: '/file_television',
			name: 'file_television',
			component: () =>
				import('../views/ArtGallery/File_television/File_television.vue')
		},
		{
			path: '/game',
			name: 'game',
			component: () =>
				import('../views/ArtGallery/Game/Game.vue')
		},
		{
			path: '/model',
			name: 'model',
			component: () =>
				import('../views/ArtGallery/Model/Model.vue')
		},
		{
			path: '/music',
			name: 'music',
			component: () =>
				import('../views/ArtGallery/Music/Music.vue')
		},
		{
			path: '/photography',
			name: 'photography',
			component: () =>
				import('../views/ArtGallery/Photography/Photography.vue')
		},


		{
			path: '/calligraphy',
			name: 'calligraphy',
			component: Calligraphy
		},
		{
			path: '/travelWorld',
			name: 'travelWorld',
			component: TravelWorld
		},

		{
			path: '/shopping_trollery',
			name: 'shopping_trollery',
			component: Shopping_trollery
		},
		{
			path: '/auction',
			name: 'auction',
			component: () =>
				import('../views/Auction/Auction.vue')

		},
		{
			path: '/productdetails',
			name: 'productdetails',
			component: () =>
				import('../views/Productdetails/Productdetails.vue')

		},
		// import checkpeopel from'../views/checkPeo/index.vue'
		{
			path: '/checkpeopel',
			name: 'checkpeopel',
			component: () =>
				import('../views/checkPeo/index.vue')
		
		},
		{
			path: '/search',
			name: 'search',
			component: () =>
				import('../views/search/search.vue')

		},
		{
			path: '/product01',
			name: 'product01',
			component: () =>
				import('../views/Check/product01.vue')
		},
		{
			path: '/product02',
			name: 'product02',
			component: () =>
				import('../views/Check/product02.vue')
		}

	]
});



router.beforeEach((to, from, next) => {
	// if (to.path === '/set/preference') {    //若要跳转的页面是登录界面
	//   next();     //直接跳转
	// }
	// else 
	if (to.path === '/instantlyCreate') { //若要跳转的页面是个人界面
		let token = localStorage.getItem('token'); //获取本地存储的token值
		console.log("token" + token);
		if (token === null || token === '') { //若token为空则验证不成功，跳转到登录页面
			next('/');
		} else { //不为空则验证成功
			next();
		}
	} else {
		next();
	}
});

export default router
