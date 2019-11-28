import axios from '@/libs/api.request'

// 创建新闻
export const createNews = (news) => {
  return axios.request({
    url: 'news',
    data: news,
    method: 'post'
  })
}

// 更新新闻
export const updateNews = (news) => {
  return axios.request({
    url: 'news',
    data: news,
    method: 'patch'
  })
}

// 查询指定id的新闻
export const getNewsById = (id) => {
  return axios.request({
    url: 'news/id/' + id,
    method: 'get'
  })
}

// 删除指定id的新闻
export const deleteNews = (id) => {
  return axios.request({
    url: 'news/id/' + id,
    method: 'delete'
  })
}

// 分页查询
export const getNewsListByPage = (page) => {
  return axios.request({
    url: 'news/page/' + page,
    method: 'get'
  })
}

// 获取所有新闻数量
export const getNewsCount = () => {
  return axios.request({
    url: 'news/count',
    method: 'get'
  })
}