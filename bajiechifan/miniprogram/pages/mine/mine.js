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

  onLoad: function() {
    let _this = this;
    let app = getApp();
    console.log("token1:" + app.globalData.token);
    if (app.globalData.token) {
      _this.setData({
        flag: 1
      });
    }
  },

  getUserProfile: function () {
    let _this = this;
    wx.login({
      success: (res) => {
        if (res.code) {
          wx.request({
            url: 'http://114.132.234.161:8888/bajie/user/login',
            data: {
              code: res.code,
            },
            success: function (res) {
              console.log(res);
              wx.setStorageSync('token', res.data.data);
            },
          })
        } else {
          console.log('登录失败' + res.errMsg);
        }
      },
    })
    wx.getUserProfile({
      desc: '你就说给不给吧',
      success: (res) => {
        console.log('success', res)
        wx.setStorage({
          data: res.userInfo,
          key: 'userInfo',
        });
        this.setData({
          flag: 1
        })
        this.onShow();
      }
    })
  },

  Toinformation: function (e) {
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

  onShow: function () {
    var useInfo = wx.getStorageSync('userInfo')
    var that = this;
    that.setData({
      avatarUrl: useInfo.avatarUrl,
      name: useInfo.nickName,
    })
  }
})