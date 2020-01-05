<template>
  <div class="user-avatar-dropdown">
    <Dropdown @on-click="handleClick">
      <Badge :dot="!!messageUnreadCount">
        <Avatar :src="userAvatar" />
      </Badge>
      <Icon :size="18" type="md-arrow-dropdown"></Icon>
      <DropdownMenu slot="list">
        <DropdownItem name="lock">锁定系统</DropdownItem>
        <DropdownItem name="changePwd">修改密码</DropdownItem>
        <DropdownItem name="logout">退出登录</DropdownItem>
      </DropdownMenu>
    </Dropdown>
    <Modal v-model="changePwdFlg" title="修改密码" @on-ok="doChangePwd">
      <Input v-model="newPwd" placeholder="请输入新密码" type="password"></Input>
    </Modal>
    <Modal v-model="lockModalFlg" fullscreen footer-hide :closable="false" title="锁定系统">
      <div v-show="!lockFlg">请设置本次锁定的密码</div>
      <div v-show="lockFlg">请输入本次锁定的密码来解锁</div>
      <Row>
        <Col span="8" offset="8">
          <Input v-model="lockPwd" placeholder="请输入锁定密码" type="password" v-show="!lockFlg"></Input>
          <Input v-model="unlockPwd" placeholder="请输入解锁密码" type="password" v-show="lockFlg"></Input>
        </Col>
        <Col span="8">
          <Button type="success" @click="lock" v-show="!lockFlg">锁定</Button>
          <Button type="success" @click="unlock" v-show="lockFlg">解锁</Button>
        </Col>
      </Row>
    </Modal>
  </div>
</template>

<script>
import "./user.less";
import { mapActions } from "vuex";
import { changePwd } from "@/api/user_management";
import store from "@/store";
export default {
  name: "User",
  props: {
    userAvatar: {
      type: String,
      default: ""
    },
    messageUnreadCount: {
      type: Number,
      default: 0
    }
  },
  data() {
    return {
      newPwd: "",
      lockPwd: "",
      unlockPwd: "",
      lockModalFlg: false,
      lockFlg: false,
      changePwdFlg: false
    };
  },
  methods: {
    ...mapActions(["handleLogOut"]),
    logout() {
      this.handleLogOut().then(() => {
        this.$router.push({
          name: "login"
        });
      });
    },
    message() {
      this.$router.push({
        name: "message_page"
      });
    },
    showChangePwd() {
      this.newPwd = "";
      this.changePwdFlg = true;
    },
    doChangePwd() {
      changePwd(store.state.user.userId, this.newPwd).then(res => {
        if (res.data.msg === "ok") {
          this.$Message.success("密码修改成功，下次登录将使用新密码");
          this.changePwdFlg = false;
        } else {
          this.$Message.error("密码修改失败，请重试");
          this.changePwdFlg = true;
        }
      });
    },
    showLock() {
      this.lockModalFlg = true;
    },
    lock() {
      this.lockFlg = true;
    },
    unlock() {
      if (this.unlockPwd === this.lockPwd) {
        this.lockModalFlg = false;
        this.lockFlg = false;
        this.lockPwd = "";
        this.unlockPwd = "";
      } else {
        alert("密码不正确");
      }
    },
    handleClick(name) {
      switch (name) {
        case "logout":
          this.logout();
          break;
        case "message":
          this.message();
          break;
        case "lock":
          this.showLock();
          break;
        case "changePwd":
          this.showChangePwd();
          break;
      }
    }
  }
};
</script>
