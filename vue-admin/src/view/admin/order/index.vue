<template>
  <div>
    <Row :gutter="16" style="margin-top: 10px;">
      <Col span="10">
        <Button type="primary" size="large" @click="codeModalFlg = true">根据取餐码出餐</Button>
      </Col>
    </Row>

    <Table border :columns="clumns" :data="orderList" style="margin-top: 10px;"></Table>
    <Page
      :total="total"
      size="small"
      @on-change="changeCurrent"
      style="position: fixed; right: 15px; bottom: 5px;"
    ></Page>
    <Modal v-model="codeModalFlg" title="输入取餐码" @on-ok="handleOk">
      <Form :label-width="140">
        <Form-item label="取餐码">
          <Input v-model="code" placeholder="请输入取餐码" style="width: 60%" />
        </Form-item>
      </Form>
    </Modal>
  </div>
</template>

<script>
import {
  getOrderListByPageNum,
  getOrderTotal,
  getOrderItemByNo,
  takeMeal
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
          title: "下单用户",
          key: "user",
          align: "center",
          render: (h, params) => {
            return h("p", params.row.user.username);
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
        }
      ],
      orderList: []
    };
  },
  methods: {
    changeCurrent(pageNum) {
      this.pageNum = pageNum;
      getOrderListByPageNum(this.pageNum).then(res => {
        this.orderList = res.data.object;
      });
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
    }
  },
  mounted() {
    getOrderTotal().then(res => {
      this.total = res.data.object;
    });
    getOrderListByPageNum(this.pageNum).then(res => {
      this.orderList = res.data.object;
      console.log(this.orderList);
    });
  }
};
</script>

<style scoped>
</style>