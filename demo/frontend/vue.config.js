// vue.config.js
const { defineConfig } = require('@vue/cli-service')
module.exports = {

    // options...
    devServer: {
        proxy: 'https://localhost:8080/',
    }
}