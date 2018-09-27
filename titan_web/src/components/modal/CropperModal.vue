<template>
  <Modal @on-visible-change="visibleChange" :value="value">
    <p slot="header" class="head_div">图片编辑</p>
    <div class="cropper-content">
      <input ref="uploads" type="file" style="display:none" :accept="accept" @change="loadImg">
      <vueCropper :img="img" :full="full" :fixed="fixed" :outputType="outputType" :autoCrop="option.autoCrop"
                  :autoCropWidth="option.cropWidth" :autoCropHeight="option.cropHeight" ref="cropper"/>
    </div>
    <div slot="footer">
      <Button type="ghost" size="small" @click="onCancel">取 消</Button>
      <Button type="success" size="small" @click="upload()">打 开</Button>
      <Button type="info" size="small" style="width:26px" @click="changeScale(1)">+</Button>
      <Button type="info" size="small" style="width:26px" @click="changeScale(-1)">-</Button>
      <Button type="info" size="small" style="width:26px" @click="rotateLeft">↺</Button>
      <Button type="info" size="small" style="width:26px" @click="rotateRight">↻</Button>
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
    'outputType': {
      type: String,
      default: 'png'
    },
    'full': {
      type: Boolean,
      default: false
    },
    'fixed': {
      type: Boolean,
      default: true
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
          this.$emit('on-submit', response)
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
      const regex = /\.(gif|jpg|jpeg|png|bmp|GIF|JPG|PNG)$/
      if (!regex.test(e.target.value)) {
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
  .head_div {
    font-weight: normal;
    font-size: 14px;
    line-height: 24px
  }

  .cropper-content {
    width: 100%;
    height: 300px;
  }
</style>
