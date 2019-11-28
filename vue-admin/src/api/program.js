import axios from '@/libs/api.request'

// 创建节目单
export const createProgram = (program) => {
  return axios.request({
    url: 'program',
    data: program,
    method: 'post'
  })
}

// 更新节目单
export const updateProgram = (program) => {
  return axios.request({
    url: 'program',
    data: program,
    method: 'patch'
  })
}

// 查询指定id的节目单
export const getProgramById = (id) => {
  return axios.request({
    url: 'program/id/' + id,
    method: 'get'
  })
}

// 删除指定id的节目单
export const deleteProgram = (id) => {
  return axios.request({
    url: 'program/id/' + id,
    method: 'delete'
  })
}

// 分页查询
export const getProgramListByPage = (page) => {
  return axios.request({
    url: 'program/page/' + page,
    method: 'get'
  })
}

// 获取所有节目单数量
export const getProgramCount = () => {
  return axios.request({
    url: 'program/count',
    method: 'get'
  })
}

// 模糊查询
export const getProgramListBySearchAndPage = (search, page) => {
  return axios.request({
    url: 'program/search/' + search + '/page/' + page,
    method: 'get'
  })
}

// 获取符合检索条件的所有节目单数量
export const getProgramCountBySearch = (search) => {
  return axios.request({
    url: '/program/search/' + search + '/count',
    method: 'get'
  })
}