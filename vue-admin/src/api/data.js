import axios from '@/libs/api.request'

export const getListByPageNum = (pageNum) => {
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
    url: 'searchUserTotal/' + searchTxt ,
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

export const getDragList = () => {
  return axios.request({
    url: 'get_drag_list',
    method: 'get'
  })
}

export const errorReq = () => {
  return axios.request({
    url: 'error_url',
    method: 'post'
  })
}

export const saveErrorLogger = info => {
  return axios.request({
    url: 'save_error_logger',
    data: info,
    method: 'post'
  })
}

export const uploadImg = formData => {
  return axios.request({
    url: 'image/upload',
    data: formData
  })
}

export const getOrgData = () => {
  return axios.request({
    url: 'get_org_data',
    method: 'get'
  })
}

export const getTreeSelectData = () => {
  return axios.request({
    url: 'get_tree_select_data',
    method: 'get'
  })
}
