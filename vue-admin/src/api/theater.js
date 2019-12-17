import axios from '@/libs/api.request'
import { deleteTicketById } from './ticket';

// 创建剧场
export const createTheater = (theater) => {
  return axios.request({
    url: 'theater',
    data: theater,
    method: 'post'
  })
}

// 更新剧场
export const updateTheater = (theater) => {
  return axios.request({
    url: 'theater',
    data: theater,
    method: 'patch'
  })
}

// 获取指定id的剧场
export const getTheaterById = (id) => {
  return axios.request({
    url: 'theater/id/' + id,
    method: 'get'
  })
}

// 删除剧场
export const deleteTheaterById = (id) => {
  return axios.request({
    url: 'theater/id/' + id,
    method: 'delete'
  })
}

// 获取所有剧场
export const getTheaterList = () => {
  return axios.request({
    url: 'theaterList',
    method: 'get'
  })
}

// 分页获取剧场列表
export const getTheaterListByPage = (page) => {
  return axios.request({
    url: 'theater/page/' + page,
    method: 'get'
  })
}

// 获取所有剧场总数
export const getTheaterCount = () => {
  return axios.request({
    url: 'theater/count',
    method: 'get'
  })
}

// 模糊查询获取剧场列表
export const getTheaterListBySearchAndPage = (search, page) => {
  return axios.request({
    url: 'theater/search/' + search + '/page/' + page,
    method: 'get'
  })
}

// 获取模糊查询的剧场总数
export const getTheaterCountBySearch = (search) => {
  return axios.request({
    url: 'theater/search/' + search + '/count',
    method: 'get'
  })
}

// 获取模糊查询的剧场总数
export const getTheaterTime = (tid) => {
  return axios.request({
    url: 'dys/program/tid/' + tid,
    method: 'get'
  })
}
