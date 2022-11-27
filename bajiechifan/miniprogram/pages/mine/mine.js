Page({
  data: {
    avatarUrl: "",
    name: "",
    flag: 0,
    myset: [{
        'name': '我发布的评论',
      },
      {
        'name': '我发布的失物招领',
      },
      {
        'name': '我发布的寻物启事',
      },
      {
        'name': '我的收藏',
      }
    ],
    set: "设置",
  },

  onShow: function () {
    let _this = this;
    let token = getApp().globalData.token;
    if (token) {
      _this.setData({
        flag: 1
      });
      let userInfo = wx.getStorageSync('userInfo');
      if (!userInfo) {
        console.log("用户信息已失效");
        wx.request({
          url: 'http://114.132.234.161:8888/bajie/user',
          header: {
            token: token
          },
          success: (res) => {
            console.log(res);
            wx.setStorageSync('userInfo', res.data.data);
            userInfo = res.data.data;
            _this.setData({
              avatarUrl: userInfo.avatarUrl,
              name: userInfo.nickName,
            });
          }
        });
      } else {
        _this.setData({
          avatarUrl: userInfo.avatarUrl,
          name: userInfo.nickName,
        });
      }
    }
  },

  getUserProfile: function () {
    let _this = this;
    // 获取用户信息
    wx.getUserProfile({
      desc: '获取用户头像、昵称',
      success: (res) => {
        let userInfo = res.userInfo;
        // 获取登录code
        wx.login({
          success: (res) => {
            if (res.code) {
              // 调用后端登录接口获取token
              wx.request({
                url: 'http://114.132.234.161:8888/bajie/user/login',
                data: {
                  code: res.code,
                },
                success: function (res) {
                  if (res.data.code == 200) {
                    let token = res.data.data;
                    wx.setStorageSync('token', token);
                    getApp().globalData.token = token;
                    // 上传用户信息
                    wx.request({
                      url: 'http://114.132.234.161:8888/bajie/user/updateUser',
                      method: 'POST',
                      header: {
                        token: token
                      },
                      data: {
                        nickName: userInfo.nickName,
                        avatarUrl: userInfo.avatarUrl
                      },
                      success: (res) => {
                        if (res.data.code == 200) {
                          console.log("用户信息上传成功");
                        } else {
                          console.log("用户信息上传失败");
                        }
                      }
                    })
                    wx.setStorageSync('userInfo', userInfo);
                    _this.setData({
                      flag: 1
                    });
                    _this.setData({
                      avatarUrl: userInfo.avatarUrl,
                      name: userInfo.nickName,
                    })
                  }
                },
                fail: function () {
                  console.log("token获取失败")
                }
              })
            } else {
              console.log('登录失败' + res.errMsg);
            }
          },
        });
      },
      fail: (res) => {
        console.log("用户拒绝授权");
      }
    })
  },

  toInformation: function (e) {
    wx.navigateTo({
      url: '../information/information',
    })
  },

  tomyset: function (e) {
    if (e.currentTarget.dataset.type == '我发布的评论') {
      wx.navigateTo({
        url: '../comment/comment',
      })
    } else if (e.currentTarget.dataset.type == '我发布的失物招领') {
      wx.navigateTo({
        url: '../Lost_and_Found/Lost_and_Found',
      })
    } else if (e.currentTarget.dataset.type == '我发布的寻物启事') {
      wx.navigateTo({
        url: '../Notice_for_Lost/Notice_for_Lost',
      })
    } else if (e.currentTarget.dataset.type == '我的收藏') {
      wx.navigateTo({
        url: '../myFavorites/myFavorites',
      })
    }
  },

})