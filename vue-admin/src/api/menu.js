import axios from '@/libs/api.request'

// 创建菜单
export const createMenu = (menu) => {
  return axios.request({
    url: 'menu',
    data: menu,
    method: 'post'
  })
}

// 更新菜单
export const updateMenu = (menu) => {
  return axios.request({
    url: 'menu',
    data: menu,
    method: 'patch'
  })
}

// 更新菜单状态
export const updateMenuStateById = (id) => {
  return axios.request({
    url: 'menu/id/' + id + '/state',
    method: 'patch'
  })
}

// 获取指定id的菜单
export const getMenuById = (id) => {
  return axios.request({
    url: 'menu/id/' + id,
    method: 'get'
  })
}

// 删除菜单
export const deleteMenuById = (id) => {
  return axios.request({
    url: 'menu/id/' + id,
    method: 'delete'
  })
}

// 分页获取菜单列表
export const getMenuListByPage = (page) => {
  return axios.request({
    url: 'menu/page/' + page,
    method: 'get'
  })
}

// 获取所有菜单总数
export const getMenuCount = () => {
  return axios.request({
    url: 'menu/count',
    method: 'get'
  })
}

// 模糊查询获取菜单列表
export const getMenuListBySearchAndPage = (search, page) => {
  return axios.request({
    url: 'menu/search/' + search + '/page/' + page,
    method: 'get'
  })
}

// 获取模糊查询的菜单总数
export const getMenuCountBySearch = (search) => {
  return axios.request({
    url: 'menu/search/' + search + '/count',
    method: 'get'
  })
}
