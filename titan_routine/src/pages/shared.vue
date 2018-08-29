<template>
  <view>
    <canvas style="width: 300px; height: 400px;" canvas-id="qrCanvas"/>
    <footbar current="shared"/>
  </view>
</template>

<script>
import footbar from '@/components/footbar'
import Bmob from '@/utils/bmob'

export default {
  name: 'shared',
  data () {
    return {
      picUrl: undefined
    }
  },
  components: {
    footbar
  },
  mounted () {
    let qrData = {
      path: 'pages/home',
      width: 200,
      interface: 'c',
      scene: 'titan_routine'
    }
    Bmob.generateCode(qrData).then(response => {
      const img = 'data:image/jpeg;base64,' + response.imageBytes
      const ctx = wx.createCanvasContext('qrCanvas')
      ctx.drawImage(img, 0, 0, 200, 200)
      ctx.draw()
    })

    Bmob.Query('banner').find().then(res => {
      console.log(res)
    })
  }
}
</script>
