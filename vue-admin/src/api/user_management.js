import axios from '@/libs/api.request'

// 创建用户
export const createUser = (user) => {
  return axios.request({
    url: 'user',
    data: user,
    method: 'post'
  })
}

// 更新用户
export const updateUser = (user) => {
  return axios.request({
    url: 'user',
    data: user,
    method: 'patch'
  })
}

// 查询指定id的用户
export const getUserById = (id) => {
  return axios.request({
    url: 'user/id/' + id,
    method: 'get'
  })
}

// 删除用户
export const deleteUserById = (id) => {
  return axios.request({
    url: 'user/id/' + id,
    method: 'delete'
  })
}

// 分页获取用户列表
export const getUserListByPage = (page) => {
  return axios.request({
    url: 'user/page/' + page,
    method: 'get'
  })
}

// 获取所有用户总数
export const getUserCount = () => {
  return axios.request({
    url: 'user/count',
    method: 'get'
  })
}

// 模糊查询获取用户列表
export const getUserListBySearchAndPage = (search, page) => {
  return axios.request({
    url: 'user/search/' + search + '/page/' + page,
    method: 'get'
  })
}

// 获取模糊查询的用户总数
export const getUserCountBySearch = (search) => {
  return axios.request({
    url: 'user/search/' + search + '/count',
    method: 'get'
  })
}

// 充值
export const recharge = (id, money) => {
  return axios.request({
    url: 'user/id/' + id + '/recharge/' + money,
    method: 'put'
  })
}

// 消费
export const consume = (id, money) => {
  return axios.request({
    url: 'user/id/' + id + '/consume/' + money,
    method: 'put'
  })
}

export const changePwd = (id, pwd) => {
  return axios.request({
    url: 'userPwd/' + id + '/' + pwd,
    method: 'put'
  })
}