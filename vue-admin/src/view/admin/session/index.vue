<template>
  <div>
    <Row :gutter="16" style="margin-top: 10px;">
      <Col span="2">
        <Button type="primary" @click="showCreate">创建专场</Button>
      </Col>
      <Col span="8">
        <!-- <Input v-model="searchTxt">
          <Button slot="append" icon="ios-search" @click="search"></Button>
        </Input>-->
      </Col>
    </Row>

    <Table border :columns="clumns" :data="sessionList" style="margin-top: 10px;"></Table>
    <Page
      :total="total"
      size="small"
      @on-change="changeCurrent"
      style="position: fixed; right: 15px; bottom: 5px;"
    ></Page>
    <Modal v-model="createModalFlg" title="创建专场" @on-ok="handleCreate">
      <Form :model="createModalObject" :label-width="140">
        <FormItem label="主演">
          <Select v-model="createModalObject.starring1" style="width: 40%">
            <Option v-for="item in this.actorList" :key="item.id" :value="item.id">{{ item.name }}</Option>
          </Select>
          <Select v-model="createModalObject.starring2" style="width: 40%">
            <Option v-for="item in this.actorList" :key="item.id" :value="item.id">{{ item.name }}</Option>
          </Select>
        </FormItem>
        <FormItem label="助演">
          <Select v-model="createModalObject.assistant1" style="width: 40%">
            <Option v-for="item in this.actorList" :key="item.id" :value="item.id">{{ item.name }}</Option>
          </Select>
          <Select v-model="createModalObject.assistant2" style="width: 40%">
            <Option v-for="item in this.actorList" :key="item.id" :value="item.id">{{ item.name }}</Option>
          </Select>
          <Select v-model="createModalObject.assistant3" style="width: 40%">
            <Option v-for="item in this.actorList" :key="item.id" :value="item.id">{{ item.name }}</Option>
          </Select>
          <Select v-model="createModalObject.assistant4" style="width: 40%">
            <Option v-for="item in this.actorList" :key="item.id" :value="item.id">{{ item.name }}</Option>
          </Select>
        </FormItem>
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
        <FormItem label="剧场">
          <Select v-model="createModalObject.tid" style="width: 60%">
            <Option v-for="item in this.theaterList" :key="item.id" :value="item.id">{{ item.name }}</Option>
          </Select>
        </FormItem>
        <FormItem label="专场日期">
          <DatePicker
            v-model="createModalObject.date"
            format="yyyy-MM-dd"
            type="date"
            placeholder="Select date"
            style="width: 200px"
          ></DatePicker>
        </FormItem>
      </Form>
    </Modal>
    <Modal v-model="editModalFlg" title="编辑专场" @on-ok="handleUpdate">
      <Form :model="editModalObject" :label-width="140">
        <FormItem label="主演">
          <Select v-model="editModalObject.starring1" style="width: 40%">
            <Option v-for="item in this.actorList" :key="item.id" :value="item.id">{{ item.name }}</Option>
          </Select>
          <Select v-model="editModalObject.starring2" style="width: 40%">
            <Option v-for="item in this.actorList" :key="item.id" :value="item.id">{{ item.name }}</Option>
          </Select>
        </FormItem>
        <FormItem label="助演">
          <Select v-model="editModalObject.assistant1" style="width: 40%">
            <Option v-for="item in this.actorList" :key="item.id" :value="item.id">{{ item.name }}</Option>
          </Select>
          <Select v-model="editModalObject.assistant2" style="width: 40%">
            <Option v-for="item in this.actorList" :key="item.id" :value="item.id">{{ item.name }}</Option>
          </Select>
          <Select v-model="editModalObject.assistant3" style="width: 40%">
            <Option v-for="item in this.actorList" :key="item.id" :value="item.id">{{ item.name }}</Option>
          </Select>
          <Select v-model="editModalObject.assistant4" style="width: 40%">
            <Option v-for="item in this.actorList" :key="item.id" :value="item.id">{{ item.name }}</Option>
          </Select>
        </FormItem>
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
          <img :src="editModalObject.img" width="200px" />
        </Form-item>
        <FormItem label="剧场">
          <Select v-model="editModalObject.tid" style="width: 60%">
            <Option v-for="item in this.theaterList" :key="item.id" :value="item.id">{{ item.name }}</Option>
          </Select>
        </FormItem>
        <FormItem label="专场日期">
          <DatePicker
            v-model="editModalObject.date"
            format="yyyy-MM-dd"
            type="date"
            placeholder="Select date"
            style="width: 200px"
          ></DatePicker>
        </FormItem>
      </Form>
    </Modal>
  </div>
</template>

<script>
import {
  createSession,
  updateSession,
  getSessionById,
  deleteSessionById,
  getSessionListByPage,
  getSessionCount,
  getSessionListBySearchAndPage,
  getSessionCountBySearch
} from "@/api/session";
import { getTheaterList, getTheaterById } from "@/api/theater";
import { getActorList } from "@/api/actor";

import { log } from "util";

export default {
  name: "session",
  data() {
    return {
      pageNum: 1,
      total: 0,
      searchTxt: "",
      createModalObject: {},
      createModalFlg: false,
      editModalObject: {},
      editModalFlg: false,
      theaterList: [],
      actorList: [],
      clumns: [
        {
          title: "专场名",
          key: "content",
          render: (h, params) => {
            return h("p", params.row.sessionName);
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
          title: "主演1",
          key: "starring1",
          render: (h, params) => {
            return h("p", params.row.starringActor1.name);
          }
        },
        {
          title: "主演2",
          key: "starring2",
          render: (h, params) => {
            return h("p", params.row.starringActor2.name);
          }
        },
        {
          title: "助演1",
          key: "assistant1",
          render: (h, params) => {
            return h("p", params.row.assistantActor1.name);
          }
        },
        {
          title: "助演2",
          key: "assistant2",
          render: (h, params) => {
            return h("p", params.row.assistantActor2.name);
          }
        },
        {
          title: "助演3",
          key: "assistant3",
          render: (h, params) => {
            return h("p", params.row.assistantActor3.name);
          }
        },
        {
          title: "助演4",
          key: "assistant4",
          render: (h, params) => {
            return h("p", params.row.assistantActor4.name);
          }
        },
        {
          title: "剧场名",
          key: "tid",
          render: (h, params) => {
            for (let i in this.theaterList) {
              if (this.theaterList[i].id === params.row.tid) {
                return h("p", this.theaterList[i].name);
              }
            }
          }
        },
        {
          title: "日期",
          key: "date",
          align: "center",
          render: (h, params) => {
            return h(
              "span",
              this.formatDatetime(params.row.date).substring(0, 12)
            );
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
      sessionList: []
    };
  },
  methods: {
    changeCurrent(pageNum) {
      this.pageNum = pageNum;
      if (this.searchTxt === "") {
        getSessionListByPage(this.pageNum).then(res => {
          this.sessionList = res.data.object;
        });
      } else {
        getSessionListBySearchAndPage(this.searchTxt, this.pageNum).then(
          res => {
            this.sessionList = res.data.object;
          }
        );
      }
    },
    search() {
      this.pageNum = 1;
      if (this.searchTxt === "") {
        getSessionCount().then(res => {
          this.total = res.data.object;
        });
        getSessionListByPage(this.pageNum).then(res => {
          this.sessionList = res.data.object;
        });
      } else {
        getSessionCountBySearch(this.searchTxt).then(res => {
          this.total = res.data.object;
        });
        getSessionListBySearchAndPage(this.searchTxt, this.pageNum).then(
          res => {
            this.sessionList = res.data.object;
          }
        );
      }
    },
    handleCreate() {
      createSession(this.createModalObject).then(res => {
        if (res.data.msg === "ok") {
          this.createModalFlg = false;
          getSessionCount().then(res => {
            this.total = res.data.object;
          });
          getSessionListByPage(this.pageNum).then(res => {
            this.sessionList = res.data.object;
          });
        }
      });
    },
    handleUpdate() {
      updateSession(this.editModalObject).then(res => {
        if (res.data.msg === "ok") {
          this.editModalFlg = false;
          getSessionCount().then(res => {
            this.total = res.data.object;
          });
          getSessionListByPage(this.pageNum).then(res => {
            this.sessionList = res.data.object;
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
        img: ""
      };
      this.createModalFlg = true;
    },
    showEdit(index) {
      getSessionById(this.sessionList[index].id).then(res => {
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
    handleFormatError(file) {
      this.$Notice.warning({
        title: "文件格式不正确",
        desc:
          "文件 " + file.name + " 格式不正确，请上传 jpg 或 png 格式的图片。"
      });
    },
    remove(index) {
      deleteSessionById(this.sessionList[index].id).then(res => {
        if (res.data.msg === "ok") {
          this.sessionList.splice(index, 1);
        }
      });
    }
  },
  mounted() {
    getSessionCount().then(res => {
      this.total = res.data.object;
    });
    getSessionListByPage(this.pageNum).then(res => {
      this.sessionList = res.data.object;
    });
    getTheaterList().then(res => {
      this.theaterList = res.data.object;
    });
    getActorList().then(res => {
      this.actorList = res.data.object;
    });
  }
};
</script>

<style scoped>
</style>