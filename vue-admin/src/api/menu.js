import axios from '@/libs/api.request'

export const getMenuData = () => {
  return axios.request({
    url: 'menus/1',
    method: 'get'
  })
}

export const createOrder = (orderDto) => {
  return axios.request({
    url: 'order',
    data: orderDto,
    method: 'post'
  })
}

