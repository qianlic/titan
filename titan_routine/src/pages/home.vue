<template>
  <view>
    <i-notice-bar icon="systemprompt" closable>
      即日起，夏黑10元3斤，欢迎品尝
    </i-notice-bar>
    <swiper class="banner" indicator-dots="true" autoplay="true" interval="3000" duration="1000">
      <swiper-item v-for="item of banner" :key="item.id">
        <navigator :url="item.link">
          <img :src="item.image_url" background-size="cover" />
        </navigator>
      </swiper-item>
    </swiper>
    <i-grid>
      <i-grid-item>
        <i-grid-icon>
          <image src="../images/icon-vip.png" />
        </i-grid-icon>
        <i-grid-label>Grid</i-grid-label>
      </i-grid-item>
      <i-grid-item>Grid</i-grid-item>
    </i-grid>
    <i-cell-group>
      <i-cell :title="address" @click="openmap" is-link>
        <i-icon slot="icon" type="coordinates_fill" size="20"/>
      </i-cell>
      <i-cell :title="workTime">
        <i-icon slot="icon" type="time_fill" size="18"/>
      </i-cell>
      <i-cell :title="phoneNumber" @click="openphone" is-link>
        <i-icon slot="icon" type="addressbook_fill" size="18"/>
      </i-cell>
    </i-cell-group>
    <button  open-type="contact"/>
    <i-divider content="--"></i-divider>
    <i-tab-bar :current="current" @change="handleChange" fixed>
      <i-tab-bar-item key="homepage" icon="homepage" current-icon="homepage_fill" title="主页"></i-tab-bar-item>
      <i-tab-bar-item key="alipay" icon="group" current-icon="group_fill" title="我的"></i-tab-bar-item>
    </i-tab-bar>
  </view>
</template>

<script>
  export default {
    name: 'home',
    data () {
      return {
        current: 'homepage',
        address: '江苏省常熟市海虞镇徐桥公交站旁',
        workTime: '7:00-20:00',
        phoneNumber: '15250452118',
        networkFlow: '1',
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
        }]
      }
    },
    methods: {
      openmap () {
        wx.openLocation({
          latitude: 31.756525,
          longitude: 120.753736,
          name: '海虞葡萄园',
          address: '海虞葡萄园',
          scale: 14
        })
      },
      openphone () {
        wx.makePhoneCall({
          phoneNumber: this.phoneNumber
        })
      },
      handleChange ({target}) {
        this.$router.push(target.key)
      }
    },
    onShareAppMessage () {
      return {
        title: '转发',
        path: '/pages/home'
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
</style>
