<template>
  <div>
    <Row>
      <Col span="8">
        <Input v-model="searchTxt">
          <Button slot="append" icon="ios-search" @click="search"></Button>
        </Input>
      </Col>
    </Row>
    <Row style="margin-top: 20px;">
      <Table highlight-row ref="currentRowTable" :columns="columns" :data="data"></Table>
    </Row>
    <Modal v-model="rechargeModalFlg" title="充值" @on-ok="handleRecharge">
      <Form :model="rechargeModalObject" :label-width="140">
        <Form-item label="用户名">
          <Input
            v-model="rechargeModalObject.username"
            placeholder="请输入"
            style="width: 60%"
            disabled
          />
        </Form-item>
        <Form-item label="昵称">
          <Input
            v-model="rechargeModalObject.nickname"
            placeholder="请输入"
            style="width: 60%"
            disabled
          />
        </Form-item>
        <Form-item label="性别">
          <Radio-group v-model="rechargeModalObject.sex">
            <Radio :label="1" disabled>男</Radio>
            <Radio :label="0" disabled>女</Radio>
          </Radio-group>
        </Form-item>
        <Form-item label="账号类别">
          <Radio-group v-model="rechargeModalObject.type" disabled>
            <Radio :label="0" disabled>老师</Radio>
            <Radio :label="1" disabled>学生</Radio>
            <Radio :label="2" disabled>管理员</Radio>
          </Radio-group>
        </Form-item>
        <Form-item label="账户金额">
          <span>{{rechargeModalObject.balance}}</span>
        </Form-item>
        <Form-item label="充值金额">
          <InputNumber :min="10" v-model="rechargeNum"></InputNumber>
        </Form-item>
      </Form>
    </Modal>
  </div>
</template>

<script>
import { searchUser, getUserInfoById, recharge } from "@/api/user_management";

export default {
  name: "recharge",
  data() {
    return {
      searchTxt: "",
      rechargeModalFlg: false,
      rechargeModalObject: {},
      rechargeNum: 0,
      dataIndex: 0,
      columns: [
        {
          type: "index",
          width: 60,
          align: "center"
        },
        {
          title: "用户名",
          key: "username"
        },
        {
          title: "昵称",
          key: "nickname"
        },
        {
          title: "性别",
          key: "sex",
          render: (h, params) => {
            return h("span", params.row.sex === 0 ? "女" : "男");
          }
        },
        {
          title: "余额",
          key: "balance"
        },
        {
          title: "操作",
          key: "action",
          width: 150,
          align: "center",
          render: (h, params) => {
            return h("div", [
              h(
                "Button",
                {
                  props: {
                    type: "primary",
                    size: "large"
                  },
                  style: {
                    marginRight: "5px"
                  },
                  on: {
                    click: () => {
                      this.showRecharge(params.index);
                    }
                  }
                },
                "充值"
              )
            ]);
          }
        }
      ],
      data: []
    };
  },
  methods: {
    search() {
      if (this.searchTxt === "") {
        this.data = [];
      } else {
        searchUser(this.searchTxt, 1).then(res => {
          this.data = res.data.object;
        });
      }
    },
    showRecharge(index) {
      this.rechargeModalFlg = true;
      getUserInfoById(this.data[index].id).then(res => {
        this.rechargeModalObject = res.data.object;
        this.dataIndex = index;
      });
    },
    handleRecharge() {
      this.rechargeModalObject.balance += this.rechargeNum;
      recharge(this.rechargeModalObject).then(res => {
        if (res.data.msg === "ok") {
          this.data[this.dataIndex].balance = this.rechargeModalObject.balance;
          this.$Notice.success({
            title: "充值成功"
          });
        }
      });
    }
  }
};
</script>

<style>
</style>