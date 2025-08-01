import request from '@/utils/request'

export function getMenList(params) {
    return request({
      url: '/sys/menu/list',
      method: 'get',
      params
    })
};

export function getSysUserList(params) {
    return request({
      url: '/sys/user/list',
      method: 'get',
      params
    })
};
