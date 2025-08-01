import request from '@/utils/request'

export function getUserCount() {
  return request({
    url: '/user/count', // 这个函数向/user/count发送一个GET请求以获取用户的数量。
    method: 'get',
    params: null
  })
}
export function getFeedBackCount() {
  return request({
    url: '/feed-back/count', // 向/feed-back/count发送一个GET请求以获取反馈的数量。
    method: 'get',
    params: null
  })
}
export function getOrderrCount() {
  return request({
    url: '/parkingOrder/count', // /parkingOrder/count发送一个GET请求以获取停车订单的数量。
    method: 'get',
    params: null
  })
}
export function getSpaceInfoCount() {
  return request({
    url: '/parkingSpace/count', // 向/parkingSpace/count发送一个GET请求以获取停车位的数量。
    method: 'get',
    params: null
  })
}
