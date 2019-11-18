import axios from '@/libs/api.request'

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
    url: 'searchNewsTotal/' + searchTxt,
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