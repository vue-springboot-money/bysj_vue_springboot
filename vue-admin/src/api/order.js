import axios from '@/libs/api.request'

// 创建订单
export const createOrder = (order) => {
  return axios.request({
    url: 'order',
    data: order,
    method: 'post'
  })
}

// 更新订单
export const updateOrder = (order) => {
  return axios.request({
    url: 'order',
    data: order,
    method: 'patch'
  })
}

// 获取指定id的订单
export const getOrderById = (id) => {
  return axios.request({
    url: 'order/id/' + id,
    method: 'get'
  })
}

// 删除订单
export const deleteOrderById = (id) => {
  return axios.request({
    url: 'order/id/' + id,
    method: 'delete'
  })
}

// 分页获取订单列表
export const getOrderListByPage = (page) => {
  return axios.request({
    url: 'order/page/' + page,
    method: 'get'
  })
}

// 获取所有订单总数
export const getOrderCount = () => {
  return axios.request({
    url: 'order/count',
    method: 'get'
  })
}

// 模糊查询获取订单列表
export const getOrderListBySearchAndPage = (search, page) => {
  return axios.request({
    url: 'order/search/' + search + '/page/' + page,
    method: 'get'
  })
}

// 获取模糊查询的订单总数
export const getOrderCountBySearch = (search) => {
  return axios.request({
    url: 'order/search/' + search + '/count',
    method: 'get'
  })
}

// 获取模糊查询的订单总数
export const getOrderData = (uid) => {
  return axios.request({
    url: 'order/uid/' + uid,
    method: 'get'
  })
}
