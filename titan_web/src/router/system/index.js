const user = {
  path: 'user',
  name: 'user',
  component: () => import('../../views/user/User')
}

const userFrom = {
  path: 'userFrom',
  name: 'userFrom',
  component: () => import('../../views/user/UserFrom')
}

const role = {
  path: 'role',
  name: 'role',
  component: () => import('../../views/role/Role')
}

const roleFrom = {
  path: 'roleFrom',
  name: 'roleFrom',
  component: () => import('../../views/role/RoleFrom')
}

const resource = {
  path: 'resource',
  name: 'resource',
  component: () => import('../../views/resource/Resource')
}

const resourceFrom = {
  path: 'resourceFrom',
  name: 'resourceFrom',
  component: () => import('../../views/resource/ResourceFrom')
}

const image = {
  path: 'image',
  name: 'image',
  component: () => import('../../views/image/Image')
}

export default [
  user,
  userFrom,
  role,
  roleFrom,
  resource,
  resourceFrom,
  image
]
