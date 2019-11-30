<template>
  <div>
    <Row :gutter="16" style="margin-top: 10px;">
      <Col span="2">
        <Button type="primary" @click="showCreate">创建剧场</Button>
      </Col>
      <Col span="8">
        <Input v-model="searchTxt">
          <Button slot="append" icon="ios-search" @click="search"></Button>
        </Input>
      </Col>
    </Row>

    <Table border :columns="clumns" :data="theaterList" style="margin-top: 10px;"></Table>
    <Page
      :total="total"
      size="small"
      @on-change="changeCurrent"
      style="position: fixed; right: 15px; bottom: 5px;"
    ></Page>
    <Modal v-model="createModalFlg" title="创建剧场" @on-ok="handleCreate">
      <Form :model="createModalObject" :label-width="140">
        <Form-item label="剧场名">
          <Input v-model="createModalObject.name" placeholder="请输入" style="width: 60%" />
        </Form-item>
        <Form-item label="多媒体">
          <Upload
            action="http://localhost:8081/uploadFile"
            :format="['jpg','jpeg','png']"
            :on-format-error="handleFormatError"
            :show-upload-list="false"
            :on-success="handleCreateSuccess"
          >
            <Button type="primary" icon="ios-cloud-upload-outline" size="small">上传图片</Button>
          </Upload>
          <img :src="this.createModalObject.video" width="200px" />
        </Form-item>
        <Form-item label="信息">
          <Input v-model="createModalObject.information" placeholder="请输入" style="width: 60%" />
        </Form-item>
        <Form-item label="地址">
          <Input v-model="createModalObject.address" placeholder="请输入" style="width: 60%" />
        </Form-item>
        <FormItem label="电话">
          <Input v-model="createModalObject.tel" placeholder="请输入" style="width: 60%" />
        </FormItem>
      </Form>
    </Modal>
    <Modal v-model="editModalFlg" title="编辑剧场" @on-ok="handleUpdate">
      <Form :model="editModalObject" :label-width="140">
        <Form-item label="剧场名">
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
            <Button type="primary" icon="ios-cloud-upload-outline" size="small">上传图片</Button>
          </Upload>
          <img :src="this.editModalObject.video" width="200px" />
        </Form-item>
        <Form-item label="信息">
          <Input v-model="editModalObject.information" placeholder="请输入" style="width: 60%" />
        </Form-item>
        <Form-item label="地址">
          <Input v-model="editModalObject.address" placeholder="请输入" style="width: 60%" />
        </Form-item>
        <Form-item label="电话">
          <Input v-model="editModalObject.tel" placeholder="请输入" style="width: 60%" />
        </Form-item>
      </Form>
    </Modal>
  </div>
</template>

<script>
import {
  createTheater,
  updateTheater,
  getTheaterById,
  deleteTheaterById,
  getTheaterListByPage,
  getTheaterCount,
  getTheaterListBySearchAndPage,
  getTheaterCountBySearch
} from "@/api/theater";

import { log } from "util";

export default {
  name: "theater",
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
          title: "剧场名",
          key: "name",
          render: (h, params) => {
            return h("b", params.row.name);
          }
        },
        {
          title: "多媒体信息",
          key: "video",
          align: "center",
          render: (h, params) => {
            return h("img", {
              style: {
                width: "100px",
                hight: "100px"
              },
              attrs: {
                src: params.row.video
              }
            });
          }
        },
        {
          title: "信息",
          key: "information",
          align: "center"
        },
        {
          title: "地址",
          key: "address",
          align: "center"
        },
        {
          title: "电话",
          key: "tel",
          align: "center",
          render: (h, params) => {
            return h("span", params.row.tel);
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
      theaterList: []
    };
  },
  methods: {
    changeCurrent(pageNum) {
      this.pageNum = pageNum;
      if (this.searchTxt === "") {
        getTheaterListByPage(this.pageNum).then(res => {
          this.theaterList = res.data.object;
        });
      } else {
        getTheaterListBySearchAndPage(this.searchTxt, this.pageNum).then(res => {
          this.theaterList = res.data.object;
        });
      }
    },
    search() {
      this.pageNum = 1;
      if (this.searchTxt === "") {
        getTheaterCount().then(res => {
          this.total = res.data.object;
        });
        getTheaterListByPage(this.pageNum).then(res => {
          this.theaterList = res.data.object;
        });
      } else {
        getTheaterCountBySearch(this.searchTxt).then(res => {
          this.total = res.data.object;
        });
        getTheaterListBySearchAndPage(this.searchTxt, this.pageNum).then(res => {
          this.theaterList = res.data.object;
        });
      }
    },
    handleCreate() {
      createTheater(this.createModalObject).then(res => {
        if (res.data.msg === "ok") {
          this.createModalFlg = false;
          getTheaterCount().then(res => {
            this.total = res.data.object;
          });
          getTheaterListByPage(this.pageNum).then(res => {
            this.theaterList = res.data.object;
          });
        }
      });
    },
    handleUpdate() {
      updateTheater(this.editModalObject).then(res => {
        if (res.data.msg === "ok") {
          this.editModalFlg = false;
          getTheaterCount().then(res => {
            this.total = res.data.object;
          });
          getTheaterListByPage(this.pageNum).then(res => {
            this.theaterList = res.data.object;
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
        // 默认图片
        video: "https://img.zcool.cn/community/01a92a5a151826a80120518742bb1d.JPG"
      };
      this.createModalFlg = true;
    },
    showEdit(index) {
      getTheaterById(this.theaterList[index].id).then(res => {
        this.editModalObject = res.data.object;
        this.editModalFlg = true;
      });
    },
    handleCreateSuccess(res, file) {
      this.createModalObject.img = res.object;
    },
    handleUpdateSuccess(res, file) {
      this.editModalObject.img = res.object;
    },
    remove(index) {
      deleteTheaterById(this.theaterList[index].id).then(res => {
        if (res.data.msg === "ok") {
          this.theaterList.splice(index, 1);
        }
      });
    }
  },
  mounted() {
    getTheaterCount().then(res => {
      this.total = res.data.object;
    });
    getTheaterListByPage(this.pageNum).then(res => {
      this.theaterList = res.data.object;
    });
  }
};
</script>

<style scoped>
</style>