Page({
  data: {
    canteen_list: [],
    collectFlagArray: [],
    swiperList: []
  },
  onShow() {
    var j;
    var temp = [];
    for (j = 0; j < this.data.canteen_list.length; j++) {
      temp[j] = false;
    }
    this.setData({
      collectFlagArray: temp
    })
    var _this = this;
    wx.request({
      url: 'http://114.132.234.161:8888/bajie/canteen/list',
      method: 'POST',
      data: {
        "sortType": 0,
        "longitude": 8,
        "latitude": 82
      },
      header: {
        'token': getApp().globalData.token,
        'contebt-Type': 'application/x-www-form-urlencoded'
      },
      success(res) {
        var resstr = JSON.stringify(res.data)
        var resobj = JSON.parse(resstr)
        _this.setData({
          canteen_list: resobj.data
        })
        console.log(resobj);
      },
      fail() {
        console.log("fail")
      }
    })
    wx.request({
      url: 'http://114.132.234.161:8888/bajie/banner/list',
      method: 'GET',
      header: {
        'contebt-Type': 'application/json'
      },
      success(res) {
        var resstr = JSON.stringify(res.data)
        var resobj = JSON.parse(resstr)
        _this.setData({
          swiperList: resobj.data
        })
      },
      fail() {
        console.log("fail")
      }
    })
  },
  toCanteenDetail(e) {
    wx.navigateTo({
      url: '/pages/canteenDetails/canteenDetails?id=' + e.currentTarget.dataset.id,
    })
  },
  clickCollection: function (e) {
    let _this = this;
    let i = e.target.dataset.num;
    console.log("i=" + i);
    let token = getApp().globalData.token;
    // 如果用户未登录
    if (!token) {
      console.log("登录后才可进行收藏");
      return;
    }
    // 用户已登录
    _this.setData({
      ["canteen_list[" + i + "].isCollected"]: !_this.data.canteen_list[i].isCollected
    });
    wx.request({
      url: 'http://114.132.234.161:8888/bajie/collection/',
      method: 'POST',
      header: {
        'token': token
      },
      data: {
        type: 0,
        targetId: _this.data.canteen_list[i].canteenId,
        isCollected: _this.data.canteen_list[i].isCollected ? 1 : 0
      },
      success: (res) => {
        console.log(res);
        if (res.data.code != 200) {
          console.log("失败（收藏）", res)
        }
        if (_this.data.canteen_list[i].isCollected) {
          console.log("收藏成功");
        } else {
          console.log("取消收藏成功");
        }
      }
    })
  },
  clickSearch() {
    wx.navigateTo({
      url: '/pages/search/search',
    })
  },
  toMyFavorites() {
    wx.navigateTo({
      url: '/pages/myFavorites/myFavorites',
    })
  }
})