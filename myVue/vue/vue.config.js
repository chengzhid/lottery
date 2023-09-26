const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({

  devServer:{
    proxy: {
      '/userManagement': {  //代理地址
        target: 'http://localhost:80/userManagement',  //需要代理的地址， 实际生产环境需要访问的地址
        changeOrigin: true,  //是否跨域
        secure: false,
        pathRewrite: {
          '^/userManagement': ''   //本身的接口地址没有 '/api' 这种通用前缀，所以要rewrite，如果本身有则去掉
        }
      },
      '/getAllPrizeInfo':{
        // target: 'http://localhost:80/lottery',
        target: 'http://localhost:8772/getAllPrizeInfo',
        changeOrigin: true,
        secure: false,
        pathRewrite: {
          '^/getAllPrizeInfo' : ''
        }
      },
      '/getLottery':{
        // target: 'http://localhost:80/lottery',
        target: 'http://localhost:8772/getLottery',
        changeOrigin: true,
        secure: false,
        pathRewrite: {
          '^/getLottery' : ''
        }
      },
    },
  },

  transpileDependencies: true
})
