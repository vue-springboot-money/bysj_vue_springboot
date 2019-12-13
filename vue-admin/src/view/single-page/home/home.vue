<template>
  <div>
    <Carousel autoplay v-model="value2" loop height="300px">
      <CarouselItem>
        <div class="demo-carousel">
          <img src="../../../assets/images/slide1.jpg" class="slide" />
        </div>
      </CarouselItem>
      <CarouselItem>
        <div class="demo-carousel">
          <img src="../../../assets/images/slide2.jpg" class="slide" />
        </div>
      </CarouselItem>
      <CarouselItem>
        <div class="demo-carousel">
          <img src="../../../assets/images/slide1.jpg" class="slide" />
        </div>
      </CarouselItem>
      <CarouselItem>
        <div class="demo-carousel">
          <img src="../../../assets/images/slide2.jpg" class="slide" />
        </div>
      </CarouselItem>
    </Carousel>
    <Collapse v-for="(news,index) in newslist" v-model="value1" accordion>
      <Panel :name="`${index+1}`">
        {{news.title}}
        <p slot="content">
          <img :src="news.img" width="100%" />
          {{news.content}}
        </p>
      </Panel>
    </Collapse>
  </div>
</template>

<script>
import { getNewsListByPage } from "@/api/news";
export default {
  name: "home",
  data() {
    return {
      value2: 0,
      value1: 0,
      pageid: 1
    };
  },
  created() {
    getNewsListByPage(this.pageid).then(res => {
      debugger;
      this.newslist = res.data.object;
    });
  },
  mounted() {
    //
  }
};
</script>

<style lang="less">
.slide {
  height: 450px;
}
</style>