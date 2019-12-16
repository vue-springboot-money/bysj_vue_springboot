import axios from '@/libs/api.request'

// 获取年销售数据
export const getYear = () => {
  return axios.request({
    url: 'statistics/year',
    method: 'get'
  })
}

// 获取月销售数据
export const getMonth = () => {
  return axios.request({
    url: 'statistics/month',
    method: 'get'
  })
}

// 获取日销售数据
export const getDay = () => {
  return axios.request({
    url: 'statistics/day',
    method: 'get'
  })
}

// 获取年销售总数
export const getYearTotle = () => {
  return axios.request({
    url: 'statistics/yearTotle',
    method: 'get'
  })
}