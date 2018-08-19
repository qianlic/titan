import Store from 'storejs'

const STORE_PREFIX = 'titan'
const AUTH_TOKEN = 'AUTH_TOKEN'

export function getAuthTokenItem (key) {
  const token = getStoreItem(AUTH_TOKEN)
  if (token) {
    return token[key]
  }
  return undefined
}

export function setAuthToken (token) {
  return setStoreItem(AUTH_TOKEN, token)
}

export function clearStore () {
  return Store.clear()
}

export function getStoreItem (key) {
  return Store.get(`${STORE_PREFIX} - ${key}`)
}

export function setStoreItem (key, value) {
  return Store.set(`${STORE_PREFIX} - ${key}`, value)
}

export function removeStoreItem (key) {
  return Store.remove(`${STORE_PREFIX} - ${key}`)
}

export default {
  getAuthTokenItem,
  setAuthToken,
  clearStore,
  getStoreItem,
  setStoreItem,
  removeStoreItem
}
