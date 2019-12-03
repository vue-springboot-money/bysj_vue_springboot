import axios from '@/libs/api.request'

export const getLogisticsByOid = (id) => {
  return axios.request({
    url: 'logistics/oid/' + id,
    method: 'get'
  })
}

export const getLogisticsByUid = (uid) => {
  return axios.request({
    url: 'logistics/uid/' + uid + '/page/1',
    method: 'get'
  })
}

export const changeLogisticsState = (order) => {
  return axios.request({
    url: 'logistics',
    data: order,
    method: 'patch'
  })
}
