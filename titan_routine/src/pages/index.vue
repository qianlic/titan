<template>
  <view class="container">
    <swiper class="banner" indicator-dots="true" autoplay="true" interval="3000" duration="1000">
      <swiper-item v-for="item of banner" :key="item.id">
        <navigator :url="item.link">
          <img :src="item.image_url" background-size="cover" />
        </navigator>
      </swiper-item>
    </swiper>

    <view class="m-menu">
      <navigator  class="item" :url="item.url" v-for="item of channel" :key="item.id">
        <img :src="item.icon_url" background-size="cover" />
        <text>{{item.name}}</text>
      </navigator>
    </view>
    <card :text="userinfo"/>
    <button @click="getUserInfo">登录</button>
    <view>
      <navigator url="/pages/counter"  hover-class="changestyle" @click="clickHandle">去往Vuex示例页面</navigator>
    </view>
  </view>
</template>

<script>
import card from '@/components/card'

export default {
  data () {
    return {
      userinfo: 'test',
      motto: 'Hello World',
      userInfo: {},
      'banner': [{
        'id': 1,
        'ad_position_id': 1,
        'media_type': 1,
        'name': '合作 谁是你的菜',
        'link': '/pages/counter?id=1005002',
        'image_url': 'http://yanxuan.nosdn.127.net/65091eebc48899298171c2eb6696fe27.jpg',
        'content': '合作 谁是你的菜',
        'end_time': 0,
        'enabled': 1
      }, {
        'id': 2,
        'ad_position_id': 1,
        'media_type': 1,
        'name': '活动 美食节',
        'link': '/pages/counter?id=1005001',
        'image_url': 'http://yanxuan.nosdn.127.net/bff2e49136fcef1fd829f5036e07f116.jpg',
        'content': '活动 美食节',
        'end_time': 0,
        'enabled': 1
      }, {
        'id': 3,
        'ad_position_id': 1,
        'media_type': 1,
        'name': '活动 母亲节',
        'link': '/pages/counter?id=1005000',
        'image_url': 'http://yanxuan.nosdn.127.net/8e50c65fda145e6dd1bf4fb7ee0fcecc.jpg',
        'content': '活动 母亲节',
        'end_time': 0,
        'enabled': 1
      }],
      'channel': [{
        'id': 1,
        'name': '居家',
        'url': '/pages/counter?id=1005000',
        'icon_url': 'https://yanxuan.nosdn.127.net/243e5bf327a87217ad1f54592f0176ec.png',
        'sort_order': 1
      }, {
        'id': 2,
        'name': '餐厨',
        'url': '/pages/counter?id=1005001',
        'icon_url': 'https://yanxuan.nosdn.127.net/f109afbb7e7a00c243c1da29991a5aa3.png',
        'sort_order': 2
      }, {
        'id': 3,
        'name': '配件',
        'url': '/pages/counter?id=1008000',
        'icon_url': 'https://yanxuan.nosdn.127.net/288b0e864a24763bade8e22c0c39ff02.png',
        'sort_order': 3
      }, {
        'id': 4,
        'name': '服装',
        'url': '/pages/counter?id=1010000',
        'icon_url': 'https://yanxuan.nosdn.127.net/863b1e4a6c84fb33f5d09d91d2a36881.png',
        'sort_order': 4
      }, {
        'id': 5,
        'name': '志趣',
        'url': '/pages/counter?id=1019000',
        'icon_url': 'https://yanxuan.nosdn.127.net/a13a24413e66474cc7b0551984cfe9d4.png',
        'sort_order': 5
      }]
    }
  },
  components: {
    card
  },
  methods: {
    getUserInfo () {
      // 调用登录接口
      wx.login({
        success: () => {
          wx.getUserInfo({
            success: (res) => {
              this.userinfo = '获取用户信息成功'
              this.userInfo = res.userInfo
              console.log(res)
            },
            fail: () => {
              this.userinfo = '获取用户信息失败'
            }
          })
        },
        fail: () => {
          this.userinfo = '登录失败'
        },
        complete: () => {
          this.userinfo = '登录完成'
        }
      })
    },
    clickHandle (msg, ev) {
      // eslint-disable-next-line
      console.log('clickHandle:', msg, ev)
    }
  }
}
</script>

<style scoped>
.banner {
  width: 750rpx;
  height: 417rpx;
}

.banner image {
  width: 100%;
  height: 417rpx;
}

.m-menu {
  display: flex;
  height: 181rpx;
  width: 750rpx;
  flex-flow: row nowrap;
  align-items: center;
  justify-content: space-between;
  background-color: #fff;
}

.m-menu .item {
  flex: 1;
  display: block;
  padding: 20rpx 0;
}

.m-menu image {
  display: block;
  width: 58rpx;
  height: 58rpx;
  margin: 0 auto;
  margin-bottom: 12rpx;
}

.m-menu text {
  display: block;
  font-size: 24rpx;
  text-align: center;
  margin: 0 auto;
  line-height: 1;
  color: #333;
}
</style>
