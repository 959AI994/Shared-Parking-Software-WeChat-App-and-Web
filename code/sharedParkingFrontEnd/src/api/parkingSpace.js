import request from '@/utils/request'

export function getParkingSpaceList(params) {
  return request({
    url: '/parkingSpace/getPageList',
    method: 'post',
    params
  })
}
export function getSpaceServiceById(params) {
  return request({
    url: '/parkingSpace/getSpaceServiceById?id='+params,
    method: 'get',
    params
  })
}
//修改
export function update(params) {
  return request({
    url: '/parkingSpace/update',
    method: 'post',
    params
  })
}