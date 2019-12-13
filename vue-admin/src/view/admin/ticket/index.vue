<template>
  <div>
    <Row :gutter="16" style="margin-top: 10px;">
      <Col span="2">
        <Button type="primary" @click="showCreate">创建门票</Button>
      </Col>
      <Col span="8">
        <!-- <Input v-model="searchTxt">
          <Button slot="append" icon="ios-search" @click="search"></Button>
        </Input>-->
      </Col>
    </Row>
    <Tabs value="name1">
      <TabPane label="节目单" name="name1">
        <Table border :columns="clumns1" :data="programTicketList" style="margin-top: 10px;"></Table>
      </TabPane>
      <TabPane label="专场" name="name2">
        <Table border :columns="clumns2" :data="sessionTicketList" style="margin-top: 10px;"></Table>
      </TabPane>
    </Tabs>

    <Modal v-model="createModalFlg" title="创建门票" @on-ok="handleCreate">
      <Form :model="createModalObject" :label-width="140">
        <span style="color: red">场次及专场只能选择其一</span>
        <Form-item label="场次">
          <Select v-model="createModalObject.pid" style="width: 60%" clearable>
            <Option
              v-for="item in this.programList"
              :key="item.id"
              :value="item.id"
            >{{ formatDate(item.date) + item.theater.name }}</Option>
          </Select>
        </Form-item>
        <Form-item label="专场">
          <Select v-model="createModalObject.sid" style="width: 60%" clearable>
            <Option
              v-for="item in this.sessionList"
              :key="item.id"
              :value="item.id"
            >{{ formatDate(item.date) + item.sessionName }}</Option>
          </Select>
        </Form-item>
        <FormItem label="票价及数量设置">
          200：
          <InputNumber v-model="createModalObject.num200"></InputNumber>张
          <br />400：
          <InputNumber v-model="createModalObject.num400"></InputNumber>张
          <br />600：
          <InputNumber v-model="createModalObject.num600"></InputNumber>张
          <br />800：
          <InputNumber v-model="createModalObject.num800"></InputNumber>张
        </FormItem>
      </Form>
    </Modal>
    <Modal v-model="editModalFlg" title="编辑门票" @on-ok="handleUpdate">
      <Form :model="editModalObject" :label-width="140">
        <FormItem label="票价及数量设置">
          {{editModalObject.price}}：
          <InputNumber v-model="editModalObject.num"></InputNumber>张
        </FormItem>
      </Form>
    </Modal>
  </div>
</template>

<script>
import {
  createTicket,
  updateTicket,
  getTicketById,
  deleteTicketById,
  getProgramTicket,
  getSessionTicket
} from "@/api/ticket";
import { getProgramListForTicket } from "@/api/program";
import { getSessionListForTicket } from "@/api/session";
import { getTeamList, getTeamById } from "@/api/team";

import { log } from "util";
import { format } from "path";

export default {
  name: "ticket",
  data() {
    return {
      createModalObject: {},
      createModalFlg: false,
      editModalObject: {},
      editModalFlg: false,
      programList: [],
      sessionList: [],
      programTicketList: [],
      sessionTicketList: [],
      clumns1: [
        {
          title: "演出地点",
          key: "name",
          render: (h, params) => {
            return h("b", params.row.program.theater.name);
          }
        },
        {
          title: "演出时间",
          key: "date",
          align: "center",
          render: (h, params) => {
            return h("span", this.formatDatetime(params.row.program.date));
          }
        },
        {
          title: "票价",
          key: "team",
          align: "center",
          render: (h, params) => {
            return h("span", params.row.price);
          }
        },
        {
          title: "数量",
          key: "num",
          align: "center",
          render: (h, params) => {
            return h("span", params.row.num);
          }
        },
        {
          title: "状态",
          key: "state",
          align: "center",
          render: (h, params) => {
            return h("span", params.row.state === 0 ? "未发布" : "已发布");
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
                    type: params.row.state === 0 ? "primary" : "warning",
                    size: "small"
                  },
                  style: {
                    marginRight: "5px"
                  },
                  on: {
                    click: () => {
                      this.changeProgramState(params.index);
                    }
                  }
                },
                params.row.state === 0 ? "发布" : "撤回"
              ),
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
                      this.showProgramEdit(params.index);
                    }
                  }
                },
                "编辑"
              )
            ]);
          }
        }
      ],
      clumns2: [
        {
          title: "专场名称",
          key: "sessionName",
          render: (h, params) => {
            return h("b", params.row.session.sessionName);
          }
        },
        {
          title: "演出时间",
          key: "date",
          align: "center",
          render: (h, params) => {
            return h("span", this.formatDatetime(params.row.session.date));
          }
        },
        {
          title: "票价",
          key: "team",
          align: "center",
          render: (h, params) => {
            return h("span", params.row.price);
          }
        },
        {
          title: "数量",
          key: "num",
          align: "center",
          render: (h, params) => {
            return h("span", params.row.num);
          }
        },
        {
          title: "状态",
          key: "state",
          align: "center",
          render: (h, params) => {
            return h("span", params.row.state === 0 ? "未发布" : "已发布");
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
                    type: params.row.state === 0 ? "primary" : "warning",
                    size: "small"
                  },
                  style: {
                    marginRight: "5px"
                  },
                  on: {
                    click: () => {
                      this.changeSessionState(params.index);
                    }
                  }
                },
                params.row.state === 0 ? "发布" : "撤回"
              ),
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
                      this.showSessionEdit(params.index);
                    }
                  }
                },
                "编辑"
              )
            ]);
          }
        }
      ],
      ticketList: []
    };
  },
  methods: {
    changeCurrent(pageNum) {
      this.pageNum = pageNum;
      if (this.searchTxt === "") {
        getTicketListByPage(this.pageNum).then(res => {
          this.ticketList = res.data.object;
        });
      } else {
        getTicketListBySearchAndPage(this.searchTxt, this.pageNum).then(res => {
          this.ticketList = res.data.object;
        });
      }
    },
    search() {
      this.pageNum = 1;
      if (this.searchTxt === "") {
        getTicketCount().then(res => {
          this.total = res.data.object;
        });
        getTicketListByPage(this.pageNum).then(res => {
          this.ticketList = res.data.object;
        });
      } else {
        getTicketCountBySearch(this.searchTxt).then(res => {
          this.total = res.data.object;
        });
        getTicketListBySearchAndPage(this.searchTxt, this.pageNum).then(res => {
          this.ticketList = res.data.object;
        });
      }
    },
    handleCreate() {
      console.log(this.createModalObject);
      createTicket(this.createModalObject).then(res => {
        if (res.data.msg === "ok") {
          this.$Message.success("设置成功");
          this.createModalFlg = false;
          getTicketCount().then(res => {
            this.total = res.data.object;
          });
          getTicketListByPage(this.pageNum).then(res => {
            this.ticketList = res.data.object;
          });
        }
      });
    },
    handleUpdate() {
      updateTicket(this.editModalObject).then(res => {
        if (res.data.msg === "ok") {
          this.editModalFlg = false;
          getProgramTicket().then(res => {
            this.programTicketList = res.data.object;
          });
          getSessionTicket().then(res => {
            this.sessionTicketList = res.data.object;
          });
        }
      });
    },
    formatDate(datatime) {
      return (
        datatime.substring(0, 4) +
        "年" +
        datatime.substring(5, 7) +
        "月" +
        datatime.substring(8, 10) +
        "日"
      );
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
        num200: 1,
        num400: 1,
        num600: 1,
        num800: 1
      };
      this.createModalFlg = true;

      getProgramListForTicket().then(res => {
        this.programList = res.data.object;
      });
      getSessionListForTicket().then(res => {
        this.sessionList = res.data.object;
      });
    },
    showProgramEdit(index) {
      getTicketById(this.programTicketList[index].id).then(res => {
        this.editModalObject = res.data.object;
        this.editModalFlg = true;
      });
    },
    showSessionEdit(index) {
      getTicketById(this.sessionTicketList[index].id).then(res => {
        this.editModalObject = res.data.object;
        this.editModalFlg = true;
      });
    },
    // 上/下架
    changeProgramState(index) {
      this.programTicketList[index].state =
        this.programTicketList[index].state === 0 ? 1 : 0;
      updateTicket(this.programTicketList[index])
        .then(res => {
          if (res.data.msg === "ok") {
            getProgramTicket().then(res => {
              this.programTicketList = res.data.object;
            });
            getSessionTicket().then(res => {
              this.sessionTicketList = res.data.object;
            });
          } else {
            this.programTicketList[index].state =
              this.programTicketList[index].state === 0 ? 1 : 0;
          }
        })
        .catch(err => {
          this.programTicketList[index].state =
            this.programTicketList[index].state === 0 ? 1 : 0;
        });
    },
    // 上/下架
    changeSessionState(index) {
      this.sessionTicketList[index].state =
        this.sessionTicketList[index].state === 0 ? 1 : 0;
      updateTicket(this.sessionTicketList[index])
        .then(res => {
          if (res.data.msg === "ok") {
            getProgramTicket().then(res => {
              this.programTicketList = res.data.object;
            });
            getSessionTicket().then(res => {
              this.sessionTicketList = res.data.object;
            });
          } else {
            this.sessionTicketList[index].state =
              this.sessionTicketList[index].state === 0 ? 1 : 0;
          }
        })
        .catch(err => {
          this.sessionTicketList[index].state =
            this.sessionTicketList[index].state === 0 ? 1 : 0;
        });
    },
    handleCreateSuccess(res, file) {
      this.createModalObject.img = res.object;
    },
    handleUpdateSuccess(res, file) {
      this.editModalObject.img = res.object;
    },
    remove(index) {
      deleteTicketById(this.ticketList[index].id).then(res => {
        if (res.data.msg === "ok") {
          this.ticketList.splice(index, 1);
        }
      });
    }
  },
  mounted() {
    getProgramTicket().then(res => {
      this.programTicketList = res.data.object;
    });
    getSessionTicket().then(res => {
      this.sessionTicketList = res.data.object;
    });
  }
};
</script>

<style scoped>
</style>