import axios from '@/libs/api.request'

export const getOrderListByPageNum = (pageNum) => {
  return axios.request({
    url: 'orderList/' + pageNum,
    method: 'get'
  })
}

export const getOrderTotal = () => {
  return axios.request({
    url: 'orderTotal',
    method: 'get'
  })
}

export const getOrderItemByNo = (no) => {
  return axios.request({
    url: 'orderItem/' + no,
    method: 'get'
  })
}

export const getOrderData = (uid) => {
  return axios.request({
    url: 'orders/uid/' + uid,
    method: 'get'
  })
}

export const createOrder = (order) => {
  return axios.request({
    url: 'order',
    data: order,
    method: 'post'
  })
}
