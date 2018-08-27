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

const trigger = {
  path: 'trigger',
  name: 'trigger',
  component: () => import('../../views/trigger/Trigger')
}

const triggerFrom = {
  path: 'triggerFrom',
  name: 'triggerFrom',
  component: () => import('../../views/trigger/TriggerFrom')
}

export default [
  jobdetail,
  jobdetailFrom,
  trigger,
  triggerFrom
]
