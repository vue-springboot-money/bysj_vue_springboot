import axios from '@/libs/api.request'

// 用户相关
export const getUserListByPageNum = (pageNum) => {
  return axios.request({
    url: 'users/' + pageNum,
    method: 'get'
  })
}

export const getUserTotal = () => {
  return axios.request({
    url: 'userTotal',
    method: 'get'
  })
}

export const getUserInfoById = (id) => {
  return axios.request({
    url: 'user/' + id,
    method: 'get'
  })
}

export const getSearchUserTotal = (searchTxt) => {
  return axios.request({
    url: 'searchUserTotal/' + searchTxt,
    method: 'get'
  })
}

export const searchUser = (searchTxt, pageNum) => {
  return axios.request({
    url: 'user/search/' + searchTxt + '/pageNum/' + pageNum + '',
    method: 'get'
  })
}

export const createUser = (user) => {
  return axios.request({
    url: 'user',
    data: user,
    method: 'post'
  })
}

export const updateUser = (user) => {
  return axios.request({
    url: 'user',
    data: user,
    method: 'patch'
  })
}

export const deleteUser = (id) => {
  return axios.request({
    url: 'user/' + id,
    method: 'delete'
  })
}

export const recharge = (user) => {
  return axios.request({
    url: 'recharge',
    data: user,
    method: 'patch'
  })
}