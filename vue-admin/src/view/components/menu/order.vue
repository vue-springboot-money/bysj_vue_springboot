<template>
  <div>
    <!-- <Table border :columns="clumns" :data="orderData" style="margin-top: 10px;"></Table> -->
    <Table border :columns="clumns" :data="orderData">
      <template slot-scope="{ row }" slot="name">
        <strong>{{ row.id }}</strong>
      </template>
      <template slot-scope="{ row, index }" slot="action" style="width:200px">
        <!-- <Button type="primary" size="small" style="margin-right: 5px" @click="showRate(index)">View</Button>
        <Button type="error" size="small" @click="remove(index)">Delete</Button>-->
        <Rate v-show="!row.state" v-model="value" />
      </template>
    </Table>
    <Modal v-model="modal1" title="Common Modal dialog box title" @on-ok="ok" @on-cancel="cancel">
      <p>Content of dialog</p>
      <p>Content of dialog</p>
      <p>Content of dialog</p>
    </Modal>
  </div>
</template>

<script>
import { getOrderData } from "@/api/order";
import { log } from "util";

export default {
  name: "user",
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
          title: "Id",
          key: "id"
        },
        {
          title: "订单号",
          key: "no",
          width: 300
        },
        {
          title: "金额",
          key: "price"
        },
        {
          title: "取餐码",
          key: "code"
        },
        {
          title: "创建时间",
          key: "createtime",
          render: (h, params) => {
            return h("span", this.formatDatetime(params.row.createtime));
          }
        },
        {
          title: "Action",
          slot: "action",
          width: 200,
          align: "center"
        }
      ],
      userList: [],
      orderData: [],
      uid: 1,
      model1: false
    };
  },
  methods: {
    changeCurrent(pageNum) {
      this.pageNum = pageNum;
      if (this.searchTxt === "") {
        getUserListByPageNum(this.pageNum).then(res => {
          this.userList = res.data.object;
        });
      } else {
        searchUser(this.searchTxt, this.pageNum).then(res => {
          this.userList = res.data.object;
        });
      }
    },
    handleCreate() {
      createUser(this.createModalObject).then(res => {
        if (res.data.msg === "ok") {
          this.createModalFlg = false;
          getUserTotal().then(res => {
            this.total = res.data.object;
          });
          getUserListByPageNum(this.pageNum).then(res => {
            this.userList = res.data.object;
          });
        }
      });
    },
    handleUpdate() {
      updateUser(this.editModalObject).then(res => {
        if (res.data.msg === "ok") {
          this.editModalFlg = false;
          getUserTotal().then(res => {
            this.total = res.data.object;
          });
          getUserListByPageNum(this.pageNum).then(res => {
            this.userList = res.data.object;
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
        sex: 1,
        type: 1
      };
      this.createModalFlg = true;
    },
    rate(index) {
      getUserInfoById(this.orderData[index].id).then(res => {
        this.editModalObject = res.data.object;
        this.editModalFlg = true;
      });
    },
    remove(index) {
      deleteUser(this.userList[index].id).then(res => {
        if (res.data.msg === "ok") {
          this.userList.splice(index, 1);
        }
      });
    },
    showRate(index) {
      // this.createModalObject = {
      //   sex: 1,
      //   type: 1
      // };
      this.model1 = true;
    }
  },
  mounted() {
    getOrderData(this.uid).then(res => {
      debugger;
      this.orderData = res.data.object;
    });
  }
};
</script>

<style>
</style>