import request from '@/utils/request'

export function getPageList(params) {
  return request({
    url: '/parkingOrder/getPageList',
    method: 'post',
    params
  })
}
export function deleteOrder(params) {
  return request({
    url: '/parkingOrder/delete?id=' + params,
    method: 'get',
    params
  })
}

export function update(params) {
  return request({
    url: '/parkingOrder/update',
    method: 'post',
    params
  })
}
// 识别入场
export function identificationPlateIntoThe(params) {
  return request({
    url: '/parkingOrder/identificationPlateIntoThe',
    method: 'post',
    params
  })
}
// 识别出场结算
export function orderConfirmationForSettlement(params) {
  return request({
    url: '/parkingOrder/orderConfirmationForSettlement',
    method: 'post',
    params
  })
}
