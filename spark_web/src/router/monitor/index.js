const thread = {
  path: 'thread',
  name: 'thread',
  component: () => import('../../views/thread/Thread')
}

const tomcat = {
  path: 'tomcat',
  name: 'tomcat',
  component: () => import('../../views/tomcat/Tomcat')
}

const redis = {
  path: 'redis',
  name: 'redis',
  component: () => import('../../views/redis/Redis')
}

const memory = {
  path: 'memory',
  name: 'memory',
  component: () => import('../../views/memory/Memory')
}

export default [
  thread,
  tomcat,
  redis,
  memory
]
