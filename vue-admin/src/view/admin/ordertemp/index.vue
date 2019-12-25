<template>
  <div>
    <Row :gutter="16" style="margin-top: 10px;">
      <Col span="2">
        <Button type="primary" @click="showCreate">创建点菜</Button>
      </Col>
      <Col span="8">
        <Button type="warning" @click="showSubmit">结账</Button>
      </Col>
    </Row>

    <Table border :columns="clumns" :data="ordertempList" style="margin-top: 10px;"></Table>
    <Page
      :total="total"
      size="small"
      @on-change="changeCurrent"
      style="position: fixed; right: 15px; bottom: 5px;"
    ></Page>
    <Modal v-model="createModalFlg" title="创建点菜" @on-ok="handleCreate">
      <Form :model="createModalObject" :label-width="140">
        <Form-item label="台号">
          <Select v-model="createModalObject.did" style="width: 60%">
            <Option v-for="item in deskList" :key="item.id" :value="item.id">{{ item.name }}</Option>
          </Select>
        </Form-item>
        <Form-item label="菜品">
          <Select v-model="createModalObject.mid" style="width: 60%">
            <Option v-for="item in menuList" :key="item.id" :value="item.id">{{ item.name }}</Option>
          </Select>
        </Form-item>
        <Form-item label="数量">
          <InputNumber :max="999" :min="1" v-model="createModalObject.amount"></InputNumber>
        </Form-item>
      </Form>
    </Modal>
    <Modal v-model="editModalFlg" title="编辑点菜" @on-ok="handleUpdate">
      <Form :model="editModalObject" :label-width="140">
        <Form-item label="台号">
          <Select v-model="editModalObject.did" style="width: 60%">
            <Option v-for="item in deskListUsed" :key="item.id" :value="item.id">{{ item.name }}</Option>
          </Select>
        </Form-item>
        <Form-item label="菜品">
          <Select v-model="editModalObject.mid" style="width: 60%">
            <Option v-for="item in menuList" :key="item.id" :value="item.id">{{ item.name }}</Option>
          </Select>
        </Form-item>
        <Form-item label="数量">
          <InputNumber :max="999" :min="1" v-model="editModalObject.amount"></InputNumber>
        </Form-item>
      </Form>
    </Modal>
    <Modal v-model="submitModalFlg" title="结账" @on-ok="handleSubmit">
      <Form :model="submitModalObject" :label-width="140">
        <Form-item label="台号">
          <Select style="width: 60%" v-model="submitModalObject.did" @on-change="getOrdertemp">
            <Option v-for="item in deskListUsed" :key="item.id" :value="item.id">{{ item.name }}</Option>
          </Select>
        </Form-item>
        <Form-item label="详情">
          <List size="small" style="width: 60%">
            <ListItem v-for="item in orderList" :key="item">
              <span>{{item.menu.name}} {{item.amount}}</span>&nbsp;
              <span style="color: red" v-if="item.state == 0">未签单</span>
              <span style="color: green" v-if="item.state == 1">已签单</span>
            </ListItem>
          </List>
        </Form-item>
      </Form>
    </Modal>
  </div>
</template>

<script>
import {
  createOrdertemp,
  updateOrdertemp,
  updateOrdertempStateById,
  getOrdertempById,
  getOrdertempByDid,
  deleteOrdertempById,
  getOrdertempListByPage,
  getOrdertempCount,
  getOrdertempListBySearchAndPage,
  getOrdertempCountBySearch
} from "@/api/ordertemp";
import { createOrder } from "@/api/order";
import { getDeskByState, getDeskList } from "@/api/desk";
import { getCategoryList } from "@/api/category";
import { getMenuList } from "@/api/menu";

import { log } from "util";

export default {
  name: "ordertemp",
  data() {
    return {
      pageNum: 1,
      total: 0,
      searchTxt: "",
      createModalObject: {},
      createModalFlg: false,
      editModalObject: {},
      editModalFlg: false,
      submitModalObject: {},
      submitModalFlg: false,
      categoryList: [],
      deskListUsed: [],
      deskList: [],
      menuList: [],
      orderList: [],
      clumns: [
        {
          title: "台号",
          key: "did",
          sortable: true,
          render: (h, params) => {
            return h("b", params.row.desk.name);
          }
        },
        {
          title: "菜单",
          key: "img",
          align: "center",
          render: (h, params) => {
            return h("span", params.row.menu.name);
          }
        },
        {
          title: "数量",
          key: "amount",
          align: "center",
          render: (h, params) => {
            return h("span", params.row.amount);
          }
        },
        {
          title: "价格",
          key: "cid",
          align: "center",
          render: (h, params) => {
            return h("span", params.row.menu.price * params.row.amount);
          }
        },
        {
          title: "状态",
          key: "state",
          align: "center",
          render: (h, params) => {
            return h("span", params.row.state === 0 ? "未签单" : "已签单");
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
                      this.changeState(params.index);
                    }
                  }
                },
                params.row.state === 0 ? "签单" : "撤回"
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
      ordertempList: []
    };
  },
  methods: {
    changeCurrent(pageNum) {
      this.pageNum = pageNum;
      if (this.searchTxt === "") {
        getOrdertempListByPage(this.pageNum).then(res => {
          this.ordertempList = res.data.object;
        });
      } else {
        getOrdertempListBySearchAndPage(this.searchTxt, this.pageNum).then(
          res => {
            this.ordertempList = res.data.object;
          }
        );
      }
    },
    search() {
      this.pageNum = 1;
      if (this.searchTxt === "") {
        getOrdertempCount().then(res => {
          this.total = res.data.object;
        });
        getOrdertempListByPage(this.pageNum).then(res => {
          this.ordertempList = res.data.object;
        });
      } else {
        getOrdertempCountBySearch(this.searchTxt).then(res => {
          this.total = res.data.object;
        });
        getOrdertempListBySearchAndPage(this.searchTxt, this.pageNum).then(
          res => {
            this.ordertempList = res.data.object;
          }
        );
      }
    },
    handleCreate() {
      for (let i in this.menuList) {
        if (this.menuList[i].id === this.createModalObject.mid) {
          this.createModalObject.price =
            this.menuList[i].price * this.createModalObject.amount;
        }
      }
      createOrdertemp(this.createModalObject).then(res => {
        if (res.data.msg === "ok") {
          this.createModalFlg = false;
          getOrdertempCount().then(res => {
            this.total = res.data.object;
          });
          getOrdertempListByPage(this.pageNum).then(res => {
            this.ordertempList = res.data.object;
          });
        }
      });
    },
    handleUpdate() {
      updateOrdertemp(this.editModalObject).then(res => {
        this.editModalFlg = false;
        getOrdertempCount().then(res => {
          this.total = res.data.object;
        });
        getOrdertempListByPage(this.pageNum).then(res => {
          this.ordertempList = res.data.object;
        });
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
        state: 0,
        amount: 1
      };
      getDeskList().then(res => {
        this.deskList = res.data.object;
      });
      this.createModalFlg = true;
    },
    showEdit(index) {
      getOrdertempById(this.ordertempList[index].id).then(res => {
        this.editModalObject = res.data.object;
        this.editModalFlg = true;
      });
      getDeskByState(1).then(res => {
        this.deskListUsed = res.data.object;
      });
    },
    showSubmit(index) {
      this.submitModalObject = {
        did: null
      }
      this.orderList = [];
      this.submitModalFlg = true;
      getDeskByState(1).then(res => {
        this.deskListUsed = res.data.object;
      });
    },
    // 发布/撤回
    changeState(index) {
      this.ordertempList[index].state =
        this.ordertempList[index].state === 0 ? 1 : 0;
      updateOrdertemp(this.ordertempList[index])
        .then(res => {
          if (res.data.msg === "ok") {
            getOrdertempCount().then(res => {
              this.total = res.data.object;
            });
            getOrdertempListByPage(this.pageNum).then(res => {
              this.ordertempList = res.data.object;
            });
          } else {
            this.ordertempList[index].state =
              this.ordertempList[index].state === 0 ? 1 : 0;
          }
        })
        .catch(err => {
          this.ordertempList[index].state =
            this.ordertempList[index].state === 0 ? 1 : 0;
        });
    },
    handleCreateSuccess(res, file) {
      this.createModalObject.img = res.object;
    },
    handleUpdateSuccess(res, file) {
      this.editModalObject.img = res.object;
    },
    remove(index) {
      deleteOrdertempById(this.ordertempList[index].id).then(res => {
        if (res.data.msg === "ok") {
          this.ordertempList.splice(index, 1);
        }
      });
    },
    getOrdertemp() {
      getOrdertempByDid(this.submitModalObject.did).then(res => {
        debugger;
        this.orderList = res.data.object;
      });
    },
    handleSubmit() {
      debugger;
      let object = { did: this.submitModalObject.did };
      createOrder(object).then(res => {
        if (res.data.msg === "err") {
          this.$Notice.error({
            title: res.data.object
          });
          this.submitModalFlg = true;
        } else {
          this.$Notice.success({
            title: "结账成功"
          });
          this.submitModalFlg = false;
          getOrdertempCount().then(res => {
            this.total = res.data.object;
          });
          getOrdertempListByPage(this.pageNum).then(res => {
            this.ordertempList = res.data.object;
          });
        }
      });
      this.deskListUsed = [];
    }
  },
  mounted() {
    getOrdertempCount().then(res => {
      this.total = res.data.object;
    });
    getOrdertempListByPage(this.pageNum).then(res => {
      this.ordertempList = res.data.object;
    });

    getCategoryList().then(res => {
      debugger;
      this.categoryList = res.data.object;
    });
    getMenuList().then(res => {
      debugger;
      this.menuList = res.data.object;
    });
  }
};
</script>

<style scoped>
</style>