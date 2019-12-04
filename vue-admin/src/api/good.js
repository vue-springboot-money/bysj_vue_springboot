import axios from '@/libs/api.request'

// 商品相关
export const getGoodListByPageNum = (pageNum) => {
  return axios.request({
    url: 'goods/' + pageNum,
    method: 'get'
  })
}

export const getGoodTotal = () => {
  return axios.request({
    url: 'goodTotal',
    method: 'get'
  })
}

export const getGoodInfoById = (id) => {
  return axios.request({
    url: 'good/' + id,
    method: 'get'
  })
}

export const getSearchGoodTotal = (searchTxt) => {
  return axios.request({
    url: 'searchGoodTotal/' + searchTxt,
    method: 'get'
  })
}

export const searchGood = (searchTxt, pageNum) => {
  return axios.request({
    url: 'good/search/' + searchTxt + '/pageNum/' + pageNum + '',
    method: 'get'
  })
}

export const createGood = (good) => {

  if (good.img === 'https://img.zcool.cn/community/01a92a5a151826a80120518742bb1d.JPG') {
    good.img = null
  }

  return axios.request({
    url: 'good',
    data: good,
    method: 'post'
  })
}

export const updateGood = (good) => {
  return axios.request({
    url: 'good',
    data: good,
    method: 'patch'
  })
}

export const deleteGood = (id) => {
  return axios.request({
    url: 'good/' + id,
    method: 'delete'
  })
}

export const getGoodData = () => {
  return axios.request({
    url: 'goods/1',
    method: 'get'
  })
}

export const getGoodsByCategory = (cid) => {
  cid = cid + 1
  return axios.request({
    url: 'goods/category/' + cid + '/page/1',
    method: 'get'
  })
}



