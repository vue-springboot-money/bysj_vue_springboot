import axios from '@/libs/api.request'

export const createOrder = (orderDto) => {
  return axios.request({
    url: 'order',
    data: orderDto,
    method: 'post'
  })
}