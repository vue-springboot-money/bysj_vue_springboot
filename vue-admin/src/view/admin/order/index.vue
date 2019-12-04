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
  </div>
</template>

<script>
import {
  getOrderListByPage,
  getOrderCount,
  getOrderItemByNo,
  updateOrder
} from "@/api/order";
import { log } from "util";

export default {
  name: "order",
  data() {
    return {
      pageNum: 1,
      total: 0,
      codeModalFlg: false,
      code: "",
      clumns: [
        {
          title: "订单编号",
          key: "orderEntity.no",
          width: 250,
          align: "center",
          render: (h, params) => {
            return h("b", params.row.order.no);
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
          title: "台号",
          key: "did",
          align: "center",
          render: (h, params) => {
            return h("p", params.row.desk.name);
          }
        },
        {
          title: "价格",
          key: "price",
          align: "center",
          render: (h, params) => {
            return h("p", params.row.order.price);
          }
        },
        {
          title: "状态",
          key: "state",
          align: "center",
          render: (h, params) => {
            return h(
              "span",
              params.row.order.state === 0 ? "未结账" : "已结账"
            );
          }
        },
        {
          title: "创建时间",
          key: "createtime",
          align: "center",
          width: 180,
          render: (h, params) => {
            return h("span", this.formatDatetime(params.row.order.createtime));
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
                    marginRight: "5px",
                    disable: params.row.entity.state === 1 ? "disable" : ""
                  },
                  on: {
                    click: () => {
                      this.submit(params.index);
                    }
                  }
                },
                "结账"
              )
            ]);
          }
        }
      ],
      orderList: []
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
    handleOk() {
      takeMeal(this.code).then(res => {
        if (res.data.msg === "ok") {
          this.$message({
            message: "订单号：" + res.data.object[0].no + "，出餐成功",
            type: "success"
          });
        } else {
          this.$message.error("出餐失败");
        }

        getOrderTotal().then(res => {
          this.total = res.data.object;
        });
        getOrderListByPageNum(this.pageNum).then(res => {
          this.orderList = res.data.object;
          console.log(this.orderList);
        });
      });

      this.codeModalFlg = false;
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
    submit(index) {
      let order = this.orderList[index].order;
      order.state = 1;
      console.log(order)
      debugger
      updateOrder(order).then(res => {
        if (res.data.msg === "ok") {
          this.orderList[index].state = 1
          this.$Notice.success({
            title: "结账成功"
          });
        } else {
          this.orderList[index].state = 0
          this.$Notice.success({
            title: "结账失败"
          });
        }
      });
    }
  },
  mounted() {
    getOrderCount().then(res => {
      this.total = res.data.object;
    });
    getOrderListByPage(this.pageNum).then(res => {
      this.orderList = res.data.object;
    });
  }
};
</script>

<style scoped>
</style>