function padLeftZero (str) {
  return ('00' + str).substr(str.length)
}

export function formatDate (date, fmt) {
  if (/(y+)/.test(fmt)) {
    fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length))
  }
  let o = {
    'M+': date.getMonth() + 1,
    'd+': date.getDate(),
    'h+': date.getHours(),
    'm+': date.getMinutes(),
    's+': date.getSeconds()
  }
  for (let k in o) {
    if (new RegExp(`(${k})`).test(fmt)) {
      let str = o[k] + ''
      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? str : padLeftZero(str))
    }
  }
  return fmt
}

function createNode (node, list, level = 1) {
  node['level'] = level
  const nodes = [node]
  const child = list.filter(x => x.parentid === node.id)
  if (child.length > 0) {
    node['leaf'] = false
    child.forEach(x => {
      nodes.push(...createNode(x, list, level + 1))
    })
  } else {
    node['leaf'] = true
  }
  return nodes
}

export function formatTree (list) {
  const tree = []
  list.filter(x => x.parentid === 0).forEach(x => {
    tree.push(...createNode(x, list))
  })
  return tree
}
