<template>
  <view>
    <i-notice-bar icon="systemprompt" v-if="false" closable>
      即日起，夏黑10元3斤，欢迎品尝
    </i-notice-bar>
    <swiper class="banner" indicator-dots="true" autoplay="true" interval="3000" duration="1000">
      <swiper-item v-for="item of banner" :key="item.id">
        <img :src="item.image_url" background-size="cover" />
      </swiper-item>
    </swiper>
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
    <footbar current="home"/>
  </view>
</template>

<script>
import {mapGetters} from 'vuex'
import footbar from '@/components/footbar'

export default {
  name: 'home',
  components: {
    footbar
  },
  computed: {
    ...mapGetters([
      'address',
      'workTime',
      'phoneNumber'
    ]),
    ...mapGetters('home', [
      'banner'
    ])
  },
  methods: {
    openmap () {
      wx.openLocation({
        latitude: 31.756525,
        longitude: 120.753736,
        name: '兰芝华葡萄',
        address: '兰芝华葡萄',
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
