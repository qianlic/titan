<template>
  <view>
    <canvas style="width: 400px; height: 400px;" canvas-id="qrCanvas"/>
    <i-button type="primary" @click="onShare">保存</i-button>
    <footbar current="shared"/>
  </view>
</template>

<script>
import footbar from '@/components/footbar'

export default {
  name: 'shared',
  data () {
    return {
      picUrl: undefined,
      width: 0
    }
  },
  components: {
    footbar
  },
  methods: {
    onShare () {
      wx.canvasToTempFilePath({
        x: 0,
        y: 0,
        destWidth: 0.7 * this.width,
        destHeight: 0.7 * this.width,
        canvasId: 'qrCanvas',
        success: function (res) {
          wx.saveImageToPhotosAlbum({
            filePath: res.tempFilePath,
            success () {
              wx.showModal({
                title: '存图成功',
                content: '图片成功保存到相册了，去发圈噻~',
                showCancel: false,
                confirmText: '好哒',
                confirmColor: '#72B9C3',
                success: function (res) {
                  if (res.confirm) {
                    console.log('用户点击确定')
                  }
                }
              })
            }
          })
        }
      })
    }
  },
  onReady () {
    this.width = wx.getSystemInfoSync().windowWidth
    const a = 0.15 * this.width
    const b = 0.7 * this.width
    const ctx = wx.createCanvasContext('qrCanvas')
    ctx.drawImage('../../../static/img/qrcode.jpg', a, a, b, b)
    ctx.draw(true)
  }
}
</script>
