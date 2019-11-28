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

// 查询指定id的订单
export const getOrderById = (id) => {
  return axios.request({
    url: 'order/id/' + id,
    method: 'get'
  })
}

// 删除指定id的订单
export const deleteOrder = (id) => {
  return axios.request({
    url: 'order/id/' + id,
    method: 'delete'
  })
}

// 分页查询
export const getOrderListByPage = (page) => {
  return axios.request({
    url: 'order/page/' + page,
    method: 'get'
  })
}

// 获取所有订单数量
export const getOrderCount = () => {
  return axios.request({
    url: 'order/count',
    method: 'get'
  })
}

// 模糊查询
export const getOrderListBySearchAndPage = (search, page) => {
  return axios.request({
    url: 'order/search/' + search + '/page/' + page,
    method: 'get'
  })
}

// 获取符合检索条件的所有订单数量
export const getOrderCountBySearch = (search) => {
  return axios.request({
    url: '/order/search/' + search + '/count',
    method: 'get'
  })
}