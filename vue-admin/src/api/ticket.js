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

// 获取指定id的门票
export const getTicketById = (id) => {
  return axios.request({
    url: 'ticket/id/' + id,
    method: 'get'
  })
}

// 删除门票
export const deleteTicketById = (id) => {
  return axios.request({
    url: 'ticket/id/' + id,
    method: 'delete'
  })
}

// 分页获取非专场门票
export const getProgramTicketListByPage = (page) => {
  return axios.request({
    url: 'ticket/program/page/' + page,
    method: 'get'
  })
}

// 获取所有非专场门票数量
export const getProgramTicketCount = () => {
  return axios.request({
    url: 'ticket/program/count',
    method: 'get'
  })
}

// 分页获取专场门票
export const getSessionTicketListBySearchAndPage = (page) => {
  return axios.request({
    url: 'ticket/session/page/' + page,
    method: 'get'
  })
}

// 获取所有专场门票数量
export const getSessionTicketCount = () => {
  return axios.request({
    url: 'ticket/session/count',
    method: 'get'
  })
}

// 获取所有专场门票
export const getSessionTicket = () => {
  return axios.request({
    url: 'ticket/s/',
    method: 'get'
  })
}

// 获取所有非专场门票
export const getProgramTicket = () => {
  return axios.request({
    url: 'ticket/p/',
    method: 'get'
  })
}

// 获取所有非专场门票
export const getProgramTimeTicket = (pid) => {
  return axios.request({
    url: 'dys/ticket/pid/' + pid,
    method: 'get'
  })
}

// 获取所有非专场门票
export const getSessionTimeTicket = (sid) => {
  return axios.request({
    url: 'ticket/sid/' + sid,
    method: 'get'
  })
}