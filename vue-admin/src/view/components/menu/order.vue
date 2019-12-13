<template>
  <div>
    <Row :gutter="16" style="margin-top: 10px;">
      <Col span="10"></Col>
    </Row>

    <Table border :columns="clumns" :data="orderList" style="margin-top: 10px;"></Table>
    <Page
      :total="total"
      size="small"
      @on-change="changeCurrent"
      style="position: fixed; right: 15px; bottom: 5px;"
    ></Page>

    <Modal v-model="detailModalFlg" title="订单状态" @on-ok="handleUpdate">
      <Form :model="detailModalObject" :label-width="140">
        <Form-item label="订单号">{{ detailModalObject.no }}</Form-item>
        <Form-item label="订单详情">
          <List border>
            <ListItem
              v-for="item in this.detailModalObject.orderItemList"
              :key="item.id"
            >{{item.name}} X {{item.count}}</ListItem>
          </List>
        </Form-item>
        <FormItem label="配送员">{{detailModalObject.userEntity.nickname}}</FormItem>
        <Steps
          :current="detailModalObject.logisticsEntity.state"
          direction="vertical"
          style="margin-left:100px;"
        >
          <Step title="未开始配送"></Step>
          <Step title="正在配送"></Step>
          <Step title="已送达"></Step>
        </Steps>
        <!-- <FormItem
          label="配送状态"
        >{{detailModalObject.logisticsEntity.state === 0 ? "未开始配送" : detailModalObject.logisticsEntity.state === 1 ? "正在配送" : "已送达"}}</FormItem>-->
      </Form>
    </Modal>
  </div>
</template>

<script>
import { getOrderData, getOrderItemByNo } from "@/api/order";
import store from "@/store";
import { log } from "util";

export default {
  name: "order",
  created() {
    getOrderData(this.uid).then(res => {
      debugger;
      this.orderList = res.data.object;
      console.log(this.orderList);
    });
  },
  data() {
    return {
      pageNum: 1,
      total: 0,
      codeModalFlg: false,
      code: "",
      detailModalFlg: false,
      detailModalObject: {
        userEntity: {},
        logisticsEntity: {},
        orderItemList: []
      },
      clumns: [
        {
          title: "订单编号",
          key: "orderEntity.no",
          width: 250,
          align: "center",
          render: (h, params) => {
            return h("b", params.row.orderEntity.no);
          }
        },
        {
          title: "订单详情",
          align: "left",
          render: (h, params) => {
            let itemArr = [];
            for (let i in params.row.itemList) {
              itemArr[i] = h(
                "p",
                params.row.itemList[i].name +
                  " x " +
                  params.row.itemList[i].count
              );
            }
            return itemArr;
          }
        },
        {
          title: "价格",
          key: "price",
          align: "center",
          render: (h, params) => {
            return h("p", params.row.orderEntity.price);
          }
        },
        {
          title: "状态",
          key: "state",
          align: "center",
          render: (h, params) => {
            return h(
              "span",
              params.row.orderEntity.state === 0 ? "未完成" : "已完成"
            );
          }
        },
        {
          title: "创建时间",
          key: "createtime",
          align: "center",
          width: 180,
          render: (h, params) => {
            return h(
              "span",
              this.formatDatetime(params.row.orderEntity.createtime)
            );
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
                      this.showDetail(params.index);
                    }
                  }
                },
                "查看详情"
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
      orderList: [],
      uid: store.state.user.userId
    };
  },
  methods: {
    changeCurrent(pageNum) {
      this.pageNum = pageNum;
      if (this.searchTxt === "") {
        getMenuListByPageNum(this.pageNum).then(res => {
          this.orderList = res.data.object;
        });
      } else {
        searchMenu(this.searchTxt, this.pageNum).then(res => {
          this.orderList = res.data.object;
        });
      }
    },
    search() {
      this.pageNum = 1;
      if (this.searchTxt === "") {
        getMenuTotal().then(res => {
          this.total = res.data.object;
        });
        getMenuListByPageNum(this.pageNum).then(res => {
          this.orderList = res.data.object;
        });
      } else {
        getSearchMenuTotal(this.searchTxt).then(res => {
          this.total = res.data.object;
        });
        searchMenu(this.searchTxt, this.pageNum).then(res => {
          this.orderList = res.data.object;
        });
      }
    },
    handleCreate() {
      createMenu(this.createModalObject).then(res => {
        if (res.data.msg === "ok") {
          this.createModalFlg = false;
          getMenuTotal().then(res => {
            this.total = res.data.object;
          });
          getMenuListByPageNum(this.pageNum).then(res => {
            this.orderList = res.data.object;
          });
        }
      });
    },
    handleUpdate() {
      updateMenu(this.editModalObject).then(res => {
        if (res.data.msg === "ok") {
          this.editModalFlg = false;
          getMenuTotal().then(res => {
            this.total = res.data.object;
          });
          getMenuListByPageNum(this.pageNum).then(res => {
            this.orderList = res.data.object;
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
    showDetail(index) {
      console.log(this.orderList, index);
      getLogisticsByOid(this.orderList[index].orderEntity.id).then(res => {
        this.detailModalObject = res.data.object;
        this.detailModalObject.orderItemList = this.orderList[index].itemList;
        this.detailModalObject.no = this.orderList[index].orderEntity.no;
        this.detailModalFlg = true;
      });

      console.log(this.detailModalObject);
    }
  }
};
</script>

<style scoped>
</style>