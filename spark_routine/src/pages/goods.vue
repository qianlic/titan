<template>
  <view>
    <view class="a-section a-new" v-if="goods.length">
      <view class="h">
        <view>
          <text class="txt">人气推荐</text>
        </view>
      </view>
      <view class="b">
        <view class="item" v-for="item of goods" :key="item.objectId">
          <img class="img" :src="item.pic_url" background-size="cover" />
          <text class="name">{{item.name}}</text>
          <text class="price">￥{{item.retail_price}}/斤</text>
        </view>
      </view>
    </view>
    <footbar current="goods"/>
  </view>
</template>

<script>
import {mapGetters, mapActions} from 'vuex'
import footbar from '@/components/footbar'

export default {
  name: 'goods',
  components: {
    footbar
  },
  computed: mapGetters('goods', [
    'goods'
  ]),
  methods: mapActions('goods', [
    'loadDatas'
  ]),
  mounted () {
    this.loadDatas()
  }
}
</script>

<style scoped>
  .a-section {
    width: 750rpx;
    height: auto;
    overflow: hidden;
    background: #fff;
    color: #333;
    margin-top: 20rpx;
  }

  .a-section .h {
    display: flex;
    flex-flow: row nowrap;
    align-items: center;
    justify-content: center;
    height: 130rpx;
  }

  .a-section .h .txt {
    padding-right: 30rpx;
    background-size: 16.656rpx 27rpx;
    display: inline-block;
    height: 36rpx;
    font-size: 33rpx;
    line-height: 36rpx;
  }

  .a-new .b {
    width: 750rpx;
    height: auto;
    overflow: hidden;
    padding: 0 31rpx 45rpx 31rpx;
  }

  .a-new .b .item {
    float: left;
    width: 302rpx;
    margin-top: 10rpx;
    margin-left: 21rpx;
    margin-right: 21rpx;
  }

  .a-new .b .img {
    width: 302rpx;
    height: 302rpx;
  }

  .a-new .b .name {
    text-align: center;
    display: block;
    width: 302rpx;
    height: 35rpx;
    margin-bottom: 14rpx;
    overflow: hidden;
    font-size: 30rpx;
    color: #333;
  }

  .a-new .b .price {
    display: block;
    text-align: center;
    line-height: 30rpx;
    font-size: 30rpx;
    color: #b4282d;
  }
</style>
