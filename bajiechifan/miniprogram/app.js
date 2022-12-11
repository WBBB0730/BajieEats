// app.js
App({
  onLaunch: function () {
    if (!wx.cloud) {
      console.error('请使用 2.2.3 或以上的基础库以使用云能力');
    } else {
      wx.cloud.init({
        // env 参数说明：
        //   env 参数决定接下来小程序发起的云开发调用（wx.cloud.xxx）会默认请求到哪个云环境的资源
        //   此处请填入环境 ID, 环境 ID 可打开云控制台查看
        //   如不填则使用默认环境（第一个创建的环境）
        // env: 'my-env-id',
        traceUser: true,
      });
    }
    this.globalData = {
      token: wx.getStorageSync('token'),
      location: wx.getStorageSync('location')
    };
  },

  globalData: {
    token: '',
    location: null
  },

  // 登录
  login: function () {
    let _this = this;
    let token = '';
    // 获取code
    return new Promise((resolve, reject) => {
      wx.login({
        success: (res) => {
          if (res.code) {
            // 获取token
            wx.request({
              url: 'http://114.132.234.161:8888/bajie/user/login',
              data: {
                code: res.code,
              },
              success: (res) => {
                if (res.data.code == 200) {
                  token = res.data.data;
                  _this.globalData.token = token;
                  wx.setStorageSync('token', token);
                  console.log("登录成功", res);
                  resolve(token);
                } else {
                  console.log("登录（获取token）失败", res);
                  reject(res);
                }
              },
              fail: (res) => {
                console.log("登录（获取token）失败", res);
                reject(res);
              }
            })
          } else {
            console.log("登录（获取code）失败", res);
            reject(res);
          }
        },
        fail: (res) => {
          console.log("登录（获取code）失败", res);
          reject(res);
        }
      })
    });
  },

  request(options) {
    return new Promise((resolve, reject) => {
      options.url = "http://114.132.234.161:8888/bajie" + options.url;
      options.success = (res) => {
        resolve(res);
      };
      options.fail = (res) => {
        reject(res);
      };
      wx.request(options);
    })
  },

  showErr(message) {
    wx.showToast({
      title: message,
      icon: "error",
      mask: true
    });
  },


});