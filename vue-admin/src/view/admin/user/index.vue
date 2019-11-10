<template>
  <div>
    <Row :gutter="16" style="margin-top: 10px;">
      <Col span="2">
        <Button type="primary" @click="showCreate">创建用户</Button>
      </Col>
      <Col span="8">
        <Input v-model="searchTxt">
          <Button slot="append" icon="ios-search" @click="search"></Button>
        </Input>
      </Col>
    </Row>

    <Table border :columns="clumns" :data="userList" style="margin-top: 10px;"></Table>
    <Page
      :total="total"
      size="small"
      @on-change="changeCurrent"
      style="position: fixed; right: 15px; bottom: 5px;"
    ></Page>
    <Modal v-model="createModalFlg" title="创建用户" @on-ok="handleCreate">
      <Form :model="createModalObject" :label-width="140">
        <Form-item label="用户名">
          <Input v-model="createModalObject.username" placeholder="请输入" style="width: 60%" />
        </Form-item>
        <Form-item label="昵称">
          <Input v-model="createModalObject.nickname" placeholder="请输入" style="width: 60%" />
        </Form-item>
        <Form-item label="性别">
          <Radio-group v-model="createModalObject.sex">
            <Radio :label="1">男</Radio>
            <Radio :label="0">女</Radio>
          </Radio-group>
        </Form-item>
        <Form-item label="账号类别">
          <Radio-group v-model="createModalObject.type">
            <Radio :label="0">老师</Radio>
            <Radio :label="1">学生</Radio>
            <Radio :label="2">管理员</Radio>
          </Radio-group>
        </Form-item>
      </Form>
    </Modal>
    <Modal v-model="editModalFlg" title="编辑用户" @on-ok="handleUpdate">
      <Form :model="editModalObject" :label-width="140">
        <Form-item label="用户名">
          <Input
            v-model="editModalObject.username"
            placeholder="请输入"
            :disabled="true"
            style="width: 60%"
          />
        </Form-item>
        <Form-item label="昵称">
          <Input v-model="editModalObject.nickname" placeholder="请输入" style="width: 60%" />
        </Form-item>
        <Form-item label="性别">
          <Radio-group v-model="editModalObject.sex">
            <Radio :label="1">男</Radio>
            <Radio :label="0">女</Radio>
          </Radio-group>
        </Form-item>
        <Form-item label="账号类别">
          <Radio-group v-model="editModalObject.type">
            <Radio :label="0">老师</Radio>
            <Radio :label="1">学生</Radio>
            <Radio :label="2">管理员</Radio>
          </Radio-group>
        </Form-item>
      </Form>
    </Modal>
  </div>
</template>

<script>
import {
  getUserTotal,
  getUserListByPageNum,
  getUserInfoById,
  createUser,
  updateUser,
  searchUser,
  getSearchUserTotal,
  deleteUser
} from "@/api/data";
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
          title: "用户名",
          key: "username"
        },
        {
          title: "昵称",
          key: "nickname"
        },
        {
          title: "账户类别",
          key: "type",
          render: (h, params) => {
            return h(
              "span",
              params.row.type === 0
                ? "教师"
                : params.row.type === 1
                ? "学生"
                : params.row.type === 2
                ? "管理员"
                : ""
            );
          }
        },
        {
          title: "性别",
          key: "sex",
          render: (h, params) => {
            return h("span", params.row.sex === 0 ? "女" : "男");
          }
        },
        {
          title: "注册时间",
          key: "createtime",
          render: (h, params) => {
            return h("span", this.formatDatetime(params.row.createtime));
          }
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
      userList: []
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
    search() {
      this.pageNum = 1;
      if (this.searchTxt === "") {
        getUserTotal().then(res => {
          this.total = res.data.object;
        });
        getUserListByPageNum(this.pageNum).then(res => {
          this.userList = res.data.object;
        });
      } else {
        getSearchUserTotal(this.searchTxt).then(res => {
          this.total = res.data.object;
        });
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
    showEdit(index) {
      getUserInfoById(this.userList[index].id).then(res => {
        this.editModalObject = res.data.object;
        this.editModalFlg = true;
      });
    },
    remove(index) {
      deleteUser(this.userList[index].id).then(res => {
        if (res.data.msg === 'ok') {
          this.userList.splice(index, 1);
        }
      })
    }
  },
  mounted() {
    getUserTotal().then(res => {
      this.total = res.data.object;
    });
    getUserListByPageNum(this.pageNum).then(res => {
      this.userList = res.data.object;
    });
  }
};
</script>

<style>
</style>