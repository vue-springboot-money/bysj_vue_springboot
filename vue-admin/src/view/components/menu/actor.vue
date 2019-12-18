<template>
  <div>
    <!-- 队伍列表 -->
    <Row type="flex" justify="start" :gutter="16" v-if="!tableShowFlg">
      <Col span="4" v-for="item in this.teamList" :key="item.id" style="margin-bottom: 10px;">
        <a @click="showActorList(item.id)">
          <Card style="text-align: center">
            <img :src="item.img" width="200px" height="200px" />
            <h2 slot="title">{{ item.name }}</h2>
            <h3>{{item.introduce}}</h3>
          </Card>
        </a>
      </Col>
    </Row>
    <!-- 演员列表 -->
    <div v-if="tableShowFlg">
      <Button type="primary" @click="() => {this.tableShowFlg = false;}">
        <Icon type="ios-arrow-back"></Icon>返回上一级
      </Button>
      <Row type="flex" justify="start" :gutter="16" style="margin-top: 20px">
        <Col span="5" style="margin-bottom: 10px;">
          <img :src="team.img" width="300px" height="300px" />
        </Col>
        <Col span="4" style="margin-bottom: 10px;">
          <h1>{{team.name}}</h1>
          <br />
          <h2>{{team.introduce}}</h2>
        </Col>
      </Row>
      <Table border :columns="clumns" :data="actorList" style="margin-top: 20px;"></Table>
    </div>
  </div>
</template>

<script>
import { getTeamList, getTeamById } from "@/api/team";
import { getActorListByTid } from "@/api/actor";
export default {
  name: "dys-actor",
  data() {
    return {
      // 用于队伍详情上半部分
      team: {},
      // 队伍列表
      teamList: [],
      // 演员列表
      actorList: [],
      // 队伍列表与演员列表切换flag
      tableShowFlg: false,
      // table的配置对象
      clumns: [
        {
          title: "演员名",
          key: "name",
          width: 150,
          render: (h, params) => {
            return h("b", params.row.name);
          }
        },
        {
          title: "图片",
          key: "img",
          align: "center",
          width: 190,
          render: (h, params) => {
            return h("img", {
              style: {
                width: "150px",
                hight: "150px"
              },
              attrs: {
                src: params.row.img
              }
            });
          }
        },
        {
          title: "信息",
          key: "information",
          align: "center"
        },
        {
          title: "代表作",
          key: "masterpiece",
          align: "center"
        }
      ]
    };
  },
  methods: {
    /**
     * 展示所选队伍的演员列表
     */
    showActorList(tid) {
      this.$Loading.start();
      getTeamById(tid).then(res => {
        this.team = res.data.object;
        getActorListByTid(tid).then(res => {
          this.actorList = res.data.object;
          this.$Loading.finish();
        });
      });
      this.tableShowFlg = true;
    }
  },
  mounted() {
    this.$Loading.start();
    /**
     * 获取队伍列表
     */
    getTeamList().then(res => {
      this.teamList = res.data.object;
      this.$Loading.finish();
    });
  }
};
</script>

<style>
</style>