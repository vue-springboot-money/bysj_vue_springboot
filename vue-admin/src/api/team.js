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

// 获取指定id的队伍
export const getTeamById = (id) => {
  return axios.request({
    url: 'team/id/' + id,
    method: 'get'
  })
}

// 删除队伍
export const deleteTeamById = (id) => {
  return axios.request({
    url: 'team/id/' + id,
    method: 'delete'
  })
}

// 分页获取队伍列表
export const getTeamListByPage = (page) => {
  return axios.request({
    url: 'team/page/' + page,
    method: 'get'
  })
}

// 获取所有队伍总数
export const getTeamCount = () => {
  return axios.request({
    url: 'team/count',
    method: 'get'
  })
}

// 模糊查询获取队伍列表
export const getTeamListBySearchAndPage = (search, page) => {
  return axios.request({
    url: 'team/search/' + search + '/page/' + page,
    method: 'get'
  })
}

// 获取模糊查询的队伍总数
export const getTeamCountBySearch = (search) => {
  return axios.request({
    url: 'team/search/' + search + '/count',
    method: 'get'
  })
}
