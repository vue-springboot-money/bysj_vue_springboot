<template>
  <div>
    <Row :gutter="16" style="margin-top: 10px;">
      <Col span="2">
        <Button type="primary" @click="showCreate">创建商品</Button>
      </Col>
      <Col span="8">
        <Input v-model="searchTxt">
          <Button slot="append" icon="ios-search" @click="search"></Button>
        </Input>
      </Col>
    </Row>

    <Table border :columns="clumns" :data="goodList" style="margin-top: 10px;"></Table>
    <Page
      :total="total"
      size="small"
      @on-change="changeCurrent"
      style="position: fixed; right: 15px; bottom: 5px;"
    ></Page>
    <Modal v-model="createModalFlg" title="创建商品" @on-ok="handleCreate">
      <Form :model="createModalObject" :label-width="140">
        <Form-item label="商品名">
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
          <img :src="createModalObject.img" width="200px" />
        </Form-item>
        <FormItem label="分类">
          <Select v-model="createModalObject.cid" style="width: 60%">
            <Option v-for="item in this.categoryList" :key="item.id" v-bind:value="item.id" >{{ item.name }}</Option>
          </Select>
        </FormItem>
        <Form-item label="价格">
          <Input-number :max="99" :min="1" :step="1" v-model="createModalObject.price"></Input-number>
        </Form-item>
      </Form>
    </Modal>
    <Modal v-model="editModalFlg" title="编辑商品" @on-ok="handleUpdate">
      <Form :model="editModalObject" :label-width="140">
        <Form-item label="商品名">
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
          <img :src="editModalObject.img" width="200px" />
        </Form-item>
        <FormItem label="分类">
          <Select v-model="editModalObject.cid" style="width: 60%">
            <Option v-for="item in this.categoryList" :key="item.id" v-bind:value="item.id">{{ item.name }}</Option>
          </Select>
        </FormItem>
        <Form-item label="价格">
          <Input-number :max="99" :min="1" :step="1" v-model="editModalObject.price"></Input-number>
        </Form-item>
      </Form>
    </Modal>
  </div>
</template>

<script>
import {
  getGoodTotal,
  getGoodListByPageNum,
  getGoodInfoById,
  createGood,
  updateGood,
  searchGood,
  getSearchGoodTotal,
  deleteGood
} from "@/api/good";
import { getCategoryList } from "@/api/category";
import { log } from "util";

export default {
  name: "good",
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
          title: "商品名",
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
          title: "单价",
          key: "price",
          align: "center"
        },
        {
          title: "类别",
          key: "cid",
          align: "center",
          render: (h, params) => {
            let name = "";
            for (let i in this.categoryList) {
              if (this.categoryList[i].id === params.row.cid) {
                name = this.categoryList[i].name;
              }
            }

            return h("span", name);
          }
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
      goodList: []
    };
  },
  methods: {
    changeCurrent(pageNum) {
      this.pageNum = pageNum;
      if (this.searchTxt === "") {
        getGoodListByPageNum(this.pageNum).then(res => {
          this.goodList = res.data.object;
        });
      } else {
        searchGood(this.searchTxt, this.pageNum).then(res => {
          this.goodList = res.data.object;
        });
      }
    },
    search() {
      this.pageNum = 1;
      if (this.searchTxt === "") {
        getGoodTotal().then(res => {
          this.total = res.data.object;
        });
        getGoodListByPageNum(this.pageNum).then(res => {
          this.goodList = res.data.object;
        });
      } else {
        getSearchGoodTotal(this.searchTxt).then(res => {
          this.total = res.data.object;
        });
        searchGood(this.searchTxt, this.pageNum).then(res => {
          this.goodList = res.data.object;
        });
      }
    },
    handleCreate() {
      createGood(this.createModalObject).then(res => {
        if (res.data.msg === "ok") {
          this.createModalFlg = false;
          getGoodTotal().then(res => {
            this.total = res.data.object;
          });
          getGoodListByPageNum(this.pageNum).then(res => {
            this.goodList = res.data.object;
          });
        }
      });
    },
    handleUpdate() {
      console.log(this.editModalObject)
      updateGood(this.editModalObject).then(res => {
        if (res.data.msg === "ok") {
          this.editModalFlg = false;
          getGoodTotal().then(res => {
            this.total = res.data.object;
          });
          getGoodListByPageNum(this.pageNum).then(res => {
            this.goodList = res.data.object;
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
      getCategoryList().then(res => {
        this.categoryList = res.data.object;
      });
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
      getCategoryList().then(res => {
        this.categoryList = res.data.object;
      });
      getGoodInfoById(this.goodList[index].id).then(res => {
        this.editModalObject = res.data.object;
        if (this.editModalObject.img.indexOf("http") === -1) {
          this.editModalObject.img = this.editModalObject.img;
        }
        this.editModalFlg = true;
      });
    },
    // 上/下架
    changeState(index) {
      this.goodList[index].state = this.goodList[index].state === 0 ? 1 : 0;
      updateGood(this.goodList[index])
        .then(res => {
          if (res.data.msg === "ok") {
            getGoodTotal().then(res => {
              this.total = res.data.object;
            });
            getGoodListByPageNum(this.pageNum).then(res => {
              this.goodList = res.data.object;
            });
          } else {
            this.goodList[index].state =
              this.goodList[index].state === 0 ? 1 : 0;
          }
        })
        .catch(err => {
          this.goodList[index].state = this.goodList[index].state === 0 ? 1 : 0;
        });
    },
    handleCreateSuccess(res, file) {
      this.createModalObject.img = res.object;
    },
    handleUpdateSuccess(res, file) {
      this.editModalObject.img = res.object;
    },
    remove(index) {
      deleteGood(this.goodList[index].id).then(res => {
        if (res.data.msg === "ok") {
          this.goodList.splice(index, 1);
        }
      });
    }
  },
  mounted() {
    getGoodTotal().then(res => {
      this.total = res.data.object;
    });
    getGoodListByPageNum(this.pageNum).then(res => {
      this.goodList = res.data.object;
    });
    getCategoryList().then(res => {
        this.categoryList = res.data.object;
      });
  }
};
</script>

<style scoped>
</style>