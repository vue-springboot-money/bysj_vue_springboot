import axios from '@/libs/api.request'

// 创建交接班
export const createShift = (shift) => {
  return axios.request({
    url: 'shift',
    data: shift,
    method: 'post'
  })
}

// 更新交接班
export const updateShift = (shift) => {
  return axios.request({
    url: 'shift',
    data: shift,
    method: 'patch'
  })
}

// 获取指定id的交接班
export const getShiftById = (id) => {
  return axios.request({
    url: 'shift/id/' + id,
    method: 'get'
  })
}

// 删除交接班
export const deleteShiftById = (id) => {
  return axios.request({
    url: 'shift/id/' + id,
    method: 'delete'
  })
}

// 获取所有交接班
export const getShiftList = () => {
  return axios.request({
    url: 'shiftList',
    method: 'get'
  })
}

// 分页获取交接班列表
export const getShiftListByPage = (page) => {
  return axios.request({
    url: 'shift/page/' + page,
    method: 'get'
  })
}

// 获取所有交接班总数
export const getShiftCount = () => {
  return axios.request({
    url: 'shift/count',
    method: 'get'
  })
}

// 模糊查询获取交接班列表
export const getShiftListBySearchAndPage = (search, page) => {
  return axios.request({
    url: 'shift/search/' + search + '/page/' + page,
    method: 'get'
  })
}

// 获取模糊查询的交接班总数
export const getShiftCountBySearch = (search) => {
  return axios.request({
    url: 'shift/search/' + search + '/count',
    method: 'get'
  })
}
