<template>
  <div>
    <Row :gutter="16" style="margin-top: 10px;">
      <Col span="2">
        <Button type="primary" @click="showCreate">创建节目单</Button>
      </Col>
      <Col span="8">
        <Input v-model="searchTxt">
          <Button slot="append" icon="ios-search" @click="search"></Button>
        </Input>
      </Col>
    </Row>

    <Table border :columns="clumns" :data="programList" style="margin-top: 10px;"></Table>
    <Page
      :total="total"
      size="small"
      @on-change="changeCurrent"
      style="position: fixed; right: 15px; bottom: 5px;"
    ></Page>
    <Modal v-model="createModalFlg" title="创建节目单" @on-ok="handleCreate">
      <Form :model="createModalObject" :label-width="140">
        <FormItem label="剧场">
          <Select v-model="createModalObject.tid" style="width: 60%">
            <Option v-for="item in this.theaterList" :key="item.id" :value="item.id">{{ item.name }}</Option>
          </Select>
        </FormItem>
        <Form-item label="节目单内容">
          <Input
            v-model="createModalObject.content"
            show-word-limit
            type="textarea"
            placeholder="Enter something..."
            style="width: 60%"
          />
        </Form-item>
        <FormItem label="节目单日期">
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
    <Modal v-model="editModalFlg" title="编辑节目单" @on-ok="handleUpdate">
      <Form :model="editModalObject" :label-width="140">
        <FormItem label="剧场">
          <Select v-model="editModalObject.tid" style="width: 60%">
            <Option v-for="item in this.theaterList" :key="item.id" :value="item.id">{{ item.name }}</Option>
          </Select>
        </FormItem>
        <Form-item label="节目单内容">
          <Input
            v-model="editModalObject.content"
            show-word-limit
            type="textarea"
            placeholder="Enter something..."
            style="width: 60%"
          />
        </Form-item>
        <FormItem label="节目单日期">
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
  createProgram,
  updateProgram,
  getProgramById,
  deleteProgramById,
  getProgramListByPage,
  getProgramCount,
  getProgramListBySearchAndPage,
  getProgramCountBySearch
} from "@/api/program";
import { getTheaterList, getTheaterById } from "@/api/theater";

import { log } from "util";

export default {
  name: "program",
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
      clumns: [
        {
          title: "剧场名",
          key: "name",
          render: (h, params) => {
            return h("b", params.row.theater.name);
          }
        },
        {
          title: "节目单",
          key: "content",
          render: (h, params) => {
            let contentArr = params.row.content.split("\n");
            let hArr = [];
            for (let i in contentArr) {
              hArr[i] = h("p", contentArr[i]);
            }
            return hArr;
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
      programList: []
    };
  },
  methods: {
    changeCurrent(pageNum) {
      this.pageNum = pageNum;
      if (this.searchTxt === "") {
        getProgramListByPage(this.pageNum).then(res => {
          this.programList = res.data.object;
        });
      } else {
        getProgramListBySearchAndPage(this.searchTxt, this.pageNum).then(
          res => {
            this.programList = res.data.object;
          }
        );
      }
    },
    search() {
      this.pageNum = 1;
      if (this.searchTxt === "") {
        getProgramCount().then(res => {
          this.total = res.data.object;
        });
        getProgramListByPage(this.pageNum).then(res => {
          this.programList = res.data.object;
        });
      } else {
        getProgramCountBySearch(this.searchTxt).then(res => {
          this.total = res.data.object;
        });
        getProgramListBySearchAndPage(this.searchTxt, this.pageNum).then(
          res => {
            this.programList = res.data.object;
          }
        );
      }
    },
    handleCreate() {
      createProgram(this.createModalObject).then(res => {
        if (res.data.msg === "ok") {
          this.createModalFlg = false;
          getProgramCount().then(res => {
            this.total = res.data.object;
          });
          getProgramListByPage(this.pageNum).then(res => {
            this.programList = res.data.object;
          });
        }
      });
    },
    handleUpdate() {
      updateProgram(this.editModalObject).then(res => {
        if (res.data.msg === "ok") {
          this.editModalFlg = false;
          getProgramCount().then(res => {
            this.total = res.data.object;
          });
          getProgramListByPage(this.pageNum).then(res => {
            this.programList = res.data.object;
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
        date: new Date()
      };
      this.createModalFlg = true;
    },
    showEdit(index) {
      getProgramById(this.programList[index].id).then(res => {
        this.editModalObject = res.data.object;
        this.editModalFlg = true;
      });
    },
    remove(index) {
      deleteProgramById(this.programList[index].id).then(res => {
        if (res.data.msg === "ok") {
          this.programList.splice(index, 1);
        }
      });
    }
  },
  mounted() {
    getProgramCount().then(res => {
      this.total = res.data.object;
    });
    getProgramListByPage(this.pageNum).then(res => {
      this.programList = res.data.object;
    });
    getTheaterList().then(res => {
      this.theaterList = res.data.object;
    });
  }
};
</script>

<style scoped>
</style>