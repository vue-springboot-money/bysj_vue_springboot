import axios from '@/libs/api.request'

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
    url: 'searchMenuTotal/' + searchTxt,
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

export const getMenuData = () => {
  return axios.request({
    url: 'menus/1',
    method: 'get'
  })
}

export const getMenuByName = (name) => {
  return axios.request({
    url: 'menus/name/' + name,
    method: 'get'
  })
}

