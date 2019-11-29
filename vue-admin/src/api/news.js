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

// 更新新闻状态
export const updateNewsStateById = (id) => {
  return axios.request({
    url: 'news/id/' + id + '/state',
    method: 'patch'
  })
}

// 获取指定id的新闻
export const getNewsById = (id) => {
  return axios.request({
    url: 'news/id/' + id,
    method: 'get'
  })
}

// 删除新闻
export const deleteNewsById = (id) => {
  return axios.request({
    url: 'news/id/' + id,
    method: 'delete'
  })
}

// 分页获取新闻列表
export const getNewsListByPage = (page) => {
  return axios.request({
    url: 'news/page/' + page,
    method: 'get'
  })
}

// 获取所有新闻总数
export const getNewsCount = () => {
  return axios.request({
    url: 'news/count',
    method: 'get'
  })
}

// 模糊查询获取新闻列表
export const getNewsListBySearchAndPage = (search, page) => {
  return axios.request({
    url: 'news/search/' + search + '/page/' + page,
    method: 'get'
  })
}

// 获取模糊查询的新闻总数
export const getNewsCountBySearch = (search) => {
  return axios.request({
    url: 'news/search/' + search + '/count',
    method: 'get'
  })
}
