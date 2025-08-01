import request from '@/utils/request'

export function getList(params) {
  return request({ // 此函数用于获取反馈的列表。它向/feed-back/getPageList发送一个POST请求，请求参数是params
    url: '/feed-back/getPageList',
    method: 'post',
    params
  })
}
export function deleteFeedBack(params) {
  return request({ // 用于删除某个反馈。它向/feed-back/delete发送一个GET请求，请求的URL中包含了需要删除的反馈的ID，ID由params参数提供。
    url: '/feed-back/delete?id='+params,
    method: 'get',
  })
}
