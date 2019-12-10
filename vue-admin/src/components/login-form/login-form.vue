<template>
  <Form ref="loginForm" :model="form" :rules="rules" @keydown.enter.native="handleSubmit">
    <FormItem prop="userName">
      <Input v-model="form.userName" placeholder="请输入用户名">
        <span slot="prepend">
          <Icon :size="16" type="ios-person"></Icon>
        </span>
      </Input>
    </FormItem>
    <FormItem prop="password">
      <Input type="password" v-model="form.password" placeholder="请输入密码">
        <span slot="prepend">
          <Icon :size="14" type="md-lock"></Icon>
        </span>
      </Input>
    </FormItem>
    <FormItem>
      <Button type="text" @click="showRegister" style="margin-left: 200px;">注册</Button>
    </FormItem>
    <FormItem>
      <Button @click="handleSubmit" type="primary" long>登录</Button>
    </FormItem>
    <Modal v-model="registerModelFlg" title="注册" @on-ok="ok">
      <Form :model="registerModelObject" style="width: 60%; margin-left: 20%">
        <FormItem label="用户名">
          <Input v-model="registerModelObject.username" placeholder="请输入用户名"></Input>
        </FormItem>
        <FormItem label="密码">
          <Input v-model="registerModelObject.password" placeholder="请输入密码" type="password"></Input>
        </FormItem>
        <FormItem label="确认密码">
          <Input v-model="registerModelObject.rePassword" placeholder="请确认密码" type="password"></Input>
          <p style="color: red">{{message}}</p>
        </FormItem>
      </Form>
    </Modal>
  </Form>
</template>
<script>
import { createUser, getUserByUsername } from "@/api/user_management";
export default {
  name: "LoginForm",
  props: {
    userNameRules: {
      type: Array,
      default: () => {
        return [{ required: true, message: "账号不能为空", trigger: "blur" }];
      }
    },
    passwordRules: {
      type: Array,
      default: () => {
        return [{ required: true, message: "密码不能为空", trigger: "blur" }];
      }
    }
  },
  watch: {
    registerModelObject: {
      handler: function(val, oldval) {
        if (
          this.registerModelObject.password !=
          this.registerModelObject.rePassword
        ) {
          this.message = "两次密码不一致";
        } else {
          this.message = "";
        }
      },
      deep: true //对象内部的属性监听，也叫深度监听
    }
  },
  data() {
    return {
      form: {
        userName: "",
        password: ""
      },
      registerModelFlg: false,
      registerModelObject: {
        username: "",
        password: "",
        rePassword: ""
      },
      message: ""
    };
  },
  computed: {
    rules() {
      return {
        userName: this.userNameRules,
        password: this.passwordRules
      };
    }
  },
  methods: {
    handleSubmit() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.$emit("on-success-valid", {
            userName: this.form.userName,
            password: this.form.password
          });
        }
      });
    },
    showRegister() {
      this.registerModelFlg = true;
    },
    ok() {
      getUserByUsername(this.registerModelObject.username).then(res => {
        if (res.data.object) {
          this.$Notice.error({
            title: "用户名已存在，请重新输入"
          });
          this.registerModelFlg = true;
        } else {
          this.registerModelFlg = true;
          createUser(this.registerModelObject).then(res => {
            if (res.data.msg === "ok") {
              this.$Notice.success({
                title: "注册成功"
              });
              this.registerModelFlg = false;
            }
          });
        }
      });
    }
  }
};
</script>
