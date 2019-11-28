import axios from '@/libs/api.request'

// 创建专场
export const createSession = (session) => {
  return axios.request({
    url: 'session',
    data: session,
    method: 'post'
  })
}

// 更新专场
export const updateSession = (session) => {
  return axios.request({
    url: 'session',
    data: session,
    method: 'patch'
  })
}

// 查询指定id的专场
export const getSessionById = (id) => {
  return axios.request({
    url: 'session/id/' + id,
    method: 'get'
  })
}

// 删除指定id的专场
export const deleteSession = (id) => {
  return axios.request({
    url: 'session/id/' + id,
    method: 'delete'
  })
}

// 分页查询
export const getSessionListByPage = (page) => {
  return axios.request({
    url: 'session/page/' + page,
    method: 'get'
  })
}

// 获取所有专场数量
export const getSessionCount = () => {
  return axios.request({
    url: 'session/count',
    method: 'get'
  })
}