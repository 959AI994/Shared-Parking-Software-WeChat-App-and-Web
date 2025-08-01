import request from '@/utils/request'
// 分页列表
export function getParkingList(params) {
  return request({
    url: '/parkingInfo/getPageList',
    method: 'post',
    params
  })
}
// 根据地址获取经纬度
export function getLonLat(params) {
  return request({
    url: '/point/getLonLat?address=' + params,
    method: 'get'
  })
}
// 添加小区停车场
export function parkingInfoAdd(params) {
  return request({
    url: '/parkingInfo/add',
    method: 'post',
    params
  })
}

// 查询单个小区停车场信息
export function getParkingInfo(params) {
  return request({
    url: '/parkingInfo/getParkingInfo?id=' + params,
    method: 'get'
  })
}
// 小区选择器
export function getpickerInfo(params) {
  return request({
    url: '/parkingInfo/pickerInfo',
    method: 'get'
  })
}
