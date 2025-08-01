import request from '@/utils/request'
// API函数是在前端开发中使用的，主要用于在客户端（通常是Web浏览器）和服务器之间进行数据交互。
// 一些发送HTTP请求的函数，这些函数使用了一个名为request的函数来发送请求。
// 这些函数用于处理对“访问权限(access)”的一些操作，包括获取列表、添加、更新和删除。
// 每个函数接收一个名为params的参数，该参数被传递给request函数作为请求参数。这些函数都使用POST方法发送请求。
export function getAccessPageList(params) {
  return request({
    url: '/access/getPageList',
    method: 'post',
    params
  })
}
export function addAccess(params) {
  return request({
    url: '/access/add',
    method: 'post',
    params
  })
}
export function updateAccess(params) {
  return request({
    url: '/access/update',
    method: 'post',
    params
  })
}
export function deleteAccess(params) {
  return request({
    url: '/access/delete',
    method: 'post',
    params
  })
}
