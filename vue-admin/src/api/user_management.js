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

// 删除指定id的用户
export const deleteUser = (id) => {
  return axios.request({
    url: 'user/id/' + id,
    method: 'delete'
  })
}

// 分页查询
export const getUserListByPage = (page) => {
  return axios.request({
    url: 'user/page/' + page,
    method: 'get'
  })
}

// 获取所有用户数量
export const getUserCount = () => {
  return axios.request({
    url: 'user/count',
    method: 'get'
  })
}

// 模糊查询
export const getUserListBySearchAndPage = (search, page) => {
  return axios.request({
    url: 'user/search/' + search + '/page/' + page,
    method: 'get'
  })
}

// 获取符合检索条件的所有用户数量
export const getUserCountBySearch = (search) => {
  return axios.request({
    url: '/user/search/' + search + '/count',
    method: 'get'
  })
}

// 充值
export const recharge = (id, money) => {
  return axios.request({
    url: '/user/' + id + '/recharge/' + money,
    method: 'put'
  })
}

// 消费
export const consume = (id, money) => {
  return axios.request({
    url: '/user/' + id + '/consume/' + money,
    method: 'put'
  })
}