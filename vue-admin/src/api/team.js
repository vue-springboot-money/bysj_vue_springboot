import axios from '@/libs/api.request'

// 创建队伍
export const createTeam = (team) => {
  return axios.request({
    url: 'team',
    data: team,
    method: 'post'
  })
}

// 更新队伍
export const updateTeam = (team) => {
  return axios.request({
    url: 'team',
    data: team,
    method: 'patch'
  })
}

// 查询指定id的队伍
export const getTeamById = (id) => {
  return axios.request({
    url: 'team/id/' + id,
    method: 'get'
  })
}

// 删除指定id的队伍
export const deleteTeam = (id) => {
  return axios.request({
    url: 'team/id/' + id,
    method: 'delete'
  })
}

// 分页查询
export const getTeamListByPage = (page) => {
  return axios.request({
    url: 'team/page/' + page,
    method: 'get'
  })
}

// 获取所有队伍数量
export const getTeamCount = () => {
  return axios.request({
    url: 'team/count',
    method: 'get'
  })
}

// 模糊查询
export const getTeamListBySearchAndPage = (search, page) => {
  return axios.request({
    url: 'team/search/' + search + '/page/' + page,
    method: 'get'
  })
}

// 获取符合检索条件的所有队伍数量
export const getTeamCountBySearch = (search) => {
  return axios.request({
    url: '/team/search/' + search + '/count',
    method: 'get'
  })
}