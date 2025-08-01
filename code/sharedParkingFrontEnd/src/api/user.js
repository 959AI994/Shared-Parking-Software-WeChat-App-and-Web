import request from '@/utils/request'

export function login(params) {
  return request({
    url: '/sys/login',
    method: 'post',
    params
  })
}

export function getInfo(token) {
  return request({
    url: '/sys/menu/nav',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/vue-admin-template/user/logout',
    method: 'post'
  })
}

//分页获取用户信息
export function getUserList(params) {
  return request({
    url: '/user/getPageList',
    method: 'post',
    params
  })
}