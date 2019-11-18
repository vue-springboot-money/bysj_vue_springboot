<template>
  <div>
    <Row :gutter="16" style="margin-top: 10px;">
      <Col span="2">
        <Button type="primary" @click="showCreate">创建菜品</Button>
      </Col>
      <Col span="8">
        <Input v-model="searchTxt">
          <Button slot="append" icon="ios-search" @click="search"></Button>
        </Input>
      </Col>
    </Row>

    <Table border :columns="clumns" :data="menuList" style="margin-top: 10px;"></Table>
    <Page
      :total="total"
      size="small"
      @on-change="changeCurrent"
      style="position: fixed; right: 15px; bottom: 5px;"
    ></Page>
    <Modal v-model="createModalFlg" title="创建菜品" @on-ok="handleCreate">
      <Form :model="createModalObject" :label-width="140">
        <Form-item label="菜品名">
          <Input v-model="createModalObject.name" placeholder="请输入" style="width: 60%" />
        </Form-item>
        <Form-item label="图片">
          <Upload
            action="http://localhost:8081/uploadFile"
            :format="['jpg','jpeg','png']"
            :on-format-error="handleFormatError"
            :show-upload-list="false"
            :on-success="handleCreateSuccess"
          >
            <Button type="primary" icon="ios-cloud-upload-outline" size="small">上传文件</Button>
          </Upload>
          <img :src="this.createModalObject.img" width="200px" />
        </Form-item>
        <Form-item label="价格">
          <Input-number :max="99" :min="1" :step="1" v-model="createModalObject.price"></Input-number>
        </Form-item>
      </Form>
    </Modal>
    <Modal v-model="editModalFlg" title="编辑菜品" @on-ok="handleUpdate">
      <Form :model="editModalObject" :label-width="140">
        <Form-item label="菜品名">
          <Input v-model="editModalObject.name" placeholder="请输入" style="width: 60%" />
        </Form-item>
        <Form-item label="图片">
          <Upload
            action="http://localhost:8081/uploadFile"
            :format="['jpg','jpeg','png']"
            :on-format-error="handleFormatError"
            :show-upload-list="false"
            :on-success="handleUpdateSuccess"
          >
            <Button type="primary" icon="ios-cloud-upload-outline" size="small">上传文件</Button>
          </Upload>
          <img :src="this.editModalObject.img" width="200px" />
        </Form-item>
        <Form-item label="价格">
          <Input-number :max="99" :min="1" :step="1" v-model="editModalObject.price"></Input-number>
        </Form-item>
      </Form>
    </Modal>
  </div>
</template>

<script>
import {
  
} from "@/api/data";
import { log } from "util";

export default {
  name: "order",
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
          title: "菜品名",
          key: "name",
          render: (h, params) => {
            return h(
              "b",
              params.row.name
            );
          }
        },
        {
          title: "图片",
          key: "img",
          align: "center",
          render: (h, params) => {
            return h("img", {
              style: {
                width: "100px",
                hight: "100px"
              },
              attrs: {
                src: params.row.img
              }
            });
          }
        },
        {
          title: "单价",
          key: "price",
          align: "center"
        },
        {
          title: "状态",
          key: "state",
          align: "center",
          render: (h, params) => {
            return h("span", params.row.state === 0 ? "未上架" : "已上架");
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
                params.row.state === 0 ? "上架" : "下架"
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
      menuList: []
    };
  },
  methods: {
    changeCurrent(pageNum) {
      this.pageNum = pageNum;
      if (this.searchTxt === "") {
        getMenuListByPageNum(this.pageNum).then(res => {
          this.menuList = res.data.object;
        });
      } else {
        searchMenu(this.searchTxt, this.pageNum).then(res => {
          this.menuList = res.data.object;
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
          this.menuList = res.data.object;
        });
      } else {
        getSearchMenuTotal(this.searchTxt).then(res => {
          this.total = res.data.object;
        });
        searchMenu(this.searchTxt, this.pageNum).then(res => {
          this.menuList = res.data.object;
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
            this.menuList = res.data.object;
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
            this.menuList = res.data.object;
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
        // 初始化，未上架
        state: 0,
        // 默认图片
        img:
          "https://img.zcool.cn/community/01a92a5a151826a80120518742bb1d.JPG",
        price: 0
      };
      this.createModalFlg = true;
    },
    showEdit(index) {
      getMenuInfoById(this.menuList[index].id).then(res => {
        this.editModalObject = res.data.object;
        if (this.editModalObject.img.indexOf("http") === -1) {
          this.editModalObject.img = this.editModalObject.img;
        }
        this.editModalFlg = true;
      });
    },
    // 上/下架
    changeState(index) {
      this.menuList[index].state = this.menuList[index].state === 0 ? 1 : 0;
      updateMenu(this.menuList[index])
        .then(res => {
          if (res.data.msg === "ok") {
            getMenuTotal().then(res => {
              this.total = res.data.object;
            });
            getMenuListByPageNum(this.pageNum).then(res => {
              this.menuList = res.data.object;
            });
          } else {
            this.menuList[index].state =
              this.menuList[index].state === 0 ? 1 : 0;
          }
        })
        .catch(err => {
          this.menuList[index].state = this.menuList[index].state === 0 ? 1 : 0;
        });
    },
    handleCreateSuccess(res, file) {
      this.createModalObject.img = res.object;
    },
    handleUpdateSuccess(res, file) {
      this.editModalObject.img = res.object;
    },
    remove(index) {
      deleteMenu(this.menuList[index].id).then(res => {
        if (res.data.msg === "ok") {
          this.menuList.splice(index, 1);
        }
      });
    }
  },
  mounted() {
    getMenuTotal().then(res => {
      this.total = res.data.object;
    });
    getMenuListByPageNum(this.pageNum).then(res => {
      this.menuList = res.data.object;
    });
  }
};
</script>

<style scoped>
</style>