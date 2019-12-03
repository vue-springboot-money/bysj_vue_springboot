<template>
  <div>
    <Row :gutter="16" style="margin-top: 10px;">
      <Col span="2">
        <Button type="primary" @click="showCreate">创建菜单</Button>
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
    <Modal v-model="createModalFlg" title="创建菜单" @on-ok="handleCreate">
      <Form :model="createModalObject" :label-width="140">
        <Form-item label="菜名">
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
            <Button type="primary" icon="ios-cloud-upload-outline" size="small">上传图片</Button>
          </Upload>
          <img :src="this.createModalObject.img" width="200px" />
        </Form-item>
        <Form-item label="类别">
          <Select v-model="createModalObject.cid" style="width: 60%">
            <Option
              v-for="item in this.categoryList"
              :key="item.id"
              :value="item.id"
            >{{ item.name }}</Option>
          </Select>
        </Form-item>
        <Form-item label="价格">
          <InputNumber :max="999" :min="1" v-model="createModalObject.price"></InputNumber>
        </Form-item>
      </Form>
    </Modal>
    <Modal v-model="editModalFlg" title="编辑菜单" @on-ok="handleUpdate">
      <Form :model="editModalObject" :label-width="140">
        <Form-item label="菜名">
          <Input v-model="editModalObject.name" placeholder="请输入" style="width: 60%" />
        </Form-item>
        <Form-item label="图片">
          <Upload
            action="http://localhost:8081/uploadFile"
            :format="['jpg','jpeg','png']"
            :on-format-error="handleFormatError"
            :show-upload-list="false"
            :on-success="handleCreateSuccess"
          >
            <Button type="primary" icon="ios-cloud-upload-outline" size="small">上传图片</Button>
          </Upload>
          <img :src="this.editModalObject.img" width="200px" />
        </Form-item>
        <Form-item label="类别">
          <Select v-model="editModalObject.cid" style="width: 60%">
            <Option
              v-for="item in this.categoryList"
              :key="item.id"
              :value="item.id"
            >{{ item.name }}</Option>
          </Select>
        </Form-item>
        <Form-item label="价格">
          <InputNumber :max="999" :min="1" v-model="editModalObject.price"></InputNumber>
        </Form-item>
      </Form>
    </Modal>
  </div>
</template>

<script>
import {
  createMenu,
  updateMenu,
  updateMenuStateById,
  getMenuById,
  deleteMenuById,
  getMenuListByPage,
  getMenuCount,
  getMenuListBySearchAndPage,
  getMenuCountBySearch
} from "@/api/menu";

import { getCategoryList } from "@/api/category";
import { log } from "util";

export default {
  name: "menu",
  data() {
    return {
      pageNum: 1,
      total: 0,
      searchTxt: "",
      createModalObject: {},
      createModalFlg: false,
      editModalObject: {},
      editModalFlg: false,
      categoryList: [],
      clumns: [
        {
          title: "菜名",
          key: "name",
          render: (h, params) => {
            return h("b", params.row.name);
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
          title: "价格",
          key: "price",
          align: "center"
        },
        {
          title: "类别",
          key: "cid",
          align: "center",
          render: (h, params) => {
            return h("span", params.row.category.name);
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
                      this.changeState(params.index);
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
        getMenuListByPage(this.pageNum).then(res => {
          this.menuList = res.data.object;
        });
      } else {
        getMenuListBySearchAndPage(this.searchTxt, this.pageNum).then(res => {
          this.menuList = res.data.object;
        });
      }
    },
    search() {
      this.pageNum = 1;
      if (this.searchTxt === "") {
        getMenuCount().then(res => {
          this.total = res.data.object;
        });
        getMenuListByPage(this.pageNum).then(res => {
          this.menuList = res.data.object;
        });
      } else {
        getMenuCountBySearch(this.searchTxt).then(res => {
          this.total = res.data.object;
        });
        getMenuListBySearchAndPage(this.searchTxt, this.pageNum).then(res => {
          this.menuList = res.data.object;
        });
      }
    },
    handleCreate() {
      createMenu(this.createModalObject).then(res => {
        if (res.data.msg === "ok") {
          this.createModalFlg = false;
          getMenuCount().then(res => {
            this.total = res.data.object;
          });
          getMenuListByPage(this.pageNum).then(res => {
            this.menuList = res.data.object;
          });
        }
      });
    },
    handleUpdate() {
      updateMenu(this.editModalObject).then(res => {
        this.editModalFlg = false;
        getMenuCount().then(res => {
          this.total = res.data.object;
        });
        getMenuListByPage(this.pageNum).then(res => {
          this.menuList = res.data.object;
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
        title: "",
        // 初始化，未发布
        state: 0,
        // 默认图片
        img:
          "https://img.zcool.cn/community/01a92a5a151826a80120518742bb1d.JPG",
        author: ""
      };
      this.createModalFlg = true;
    },
    showEdit(index) {
      getMenuById(this.menuList[index].id).then(res => {
        this.editModalObject = res.data.object;
        if (this.editModalObject.img.indexOf("http") === -1) {
          this.editModalObject.img = this.editModalObject.img;
        }
        this.editModalFlg = true;
      });
    },
    // 发布/撤回
    changeState(index) {
      this.menuList[index].state = this.menuList[index].state === 0 ? 1 : 0;
      updateMenu(this.menuList[index])
        .then(res => {
          if (res.data.msg === "ok") {
            getMenuCount().then(res => {
              this.total = res.data.object;
            });
            getMenuListByPage(this.pageNum).then(res => {
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
      deleteMenuById(this.menuList[index].id).then(res => {
        if (res.data.msg === "ok") {
          this.menuList.splice(index, 1);
        }
      });
    }
  },
  mounted() {
    getMenuCount().then(res => {
      this.total = res.data.object;
    });
    getMenuListByPage(this.pageNum).then(res => {
      this.menuList = res.data.object;
    });
    getCategoryList().then(res => {
      this.categoryList = res.data.object;
    });
  }
};
</script>

<style scoped>
</style>