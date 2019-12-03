<template>
  <div>
    <Row :gutter="16" style="margin-top: 10px;">
      <Col span="2">
        <Button type="primary" @click="showCreate">创建台号</Button>
      </Col>
      <Col span="8">
        <Input v-model="searchTxt">
          <Button slot="append" icon="ios-search" @click="search"></Button>
        </Input>
      </Col>
    </Row>

    <Table border :columns="clumns" :data="deskList" style="margin-top: 10px;"></Table>
    <Page
      :total="total"
      size="small"
      @on-change="changeCurrent"
      style="position: fixed; right: 15px; bottom: 5px;"
    ></Page>
    <Modal v-model="createModalFlg" title="创建台号" @on-ok="handleCreate">
      <Form :model="createModalObject" :label-width="140">
        <Form-item label="台号名">
          <Input v-model="createModalObject.name" placeholder="请输入" style="width: 60%" />
        </Form-item>
      </Form>
    </Modal>
    <Modal v-model="editModalFlg" title="编辑台号" @on-ok="handleUpdate">
      <Form :model="editModalObject" :label-width="140">
        <Form-item label="台号名">
          <Input v-model="editModalObject.name" placeholder="请输入" style="width: 60%" />
        </Form-item>
      </Form>
    </Modal>
  </div>
</template>

<script>
import {
  createDesk,
  updateDesk,
  getDeskById,
  deleteDeskById,
  getDeskListByPage,
  getDeskCount,
  getDeskListBySearchAndPage,
  getDeskCountBySearch
} from "@/api/desk";

import { log } from "util";

export default {
  name: "desk",
  data() {
    return {
      pageNum: 1,
      total: 0,
      searchTxt: "",
      createModalObject: {},
      createModalFlg: false,
      editModalObject: {},
      editModalFlg: false,
      teamList: [],
      clumns: [
        {
          title: "台号名",
          key: "name",
          render: (h, params) => {
            return h("b", params.row.name);
          }
        },
        {
          title: "状态",
          key: "state",
          align: "center",
          render: (h, params) => {
            return h(
              "span",
              params.row.state === 0
                ? "空闲"
                : params.row.state === 1
                ? "在用"
                : ""
            );
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
      deskList: []
    };
  },
  methods: {
    changeCurrent(pageNum) {
      this.pageNum = pageNum;
      if (this.searchTxt === "") {
        getDeskListByPage(this.pageNum).then(res => {
          this.deskList = res.data.object;
        });
      } else {
        getDeskListBySearchAndPage(this.searchTxt, this.pageNum).then(res => {
          this.deskList = res.data.object;
        });
      }
    },
    search() {
      this.pageNum = 1;
      if (this.searchTxt === "") {
        getDeskCount().then(res => {
          this.total = res.data.object;
        });
        getDeskListByPage(this.pageNum).then(res => {
          this.deskList = res.data.object;
        });
      } else {
        getDeskCountBySearch(this.searchTxt).then(res => {
          this.total = res.data.object;
        });
        getDeskListBySearchAndPage(this.searchTxt, this.pageNum).then(res => {
          this.deskList = res.data.object;
        });
      }
    },
    handleCreate() {
      createDesk(this.createModalObject).then(res => {
        if (res.data.msg === "ok") {
          this.createModalFlg = false;
          getDeskCount().then(res => {
            this.total = res.data.object;
          });
          getDeskListByPage(this.pageNum).then(res => {
            this.deskList = res.data.object;
          });
        }
      });
    },
    handleUpdate() {
      updateDesk(this.editModalObject).then(res => {
        if (res.data.msg === "ok") {
          this.editModalFlg = false;
          getDeskCount().then(res => {
            this.total = res.data.object;
          });
          getDeskListByPage(this.pageNum).then(res => {
            this.deskList = res.data.object;
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
    handleFormatError(file) {
      this.$Notice.warning({
        title: "文件格式不正确",
        desc:
          "文件 " + file.name + " 格式不正确，请上传 jpg 或 png 格式的图片。"
      });
    },
    showCreate() {
      this.createModalObject = {
        name: "",
      };
      this.createModalFlg = true;
    },
    showEdit(index) {
      getDeskById(this.deskList[index].id).then(res => {
        this.editModalObject = res.data.object;
        this.editModalFlg = true;
      });
    },
    // 上/下架
    changeState(index) {
      this.deskList[index].state = this.deskList[index].state === 0 ? 1 : 0;
      updateDesk(this.deskList[index])
        .then(res => {
          if (res.data.msg === "ok") {
            getDeskCount().then(res => {
              this.total = res.data.object;
            });
            getDeskListByPage(this.pageNum).then(res => {
              this.deskList = res.data.object;
            });
          } else {
            this.deskList[index].state =
              this.deskList[index].state === 0 ? 1 : 0;
          }
        })
        .catch(err => {
          this.deskList[index].state = this.deskList[index].state === 0 ? 1 : 0;
        });
    },
    handleCreateSuccess(res, file) {
      this.createModalObject.img = res.object;
    },
    handleUpdateSuccess(res, file) {
      this.editModalObject.img = res.object;
    },
    remove(index) {
      deleteDeskById(this.deskList[index].id).then(res => {
        if (res.data.msg === "ok") {
          this.deskList.splice(index, 1);
        }
      });
    }
  },
  mounted() {
    getDeskCount().then(res => {
      this.total = res.data.object;
    });
    getDeskListByPage(this.pageNum).then(res => {
      this.deskList = res.data.object;
    });
    getTeamList().then(res => {
      this.teamList = res.data.object;
    });
  }
};
</script>

<style scoped>
</style>