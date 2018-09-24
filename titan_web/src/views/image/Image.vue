<template>
  <div>
    <PageTable ref="table" :columns="columns" :searchs="searchs" :buttons="buttons" :data="datas"
               @select-row="setSelectRows" @load-data="loadDatas" selection/>
    <PageModal title="上传图片" v-model="isShowUploadModle" @on-submit="changeUpload">
      <Form label-position="left" :label-width="60">
        <Upload type="drag" action="/api/system/image/upload" name="file" :headers="headers">
          <div style="padding: 20px 0">
            <Icon type="ios-cloud-upload" size="52" style="color: #3399ff"></Icon>
            <p>Click or drag files here to upload</p>
          </div>
        </Upload>
      </Form>
    </PageModal>
  </div>
</template>

<script>
import PageTable from '../../components/table/PageTable'
import PageModal from '../../components/modal/PageModal'
import {mapGetters, mapActions} from 'vuex'
import localStore from '../../store/localStore'

export default {
  name: 'image',
  data () {
    const AUTH_TOKEN = localStore.getAuthTokenItem('Authorization')
    return {
      isShowUploadModle: false,
      headers: {
        'Authorization': AUTH_TOKEN
      },
      columns: [{
        title: '图片',
        type: 'image',
        domain: 'https://i.loli.net/',
        key: 'path'
      }, {
        title: '名称',
        key: 'filename'
      }, {
        title: '路径',
        key: 'path'
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
    PageModal
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
    createData () {
      this.$router.push({
        name: 'userFrom',
        params: {iscreate: true}
      })
    },
    deleteData (hash) {
      this.removeDatas({hash}).then(response => {
        if (response.status === 0) {
          this.$refs.table.pageChange()
          this.$Message.success(response.message)
        }
      })
    },
    showUploadModal () {
      this.isShowUploadModle = true
    },
    changeUpload () {
      this.$refs.table.pageChange()
      this.isShowUploadModle = false
    }
  }
}
</script>
