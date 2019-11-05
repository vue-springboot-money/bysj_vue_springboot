<template>
  <Table border :columns="clumns" :data="userList"></Table>
</template>

<script>
import { getTableData } from "@/api/data";

export default {
  name: "user",
  data() {
    return {
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
    showEdit(index) {
      this.$Modal.info({
        title: "User Info",
        content: `Name：${this.userList[index].username}<br>Age：${this.userList[index].age}<br>Address：${this.userList[index].address}`
      });
    },
    remove(index) {
      this.data6.splice(index, 1);
    }
  },
  mounted() {
    getTableData().then(res => {
      this.userList = res.data.object;
    });
  }
};
</script>

<style>
</style>