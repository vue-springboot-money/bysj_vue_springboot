<template>
  <div>
    <Row :gutter="15" style="margin-top: 10px;">
      <Col span="2">
        <Button type="primary" @click="showCreate">创建类别</Button>
      </Col>
      <Col span="8">
        <Input v-model="searchTxt">
          <Button slot="append" icon="ios-search" @click="search"></Button>
        </Input>
      </Col>
    </Row>

    <Table border :columns="clumns" :data="categoryList" style="margin-top: 10px;"></Table>
    <Page
      :total="total"
      size="small"
      @on-change="changeCurrent"
      style="position: fixed; right: 15px; bottom: 5px;"
    ></Page>
    <Modal v-model="createModalFlg" title="创建类别" @on-ok="handleCreate">
      <Form :model="createModalObject" :label-width="140">
        <Form-item label="类别名">
          <Input v-model="createModalObject.name" placeholder="请输入" style="width: 60%" />
        </Form-item>
      </Form>
    </Modal>
    <Modal v-model="editModalFlg" title="编辑类别" @on-ok="handleUpdate">
      <Form :model="editModalObject" :label-width="140">
        <Form-item label="类别名">
          <Input v-model="editModalObject.name" placeholder="请输入" style="width: 60%" />
        </Form-item>
      </Form>
    </Modal>
  </div>
</template>

<script>
import {
  getCategoryTotal,
  getCategoryListByPageNum,
  getCategoryInfoById,
  createCategory,
  updateCategory,
  searchCategory,
  getSearchCategoryTotal,
  deleteCategory
} from "@/api/category";
import { log } from "util";

export default {
  name: "category",
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
          title: "类别名",
          key: "name",
          render: (h, params) => {
            return h(
              "b",
              params.row.name
            );
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
      categoryList: []
    };
  },
  methods: {
    changeCurrent(pageNum) {
      this.pageNum = pageNum;
      if (this.searchTxt === "") {
        getCategoryListByPageNum(this.pageNum).then(res => {
          this.categoryList = res.data.object;
        });
      } else {
        searchCategory(this.searchTxt, this.pageNum).then(res => {
          this.categoryList = res.data.object;
        });
      }
    },
    search() {
      this.pageNum = 1;
      if (this.searchTxt === "") {
        getCategoryTotal().then(res => {
          this.total = res.data.object;
        });
        getCategoryListByPageNum(this.pageNum).then(res => {
          this.categoryList = res.data.object;
        });
      } else {
        getSearchCategoryTotal(this.searchTxt).then(res => {
          this.total = res.data.object;
        });
        searchCategory(this.searchTxt, this.pageNum).then(res => {
          this.categoryList = res.data.object;
        });
      }
    },
    handleCreate() {
      createCategory(this.createModalObject).then(res => {
        if (res.data.msg === "ok") {
          this.createModalFlg = false;
          getCategoryTotal().then(res => {
            this.total = res.data.object;
          });
          getCategoryListByPageNum(this.pageNum).then(res => {
            this.categoryList = res.data.object;
          });
        }
      });
    },
    handleUpdate() {
      updateCategory(this.editModalObject).then(res => {
        if (res.data.msg === "ok") {
          this.editModalFlg = false;
          getCategoryTotal().then(res => {
            this.total = res.data.object;
          });
          getCategoryListByPageNum(this.pageNum).then(res => {
            this.categoryList = res.data.object;
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
        name: "",
        // 初始化，未上架
        state: 0
      };
      this.createModalFlg = true;
    },
    showEdit(index) {
      getCategoryInfoById(this.categoryList[index].id).then(res => {
        this.editModalObject = res.data.object;
        this.editModalFlg = true;
      });
    },
    // 发布/撤回
    changeState(index) {
      this.categoryList[index].state = this.categoryList[index].state === 0 ? 1 : 0;
      updateCategory(this.categoryList[index])
        .then(res => {
          if (res.data.msg === "ok") {
            getCategoryTotal().then(res => {
              this.total = res.data.object;
            });
            getCategoryListByPageNum(this.pageNum).then(res => {
              this.categoryList = res.data.object;
            });
          } else {
            this.categoryList[index].state =
              this.categoryList[index].state === 0 ? 1 : 0;
          }
        })
        .catch(err => {
          this.categoryList[index].state = this.categoryList[index].state === 0 ? 1 : 0;
        });
    },
    remove(index) {
      deleteCategory(this.categoryList[index].id).then(res => {
        if (res.data.msg === "ok") {
          this.categoryList.splice(index, 1);
        }
      });
    }
  },
  mounted() {
    getCategoryTotal().then(res => {
      this.total = res.data.object;
    });
    getCategoryListByPageNum(this.pageNum).then(res => {
      this.categoryList = res.data.object;
    });
  }
};
</script>

<style scoped>
</style>