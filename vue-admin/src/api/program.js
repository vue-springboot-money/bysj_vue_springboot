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

// 获取指定id的节目单
export const getProgramById = (id) => {
  return axios.request({
    url: 'program/id/' + id,
    method: 'get'
  })
}

// 删除节目单
export const deleteProgramById = (id) => {
  return axios.request({
    url: 'program/id/' + id,
    method: 'delete'
  })
}

// 分页获取节目单列表
export const getProgramListByPage = (page) => {
  return axios.request({
    url: 'program/page/' + page,
    method: 'get'
  })
}

// 获取所有节目单总数
export const getProgramCount = () => {
  return axios.request({
    url: 'program/count',
    method: 'get'
  })
}

// 模糊查询获取节目单列表
export const getProgramListBySearchAndPage = (search, page) => {
  return axios.request({
    url: 'program/search/' + search + '/page/' + page,
    method: 'get'
  })
}

// 获取模糊查询的节目单总数
export const getProgramCountBySearch = (search) => {
  return axios.request({
    url: 'program/search/' + search + '/count',
    method: 'get'
  })
}
