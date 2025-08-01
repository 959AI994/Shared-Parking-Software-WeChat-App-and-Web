import defaultSettings from '@/settings'

const title = defaultSettings.title || '共享车位后台管理'

export default function getPageTitle(pageTitle) {
  if (pageTitle) {
    return `${pageTitle} - ${title}`
  }
  return `${title}`
}
