module.exports = {
  devServer: {
    proxy: {
        "^/": {
          target: "http://35.240.133.202:7000"
        }
    }
  }
}
