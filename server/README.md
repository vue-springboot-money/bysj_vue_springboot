# bysj_springboot

## 采用SpringBoot+MyBatis（MySQL）

### 接口文档

#### LoginController

>##### description: 登陆接口
>##### api: login
>##### method: post
>##### request json:
>```json
>{
>    username: 'username',
>    password: 'password'
>}
>```
>##### response json:
>```json
>{
>    "code": 200,
>    "msg": "登陆成功",
>    "object": {
>        "id": 1,
>        "username": "admin",
>        "password": "21232f297a57a5a743894a0e4a801fc3",
>        "type": 0
>    }
>}
>```
#### UserController

>##### description: 创建用户
>##### api: user
>##### method: post
>##### request json:
>```json
>{
>    username: 'test',
>    password: 'test',
>    type: 0
>}
>```
>##### response json:
>```json
>{
>    "code": 200,
>    "msg": "创建用户成功",
>    "object": {
>        "id": 5,
>        "username": "test",
>        "password": "098f6bcd4621d373cade4e832627b4f6",
>        "type": 0
>    }
>}
>```

>##### description: 查询所有用户
>##### api: user
>##### method: get
>##### response json:
>```json
>{
>    "code": 200,
>    "msg": "查询成功",
>    "object": [
>        {
>            "id": 1,
>            "username": "admin",
>            "password": "21232f297a57a5a743894a0e4a801fc3",
>            "type": 0
>        },
>        {
>            "id": 2,
>            "username": "student",
>            "password": "cd73502828457d15655bbd7a63fb0bc8",
>            "type": 1
>        },
>        {
>            "id": 3,
>            "username": "teacher",
>            "password": "8d788385431273d11e8b43bb78f3aa41",
>            "type": 2
>        },
>        {
>            "id": 5,
>            "username": "test",
>            "password": "098f6bcd4621d373cade4e832627b4f6",
>            "type": 0
>        }
>    ]
>}
>```

>##### description: 根据id查询用户
>##### api: user/id/{id}
>##### method: get
>##### response json:
>```json
>{
>    "code": 200,
>    "msg": "查询成功",
>    "object": {
>        "id": 1,
>        "username": "admin",
>        "password": "21232f297a57a5a743894a0e4a801fc3",
>        "type": 0
>    }
>}
>```

>##### description: 根据username查询用户
>##### api: user/username/{username}
>##### method: get
>##### response json:
>```json
>{
>    "code": 200,
>    "msg": "查询成功",
>    "object": {
>        "id": 1,
>        "username": "admin",
>        "password": "21232f297a57a5a743894a0e4a801fc3",
>        "type": 0
>    }
>}
>```

>##### description: 根据type查询用户
>##### api: user/type/{type}
>##### method: get
>##### response json:
>```json
>{
>    "code": 200,
>    "msg": "查询成功",
>    "object": {
>        "id": 1,
>        "username": "admin",
>        "password": "21232f297a57a5a743894a0e4a801fc3",
>        "type": 0
>    }
>}
>```

>##### description: 根据id删除用户
>##### api: user/id/{id}
>##### method: delete
>##### response json:
>```json
>{
>    "code": 200,
>    "msg": "删除成功",
>    "object": {id}
>}
>```