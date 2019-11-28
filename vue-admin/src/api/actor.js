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

// 查询指定id的演员
export const getActorById = (id) => {
  return axios.request({
    url: 'actor/id/' + id,
    method: 'get'
  })
}

// 删除指定id的演员
export const deleteActor = (id) => {
  return axios.request({
    url: 'actor/id/' + id,
    method: 'delete'
  })
}

// 分页查询
export const getActorListByPage = (page) => {
  return axios.request({
    url: 'actor/page/' + page,
    method: 'get'
  })
}

// 获取所有演员数量
export const getActorCount = () => {
  return axios.request({
    url: 'actor/count',
    method: 'get'
  })
}

// 模糊查询
export const getActorListBySearchAndPage = (search, page) => {
  return axios.request({
    url: 'actor/search/' + search + '/page/' + page,
    method: 'get'
  })
}

// 获取符合检索条件的所有演员数量
export const getActorCountBySearch = (search) => {
  return axios.request({
    url: '/actor/search/' + search + '/count',
    method: 'get'
  })
}