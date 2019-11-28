import axios from '@/libs/api.request'

// 创建剧场
export const createTheater = (theater) => {
  return axios.request({
    url: 'theater',
    data: theater,
    method: 'post'
  })
}

// 更新剧场
export const updateTheater = (theater) => {
  return axios.request({
    url: 'theater',
    data: theater,
    method: 'patch'
  })
}

// 查询指定id的剧场
export const getTheaterById = (id) => {
  return axios.request({
    url: 'theater/id/' + id,
    method: 'get'
  })
}

// 删除指定id的剧场
export const deleteTheater = (id) => {
  return axios.request({
    url: 'theater/id/' + id,
    method: 'delete'
  })
}

// 分页查询
export const getTheaterListByPage = (page) => {
  return axios.request({
    url: 'theater/page/' + page,
    method: 'get'
  })
}

// 获取所有剧场数量
export const getTheaterCount = () => {
  return axios.request({
    url: 'theater/count',
    method: 'get'
  })
}

// 模糊查询
export const getTheaterListBySearchAndPage = (search, page) => {
  return axios.request({
    url: 'theater/search/' + search + '/page/' + page,
    method: 'get'
  })
}

// 获取符合检索条件的所有剧场数量
export const getTheaterCountBySearch = (search) => {
  return axios.request({
    url: '/theater/search/' + search + '/count',
    method: 'get'
  })
}