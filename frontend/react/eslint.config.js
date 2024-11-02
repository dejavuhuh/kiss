import antfu from '@antfu/eslint-config'
import pluginQuery from '@tanstack/eslint-plugin-query'

const antfuConfig = await antfu({
  react: true,
})

const tanstackQueryConfig = pluginQuery.configs['flat/recommended']

export default [
  ...antfuConfig,
  ...tanstackQueryConfig,
]
