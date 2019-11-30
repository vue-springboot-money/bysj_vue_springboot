<template>
  <div>
    <Row :gutter="16" style="margin-top: 10px;">
      <Col span="2">
        <Button type="primary" @click="showCreate">创建队伍</Button>
      </Col>
      <Col span="8">
        <Input v-model="searchTxt">
          <Button slot="append" icon="ios-search" @click="search"></Button>
        </Input>
      </Col>
    </Row>

    <Table border :columns="clumns" :data="teamList" style="margin-top: 10px;"></Table>
    <Page
      :total="total"
      size="small"
      @on-change="changeCurrent"
      style="position: fixed; right: 15px; bottom: 5px;"
    ></Page>
    <Modal v-model="createModalFlg" title="创建队伍" @on-ok="handleCreate">
      <Form :model="createModalObject" :label-width="140">
        <Form-item label="队伍名">
          <Input v-model="createModalObject.name" placeholder="请输入" style="width: 60%" />
        </Form-item>
      </Form>
    </Modal>
    <Modal v-model="editModalFlg" title="编辑队伍" @on-ok="handleUpdate">
      <Form :model="editModalObject" :label-width="140">
        <Form-item label="队伍名">
          <Input v-model="editModalObject.name" placeholder="请输入" style="width: 60%" />
        </Form-item>
      </Form>
    </Modal>
  </div>
</template>

<script>
import {
  createTeam,
  updateTeam,
  updateTeamStateById,
  getTeamById,
  deleteTeamById,
  getTeamListByPage,
  getTeamCount,
  getTeamListBySearchAndPage,
  getTeamCountBySearch
} from "@/api/team";
import { log } from "util";

export default {
  name: "team",
  data() {
    return {
      pageNum: 1,
      total: 0,
      searchTxt: "",
      createModalObject: {},
      createModalFlg: false,
      editModalObject: {},
      editModalFlg: false,
      clumns: [
        {
          title: "队伍名",
          key: "name",
          align: "center",
          render: (h, params) => {
            return h("b", params.row.name);
          }
        },
        {
          title: "创建时间",
          key: "createtime",
          align: "center",
          render: (h, params) => {
            return h("span", this.formatDatetime(params.row.createtime));
          }
        },
        {
          title: "操作",
          key: "action",
          width: 180,
          align: "center",
          render: (h, params) => {
            return h("div", [
              h(
                "Button",
                {
                  props: {
                    type: "primary",
                    size: "small"
                  },
                  style: {
                    marginRight: "5px"
                  },
                  on: {
                    click: () => {
                      this.showEdit(params.index);
                    }
                  }
                },
                "编辑"
              ),
              h(
                "Button",
                {
                  props: {
                    type: "error",
                    size: "small"
                  },
                  on: {
                    click: () => {
                      this.remove(params.index);
                    }
                  }
                },
                "删除"
              )
            ]);
          }
        }
      ],
      teamList: []
    };
  },
  methods: {
    changeCurrent(pageNum) {
      this.pageNum = pageNum;
      if (this.searchTxt === "") {
        getTeamCount().then(res => {
          this.total = res.data.object;
        });
        getTeamListByPage(this.pageNum).then(res => {
          this.teamList = res.data.object;
        });
      } else {
        getTeamCountBySearch(this.searchTxt).then(res => {
          this.total = res.data.object;
        });
        getTeamListBySearchAndPage(this.searchTxt, this.pageNum).then(res => {
          this.teamList = res.data.object;
        });
      }
    },
    search() {
      this.pageNum = 1;
      if (this.searchTxt === "") {
        getTeamCount().then(res => {
          this.total = res.data.object;
        });
        getTeamListByPage(this.pageNum).then(res => {
          this.teamList = res.data.object;
        });
      } else {
        getTeamCountBySearch(this.searchTxt).then(res => {
          this.total = res.data.object;
        });
        getTeamListBySearchAndPage(this.searchTxt, this.pageNum).then(res => {
          this.teamList = res.data.object;
        });
      }
    },
    handleCreate() {
      createTeam(this.createModalObject).then(res => {
        if (res.data.msg === "ok") {
          this.createModalFlg = false;
          getTeamCount().then(res => {
            this.total = res.data.object;
          });
          getTeamListByPage(this.pageNum).then(res => {
            this.teamList = res.data.object;
          });
        }
      });
    },
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
    },
    showCreate() {
      this.createModalObject = {};
      this.createModalFlg = true;
    },
    showEdit(index) {
      getTeamById(this.teamList[index].id).then(res => {
        this.editModalObject = res.data.object;
        this.editModalFlg = true;
      });
    },
    remove(index) {
      deleteTeamById(this.teamList[index].id).then(res => {
        if (res.data.msg === "ok") {
          this.teamList.splice(index, 1);
        }
      });
    }
  },
  mounted() {
    getTeamCount().then(res => {
      this.total = res.data.object;
    });
    getTeamListByPage(this.pageNum).then(res => {
      this.teamList = res.data.object;
    });
  }
};
</script>

<style scoped>
</style>