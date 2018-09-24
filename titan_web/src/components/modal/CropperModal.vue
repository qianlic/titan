<template>
  <Modal @on-visible-change="visibleChange" :value="value">
    <p slot="header" class="head_div">图片编辑</p>
    <div class="cropper-content">
        <vueCropper ref="cropper" :img="img" :outputType="option.outputType" :fixed="option.fixed"
                    :autoCropWidth="option.cropWidth" :autoCropHeight="option.cropHeight"
                    :autoCrop="option.autoCrop" :original="option.original"/>
    </div>
    <div class="footer-btn">
      <input ref="uploads" type="file" style="display:none" :accept="accept" @change="loadImg">
      <Button size="small" type="info" @click="upload()">更换图片</Button>
      <Button size="small" type="info" style="width:26px" @click="changeScale(1)">+</Button>
      <Button size="small" type="info" style="width:26px" @click="changeScale(-1)">-</Button>
      <Button size="small" type="info" style="width:26px" @click="rotateLeft">↺</Button>
      <Button size="small" type="info" style="width:26px" @click="rotateRight">↻</Button>
    </div>
    <div slot="footer">
      <Button type="ghost" size="small" @click="onCancel">取 消</Button>
      <Button type="primary" size="small" @click="onSubmit">确 认</Button>
    </div>
  </Modal>
</template>

<script>
import vueCropper from 'vue-cropper'
import uuid from 'node-uuid'
import request from '../../api/image'

export default {
  name: 'cropper-modal',
  data () {
    return {
      img: '',
      accept: 'image/png,image/jpeg,image/gif,image/jpg',
      option: {
        fixed: true,
        original: true,
        outputType: 'png',
        autoCrop: true,
        cropWidth: 200,
        cropHeight: 200
      }
    }
  },
  props: {
    'imgurl': {
      type: String
    },
    'value': {
      type: Boolean,
      default: false
    }
  },
  methods: {
    onSubmit () {
      this.$refs.cropper.getCropBlob((data) => {
        const form = new FormData()
        form.append('file', data, uuid.v1() + '.png')
        request.upload(form).then(response => {
          this.$emit('on-submit', response.data)
        })
      })
    },
    onCancel () {
      this.visibleChange(false)
    },
    visibleChange (show) {
      if (show) {
        this.img = this.imgurl
      }
      this.$emit('input', show)
    },
    loadImg (e) {
      const file = e.target.files[0]
      if (!/\.(gif|jpg|jpeg|png|bmp|GIF|JPG|PNG)$/.test(e.target.value)) {
        this.$Message.error('图片类型必须是.gif,jpeg,jpg,png,bmp中的一种')
        return
      }
      const reader = new FileReader()
      reader.onload = (e) => {
        const result = new Blob([e.target.result])
        this.img = window.URL.createObjectURL(result)
      }
      reader.readAsArrayBuffer(file)
    },
    changeScale (num) {
      this.$refs.cropper.changeScale(num)
    },
    rotateLeft () {
      this.$refs.cropper.rotateLeft()
    },
    rotateRight () {
      this.$refs.cropper.rotateRight()
    },
    upload () {
      this.$refs.uploads.click()
    }
  },
  components: {
    vueCropper
  }
}
</script>

<style scoped>
  .head_div{
    font-weight:normal;
    font-size:14px;
    line-height:24px
  }
  .cropper-content {
    width: 350px;
    height: 300px;
  }
  .footer-btn {
    margin-top: 20px;
  }
</style>
