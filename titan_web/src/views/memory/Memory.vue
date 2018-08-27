<template>
  <Card>
    <Alert type="success">{{memoryDetails}}</Alert>
    <Row>
      <Col span="12">
        <Echarts style="width: 100%;height:400px;" title="物理内存占用" :data="fhysicalUsed"/>
      </Col>
      <Col span="12">
        <Echarts style="width: 100%;height:400px;" title="虚拟内存占用" :data="swapUsed"/>
      </Col>
    </Row>
    <Row>
      <Col span="12">
        <Echarts style="width: 100%;height:400px;" title="JVM内存占用" :data="memoryUsed"/>
      </Col>
    </Row>
  </Card>
</template>

<script>
import Echarts from '../../components/chart/Echarts'
import {mapGetters, mapActions} from 'vuex'

export default {
  name: 'memory',
  components: {
    Echarts
  },
  computed: {
    ...mapGetters('memory', [
      'memoryUsed',
      'fhysicalUsed',
      'swapUsed',
      'memoryDetails'
    ])
  },
  methods: {
    ...mapActions('memory', [
      'loadDatas'
    ])
  },
  created () {
    this.loadDatas()
  }
}
</script>
