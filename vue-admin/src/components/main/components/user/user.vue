<template>
  <div class="user-avatar-dropdown">
    <Dropdown @on-click="handleClick">
      <Badge :dot="!!messageUnreadCount">
        <Avatar :src="userAvatar" />
      </Badge>
      <Icon :size="18" type="md-arrow-dropdown"></Icon>
      <DropdownMenu slot="list">
        <DropdownItem name="changePwd">修改密码</DropdownItem>
        <DropdownItem name="logout">退出登录</DropdownItem>
      </DropdownMenu>
    </Dropdown>
    <Modal v-model="changePwdFlg" title="修改密码" @on-ok="doChangePwd">
      <Input v-model="newPwd" placeholder="请输入新密码" type="password"></Input>
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
      changePwdFlg: false,
      newPwd: ""
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
    handleClick(name) {
      switch (name) {
        case "logout":
          this.logout();
          break;
        case "changePwd":
          this.showChangePwd();
          break;
      }
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
    }
  }
};
</script>
