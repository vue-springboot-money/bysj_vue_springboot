<template>
  <div>
    <Row :gutter="16" style="margin-top: 10px;">
      <Col span="2">
        <Button type="primary" @click="showCreate">创建接班</Button>
      </Col>
      <Col span="8">
        <!-- <Input v-model="searchTxt">
          <Button slot="append" icon="ios-search" @click="search"></Button>
        </Input>-->
      </Col>
    </Row>

    <Table border :columns="clumns" :data="shiftList" style="margin-top: 10px;"></Table>
    <Page
      :total="total"
      size="small"
      @on-change="changeCurrent"
      style="position: fixed; right: 15px; bottom: 5px;"
    ></Page>
    <Modal v-model="createModalFlg" title="创建交接班" @on-ok="handleCreate">
      <Form :model="createModalObject" :label-width="140">
        <Form-item label="员工用户名">
          <Input v-model="createModalObject.username" disabled style="width: 60%" />
        </Form-item>
      </Form>
    </Modal>
  </div>
</template>

<script>
import {
  createShift,
  updateShift,
  updateShiftStateById,
  getShiftById,
  deleteShiftById,
  getShiftListByPage,
  getShiftCount,
  getShiftListBySearchAndPage,
  getShiftCountBySearch
} from "@/api/shift";

import { log } from "util";
import store from "@/store";

export default {
  name: "shift",
  data() {
    return {
      pageNum: 1,
      total: 0,
      searchTxt: "",
      createModalObject: {},
      createModalFlg: false,
      categoryList: [],
      clumns: [
        {
          title: "员工用户名",
          key: "uid",
          render: (h, params) => {
            return h("b", params.row.user.username);
          }
        },
        {
          title: "员工昵称",
          key: "uid",
          render: (h, params) => {
            return h("b", params.row.user.nickname);
          }
        },
        {
          title: "接班时间",
          key: "start",
          align: "center",
          render: (h, params) => {
            return h("span", this.formatDatetime(params.row.start));
          }
        },
        {
          title: "交班时间",
          key: "end",
          align: "center",
          render: (h, params) => {
            return h("span", this.formatDatetime(params.row.end));
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
                    type: params.row.end ? "primary" : "warning",
                    size: "small",
                    disabled: params.row.end ? "disabled" : false
                  },
                  style: {
                    marginRight: "5px"
                  },
                  on: {
                    click: () => {
                      this.changeState(params.index);
                    }
                  }
                },
                params.row.end ? "已交班" : "交班"
              )
            ]);
          }
        }
      ],
      shiftList: []
    };
  },
  methods: {
    changeCurrent(pageNum) {
      this.pageNum = pageNum;
      if (this.searchTxt === "") {
        getShiftListByPage(this.pageNum).then(res => {
          this.shiftList = res.data.object;
        });
      } else {
        getShiftListBySearchAndPage(this.searchTxt, this.pageNum).then(res => {
          this.shiftList = res.data.object;
        });
      }
    },
    search() {
      this.pageNum = 1;
      if (this.searchTxt === "") {
        getShiftCount().then(res => {
          this.total = res.data.object;
        });
        getShiftListByPage(this.pageNum).then(res => {
          this.shiftList = res.data.object;
        });
      } else {
        getShiftCountBySearch(this.searchTxt).then(res => {
          this.total = res.data.object;
        });
        getShiftListBySearchAndPage(this.searchTxt, this.pageNum).then(res => {
          this.shiftList = res.data.object;
        });
      }
    },
    handleCreate() {
      createShift(this.createModalObject).then(res => {
        if (res.data.msg === "ok") {
          this.createModalFlg = false;
          getShiftCount().then(res => {
            this.total = res.data.object;
          });
          getShiftListByPage(this.pageNum).then(res => {
            this.shiftList = res.data.object;
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
      this.createModalObject = {
        uid: store.state.user.userId,
        username: store.state.user.userName
      };
      this.createModalFlg = true;
    },
    // 交班
    changeState(index) {
      updateShift(this.shiftList[index])
        .then(res => {
          if (res.data.msg === "ok") {
            this.$Notice.success({
              title: "交班成功"
            });
            getShiftCount().then(res => {
              this.total = res.data.object;
            });
            getShiftListByPage(this.pageNum).then(res => {
              this.shiftList = res.data.object;
            });
          } else {
            this.$Notice.error({
              title: "交班失败"
            });
          }
        })
        .catch(err => {
          this.shiftList[index].state =
            this.shiftList[index].state === 0 ? 1 : 0;
        });
    },
    handleCreateSuccess(res, file) {
      this.createModalObject.img = res.object;
    },
    handleUpdateSuccess(res, file) {
      this.editModalObject.img = res.object;
    },
    remove(index) {
      deleteShiftById(this.shiftList[index].id).then(res => {
        if (res.data.msg === "ok") {
          this.shiftList.splice(index, 1);
        }
      });
    }
  },
  mounted() {
    getShiftCount().then(res => {
      this.total = res.data.object;
    });
    getShiftListByPage(this.pageNum).then(res => {
      this.shiftList = res.data.object;
    });
  }
};
</script>

<style scoped>
</style>