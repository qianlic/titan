const jobdetail = {
  path: 'jobdetail',
  name: 'jobdetail',
  component: () => import('../../views/jobdetail/JobDetail')
}

const jobdetailFrom = {
  path: 'jobdetailFrom',
  name: 'jobdetailFrom',
  component: () => import('../../views/jobdetail/JobDetailFrom')
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
  jobdetail,
  jobdetailFrom,
  schedule,
  scheduleFrom
]
