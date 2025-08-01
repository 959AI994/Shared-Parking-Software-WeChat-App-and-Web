import request from '@/utils/request'

export function loginsss(params) {
  return request({ // 获取停车信息列表。
    url: '/parkingInfo/getList',
    method: 'get',
    params
  })
}
