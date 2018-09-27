<template>
  <div class="hello">
    <QuillEditor v-model="msg"/>
  </div>
</template>

<script>
import QuillEditor from '../../components/VueQuillEditor'

export default {
  name: 'Home',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App2'
    }
  },
  components: {
    QuillEditor
  }
}
</script>

<style scoped>
h1 {
  font-weight: normal;
}
</style>
