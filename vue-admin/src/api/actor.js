import axios from '@/libs/api.request'

// 创建演员
export const createActor = (actor) => {
  return axios.request({
    url: 'actor',
    data: actor,
    method: 'post'
  })
}

// 更新演员
export const updateActor = (actor) => {
  return axios.request({
    url: 'actor',
    data: actor,
    method: 'patch'
  })
}

// 获取指定id的演员
export const getActorById = (id) => {
  return axios.request({
    url: 'actor/id/' + id,
    method: 'get'
  })
}

// 删除演员
export const deleteActorById = (id) => {
  return axios.request({
    url: 'actor/id/' + id,
    method: 'delete'
  })
}

// 分页获取演员列表
export const getActorListByPage = (page) => {
  return axios.request({
    url: 'actor/page/' + page,
    method: 'get'
  })
}

// 获取所有演员总数
export const getActorCount = () => {
  return axios.request({
    url: 'actor/count',
    method: 'get'
  })
}

// 获取所有演员
export const getActorList = (page) => {
  return axios.request({
    url: 'actorList',
    method: 'get'
  })
}

// 模糊查询获取演员列表
export const getActorListBySearchAndPage = (search, page) => {
  return axios.request({
    url: 'actor/search/' + search + '/page/' + page,
    method: 'get'
  })
}

// 获取模糊查询的演员总数
export const getActorCountBySearch = (search) => {
  return axios.request({
    url: 'actor/search/' + search + '/count',
    method: 'get'
  })
}

// 根据队伍id获取演员列表
export const getActorListByTid = (tid) => {
  return axios.request({
    url: 'dys/actor/tid/' + tid,
    method: 'get'
  })
}
