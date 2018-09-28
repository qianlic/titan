<template>
  <div :style="{height: height+'px'}">
    <quillEditor ref="editor" @change="onChange" :value="value" :options="options" :style="{height: height-42+'px'}"/>
    <CropperModal v-model="isShowUploadModle" @on-submit="uploadHandler" :full="full" :fixed="fixed"/>
  </div>
</template>

<script>
import CropperModal from './modal/CropperModal'
import {quillEditor} from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import '../styles/quill.snow.css'

export default {
  name: 'vue-quill-editor',
  data () {
    const toolbar = [
      ['bold', 'italic', 'underline', 'strike', { 'script': 'sub' }, { 'script': 'super' }],
      [{ 'list': 'ordered' }, { 'list': 'bullet' }, { 'align': [] }, { 'indent': '-1' }, { 'indent': '+1' }],
      [{ 'size': ['small', false, 'large', 'huge'] }, { 'font': [] }, { 'color': [] }, { 'background': [] }],
      ['code-block', 'blockquote', 'clean'],
      ['link', 'image']
    ]
    return {
      isShowUploadModle: false,
      full: true,
      fixed: false,
      options: {
        modules: {
          toolbar
        },
        placeholder: null,
        theme: 'snow'
      }
    }
  },
  props: {
    'value': {
      type: String
    },
    'height': {
      type: Number,
      default: 200
    }
  },
  components: {
    quillEditor,
    CropperModal
  },
  computed: {
    editor () {
      return this.$refs.editor.quill
    }
  },
  methods: {
    onChange ({html}) {
      this.$emit('input', html)
    },
    imgHandler () {
      this.isShowUploadModle = true
    },
    uploadHandler (response) {
      const range = this.editor.getSelection()
      this.editor.insertEmbed(range != null ? range.index : 0, 'image', response.data.url, 'Quill.sources.USER')
      this.isShowUploadModle = false
    }
  },
  mounted () {
    this.editor.getModule('toolbar').addHandler('image', this.imgHandler)
  }
}
</script>
