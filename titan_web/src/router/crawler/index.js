const crawler = {
  path: 'crawler',
  name: 'crawler',
  component: () => import('../../views/crawler/Crawler')
}

const crawlerFrom = {
  path: 'crawlerFrom',
  name: 'crawlerFrom',
  component: () => import('../../views/crawler/CrawlerFrom')
}

const weburl = {
  path: 'weburl',
  name: 'weburl',
  component: () => import('../../views/weburl/WebUrl')
}

export default [
  crawler,
  crawlerFrom,
  weburl
]
