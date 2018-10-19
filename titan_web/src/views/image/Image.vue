<template>
  <div>
    <PageTable ref="table" :columns="columns" :searchs="searchs" :buttons="buttons" :data="datas"
               @select-row="setSelectRows" @load-data="loadDatas" selection/>
    <CropperModal v-model="isShowUploadModle" @on-submit="uploadHandler" :full="option.full" :fixed="option.fixed"/>
  </div>
</template>

<script>
import PageTable from '../../components/table/PageTable'
import CropperModal from '../../components/modal/CropperModal'
import {mapGetters, mapActions} from 'vuex'

export default {
  name: 'image',
  data () {
    return {
      isShowUploadModle: false,
      option: {
        full: true,
        fixed: false
      },
      columns: [{
        title: '图片',
        type: 'image',
        key: 'url'
      }, {
        title: '名称',
        key: 'filename'
      }, {
        title: '路径',
        key: 'url'
      }, {
        title: '高度',
        width: 80,
        key: 'height'
      }, {
        title: '宽度',
        width: 80,
        key: 'width'
      }, {
        title: '大小',
        width: 80,
        key: 'size'
      }, {
        title: '操作',
        width: 80,
        type: 'operation',
        buttons: [{
          title: '删除',
          type: 'error',
          click: ({hash}) => this.deleteData(hash)
        }]
      }],
      searchs: [{
        title: '图片名',
        name: 'filename',
        type: 'input'
      }],
      buttons: [ {
        title: '图片上传',
        type: 'info',
        icon: 'upload',
        on: this.showUploadModal
      }]
    }
  },
  components: {
    PageTable,
    CropperModal
  },
  computed: {
    ...mapGetters('image', [
      'datas',
      'selectIds'
    ])
  },
  methods: {
    ...mapActions('image', [
      'loadDatas',
      'removeDatas',
      'setSelectRows'
    ]),
    deleteData (hash) {
      this.removeDatas({hash}).then(response => {
        if (response.success) {
          this.$refs.table.pageChange()
          this.$Message.success(response.message)
        }
      })
    },
    showUploadModal () {
      this.isShowUploadModle = true
    },
    uploadHandler () {
      this.$refs.table.pageChange()
      this.isShowUploadModle = false
    }
  }
}
</script>
