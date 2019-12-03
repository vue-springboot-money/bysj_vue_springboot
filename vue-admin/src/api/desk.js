import axios from '@/libs/api.request'

// 创建台号
export const createDesk = (desk) => {
  return axios.request({
    url: 'desk',
    data: desk,
    method: 'post'
  })
}

// 更新台号
export const updateDesk = (desk) => {
  return axios.request({
    url: 'desk',
    data: desk,
    method: 'patch'
  })
}

// 获取指定id的台号
export const getDeskById = (id) => {
  return axios.request({
    url: 'desk/id/' + id,
    method: 'get'
  })
}

// 删除台号
export const deleteDeskById = (id) => {
  return axios.request({
    url: 'desk/id/' + id,
    method: 'delete'
  })
}

// 分页获取台号列表
export const getDeskListByPage = (page) => {
  return axios.request({
    url: 'desk/page/' + page,
    method: 'get'
  })
}

// 获取所有台号总数
export const getDeskCount = () => {
  return axios.request({
    url: 'desk/count',
    method: 'get'
  })
}

// 获取所有台号
export const getDeskList = () => {
  return axios.request({
    url: 'deskList',
    method: 'get'
  })
}

// 模糊查询获取台号列表
export const getDeskListBySearchAndPage = (search, page) => {
  return axios.request({
    url: 'desk/search/' + search + '/page/' + page,
    method: 'get'
  })
}

// 获取模糊查询的台号总数
export const getDeskCountBySearch = (search) => {
  return axios.request({
    url: 'desk/search/' + search + '/count',
    method: 'get'
  })
}
