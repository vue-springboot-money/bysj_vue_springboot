<template>
  <div class="pos">
    <div>
      <el-row>
        <el-col :span="7" class="pos-order" id="order-list">
          <el-tabs>
            <el-tab-pane label="点餐">
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
                <el-button type="warning">挂单</el-button>
                <el-button type="danger" @click="delAllGoods()">删除</el-button>
                <el-button type="success" @click="checkout()">结账</el-button>
              </div>
            </el-tab-pane>

            <el-tab-pane label="挂单">挂单</el-tab-pane>
            <el-tab-pane label="外卖">外卖</el-tab-pane>
          </el-tabs>
        </el-col>

        <!--商品展示-->
        <el-col :span="17">
          <div class="often-goods">
            <div class="title">最近点过</div>
            <div class="often-goods-list">
              <ul>
                <li v-for="goods in oftenGoods" @click="addOrderList(goods)">
                  <span>{{goods.name}}</span>
                  <span class="o-price">￥{{goods.price}}元</span>
                </li>
              </ul>
            </div>
          </div>

          <div class="goods-type">
            <el-tabs>
              <el-tab-pane label="汉堡">
                <ul class="cookList">
                  <li v-for="goods in oftenGoods" @click="addOrderList(goods)">
                    <span class="foodImg">
                      <img :src="goods.img" class="food-img" />
                    </span>
                    <!-- <img src="../../../assets/images/1.png" /> -->
                    <span class="foodName">{{goods.name}}</span>
                    <span class="foodPrice">￥{{goods.price}}元</span>
                  </li>
                </ul>
              </el-tab-pane>
              <el-tab-pane label="小食">
                <ul class="cookList">
                  <li v-for="goods in type1Goods" @click="addOrderList(goods)">
                    <span class="foodImg">
                      <img :src="goods.img" width="100%" />
                    </span>
                    <span class="foodName">{{goods.name}}</span>
                    <span class="foodPrice">￥{{goods.price}}元</span>
                  </li>
                </ul>
              </el-tab-pane>
              <el-tab-pane label="饮料">
                <ul class="cookList">
                  <li v-for="goods in type2Goods" @click="addOrderList(goods)">
                    <span class="foodImg">
                      <img :src="goods.img" width="100%" />
                    </span>
                    <span class="foodName">{{goods.name}}</span>
                    <span class="foodPrice">￥{{goods.price}}元</span>
                  </li>
                </ul>
              </el-tab-pane>
              <el-tab-pane label="套餐">
                <ul class="cookList">
                  <li v-for="goods in type3Goods" @click="addOrderList(goods)">
                    <span class="foodImg">
                      <img :src="goods.img" width="100%" />
                    </span>
                    <span class="foodName">{{goods.name}}</span>
                    <span class="foodPrice">￥{{goods.price}}元</span>
                  </li>
                </ul>
              </el-tab-pane>
            </el-tabs>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { getMenuData, createOrder } from "@/api/menu";
export default {
  name: "Pos",
  mounted: function() {
    var orderHeight = document.body.clientHeight;
    document.getElementById("order-list").style.height = orderHeight + "px";
  },
  created() {
    getMenuData().then(res => {
      debugger;
      this.oftenGoods = res.data.object;
    });
    //读取常用商品列表
    // axios
    //   .get("http://jspang.com/DemoApi/oftenGoods.php")
    //   .then(response => {
    //     //console.log(response);
    //     this.oftenGoods = response.data;
    //   })
    //   .catch(error => {
    //     console.log(error);
    //     alert("网络错误，不能访问");
    //   });
    // //读取分类商品列表
    // axios
    //   .get("menus/1")
    //   .then(response => {
    //     //console.log(response);
    //     //this.oftenGoods=response.data;
    //     this.type0Goods = response.data[0];
    //     this.type1Goods = response.data[1];
    //     this.type2Goods = response.data[2];
    //     this.type3Goods = response.data[3];
    //   })
    //   .catch(error => {
    //     console.log(error);
    //     alert("网络错误，不能访问");
    //   });
  },
  data() {
    return {
      tableData: [], //订单列表的值
      oftenGoods: [],
      type0Goods: [],
      type1Goods: [],
      type2Goods: [],
      type3Goods: [],
      totalMoney: 0, //订单总价格
      totalCount: 0 //订单商品总数量
    };
  },
  methods: {
    //添加订单列表的方法
    addOrderList(goods) {
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
        let arr = this.tableData.filter(o => o.id == goods.id);
        arr[0].count++;
        //console.log(arr);
      } else {
        //不存在就推入数组
        let newGoods = {
          id: goods.id,
          name: goods.name,
          price: goods.price,
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
          uid: 1,
          price: this.totalMoney,
          itemList: this.tableData
        }).then(res => {
          if (res.data.msg !== "ok") {
            this.$message.error(res.data.msg);
          } else {
            this.tableData = [];
            this.totalCount = 0;
            this.totalMoney = 0;
            this.$message({
              message: "结账成功，感谢你又为店里出了一份力!",
              type: "success"
            });
          }
        });
      } else {
        this.$message.error("不能空结。老板了解你急切的心情！");
      }
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
