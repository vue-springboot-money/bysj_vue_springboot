<template>
  <div>
    <Row :gutter="16" style="margin-top: 10px;">
      <Col span="2">
        <Button type="primary" @click="showCreate">创建演员</Button>
      </Col>
      <Col span="8">
        <Input v-model="searchTxt">
          <Button slot="append" icon="ios-search" @click="search"></Button>
        </Input>
      </Col>
    </Row>

    <Table border :columns="clumns" :data="actorList" style="margin-top: 10px;"></Table>
    <Page
      :total="total"
      size="small"
      @on-change="changeCurrent"
      style="position: fixed; right: 15px; bottom: 5px;"
    ></Page>
    <Modal v-model="createModalFlg" title="创建演员" @on-ok="handleCreate">
      <Form :model="createModalObject" :label-width="140">
        <Form-item label="演员名">
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
        <Form-item label="信息">
          <Input v-model="createModalObject.information" placeholder="请输入" style="width: 60%" />
        </Form-item>
        <Form-item label="代表作">
          <Input v-model="createModalObject.masterpiece" placeholder="请输入" style="width: 60%" />
        </Form-item>
        <FormItem label="队伍">
          <Select v-model="createModalObject.tid" style="width: 60%">
            <Option v-for="item in this.teamList" :key="item.id" :value="item.id">{{ item.name }}</Option>
          </Select>
        </FormItem>
      </Form>
    </Modal>
    <Modal v-model="editModalFlg" title="编辑演员" @on-ok="handleUpdate">
      <Form :model="editModalObject" :label-width="140">
        <Form-item label="演员名">
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
          <img :src="this.editModalObject.img" width="200px" />
        </Form-item>
        <Form-item label="信息">
          <Input v-model="editModalObject.information" placeholder="请输入" style="width: 60%" />
        </Form-item>
        <Form-item label="代表作">
          <Input v-model="editModalObject.masterpiece" placeholder="请输入" style="width: 60%" />
        </Form-item>
        <FormItem label="队伍">
          <Select v-model="editModalObject.tid" style="width: 60%">
            <Option v-for="item in this.teamList" :key="item.id" :value="item.id">{{ item.name }}</Option>
          </Select>
        </FormItem>
      </Form>
    </Modal>
  </div>
</template>

<script>
import {
  createActor,
  updateActor,
  getActorById,
  deleteActorById,
  getActorListByPage,
  getActorCount,
  getActorListBySearchAndPage,
  getActorCountBySearch
} from "@/api/actor";
import { getTeamList, getTeamById } from "@/api/team";

import { log } from "util";

export default {
  name: "actor",
  data() {
    return {
      pageNum: 1,
      total: 0,
      searchTxt: "",
      createModalObject: {},
      createModalFlg: false,
      editModalObject: {},
      editModalFlg: false,
      teamList: [],
      clumns: [
        {
          title: "演员名",
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
          title: "信息",
          key: "information",
          align: "center"
        },
        {
          title: "代表作",
          key: "masterpiece",
          align: "center"
        },
        {
          title: "队名",
          key: "team",
          align: "center",
          render: (h, params) => {
            return h("span", params.row.team.name);
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
      actorList: []
    };
  },
  methods: {
    changeCurrent(pageNum) {
      this.pageNum = pageNum;
      if (this.searchTxt === "") {
        getActorListByPage(this.pageNum).then(res => {
          this.actorList = res.data.object;
        });
      } else {
        getActorListBySearchAndPage(this.searchTxt, this.pageNum).then(res => {
          this.actorList = res.data.object;
        });
      }
    },
    search() {
      this.pageNum = 1;
      if (this.searchTxt === "") {
        getActorCount().then(res => {
          this.total = res.data.object;
        });
        getActorListByPage(this.pageNum).then(res => {
          this.actorList = res.data.object;
        });
      } else {
        getActorCountBySearch(this.searchTxt).then(res => {
          this.total = res.data.object;
        });
        getActorListBySearchAndPage(this.searchTxt, this.pageNum).then(res => {
          this.actorList = res.data.object;
        });
      }
    },
    handleCreate() {
      createActor(this.createModalObject).then(res => {
        if (res.data.msg === "ok") {
          this.createModalFlg = false;
          getActorCount().then(res => {
            this.total = res.data.object;
          });
          getActorListByPage(this.pageNum).then(res => {
            this.actorList = res.data.object;
          });
        }
      });
    },
    handleUpdate() {
      updateActor(this.editModalObject).then(res => {
        if (res.data.msg === "ok") {
          this.editModalFlg = false;
          getActorCount().then(res => {
            this.total = res.data.object;
          });
          getActorListByPage(this.pageNum).then(res => {
            this.actorList = res.data.object;
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
        // 默认图片
        img: "https://img.zcool.cn/community/01a92a5a151826a80120518742bb1d.JPG"
      };
      this.createModalFlg = true;
    },
    showEdit(index) {
      getActorById(this.actorList[index].id).then(res => {
        this.editModalObject = res.data.object;
        if (this.editModalObject.img.indexOf("http") === -1) {
          this.editModalObject.img = this.editModalObject.img;
        }
        this.editModalFlg = true;
      });
    },
    // 上/下架
    changeState(index) {
      this.actorList[index].state = this.actorList[index].state === 0 ? 1 : 0;
      updateActor(this.actorList[index])
        .then(res => {
          if (res.data.msg === "ok") {
            getActorCount().then(res => {
              this.total = res.data.object;
            });
            getActorListByPage(this.pageNum).then(res => {
              this.actorList = res.data.object;
            });
          } else {
            this.actorList[index].state =
              this.actorList[index].state === 0 ? 1 : 0;
          }
        })
        .catch(err => {
          this.actorList[index].state =
            this.actorList[index].state === 0 ? 1 : 0;
        });
    },
    handleCreateSuccess(res, file) {
      this.createModalObject.img = res.object;
    },
    handleUpdateSuccess(res, file) {
      this.editModalObject.img = res.object;
    },
    remove(index) {
      deleteActorById(this.actorList[index].id).then(res => {
        if (res.data.msg === "ok") {
          this.actorList.splice(index, 1);
        }
      });
    }
  },
  mounted() {
    getActorCount().then(res => {
      this.total = res.data.object;
    });
    getActorListByPage(this.pageNum).then(res => {
      this.actorList = res.data.object;
    });
    getTeamList().then(res => {
      this.teamList = res.data.object;
    });
  }
};
</script>

<style scoped>
</style>