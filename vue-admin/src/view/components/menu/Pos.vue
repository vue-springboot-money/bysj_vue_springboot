<template>
  <div class="pos">
    <div>
      <el-row>
        <!--商品展示-->
        <el-col :span="24">
          <Tabs type="card">
            <TabPane label="专场">
              <el-row>
                <el-col :span="4" v-for="(session, index) in sessions" :key="session">
                  <el-card :body-style="{ padding: '0px' }">
                    <img :src="session.img" class="image" style="width:225px; height:225px;" />
                    <div style="padding: 14px;">
                      <span>{{session.sessionName}}</span>
                      <div class="bottom clearfix">
                        <el-button type="text" class="button" @click="getTicket(session)">查看详情🔍</el-button>
                      </div>
                    </div>
                  </el-card>
                </el-col>
              </el-row>
            </TabPane>
            <TabPane label="非专场">
              <el-row>
                <el-col :span="4" v-for="(program, index) in programs" :key="program">
                  <el-card :body-style="{ padding: '0px' }">
                    <img :src="program.img" class="image" style="width:225px; height:225px;" />
                    <div style="padding: 14px;">
                      <span>{{program.content}}</span>
                      <div class="bottom clearfix">
                        <el-button type="text" class="button" @click="getTicket(program)">查看详情🔍</el-button>
                      </div>
                    </div>
                  </el-card>
                </el-col>
              </el-row>
            </TabPane>

            <Button @click="value1 = true" type="primary" slot="extra">购物车🛒</Button>
          </Tabs>
        </el-col>
        <!-- <el-col :span="7" class="pos-order" id="order-list"></el-col> -->
      </el-row>
      <Modal v-model="editModalFlg" title="选取票价" width="360" @on-ok="handleCreate()">
        <div>
          <Button
            v-for="sTicket in sTickets"
            :key="sTicket"
            @click="getNum(sTicket)"
            style="margin-left:10px"
          >{{sTicket.price}}元</Button>
        </div>
        <div style="margin:20px;">
          剩余票数：
          <span v-if="num">{{num}}</span>
        </div>
      </Modal>
      <Drawer title="购物车🛒" :closable="false" v-model="value1" width="560">
        <table width="100%">
          <thead>
            <tr>
              <td>商品</td>
              <td width="50">量</td>
              <td width="70">金额</td>
              <td width="100">操作</td>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(item,index) in tableData">
              <td>{{item.name}}</td>
              <td>{{item.count}}</td>
              <td>{{item.price}}</td>
              <td>
                <el-button type="text" size="small" @click="delSingleGoods(item)">删除</el-button>
                <el-button type="text" size="small" @click="addOrderList(item)">增加</el-button>
              </td>
            </tr>
          </tbody>
        </table>

        <div class="totalDiv">
          <small>数量：</small>
          <strong>{{totalCount}}</strong> &nbsp;&nbsp;&nbsp;&nbsp;
          <small>总计：</small>
          <strong>{{totalMoney}}</strong> 元
        </div>

        <div class="order-btn">
          <el-button type="danger" @click="delAllGoods()">清空</el-button>
          <el-button type="success" @click="checkout()">结账</el-button>
        </div>
      </Drawer>
    </div>
  </div>
</template>

<script>
import axios from "axios";
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
import { getSessionTicket, getProgramTicket } from "@/api/ticket";
import { createOrder } from "@/api/order";
import store from "@/store";
export default {
  name: "Pos",
  mounted: function() {
    var orderHeight = document.body.clientHeight;
    document.getElementById("order-list").style.height = orderHeight + "px";
  },
  created() {
    getSessionListByPage(1).then(res => {
      debugger;
      this.sessions = res.data.object;
    });
    getProgramListByPage(1).then(res => {
      debugger;
      this.programs = res.data.object;
    });
  },
  data() {
    return {
      tableData: [], //订单列表的值
      sessions: [],
      programs: [],
      totalMoney: 0, //订单总价格
      totalCount: 0, //订单商品总数量
      categorys: [],
      editModalFlg: false,
      num: 0,
      value1: false,
      ticket: {},
      sessionName: ""
    };
  },
  methods: {
    //添加订单列表的方法
    handleCreate() {
      //console.log(goods);
      this.totalCount = 0; //汇总数量清0
      this.totalMoney = 0;
      let isHave = false;
      //判断是否这个商品已经存在于订单列表
      for (let i = 0; i < this.tableData.length; i++) {
        console.log(this.tableData[i].id);
        if (this.tableData[i].id == goods.id) {
          isHave = true; //存在
        }
      }
      //根据isHave的值判断订单列表中是否已经有此商品
      if (isHave) {
        //存在就进行数量添加
        let arr = this.tableData.filter(o => o.id == this.ticket.id);
        arr[0].count++;
        //console.log(arr);
      } else {
        //不存在就推入数组
        let newGoods = {
          id: this.ticket.id,
          name: this.sessionName,
          price: this.ticket.price,
          count: 1
        };
        debugger;
        this.tableData.push(newGoods);
      }
      this.getAllMoney();
    },
    //删除单个商品
    delSingleGoods(goods) {
      debugger;
      console.log(goods);
      this.tableData = this.tableData.filter(o => o.id != goods.id);
      this.getAllMoney();
    },
    //删除所有商品
    delAllGoods() {
      debugger;
      this.tableData = [];
      this.totalCount = 0;
      this.totalMoney = 0;
    },
    //汇总数量和金额
    getAllMoney() {
      debugger;
      this.totalCount = 0;
      this.totalMoney = 0;
      if (this.tableData) {
        this.tableData.forEach(element => {
          this.totalCount += element.count;
          this.totalMoney = this.totalMoney + element.price * element.count;
        });
      }
    },
    //结账方法模拟
    checkout() {
      if (this.totalCount != 0) {
        createOrder({
          uid: store.state.user.userId,
          price: this.totalMoney,
          itemList: this.tableData
        }).then(res => {
          if (res.data.msg !== "ok") {
            this.$message.error(res.data.msg);
          } else {
            this.tableData = [];
            this.totalCount = 0;
            this.totalMoney = 0;
            this.$router.push({
              name: "order"
            });
          }
        });
      } else {
        this.$message.error("不能空结。老板了解你急切的心情！");
      }
    },
    getTicket(session) {
      if (session.sessionName) {
        getSessionTicket(session.id).then(res => {
          debugger;
          this.sTickets = res.data.object;
          this.editModalFlg = true;
          this.sessionName = session.sessionName;
        });
      } else {
        getProgramTicket(session.id).then(res => {
          debugger;
          this.sTickets = res.data.object;
          this.editModalFlg = true;
          this.sessionName = session.content;
        });
      }
    },
    getNum(ticket) {
      this.num = ticket.num;
      this.ticket = ticket;
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.pos {
  font-size: 12px;
}
.pos-order {
  background-color: #f9fafc;
  border-right: 1px solid #c0ccda;
  height: 1000px;
  border-left: 1px solid #cccccc;
  padding: 10px;
}
.order-btn {
  margin-top: 10px;
  text-align: center;
}
.title {
  height: 20px;
  background-color: #f9fafc;
  color: #58b7ff;
  margin: 10px;
  font-size: 16px;
}
.often-goods-list ul li {
  list-style: none;
  float: left;
  border: 1px solid #e5e9f2;
  padding: 10px;
  margin: 5px;
  background-color: #fff;
  cursor: pointer;
}
.goods-type {
  clear: both;
}
.o-price {
  color: #58b7ff;
}
.often-goods-list {
  border-bottom: 1px solid #c0ccda;
  height: auto;
  overflow: hidden;
  padding-bottom: 10px;
  background-color: #f9fafc;
}
.cookList li {
  list-style: none;
  width: 20%;
  border: 1px solid #e5e9f2;
  height: 200px;
  overflow: hidden;
  background-color: #fff;
  padding: 2px;
  float: left;
  margin: 2px;
  cursor: pointer;
}
.cookList li span {
  display: block;
  float: left;
}
.foodImg {
  width: 40%;
}
.foodName {
  font-size: 18px;
  padding-left: 10px;
  padding-top: 5px;
  color: brown;
}
.foodPrice {
  font-size: 16px;
  padding-left: 5px;
  padding-top: 5px;
}
.totalDiv {
  height: auot;
  overflow: hidden;
  text-align: right;
  font-size: 16px;
  background-color: #fff;
  border-bottom: 1px solid #e5e9f2;
  padding: 10px;
}
.food-img {
  width: 210%;
  margin-left: 15px;
  height: 150px;
}
table {
  font-size: 14px;
  color: #606266;
  border-collapse: collapse;
}
td {
  border: 1px solid #e5e9f2;
  padding: 15px;
}
</style>
