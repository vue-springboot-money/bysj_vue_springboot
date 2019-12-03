import axios from '@/libs/api.request'

// 创建分类
export const createCategory = (category) => {
  return axios.request({
    url: 'category',
    data: category,
    method: 'post'
  })
}

// 更新分类
export const updateCategory = (category) => {
  return axios.request({
    url: 'category',
    data: category,
    method: 'patch'
  })
}

// 更新分类状态
export const updateCategoryStateById = (id) => {
  return axios.request({
    url: 'category/id/' + id + '/state',
    method: 'patch'
  })
}

// 获取指定id的分类
export const getCategoryById = (id) => {
  return axios.request({
    url: 'category/id/' + id,
    method: 'get'
  })
}

// 删除分类
export const deleteCategoryById = (id) => {
  return axios.request({
    url: 'category/id/' + id,
    method: 'delete'
  })
}

// 分页获取分类列表
export const getCategoryListByPage = (page) => {
  return axios.request({
    url: 'category/page/' + page,
    method: 'get'
  })
}

// 获取所有分类总数
export const getCategoryCount = () => {
  return axios.request({
    url: 'category/count',
    method: 'get'
  })
}

// 模糊查询获取分类列表
export const getCategoryListBySearchAndPage = (search, page) => {
  return axios.request({
    url: 'category/search/' + search + '/page/' + page,
    method: 'get'
  })
}

// 获取模糊查询的分类总数
export const getCategoryCountBySearch = (search) => {
  return axios.request({
    url: 'category/search/' + search + '/count',
    method: 'get'
  })
}

// 获取所有分类
export const getCategoryList = () => {
  return axios.request({
    url: 'categoryList',
    method: 'get'
  })
}
