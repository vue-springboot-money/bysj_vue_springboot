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

// 获取指定id的专场
export const getSessionById = (id) => {
  return axios.request({
    url: 'session/id/' + id,
    method: 'get'
  })
}

// 删除专场
export const deleteSessionById = (id) => {
  return axios.request({
    url: 'session/id/' + id,
    method: 'delete'
  })
}

// 分页获取专场列表
export const getSessionListByPage = (page) => {
  return axios.request({
    url: 'session/page/' + page,
    method: 'get'
  })
}

// 获取所有专场总数
export const getSessionCount = () => {
  return axios.request({
    url: 'session/count',
    method: 'get'
  })
}

// 模糊查询获取专场列表
export const getSessionListBySearchAndPage = (search, page) => {
  return axios.request({
    url: 'session/search/' + search + '/page/' + page,
    method: 'get'
  })
}

// 获取模糊查询的专场总数
export const getSessionCountBySearch = (search) => {
  return axios.request({
    url: 'session/search/' + search + '/count',
    method: 'get'
  })
}
