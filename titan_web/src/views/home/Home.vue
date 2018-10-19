<template>
  <Carousel v-model="value" loop>
    <CarouselItem>
      <div class="demo-carousel">1</div>
    </CarouselItem>
    <CarouselItem>
      <div class="demo-carousel">2</div>
    </CarouselItem>
    <CarouselItem>
      <div class="demo-carousel">3</div>
    </CarouselItem>
    <CarouselItem>
      <div class="demo-carousel">4</div>
    </CarouselItem>
  </Carousel>
</template>

<script>
export default {
  name: 'Home',
  data () {
    return {
      value: 0
    }
  }
}
</script>

<style scoped>
  .demo-carousel{
    width: 100%;
    height: 400px;
    line-height: 400px;
    text-align: center;
    background: #506b9e;
    font-size: 20px;
    color: #fff;
  }
</style>
