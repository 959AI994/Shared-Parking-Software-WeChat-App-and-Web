import { login, logout, getInfo } from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'

const getDefaultState = () => {
  return {
    token: getToken(),
    name: '',
    avatar: '',
    roles: '',
    menus: []
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_ROLES: (state, roles) => {
    state.roles = roles
  },
  SET_MENUS: (state, menus) => {
    state.menus = menus
  }
}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { username, password } = userInfo
    // commit('SET_NAME', username)
    // commit('SET_TOKEN', "vue_admin_template_token")
    // setToken("vue_admin_template_token")
    // commit('SET_AVATAR', "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif")
    return new Promise((resolve, reject) => {
      login({ username: username.trim(), password: password }).then(response => {
        const { data } = response
        // console.log("resToken",data)
        commit('SET_TOKEN', data)
        setToken(data)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // get user info
  // getInfo({ commit, state }) {
  // 通过上面的令牌验证 servlet，得到返回值
  // {
  //  code : 200,
  //  message: "获取token成功",
  //  success: true,
  //  data: "{
  //		"role" : "user",
  //		"avatar": "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
  //		"introduction":"I am an user",
  //		"name" : "User"
  //	}"
  // }
  // 对 data 进行解析
  // commit('SET_NAME', "admin")
  // commit('SET_AVATAR', "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif")

  // return new Promise((resolve, reject) => {
  //   getInfo(state.token).then(response => {
  //     const { data } = response

  //     if (!data) {
  //       return reject('Verification failed, please Login again.')
  //     }

  //     const { name, avatar } = data

  //     commit('SET_NAME', name)
  //     commit('SET_AVATAR', avatar)
  //     resolve(data)
  //   }).catch(error => {
  //     reject(error)
  //   })
  // })
  // },

  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo(state.token).then(response => {
        const { data } = response
        if (!data) {
          reject('Verification failed, please Login again.')
        }
        console.log('menuDate', data.menu)
        const menus =
          [{
            path: '/parking',
            component: 'Layout',
            redirect: '/parking/parkingList',
            name: 'parking',
            orderNum: 0,
            parentName: null,
            meta: { title: '小区停车场管理', icon: 'el-icon-s-help' },
            children: [{
              path: '/parkingList',
              name: 'parkingList',
              component: '/parking/parkingList',
              meta: { title: '小区停车场管理', icon: 'el-icon-location-information' }
            }]
          }]

        // const { name, avatar, companyName, employeeid } = data
        commit('SET_NAME', 'admin')
        // commit('SET_AVATAR', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif')
        commit('SET_AVATAR', 'https://img.soogif.com/NQy8FNhHUebORPnFRkJogMVARjCvkpRv.gif') // 前端界面的动图小图标
        commit('SET_ROLES', 'admin')
        commit('SET_MENUS', data.menu)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({ commit, state }) {
    removeToken()
    resetRouter()
    console.log('resetRouter')
    commit('RESET_STATE')
    // return new Promise((resolve, reject) => {

    //   // logout(state.token).then(() => {
    //   //   removeToken() // must remove  token  first
    //   //   resetRouter()
    //   //   commit('RESET_STATE')
    //   //   resolve()
    //   // }).catch(error => {
    //   //   reject(error)
    //   // })
    // })
  },

  // remove token
  resetToken({ commit }) {
    removeToken() // must remove  token  first
    commit('RESET_STATE')
    // return new Promise(resolve => {
    //   removeToken() // must remove  token  first
    //   commit('RESET_STATE')
    //   resolve()
    // })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

