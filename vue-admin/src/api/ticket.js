import axios from '@/libs/api.request'

// 创建门票
export const createTicket = (ticket) => {
  return axios.request({
    url: 'ticket',
    data: ticket,
    method: 'post'
  })
}

// 更新门票
export const updateTicket = (ticket) => {
  return axios.request({
    url: 'ticket',
    data: ticket,
    method: 'patch'
  })
}

// 查询指定id的门票
export const getTicketById = (id) => {
  return axios.request({
    url: 'ticket/id/' + id,
    method: 'get'
  })
}

// 删除指定id的门票
export const deleteTicket = (id) => {
  return axios.request({
    url: 'ticket/id/' + id,
    method: 'delete'
  })
}

// 分页查询非专场
export const getProgramTicketListByPage = (page) => {
  return axios.request({
    url: 'ticket/program/page/' + page,
    method: 'get'
  })
}

// 获取非专场所有门票数量
export const getProgramTicketCount = () => {
  return axios.request({
    url: 'ticket/program/count',
    method: 'get'
  })
}

// 分页查询专场
export const getSessionTicketListByPage = (page) => {
  return axios.request({
    url: 'ticket/session/page/' + page,
    method: 'get'
  })
}

// 获取专场所有门票数量
export const getSessionTicketCount = () => {
  return axios.request({
    url: 'ticket/session/count',
    method: 'get'
  })
}