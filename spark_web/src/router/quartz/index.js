const execute = {
  path: 'execute',
  name: 'execute',
  component: () => import('../../views/execute/Execute')
}

const schedule = {
  path: 'schedule',
  name: 'schedule',
  component: () => import('../../views/schedule/Schedule')
}

const scheduleFrom = {
  path: 'scheduleFrom',
  name: 'scheduleFrom',
  component: () => import('../../views/schedule/ScheduleFrom')
}

export default [
  execute,
  schedule,
  scheduleFrom
]
