<style lang="less">
@import "./login.less";
</style>

<template>
  <div class="login">
    <div class="login-con">
      <Card icon="log-in" title="欢迎登录" :bordered="false">
        <div class="form-con">
          <p class="login-tip" style="color: red">{{this.message}}</p>
          <login-form @on-success-valid="handleSubmit"></login-form>
        </div>
      </Card>
    </div>
  </div>
</template>

<script>
import LoginForm from "_c/login-form";
import { mapActions } from "vuex";
export default {
  components: {
    LoginForm
  },
  data() {
    return {
      message: ""
    }
  },
  methods: {
    ...mapActions(["handleLogin", "getUserInfo"]),
    handleSubmit({ userName, password }) {
      this.message = ""
      this.handleLogin({ userName, password }).then(res => {
        if (res !== null) {
          this.getUserInfo().then(res => {
            this.$router.push({
              name: this.$config.homeName
            });
          });
        } else {
          this.message = "用户名或密码错误"
        }
      });
    }
  }
};
</script>

<style>
</style>
