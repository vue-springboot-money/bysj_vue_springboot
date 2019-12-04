import axios from '@/libs/api.request'

// 创建点菜
export const createOrdertemp = (orderTemp) => {
  return axios.request({
    url: 'orderTemp',
    data: orderTemp,
    method: 'post'
  })
}

// 更新点菜
export const updateOrdertemp = (orderTemp) => {
  return axios.request({
    url: 'orderTemp',
    data: orderTemp,
    method: 'patch'
  })
}

// 更新点菜状态
export const updateOrdertempStateById = (id) => {
  return axios.request({
    url: 'orderTemp/id/' + id + '/state',
    method: 'patch'
  })
}

// 获取指定id的点菜
export const getOrdertempById = (id) => {
  return axios.request({
    url: 'orderTemp/id/' + id,
    method: 'get'
  })
}

// 获取指定did的点菜
export const getOrdertempByDid = (did) => {
  return axios.request({
    url: 'orderTemp/did/' + did,
    method: 'get'
  })
}

// 删除点菜
export const deleteOrdertempById = (id) => {
  return axios.request({
    url: 'orderTemp/id/' + id,
    method: 'delete'
  })
}

// 分页获取点菜列表
export const getOrdertempListByPage = (page) => {
  return axios.request({
    url: 'orderTemp/page/' + page,
    method: 'get'
  })
}

// 获取所有点菜总数
export const getOrdertempCount = () => {
  return axios.request({
    url: 'orderTemp/count',
    method: 'get'
  })
}

// 模糊查询获取点菜列表
export const getOrdertempListBySearchAndPage = (search, page) => {
  return axios.request({
    url: 'orderTemp/search/' + search + '/page/' + page,
    method: 'get'
  })
}

// 获取模糊查询的点菜总数
export const getOrdertempCountBySearch = (search) => {
  return axios.request({
    url: 'orderTemp/search/' + search + '/count',
    method: 'get'
  })
}
