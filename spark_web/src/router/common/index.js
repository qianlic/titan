const image = {
  path: 'image',
  name: 'image',
  component: () => import('../../views/image/Image')
}

const article = {
  path: 'article',
  name: 'article',
  component: () => import('../../views/article/Article')
}

const articleFrom = {
  path: 'articleFrom',
  name: 'articleFrom',
  component: () => import('../../views/article/articleFrom')
}

export default [
  image,
  article,
  articleFrom
]
