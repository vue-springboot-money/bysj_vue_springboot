import axios from '@/libs/api.request'

export const getLogisticsByOid = (id) => {
  return axios.request({
    url: 'logistics/oid/' + id,
    method: 'get'
  })
}