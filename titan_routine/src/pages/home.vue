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
    <i-cell-group>
      <i-cell title="江苏省常熟市海虞镇金丝狐公交站旁" @click="openmap" is-link>
        <i-icon slot="icon" type="coordinates_fill" size="20"/>
      </i-cell>
      <i-cell title="7:00-20:00">
        <i-icon slot="icon" type="time_fill" size="18"/>
      </i-cell>
      <i-cell :title="phoneNumber" @click="openphone" is-link>
        <i-icon slot="icon" type="addressbook_fill" size="18"/>
      </i-cell>
    </i-cell-group>
  </view>
</template>

<script>
  export default {
    name: 'home',
    data () {
      return {
        phoneNumber: '15250452118',
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
          scale: 14
        })
      },
      openphone () {
        wx.makePhoneCall({
          phoneNumber: this.phoneNumber
        })
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
