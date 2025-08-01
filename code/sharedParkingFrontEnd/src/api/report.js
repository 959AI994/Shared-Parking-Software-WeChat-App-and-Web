import request from '@/utils/request'

// 函数用于获取收入报表的数据。它接收一个参数params，该参数包含了发送请求时需要的参数。
// 使用request函数向服务器发送POST请求，请求的URL是/parkingOrder/incomeStatement，
// 并将params作为请求的参数。
export function incomeStatement(params) {
  return request({
    url: '/parkingOrder/incomeStatement',
    method: 'post',
    params
  })
}

// orderReport(params)函数用于获取订单报表的数据。
// 它也接收一个参数params，并使用request函数向服务器发送POST请求，
// 请求的URL是/parkingOrder/orderReport，
// 并将params作为请求的参数。
export function orderReport(params) {
  return request({
    url: '/parkingOrder/orderReport',
    method: 'post',
    params
  })
}
