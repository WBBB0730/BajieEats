Page({
  data: {
    avatarUrl:wx.getStorageSync('userInfo').avatarUrl,
    name:wx.getStorageSync('userInfo').nickName,
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
      _this.getUserProfile(token);
    }
  },

  login: function () {
    console.log("this1: ", this);
    let _this = this;
    getApp().login().then(_this.getUserProfile);
  },

  getUserProfile: function (token) {
    let _this = this;
    console.log("login-token: " + token);
    wx.request({
      url: 'http://114.132.234.161:8888/bajie/user',
      header: {
        token: token
      },
      success: (res) => {
        _this.setData({
          avatarUrl: res.data.data.avatarUrl,
          name: res.data.data.nickName,
          flag: 1
        });
        wx.setStorageSync('userInfo', res.data.data);
      }
    });
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