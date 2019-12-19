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
    <Divider><b>新闻资讯</b></Divider>
    <List item-layout="vertical">
        <ListItem v-for="item in this.newslist" :key="item.id">
            <ListItemMeta :title="item.title" :description="item.author + ' ' + formatDatetime(item.createtime)" />
           <template style="width: 100%">{{ item.content }}</template>
            <template slot="extra">
                <img :src="item.img" style="width: 280px">
            </template>
        </ListItem>
    </List>
  </div>
</template>

<script>
import { getNewsList } from "@/api/news";
export default {
  name: "home",
  data() {
    return {
      value2: 0,
      value1: 0
    };
  },
  created() {
    getNewsList().then(res => {
      debugger;
      this.newslist = res.data.object;
    });
  },
  mounted() {
    //
  },
  methods: {
    formatDatetime(datatime) {
      return (
        datatime.substring(0, 4) +
        "年" +
        datatime.substring(5, 7) +
        "月" +
        datatime.substring(8, 10) +
        "日" +
        " " +
        datatime.substring(11, 19)
      );
    }
  }
};
</script>

<style lang="less">
.slide {
  height: 450px;
}
</style>