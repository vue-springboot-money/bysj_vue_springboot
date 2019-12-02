import axios from '@/libs/api.request'

// 类别相关
export const getCategoryListByPageNum = (pageNum) => {
  return axios.request({
    url: 'categorys/' + pageNum,
    method: 'get'
  })
}

export const getCategoryTotal = () => {
  return axios.request({
    url: 'categoryTotal',
    method: 'get'
  })
}

export const getCategoryInfoById = (id) => {
  return axios.request({
    url: 'category/' + id,
    method: 'get'
  })
}

export const getSearchCategoryTotal = (searchTxt) => {
  return axios.request({
    url: 'searchCategoryTotal/' + searchTxt,
    method: 'get'
  })
}

export const searchCategory = (searchTxt, pageNum) => {
  return axios.request({
    url: 'category/search/' + searchTxt + '/pageNum/' + pageNum + '',
    method: 'get'
  })
}

export const createCategory = (category) => {

  if (category.img === 'https://img.zcool.cn/community/01a92a5a151826a80120518742bb1d.JPG') {
    category.img = null
  }

  return axios.request({
    url: 'category',
    data: category,
    method: 'post'
  })
}

export const updateCategory = (category) => {
  return axios.request({
    url: 'category',
    data: category,
    method: 'patch'
  })
}

export const deleteCategory = (id) => {
  return axios.request({
    url: 'category/' + id,
    method: 'delete'
  })
}

export const getCategoryData = () => {
  return axios.request({
    url: 'categorys/1',
    method: 'get'
  })
}


