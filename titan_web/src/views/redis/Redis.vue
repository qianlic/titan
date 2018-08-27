<template>
  <div>
    <Card style="margin: 20px" v-for="(data,key,idx) in datas" :key="idx">
      <p slot="title">
        <Icon type="help-buoy"></Icon>
        {{key}}
      </p>
      <p v-for="(item,key,idx) in data" :key="idx">
        <Row>
          <Col span="8">{{key}}</Col>
          <Col span="8">{{item}}</Col>
        </Row>
      </p>
    </Card>
  </div>
</template>

<script>
import {mapGetters, mapActions} from 'vuex'

export default {
  name: 'redis',
  computed: {
    ...mapGetters('redis', [
      'datas'
    ])
  },
  methods: {
    ...mapActions('redis', [
      'loadDatas'
    ])
  },
  created () {
    this.loadDatas()
  }
}
</script>
