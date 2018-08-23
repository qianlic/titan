<template>
  <view>
    <view class="a-section a-new" v-if="newGoods.length">
      <view class="h">
        <view>
          <text class="txt">人气推荐</text>
        </view>
      </view>
      <view class="b">
        <view class="item" v-for="item of newGoods" :key="item.id">
          <img class="img" :src="item.list_pic_url" background-size="cover" />
          <text class="name">{{item.name}}</text>
          <text class="price">￥{{item.retail_price}}/斤</text>
        </view>
      </view>
    </view>
    <footbar :current="current"></footbar>
  </view>
</template>

<script>
  import footbar from '@/components/footbar'

  export default {
    name: 'goods',
    data () {
      return {
        current: 'goods',
        newGoods: [{
          'id': 1134030,
          'name': '商品一',
          'list_pic_url': 'http://yanxuan.nosdn.127.net/aa49dfe878becf768eddc4c1636643a6.png',
          'retail_price': 6
        }, {
          'id': 1134032,
          'name': '商品二',
          'list_pic_url': 'http://yanxuan.nosdn.127.net/8b30eeb17c831eba08b97bdcb4c46a8e.png',
          'retail_price': 8
        }, {
          'id': 1135002,
          'name': '商品三',
          'list_pic_url': 'http://yanxuan.nosdn.127.net/45548f26cfd0c7c41e0afc3709d48286.png',
          'retail_price': 10
        }, {
          'id': 1152161,
          'name': '商品四',
          'list_pic_url': 'http://yanxuan.nosdn.127.net/977401e75113f7c8334c4fb5b4bf6215.png',
          'retail_price': 15
        }]
      }
    },
    components: {
      footbar
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
