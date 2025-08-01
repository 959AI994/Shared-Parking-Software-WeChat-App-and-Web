const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  cmpname: state => state.user.cmpname,
  userid: state => state.user.userid,
  roles: state => state.user.roles,
  menus: state => state.user.menus
}
export default getters
