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

// 菜品相关
export const getMenuListByPageNum = (pageNum) => {
  return axios.request({
    url: 'menus/' + pageNum,
    method: 'get'
  })
}

export const getMenuTotal = () => {
  return axios.request({
    url: 'menuTotal',
    method: 'get'
  })
}

export const getMenuInfoById = (id) => {
  return axios.request({
    url: 'menu/' + id,
    method: 'get'
  })
}

export const getSearchMenuTotal = (searchTxt) => {
  return axios.request({
    url: 'searchMenuTotal/' + searchTxt ,
    method: 'get'
  })
}

export const searchMenu = (searchTxt, pageNum) => {
  return axios.request({
    url: 'menu/search/' + searchTxt + '/pageNum/' + pageNum + '',
    method: 'get'
  })
}

export const createMenu = (menu) => {

  if (menu.img === 'https://img.zcool.cn/community/01a92a5a151826a80120518742bb1d.JPG') {
    menu.img = null
  }

  return axios.request({
    url: 'menu',
    data: menu,
    method: 'post'
  })
}

export const updateMenu = (menu) => {
  return axios.request({
    url: 'menu',
    data: menu,
    method: 'patch'
  })
}

export const deleteMenu = (id) => {
  return axios.request({
    url: 'menu/' + id,
    method: 'delete'
  })
}

// 新闻相关
export const getNewsListByPageNum = (pageNum) => {
  return axios.request({
    url: 'newsList/' + pageNum,
    method: 'get'
  })
}

export const getNewsTotal = () => {
  return axios.request({
    url: 'newsTotal',
    method: 'get'
  })
}

export const getNewsInfoById = (id) => {
  return axios.request({
    url: 'news/' + id,
    method: 'get'
  })
}

export const getSearchNewsTotal = (searchTxt) => {
  return axios.request({
    url: 'searchNewsTotal/' + searchTxt ,
    method: 'get'
  })
}

export const searchNews = (searchTxt, pageNum) => {
  return axios.request({
    url: 'news/search/' + searchTxt + '/pageNum/' + pageNum + '',
    method: 'get'
  })
}

export const createNews = (news) => {

  if (news.img === 'https://img.zcool.cn/community/01a92a5a151826a80120518742bb1d.JPG') {
    news.img = null
  }

  return axios.request({
    url: 'news',
    data: news,
    method: 'post'
  })
}

export const updateNews = (news) => {
  return axios.request({
    url: 'news',
    data: news,
    method: 'patch'
  })
}

export const deleteNews = (id) => {
  return axios.request({
    url: 'news/' + id,
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
