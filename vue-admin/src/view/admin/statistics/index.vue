<template>
  <div>
    <Row :gutter="20">
      <i-col :xs="12" :md="8" :lg="4" style="height: 120px;padding-bottom: 10px;">
        <infor-card shadow :color="inforCardData1.color" :icon="inforCardData1.icon" :icon-size="36">
          <h2><count-to :end="inforCardData1.count" count-class="count-style" /></h2>
          <b>{{ inforCardData1.title }}</b>
        </infor-card>
      </i-col>
      <i-col :xs="12" :md="8" :lg="4" style="height: 120px;padding-bottom: 10px;">
        <infor-card shadow :color="inforCardData2.color" :icon="inforCardData2.icon" :icon-size="36">
          <h2><count-to :end="inforCardData2.count" count-class="count-style" /></h2>
          <b>{{ inforCardData2.title }}</b>
        </infor-card>
      </i-col>
    </Row>
    <Row :gutter="20" style="margin-top: 10px;">
      <i-col :md="24" :lg="16" style="margin-bottom: 20px;">
        <Card shadow>
          <div class="echart-box" id="month" style="width: 800px;height:330px"></div>
        </Card>
      </i-col>
    </Row>
    <Row :gutter="20" style="margin-top: 10px;">
      <i-col :md="24" :lg="16" style="margin-bottom: 20px;">
        <Card shadow>
          <div class="echart-box" id="year" style="width: 800px;height:330px"></div>
        </Card>
      </i-col>
    </Row>
  </div>
</template>

<script>
import InforCard from "_c/info-card";
import CountTo from "_c/count-to";
import { getYear, getMonth, getDay, getYearTotle } from "@/api/statistics";
export default {
  name: "statistics",
  components: {
    InforCard,
    CountTo
  },
  methods: {
    drawLine(yearArr, monthArr) {
      let year = this.$echarts.init(document.getElementById("year"));
      year.setOption({
        color: ["#4ED8DA"],
        title: {
          text: "年销售额",
          x: 15,
          y: 15,
          textStyle: {
            fontSize: 16,
            fontWeight: "bolder",
            color: "#333"
          }
        },
        tooltip: {
          trigger: "axis"
        },
        grid: {
          borderWidth: 0,
          x: "10%",
          y: "20%",
          x2: "5%",
          y2: "15%"
        },
        calculable: true,
        xAxis: [
          {
            type: "category",
            axisLine: {
              show: false
            },
            splitLine: {
              show: false //不显示分割线
            },
            data: [
              "1月",
              "2月",
              "3月",
              "4月",
              "5月",
              "6月",
              "7月",
              "8月",
              "9月",
              "10月",
              "11月",
              "12月"
            ]
          }
        ],
        yAxis: [
          {
            type: "value",
            axisLine: {
              show: false
            }
          }
        ],
        series: [
          {
            name: "销售额",
            type: "bar",
            data: yearArr
          }
        ]
      });

      let month = this.$echarts.init(document.getElementById("month"));
      let monthTitle = []
      for (let i in monthArr) {
          monthTitle[i] = parseInt(i) + parseInt(1) + "日"
      }
      month.setOption({
        color: ["#4ED8DA"],
        title: {
          text: "月销售额",
          x: 15,
          y: 15,
          textStyle: {
            fontSize: 16,
            fontWeight: "bolder",
            color: "#333"
          }
        },
        tooltip: {
          trigger: "axis"
        },
        grid: {
          borderWidth: 0,
          x: "10%",
          y: "20%",
          x2: "5%",
          y2: "15%"
        },
        calculable: true,
        xAxis: [
          {
            type: "category",
            axisLine: {
              show: false
            },
            splitLine: {
              show: false //不显示分割线
            },
            data: monthTitle
          }
        ],
        yAxis: [
          {
            type: "value",
            axisLine: {
              show: false
            }
          }
        ],
        series: [
          {
            name: "销售额",
            type: "bar",
            data: monthArr
          }
        ]
      });

      //根据窗口的大小变动图表 --- 重点
      window.onresize = function() {
        myChart.resize();
        //myChart1.resize();    //若有多个图表变动，可多写
      };
    }
  },
  data() {
    return {
      inforCardData1: {
        title: "今日营业额",
        icon: "logo-usd",
        count: 0,
        color: "#2d8cf0"
      },
      inforCardData2: {
        title: "全年营业额",
        icon: "logo-usd",
        count: 0,
        color: "#ff9900"
      }
    };
  },
  mounted() {
    let monthArr = [];
    let yearArr = [];
    
    getDay().then(res => {
      this.inforCardData1.count = res.data.object;
    });
    getMonth().then(res => {
      debugger
      monthArr = res.data.object;
      getYear().then(res => {
        yearArr = res.data.object;
        this.drawLine(yearArr, monthArr);
      });
    });

    getYearTotle().then(res => {
      debugger
      this.inforCardData2.count = res.data.object;
    });
  }
};
</script>

<style lang="less" scoped>
.count-style {
  font-size: 50px;
}
</style>