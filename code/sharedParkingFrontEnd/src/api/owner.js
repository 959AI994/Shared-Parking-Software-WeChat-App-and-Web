import request from '@/utils/request'

export function geOwnerList(params) {
  return request({
    url: '/ownerAuth/getPageList',
    method: 'post',
    params
  })
}
export function arrces(params) {
  return request({
    url: '/ownerAuth/arrces',
    method: 'post',
    params
  })
}